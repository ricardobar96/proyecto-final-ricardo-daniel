import React, { useState, useEffect } from 'react'
import './juegosHome.css'
import axios from 'axios';
import { Link, useParams, Navigate, useNavigate } from 'react-router-dom';
import { videojuegos } from '../modelo/videojuegos';
import { usuarios } from '../modelo/usuarios';
import { juegosUsuario } from '../modelo/juegosUsuario';

interface IProps { }

interface IState { videojuegos?: videojuegos[], usuario?: usuarios, juegosUsuario?: juegosUsuario[]; }

export const JuegosHome = () => {
    const [videojuegos, setVideojuego] = useState<IState>();
    const [usuario, setUsuario] = useState<IState>();
    const [juegosUsuario, setJuegosUsuario] = useState<IState>();
    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;
    const { id } = useParams();
    let navigate = useNavigate();
    let contadorProgreso = 0;

    var usuarioActual: usuarios = JSON.parse(localStorage.getItem('usuarioActual') || '{}');
    let nameUser = usuarioActual.nombre;

    let dateThirtyDays = new Date();
    dateThirtyDays.setDate(dateThirtyDays.getDate() - 3000);

    let newerGames: videojuegos[] = [];

    videojuegos?.videojuegos?.map((v: videojuegos) => {
        newerGames.push(v);
    });

    newerGames.sort((a, b) => (a.fecha) - (b.fecha));

    let popularGames: videojuegos[] = [];

    videojuegos?.videojuegos?.map((v: videojuegos) => {
        if (v.puntuacion >= 7) {
            popularGames.push(v);
        }
    });

    popularGames.sort((a, b) => (a.puntuacion) - (b.puntuacion));

    popularGames.reverse();

    function goCreateGame() {
        navigate("/api/v1/createGame");
    }

    useEffect(() => {
        const getVideojuego = async () => {
            const rutajuegosHome: string = rutaBase + "/api/v0/videojuego";
            let ruta = rutajuegosHome;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setVideojuego({ videojuegos: respuesta.data });
        }
        const getUsuario = async () => {
            const rutaUsuario: string = rutaBase + "/api/v0/usuario";
            let ruta = rutaUsuario;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setUsuario({ usuario: respuesta.data });
        }
        const getJuegosUsuario = async () => {
            const rutaJuegosUsuario: string = rutaBase + "/api/v0/juegousuario";
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
            <div className='buttonCreateBox'>
                {usuarioActual.rol == "ROLE_ADMIN" ?
                    <button className="buttonCreate" onClick={goCreateGame}>Crear juego</button>
                    : null
                }
            </div>

            <h3 className='title'>Videojuegos en tendencias:</h3>
            <div className='tendenciasWrapper'>
                <ul className='tendenciasList'>
                    {
                        popularGames.slice(0, 4).map((a: videojuegos) => {
                            return (
                                <div className='juegosHomeBox'>
                                    <Link to={{ pathname: "/api/v0/videojuego/" + a.id }}>
                                        <li>
                                            <span><img src={a.imagen} className='imageGameHome' /></span>
                                            <div className='titleHomeBox'>
                                                <h5 className='titleGameHome'>{a.nombre}</h5>
                                            </div>
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
                        newerGames.slice(0, 4).map((a: videojuegos) => {
                            return (
                                <div className='juegosHomeBox'>
                                    <Link to={{ pathname: "/api/v0/videojuego/" + a.id }}>
                                        <li>
                                            <span><img src={a.imagen} className='imageGameHome' /></span>
                                            <div className='titleHomeBox'>
                                                <h5 className='titleGameHome'>{a.nombre}</h5>
                                            </div>
                                        </li>
                                    </Link>
                                </div>
                            );
                        })
                    }
                </ul>
            </div>

            {usuarioActual.nombre != null ?
                <h3 className='title'>Tus juegos en progreso:</h3>
                : null
            }

            <div className='nuevosWrapper'>
                <ul className='nuevosList'>
                    {
                        juegosUsuario?.juegosUsuario?.map((v: juegosUsuario) => {
                            if ((v.usuario.nombre === nameUser) && (v.completado == 0) && (contadorProgreso <= 3)) {
                                //contadorProgreso = contadorProgreso + 1
                                return (
                                    <div className='juegosHomeBox'>
                                        <Link to={{ pathname: "/api/v0/videojuego/" + v.videojuego.id }}>
                                            <li>
                                                <span><img src={v.videojuego.imagen} className='imageGameHome' /></span>
                                                <div className='titleHomeBox'>
                                                    <h5 className='titleGameHome'>{v.videojuego.nombre}</h5>
                                                </div>
                                            </li>
                                        </Link>
                                    </div>
                                );
                            }
                        })
                    }
                </ul>
            </div>
        </div>
    )
}
export default JuegosHome;
