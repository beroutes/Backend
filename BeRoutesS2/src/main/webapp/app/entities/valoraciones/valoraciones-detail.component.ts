import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IValoraciones } from 'app/shared/model/valoraciones.model';

@Component({
  selector: 'jhi-valoraciones-detail',
  templateUrl: './valoraciones-detail.component.html'
})
export class ValoracionesDetailComponent implements OnInit {
  valoraciones: IValoraciones | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ valoraciones }) => (this.valoraciones = valoraciones));
  }

  previousState(): void {
    window.history.back();
  }
}
