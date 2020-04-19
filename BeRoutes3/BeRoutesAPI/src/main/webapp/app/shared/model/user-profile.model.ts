import { Moment } from 'moment';
import { ICountry } from 'app/shared/model/country.model';
import { IPhoto } from 'app/shared/model/photo.model';
import { ITravelRoute } from 'app/shared/model/travel-route.model';
import { IValuation } from 'app/shared/model/valuation.model';
import { IFollower } from 'app/shared/model/follower.model';

export interface IUserProfile {
  id?: number;
  firstName?: string;
  lastName?: string;
  email?: string;
  telephone?: number;
  userName?: string;
  password?: string;
  age?: number;
  biography?: string;
  createdAt?: Moment;
  updatedAt?: Moment;
  country?: ICountry;
  photo?: IPhoto;
  travelRoutes?: ITravelRoute[];
  valuations?: IValuation[];
  followers?: IFollower[];
}

export class UserProfile implements IUserProfile {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public telephone?: number,
    public userName?: string,
    public password?: string,
    public age?: number,
    public biography?: string,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public country?: ICountry,
    public photo?: IPhoto,
    public travelRoutes?: ITravelRoute[],
    public valuations?: IValuation[],
    public followers?: IFollower[]
  ) {}
}
