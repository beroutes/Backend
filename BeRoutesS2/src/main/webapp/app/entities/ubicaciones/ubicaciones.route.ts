import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUbicaciones, Ubicaciones } from 'app/shared/model/ubicaciones.model';
import { UbicacionesService } from './ubicaciones.service';
import { UbicacionesComponent } from './ubicaciones.component';
import { UbicacionesDetailComponent } from './ubicaciones-detail.component';
import { UbicacionesUpdateComponent } from './ubicaciones-update.component';

@Injectable({ providedIn: 'root' })
export class UbicacionesResolve implements Resolve<IUbicaciones> {
  constructor(private service: UbicacionesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUbicaciones> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ubicaciones: HttpResponse<Ubicaciones>) => {
          if (ubicaciones.body) {
            return of(ubicaciones.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Ubicaciones());
  }
}

export const ubicacionesRoute: Routes = [
  {
    path: '',
    component: UbicacionesComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Ubicaciones'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: UbicacionesDetailComponent,
    resolve: {
      ubicaciones: UbicacionesResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Ubicaciones'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: UbicacionesUpdateComponent,
    resolve: {
      ubicaciones: UbicacionesResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Ubicaciones'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: UbicacionesUpdateComponent,
    resolve: {
      ubicaciones: UbicacionesResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Ubicaciones'
    },
    canActivate: [UserRouteAccessService]
  }
];
