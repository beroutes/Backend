import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRutas, Rutas } from 'app/shared/model/rutas.model';
import { RutasService } from './rutas.service';

@Component({
  selector: 'jhi-rutas-update',
  templateUrl: './rutas-update.component.html'
})
export class RutasUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    titulo: [null, [Validators.required, Validators.minLength(5), Validators.maxLength(30)]],
    duracion: [null, [Validators.required]],
    temporada: [null, [Validators.required]],
    presupuesto: [null, [Validators.required]],
    descripcion: [null, [Validators.required, Validators.minLength(10)]]
  });

  constructor(protected rutasService: RutasService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rutas }) => {
      this.updateForm(rutas);
    });
  }

  updateForm(rutas: IRutas): void {
    this.editForm.patchValue({
      id: rutas.id,
      titulo: rutas.titulo,
      duracion: rutas.duracion,
      temporada: rutas.temporada,
      presupuesto: rutas.presupuesto,
      descripcion: rutas.descripcion
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const rutas = this.createFromForm();
    if (rutas.id !== undefined) {
      this.subscribeToSaveResponse(this.rutasService.update(rutas));
    } else {
      this.subscribeToSaveResponse(this.rutasService.create(rutas));
    }
  }

  private createFromForm(): IRutas {
    return {
      ...new Rutas(),
      id: this.editForm.get(['id'])!.value,
      titulo: this.editForm.get(['titulo'])!.value,
      duracion: this.editForm.get(['duracion'])!.value,
      temporada: this.editForm.get(['temporada'])!.value,
      presupuesto: this.editForm.get(['presupuesto'])!.value,
      descripcion: this.editForm.get(['descripcion'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRutas>>): void {
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
