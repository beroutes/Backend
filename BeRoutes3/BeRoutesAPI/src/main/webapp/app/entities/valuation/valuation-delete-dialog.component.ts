import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IValuation } from 'app/shared/model/valuation.model';
import { ValuationService } from './valuation.service';

@Component({
  templateUrl: './valuation-delete-dialog.component.html'
})
export class ValuationDeleteDialogComponent {
  valuation?: IValuation;

  constructor(protected valuationService: ValuationService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.valuationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('valuationListModification');
      this.activeModal.close();
    });
  }
}
