import "./topbar.css";
import { Search, Person, Menu, Chat, ExitToApp } from "@material-ui/icons";
import React, { useState, useEffect, useRef } from 'react'
import axios from 'axios';
import { Link, Navigate, useNavigate } from 'react-router-dom';
import 'react-dropdown/style.css';
import toast, { Toaster } from "react-hot-toast";
import { videojuegos } from "../modelo/videojuegos";
import { usuarios } from "../modelo/usuarios";

interface IState { videojuegos?: videojuegos[], usuario?: usuarios[]; }

export default function Topbar() {
    let navigate = useNavigate();
    const juegoBuscar = useRef<HTMLInputElement>(null);
    const [videojuegos, setVideojuego] = useState<IState>();
    const [usuario, setUsuario] = useState<IState>();
    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;

    var usuarioActual: usuarios = JSON.parse(localStorage.getItem('usuarioActual') || '{}');
    let avatar = usuarioActual.avatar;
    let nameUser = (localStorage.getItem('user') || '{}');
    let idUser = 0;
    usuario?.usuario?.map((u: usuarios) => {
        if (u.nombre == nameUser) {
            idUser = u.id;
        }
    });

    useEffect(() => {
        const getVideojuego = async () => {
            const rutajuegosHome: string = rutaBase + "/api/v1/videojuego";
            let ruta = rutajuegosHome;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setVideojuego({ videojuegos: respuesta.data });
        }
        const getUsuario = async () => {
            const rutaUsuario: string = rutaBase + "/api/v1/usuario";
            let ruta = rutaUsuario;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setUsuario({ usuario: respuesta.data });
        }
        getVideojuego();
        getUsuario();
    }, []);

    function Logout() {
        localStorage.clear();
        navigate("/");
    }

    function SearchGame() {
        let buscar = juegoBuscar.current?.value;
        let idBuscar = 0;
        videojuegos?.videojuegos?.map((a: videojuegos) => {
            if (a.nombre.toUpperCase() == buscar?.toUpperCase()) {
                idBuscar = a.id;
            }
        });

        if (idBuscar > 0) {
            navigate("/api/v1/videojuego/" + idBuscar);
        }
        else {
            toast.error("No se ha podido encontrar el juego");
        }
    }

    function goChat() {
        navigate("/chat");
    }

    function goProfile() {
        navigate("/api/v1/usuario/" + idUser);
    }

    const handleKeypress = (event: React.KeyboardEvent<HTMLInputElement>) => {
        if (event.keyCode === 13) {
            SearchGame();
        }
    }

    return (
        <div className="topbarContainer">
            <div className="topbarLeft">
                <span className="logo">Player2</span>
            </div>
            <div className="topbarCenter">
                <div className="topbarLinks">
                    <Link to="/home" style={{ textDecoration: "none" }}>
                        <span className="topbarLink">Home</span>
                    </Link>
                    <Link to={{ pathname: "/api/v1/usuario/" + idUser }} style={{ textDecoration: "none" }}>
                        <span className="topbarLink">Perfil</span>
                    </Link>
                    <Link to="/games" style={{ textDecoration: "none" }}>
                        <span className="topbarLink">Videojuegos</span>
                    </Link>
                </div>
            </div>
            <div className="topbarRight">
                <div className="searchbar">
                    <Toaster position="top-center" gutter={56} />
                    <Search className="searchIcon" onClick={SearchGame} />
                    <input placeholder="Busca un videojuego" className="searchInput" ref={juegoBuscar} onKeyDown={(e) => handleKeypress(e)} />
                </div>
                <div className="topbarIcons">
                    <div className="topbarIconsItem">
                        <Chat onClick={goChat} />
                    </div>
                    <div className="topbarIconsItem">
                        <img src={avatar} className="topbarAvatar" onClick={goProfile} />
                    </div>
                    <div className="topbarIconsItem">
                        <ExitToApp onClick={Logout} />
                    </div>

                </div>
            </div>
        </div>
    )
}