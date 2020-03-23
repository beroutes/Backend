export interface ICategorias {
  id?: number;
  nombre?: string;
}

export class Categorias implements ICategorias {
  constructor(public id?: number, public nombre?: string) {}
}
