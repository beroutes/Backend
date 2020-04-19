import { ITravelRoute } from 'app/shared/model/travel-route.model';
import { IUserProfile } from 'app/shared/model/user-profile.model';

export interface IValuation {
  id?: number;
  comment?: string;
  rating?: number;
  travelRoute?: ITravelRoute;
  userProfile?: IUserProfile;
}

export class Valuation implements IValuation {
  constructor(
    public id?: number,
    public comment?: string,
    public rating?: number,
    public travelRoute?: ITravelRoute,
    public userProfile?: IUserProfile
  ) {}
}
