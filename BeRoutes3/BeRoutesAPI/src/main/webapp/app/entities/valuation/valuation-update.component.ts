import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IValuation, Valuation } from 'app/shared/model/valuation.model';
import { ValuationService } from './valuation.service';
import { ITravelRoute } from 'app/shared/model/travel-route.model';
import { TravelRouteService } from 'app/entities/travel-route/travel-route.service';
import { IUserProfile } from 'app/shared/model/user-profile.model';
import { UserProfileService } from 'app/entities/user-profile/user-profile.service';

type SelectableEntity = ITravelRoute | IUserProfile;

@Component({
  selector: 'jhi-valuation-update',
  templateUrl: './valuation-update.component.html'
})
export class ValuationUpdateComponent implements OnInit {
  isSaving = false;
  travelroutes: ITravelRoute[] = [];
  userprofiles: IUserProfile[] = [];

  editForm = this.fb.group({
    id: [],
    comment: [],
    rating: [],
    travelRoute: [],
    userProfile: []
  });

  constructor(
    protected valuationService: ValuationService,
    protected travelRouteService: TravelRouteService,
    protected userProfileService: UserProfileService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ valuation }) => {
      this.updateForm(valuation);

      this.travelRouteService.query().subscribe((res: HttpResponse<ITravelRoute[]>) => (this.travelroutes = res.body || []));

      this.userProfileService.query().subscribe((res: HttpResponse<IUserProfile[]>) => (this.userprofiles = res.body || []));
    });
  }

  updateForm(valuation: IValuation): void {
    this.editForm.patchValue({
      id: valuation.id,
      comment: valuation.comment,
      rating: valuation.rating,
      travelRoute: valuation.travelRoute,
      userProfile: valuation.userProfile
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const valuation = this.createFromForm();
    if (valuation.id !== undefined) {
      this.subscribeToSaveResponse(this.valuationService.update(valuation));
    } else {
      this.subscribeToSaveResponse(this.valuationService.create(valuation));
    }
  }

  private createFromForm(): IValuation {
    return {
      ...new Valuation(),
      id: this.editForm.get(['id'])!.value,
      comment: this.editForm.get(['comment'])!.value,
      rating: this.editForm.get(['rating'])!.value,
      travelRoute: this.editForm.get(['travelRoute'])!.value,
      userProfile: this.editForm.get(['userProfile'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IValuation>>): void {
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
