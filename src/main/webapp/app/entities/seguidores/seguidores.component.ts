import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISeguidores } from 'app/shared/model/seguidores.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { SeguidoresService } from './seguidores.service';
import { SeguidoresDeleteDialogComponent } from './seguidores-delete-dialog.component';

@Component({
  selector: 'jhi-seguidores',
  templateUrl: './seguidores.component.html'
})
export class SeguidoresComponent implements OnInit, OnDestroy {
  seguidores: ISeguidores[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected seguidoresService: SeguidoresService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.seguidores = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.seguidoresService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<ISeguidores[]>) => this.paginateSeguidores(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.seguidores = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSeguidores();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISeguidores): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSeguidores(): void {
    this.eventSubscriber = this.eventManager.subscribe('seguidoresListModification', () => this.reset());
  }

  delete(seguidores: ISeguidores): void {
    const modalRef = this.modalService.open(SeguidoresDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.seguidores = seguidores;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateSeguidores(data: ISeguidores[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.seguidores.push(data[i]);
      }
    }
  }
}
