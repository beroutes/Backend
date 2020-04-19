import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ICountry, Country } from 'app/shared/model/country.model';
import { CountryService } from './country.service';
import { IRegion } from 'app/shared/model/region.model';
import { RegionService } from 'app/entities/region/region.service';
import { ICity } from 'app/shared/model/city.model';
import { CityService } from 'app/entities/city/city.service';

type SelectableEntity = IRegion | ICity;

@Component({
  selector: 'jhi-country-update',
  templateUrl: './country-update.component.html'
})
export class CountryUpdateComponent implements OnInit {
  isSaving = false;
  regions: IRegion[] = [];
  cities: ICity[] = [];

  editForm = this.fb.group({
    id: [],
    countryName: [null, [Validators.required]],
    region: [],
    city: []
  });

  constructor(
    protected countryService: CountryService,
    protected regionService: RegionService,
    protected cityService: CityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ country }) => {
      this.updateForm(country);

      this.regionService
        .query({ filter: 'country-is-null' })
        .pipe(
          map((res: HttpResponse<IRegion[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IRegion[]) => {
          if (!country.region || !country.region.id) {
            this.regions = resBody;
          } else {
            this.regionService
              .find(country.region.id)
              .pipe(
                map((subRes: HttpResponse<IRegion>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IRegion[]) => (this.regions = concatRes));
          }
        });

      this.cityService
        .query({ filter: 'country-is-null' })
        .pipe(
          map((res: HttpResponse<ICity[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ICity[]) => {
          if (!country.city || !country.city.id) {
            this.cities = resBody;
          } else {
            this.cityService
              .find(country.city.id)
              .pipe(
                map((subRes: HttpResponse<ICity>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ICity[]) => (this.cities = concatRes));
          }
        });
    });
  }

  updateForm(country: ICountry): void {
    this.editForm.patchValue({
      id: country.id,
      countryName: country.countryName,
      region: country.region,
      city: country.city
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const country = this.createFromForm();
    if (country.id !== undefined) {
      this.subscribeToSaveResponse(this.countryService.update(country));
    } else {
      this.subscribeToSaveResponse(this.countryService.create(country));
    }
  }

  private createFromForm(): ICountry {
    return {
      ...new Country(),
      id: this.editForm.get(['id'])!.value,
      countryName: this.editForm.get(['countryName'])!.value,
      region: this.editForm.get(['region'])!.value,
      city: this.editForm.get(['city'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICountry>>): void {
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
