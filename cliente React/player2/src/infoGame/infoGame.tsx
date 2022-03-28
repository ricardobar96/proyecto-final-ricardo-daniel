import "./infoGame.css";
import React, { useState, useEffect, useRef } from 'react';
import { Link, Route, BrowserRouter, Routes, useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import Topbar from '../topbar/topbar';
import { videojuegos } from "../modelo/videojuegos";
import { pistas } from "../modelo/pistas";
import { reviews } from "../modelo/reviews";
import { usuarios } from "../modelo/usuarios";

interface IState { videojuego?: videojuegos, pista?: pistas[], review?: reviews[], usuario?: usuarios; }

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
            let videojuego: videojuegos = data;
            console.log(videojuego);
            setStGame({ videojuego });
        }
        const getClue = async (id: string | undefined) => {
            let rutadePistas = "http://localhost:8080/api/v1/clues/";
            let { data } = await axios.get(rutadePistas + id + '/clues');
            let pista: pistas[] = data;
            console.log(pista);
            setStClue({ pista });
        }
        const getReview = async (id: string | undefined) => {
            let rutadeReviews = "http://localhost:8080/api/v1/review/";
            let { data } = await axios.get(rutadeReviews);
            let review: reviews[] = data;
            console.log(review);
            setStClue({ review });
        }
        const getUser = async (id: string | undefined) => {
            let rutadeUsuarios = "http://localhost:8080/api/v1/usuario/";
            let { data } = await axios.get(rutadeUsuarios + id + '/users');
            let usuario: usuarios = data;
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
                            {stClue.pista?.map((p: pistas) => {
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
                            {stReview.review?.map((r: reviews) => {
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