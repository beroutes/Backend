import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BeRoutesSharedModule } from 'app/shared/shared.module';
import { FotosComponent } from './fotos.component';
import { FotosDetailComponent } from './fotos-detail.component';
import { FotosUpdateComponent } from './fotos-update.component';
import { FotosDeleteDialogComponent } from './fotos-delete-dialog.component';
import { fotosRoute } from './fotos.route';

@NgModule({
  imports: [BeRoutesSharedModule, RouterModule.forChild(fotosRoute)],
  declarations: [FotosComponent, FotosDetailComponent, FotosUpdateComponent, FotosDeleteDialogComponent],
  entryComponents: [FotosDeleteDialogComponent]
})
export class BeRoutesFotosModule {}
