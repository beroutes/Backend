import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IValoraciones } from 'app/shared/model/valoraciones.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ValoracionesService } from './valoraciones.service';
import { ValoracionesDeleteDialogComponent } from './valoraciones-delete-dialog.component';

@Component({
  selector: 'jhi-valoraciones',
  templateUrl: './valoraciones.component.html'
})
export class ValoracionesComponent implements OnInit, OnDestroy {
  valoraciones: IValoraciones[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected valoracionesService: ValoracionesService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.valoraciones = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.valoracionesService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IValoraciones[]>) => this.paginateValoraciones(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.valoraciones = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInValoraciones();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IValoraciones): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInValoraciones(): void {
    this.eventSubscriber = this.eventManager.subscribe('valoracionesListModification', () => this.reset());
  }

  delete(valoraciones: IValoraciones): void {
    const modalRef = this.modalService.open(ValoracionesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.valoraciones = valoraciones;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateValoraciones(data: IValoraciones[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.valoraciones.push(data[i]);
      }
    }
  }
}
