import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITravelRoute } from 'app/shared/model/travel-route.model';
import { TravelRouteService } from './travel-route.service';

@Component({
  templateUrl: './travel-route-delete-dialog.component.html'
})
export class TravelRouteDeleteDialogComponent {
  travelRoute?: ITravelRoute;

  constructor(
    protected travelRouteService: TravelRouteService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.travelRouteService.delete(id).subscribe(() => {
      this.eventManager.broadcast('travelRouteListModification');
      this.activeModal.close();
    });
  }
}
