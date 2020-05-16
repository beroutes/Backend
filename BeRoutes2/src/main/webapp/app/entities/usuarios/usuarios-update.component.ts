import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IUsuarios, Usuarios } from 'app/shared/model/usuarios.model';
import { UsuariosService } from './usuarios.service';

@Component({
  selector: 'jhi-usuarios-update',
  templateUrl: './usuarios-update.component.html'
})
export class UsuariosUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nombre: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
    apellidos: [null, [Validators.required, Validators.minLength(5), Validators.maxLength(30)]],
    email: [null, [Validators.required, Validators.minLength(5), Validators.maxLength(30), Validators.pattern('^[a-zA-Z0-9]*$')]],
    usuario: [null, [Validators.required, Validators.minLength(6), Validators.maxLength(20), Validators.pattern('^[a-zA-Z0-9]*$')]],
    password: [null, [Validators.required, Validators.minLength(8), Validators.maxLength(30), Validators.pattern('^[a-zA-Z0-9]*$')]],
    fechaRegistro: [null, [Validators.required]],
    ciudad: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
    urlFotoPerfil: [null, [Validators.minLength(5), Validators.maxLength(100000), Validators.pattern('^[a-zA-Z0-9]*$')]]
  });

  constructor(protected usuariosService: UsuariosService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ usuarios }) => {
      if (!usuarios.id) {
        const today = moment().startOf('day');
        usuarios.fechaRegistro = today;
      }

      this.updateForm(usuarios);
    });
  }

  updateForm(usuarios: IUsuarios): void {
    this.editForm.patchValue({
      id: usuarios.id,
      nombre: usuarios.nombre,
      apellidos: usuarios.apellidos,
      email: usuarios.email,
      usuario: usuarios.usuario,
      password: usuarios.password,
      fechaRegistro: usuarios.fechaRegistro ? usuarios.fechaRegistro.format(DATE_TIME_FORMAT) : null,
      ciudad: usuarios.ciudad,
      urlFotoPerfil: usuarios.urlFotoPerfil
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const usuarios = this.createFromForm();
    if (usuarios.id !== undefined) {
      this.subscribeToSaveResponse(this.usuariosService.update(usuarios));
    } else {
      this.subscribeToSaveResponse(this.usuariosService.create(usuarios));
    }
  }

  private createFromForm(): IUsuarios {
    return {
      ...new Usuarios(),
      id: this.editForm.get(['id'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
      apellidos: this.editForm.get(['apellidos'])!.value,
      email: this.editForm.get(['email'])!.value,
      usuario: this.editForm.get(['usuario'])!.value,
      password: this.editForm.get(['password'])!.value,
      fechaRegistro: this.editForm.get(['fechaRegistro'])!.value
        ? moment(this.editForm.get(['fechaRegistro'])!.value, DATE_TIME_FORMAT)
        : undefined,
      ciudad: this.editForm.get(['ciudad'])!.value,
      urlFotoPerfil: this.editForm.get(['urlFotoPerfil'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUsuarios>>): void {
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
