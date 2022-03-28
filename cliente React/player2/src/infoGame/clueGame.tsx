import "./infoGame.css";
import React, { useState, useEffect, useRef } from 'react';
import { Link, Route, BrowserRouter, Routes, useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import Topbar from '../topbar/topbar';
import toast, { Toaster } from "react-hot-toast";
import { videojuegos } from "../modelo/videojuegos";
import { pistas } from "../modelo/pistas";
import { usuarios } from "../modelo/usuarios";

interface IState { videojuego?: videojuegos, pista?: pistas, usuario?: usuarios; }


export default function ClueGame() {
    let navigate = useNavigate();
    const [stGame, setStGame] = useState<IState>({});
    const [stClue, setStClue] = useState<IState>({});
    const [stUser, setStUser] = useState<IState>({});
    const { id } = useParams();

    //const clueUser = useRef<HTMLInputElement>(null);
    //const clueGame = useRef<HTMLInputElement>(null);
    const clueTitle = useRef<HTMLInputElement>(null);
    const clueBody = useRef<HTMLTextAreaElement>(null);

    const createClue = async (event: React.FormEvent<HTMLFormElement>) => {

        event.preventDefault();
        let formulario: HTMLFormElement = event.currentTarget;

        //let userC = clueUser.current?.value;
        //let gameC = clueGame.current?.value;
        let titleC = clueTitle.current?.value;
        let bodyC = clueBody.current?.value;

        /*
        let rutaDeJuego = "http://localhost:8080/api/v1/infoGame/";
        let { data } = await axios.get(rutaDeJuego + id);
        let userActual: player2.usuarios = data;
        let gameActual: player2.videojuegos = data;
        */

        let fechaActual = new Date();

        let rutaDeVideojuego = "http://localhost:8080/api/v1/videojuego/";
        let { data } = await axios.get(rutaDeVideojuego + stGame.videojuego?.id);
        let juegoActual: videojuegos = data;

        
        const userActual = {
            "id": stUser.usuario?.id,
            "nombre": stUser.usuario?.nombre,
            "password": stUser.usuario?.password,
            "rol": stUser.usuario?.rol,
            "avatar": stUser.usuario?.avatar,
            "banner": stUser.usuario?.banner,
            "descripcion": stUser.usuario?.descripcion,
            "color": stUser.usuario?.color,
            "activo": stUser.usuario?.activo,
        }
        

        const newClue = {
            "titulo": titleC,
            "contenido": bodyC,
            "usuario": userActual,
            "videojuego": juegoActual,
            "fecha": fechaActual,
        }

        let ruta = "http://localhost:8080/api/v1/pista";
        const axiospost = async (rutaDePista: string) => {
            try {
                const { data } = await axios.post(rutaDePista, newClue)
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }
        axiospost(ruta).then(respuesta => {
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
        const getClue = async (id: string | undefined) => {
            let rutadePistas = "http://localhost:8080/api/v1/pista/";
            let { data } = await axios.get(rutadePistas + id + '/clues');
            let pista: pistas = data;
            console.log(pista);
            setStClue({ pista });
        }
        const getUser = async (id: string | undefined) => {
            let rutadeUsuarios = "http://localhost:8080/api/v1/usuario/";
            let { data } = await axios.get(rutadeUsuarios + id);
            let usuario: usuarios = data;
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
            //createClue();
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
                    <h2 className="title">Escribir pista:</h2>

                    <form onSubmit={createClue} className='formClue'>
                        <Toaster position="top-center" gutter={56} />
                        <input type="text" ref={clueTitle} placeholder="Título" className="inputForm" required /> <br />
                        <textarea ref={clueBody} placeholder="Escribe aquí una pista" cols={130} rows={10} required /><br />
                        <button type="submit" className="buttonForm" onClick={sendClue}>Crear Pista</button>
                    </form>
                </div>
            </div>
        </>
    )
}