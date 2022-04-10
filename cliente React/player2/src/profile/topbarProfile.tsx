import "./topbarProfile.css";
import { Search, Person, Menu, Chat, ExitToApp, Settings } from "@material-ui/icons";
import React, { useState, useEffect, useRef } from 'react'
import axios from 'axios';
import { Link, Navigate, useNavigate, useParams } from 'react-router-dom';
import 'react-dropdown/style.css';
import { usuarios } from "../modelo/usuarios";

interface IState { usuario?: usuarios;}

export default function TopbarProfile() {
    var usuarioActual: usuarios = JSON.parse(localStorage.getItem('usuarioActual') || '{}');
    var idUser = usuarioActual.id;

    const { id } = useParams();
    const [stUser, setStUser] = useState<IState>({});

    useEffect(() => {
        const getUser = async (id: string | undefined) => {
            let rutadeUsuarios = "http://localhost:8080/api/v0/usuario/";
            let { data } = await axios.get(rutadeUsuarios + id);
            let usuario: usuarios = data;
            console.log(usuario);
            setStUser({ usuario });
        }
        getUser(id);
    },
        []
    )

    return (
        <><img className="topbarProfileBanner" src={stUser.usuario?.banner} /><div className="topbarProfileContainer">
            <div className="topbarCenter">
                <div className="topbarLinks">
                    <Link to={{ pathname: "/api/v0/usuario/" + idUser }} style={{ textDecoration: "none" }}>
                        <span className="topbarProfileLink">Principal</span>
                    </Link>
                    <Link to={{ pathname: "/api/v1/usuario/" + idUser + "/gamesProfile" }} style={{ textDecoration: "none" }}>
                        <span className="topbarProfileLink">Videojuegos</span>
                    </Link>
                    <Link to={{ pathname: "/api/v1/usuario/" + idUser + "/socialProfile" }} style={{ textDecoration: "none" }}>
                        <span className="topbarProfileLink">Social</span>
                    </Link>
                    <Link to={{ pathname: "/api/v1/usuario/" + idUser + "/reviewsProfile" }} style={{ textDecoration: "none" }}>
                        <span className="topbarProfileLink">Reviews</span>
                    </Link>
                    <Link to={{ pathname: "/api/v1/usuario/" + idUser + "/settings" }} style={{ textDecoration: "none" }}>
                        <span className="topbarProfileLink">Ajustes</span>
                    </Link>
                </div>
            </div>
        </div></>

    )
}