import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICiudades } from 'app/shared/model/ciudades.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { CiudadesService } from './ciudades.service';
import { CiudadesDeleteDialogComponent } from './ciudades-delete-dialog.component';

@Component({
  selector: 'jhi-ciudades',
  templateUrl: './ciudades.component.html'
})
export class CiudadesComponent implements OnInit, OnDestroy {
  ciudades: ICiudades[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected ciudadesService: CiudadesService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.ciudades = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.ciudadesService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<ICiudades[]>) => this.paginateCiudades(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.ciudades = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCiudades();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICiudades): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCiudades(): void {
    this.eventSubscriber = this.eventManager.subscribe('ciudadesListModification', () => this.reset());
  }

  delete(ciudades: ICiudades): void {
    const modalRef = this.modalService.open(CiudadesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ciudades = ciudades;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateCiudades(data: ICiudades[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.ciudades.push(data[i]);
      }
    }
  }
}
