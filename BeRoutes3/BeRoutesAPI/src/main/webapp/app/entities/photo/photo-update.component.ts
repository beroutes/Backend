import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPhoto, Photo } from 'app/shared/model/photo.model';
import { PhotoService } from './photo.service';
import { ITravelRoute } from 'app/shared/model/travel-route.model';
import { TravelRouteService } from 'app/entities/travel-route/travel-route.service';

@Component({
  selector: 'jhi-photo-update',
  templateUrl: './photo-update.component.html'
})
export class PhotoUpdateComponent implements OnInit {
  isSaving = false;
  travelroutes: ITravelRoute[] = [];

  editForm = this.fb.group({
    id: [],
    titlePhoto: [],
    descriptionPhoto: [],
    urlPhoto: [],
    travelRoute: []
  });

  constructor(
    protected photoService: PhotoService,
    protected travelRouteService: TravelRouteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ photo }) => {
      this.updateForm(photo);

      this.travelRouteService.query().subscribe((res: HttpResponse<ITravelRoute[]>) => (this.travelroutes = res.body || []));
    });
  }

  updateForm(photo: IPhoto): void {
    this.editForm.patchValue({
      id: photo.id,
      titlePhoto: photo.titlePhoto,
      descriptionPhoto: photo.descriptionPhoto,
      urlPhoto: photo.urlPhoto,
      travelRoute: photo.travelRoute
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const photo = this.createFromForm();
    if (photo.id !== undefined) {
      this.subscribeToSaveResponse(this.photoService.update(photo));
    } else {
      this.subscribeToSaveResponse(this.photoService.create(photo));
    }
  }

  private createFromForm(): IPhoto {
    return {
      ...new Photo(),
      id: this.editForm.get(['id'])!.value,
      titlePhoto: this.editForm.get(['titlePhoto'])!.value,
      descriptionPhoto: this.editForm.get(['descriptionPhoto'])!.value,
      urlPhoto: this.editForm.get(['urlPhoto'])!.value,
      travelRoute: this.editForm.get(['travelRoute'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPhoto>>): void {
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

  trackById(index: number, item: ITravelRoute): any {
    return item.id;
  }
}
