import "./infoGame.css";
import React, { useState, useEffect, useRef } from 'react';
import { Link, Route, BrowserRouter, Routes, useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import Topbar from '../topbar/topbar';
import { videojuegos } from "../modelo/videojuegos";
import { pistas } from "../modelo/pistas";
import { reviews } from "../modelo/reviews";
import { usuarios } from "../modelo/usuarios";
import { Checkbox } from "@material-ui/core";
import { juegosUsuario } from '../modelo/juegosUsuario';

interface IState { videojuego?: videojuegos, pista?: pistas[], review?: reviews[], usuario?: usuarios; juegosUsuario?: juegosUsuario[]; }

export default function InfoGame() {
    let navigate = useNavigate();
    const [stGame, setStGame] = useState<IState>({});
    const [stClue, setStClue] = useState<IState>({});
    const [stReview, setStReview] = useState<IState>({});
    const [stUser, setStUser] = useState<IState>({});
    const [stUserGames, setStUserGames] = useState<IState>({});
    const { id } = useParams();

    const token = localStorage.getItem("token") as string;
    const headers = {
        headers: { Authorization: token }
    };

    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;

    const reviewUser = useRef<HTMLInputElement>(null);
    const reviewGame = useRef<HTMLInputElement>(null);
    const reviewTitle = useRef<HTMLInputElement>(null);
    const reviewBody = useRef<HTMLTextAreaElement>(null);

    const clueUser = useRef<HTMLInputElement>(null);
    const clueGame = useRef<HTMLInputElement>(null);
    const clueTitle = useRef<HTMLInputElement>(null);
    const clueBody = useRef<HTMLTextAreaElement>(null);

    var usuarioActual: usuarios = JSON.parse(localStorage.getItem('usuarioActual') || '{}');
    let initialCheckComplete: any = 0;
    let completado = false;
    let progress = false;
    let juegoUsuarioActual: juegosUsuario;

    stUserGames.juegosUsuario?.map((j: juegosUsuario) => {
        if (j.videojuego.id === stGame.videojuego?.id) {
            if (j.usuario.id === usuarioActual.id) {
                juegoUsuarioActual = j;
                initialCheckComplete = 1;
                progress = true;
                if (j.completado == 1) {
                    completado = true;
                }
            }
        }
    });

    const [checkedCompleted, setCheckedCompleted] = React.useState(false);
    const [checkedProgress, setCheckedProgress] = React.useState(false);

    function createReview() {
        let ruta = "/api/v1/newReview/videojuego/" + stGame.videojuego?.id;
        navigate(ruta);
    }

    function createClue() {
        let ruta = "/api/v1/newClue/videojuego/" + stGame.videojuego?.id;
        navigate(ruta);
    }

    function handleChangeCompleted() {
        if (completado == false) {
            const newJuegoUsuario = new juegosUsuario(juegoUsuarioActual.id, 1, juegoUsuarioActual.horas, 
                juegoUsuarioActual.usuario, juegoUsuarioActual.videojuego, juegoUsuarioActual.puntuacion,
                juegoUsuarioActual.fecha);
            let ruta = "http://localhost:8080/api/v1/juegousuario";
            const axiosput = async (rutaDeJuegoUsuario: string) => {
                try {
                    const { data } = await axios.put(rutaDeJuegoUsuario + "/" + juegoUsuarioActual.id, newJuegoUsuario, headers)
                    console.log(data);
                } catch (error) {
                    console.log(error);
                }
            }
            axiosput(ruta).then(respuesta => {
                navigate(0)
            });

            //completado = true;
        }
        if (completado == true) {
            const newJuegoUsuario = new juegosUsuario(juegoUsuarioActual.id, 0, juegoUsuarioActual.horas, juegoUsuarioActual.usuario, 
                juegoUsuarioActual.videojuego, juegoUsuarioActual.puntuacion, juegoUsuarioActual.fecha);
            let ruta = "http://localhost:8080/api/v1/juegousuario";
            const axiosput = async (rutaDeJuegoUsuario: string) => {
                try {
                    const { data } = await axios.put(rutaDeJuegoUsuario + "/" + juegoUsuarioActual.id, newJuegoUsuario, headers)
                    console.log(data);
                } catch (error) {
                    console.log(error);
                }
            }
            axiosput(ruta).then(respuesta => {
                navigate(0)
            });

            //completado = false;
        }
        setCheckedCompleted(!checkedCompleted);
    }

    function handleChangeProgress() {
        if (progress == false) {
            const newJuegoUsuario = new juegosUsuario(1, 0, 0, usuarioActual, stGame.videojuego!, 5, new Date());
            let ruta = "http://localhost:8080/api/v1/juegousuario";
            const axiospost = async (rutaDeJuegoUsuario: string) => {
                try {
                    const { data } = await axios.post(rutaDeJuegoUsuario, newJuegoUsuario, headers)
                    console.log(data);
                } catch (error) {
                    console.log(error);
                }
            }
            axiospost(ruta).then(respuesta => {
                navigate(0)
            });

            completado = true;
        }
        if (progress == true) {
            let ruta = "http://localhost:8080/api/v1/juegousuario";
            const axiosdelete = async (rutaDeJuegoUsuario: string) => {
                try {
                    const { data } = await axios.delete(rutaDeJuegoUsuario + "/" + juegoUsuarioActual.id, headers)
                    console.log(data);
                } catch (error) {
                    console.log(error);
                }
            }
            axiosdelete(ruta).then(respuesta => {
                navigate(0)
            });

            completado = false;
        }
    }

    /*
    function checkUserHasGame() {
        stUserGames.juegosUsuario?.map((j: juegosUsuario) => {
            if (j.videojuego.id === stGame.videojuego?.id) {
                if (j.usuario.id === usuarioActual.id) {
                    initialCheckComplete = 1;
                }
            }
            console.log("EL VALOR ES " + initialCheckComplete);
        });
    }
    */

    useEffect(() => {
        const getGame = async (id: string | undefined) => {
            let rutaDeJuego = "http://localhost:8080/api/v0/videojuego/";
            let { data } = await axios.get(rutaDeJuego + id);
            let videojuego: videojuegos = data;
            console.log(videojuego);
            setStGame({ videojuego });
        }
        const getClue = async () => {
            const rutaPistas: string = rutaBase + "/api/v0/pista";
            let ruta = rutaPistas;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setStClue({ pista: respuesta.data });
        }
        const getReview = async () => {
            const rutaReviews: string = rutaBase + "/api/v0/review";
            let ruta = rutaReviews;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setStReview({ review: respuesta.data });
        }
        const getUser = async (id: string | undefined) => {
            let rutadeUsuarios = "http://localhost:8080/api/v0/usuario/";
            let { data } = await axios.get(rutadeUsuarios + id);
            let usuario: usuarios = data;
            console.log(usuario);
            setStUser({ usuario });
        }
        const getJuegosUsuario = async () => {
            const rutaJuegosUsuario: string = rutaBase + "/api/v0/juegousuario";
            let ruta = rutaJuegosUsuario;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setStUserGames({ juegosUsuario: respuesta.data });
        }
        getUser(id);
        getReview();
        getClue();
        getGame(id);
        getJuegosUsuario();
    },
        [id]
    )
    return (
        <>
            <Topbar />
            <div className="infoGame">
                <div className="infoGameWrapper">
                    <h2 className='titleGameInfo'>{stGame.videojuego?.nombre}</h2>
                    <span><img src={stGame.videojuego?.imagen} className='imageGameInfo' /></span>
                    <br />
                    <br />
                    <br />

                    {usuarioActual.nombre != null ?
                        <span style={{ color: "orangered", fontWeight: "bolder" }}>¿En progreso?</span>
                        : null
                    }

                    {usuarioActual.nombre != null ?
                        <Checkbox
                            value={checkedProgress}
                            checked={progress}
                            onClick={handleChangeProgress}
                            style={{ marginLeft: "5px" }}
                        />
                        : null
                    }

                    <br />

                    {initialCheckComplete == 1 ?
                        <span style={{ color: "orangered", fontWeight: "bolder" }}>¿Completado?</span>
                        : null
                    }

                    {initialCheckComplete == 1 ?
                        <Checkbox
                            value={checkedCompleted}
                            checked={completado}
                            //onChange={handleChangeCompleted}
                            onClick={handleChangeCompleted}
                            style={{ marginLeft: "5px" }}
                        />
                        : null
                    }

                    <div className="description">
                        <h3 className="title">Descripción:</h3>
                        <p className="infoP">
                            {stGame.videojuego?.descripcion}
                        </p>
                    </div>
                    <div className='infoPistasWrapper'>
                        <h3 className="title">Pistas:</h3>
                        <ul className='pistasList'>
                            {stClue.pista?.map((p: pistas) => {
                                if (p.videojuego.id == stGame.videojuego?.id)
                                    return (
                                        <li className="pistasItem">
                                            <Link to={{ pathname: "/api/v0/usuario/" + p.usuario.id }} style={{ textDecoration: "none" }}>
                                                <span className="pistasLeft"><img src={p.usuario.avatar} className="avatarInfo" /> {p.usuario.nombre}</span>
                                            </Link>
                                            <div className="pistasRight">
                                                <h3>{p.titulo} ({p.fecha})</h3>
                                                <p>{p.contenido}</p>
                                            </div>
                                        </li>
                                    );
                            })}
                        </ul>
                    </div>
                    <button type="button" className="buttonForm" onClick={createClue}>Crear Pista</button>

                    <div className='infoReviewsWrapper'>
                        <h3 className="title">Reviews:</h3>
                        <ul className='reviewsList'>
                            {stReview.review?.map((r: reviews) => {
                                if (r.videojuego.id == stGame.videojuego?.id)
                                    return (
                                        <li className="pistasItem">
                                            <Link to={{ pathname: "/api/v0/usuario/" + r.usuario.id }} style={{ textDecoration: "none" }}>
                                                <span className="pistasLeft"><img src={r.usuario.avatar} className="avatarInfo" /> {r.usuario.nombre}</span>
                                            </Link>
                                            <div className="pistasRight">
                                                <h3>{r.titulo} ({r.fecha})</h3>
                                                <p>{r.contenido}</p>
                                            </div>
                                        </li>
                                    );
                            })}
                        </ul>
                    </div>
                    <button type="button" className="buttonForm" onClick={createReview}>Crear Review</button>
                </div>
            </div>
        </>
    )
}