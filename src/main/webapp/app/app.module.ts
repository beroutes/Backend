import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { BeRoutesSharedModule } from 'app/shared/shared.module';
import { BeRoutesCoreModule } from 'app/core/core.module';
import { BeRoutesAppRoutingModule } from './app-routing.module';
import { BeRoutesHomeModule } from './home/home.module';
import { BeRoutesEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    BeRoutesSharedModule,
    BeRoutesCoreModule,
    BeRoutesHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    BeRoutesEntityModule,
    BeRoutesAppRoutingModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent]
})
export class BeRoutesAppModule {}
