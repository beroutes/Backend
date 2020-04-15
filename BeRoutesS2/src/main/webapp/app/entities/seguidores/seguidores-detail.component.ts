import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISeguidores } from 'app/shared/model/seguidores.model';

@Component({
  selector: 'jhi-seguidores-detail',
  templateUrl: './seguidores-detail.component.html'
})
export class SeguidoresDetailComponent implements OnInit {
  seguidores: ISeguidores | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ seguidores }) => (this.seguidores = seguidores));
  }

  previousState(): void {
    window.history.back();
  }
}
