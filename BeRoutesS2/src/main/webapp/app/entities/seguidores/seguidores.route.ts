import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISeguidores, Seguidores } from 'app/shared/model/seguidores.model';
import { SeguidoresService } from './seguidores.service';
import { SeguidoresComponent } from './seguidores.component';
import { SeguidoresDetailComponent } from './seguidores-detail.component';
import { SeguidoresUpdateComponent } from './seguidores-update.component';

@Injectable({ providedIn: 'root' })
export class SeguidoresResolve implements Resolve<ISeguidores> {
  constructor(private service: SeguidoresService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISeguidores> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((seguidores: HttpResponse<Seguidores>) => {
          if (seguidores.body) {
            return of(seguidores.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Seguidores());
  }
}

export const seguidoresRoute: Routes = [
  {
    path: '',
    component: SeguidoresComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Seguidores'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SeguidoresDetailComponent,
    resolve: {
      seguidores: SeguidoresResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Seguidores'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SeguidoresUpdateComponent,
    resolve: {
      seguidores: SeguidoresResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Seguidores'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SeguidoresUpdateComponent,
    resolve: {
      seguidores: SeguidoresResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Seguidores'
    },
    canActivate: [UserRouteAccessService]
  }
];
