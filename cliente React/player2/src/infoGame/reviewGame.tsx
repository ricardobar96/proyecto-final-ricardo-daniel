import "./reviewGame.css";
import React, { useState, useEffect, useRef } from 'react';
import { Link, Route, BrowserRouter, Routes, useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import Topbar from '../topbar/topbar';
import toast, { Toaster } from "react-hot-toast";

interface IState { videojuego?: player2.videojuegos, review?: player2.reviews[], usuario?: player2.usuarios; }

declare module player2 {

    export interface videojuegos {
        id: number;
        nombre: string;
        fecha: string;
        puntuacion: number;
        descripcion: string;
        imagen: string;
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

export default function ReviewGame() {
    let navigate = useNavigate();
    const [stGame, setStGame] = useState<IState>({});
    const [stReview, setStReview] = useState<IState>({});
    const [stUser, setStUser] = useState<IState>({});
    const { id } = useParams();

    const reviewUser = useRef<HTMLInputElement>(null);
    const reviewGame = useRef<HTMLInputElement>(null);
    const reviewTitle = useRef<HTMLInputElement>(null);
    const reviewBody = useRef<HTMLTextAreaElement>(null);

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

        const newReview = {
            "titulo": titleR,
            "contenido": bodyR,
            "idusuario": userActual.id,
            "idvideojuego": gameActual.id,
            "fecha": fechaActual,
        }

        let ruta = "http://localhost:8080/api/v1/infogame";
        try {
            const { data } = await axios.post(ruta, newReview)
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

        const getReview = async (id: string | undefined) => {
            let rutadeReviews = "http://localhost:8080/api/v1/reviews/";
            let { data } = await axios.get(rutadeReviews + id + '/reviews');
            let review: player2.reviews[] = data;
            console.log(review);
            setStReview({ review });
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
        getGame(id);
    },
        [id]
    )

    function sendReview() {
        let actualTitle = reviewTitle.current?.value;
        let actualBody = reviewBody.current?.value;

        if (actualTitle == '' || actualBody == '') {
            toast.error("Debes rellenar los dos campos");
        }
        else{
            navigate('/');
        }
    }

    return (
        <>
            <Topbar />
            <div className="infoGame">
                <div className="infoGameWrapper">
                    <h1 className='titleGameInfo'>{stGame.videojuego?.nombre}</h1>
                    <br />
                    <h2 className="title">Escribir review:</h2>

                    <form onSubmit={createReview} className='formReview'>
                        <Toaster position="top-center" gutter={56} />
                        <input type="text" ref={reviewTitle} placeholder="Título" className="inputForm" required /> <br />
                        <textarea ref={reviewBody} placeholder="Escribe aquí una review" cols={130} rows={10} required /><br />
                        <button type="submit" className="buttonForm" onClick={sendReview}>Crear </button>
                    </form>
                </div>
            </div>
        </>
    )
}