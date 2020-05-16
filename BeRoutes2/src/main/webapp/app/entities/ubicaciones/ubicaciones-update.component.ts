import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUbicaciones, Ubicaciones } from 'app/shared/model/ubicaciones.model';
import { UbicacionesService } from './ubicaciones.service';

@Component({
  selector: 'jhi-ubicaciones-update',
  templateUrl: './ubicaciones-update.component.html'
})
export class UbicacionesUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    titulo: [null, [Validators.required]],
    descripcion: [],
    coordenadaX: [null, [Validators.required]],
    coordenadaY: [],
    duracion: [],
    qr: [null, []]
  });

  constructor(protected ubicacionesService: UbicacionesService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ubicaciones }) => {
      this.updateForm(ubicaciones);
    });
  }

  updateForm(ubicaciones: IUbicaciones): void {
    this.editForm.patchValue({
      id: ubicaciones.id,
      titulo: ubicaciones.titulo,
      descripcion: ubicaciones.descripcion,
      coordenadaX: ubicaciones.coordenadaX,
      coordenadaY: ubicaciones.coordenadaY,
      duracion: ubicaciones.duracion,
      qr: ubicaciones.qr
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ubicaciones = this.createFromForm();
    if (ubicaciones.id !== undefined) {
      this.subscribeToSaveResponse(this.ubicacionesService.update(ubicaciones));
    } else {
      this.subscribeToSaveResponse(this.ubicacionesService.create(ubicaciones));
    }
  }

  private createFromForm(): IUbicaciones {
    return {
      ...new Ubicaciones(),
      id: this.editForm.get(['id'])!.value,
      titulo: this.editForm.get(['titulo'])!.value,
      descripcion: this.editForm.get(['descripcion'])!.value,
      coordenadaX: this.editForm.get(['coordenadaX'])!.value,
      coordenadaY: this.editForm.get(['coordenadaY'])!.value,
      duracion: this.editForm.get(['duracion'])!.value,
      qr: this.editForm.get(['qr'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUbicaciones>>): void {
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
}
