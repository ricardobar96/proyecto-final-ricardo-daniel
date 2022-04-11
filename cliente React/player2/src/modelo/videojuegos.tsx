import { generos } from "./generos";
import { juegosUsuario } from './juegosUsuario';

export class videojuegos {
    constructor(
        public id: number,
        public nombre: string,
        public fecha: any,
        public puntuacion: number,
        public descripcion: string,
        public imagen: string,
        public generos: generos[]
        //public juegoUsuarios: juegosUsuario[]
    ) { }
}