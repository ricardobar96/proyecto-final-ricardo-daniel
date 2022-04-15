import "./profileRight.css";
import React, { useEffect, useRef, useState } from 'react';
import axios from 'axios';
import { Link, Navigate, useNavigate, useParams } from 'react-router-dom';
import { usuarios } from "../../modelo/usuarios";
import { videojuegos } from "../../modelo/videojuegos";
import { juegosUsuario } from '../../modelo/juegosUsuario';
import { Actividad } from "./actividad";
interface IState { videojuegos?: videojuegos[]; usuario?: usuarios | undefined; juegosUsuario?: juegosUsuario[]; }

export default function ProfileRight() {
    let navigate = useNavigate();
    let contadorGamesUser = 0;
    let hoursPlayed = 0;
    let totalGrades = 0;

    const [videojuegos, setVideojuego] = useState<IState>();
    const [juegosUsuario, setJuegosUsuario] = useState<IState>();
    const [stUser, setStUser] = useState<IState>({});


    let usuario:usuarios = stUser.usuario!; 

    const { id } = useParams();

    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;

    var usuarioActual: usuarios = JSON.parse(localStorage.getItem('usuarioActual') || '{}');

    juegosUsuario?.juegosUsuario?.map((j: juegosUsuario) => {
        if (j.usuario.id === stUser.usuario?.id){
            contadorGamesUser += 1;
            hoursPlayed += j.horas;
            totalGrades += j.puntuacion;
        }
    });

    let daysPlayed = hoursPlayed/24;
    let totalDays = Number((daysPlayed).toFixed(1));

    let average = totalGrades/contadorGamesUser;
    let totalAverage = Number((average).toFixed(1));

    if(!(totalAverage >= 0)){
        totalAverage = 0;
    }

    useEffect(() => {
        const getVideojuego = async () => {
            const rutajuegosHome: string = rutaBase + "/api/v0/videojuego";
            let ruta = rutajuegosHome;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setVideojuego({ videojuegos: respuesta.data });
        }
        const getUser = async (id: string | undefined) => {
            let rutadeUsuarios = "http://localhost:8080/api/v0/usuario/";
            let { data } = await axios.get(rutadeUsuarios + id);
            let usuario: usuarios = data;
            console.log(usuario);
            setStUser({ usuario });
        }
        const getJuegosUsuario = async () => {
            const rutaJuegosUsuario: string = rutaBase + "/api/v0/juegousuario";
            let ruta = rutaJuegosUsuario;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setJuegosUsuario({ juegosUsuario: respuesta.data });
        }
        getJuegosUsuario();
        getVideojuego();
        getUser(id);
    },
        []
    )
    return (
        <div className="profileRight">
            <div className="statsContent">
                <ul>
                    <li className="statsList">
                        <span className="valueStats">{contadorGamesUser}</span>
                        <h4 className="title">Total videojuegos</h4>
                    </li>
                </ul>

                <ul>
                    <li className="statsList">
                        <span className="valueStats">{totalDays}</span>
                        <h4 className="title">Días jugados</h4>
                    </li>
                </ul>

                <ul>
                    <li className="statsList">
                        <span className="valueStats">{totalAverage}</span>
                        <h4 className="title">Puntuación media</h4>
                    </li>
                </ul>
            </div>

            <br/>

            <h3 className="title">Actividad:</h3>
            {(typeof usuario !== 'undefined') ? (<Actividad usuario={usuario}/>) : null}
            
        </div>
    )
}