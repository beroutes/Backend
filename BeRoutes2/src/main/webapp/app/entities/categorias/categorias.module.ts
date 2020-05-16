import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BeRoutesSharedModule } from 'app/shared/shared.module';
import { CategoriasComponent } from './categorias.component';
import { CategoriasDetailComponent } from './categorias-detail.component';
import { CategoriasUpdateComponent } from './categorias-update.component';
import { CategoriasDeleteDialogComponent } from './categorias-delete-dialog.component';
import { categoriasRoute } from './categorias.route';

@NgModule({
  imports: [BeRoutesSharedModule, RouterModule.forChild(categoriasRoute)],
  declarations: [CategoriasComponent, CategoriasDetailComponent, CategoriasUpdateComponent, CategoriasDeleteDialogComponent],
  entryComponents: [CategoriasDeleteDialogComponent]
})
export class BeRoutesCategoriasModule {}
