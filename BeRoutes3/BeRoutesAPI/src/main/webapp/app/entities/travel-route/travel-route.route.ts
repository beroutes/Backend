import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITravelRoute, TravelRoute } from 'app/shared/model/travel-route.model';
import { TravelRouteService } from './travel-route.service';
import { TravelRouteComponent } from './travel-route.component';
import { TravelRouteDetailComponent } from './travel-route-detail.component';
import { TravelRouteUpdateComponent } from './travel-route-update.component';

@Injectable({ providedIn: 'root' })
export class TravelRouteResolve implements Resolve<ITravelRoute> {
  constructor(private service: TravelRouteService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITravelRoute> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((travelRoute: HttpResponse<TravelRoute>) => {
          if (travelRoute.body) {
            return of(travelRoute.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TravelRoute());
  }
}

export const travelRouteRoute: Routes = [
  {
    path: '',
    component: TravelRouteComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'TravelRoutes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TravelRouteDetailComponent,
    resolve: {
      travelRoute: TravelRouteResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TravelRoutes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TravelRouteUpdateComponent,
    resolve: {
      travelRoute: TravelRouteResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TravelRoutes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TravelRouteUpdateComponent,
    resolve: {
      travelRoute: TravelRouteResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'TravelRoutes'
    },
    canActivate: [UserRouteAccessService]
  }
];
