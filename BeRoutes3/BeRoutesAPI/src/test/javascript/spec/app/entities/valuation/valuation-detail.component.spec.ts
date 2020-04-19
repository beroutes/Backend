import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BeRoutesApiTestModule } from '../../../test.module';
import { ValuationDetailComponent } from 'app/entities/valuation/valuation-detail.component';
import { Valuation } from 'app/shared/model/valuation.model';

describe('Component Tests', () => {
  describe('Valuation Management Detail Component', () => {
    let comp: ValuationDetailComponent;
    let fixture: ComponentFixture<ValuationDetailComponent>;
    const route = ({ data: of({ valuation: new Valuation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BeRoutesApiTestModule],
        declarations: [ValuationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ValuationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ValuationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load valuation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.valuation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
