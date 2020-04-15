export interface ICiudades {
  id?: number;
  nombre?: string;
}

export class Ciudades implements ICiudades {
  constructor(public id?: number, public nombre?: string) {}
}
