import { Moment } from 'moment';

export interface IUsuarios {
  id?: number;
  nombre?: string;
  apellidos?: string;
  email?: string;
  usuario?: string;
  password?: string;
  fechaRegistro?: Moment;
  ciudad?: string;
  urlFotoPerfil?: string;
}

export class Usuarios implements IUsuarios {
  constructor(
    public id?: number,
    public nombre?: string,
    public apellidos?: string,
    public email?: string,
    public usuario?: string,
    public password?: string,
    public fechaRegistro?: Moment,
    public ciudad?: string,
    public urlFotoPerfil?: string
  ) {}
}
