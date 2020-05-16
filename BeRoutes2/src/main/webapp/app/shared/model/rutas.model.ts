export interface IRutas {
  id?: number;
  titulo?: string;
  duracion?: number;
  temporada?: string;
  presupuesto?: number;
  descripcion?: string;
}

export class Rutas implements IRutas {
  constructor(
    public id?: number,
    public titulo?: string,
    public duracion?: number,
    public temporada?: string,
    public presupuesto?: number,
    public descripcion?: string
  ) {}
}
