import React, { useState, useEffect } from 'react'
import './juegosHome.css'
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';

interface IProps { }

interface IState { videojuegos?: Array<player2.videojuegos>, usuario?: player2.usuarios, juegosUsuario?: Array<player2.juegosUsuario>; }

declare module player2 {

    export interface videojuegos {
        id: number;
        nombre: string;
        fecha: string;
        descripcion: string;
        imagen: string;
    }

    export interface usuarios {
        id: number;
        nombre: string;
        password: string;
        rol: string;
        avatar: string;
        activo: number;
        color: string;
        banner: string;
        descripcion: string;
    }

    export interface juegosUsuario {
        id: number;
        completado: number;
        horas: number;
        usuario: usuarios;
        videojuego: videojuegos;
        puntuacion: number;
    }

}

export const JuegosHome = () => {
    const [videojuegos, setVideojuego] = useState<IState>();
    const [usuario, setUsuario] = useState<IState>();
    const [juegosUsuario, setJuegosUsuario] = useState<IState>();
    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;
    const { id } = useParams();

    let nameUser = (localStorage.getItem('user') || '{}');
    console.log("NOMBRE DEL USER:" + nameUser);

    useEffect(() => {
        const getVideojuego = async () => {
            const rutajuegosHome: string = rutaBase + "/api/v1/videojuego";
            let ruta = rutajuegosHome;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setVideojuego({ videojuegos: respuesta.data });
        }
        const getUsuario = async () => {
            const rutaUsuario: string = rutaBase + "/api/v1/usuario";
            let ruta = rutaUsuario;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setUsuario({ usuario: respuesta.data });
        }
        const getJuegosUsuario = async () => {
            const rutaJuegosUsuario: string = rutaBase + "/api/v1/juegousuario";
            let ruta = rutaJuegosUsuario;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setJuegosUsuario({ juegosUsuario: respuesta.data });
        }
        getVideojuego();
        getUsuario();
        getJuegosUsuario();
    }, []);

    return (
        <div className="juegosHome">
            <h3 className='title'>Videojuegos en tendencias:</h3>
            <div className='tendenciasWrapper'>
                <ul className='tendenciasList'>
                    {
                        videojuegos?.videojuegos?.slice(0, 5).map((a: player2.videojuegos) => {
                            return (
                                <div className='juegosHomeBox'>
                                    <Link to={{ pathname: "/api/v1/videojuego/" + a.id }}>
                                        <li>
                                            <span><img src={a.imagen} className='imageGameHome' /></span>
                                            <h5 className='titleGameHome'>{a.nombre}</h5>
                                        </li>
                                    </Link>
                                </div>
                            );
                        })
                    }
                </ul>
            </div>

            <h3 className='title'>Nuevos videojuegos:</h3>
            <div className='nuevosWrapper'>
                <ul className='nuevosList'>
                    {
                        videojuegos?.videojuegos?.slice(0, 5).map((a: player2.videojuegos) => {
                            return (
                                <div className='juegosHomeBox'>
                                    <Link to={{ pathname: "/api/v1/videojuego/" + a.id }}>
                                        <li>
                                            <span><img src={a.imagen} className='imageGameHome' /></span>
                                            <h5 className='titleGameHome'>{a.nombre}</h5>
                                        </li>
                                    </Link>
                                </div>
                            );
                        })
                    }
                </ul>
            </div>

            <h3 className='title'>Tus juegos en progreso:</h3>
            <div className='progresoWrapper'>
                <ul className='progresoList'>
                    {
                        juegosUsuario?.juegosUsuario?.slice(0, 5).map((v: player2.juegosUsuario) => {
                            if ((v.usuario.nombre === nameUser) && (v.completado == 0))
                                return (
                                    <div className='juegosHomeBox'>
                                        <Link to={{ pathname: "/api/v1/videojuego/" + v.videojuego.id }}>
                                            <li>
                                                <span><img src={v.videojuego.imagen} className='imageGameHome' /></span>
                                                <h5 className='titleGameHome'>{v.videojuego.nombre}</h5>
                                            </li>
                                        </Link>
                                    </div>
                                );
                        })
                    }
                </ul>
            </div>
        </div>
    )
}
export default JuegosHome;
