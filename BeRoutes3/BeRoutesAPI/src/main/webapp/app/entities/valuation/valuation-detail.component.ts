import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IValuation } from 'app/shared/model/valuation.model';

@Component({
  selector: 'jhi-valuation-detail',
  templateUrl: './valuation-detail.component.html'
})
export class ValuationDetailComponent implements OnInit {
  valuation: IValuation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ valuation }) => (this.valuation = valuation));
  }

  previousState(): void {
    window.history.back();
  }
}
