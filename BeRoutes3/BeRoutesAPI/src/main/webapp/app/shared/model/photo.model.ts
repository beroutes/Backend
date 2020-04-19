import { ILocation } from 'app/shared/model/location.model';
import { IUserProfile } from 'app/shared/model/user-profile.model';
import { ITravelRoute } from 'app/shared/model/travel-route.model';

export interface IPhoto {
  id?: number;
  titlePhoto?: string;
  descriptionPhoto?: string;
  urlPhoto?: string;
  location?: ILocation;
  userProfile?: IUserProfile;
  travelRoute?: ITravelRoute;
}

export class Photo implements IPhoto {
  constructor(
    public id?: number,
    public titlePhoto?: string,
    public descriptionPhoto?: string,
    public urlPhoto?: string,
    public location?: ILocation,
    public userProfile?: IUserProfile,
    public travelRoute?: ITravelRoute
  ) {}
}
