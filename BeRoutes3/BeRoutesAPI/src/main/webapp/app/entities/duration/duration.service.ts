import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, SearchWithPagination } from 'app/shared/util/request-util';
import { IDuration } from 'app/shared/model/duration.model';

type EntityResponseType = HttpResponse<IDuration>;
type EntityArrayResponseType = HttpResponse<IDuration[]>;

@Injectable({ providedIn: 'root' })
export class DurationService {
  public resourceUrl = SERVER_API_URL + 'api/durations';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/durations';

  constructor(protected http: HttpClient) {}

  create(duration: IDuration): Observable<EntityResponseType> {
    return this.http.post<IDuration>(this.resourceUrl, duration, { observe: 'response' });
  }

  update(duration: IDuration): Observable<EntityResponseType> {
    return this.http.put<IDuration>(this.resourceUrl, duration, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDuration>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDuration[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: SearchWithPagination): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDuration[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
