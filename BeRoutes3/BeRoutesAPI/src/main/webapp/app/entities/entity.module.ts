import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'region',
        loadChildren: () => import('./region/region.module').then(m => m.BeRoutesApiRegionModule)
      },
      {
        path: 'city',
        loadChildren: () => import('./city/city.module').then(m => m.BeRoutesApiCityModule)
      },
      {
        path: 'country',
        loadChildren: () => import('./country/country.module').then(m => m.BeRoutesApiCountryModule)
      },
      {
        path: 'location',
        loadChildren: () => import('./location/location.module').then(m => m.BeRoutesApiLocationModule)
      },
      {
        path: 'travel-route',
        loadChildren: () => import('./travel-route/travel-route.module').then(m => m.BeRoutesApiTravelRouteModule)
      },
      {
        path: 'user-profile',
        loadChildren: () => import('./user-profile/user-profile.module').then(m => m.BeRoutesApiUserProfileModule)
      },
      {
        path: 'follower',
        loadChildren: () => import('./follower/follower.module').then(m => m.BeRoutesApiFollowerModule)
      },
      {
        path: 'duration',
        loadChildren: () => import('./duration/duration.module').then(m => m.BeRoutesApiDurationModule)
      },
      {
        path: 'category',
        loadChildren: () => import('./category/category.module').then(m => m.BeRoutesApiCategoryModule)
      },
      {
        path: 'photo',
        loadChildren: () => import('./photo/photo.module').then(m => m.BeRoutesApiPhotoModule)
      },
      {
        path: 'valuation',
        loadChildren: () => import('./valuation/valuation.module').then(m => m.BeRoutesApiValuationModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class BeRoutesApiEntityModule {}
