import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BeRoutesSharedModule } from 'app/shared/shared.module';
import { SeguidoresComponent } from './seguidores.component';
import { SeguidoresDetailComponent } from './seguidores-detail.component';
import { SeguidoresUpdateComponent } from './seguidores-update.component';
import { SeguidoresDeleteDialogComponent } from './seguidores-delete-dialog.component';
import { seguidoresRoute } from './seguidores.route';

@NgModule({
  imports: [BeRoutesSharedModule, RouterModule.forChild(seguidoresRoute)],
  declarations: [SeguidoresComponent, SeguidoresDetailComponent, SeguidoresUpdateComponent, SeguidoresDeleteDialogComponent],
  entryComponents: [SeguidoresDeleteDialogComponent]
})
export class BeRoutesSeguidoresModule {}
