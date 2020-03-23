import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISeguidores } from 'app/shared/model/seguidores.model';

type EntityResponseType = HttpResponse<ISeguidores>;
type EntityArrayResponseType = HttpResponse<ISeguidores[]>;

@Injectable({ providedIn: 'root' })
export class SeguidoresService {
  public resourceUrl = SERVER_API_URL + 'api/seguidores';

  constructor(protected http: HttpClient) {}

  create(seguidores: ISeguidores): Observable<EntityResponseType> {
    return this.http.post<ISeguidores>(this.resourceUrl, seguidores, { observe: 'response' });
  }

  update(seguidores: ISeguidores): Observable<EntityResponseType> {
    return this.http.put<ISeguidores>(this.resourceUrl, seguidores, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISeguidores>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISeguidores[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
