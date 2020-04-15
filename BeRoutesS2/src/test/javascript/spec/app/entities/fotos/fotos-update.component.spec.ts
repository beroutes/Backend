import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BeRoutesTestModule } from '../../../test.module';
import { FotosUpdateComponent } from 'app/entities/fotos/fotos-update.component';
import { FotosService } from 'app/entities/fotos/fotos.service';
import { Fotos } from 'app/shared/model/fotos.model';

describe('Component Tests', () => {
  describe('Fotos Management Update Component', () => {
    let comp: FotosUpdateComponent;
    let fixture: ComponentFixture<FotosUpdateComponent>;
    let service: FotosService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BeRoutesTestModule],
        declarations: [FotosUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(FotosUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FotosUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FotosService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Fotos(123);
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
        const entity = new Fotos();
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
