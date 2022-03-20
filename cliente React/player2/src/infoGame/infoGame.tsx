import "./infoGame.css";
import { VideogameAsset } from "@material-ui/icons";
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

    const createClue = async (event: React.FormEvent<HTMLFormElement>) => {

        event.preventDefault();
        let formulario: HTMLFormElement = event.currentTarget;

        let userC = clueUser.current?.value;
        let gameC = clueGame.current?.value;
        let titleC = clueTitle.current?.value;
        let bodyC = clueBody.current?.value;

        let rutaDeJuego = "http://localhost:8080/api/v1/infoGame/";
        let { data } = await axios.get(rutaDeJuego + id);
        let userActual: player2.usuarios = data;
        let gameActual: player2.videojuegos = data;
        let fechaActual = new Date();

        const newClue = {
            "titulo": titleC,
            "contenido": bodyC,
            "idusuario": userActual.id,
            "idvideojuego": gameActual.id,
            "fecha": fechaActual,
        }

        let ruta = "http://localhost:8080/api/v1/infogame";
        try {
            const { data } = await axios.post(ruta, newClue)
            console.log(data);
        } catch (error) {
            console.log(error);

        }
        navigate("/infogame/" + gameActual.id);
    }

    const createReview = async (event: React.FormEvent<HTMLFormElement>) => {

        event.preventDefault();
        let formulario: HTMLFormElement = event.currentTarget;

        let userR = reviewUser.current?.value;
        let gameR = reviewGame.current?.value;
        let titleR = reviewTitle.current?.value;
        let bodyR = reviewBody.current?.value;

        let rutaDeJuego = "http://localhost:8080/api/v1/infoGame/";
        let { data } = await axios.get(rutaDeJuego + id);
        let userActual: player2.usuarios = data;
        let gameActual: player2.videojuegos = data;
        let fechaActual = new Date();

        const newClue = {
            "titulo": titleR,
            "contenido": bodyR,
            "idusuario": userActual.id,
            "idvideojuego": gameActual.id,
            "fecha": fechaActual,
        }

        let ruta = "http://localhost:8080/api/v1/infogame";
        try {
            const { data } = await axios.post(ruta, newClue)
            console.log(data);
        } catch (error) {
            console.log(error);

        }
        navigate("/infogame/" + gameActual.id);
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
                    <form onSubmit={createClue} className='formClue'>
                        <input type="text" ref={clueTitle} placeholder="Título" className="inputForm" /> <br />
                        <textarea ref={clueBody} placeholder="Escribe aquí una pista" cols={40} rows={10} /> <br />
                        <button type="submit" className="buttonForm">Crear </button>
                    </form>

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
                    <form onSubmit={createReview} className='formReview'>
                        <input type="text" ref={reviewTitle} placeholder="Título" className="inputForm" /> <br />
                        <textarea ref={reviewBody} placeholder="Escribe aquí una review" cols={40} rows={10} /><br />
                        <button type="submit" className="buttonForm">Crear </button>
                    </form>
                </div>
            </div>
        </>
    )
}