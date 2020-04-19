import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BeRoutesApiTestModule } from '../../../test.module';
import { ValuationUpdateComponent } from 'app/entities/valuation/valuation-update.component';
import { ValuationService } from 'app/entities/valuation/valuation.service';
import { Valuation } from 'app/shared/model/valuation.model';

describe('Component Tests', () => {
  describe('Valuation Management Update Component', () => {
    let comp: ValuationUpdateComponent;
    let fixture: ComponentFixture<ValuationUpdateComponent>;
    let service: ValuationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BeRoutesApiTestModule],
        declarations: [ValuationUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ValuationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ValuationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ValuationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Valuation(123);
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
        const entity = new Valuation();
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
