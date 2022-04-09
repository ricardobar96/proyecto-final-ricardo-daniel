import "./socialProfile.css";
import React, { useEffect, useRef, useState } from 'react';
import axios from 'axios';
import { Link, Navigate, useNavigate } from 'react-router-dom';
import { usuarios } from "../modelo/usuarios";
import Topbar from "../topbar/topbar";
import TopbarProfile from "./topbarProfile";

interface IState { usuario?: usuarios[]; }

export default function SocialProfile() {
    let navigate = useNavigate();
    const [stUser, setStUser] = useState<IState>({});
    const [checkedF, setCheckedF] = React.useState(0);

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

    return (
        <div style={{
            backgroundColor: usuarioActual.color != "Azul" ? usuarioActual.color : 'lightsteelblue',
            height: "100vh"
        }}>
            <Topbar />
            <TopbarProfile />
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
                <ul className='socialProfileList'>
                    {
                        stUser?.usuario?.map((u: usuarios) => {
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

                            if (checkedF == 1)
                                return (
                                    <p>FOLLOWING</p>
                                );
                        })
                    }
                </ul>
            </div>
        </div>
    )
}