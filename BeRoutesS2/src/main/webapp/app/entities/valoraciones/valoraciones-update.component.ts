import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IValoraciones, Valoraciones } from 'app/shared/model/valoraciones.model';
import { ValoracionesService } from './valoraciones.service';

@Component({
  selector: 'jhi-valoraciones-update',
  templateUrl: './valoraciones-update.component.html'
})
export class ValoracionesUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    estrellas: [],
    comentarios: []
  });

  constructor(protected valoracionesService: ValoracionesService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ valoraciones }) => {
      this.updateForm(valoraciones);
    });
  }

  updateForm(valoraciones: IValoraciones): void {
    this.editForm.patchValue({
      id: valoraciones.id,
      estrellas: valoraciones.estrellas,
      comentarios: valoraciones.comentarios
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const valoraciones = this.createFromForm();
    if (valoraciones.id !== undefined) {
      this.subscribeToSaveResponse(this.valoracionesService.update(valoraciones));
    } else {
      this.subscribeToSaveResponse(this.valoracionesService.create(valoraciones));
    }
  }

  private createFromForm(): IValoraciones {
    return {
      ...new Valoraciones(),
      id: this.editForm.get(['id'])!.value,
      estrellas: this.editForm.get(['estrellas'])!.value,
      comentarios: this.editForm.get(['comentarios'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IValoraciones>>): void {
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
