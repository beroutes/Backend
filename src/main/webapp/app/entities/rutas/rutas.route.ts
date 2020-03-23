import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRutas, Rutas } from 'app/shared/model/rutas.model';
import { RutasService } from './rutas.service';
import { RutasComponent } from './rutas.component';
import { RutasDetailComponent } from './rutas-detail.component';
import { RutasUpdateComponent } from './rutas-update.component';

@Injectable({ providedIn: 'root' })
export class RutasResolve implements Resolve<IRutas> {
  constructor(private service: RutasService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRutas> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((rutas: HttpResponse<Rutas>) => {
          if (rutas.body) {
            return of(rutas.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Rutas());
  }
}

export const rutasRoute: Routes = [
  {
    path: '',
    component: RutasComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Rutas'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: RutasDetailComponent,
    resolve: {
      rutas: RutasResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Rutas'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: RutasUpdateComponent,
    resolve: {
      rutas: RutasResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Rutas'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: RutasUpdateComponent,
    resolve: {
      rutas: RutasResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Rutas'
    },
    canActivate: [UserRouteAccessService]
  }
];
