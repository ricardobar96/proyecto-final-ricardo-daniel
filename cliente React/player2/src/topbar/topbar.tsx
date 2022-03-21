import "./topbar.css";
import { Search, Person, Menu, Chat, ExitToApp } from "@material-ui/icons";
import React, { useState, useEffect, useRef } from 'react'
import axios from 'axios';
import { Link, Navigate, useNavigate } from 'react-router-dom';
import 'react-dropdown/style.css';
import toast, { Toaster } from "react-hot-toast";

interface IState { videojuegos?: Array<player2.videojuegos>; }

declare module player2 {

    export interface videojuegos {
        id: number;
        nombre: string;
        fecha: string;
        puntuacion: number;
        descripcion: string;
        imagen: string;
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

export default function Topbar() {
    let navigate = useNavigate();
    const juegoBuscar = useRef<HTMLInputElement>(null);
    const [videojuegos, setVideojuego] = useState<IState>();
    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;
    const rutajuegosHome: string = rutaBase + "/api/v1/videojuego";

    useEffect(() => {
        const getVideojuego = async () => {
            let ruta = rutajuegosHome;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setVideojuego({ videojuegos: respuesta.data });
        }
        getVideojuego();
    }, []);

    function Logout() {
        localStorage.clear();
        navigate("/");
    }

    function SearchGame() {
        let buscar = juegoBuscar.current?.value;
        let idBuscar = 0;
        videojuegos?.videojuegos?.map((a: player2.videojuegos) => {
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
                    <Link to="/register" style={{ textDecoration: "none" }}>
                        <span className="topbarLink">Perfil</span>
                    </Link>
                    <Link to="/games" style={{ textDecoration: "none" }}>
                        <span className="topbarLink">Videojuegos</span>
                    </Link>
                </div>
            </div>
            <div className="topbarRight">
                <div className="searchbar">
                    <Toaster position="top-right" gutter={56} />
                    <Search className="searchIcon" onClick={SearchGame} />
                    <input placeholder="Busca un videojuego" className="searchInput" ref={juegoBuscar} />
                </div>
                <div className="topbarIcons">
                    <div className="topbarIconsItem">
                        <Chat onClick={goChat} />
                    </div>
                    <div className="topbarIconsItem">
                        <Person />
                    </div>
                    <div className="topbarIconsItem">
                        <ExitToApp onClick={Logout} />
                    </div>

                </div>
            </div>
        </div>
    )
}