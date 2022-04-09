import "./profileRight.css";
import React, { useEffect, useRef, useState } from 'react';
import axios from 'axios';
import { Link, Navigate, useNavigate } from 'react-router-dom';
import { reviews } from "../../modelo/reviews";
import { usuarios } from "../../modelo/usuarios";
import { videojuegos } from "../../modelo/videojuegos";
import Topbar from "../../topbar/topbar";
import TopbarProfile from "../topbarProfile";

interface IState { videojuego?: videojuegos, review?: reviews[], usuario?: usuarios; }

export default function ProfileRight() {
    let navigate = useNavigate();
    let contadorGamesUser = 0;
    let numeroGames = 0;

    const [stGame, setStGame] = useState<IState>({});

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
            setStGame({ review: respuesta.data });
        }
        getGame();
    },
        []
    )
    return (
        <div className="profileRight">
            <h3 className="title">Estadísticas:</h3>
        </div>
    )
}