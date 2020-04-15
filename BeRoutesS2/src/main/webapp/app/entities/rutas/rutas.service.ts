import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRutas } from 'app/shared/model/rutas.model';

type EntityResponseType = HttpResponse<IRutas>;
type EntityArrayResponseType = HttpResponse<IRutas[]>;

@Injectable({ providedIn: 'root' })
export class RutasService {
  public resourceUrl = SERVER_API_URL + 'api/rutas';

  constructor(protected http: HttpClient) {}

  create(rutas: IRutas): Observable<EntityResponseType> {
    return this.http.post<IRutas>(this.resourceUrl, rutas, { observe: 'response' });
  }

  update(rutas: IRutas): Observable<EntityResponseType> {
    return this.http.put<IRutas>(this.resourceUrl, rutas, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRutas>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRutas[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
