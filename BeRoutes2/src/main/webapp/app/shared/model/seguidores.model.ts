export interface ISeguidores {
  id?: number;
}

export class Seguidores implements ISeguidores {
  constructor(public id?: number) {}
}
