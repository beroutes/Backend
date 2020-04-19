import { ITravelRoute } from 'app/shared/model/travel-route.model';

export interface ICategory {
  id?: number;
  nameCategory?: string;
  cheap?: boolean;
  luxury?: boolean;
  lonely?: boolean;
  friends?: boolean;
  romantic?: boolean;
  kids?: boolean;
  sport?: boolean;
  relaxation?: boolean;
  art?: boolean;
  food?: boolean;
  nature?: boolean;
  city?: boolean;
  travelRoute?: ITravelRoute;
}

export class Category implements ICategory {
  constructor(
    public id?: number,
    public nameCategory?: string,
    public cheap?: boolean,
    public luxury?: boolean,
    public lonely?: boolean,
    public friends?: boolean,
    public romantic?: boolean,
    public kids?: boolean,
    public sport?: boolean,
    public relaxation?: boolean,
    public art?: boolean,
    public food?: boolean,
    public nature?: boolean,
    public city?: boolean,
    public travelRoute?: ITravelRoute
  ) {
    this.cheap = this.cheap || false;
    this.luxury = this.luxury || false;
    this.lonely = this.lonely || false;
    this.friends = this.friends || false;
    this.romantic = this.romantic || false;
    this.kids = this.kids || false;
    this.sport = this.sport || false;
    this.relaxation = this.relaxation || false;
    this.art = this.art || false;
    this.food = this.food || false;
    this.nature = this.nature || false;
    this.city = this.city || false;
  }
}
