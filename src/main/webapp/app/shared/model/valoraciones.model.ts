export interface IValoraciones {
  id?: number;
  estrellas?: number;
  comentarios?: string;
}

export class Valoraciones implements IValoraciones {
  constructor(public id?: number, public estrellas?: number, public comentarios?: string) {}
}
