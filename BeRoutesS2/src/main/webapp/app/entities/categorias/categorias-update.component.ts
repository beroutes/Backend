import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICategorias, Categorias } from 'app/shared/model/categorias.model';
import { CategoriasService } from './categorias.service';

@Component({
  selector: 'jhi-categorias-update',
  templateUrl: './categorias-update.component.html'
})
export class CategoriasUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nombre: [null, [Validators.required]]
  });

  constructor(protected categoriasService: CategoriasService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ categorias }) => {
      this.updateForm(categorias);
    });
  }

  updateForm(categorias: ICategorias): void {
    this.editForm.patchValue({
      id: categorias.id,
      nombre: categorias.nombre
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const categorias = this.createFromForm();
    if (categorias.id !== undefined) {
      this.subscribeToSaveResponse(this.categoriasService.update(categorias));
    } else {
      this.subscribeToSaveResponse(this.categoriasService.create(categorias));
    }
  }

  private createFromForm(): ICategorias {
    return {
      ...new Categorias(),
      id: this.editForm.get(['id'])!.value,
      nombre: this.editForm.get(['nombre'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICategorias>>): void {
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
