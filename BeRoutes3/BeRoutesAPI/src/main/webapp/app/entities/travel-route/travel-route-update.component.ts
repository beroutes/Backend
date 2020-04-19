import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITravelRoute, TravelRoute } from 'app/shared/model/travel-route.model';
import { TravelRouteService } from './travel-route.service';
import { IDuration } from 'app/shared/model/duration.model';
import { DurationService } from 'app/entities/duration/duration.service';
import { ICategory } from 'app/shared/model/category.model';
import { CategoryService } from 'app/entities/category/category.service';
import { IUserProfile } from 'app/shared/model/user-profile.model';
import { UserProfileService } from 'app/entities/user-profile/user-profile.service';

type SelectableEntity = IDuration | ICategory | IUserProfile;

@Component({
  selector: 'jhi-travel-route-update',
  templateUrl: './travel-route-update.component.html'
})
export class TravelRouteUpdateComponent implements OnInit {
  isSaving = false;
  durations: IDuration[] = [];
  categories: ICategory[] = [];
  userprofiles: IUserProfile[] = [];

  editForm = this.fb.group({
    id: [],
    titleRoute: [],
    destination: [],
    season: [],
    budget: [],
    descriptionRoute: [],
    createdAt: [],
    updatedAt: [],
    duration: [],
    category: [],
    userProfile: []
  });

  constructor(
    protected travelRouteService: TravelRouteService,
    protected durationService: DurationService,
    protected categoryService: CategoryService,
    protected userProfileService: UserProfileService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ travelRoute }) => {
      if (!travelRoute.id) {
        const today = moment().startOf('day');
        travelRoute.createdAt = today;
        travelRoute.updatedAt = today;
      }

      this.updateForm(travelRoute);

      this.durationService
        .query({ filter: 'travelroute-is-null' })
        .pipe(
          map((res: HttpResponse<IDuration[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IDuration[]) => {
          if (!travelRoute.duration || !travelRoute.duration.id) {
            this.durations = resBody;
          } else {
            this.durationService
              .find(travelRoute.duration.id)
              .pipe(
                map((subRes: HttpResponse<IDuration>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IDuration[]) => (this.durations = concatRes));
          }
        });

      this.categoryService
        .query({ filter: 'travelroute-is-null' })
        .pipe(
          map((res: HttpResponse<ICategory[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ICategory[]) => {
          if (!travelRoute.category || !travelRoute.category.id) {
            this.categories = resBody;
          } else {
            this.categoryService
              .find(travelRoute.category.id)
              .pipe(
                map((subRes: HttpResponse<ICategory>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ICategory[]) => (this.categories = concatRes));
          }
        });

      this.userProfileService.query().subscribe((res: HttpResponse<IUserProfile[]>) => (this.userprofiles = res.body || []));
    });
  }

  updateForm(travelRoute: ITravelRoute): void {
    this.editForm.patchValue({
      id: travelRoute.id,
      titleRoute: travelRoute.titleRoute,
      destination: travelRoute.destination,
      season: travelRoute.season,
      budget: travelRoute.budget,
      descriptionRoute: travelRoute.descriptionRoute,
      createdAt: travelRoute.createdAt ? travelRoute.createdAt.format(DATE_TIME_FORMAT) : null,
      updatedAt: travelRoute.updatedAt ? travelRoute.updatedAt.format(DATE_TIME_FORMAT) : null,
      duration: travelRoute.duration,
      category: travelRoute.category,
      userProfile: travelRoute.userProfile
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const travelRoute = this.createFromForm();
    if (travelRoute.id !== undefined) {
      this.subscribeToSaveResponse(this.travelRouteService.update(travelRoute));
    } else {
      this.subscribeToSaveResponse(this.travelRouteService.create(travelRoute));
    }
  }

  private createFromForm(): ITravelRoute {
    return {
      ...new TravelRoute(),
      id: this.editForm.get(['id'])!.value,
      titleRoute: this.editForm.get(['titleRoute'])!.value,
      destination: this.editForm.get(['destination'])!.value,
      season: this.editForm.get(['season'])!.value,
      budget: this.editForm.get(['budget'])!.value,
      descriptionRoute: this.editForm.get(['descriptionRoute'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedAt: this.editForm.get(['updatedAt'])!.value ? moment(this.editForm.get(['updatedAt'])!.value, DATE_TIME_FORMAT) : undefined,
      duration: this.editForm.get(['duration'])!.value,
      category: this.editForm.get(['category'])!.value,
      userProfile: this.editForm.get(['userProfile'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITravelRoute>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
