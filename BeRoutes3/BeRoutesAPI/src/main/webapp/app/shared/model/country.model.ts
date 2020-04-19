import { IRegion } from 'app/shared/model/region.model';
import { ICity } from 'app/shared/model/city.model';

export interface ICountry {
  id?: number;
  countryName?: string;
  region?: IRegion;
  city?: ICity;
}

export class Country implements ICountry {
  constructor(public id?: number, public countryName?: string, public region?: IRegion, public city?: ICity) {}
}
