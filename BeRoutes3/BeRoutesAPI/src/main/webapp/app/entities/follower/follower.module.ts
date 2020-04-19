import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BeRoutesApiSharedModule } from 'app/shared/shared.module';
import { FollowerComponent } from './follower.component';
import { FollowerDetailComponent } from './follower-detail.component';
import { FollowerUpdateComponent } from './follower-update.component';
import { FollowerDeleteDialogComponent } from './follower-delete-dialog.component';
import { followerRoute } from './follower.route';

@NgModule({
  imports: [BeRoutesApiSharedModule, RouterModule.forChild(followerRoute)],
  declarations: [FollowerComponent, FollowerDetailComponent, FollowerUpdateComponent, FollowerDeleteDialogComponent],
  entryComponents: [FollowerDeleteDialogComponent]
})
export class BeRoutesApiFollowerModule {}
