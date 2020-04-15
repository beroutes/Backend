export interface IFotos {
  id?: number;
  titulo?: string;
  url?: string;
}

export class Fotos implements IFotos {
  constructor(public id?: number, public titulo?: string, public url?: string) {}
}
