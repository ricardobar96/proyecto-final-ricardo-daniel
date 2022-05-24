import { usuarios } from "./usuarios";

export class mensajesChat {
  constructor(
      public contenido: string,
      public fecha: Date,
      public enviado: boolean,
      public autor: usuarios,
      public room: string
  ) { }
}