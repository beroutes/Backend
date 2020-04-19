import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BeRoutesApiTestModule } from '../../../test.module';
import { TravelRouteDetailComponent } from 'app/entities/travel-route/travel-route-detail.component';
import { TravelRoute } from 'app/shared/model/travel-route.model';

describe('Component Tests', () => {
  describe('TravelRoute Management Detail Component', () => {
    let comp: TravelRouteDetailComponent;
    let fixture: ComponentFixture<TravelRouteDetailComponent>;
    const route = ({ data: of({ travelRoute: new TravelRoute(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BeRoutesApiTestModule],
        declarations: [TravelRouteDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TravelRouteDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TravelRouteDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load travelRoute on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.travelRoute).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
