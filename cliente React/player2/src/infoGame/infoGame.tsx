import "./infoGame.css";
import React, { useState, useEffect, useRef } from 'react';
import { Link, Route, BrowserRouter, Routes, useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import Topbar from '../topbar/topbar';

interface IState { videojuego?: player2.videojuegos, pista?: player2.pistas[], review?: player2.reviews[], usuario?: player2.usuarios; }

declare module player2 {

    export interface videojuegos {
        id: number;
        nombre: string;
        fecha: string;
        puntuacion: number;
        descripcion: string;
        imagen: string;
    }

    export interface pistas {
        id: number;
        titulo: string;
        contenido: string;
        fecha: string;
        idvideojuego: number;
        idusuario: number;
    }

    export interface reviews {
        id: number;
        titulo: string;
        contenido: string;
        fecha: string;
        idvideojuego: number;
        idusuario: number;
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

export default function InfoGame() {
    let navigate = useNavigate();
    const [stGame, setStGame] = useState<IState>({});
    const [stClue, setStClue] = useState<IState>({});
    const [stReview, setStReview] = useState<IState>({});
    const [stUser, setStUser] = useState<IState>({});
    const { id } = useParams();

    const reviewUser = useRef<HTMLInputElement>(null);
    const reviewGame = useRef<HTMLInputElement>(null);
    const reviewTitle = useRef<HTMLInputElement>(null);
    const reviewBody = useRef<HTMLTextAreaElement>(null);

    const clueUser = useRef<HTMLInputElement>(null);
    const clueGame = useRef<HTMLInputElement>(null);
    const clueTitle = useRef<HTMLInputElement>(null);
    const clueBody = useRef<HTMLTextAreaElement>(null);

    function createReview() {
        let ruta = "/api/v1/newReview/videojuego/" + stGame.videojuego?.id;
        navigate(ruta);
    }

    function createClue() {
        let ruta = "/api/v1/newClue/videojuego/" + stGame.videojuego?.id;
        navigate(ruta);
    }

    useEffect(() => {
        const getGame = async (id: string | undefined) => {
            let rutaDeJuego = "http://localhost:8080/api/v1/videojuego/";
            let { data } = await axios.get(rutaDeJuego + id);
            let videojuego: player2.videojuegos = data;
            console.log(videojuego);
            setStGame({ videojuego });
        }
        const getClue = async (id: string | undefined) => {
            let rutadePistas = "http://localhost:8080/api/v1/clues/";
            let { data } = await axios.get(rutadePistas + id + '/clues');
            let pista: player2.pistas[] = data;
            console.log(pista);
            setStClue({ pista });
        }
        const getReview = async (id: string | undefined) => {
            let rutadeReviews = "http://localhost:8080/api/v1/reviews/";
            let { data } = await axios.get(rutadeReviews + id + '/reviews');
            let review: player2.reviews[] = data;
            console.log(review);
            setStClue({ review });
        }
        const getUser = async (id: string | undefined) => {
            let rutadeUsuarios = "http://localhost:8080/api/v1/users/";
            let { data } = await axios.get(rutadeUsuarios + id + '/users');
            let usuario: player2.usuarios = data;
            console.log(usuario);
            setStUser({ usuario });
        }
        getUser(id);
        getReview(id);
        getClue(id);
        getGame(id);
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

                    <div className="desciption">
                        <h3 className="title">Descripción:</h3>
                        <p className="infoP">
                            {stGame.videojuego?.descripcion}
                        </p>
                    </div>
                    <div className='infoPistasWrapper'>
                        <h3 className="title">Pistas:</h3>
                        <ul className='pistasList'>
                            {stClue.pista?.map((p: player2.pistas) => {
                                return (
                                    <li>
                                        <span>{p.titulo} ({p.fecha})</span>
                                        <p>{p.contenido}</p>
                                    </li>
                                );
                            })}
                        </ul>
                    </div>
                    <button type="button" className="buttonForm" onClick={createClue}>Crear Pista</button>

                    <div className='infoReviewsWrapper'>
                        <h3 className="title">Reviews:</h3>
                        <ul className='reviewsList'>
                            {stReview.review?.map((r: player2.reviews) => {
                                return (
                                    <li>
                                        <span>{r.titulo} ({r.fecha})</span>
                                        <p>{r.contenido}</p>
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