import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISeguidores, Seguidores } from 'app/shared/model/seguidores.model';
import { SeguidoresService } from './seguidores.service';

@Component({
  selector: 'jhi-seguidores-update',
  templateUrl: './seguidores-update.component.html'
})
export class SeguidoresUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: []
  });

  constructor(protected seguidoresService: SeguidoresService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ seguidores }) => {
      this.updateForm(seguidores);
    });
  }

  updateForm(seguidores: ISeguidores): void {
    this.editForm.patchValue({
      id: seguidores.id
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const seguidores = this.createFromForm();
    if (seguidores.id !== undefined) {
      this.subscribeToSaveResponse(this.seguidoresService.update(seguidores));
    } else {
      this.subscribeToSaveResponse(this.seguidoresService.create(seguidores));
    }
  }

  private createFromForm(): ISeguidores {
    return {
      ...new Seguidores(),
      id: this.editForm.get(['id'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISeguidores>>): void {
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
