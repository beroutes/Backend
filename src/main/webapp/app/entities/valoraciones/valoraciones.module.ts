import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BeRoutesSharedModule } from 'app/shared/shared.module';
import { ValoracionesComponent } from './valoraciones.component';
import { ValoracionesDetailComponent } from './valoraciones-detail.component';
import { ValoracionesUpdateComponent } from './valoraciones-update.component';
import { ValoracionesDeleteDialogComponent } from './valoraciones-delete-dialog.component';
import { valoracionesRoute } from './valoraciones.route';

@NgModule({
  imports: [BeRoutesSharedModule, RouterModule.forChild(valoracionesRoute)],
  declarations: [ValoracionesComponent, ValoracionesDetailComponent, ValoracionesUpdateComponent, ValoracionesDeleteDialogComponent],
  entryComponents: [ValoracionesDeleteDialogComponent]
})
export class BeRoutesValoracionesModule {}
