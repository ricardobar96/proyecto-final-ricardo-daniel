import "./socialProfile.css";
import React, { useEffect, useRef, useState } from 'react';
import axios from 'axios';
import { Link, Navigate, useNavigate } from 'react-router-dom';
import { Search } from "@material-ui/icons";
import { usuarios } from '../modelo/usuarios';
import Topbar from "../topbar/topbar";
import TopbarProfile from "./topbarProfile";
import toast, { Toaster } from "react-hot-toast";

interface IState { usuario?: usuarios[]; }

export default function SocialProfile() {
    let navigate = useNavigate();
    const [stUser, setStUser] = useState<IState>({});
    const [checkedF, setCheckedF] = React.useState(0);

    const userBuscar = useRef<HTMLInputElement>(null);
    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;

    var usuarioActual: usuarios = JSON.parse(localStorage.getItem('usuarioActual') || '{}');
    let actualCheck = 0;
    let checkShowFollow = 0;

    useEffect(() => {
        checkShowFollow = actualCheck;

        const getUser = async () => {
            const rutaGames: string = rutaBase + "/api/v0/usuario";
            let ruta = rutaGames;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setStUser({ usuario: respuesta.data });
        }
        getUser();
    },
        []
    )

    function showFollowers() {
        setCheckedF(0);
        //actualCheck = 0;
    }

    function showFollowing() {
        setCheckedF(1);
        actualCheck = 1;
    }

    function searchUser() {
        let buscar = userBuscar.current?.value;
        let idBuscar = 0;

        stUser.usuario?.map((u: usuarios) =>{
            if (u.nombre.toUpperCase() == buscar?.toUpperCase()) {
                idBuscar = u.id;
            }
        })

        if (idBuscar > 0) {
            navigate("/api/v0/usuario/" + idBuscar);
        }
        else {
            toast.error("No se ha podido encontrar el usuario");
        }
    }

    const handleKeypress = (event: React.KeyboardEvent<HTMLInputElement>) => {
        if (event.keyCode === 13) {
            searchUser();
        }
    }

    return (
        <div style={{
            backgroundColor: usuarioActual.color != "Azul" ? usuarioActual.color : 'lightsteelblue',
            height: "100vh",
            paddingBottom: "10px"
        }}>
            <Topbar/>
            <TopbarProfile />
            <h3 className="titleSocial">Seguidores: {usuarioActual.followers.length}</h3>
            <h3 className="titleSocial">Siguiendo: {usuarioActual.followeds.length}</h3>

            <div className="searchbarSocial">
                <Toaster position="top-center" gutter={56} />
                <Search className="searchIcon" onClick={searchUser} />
                <input placeholder="Busca un usuario" className="searchInput" ref={userBuscar} onKeyDown={(e) => handleKeypress(e)} />
            </div>

            <div className="buttonsSocialProfile">
                <button type="button" className="buttonForm" onClick={showFollowers}>Followers</button>
                &nbsp;
                &nbsp;
                &nbsp;
                &nbsp;
                &nbsp;
                &nbsp;
                <button type="button" className="buttonForm" onClick={showFollowing}>Following</button>
            </div>

            <div className='socialProfileWrapper'>
                <ul className='nuevosList'>
                    {
                        usuarioActual.followers.map((u: usuarios) => {
                            if (checkedF == 0)
                                return (
                                    <div className="socialProfileBox">
                                        <Link to={{ pathname: "/api/v0/usuario/" + u.id }}>
                                            <li className="itemSocialProfile">
                                                <span><img src={u.avatar} className='imageGameProfile' /></span>
                                                <div className='titleProfileBox'>
                                                    <h5 className='titleGameProfile'>{u.nombre}</h5>
                                                </div>
                                            </li>
                                        </Link>
                                    </div>
                                );
                            /*        
                            if (checkedF == 1)
                                return (
                                    <p>FOLLOWING</p>
                                );
                            */
                        })
                    }
                </ul>
            </div>

            <div className='socialProfileWrapper'>
                <ul className='nuevosList'>
                    {
                        usuarioActual.followeds.map((u: usuarios) => {
                            if (checkedF == 1)
                                return (
                                    <div className="socialProfileBox">
                                        <Link to={{ pathname: "/api/v0/usuario/" + u.id }}>
                                            <li className="itemSocialProfile">
                                                <span><img src={u.avatar} className='imageGameProfile' /></span>
                                                <div className='titleProfileBox'>
                                                    <h5 className='titleGameProfile'>{u.nombre}</h5>
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