import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUbicaciones } from 'app/shared/model/ubicaciones.model';

type EntityResponseType = HttpResponse<IUbicaciones>;
type EntityArrayResponseType = HttpResponse<IUbicaciones[]>;

@Injectable({ providedIn: 'root' })
export class UbicacionesService {
  public resourceUrl = SERVER_API_URL + 'api/ubicaciones';

  constructor(protected http: HttpClient) {}

  create(ubicaciones: IUbicaciones): Observable<EntityResponseType> {
    return this.http.post<IUbicaciones>(this.resourceUrl, ubicaciones, { observe: 'response' });
  }

  update(ubicaciones: IUbicaciones): Observable<EntityResponseType> {
    return this.http.put<IUbicaciones>(this.resourceUrl, ubicaciones, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IUbicaciones>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUbicaciones[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
