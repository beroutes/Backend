import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UbicacionesService } from 'app/entities/ubicaciones/ubicaciones.service';
import { IUbicaciones, Ubicaciones } from 'app/shared/model/ubicaciones.model';

describe('Service Tests', () => {
  describe('Ubicaciones Service', () => {
    let injector: TestBed;
    let service: UbicacionesService;
    let httpMock: HttpTestingController;
    let elemDefault: IUbicaciones;
    let expectedResult: IUbicaciones | IUbicaciones[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(UbicacionesService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Ubicaciones(0, 'AAAAAAA', 'AAAAAAA', 0, 0, 0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Ubicaciones', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Ubicaciones()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Ubicaciones', () => {
        const returnedFromService = Object.assign(
          {
            titulo: 'BBBBBB',
            descripcion: 'BBBBBB',
            coordenadaX: 1,
            coordenadaY: 1,
            duracion: 1,
            qr: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Ubicaciones', () => {
        const returnedFromService = Object.assign(
          {
            titulo: 'BBBBBB',
            descripcion: 'BBBBBB',
            coordenadaX: 1,
            coordenadaY: 1,
            duracion: 1,
            qr: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Ubicaciones', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
