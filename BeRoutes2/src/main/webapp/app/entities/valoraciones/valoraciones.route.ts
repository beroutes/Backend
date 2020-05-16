import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IValoraciones, Valoraciones } from 'app/shared/model/valoraciones.model';
import { ValoracionesService } from './valoraciones.service';
import { ValoracionesComponent } from './valoraciones.component';
import { ValoracionesDetailComponent } from './valoraciones-detail.component';
import { ValoracionesUpdateComponent } from './valoraciones-update.component';

@Injectable({ providedIn: 'root' })
export class ValoracionesResolve implements Resolve<IValoraciones> {
  constructor(private service: ValoracionesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IValoraciones> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((valoraciones: HttpResponse<Valoraciones>) => {
          if (valoraciones.body) {
            return of(valoraciones.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Valoraciones());
  }
}

export const valoracionesRoute: Routes = [
  {
    path: '',
    component: ValoracionesComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Valoraciones'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ValoracionesDetailComponent,
    resolve: {
      valoraciones: ValoracionesResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Valoraciones'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ValoracionesUpdateComponent,
    resolve: {
      valoraciones: ValoracionesResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Valoraciones'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ValoracionesUpdateComponent,
    resolve: {
      valoraciones: ValoracionesResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Valoraciones'
    },
    canActivate: [UserRouteAccessService]
  }
];
