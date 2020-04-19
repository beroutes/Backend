import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDuration, Duration } from 'app/shared/model/duration.model';
import { DurationService } from './duration.service';
import { DurationComponent } from './duration.component';
import { DurationDetailComponent } from './duration-detail.component';
import { DurationUpdateComponent } from './duration-update.component';

@Injectable({ providedIn: 'root' })
export class DurationResolve implements Resolve<IDuration> {
  constructor(private service: DurationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDuration> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((duration: HttpResponse<Duration>) => {
          if (duration.body) {
            return of(duration.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Duration());
  }
}

export const durationRoute: Routes = [
  {
    path: '',
    component: DurationComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Durations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DurationDetailComponent,
    resolve: {
      duration: DurationResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Durations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DurationUpdateComponent,
    resolve: {
      duration: DurationResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Durations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DurationUpdateComponent,
    resolve: {
      duration: DurationResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Durations'
    },
    canActivate: [UserRouteAccessService]
  }
];
