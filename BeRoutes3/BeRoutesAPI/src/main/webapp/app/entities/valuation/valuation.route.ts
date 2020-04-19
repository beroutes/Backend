import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IValuation, Valuation } from 'app/shared/model/valuation.model';
import { ValuationService } from './valuation.service';
import { ValuationComponent } from './valuation.component';
import { ValuationDetailComponent } from './valuation-detail.component';
import { ValuationUpdateComponent } from './valuation-update.component';

@Injectable({ providedIn: 'root' })
export class ValuationResolve implements Resolve<IValuation> {
  constructor(private service: ValuationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IValuation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((valuation: HttpResponse<Valuation>) => {
          if (valuation.body) {
            return of(valuation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Valuation());
  }
}

export const valuationRoute: Routes = [
  {
    path: '',
    component: ValuationComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Valuations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ValuationDetailComponent,
    resolve: {
      valuation: ValuationResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Valuations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ValuationUpdateComponent,
    resolve: {
      valuation: ValuationResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Valuations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ValuationUpdateComponent,
    resolve: {
      valuation: ValuationResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Valuations'
    },
    canActivate: [UserRouteAccessService]
  }
];
