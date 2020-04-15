import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BeRoutesTestModule } from '../../../test.module';
import { RutasDetailComponent } from 'app/entities/rutas/rutas-detail.component';
import { Rutas } from 'app/shared/model/rutas.model';

describe('Component Tests', () => {
  describe('Rutas Management Detail Component', () => {
    let comp: RutasDetailComponent;
    let fixture: ComponentFixture<RutasDetailComponent>;
    const route = ({ data: of({ rutas: new Rutas(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BeRoutesTestModule],
        declarations: [RutasDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(RutasDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RutasDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load rutas on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.rutas).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
