import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFotos } from 'app/shared/model/fotos.model';

type EntityResponseType = HttpResponse<IFotos>;
type EntityArrayResponseType = HttpResponse<IFotos[]>;

@Injectable({ providedIn: 'root' })
export class FotosService {
  public resourceUrl = SERVER_API_URL + 'api/fotos';

  constructor(protected http: HttpClient) {}

  create(fotos: IFotos): Observable<EntityResponseType> {
    return this.http.post<IFotos>(this.resourceUrl, fotos, { observe: 'response' });
  }

  update(fotos: IFotos): Observable<EntityResponseType> {
    return this.http.put<IFotos>(this.resourceUrl, fotos, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFotos>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFotos[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
