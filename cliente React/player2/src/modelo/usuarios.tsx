import { juegosUsuario } from './juegosUsuario';
import { reviews } from './reviews';
import { pistas } from './pistas';
import { mensajes } from './mensajes';
export class usuarios {
    constructor(
        public id: number,
        public nombre: string,
        public password: string,
        public rol: string,
        public avatar: string,
        public activo: number,
        public color: string,
        public banner: string,
        public descripcion: string,
        public followeds: usuarios[],
        public followers: usuarios[],
        public juegoUsuarios: juegosUsuario[],
        public reviews: reviews[],
        public pistas: pistas[],
        public mensajesEnviados: mensajes[],
        public mensajesRecibidos: mensajes[]
    ) { }
}