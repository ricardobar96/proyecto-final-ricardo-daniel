import "./trends.css";
import React, { useState, useEffect, useRef } from 'react'
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';
import { videojuegos } from "../modelo/videojuegos";
import { generos } from "../modelo/generos";

interface IState { videojuegos?: videojuegos[]; generos?: generos[]; }

interface IProps {
    genre: any,
    order: any,
    year: any
}

export default function Trends(props: IProps) {
    const [videojuegos, setVideojuego] = useState<IState>();
    const [generos, setGenero] = useState<IState>();
    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;
    const rutajuegosHome: string = rutaBase + "/api/v0/videojuego";
    const rutageneros: string = rutaBase + "/api/v0/genero";

    let popularGames: videojuegos[] = [];

    videojuegos?.videojuegos?.map((v: videojuegos) => {
        if (v.puntuacion >= 7) {
            popularGames.push(v);
        }
    });

    popularGames.sort((a, b) => (a.puntuacion) - (b.puntuacion));

    popularGames.reverse();


    let newerGames: videojuegos[] = [];

    videojuegos?.videojuegos?.map((v: videojuegos) => {
        newerGames.push(v);
    });

    newerGames.sort((a, b) => (a.fecha) - (b.fecha));

    let orderRates: videojuegos[] = [];

    videojuegos?.videojuegos?.map((v: videojuegos) => {
        orderRates.push(v);
    });

    orderRates.sort((a, b) => (a.puntuacion) - (b.puntuacion));

    orderRates.reverse();

    let alphabetGames: videojuegos[] = [];

    videojuegos?.videojuegos?.map((v: videojuegos) => {
        alphabetGames.push(v);
    });

    alphabetGames.sort((a, b) => a.nombre.localeCompare(b.nombre));


    let genresFiltered: videojuegos[] = [];

    if (props.genre != undefined) {
        videojuegos?.videojuegos?.map((a: videojuegos) => {
            a.generos.map((g: generos) => {
                if (g.nombre == props.genre) {
                    genresFiltered.push(a);
                }
            })
        })
    }

    let yearSelectedFiltered: videojuegos[] = [];

    let year;
    let fecha:Date = new Date();

    if (props.year != undefined) {
        videojuegos?.videojuegos?.map((a: videojuegos) => {
            { fecha = a.fecha }
            { year = new Date(fecha).getFullYear()}
            if (year >= props.year) {
                yearSelectedFiltered.push(a);
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
        const getGenero = async () => {
            let ruta = rutageneros;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setGenero({ generos: respuesta.data });
        }
        getGenero();
        getVideojuego();
    }, []);

    return (
        <div className="trends">

            <div className='todosWrapper'>
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
                <h3 className="title">Tendencias:</h3>
                <div className='tendenciasWrapper'>
                    <ul className='nuevosList'>
                        {
                            popularGames.slice(0, 6).map((a: videojuegos) => {
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
                </div>
                <h3 className="title">Nuevos:</h3>
                <div className='nuevosWrapper'>
                    <ul className='nuevosList'>
                        {
                            newerGames.slice(0, 6).map((a: videojuegos) => {
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
                </div>

                <h3 className="title">Todos:</h3>
                <div className='todosWrapper'>
                    <ul className='nuevosList'>
                        {
                            videojuegos?.videojuegos?.map((a: videojuegos) => {
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
                </div>
            </div>
        </div>
    )
}