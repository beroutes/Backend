import { Moment } from 'moment';
import { IDuration } from 'app/shared/model/duration.model';
import { IPhoto } from 'app/shared/model/photo.model';
import { ICountry } from 'app/shared/model/country.model';
import { ITravelRoute } from 'app/shared/model/travel-route.model';

export interface ILocation {
  id?: number;
  stepPosition?: number;
  titleLocation?: string;
  descriptionLocation?: string;
  xCoordinate?: number;
  yCoordinate?: number;
  stepDuration?: string;
  qrCode?: number;
  qrDescription?: string;
  createdAt?: Moment;
  updatedAt?: Moment;
  duration?: IDuration;
  photo?: IPhoto;
  country?: ICountry;
  travelRoute?: ITravelRoute;
}

export class Location implements ILocation {
  constructor(
    public id?: number,
    public stepPosition?: number,
    public titleLocation?: string,
    public descriptionLocation?: string,
    public xCoordinate?: number,
    public yCoordinate?: number,
    public stepDuration?: string,
    public qrCode?: number,
    public qrDescription?: string,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public duration?: IDuration,
    public photo?: IPhoto,
    public country?: ICountry,
    public travelRoute?: ITravelRoute
  ) {}
}
