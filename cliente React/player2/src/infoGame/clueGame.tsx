import "./infoGame.css";
import React, { useState, useEffect, useRef } from 'react';
import { Link, Route, BrowserRouter, Routes, useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import Topbar from '../topbar/topbar';
import toast, { Toaster } from "react-hot-toast";

interface IState { videojuego?: player2.videojuegos, pista?: player2.pistas[], usuario?: player2.usuarios; }

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

export default function ClueGame() {
    let navigate = useNavigate();
    const [stGame, setStGame] = useState<IState>({});
    const [stClue, setStClue] = useState<IState>({});
    const [stUser, setStUser] = useState<IState>({});
    const { id } = useParams();

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
        const getUser = async (id: string | undefined) => {
            let rutadeUsuarios = "http://localhost:8080/api/v1/users/";
            let { data } = await axios.get(rutadeUsuarios + id + '/users');
            let usuario: player2.usuarios = data;
            console.log(usuario);
            setStUser({ usuario });
        }
        getUser(id);
        getClue(id);
        getGame(id);
    },
        [id]
    )

    function sendClue() {
        let actualTitle = clueTitle.current?.value;
        let actualBody = clueBody.current?.value;

        if (actualTitle == '' || actualBody == '') {
            toast.error("Debes rellenar los dos campos");
        }
        else {
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
                    <h2 className="title">Escribir pista:</h2>

                    <form onSubmit={createClue} className='formClue'>
                        <Toaster position="top-center" gutter={56} />
                        <input type="text" ref={clueTitle} placeholder="Título" className="inputForm" /> <br />
                        <textarea ref={clueBody} placeholder="Escribe aquí una pista" cols={130} rows={10} /><br />
                        <button type="submit" className="buttonForm" onClick={sendClue}>Crear Pista</button>
                    </form>
                </div>
            </div>
        </>
    )
}