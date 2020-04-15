import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRutas } from 'app/shared/model/rutas.model';

@Component({
  selector: 'jhi-rutas-detail',
  templateUrl: './rutas-detail.component.html'
})
export class RutasDetailComponent implements OnInit {
  rutas: IRutas | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rutas }) => (this.rutas = rutas));
  }

  previousState(): void {
    window.history.back();
  }
}
