import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BeRoutesTestModule } from '../../../test.module';
import { FotosDetailComponent } from 'app/entities/fotos/fotos-detail.component';
import { Fotos } from 'app/shared/model/fotos.model';

describe('Component Tests', () => {
  describe('Fotos Management Detail Component', () => {
    let comp: FotosDetailComponent;
    let fixture: ComponentFixture<FotosDetailComponent>;
    const route = ({ data: of({ fotos: new Fotos(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BeRoutesTestModule],
        declarations: [FotosDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(FotosDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FotosDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load fotos on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.fotos).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
