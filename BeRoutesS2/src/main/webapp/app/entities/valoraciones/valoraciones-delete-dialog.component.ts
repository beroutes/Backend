import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IValoraciones } from 'app/shared/model/valoraciones.model';
import { ValoracionesService } from './valoraciones.service';

@Component({
  templateUrl: './valoraciones-delete-dialog.component.html'
})
export class ValoracionesDeleteDialogComponent {
  valoraciones?: IValoraciones;

  constructor(
    protected valoracionesService: ValoracionesService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.valoracionesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('valoracionesListModification');
      this.activeModal.close();
    });
  }
}
