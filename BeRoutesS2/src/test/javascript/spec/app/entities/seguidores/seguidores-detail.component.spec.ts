import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BeRoutesTestModule } from '../../../test.module';
import { SeguidoresDetailComponent } from 'app/entities/seguidores/seguidores-detail.component';
import { Seguidores } from 'app/shared/model/seguidores.model';

describe('Component Tests', () => {
  describe('Seguidores Management Detail Component', () => {
    let comp: SeguidoresDetailComponent;
    let fixture: ComponentFixture<SeguidoresDetailComponent>;
    const route = ({ data: of({ seguidores: new Seguidores(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BeRoutesTestModule],
        declarations: [SeguidoresDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SeguidoresDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SeguidoresDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load seguidores on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.seguidores).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
