import "./trends.css";
import React, { useState, useEffect, useRef } from 'react'
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';
import { VideogameAsset } from "@material-ui/icons";
import { videojuegos } from "../modelo/videojuegos";

interface IState { videojuegos?: videojuegos[]; }

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
                    <ul className='tendenciasTrendsList'>
                        {
                            videojuegos?.videojuegos?.map((a: videojuegos) => {
                                return (
                                    <div className="juegosTrendsBox">
                                        <Link to={{ pathname: "/api/v1/videojuego/" + a.id }}>
                                            <li className="itemTrends">
                                                <span><img src={a.imagen} className='imageGameTrends' /></span>
                                                <div className='titleTrendsBox'>
                                                    <h5 className='titleGameTrends'>{a.nombre}</h5>
                                                </div>
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
                    <ul className='nuevosTrendsList'>
                        {
                            videojuegos?.videojuegos?.map((a: videojuegos) => {
                                return (
                                    <div className="juegosTrendsBox">
                                        <Link to={{ pathname: "/api/v1/videojuego/" + a.id }}>
                                            <li className="itemTrends">
                                                <span><img src={a.imagen} className='imageGameTrends' /></span>
                                                <div className='titleTrendsBox'>
                                                    <h5 className='titleGameTrends'>{a.nombre}</h5>
                                                </div>
                                            </li>
                                        </Link>
                                    </div>
                                );
                            })
                        }
                    </ul>
                </div>
                <h3 className="title">Todos:</h3>
                <div className='todosWrapper'>
                    <ul className='todosTrendsList'>
                        {
                            videojuegos?.videojuegos?.map((a: videojuegos) => {
                                return (
                                    <div className="juegosTrendsBox">
                                        <Link to={{ pathname: "/api/v1/videojuego/" + a.id }}>
                                            <li className="itemTrends">
                                                <span><img src={a.imagen} className='imageGameTrends' /></span>
                                                <div className='titleTrendsBox'>
                                                    <h5 className='titleGameTrends'>{a.nombre}</h5>
                                                </div>
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