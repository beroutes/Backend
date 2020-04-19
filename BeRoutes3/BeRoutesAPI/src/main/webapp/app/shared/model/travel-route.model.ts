import { Moment } from 'moment';
import { IDuration } from 'app/shared/model/duration.model';
import { ICategory } from 'app/shared/model/category.model';
import { ILocation } from 'app/shared/model/location.model';
import { IPhoto } from 'app/shared/model/photo.model';
import { IValuation } from 'app/shared/model/valuation.model';
import { IUserProfile } from 'app/shared/model/user-profile.model';
import { Season } from 'app/shared/model/enumerations/season.model';

export interface ITravelRoute {
  id?: number;
  titleRoute?: string;
  destination?: string;
  season?: Season;
  budget?: number;
  descriptionRoute?: string;
  createdAt?: Moment;
  updatedAt?: Moment;
  duration?: IDuration;
  category?: ICategory;
  locations?: ILocation[];
  photos?: IPhoto[];
  valuations?: IValuation[];
  userProfile?: IUserProfile;
}

export class TravelRoute implements ITravelRoute {
  constructor(
    public id?: number,
    public titleRoute?: string,
    public destination?: string,
    public season?: Season,
    public budget?: number,
    public descriptionRoute?: string,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public duration?: IDuration,
    public category?: ICategory,
    public locations?: ILocation[],
    public photos?: IPhoto[],
    public valuations?: IValuation[],
    public userProfile?: IUserProfile
  ) {}
}
