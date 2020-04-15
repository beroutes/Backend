import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFotos } from 'app/shared/model/fotos.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { FotosService } from './fotos.service';
import { FotosDeleteDialogComponent } from './fotos-delete-dialog.component';

@Component({
  selector: 'jhi-fotos',
  templateUrl: './fotos.component.html'
})
export class FotosComponent implements OnInit, OnDestroy {
  fotos: IFotos[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected fotosService: FotosService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.fotos = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.fotosService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IFotos[]>) => this.paginateFotos(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.fotos = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInFotos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IFotos): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInFotos(): void {
    this.eventSubscriber = this.eventManager.subscribe('fotosListModification', () => this.reset());
  }

  delete(fotos: IFotos): void {
    const modalRef = this.modalService.open(FotosDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.fotos = fotos;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateFotos(data: IFotos[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.fotos.push(data[i]);
      }
    }
  }
}
