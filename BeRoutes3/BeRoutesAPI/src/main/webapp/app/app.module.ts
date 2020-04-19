import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { BeRoutesApiSharedModule } from 'app/shared/shared.module';
import { BeRoutesApiCoreModule } from 'app/core/core.module';
import { BeRoutesApiAppRoutingModule } from './app-routing.module';
import { BeRoutesApiHomeModule } from './home/home.module';
import { BeRoutesApiEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    BeRoutesApiSharedModule,
    BeRoutesApiCoreModule,
    BeRoutesApiHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    BeRoutesApiEntityModule,
    BeRoutesApiAppRoutingModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent]
})
export class BeRoutesApiAppModule {}
