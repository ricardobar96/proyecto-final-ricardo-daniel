import "./trends.css";
import React, { useState, useEffect, useRef } from 'react'
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';
import { VideogameAsset } from "@material-ui/icons";

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

export default function Trends() {
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
        <div className="trends">
            <div className="trendsWrapper">
                <h3 className="title">Tendencias:</h3>
                <div className='tendenciasWrapper'>
                    <ul className='tendenciasList'>
                        {
                            videojuegos?.videojuegos?.slice(0,5).map((a: player2.videojuegos) => {
                                return (
                                    <div className="juegosHomeBox">
                                    <Link to={{ pathname: "/api/v1/videojuego/" + a.id }}>
                                        <li>
                                            <span><img src={a.imagen} className='imageGameTrends' /></span>
                                            <h5 className='titleGameHome'>{a.nombre}</h5>
                                        </li>
                                    </Link>
                                    </div>
                                );
                            })
                        }
                    </ul>
                </div>
                <h3 className="title">Nuevos:</h3>
                <div className='nuevosWrapper'>
                    <ul className='nuevosList'>
                        {
                            videojuegos?.videojuegos?.slice(0,5).map((a: player2.videojuegos) => {
                                return (
                                    <div className="juegosTrendsBox">
                                    <Link to={{ pathname: "/api/v1/videojuego/" + a.id }}>
                                        <li>
                                            <span><img src={a.imagen} className='imageGameTrends' /></span>
                                            <h5 className='titleGameHome'>{a.nombre}</h5>
                                        </li>
                                    </Link>
                                    </div>
                                );
                            })
                        }
                    </ul>
                </div>
            </div>
        </div>
    )
}