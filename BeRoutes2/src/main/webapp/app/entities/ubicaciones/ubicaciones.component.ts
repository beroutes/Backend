import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUbicaciones } from 'app/shared/model/ubicaciones.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { UbicacionesService } from './ubicaciones.service';
import { UbicacionesDeleteDialogComponent } from './ubicaciones-delete-dialog.component';

@Component({
  selector: 'jhi-ubicaciones',
  templateUrl: './ubicaciones.component.html'
})
export class UbicacionesComponent implements OnInit, OnDestroy {
  ubicaciones: IUbicaciones[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected ubicacionesService: UbicacionesService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.ubicaciones = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.ubicacionesService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IUbicaciones[]>) => this.paginateUbicaciones(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.ubicaciones = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInUbicaciones();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IUbicaciones): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInUbicaciones(): void {
    this.eventSubscriber = this.eventManager.subscribe('ubicacionesListModification', () => this.reset());
  }

  delete(ubicaciones: IUbicaciones): void {
    const modalRef = this.modalService.open(UbicacionesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ubicaciones = ubicaciones;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateUbicaciones(data: IUbicaciones[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.ubicaciones.push(data[i]);
      }
    }
  }
}
