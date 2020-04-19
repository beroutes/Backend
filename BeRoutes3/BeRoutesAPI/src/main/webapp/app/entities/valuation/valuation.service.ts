import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, SearchWithPagination } from 'app/shared/util/request-util';
import { IValuation } from 'app/shared/model/valuation.model';

type EntityResponseType = HttpResponse<IValuation>;
type EntityArrayResponseType = HttpResponse<IValuation[]>;

@Injectable({ providedIn: 'root' })
export class ValuationService {
  public resourceUrl = SERVER_API_URL + 'api/valuations';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/valuations';

  constructor(protected http: HttpClient) {}

  create(valuation: IValuation): Observable<EntityResponseType> {
    return this.http.post<IValuation>(this.resourceUrl, valuation, { observe: 'response' });
  }

  update(valuation: IValuation): Observable<EntityResponseType> {
    return this.http.put<IValuation>(this.resourceUrl, valuation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IValuation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IValuation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: SearchWithPagination): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IValuation[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
