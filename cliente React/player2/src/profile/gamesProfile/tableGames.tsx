import "./tableGames.css";
import React, { useState, useEffect, useRef } from 'react'
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';
import { videojuegos } from "../../modelo/videojuegos";
import { usuarios } from "../../modelo/usuarios";
import { juegosUsuario } from "../../modelo/juegosUsuario";
import { generos } from "../../modelo/generos";

interface IState { videojuegos?: videojuegos[]; usuario?: usuarios, juegosUsuario?: juegosUsuario[]; generos?: generos[]; }

interface IProps {
    genre: any,
    order: any,
    year: any,
    search: any
}

export default function TableGames(props: IProps) {
    let nameUser = (localStorage.getItem('user') || '{}');

    const [videojuegos, setVideojuego] = useState<IState>();
    const [juegosUsuario, setJuegosUsuario] = useState<IState>();
    const [generos, setGenero] = useState<IState>();
    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;
    const rutajuegosHome: string = rutaBase + "/api/v0/videojuego";
    const rutaJuegosUsuario: string = rutaBase + "/api/v0/juegousuario";
    const rutageneros: string = rutaBase + "/api/v0/genero";


    let juegosUser: videojuegos[] = [];

    juegosUsuario?.juegosUsuario?.map((v: juegosUsuario) => {
        if ((v.usuario.nombre === nameUser))
            juegosUser.push(v.videojuego);
    })

    let newerGames: videojuegos[] = [];

    juegosUser.map((v: videojuegos) => {
        newerGames.push(v);
    });

    newerGames.sort((a, b) => (a.fecha) - (b.fecha));

    let orderRates: videojuegos[] = [];

    juegosUser.map((v: videojuegos) => {
        orderRates.push(v);
    });

    orderRates.sort((a, b) => (a.puntuacion) - (b.puntuacion));

    orderRates.reverse();

    let alphabetGames: videojuegos[] = [];

    juegosUser.map((v: videojuegos) => {
        alphabetGames.push(v);
    });

    alphabetGames.sort((a, b) => a.nombre.localeCompare(b.nombre));


    let genresFiltered: videojuegos[] = [];

    if (props.genre != undefined) {
        juegosUser.map((a: videojuegos) => {
            a.generos.map((g: generos) => {
                if (g.nombre == props.genre) {
                    genresFiltered.push(a);
                }
            })
        })
    }

    let yearSelectedFiltered: videojuegos[] = [];

    let year;
    let fecha: Date = new Date();

    if (props.year != undefined) {
        juegosUser.map((a: videojuegos) => {
            { fecha = a.fecha }
            { year = new Date(fecha).getFullYear() }
            if (year >= props.year) {
                yearSelectedFiltered.push(a);
            }
        })
    }

    let nameFiltered: videojuegos[] = [];

    let nameGame;

    let nameSearch = "";

    if (props.search != undefined) {
        nameSearch = props.search;
        juegosUser.map((a: videojuegos) => {
            nameGame = a.nombre.toUpperCase();
            if (nameGame.includes(nameSearch.toUpperCase())) {
                nameFiltered.push(a);
            }
        })
    }

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
        const getGenero = async () => {
            let ruta = rutageneros;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setGenero({ generos: respuesta.data });
        }
        getGenero();
        getVideojuego();
        getJuegosUsuario();
    }, []);

    return (
        <div className="gamesProfile">

            <div className='todosWrapper'>
            {props.search != undefined ?
                    <h3 className="title">Filtrados por nombre:</h3>
                    : null
                }

                {props.search != undefined ?
                    <ul className='nuevosList'>
                        {
                            nameFiltered.map((a: videojuegos) => {
                                return (
                                    <div className="juegosTrendsBox">
                                        <Link to={{ pathname: "/api/v0/videojuego/" + a.id }}>
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
                    : null
                }

                {props.genre != undefined ?
                    <h3 className="title">Filtrados por género:</h3>
                    : null
                }

                {props.genre != undefined ?
                    <ul className='nuevosList'>
                        {
                            genresFiltered.map((a: videojuegos) => {
                                return (
                                    <div className="juegosTrendsBox">
                                        <Link to={{ pathname: "/api/v0/videojuego/" + a.id }}>
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
                    : null
                }

                {props.year != undefined ?
                    <h3 className="title">Filtrados por año:</h3>
                    : null
                }
                {props.year != undefined ?
                    <ul className='nuevosList'>
                        {
                            yearSelectedFiltered.map((a: videojuegos) => {
                                return (
                                    <div className="juegosTrendsBox">
                                        <Link to={{ pathname: "/api/v0/videojuego/" + a.id }}>
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
                    : null
                }

                {props.order == "Puntuación" ?
                    <h3 className="title">Filtrados por puntuación:</h3>
                    : null
                }
                {props.order == "Puntuación" ?
                    <ul className='nuevosList'>
                        {
                            orderRates.map((a: videojuegos) => {
                                return (
                                    <div className="juegosTrendsBox">
                                        <Link to={{ pathname: "/api/v0/videojuego/" + a.id }}>
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
                    : null
                }

                {props.order == "Año" ?
                    <h3 className="title">Filtrados por año de salida:</h3>
                    : null
                }
                {props.order == "Año" ?
                    <ul className='nuevosList'>
                        {
                            newerGames.map((a: videojuegos) => {
                                return (
                                    <div className="juegosTrendsBox">
                                        <Link to={{ pathname: "/api/v0/videojuego/" + a.id }}>
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
                    : null
                }

                {props.order == "Título" ?
                    <h3 className="title">Filtrados por título:</h3>
                    : null
                }
                {props.order == "Título" ?
                    <ul className='nuevosList'>
                        {
                            alphabetGames.map((a: videojuegos) => {
                                return (
                                    <div className="juegosTrendsBox">
                                        <Link to={{ pathname: "/api/v0/videojuego/" + a.id }}>
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
                    : null
                }

            </div>

            <div className="trendsWrapper">
                <h3 className="title">Juegos en progreso:</h3>
                <div className='tendenciasWrapper'>
                    <ul className='nuevosList'>
                        {
                            juegosUsuario?.juegosUsuario?.map((v: juegosUsuario) => {
                                if ((v.usuario.nombre === nameUser) && (v.completado == 0))
                                    return (
                                        <div className="juegosHomeBox">
                                            <Link to={{ pathname: "/api/v0/videojuego/" + v.videojuego.id }}>
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
                    <ul className='nuevosList'>
                        {
                            juegosUsuario?.juegosUsuario?.map((v: juegosUsuario) => {
                                if ((v.usuario.nombre === nameUser) && (v.completado == 1))
                                    return (
                                        <div className="juegosHomeBox">
                                            <Link to={{ pathname: "/api/v0/videojuego/" + v.videojuego.id }}>
                                                <li>
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