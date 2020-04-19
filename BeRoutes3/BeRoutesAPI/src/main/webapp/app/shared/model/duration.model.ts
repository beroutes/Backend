import { ITravelRoute } from 'app/shared/model/travel-route.model';

export interface IDuration {
  id?: number;
  descriptionDuration?: string;
  minutes?: number;
  hours?: number;
  days?: number;
  weeks?: number;
  years?: number;
  travelRoute?: ITravelRoute;
}

export class Duration implements IDuration {
  constructor(
    public id?: number,
    public descriptionDuration?: string,
    public minutes?: number,
    public hours?: number,
    public days?: number,
    public weeks?: number,
    public years?: number,
    public travelRoute?: ITravelRoute
  ) {}
}
