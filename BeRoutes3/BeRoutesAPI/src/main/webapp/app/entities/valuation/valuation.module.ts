import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BeRoutesApiSharedModule } from 'app/shared/shared.module';
import { ValuationComponent } from './valuation.component';
import { ValuationDetailComponent } from './valuation-detail.component';
import { ValuationUpdateComponent } from './valuation-update.component';
import { ValuationDeleteDialogComponent } from './valuation-delete-dialog.component';
import { valuationRoute } from './valuation.route';

@NgModule({
  imports: [BeRoutesApiSharedModule, RouterModule.forChild(valuationRoute)],
  declarations: [ValuationComponent, ValuationDetailComponent, ValuationUpdateComponent, ValuationDeleteDialogComponent],
  entryComponents: [ValuationDeleteDialogComponent]
})
export class BeRoutesApiValuationModule {}
