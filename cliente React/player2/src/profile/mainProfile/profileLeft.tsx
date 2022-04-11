import "./profileLeft.css";
import React, { useEffect, useRef, useState } from 'react';
import axios from 'axios';
import { Link, Navigate, useNavigate, useParams } from 'react-router-dom';
import { usuarios } from "../../modelo/usuarios";
import { videojuegos } from '../../modelo/videojuegos';
import { juegosUsuario } from '../../modelo/juegosUsuario';
import { generos } from '../../modelo/generos';

interface IState { videojuego?: videojuegos[], juegosUsuario?: juegosUsuario[], usuario?: usuarios; generos?: generos[]; }

export default function ProfileLeft() {
    let navigate = useNavigate();
    let contadorGamesUser = 0;
    let numeroGames = 0;

    const { id } = useParams();

    const [stGame, setStGame] = useState<IState>({});
    const [juegosUsuario, setJuegosUsuario] = useState<IState>();
    const [stGeneros, setStGenero] = useState<IState>();
    const [stUser, setStUser] = useState<IState>({});

    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;

    let descripcion;

    if(stUser.usuario?.descripcion == null){
        descripcion = "El usuario no ha escrito nada"
    }
    else{
        descripcion = stUser.usuario.descripcion;
    }

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

    let contadores: any = [stGeneros?.generos?.length];

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

    let popularGames: juegosUsuario[] = [];

    juegosUsuario?.juegosUsuario?.map((j: juegosUsuario) => {
        if (j.usuario.id === stUser.usuario?.id) {
            if (j.puntuacion >= 7) {
                popularGames.push(j);
            }
        }
    });

    popularGames.sort((a, b) => (a.puntuacion) - (b.puntuacion));
    
    popularGames.reverse();

    useEffect(() => {
        const getGame = async () => {
            const rutaGames: string = rutaBase + "/api/v0/videojuego";
            let ruta = rutaGames;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setStGame({ videojuego: respuesta.data });
        }
        const getJuegosUsuario = async () => {
            const rutaJuegosUsuario: string = rutaBase + "/api/v0/juegousuario";
            let ruta = rutaJuegosUsuario;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setJuegosUsuario({ juegosUsuario: respuesta.data });
        }
        const getGenero = async () => {
            const rutaGeneros: string = rutaBase + "/api/v0/genero";
            let ruta = rutaGeneros;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setStGenero({ generos: respuesta.data });
        }
        const getUser = async (id: string | undefined) => {
            let rutadeUsuarios = "http://localhost:8080/api/v0/usuario/";
            let { data } = await axios.get(rutadeUsuarios + id);
            let usuario: usuarios = data;
            console.log(usuario);
            setStUser({ usuario });
        }
        getUser(id);
        getGenero();
        getGame();
        getJuegosUsuario();
    },
        []
    )
    return (
        <div className="profileLeft">
            <div className="pistasItem">
                <span className="descriptionMainLeft">{stUser.usuario?.nombre}</span>
                <div className="descriptionBox">
                    <img src={stUser.usuario?.avatar} className="avatarInfo" />
                    <div className="contentDescription">
                        <h3>Sobre mí</h3>
                        <p>{descripcion}</p>
                    </div>
                </div>
            </div>

            <br />
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
            <br />

            <h3 className="title">Videojuegos mejor puntuados:</h3>
            <div className='tendenciasWrapper'>
                <ul className='topGamesList'>
                    {
                        popularGames.slice(0, 3).map((t: juegosUsuario) => {
                            return (
                                <div className='topGamesBox'>
                                    <Link to={{ pathname: "/api/v0/videojuego/" + t.videojuego.id }}>
                                        <li>
                                            <span><img src={t.videojuego.imagen} className='imageGameHome' /></span>
                                            <div className='titleHomeBox'>
                                                <h5 className='titleGameHome'>{t.videojuego.nombre}</h5>
                                            </div>
                                        </li>
                                    </Link>
                                </div>
                            );
                        })
                    }
                </ul>
            </div>
        </div>
    )
}