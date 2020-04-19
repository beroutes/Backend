import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BeRoutesApiSharedModule } from 'app/shared/shared.module';
import { TravelRouteComponent } from './travel-route.component';
import { TravelRouteDetailComponent } from './travel-route-detail.component';
import { TravelRouteUpdateComponent } from './travel-route-update.component';
import { TravelRouteDeleteDialogComponent } from './travel-route-delete-dialog.component';
import { travelRouteRoute } from './travel-route.route';

@NgModule({
  imports: [BeRoutesApiSharedModule, RouterModule.forChild(travelRouteRoute)],
  declarations: [TravelRouteComponent, TravelRouteDetailComponent, TravelRouteUpdateComponent, TravelRouteDeleteDialogComponent],
  entryComponents: [TravelRouteDeleteDialogComponent]
})
export class BeRoutesApiTravelRouteModule {}
