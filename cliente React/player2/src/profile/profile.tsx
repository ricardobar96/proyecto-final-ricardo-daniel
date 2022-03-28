import "./profile.css";
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
        videojuego: videojuegos;
        usuario: usuarios;
    }

    export interface juegosUsuario {
        id: number;
        completado: number;
        horas: number;
        usuario: usuarios;
        videojuego: videojuegos;
        puntuacion: number;
    }

    export interface usuarios {
        id: number;
        nombre: string;
        password: string;
        rol: string;
        avatar: string;
        activo: number;
        color: string;
        banner: string;
        descripcion: string;
    }
}

export default function Profile() {
    let navigate = useNavigate();
    const [stGame, setStGame] = useState<IState>({});
    const [stReview, setStReview] = useState<IState>({});
    const [stUser, setStUser] = useState<IState>({});
    const { id } = useParams();

    var usuarioActual: player2.usuarios = JSON.parse(localStorage.getItem('usuarioActual') || '{}');

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
            let rutadeUsuarios = "http://localhost:8080/api/v1/usuario/";
            let { data } = await axios.get(rutadeUsuarios + id);
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

    return (
        <>
            Nombre usuario: {stUser.usuario?.nombre}
        </>
    )
}