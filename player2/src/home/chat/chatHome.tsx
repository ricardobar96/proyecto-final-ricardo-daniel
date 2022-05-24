import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import io from 'socket.io-client';
import { usuarios } from '../../modelo/usuarios';
import Topbar from '../../topbar/topbar';
import './chatHome.css';

interface IState { usuario?: usuarios }

export default function ChatHome() {
    var usuarioActual: usuarios = JSON.parse(localStorage.getItem('usuarioActual') || '{}');

    const [stUser, setStUser] = useState<IState>({});

    useEffect(() => {

        const getUser = async (id: number) => {
            let rutadeUsuarios = "http://localhost:8080/api/v0/usuario/";
            let { data } = await axios.get(rutadeUsuarios + id);
            let usuario: usuarios = data;
            console.log(usuario);
            setStUser({ usuario });
        }
        getUser(usuarioActual.id);
    },
        []
    );

    return (
        <>
        <Topbar />
        <div>
        {stUser.usuario?.followeds?.map((u: usuarios) => {
            console.log(u);
            console.log(stUser.usuario?.followers);
            if (stUser.usuario?.followers.findIndex(usuario => usuario.nombre === u.nombre) !== -1) {
                return (
                    <Link to={{ pathname: "" + u.id }} style={{ textDecoration: "none" }}>
                        <span className="usuarioLink"><span className='avatar'><img src={u.avatar}/></span>{u.nombre}</span>
                    </Link>
                )
            }
    })}
    </div>
            
        </>
    )
}