import React, { useState, useEffect } from 'react'
import './juegosHome.css'
import { VideogameAsset } from "@material-ui/icons";
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';

interface IProps { }

interface IState { videojuegos?: Array<player2.videojuegos>; }

declare module player2 {

    export interface videojuegos {
        id: number;
        nombre: string;
        fecha: string;
        puntuacion: number;
        descripcion: string;
        imagen: string;
    }

    export interface usuarios {
        id: number;
        nombre: string;
        password: string;
        rol: string;
        avatar: string;
        color_perfil: string;
        banner_perfil: string;
        sobre_mi: string;
    }

}

export const JuegosHome = () => {
    const [videojuegos, setVideojuego] = useState<IState>();
    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;
    const rutajuegosHome: string = rutaBase + "/api/v1/videojuego";

    useEffect(() => {
        const getVideojuego = async () => {
            let ruta = rutajuegosHome;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setVideojuego({ videojuegos: respuesta.data });
        }
        getVideojuego();
    }, []);

    return (
        <div className="juegosHome">
            <h3 className='title'>Tus juegos en progreso:</h3>
            <div className='progresoWrapper'>
                <ul className='progresoList'>
                    {
                        videojuegos?.videojuegos?.map((a: player2.videojuegos) => {
                            return (
                                <Link to={{ pathname: "/api/v1/videojuego/" + a.id }}>
                                    <li><h3 className='titleGameHome'>{a.nombre}</h3>
                                        <span><img src={a.imagen} className='imageGameHome' /></span>
                                    </li>
                                </Link>
                            );
                        })
                    }
                </ul>
            </div>

            <h3 className='title'>Videojuegos en tendencias:</h3>
            <div className='tendenciasWrapper'>
                <ul className='tendenciasList'>
                    {
                        videojuegos?.videojuegos?.map((a: player2.videojuegos) => {
                            return (
                                <Link to={{ pathname: "/api/v1/videojuego/" + a.id }}>
                                    <li><h3 className='titleGameHome'>{a.nombre}</h3>
                                        <span><img src={a.imagen} className='imageGameHome' /></span>
                                    </li>
                                </Link>
                            );
                        })
                    }
                </ul>
            </div>

            <h3 className='title'>Nuevos videojuegos:</h3>
            <div className='nuevosWrapper'>
                <ul className='nuevosList'>
                    {
                        videojuegos?.videojuegos?.map((a: player2.videojuegos) => {
                            return (
                                <Link to={{ pathname: "/api/v1/videojuego/" + a.id }}>
                                    <li><h3 className='titleGameHome'>{a.nombre}</h3>
                                        <span><img src={a.imagen} className='imageGameHome' /></span>
                                    </li>
                                </Link>
                            );
                        })
                    }
                </ul>
            </div>
        </div>
    )
}
export default JuegosHome;
