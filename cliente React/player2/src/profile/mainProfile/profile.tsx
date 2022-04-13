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


    usuarioActual.followeds.map((f: usuarios) => {
        if (f.id === stUser.usuario?.id) {
            following = true;
        }
    })

    const [checkedFollowing, setCheckedFollowing] = React.useState(false);

    function handleChangeFollowing() {
        if (following == false) {

            const newFollow = {
                "idusuariofollower": usuarioActual.id,
                "idusuariofollowed": stUser.usuario?.id
            }

            let ruta = "http://localhost:8080/api/v1/usuario/";
            const axiospost = async (rutaDeUsuario: string) => {
                try {
                    const { data } = await axios.post(rutaDeUsuario + usuarioActual.id + "/follow/" + stUser.usuario?.id,
                        newFollow, headers)
                    console.log(data);
                } catch (error) {
                    console.log(error);
                }
            }

            const usuarioFollowed = new usuarios(stUser.usuario!.id, stUser.usuario!.nombre, stUser.usuario!.password, stUser.usuario!.rol,
                stUser.usuario!.avatar, 1, stUser.usuario!.color, stUser.usuario!.banner, stUser.usuario!.descripcion, 
                stUser.usuario!.followeds, stUser.usuario!.followers, stUser.usuario!.juegosUsuario, stUser.usuario!.reviews,
                stUser.usuario!.pistas);
            let followeds: usuarios[] = usuarioActual.followeds;
            followeds.push(usuarioFollowed);

            const newUsuario = new usuarios(usuarioActual.id, usuarioActual.nombre, usuarioActual.password, usuarioActual.rol,
                usuarioActual.avatar, usuarioActual.activo, usuarioActual.color, usuarioActual.banner, usuarioActual.descripcion,
                followeds, usuarioActual.followers, stUser.usuario!.juegosUsuario, stUser.usuario!.reviews,
                stUser.usuario!.pistas);

            axiospost(ruta).then(respuesta => {
                navigate(0)
                localStorage.setItem("usuarioActual", JSON.stringify(newUsuario));
            });

            //following = true;
        }
        if (following == true) {

            let ruta = "http://localhost:8080/api/v1/usuario/";
            const axiosdelete = async (rutaDeUsuario: string) => {
                try {
                    const { data } = await axios.delete(rutaDeUsuario + usuarioActual.id + "/follow/" + stUser.usuario?.id,
                        headers)
                    console.log(data);
                } catch (error) {
                    console.log(error);
                }
            }
                
            let followeds: usuarios[] = usuarioActual.followeds;

            let indiceF:number = 0;
            indiceF = followeds.findIndex(usuarios => usuarios.id === stUser.usuario!.id);

            followeds.splice(indiceF, 1);

            const newUsuario = new usuarios(usuarioActual.id, usuarioActual.nombre, usuarioActual.password, usuarioActual.rol,
                usuarioActual.avatar, usuarioActual.activo, usuarioActual.color, usuarioActual.banner, usuarioActual.descripcion,
                followeds, usuarioActual.followers, stUser.usuario!.juegosUsuario, stUser.usuario!.reviews,
                stUser.usuario!.pistas);

            axiosdelete(ruta).then(respuesta => {
                navigate(0)
                localStorage.setItem("usuarioActual", JSON.stringify(newUsuario));
            });
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
            height: "100%"
        }}>
            <Topbar />
            <TopbarProfile />
            <div className="profileMain">
                <div className="followWrapper">
                    <br />
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
                <ProfileRight />
            </div>
        </div>
    )
}