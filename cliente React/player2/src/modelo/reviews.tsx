import { usuarios } from "./usuarios";
import { videojuegos } from "./videojuegos";

export class reviews {
    constructor(
        public id: number,
        public titulo: string,
        public contenido: string,
        public fecha: Date,
        public videojuego: videojuegos,
        public usuario: usuarios
    ) { }
}