import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ILocation, Location } from 'app/shared/model/location.model';
import { LocationService } from './location.service';
import { IDuration } from 'app/shared/model/duration.model';
import { DurationService } from 'app/entities/duration/duration.service';
import { IPhoto } from 'app/shared/model/photo.model';
import { PhotoService } from 'app/entities/photo/photo.service';
import { ICountry } from 'app/shared/model/country.model';
import { CountryService } from 'app/entities/country/country.service';
import { ITravelRoute } from 'app/shared/model/travel-route.model';
import { TravelRouteService } from 'app/entities/travel-route/travel-route.service';

type SelectableEntity = IDuration | IPhoto | ICountry | ITravelRoute;

@Component({
  selector: 'jhi-location-update',
  templateUrl: './location-update.component.html'
})
export class LocationUpdateComponent implements OnInit {
  isSaving = false;
  durations: IDuration[] = [];
  photos: IPhoto[] = [];
  countries: ICountry[] = [];
  travelroutes: ITravelRoute[] = [];

  editForm = this.fb.group({
    id: [],
    stepPosition: [null, [Validators.required]],
    titleLocation: [null, [Validators.required]],
    descriptionLocation: [null, [Validators.required]],
    xCoordinate: [],
    yCoordinate: [],
    stepDuration: [],
    qrCode: [],
    qrDescription: [],
    createdAt: [],
    updatedAt: [],
    duration: [],
    photo: [],
    country: [],
    travelRoute: []
  });

  constructor(
    protected locationService: LocationService,
    protected durationService: DurationService,
    protected photoService: PhotoService,
    protected countryService: CountryService,
    protected travelRouteService: TravelRouteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ location }) => {
      if (!location.id) {
        const today = moment().startOf('day');
        location.createdAt = today;
        location.updatedAt = today;
      }

      this.updateForm(location);

      this.durationService
        .query({ filter: 'location-is-null' })
        .pipe(
          map((res: HttpResponse<IDuration[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IDuration[]) => {
          if (!location.duration || !location.duration.id) {
            this.durations = resBody;
          } else {
            this.durationService
              .find(location.duration.id)
              .pipe(
                map((subRes: HttpResponse<IDuration>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IDuration[]) => (this.durations = concatRes));
          }
        });

      this.photoService
        .query({ filter: 'location-is-null' })
        .pipe(
          map((res: HttpResponse<IPhoto[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPhoto[]) => {
          if (!location.photo || !location.photo.id) {
            this.photos = resBody;
          } else {
            this.photoService
              .find(location.photo.id)
              .pipe(
                map((subRes: HttpResponse<IPhoto>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPhoto[]) => (this.photos = concatRes));
          }
        });

      this.countryService.query().subscribe((res: HttpResponse<ICountry[]>) => (this.countries = res.body || []));

      this.travelRouteService.query().subscribe((res: HttpResponse<ITravelRoute[]>) => (this.travelroutes = res.body || []));
    });
  }

  updateForm(location: ILocation): void {
    this.editForm.patchValue({
      id: location.id,
      stepPosition: location.stepPosition,
      titleLocation: location.titleLocation,
      descriptionLocation: location.descriptionLocation,
      xCoordinate: location.xCoordinate,
      yCoordinate: location.yCoordinate,
      stepDuration: location.stepDuration,
      qrCode: location.qrCode,
      qrDescription: location.qrDescription,
      createdAt: location.createdAt ? location.createdAt.format(DATE_TIME_FORMAT) : null,
      updatedAt: location.updatedAt ? location.updatedAt.format(DATE_TIME_FORMAT) : null,
      duration: location.duration,
      photo: location.photo,
      country: location.country,
      travelRoute: location.travelRoute
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const location = this.createFromForm();
    if (location.id !== undefined) {
      this.subscribeToSaveResponse(this.locationService.update(location));
    } else {
      this.subscribeToSaveResponse(this.locationService.create(location));
    }
  }

  private createFromForm(): ILocation {
    return {
      ...new Location(),
      id: this.editForm.get(['id'])!.value,
      stepPosition: this.editForm.get(['stepPosition'])!.value,
      titleLocation: this.editForm.get(['titleLocation'])!.value,
      descriptionLocation: this.editForm.get(['descriptionLocation'])!.value,
      xCoordinate: this.editForm.get(['xCoordinate'])!.value,
      yCoordinate: this.editForm.get(['yCoordinate'])!.value,
      stepDuration: this.editForm.get(['stepDuration'])!.value,
      qrCode: this.editForm.get(['qrCode'])!.value,
      qrDescription: this.editForm.get(['qrDescription'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedAt: this.editForm.get(['updatedAt'])!.value ? moment(this.editForm.get(['updatedAt'])!.value, DATE_TIME_FORMAT) : undefined,
      duration: this.editForm.get(['duration'])!.value,
      photo: this.editForm.get(['photo'])!.value,
      country: this.editForm.get(['country'])!.value,
      travelRoute: this.editForm.get(['travelRoute'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILocation>>): void {
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
