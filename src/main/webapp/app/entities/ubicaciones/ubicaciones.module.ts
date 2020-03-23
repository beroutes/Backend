import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BeRoutesSharedModule } from 'app/shared/shared.module';
import { UbicacionesComponent } from './ubicaciones.component';
import { UbicacionesDetailComponent } from './ubicaciones-detail.component';
import { UbicacionesUpdateComponent } from './ubicaciones-update.component';
import { UbicacionesDeleteDialogComponent } from './ubicaciones-delete-dialog.component';
import { ubicacionesRoute } from './ubicaciones.route';

@NgModule({
  imports: [BeRoutesSharedModule, RouterModule.forChild(ubicacionesRoute)],
  declarations: [UbicacionesComponent, UbicacionesDetailComponent, UbicacionesUpdateComponent, UbicacionesDeleteDialogComponent],
  entryComponents: [UbicacionesDeleteDialogComponent]
})
export class BeRoutesUbicacionesModule {}
