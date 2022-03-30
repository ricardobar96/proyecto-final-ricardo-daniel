import "./reviewGame.css";
import React, { useState, useEffect, useRef } from 'react';
import { Link, Route, BrowserRouter, Routes, useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import Topbar from '../topbar/topbar';
import toast, { Toaster } from "react-hot-toast";
import { videojuegos } from "../modelo/videojuegos";
import { reviews } from "../modelo/reviews";
import { usuarios } from "../modelo/usuarios";

interface IState { videojuego?: videojuegos, review?: reviews, usuario?: usuarios; }

export default function ReviewGame() {
    let navigate = useNavigate();
    const [stGame, setStGame] = useState<IState>({});
    const [stReview, setStReview] = useState<IState>({});
    const [stUser, setStUser] = useState<IState>({});
    const { id } = useParams();

    //const reviewUser = useRef<HTMLInputElement>(null);
    //const reviewGame = useRef<HTMLInputElement>(null);
    const reviewTitle = useRef<HTMLInputElement>(null);
    const reviewBody = useRef<HTMLTextAreaElement>(null);

    const createReview = async (event: React.FormEvent<HTMLFormElement>) => {

        event.preventDefault();
        let formulario: HTMLFormElement = event.currentTarget;

        //let userR = reviewUser.current?.value;
        //let gameR = reviewGame.current?.value;
        let titleR = reviewTitle.current?.value;
        let bodyR = reviewBody.current?.value;

        var usuarioActual: usuarios = JSON.parse(localStorage.getItem('usuarioActual') || '{}');

        let rutaDeVideojuego = "http://localhost:8080/api/v1/videojuego/";
        let { data } = await axios.get(rutaDeVideojuego + stGame.videojuego?.id);
        let juegoActual: videojuegos = data;

        /*
        const userActual = {
            "id": usuarioActual.id,
            "nombre": usuarioActual.id,
            "password": usuarioActual.password,
            "rol": usuarioActual.rol,
            "avatar": usuarioActual.avatar,
            "banner": usuarioActual.banner,
            "descripcion": usuarioActual.descripcion,
            "color": usuarioActual.color,
            "activo": usuarioActual.activo,
        }
        */
        const newReview = new reviews(1, titleR!, bodyR!, new Date(), juegoActual, usuarioActual);

        //console.log("USUARIO: " + newReview.usuario.nombre + " VIDEOJUEGO: " + newReview.videojuego.nombre + " FECHA: " + newReview.fecha);

        let rutaReview = "http://localhost:8080/api/v1/review";
        const axiospost = async (rutaReview: string) => {
            try {
                const { data } = await axios.post(rutaReview, newReview)
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }
        axiospost(rutaReview).then(respuesta => {
            navigate("/api/v1/videojuego/" + stGame.videojuego?.id)
        });
    }

    useEffect(() => {
        const getGame = async (id: string | undefined) => {
            let rutaDeJuego = "http://localhost:8080/api/v1/videojuego/";
            let { data } = await axios.get(rutaDeJuego + id);
            let videojuego: videojuegos = data;
            console.log(videojuego);
            setStGame({ videojuego });
        }

        const getReview = async (id: string | undefined) => {
            let rutadeReviews = "http://localhost:8080/api/v1/review/";
            let { data } = await axios.get(rutadeReviews + id);
            let review: reviews = data;
            console.log(review);
            setStReview({ review });
        }

        const getUser = async (id: string | undefined) => {
            let rutadeUsuarios = "http://localhost:8080/api/v1/usuario/";
            let { data } = await axios.get(rutadeUsuarios + id);
            let usuario: usuarios = data;
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
        else {
            //createReview();
            //navigate("/api/v1/videojuego/" + stGame.videojuego?.id);
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