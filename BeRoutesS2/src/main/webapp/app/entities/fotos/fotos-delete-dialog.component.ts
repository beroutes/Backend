import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFotos } from 'app/shared/model/fotos.model';
import { FotosService } from './fotos.service';

@Component({
  templateUrl: './fotos-delete-dialog.component.html'
})
export class FotosDeleteDialogComponent {
  fotos?: IFotos;

  constructor(protected fotosService: FotosService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.fotosService.delete(id).subscribe(() => {
      this.eventManager.broadcast('fotosListModification');
      this.activeModal.close();
    });
  }
}
