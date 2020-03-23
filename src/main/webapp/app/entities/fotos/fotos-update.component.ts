import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFotos, Fotos } from 'app/shared/model/fotos.model';
import { FotosService } from './fotos.service';

@Component({
  selector: 'jhi-fotos-update',
  templateUrl: './fotos-update.component.html'
})
export class FotosUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    titulo: [null, [Validators.required]],
    url: [null, [Validators.required]]
  });

  constructor(protected fotosService: FotosService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fotos }) => {
      this.updateForm(fotos);
    });
  }

  updateForm(fotos: IFotos): void {
    this.editForm.patchValue({
      id: fotos.id,
      titulo: fotos.titulo,
      url: fotos.url
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fotos = this.createFromForm();
    if (fotos.id !== undefined) {
      this.subscribeToSaveResponse(this.fotosService.update(fotos));
    } else {
      this.subscribeToSaveResponse(this.fotosService.create(fotos));
    }
  }

  private createFromForm(): IFotos {
    return {
      ...new Fotos(),
      id: this.editForm.get(['id'])!.value,
      titulo: this.editForm.get(['titulo'])!.value,
      url: this.editForm.get(['url'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFotos>>): void {
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
