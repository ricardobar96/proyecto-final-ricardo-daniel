import "./createGame.css";
import React, { useEffect, useRef, useState } from 'react';
import axios from 'axios';
import { Link, Navigate, useNavigate, useParams } from 'react-router-dom';
import { usuarios } from "../modelo/usuarios";
import Topbar from "../topbar/topbar";
import { videojuegos } from '../modelo/videojuegos';

interface IState { videojuegos?: videojuegos }

export default function CreateGame() {
    let navigate = useNavigate();
    const fechaGame = useRef<HTMLInputElement>(null);
    const nombreGame = useRef<HTMLInputElement>(null);
    const imagenGame = useRef<HTMLInputElement>(null);
    const descripcionGame = useRef<HTMLTextAreaElement>(null);
    const { id } = useParams();

    const token = localStorage.getItem("token") as string;
    const headers = {
        headers: { Authorization: token }
    };

    const [videojuegos, setVideojuego] = useState<IState>();

    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;

    var usuarioActual: usuarios = JSON.parse(localStorage.getItem('usuarioActual') || '{}');

    useEffect(() => {
        const getVideojuego = async (id: string | undefined) => {
            const rutajuegosHome: string = rutaBase + "/api/v0/videojuego";
            let ruta = rutajuegosHome;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setVideojuego({ videojuegos: respuesta.data });
        }
        getVideojuego(id);
    },
        [id]
    )

    function createGame() {
        let nombre = nombreGame.current?.value;
        let descripcion = descripcionGame.current?.value;
        let fecha = fechaGame.current?.value;
        let imagen = imagenGame.current?.value;

        //const newGame = new videojuegos(1, nombre, fecha, descripcion, imagen);

        let newGame = {
            nombre: nombre,
            fecha: fecha,
            descripcion: descripcion,
            imagen: imagen
        }

        let ruta = "http://localhost:8080/api/v1/videojuego";
        const axiospost = async (rutaGame: string) => {
            try {
                const { data } = await axios.put(rutaGame, newGame, headers)
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }
        axiospost(ruta).then(respuesta => {
            navigate("/")
        });

    }

    return (
        <div>
            <Topbar />
            <div className="ajustes">
                <h3 className="titleCreate">Crear juego:</h3>
                <br />
                <div>
                    <form className="formCreateGame">
                        <input placeholder="Nombre" className="inputCreate" ref={nombreGame} required /> <br />
                        <input placeholder="Fecha (yyyy-MM-dd)" className="inputCreate" ref={fechaGame} required /> <br />
                        <input placeholder="Dirección imagen" className="inputCreate" ref={imagenGame} required /> <br />
                        <textarea placeholder="Descripción" className="inputCreate" ref={descripcionGame} cols={80} rows={10} required /> <br />
                        <button type="button" className="buttonForm" onClick={createGame}>Crear</button>
                    </form>
                </div>
            </div>
        </div>
    )
}