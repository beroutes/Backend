import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPaises } from 'app/shared/model/paises.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { PaisesService } from './paises.service';
import { PaisesDeleteDialogComponent } from './paises-delete-dialog.component';

@Component({
  selector: 'jhi-paises',
  templateUrl: './paises.component.html'
})
export class PaisesComponent implements OnInit, OnDestroy {
  paises: IPaises[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected paisesService: PaisesService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.paises = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.paisesService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IPaises[]>) => this.paginatePaises(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.paises = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPaises();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPaises): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPaises(): void {
    this.eventSubscriber = this.eventManager.subscribe('paisesListModification', () => this.reset());
  }

  delete(paises: IPaises): void {
    const modalRef = this.modalService.open(PaisesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.paises = paises;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginatePaises(data: IPaises[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.paises.push(data[i]);
      }
    }
  }
}
