import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BeRoutesTestModule } from '../../../test.module';
import { UbicacionesUpdateComponent } from 'app/entities/ubicaciones/ubicaciones-update.component';
import { UbicacionesService } from 'app/entities/ubicaciones/ubicaciones.service';
import { Ubicaciones } from 'app/shared/model/ubicaciones.model';

describe('Component Tests', () => {
  describe('Ubicaciones Management Update Component', () => {
    let comp: UbicacionesUpdateComponent;
    let fixture: ComponentFixture<UbicacionesUpdateComponent>;
    let service: UbicacionesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BeRoutesTestModule],
        declarations: [UbicacionesUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(UbicacionesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UbicacionesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UbicacionesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Ubicaciones(123);
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
        const entity = new Ubicaciones();
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
