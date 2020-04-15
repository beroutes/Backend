import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRutas } from 'app/shared/model/rutas.model';
import { RutasService } from './rutas.service';

@Component({
  templateUrl: './rutas-delete-dialog.component.html'
})
export class RutasDeleteDialogComponent {
  rutas?: IRutas;

  constructor(protected rutasService: RutasService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.rutasService.delete(id).subscribe(() => {
      this.eventManager.broadcast('rutasListModification');
      this.activeModal.close();
    });
  }
}
