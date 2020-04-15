import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BeRoutesTestModule } from '../../../test.module';
import { SeguidoresUpdateComponent } from 'app/entities/seguidores/seguidores-update.component';
import { SeguidoresService } from 'app/entities/seguidores/seguidores.service';
import { Seguidores } from 'app/shared/model/seguidores.model';

describe('Component Tests', () => {
  describe('Seguidores Management Update Component', () => {
    let comp: SeguidoresUpdateComponent;
    let fixture: ComponentFixture<SeguidoresUpdateComponent>;
    let service: SeguidoresService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BeRoutesTestModule],
        declarations: [SeguidoresUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SeguidoresUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SeguidoresUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SeguidoresService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Seguidores(123);
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
        const entity = new Seguidores();
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
