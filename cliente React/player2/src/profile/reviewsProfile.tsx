import "./reviewsProfile.css";
import React, { useEffect, useRef, useState } from 'react';
import axios from 'axios';
import { Link, Navigate, useNavigate } from 'react-router-dom';
import { videojuegos } from "../modelo/videojuegos";
import { reviews } from "../modelo/reviews";
import { usuarios } from "../modelo/usuarios";
import Topbar from "../topbar/topbar";
import TopbarProfile from "./topbarProfile";

interface IState { videojuego?: videojuegos, review?: reviews[], usuario?: usuarios; }

export default function ReviewsProfile() {
    let navigate = useNavigate();
    let contadorReviewUser = 0;
    let numeroReviews = 0;

    const [stGame, setStGame] = useState<IState>({});
    const [stReview, setStReview] = useState<IState>({});

    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;

    var usuarioActual: usuarios = JSON.parse(localStorage.getItem('usuarioActual') || '{}');

    useEffect(() => {
        const getGame = async () => {
            const rutaGames: string = rutaBase + "/api/v0/videojuego";
            let ruta = rutaGames;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setStGame({ videojuego: respuesta.data });
        }
        const getReview = async () => {
            const rutaReviews: string = rutaBase + "/api/v0/review";
            let ruta = rutaReviews;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setStReview({ review: respuesta.data });
        }
        getReview();
        getGame();
    },
        []
    )
    return (
        <div style={{
            backgroundColor: usuarioActual.color != "Azul" ? usuarioActual.color : 'lightsteelblue',
            height: "100%"
        }}>
            <Topbar />
            <TopbarProfile />
            <div className='reviewsProfileWrapper'>
                <ul className='reviewsProfileList'>
                    {stReview.review?.map((r: reviews) => {
                        numeroReviews += 1
                        if (r.usuario.id == usuarioActual.id) {
                            contadorReviewUser += 1
                            return (
                                <li className="reviewProfileItem">
                                    <Link to={{ pathname: "/api/v0/videojuego/" + r.videojuego.id }} style={{ textDecoration: "none" }}>
                                        <span className="reviewProfileImage"><img src={r.videojuego.imagen} className="reviewProfileGameImage" /></span>
                                    </Link>
                                    <div className="reviewProfileContent">
                                        <Link to={{ pathname: "/api/v0/videojuego/" + r.videojuego.id }} style={{ textDecoration: "none" }}>
                                            <h3 className="reviewProfileGame">{r.videojuego.nombre}</h3>
                                            <h2 className="reviewProfileTitle">{r.titulo}</h2>
                                        </Link>

                                    </div>
                                </li>
                            );
                        }
                        if ((contadorReviewUser === 0) && (stReview.review?.length === numeroReviews))
                            return (
                                <div style={{
                                    backgroundColor: usuarioActual.color != "Azul" ? usuarioActual.color : 'lightsteelblue',
                                    height: "100vh"
                                }}>
                                    <br />
                                    <br />
                                    <br />
                                    <h1 className="titleNoReview">El usuario no ha escrito reviews</h1>
                                </div>
                            );
                    })}
                </ul>
            </div>
        </div>
    )
}