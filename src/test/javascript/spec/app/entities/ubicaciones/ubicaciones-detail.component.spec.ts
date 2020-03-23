import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BeRoutesTestModule } from '../../../test.module';
import { UbicacionesDetailComponent } from 'app/entities/ubicaciones/ubicaciones-detail.component';
import { Ubicaciones } from 'app/shared/model/ubicaciones.model';

describe('Component Tests', () => {
  describe('Ubicaciones Management Detail Component', () => {
    let comp: UbicacionesDetailComponent;
    let fixture: ComponentFixture<UbicacionesDetailComponent>;
    const route = ({ data: of({ ubicaciones: new Ubicaciones(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BeRoutesTestModule],
        declarations: [UbicacionesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(UbicacionesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UbicacionesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ubicaciones on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ubicaciones).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
