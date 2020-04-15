import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BeRoutesTestModule } from '../../../test.module';
import { RutasUpdateComponent } from 'app/entities/rutas/rutas-update.component';
import { RutasService } from 'app/entities/rutas/rutas.service';
import { Rutas } from 'app/shared/model/rutas.model';

describe('Component Tests', () => {
  describe('Rutas Management Update Component', () => {
    let comp: RutasUpdateComponent;
    let fixture: ComponentFixture<RutasUpdateComponent>;
    let service: RutasService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BeRoutesTestModule],
        declarations: [RutasUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(RutasUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RutasUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RutasService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Rutas(123);
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
        const entity = new Rutas();
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
