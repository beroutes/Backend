import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRutas } from 'app/shared/model/rutas.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { RutasService } from './rutas.service';
import { RutasDeleteDialogComponent } from './rutas-delete-dialog.component';

@Component({
  selector: 'jhi-rutas',
  templateUrl: './rutas.component.html'
})
export class RutasComponent implements OnInit, OnDestroy {
  rutas: IRutas[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected rutasService: RutasService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.rutas = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.rutasService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IRutas[]>) => this.paginateRutas(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.rutas = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInRutas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRutas): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRutas(): void {
    this.eventSubscriber = this.eventManager.subscribe('rutasListModification', () => this.reset());
  }

  delete(rutas: IRutas): void {
    const modalRef = this.modalService.open(RutasDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.rutas = rutas;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateRutas(data: IRutas[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.rutas.push(data[i]);
      }
    }
  }
}
