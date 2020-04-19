import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITravelRoute } from 'app/shared/model/travel-route.model';

@Component({
  selector: 'jhi-travel-route-detail',
  templateUrl: './travel-route-detail.component.html'
})
export class TravelRouteDetailComponent implements OnInit {
  travelRoute: ITravelRoute | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ travelRoute }) => (this.travelRoute = travelRoute));
  }

  previousState(): void {
    window.history.back();
  }
}
