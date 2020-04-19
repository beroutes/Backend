import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BeRoutesApiSharedModule } from 'app/shared/shared.module';
import { DurationComponent } from './duration.component';
import { DurationDetailComponent } from './duration-detail.component';
import { DurationUpdateComponent } from './duration-update.component';
import { DurationDeleteDialogComponent } from './duration-delete-dialog.component';
import { durationRoute } from './duration.route';

@NgModule({
  imports: [BeRoutesApiSharedModule, RouterModule.forChild(durationRoute)],
  declarations: [DurationComponent, DurationDetailComponent, DurationUpdateComponent, DurationDeleteDialogComponent],
  entryComponents: [DurationDeleteDialogComponent]
})
export class BeRoutesApiDurationModule {}
