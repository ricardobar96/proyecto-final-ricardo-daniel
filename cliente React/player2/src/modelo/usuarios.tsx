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
        public descripcion: string
    ) { }
}