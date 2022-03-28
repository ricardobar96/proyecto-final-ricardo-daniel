import { usuarios } from "./usuarios";
import { videojuegos } from "./videojuegos";

export class juegosUsuario {
    constructor(
        public id: number,
        public completado: number,
        public horas: number,
        public usuario: usuarios,
        public videojuego: videojuegos,
        public puntuacion: number
    ) { }
}