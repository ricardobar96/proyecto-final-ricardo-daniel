import { usuarios } from "./usuarios";

export class mensajes {
  constructor(
      public id: number,
      public contenido: string,
      public fecha: Date,
      public autor: usuarios,
      public destinatario: usuarios
  ) { }
}