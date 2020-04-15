import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUbicaciones } from 'app/shared/model/ubicaciones.model';
import { UbicacionesService } from './ubicaciones.service';

@Component({
  templateUrl: './ubicaciones-delete-dialog.component.html'
})
export class UbicacionesDeleteDialogComponent {
  ubicaciones?: IUbicaciones;

  constructor(
    protected ubicacionesService: UbicacionesService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ubicacionesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ubicacionesListModification');
      this.activeModal.close();
    });
  }
}
