import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BeRoutesSharedModule } from 'app/shared/shared.module';
import { CiudadesComponent } from './ciudades.component';
import { CiudadesDetailComponent } from './ciudades-detail.component';
import { CiudadesUpdateComponent } from './ciudades-update.component';
import { CiudadesDeleteDialogComponent } from './ciudades-delete-dialog.component';
import { ciudadesRoute } from './ciudades.route';

@NgModule({
  imports: [BeRoutesSharedModule, RouterModule.forChild(ciudadesRoute)],
  declarations: [CiudadesComponent, CiudadesDetailComponent, CiudadesUpdateComponent, CiudadesDeleteDialogComponent],
  entryComponents: [CiudadesDeleteDialogComponent]
})
export class BeRoutesCiudadesModule {}
