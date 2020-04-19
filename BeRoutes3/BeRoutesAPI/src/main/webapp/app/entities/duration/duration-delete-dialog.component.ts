import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDuration } from 'app/shared/model/duration.model';
import { DurationService } from './duration.service';

@Component({
  templateUrl: './duration-delete-dialog.component.html'
})
export class DurationDeleteDialogComponent {
  duration?: IDuration;

  constructor(protected durationService: DurationService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.durationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('durationListModification');
      this.activeModal.close();
    });
  }
}
