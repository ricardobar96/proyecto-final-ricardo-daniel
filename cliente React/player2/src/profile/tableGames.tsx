import "./tableGames.css";
import React, { useState, useEffect, useRef } from 'react'
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';
import { VideogameAsset } from "@material-ui/icons";
import { videojuegos } from "../modelo/videojuegos";

interface IState { videojuegos?: videojuegos[]; }

export default function TableGames() {
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
        <div className="gamesProfile">
            <div className="trendsWrapper">
                <h3 className="title">Títulos jugados:</h3>
                <div className='tendenciasWrapper'>
                    <ul className='gamesProfileList'>
                        {
                            videojuegos?.videojuegos?.map((a: videojuegos) => {
                                return (
                                    <div className="juegosHomeBox">
                                    <Link to={{ pathname: "/api/v1/videojuego/" + a.id }}>
                                        <li className="itemGameProfile">
                                            <span><img src={a.imagen} className='imageGameProfile' /></span>
                                            <h5 className='titleGameProfile'>{a.nombre}</h5>
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