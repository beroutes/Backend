import { IUserProfile } from 'app/shared/model/user-profile.model';

export interface IFollower {
  id?: number;
  accepted?: boolean;
  userProfiles?: IUserProfile[];
}

export class Follower implements IFollower {
  constructor(public id?: number, public accepted?: boolean, public userProfiles?: IUserProfile[]) {
    this.accepted = this.accepted || false;
  }
}
