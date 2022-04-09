import "./profileLeft.css";
import React, { useEffect, useRef, useState } from 'react';
import axios from 'axios';
import { Link, Navigate, useNavigate } from 'react-router-dom';
import { reviews } from "../../modelo/reviews";
import { usuarios } from "../../modelo/usuarios";
import { videojuegos } from '../../modelo/videojuegos';
import Topbar from "../../topbar/topbar";
import TopbarProfile from "../topbarProfile";
import { juegosUsuario } from '../../modelo/juegosUsuario';
import { generos } from '../../modelo/generos';

interface IState { videojuego?: videojuegos[], juegosUsuario?: juegosUsuario[], usuario?: usuarios; generos?: generos[];}

export default function ProfileLeft() {
    let navigate = useNavigate();
    let contadorGamesUser = 0;
    let numeroGames = 0;

    const [stGame, setStGame] = useState<IState>({});
    const [stUserGames, setStUserGames] = useState<IState>({});
    const [stGeneros, setStGenero] = useState<IState>();

    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;

    var usuarioActual: usuarios = JSON.parse(localStorage.getItem('usuarioActual') || '{}');

    let contadorAccion = 0;
    let contadorAventura = 0;
    let contadorCarreras = 0;
    let contadorEstrategia = 0;
    let contadorSimulacion = 0;
    let contadorDeporte = 0;
    let contadorRol = 0;
    let contadorPuzzle = 0;
    let contadorPlataforma = 0;
    let contadorShooter = 0;
    let contadorLucha = 0;

    let contadores:any = [stGeneros?.generos?.length];

    //for(let i=0; i<contadores.length; i++){
      //  contadores.push(0);
   // }

    //let generosPosibles = [stGeneros?.generos?.length];
/*
    stGeneros?.generos?.map((g: generos) =>{
        generosPosibles.push(g.nombre);
    })

    stUserGames.juegosUsuario?.map((j: juegosUsuario) => {
        if (j.usuario.id === usuarioActual.id) {
                j.videojuego.generos.map((g: generos) =>{
                    for(let i=0; i<=generos.length; i++){
                        if(g.nombre == generosPosibles[i]){
                            contadores[i]++;
                        }
                    }

                })
            }
        }
    );
/*
    console.log("Accion:" + contadores[1]);
    console.log("Aventura: "+ contadores[2]);
    console.log("Shooter: " + contadores[11]);
*/
    useEffect(() => {
        const getGame = async () => {
            const rutaGames: string = rutaBase + "/api/v0/videojuego";
            let ruta = rutaGames;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setStGame({ videojuego: respuesta.data });
        }
        const getUserGames = async () => {
            const rutaUserGames: string = rutaBase + "/api/v0/juegosusuario";
            let ruta = rutaUserGames;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setStUserGames({ juegosUsuario: respuesta.data });
        }
        const getGenero = async () => {
            const rutaGeneros: string = rutaBase + "/api/v0/genero";
            let ruta = rutaGeneros;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setStGenero({ generos: respuesta.data });
        }
        getGenero();
        getGame();
        getUserGames();
    },
        []
    )
    return (
        <div className="profileLeft">
            <h3 className="title">Géneros favoritos:</h3>
            <ul className='reviewsProfileList'>
                    {stGame.videojuego?.map((v: videojuegos) => {
                        numeroGames += 1
                        /*
                        if (r.usuario.id == usuarioActual.id) {
                            contadorGamesUser += 1
                            return (
                                <li className="reviewProfileItem">
                                    <Link to={{ pathname: "/api/v0/videojuego/" + r.videojuego.id }} style={{ textDecoration: "none" }}>
                                        <span className="reviewProfileImage"><img src={r.videojuego.imagen} className="reviewProfileGameImage" /></span>
                                    </Link>
                                    <div className="reviewProfileContent">
                                        <Link to={{ pathname: "/api/v0/videojuego/" + r.videojuego.id }} style={{ textDecoration: "none" }}>
                                            <h3 className="reviewProfileGame">{r.videojuego.nombre}</h3>
                                            <h2 className="reviewProfileTitle">{r.titulo}</h2>
                                        </Link>

                                    </div>
                                </li>
                            );
                        }
                        */
                    })}
                </ul>
        </div>
    )
}