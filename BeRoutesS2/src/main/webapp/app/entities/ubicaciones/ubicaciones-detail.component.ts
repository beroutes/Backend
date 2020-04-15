import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUbicaciones } from 'app/shared/model/ubicaciones.model';

@Component({
  selector: 'jhi-ubicaciones-detail',
  templateUrl: './ubicaciones-detail.component.html'
})
export class UbicacionesDetailComponent implements OnInit {
  ubicaciones: IUbicaciones | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ubicaciones }) => (this.ubicaciones = ubicaciones));
  }

  previousState(): void {
    window.history.back();
  }
}
