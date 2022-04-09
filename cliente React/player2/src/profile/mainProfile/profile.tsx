import "./profile.css";
import React, { useState, useEffect, useRef } from 'react';
import { Link, Route, BrowserRouter, Routes, useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import Topbar from '../../topbar/topbar';
import { Checkbox } from "@material-ui/core";
import TopbarProfile from '../topbarProfile';
import { usuarios } from "../../modelo/usuarios";
import { videojuegos } from "../../modelo/videojuegos";
import ProfileLeft from './profileLeft';
import ProfileRight from './profileRight';

interface IState { videojuego?: videojuegos, usuario?: usuarios; }

export default function Profile() {
    let navigate = useNavigate();
    const [stGame, setStGame] = useState<IState>({});
    const [stUser, setStUser] = useState<IState>({});
    const { id } = useParams();

    const token = localStorage.getItem("token") as string;
    const headers = {
        headers: { Authorization: token }
    };

    var usuarioActual: usuarios = JSON.parse(localStorage.getItem('usuarioActual') || '{}');
    let initialCheckFollowing: any = 0;
    let following = false;

    /* check para comprobar si usuario esta en lista de following
    stUserGames.juegosUsuario?.map((j: juegosUsuario) => {
        if (j.videojuego.id === stGame.videojuego?.id) {
            if (j.usuario.id === usuarioActual.id) {
                juegoUsuarioActual = j;
                initialCheckComplete = 1;
                progress = true;
                if (j.completado == 1) {
                    completado = true;
                }
            }
        }
    });
    */

    const [checkedFollowing, setCheckedFollowing] = React.useState(false);

    function handleChangeFollowing() {
        if (following == false) {
            //CREAR NUEVO FOLLOWER POST const newJuegoUsuario = new juegosUsuario(juegoUsuarioActual.id, 1, juegoUsuarioActual.horas, juegoUsuarioActual.usuario, juegoUsuarioActual.videojuego, juegoUsuarioActual.puntuacion);
            let ruta = "http://localhost:8080/api/v1/juegousuario";
            const axiosput = async (rutaDeJuegoUsuario: string) => {
                try {
                    //const { data } = await axios.put(rutaDeJuegoUsuario + "/" + juegoUsuarioActual.id, newJuegoUsuario, headers)
                    //console.log(data);
                } catch (error) {
                    console.log(error);
                }
            }
            axiosput(ruta).then(respuesta => {
                navigate(0)
            });

            following = true;
        }
        if (following == true) {
            //const newJuegoUsuario = new juegosUsuario(juegoUsuarioActual.id, 0, juegoUsuarioActual.horas, juegoUsuarioActual.usuario, juegoUsuarioActual.videojuego, juegoUsuarioActual.puntuacion);
            let ruta = "http://localhost:8080/api/v1/juegousuario";
            const axiosput = async (rutaDeJuegoUsuario: string) => {
                try {
                    //const { data } = await axios.put(rutaDeJuegoUsuario + "/" + juegoUsuarioActual.id, newJuegoUsuario, headers)
                    //console.log(data);
                } catch (error) {
                    console.log(error);
                }
            }
            axiosput(ruta).then(respuesta => {
                navigate(0)
            });

            following = false;
        }
        setCheckedFollowing(!checkedFollowing);
    }

    /*
    function checkUserHasGame() {
        stUserGames.juegosUsuario?.map((j: juegosUsuario) => {
            if (j.videojuego.id === stGame.videojuego?.id) {
                if (j.usuario.id === usuarioActual.id) {
                    initialCheckComplete = 1;
                }
            }
            console.log("EL VALOR ES " + initialCheckComplete);
        });
    }
    */

    useEffect(() => {
        const getGame = async (id: string | undefined) => {
            let rutaDeJuego = "http://localhost:8080/api/v0/videojuego/";
            let { data } = await axios.get(rutaDeJuego + id);
            let videojuego: videojuegos = data;
            console.log(videojuego);
            setStGame({ videojuego });
        }
        const getUser = async (id: string | undefined) => {
            let rutadeUsuarios = "http://localhost:8080/api/v0/usuario/";
            let { data } = await axios.get(rutadeUsuarios + id);
            let usuario: usuarios = data;
            console.log(usuario);
            setStUser({ usuario });
        }
        getUser(id);
        getGame(id);
    },
        [id]
    )
    return (
        <div style={{
            backgroundColor: usuarioActual.color != "Azul" ? usuarioActual.color : 'lightsteelblue',
          }}>
            <Topbar />
            <TopbarProfile />
            <div className="profileMain">
                <div className="followWrapper">
                    <br/>
                    {usuarioActual.id != stUser.usuario?.id ?
                        <span style={{ color: "orangered", fontWeight: "bolder" }}>Follow {stUser.usuario?.nombre}</span>
                        : null
                    }

                    {usuarioActual.id != stUser.usuario?.id ?
                        <Checkbox
                            value={checkedFollowing}
                            checked={following}
                            onClick={handleChangeFollowing}
                            style={{ marginLeft: "5px" }}
                        />
                        : null
                    }
                </div>
            </div>
            <div className="profileContainer">
                <ProfileLeft />
                <ProfileRight/>
            </div>
        </div>
    )
}