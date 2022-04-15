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

    const { id } = useParams();

    const [stGame, setStGame] = useState<IState>({});
    const [juegosUsuario, setJuegosUsuario] = useState<IState>();
    const [stGeneros, setStGenero] = useState<IState>();
    const [stUser, setStUser] = useState<IState>({});

    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;

    let descripcion;

    if (stUser.usuario?.descripcion == null) {
        descripcion = "El usuario no ha escrito nada"
    }
    else {
        descripcion = stUser.usuario.descripcion;
    }

    var usuarioActual: usuarios = JSON.parse(localStorage.getItem('usuarioActual') || '{}');

    let popularGenres: string[] = [];

    juegosUsuario?.juegosUsuario?.map((j: juegosUsuario) => {
        if (j.usuario.id === stUser.usuario?.id) {
            j.videojuego.generos.map((g: generos) => {
                popularGenres.push(g.nombre);
            })
        }
    });

    var map:any = [];
    var genre1 = popularGenres[0];

    for (var i = 0; i < popularGenres.length; i++) {
        if (!map[popularGenres[i]]) {
            map[popularGenres[i]] = 1;
        } else {
            ++map[popularGenres[i]];
            if (map[popularGenres[i]] > map[genre1]) {
                genre1 = popularGenres[i];
            }
        }
    }


    let contadorGenre1 = 0;

    popularGenres.map((g: string) =>{
        if(g === genre1){
            contadorGenre1 += 1;
            var index = popularGenres.indexOf(g);
            popularGenres.splice(index, 1);
        }
    })


    var map2:any = [];
    var genre2 = popularGenres[0];

    for (var i = 0; i < popularGenres.length; i++) {
        if (!map2[popularGenres[i]]) {
            map2[popularGenres[i]] = 1;
        } else {
            ++map2[popularGenres[i]];
            if (map2[popularGenres[i]] > map2[genre2]) {
                genre2 = popularGenres[i];
            }
        }
    }

    let contadorGenre2 = 0;

    popularGenres.map((g: string) =>{
        if(g === genre2){
            contadorGenre2 += 1;
            var index = popularGenres.indexOf(g);
            popularGenres.splice(index, 1);
        }
    })


    var map3:any = [];
    var genre3 = popularGenres[0];

    for (var i = 0; i < popularGenres.length; i++) {
        if (!map3[popularGenres[i]]) {
            map3[popularGenres[i]] = 1;
        } else {
            ++map3[popularGenres[i]];
            if (map3[popularGenres[i]] > map3[genre3]) {
                genre3 = popularGenres[i];
            }
        }
    }

    let contadorGenre3 = 0;

    popularGenres.map((g: string) =>{
        if(g === genre3){
            contadorGenre3 += 1;
        }
    })

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
            <div className="statsContent">
                <ul>
                    <li className="statsList">
                        <span className="valueStats">{contadorGenre1}</span>
                        <h4 className="title">{genre1}</h4>
                    </li>
                </ul>

                <ul>
                    <li className="statsList">
                    <span className="valueStats">{contadorGenre2}</span>
                        <h4 className="title">{genre2}</h4>
                    </li>
                </ul>

                <ul>
                    <li className="statsList">
                    <span className="valueStats">{contadorGenre3}</span>
                        <h4 className="title">{genre3}</h4>
                    </li>
                </ul>
            </div>
            <br />
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