import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFotos, Fotos } from 'app/shared/model/fotos.model';
import { FotosService } from './fotos.service';
import { FotosComponent } from './fotos.component';
import { FotosDetailComponent } from './fotos-detail.component';
import { FotosUpdateComponent } from './fotos-update.component';

@Injectable({ providedIn: 'root' })
export class FotosResolve implements Resolve<IFotos> {
  constructor(private service: FotosService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFotos> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((fotos: HttpResponse<Fotos>) => {
          if (fotos.body) {
            return of(fotos.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Fotos());
  }
}

export const fotosRoute: Routes = [
  {
    path: '',
    component: FotosComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Fotos'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FotosDetailComponent,
    resolve: {
      fotos: FotosResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Fotos'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FotosUpdateComponent,
    resolve: {
      fotos: FotosResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Fotos'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FotosUpdateComponent,
    resolve: {
      fotos: FotosResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Fotos'
    },
    canActivate: [UserRouteAccessService]
  }
];
