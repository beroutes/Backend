import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IUserProfile, UserProfile } from 'app/shared/model/user-profile.model';
import { UserProfileService } from './user-profile.service';
import { ICountry } from 'app/shared/model/country.model';
import { CountryService } from 'app/entities/country/country.service';
import { IPhoto } from 'app/shared/model/photo.model';
import { PhotoService } from 'app/entities/photo/photo.service';
import { IFollower } from 'app/shared/model/follower.model';
import { FollowerService } from 'app/entities/follower/follower.service';

type SelectableEntity = ICountry | IPhoto | IFollower;

@Component({
  selector: 'jhi-user-profile-update',
  templateUrl: './user-profile-update.component.html'
})
export class UserProfileUpdateComponent implements OnInit {
  isSaving = false;
  countries: ICountry[] = [];
  photos: IPhoto[] = [];
  followers: IFollower[] = [];

  editForm = this.fb.group({
    id: [],
    firstName: [null, [Validators.required]],
    lastName: [null, [Validators.required]],
    email: [null, [Validators.required]],
    telephone: [],
    userName: [],
    password: [],
    age: [],
    biography: [],
    createdAt: [],
    updatedAt: [],
    country: [],
    photo: [],
    followers: []
  });

  constructor(
    protected userProfileService: UserProfileService,
    protected countryService: CountryService,
    protected photoService: PhotoService,
    protected followerService: FollowerService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userProfile }) => {
      if (!userProfile.id) {
        const today = moment().startOf('day');
        userProfile.createdAt = today;
        userProfile.updatedAt = today;
      }

      this.updateForm(userProfile);

      this.countryService
        .query({ filter: 'userprofile-is-null' })
        .pipe(
          map((res: HttpResponse<ICountry[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ICountry[]) => {
          if (!userProfile.country || !userProfile.country.id) {
            this.countries = resBody;
          } else {
            this.countryService
              .find(userProfile.country.id)
              .pipe(
                map((subRes: HttpResponse<ICountry>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ICountry[]) => (this.countries = concatRes));
          }
        });

      this.photoService
        .query({ filter: 'userprofile-is-null' })
        .pipe(
          map((res: HttpResponse<IPhoto[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPhoto[]) => {
          if (!userProfile.photo || !userProfile.photo.id) {
            this.photos = resBody;
          } else {
            this.photoService
              .find(userProfile.photo.id)
              .pipe(
                map((subRes: HttpResponse<IPhoto>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPhoto[]) => (this.photos = concatRes));
          }
        });

      this.followerService.query().subscribe((res: HttpResponse<IFollower[]>) => (this.followers = res.body || []));
    });
  }

  updateForm(userProfile: IUserProfile): void {
    this.editForm.patchValue({
      id: userProfile.id,
      firstName: userProfile.firstName,
      lastName: userProfile.lastName,
      email: userProfile.email,
      telephone: userProfile.telephone,
      userName: userProfile.userName,
      password: userProfile.password,
      age: userProfile.age,
      biography: userProfile.biography,
      createdAt: userProfile.createdAt ? userProfile.createdAt.format(DATE_TIME_FORMAT) : null,
      updatedAt: userProfile.updatedAt ? userProfile.updatedAt.format(DATE_TIME_FORMAT) : null,
      country: userProfile.country,
      photo: userProfile.photo,
      followers: userProfile.followers
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userProfile = this.createFromForm();
    if (userProfile.id !== undefined) {
      this.subscribeToSaveResponse(this.userProfileService.update(userProfile));
    } else {
      this.subscribeToSaveResponse(this.userProfileService.create(userProfile));
    }
  }

  private createFromForm(): IUserProfile {
    return {
      ...new UserProfile(),
      id: this.editForm.get(['id'])!.value,
      firstName: this.editForm.get(['firstName'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      email: this.editForm.get(['email'])!.value,
      telephone: this.editForm.get(['telephone'])!.value,
      userName: this.editForm.get(['userName'])!.value,
      password: this.editForm.get(['password'])!.value,
      age: this.editForm.get(['age'])!.value,
      biography: this.editForm.get(['biography'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedAt: this.editForm.get(['updatedAt'])!.value ? moment(this.editForm.get(['updatedAt'])!.value, DATE_TIME_FORMAT) : undefined,
      country: this.editForm.get(['country'])!.value,
      photo: this.editForm.get(['photo'])!.value,
      followers: this.editForm.get(['followers'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserProfile>>): void {
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

  getSelected(selectedVals: IFollower[], option: IFollower): IFollower {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
