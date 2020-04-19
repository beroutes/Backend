import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BeRoutesApiTestModule } from '../../../test.module';
import { TravelRouteUpdateComponent } from 'app/entities/travel-route/travel-route-update.component';
import { TravelRouteService } from 'app/entities/travel-route/travel-route.service';
import { TravelRoute } from 'app/shared/model/travel-route.model';

describe('Component Tests', () => {
  describe('TravelRoute Management Update Component', () => {
    let comp: TravelRouteUpdateComponent;
    let fixture: ComponentFixture<TravelRouteUpdateComponent>;
    let service: TravelRouteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BeRoutesApiTestModule],
        declarations: [TravelRouteUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TravelRouteUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TravelRouteUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TravelRouteService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TravelRoute(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new TravelRoute();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
