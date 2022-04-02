import "./tableGames.css";
import React, { useState, useEffect, useRef } from 'react'
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';
import { VideogameAsset } from "@material-ui/icons";
import { videojuegos } from "../modelo/videojuegos";
import { usuarios } from "../modelo/usuarios";
import { juegosUsuario } from "../modelo/juegosUsuario";

interface IState { videojuegos?: videojuegos[]; usuario?: usuarios, juegosUsuario?: juegosUsuario[]; }

export default function TableGames() {
    let nameUser = (localStorage.getItem('user') || '{}');

    const [videojuegos, setVideojuego] = useState<IState>();
    const [juegosUsuario, setJuegosUsuario] = useState<IState>();
    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;
    const rutajuegosHome: string = rutaBase + "/api/v1/videojuego";
    const rutaJuegosUsuario: string = rutaBase + "/api/v1/juegousuario";

    useEffect(() => {
        const getVideojuego = async () => {
            let ruta = rutajuegosHome;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setVideojuego({ videojuegos: respuesta.data });
        }
        const getJuegosUsuario = async () => {
            let ruta = rutaJuegosUsuario;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setJuegosUsuario({ juegosUsuario: respuesta.data });
        }
        getVideojuego();
        getJuegosUsuario();
    }, []);

    return (
        <div className="gamesProfile">
            <div className="trendsWrapper">
                <h3 className="title">Juegos en progreso:</h3>
                <div className='tendenciasWrapper'>
                    <ul className='gamesProfileList'>
                        {
                            juegosUsuario?.juegosUsuario?.map((v: juegosUsuario) => {
                                if ((v.usuario.nombre === nameUser) && (v.completado == 0))
                                    return (
                                        <div className="juegosHomeBox">
                                            <Link to={{ pathname: "/api/v1/videojuego/" + v.videojuego.id }}>
                                                <li className="itemGameProfile">
                                                    <span><img src={v.videojuego.imagen} className='imageGameProfile' /></span>
                                                    <div className='titleProfileBox'>
                                                        <h5 className='titleGameProfile'>{v.videojuego.nombre}</h5>
                                                    </div>
                                                </li>
                                            </Link>
                                        </div>
                                    );
                            })
                        }
                    </ul>
                </div>

                <h3 className="title">Juegos completados:</h3>
                <div className='tendenciasWrapper'>
                    <ul className='gamesProfileList'>
                        {
                            juegosUsuario?.juegosUsuario?.map((v: juegosUsuario) => {
                                if ((v.usuario.nombre === nameUser) && (v.completado == 1))
                                    return (
                                        <div className="juegosHomeBox">
                                            <Link to={{ pathname: "/api/v1/videojuego/" + v.videojuego.id }}>
                                                <li className="itemGameProfile">
                                                    <span><img src={v.videojuego.imagen} className='imageGameProfile' /></span>
                                                    <div className='titleProfileBox'>
                                                        <h5 className='titleGameProfile'>{v.videojuego.nombre}</h5>
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