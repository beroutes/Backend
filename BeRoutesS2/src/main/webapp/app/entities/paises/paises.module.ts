import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BeRoutesSharedModule } from 'app/shared/shared.module';
import { PaisesComponent } from './paises.component';
import { PaisesDetailComponent } from './paises-detail.component';
import { PaisesUpdateComponent } from './paises-update.component';
import { PaisesDeleteDialogComponent } from './paises-delete-dialog.component';
import { paisesRoute } from './paises.route';

@NgModule({
  imports: [BeRoutesSharedModule, RouterModule.forChild(paisesRoute)],
  declarations: [PaisesComponent, PaisesDetailComponent, PaisesUpdateComponent, PaisesDeleteDialogComponent],
  entryComponents: [PaisesDeleteDialogComponent]
})
export class BeRoutesPaisesModule {}
