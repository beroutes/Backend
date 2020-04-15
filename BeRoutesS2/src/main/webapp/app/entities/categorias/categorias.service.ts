import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICategorias } from 'app/shared/model/categorias.model';

type EntityResponseType = HttpResponse<ICategorias>;
type EntityArrayResponseType = HttpResponse<ICategorias[]>;

@Injectable({ providedIn: 'root' })
export class CategoriasService {
  public resourceUrl = SERVER_API_URL + 'api/categorias';

  constructor(protected http: HttpClient) {}

  create(categorias: ICategorias): Observable<EntityResponseType> {
    return this.http.post<ICategorias>(this.resourceUrl, categorias, { observe: 'response' });
  }

  update(categorias: ICategorias): Observable<EntityResponseType> {
    return this.http.put<ICategorias>(this.resourceUrl, categorias, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICategorias>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICategorias[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
