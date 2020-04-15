import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BeRoutesSharedModule } from 'app/shared/shared.module';
import { RutasComponent } from './rutas.component';
import { RutasDetailComponent } from './rutas-detail.component';
import { RutasUpdateComponent } from './rutas-update.component';
import { RutasDeleteDialogComponent } from './rutas-delete-dialog.component';
import { rutasRoute } from './rutas.route';

@NgModule({
  imports: [BeRoutesSharedModule, RouterModule.forChild(rutasRoute)],
  declarations: [RutasComponent, RutasDetailComponent, RutasUpdateComponent, RutasDeleteDialogComponent],
  entryComponents: [RutasDeleteDialogComponent]
})
export class BeRoutesRutasModule {}
