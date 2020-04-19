import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, SearchWithPagination } from 'app/shared/util/request-util';
import { ITravelRoute } from 'app/shared/model/travel-route.model';

type EntityResponseType = HttpResponse<ITravelRoute>;
type EntityArrayResponseType = HttpResponse<ITravelRoute[]>;

@Injectable({ providedIn: 'root' })
export class TravelRouteService {
  public resourceUrl = SERVER_API_URL + 'api/travel-routes';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/travel-routes';

  constructor(protected http: HttpClient) {}

  create(travelRoute: ITravelRoute): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(travelRoute);
    return this.http
      .post<ITravelRoute>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(travelRoute: ITravelRoute): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(travelRoute);
    return this.http
      .put<ITravelRoute>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITravelRoute>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITravelRoute[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: SearchWithPagination): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITravelRoute[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(travelRoute: ITravelRoute): ITravelRoute {
    const copy: ITravelRoute = Object.assign({}, travelRoute, {
      createdAt: travelRoute.createdAt && travelRoute.createdAt.isValid() ? travelRoute.createdAt.toJSON() : undefined,
      updatedAt: travelRoute.updatedAt && travelRoute.updatedAt.isValid() ? travelRoute.updatedAt.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdAt = res.body.createdAt ? moment(res.body.createdAt) : undefined;
      res.body.updatedAt = res.body.updatedAt ? moment(res.body.updatedAt) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((travelRoute: ITravelRoute) => {
        travelRoute.createdAt = travelRoute.createdAt ? moment(travelRoute.createdAt) : undefined;
        travelRoute.updatedAt = travelRoute.updatedAt ? moment(travelRoute.updatedAt) : undefined;
      });
    }
    return res;
  }
}
