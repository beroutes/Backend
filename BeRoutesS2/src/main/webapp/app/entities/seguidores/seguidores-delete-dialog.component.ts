import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISeguidores } from 'app/shared/model/seguidores.model';
import { SeguidoresService } from './seguidores.service';

@Component({
  templateUrl: './seguidores-delete-dialog.component.html'
})
export class SeguidoresDeleteDialogComponent {
  seguidores?: ISeguidores;

  constructor(
    protected seguidoresService: SeguidoresService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.seguidoresService.delete(id).subscribe(() => {
      this.eventManager.broadcast('seguidoresListModification');
      this.activeModal.close();
    });
  }
}
