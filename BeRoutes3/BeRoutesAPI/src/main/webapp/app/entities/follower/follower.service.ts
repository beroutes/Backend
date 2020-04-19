import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, SearchWithPagination } from 'app/shared/util/request-util';
import { IFollower } from 'app/shared/model/follower.model';

type EntityResponseType = HttpResponse<IFollower>;
type EntityArrayResponseType = HttpResponse<IFollower[]>;

@Injectable({ providedIn: 'root' })
export class FollowerService {
  public resourceUrl = SERVER_API_URL + 'api/followers';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/followers';

  constructor(protected http: HttpClient) {}

  create(follower: IFollower): Observable<EntityResponseType> {
    return this.http.post<IFollower>(this.resourceUrl, follower, { observe: 'response' });
  }

  update(follower: IFollower): Observable<EntityResponseType> {
    return this.http.put<IFollower>(this.resourceUrl, follower, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFollower>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFollower[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: SearchWithPagination): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFollower[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
