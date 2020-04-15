export interface IUbicaciones {
  id?: number;
  titulo?: string;
  descripcion?: string;
  coordenadaX?: number;
  coordenadaY?: number;
  duracion?: number;
  qr?: string;
}

export class Ubicaciones implements IUbicaciones {
  constructor(
    public id?: number,
    public titulo?: string,
    public descripcion?: string,
    public coordenadaX?: number,
    public coordenadaY?: number,
    public duracion?: number,
    public qr?: string
  ) {}
}
