import { juegosUsuario } from './juegosUsuario';
import { reviews } from './reviews';
import { pistas } from './pistas';
import { usuarios } from './usuarios';
import { videojuegos } from './videojuegos';
export class actividad {
    constructor( 
        public tipo: string,
        public fecha: Date,
        public usuario: usuarios,
        public videojuego: videojuegos
    ) { }
}