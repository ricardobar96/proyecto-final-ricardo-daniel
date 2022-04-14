import "./addAdmin.css";
import React, { useEffect, useRef, useState } from 'react';
import axios from 'axios';
import { Link, Navigate, useNavigate, useParams } from 'react-router-dom';
import { usuarios } from "../modelo/usuarios";
import Topbar from "../topbar/topbar";
import { videojuegos } from '../modelo/videojuegos';
import { generos } from '../modelo/generos';

interface IState { usuario: usuarios[]; }

export default function AddAdmin() {
    let navigate = useNavigate();
    const nombreGenre = useRef<HTMLInputElement>(null);
    const passwordGenre = useRef<HTMLInputElement>(null);
    const idAdmin = useRef<HTMLInputElement>(null);
    const { id } = useParams();

    const token = localStorage.getItem("token") as string;
    const headers = {
        headers: { Authorization: token }
    };

    const [usuario, setUsuario] = useState<IState>();

    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;

    useEffect(() => {
        const getUsuario = async () => {
            const rutaUsuario: string = rutaBase + "/api/v0/usuario";
            let ruta = rutaUsuario;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setUsuario({ usuario: respuesta.data });
        }
        getUsuario();
    },
        []
    )

    function createAdmin() {
        let nombre = nombreGenre.current?.value;
        let password = passwordGenre.current?.value;

        const newAdmin = new usuarios(1, nombre!, password!, "ROLE_ADMIN", "", 1, "", "", "", [], [], [], [], []);

        let ruta = "http://localhost:8080/api/v2/usuario";
        const axiospost = async (rutaUser: string) => {
            try {
                const { data } = await axios.post(rutaUser, newAdmin, headers)
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }
        axiospost(ruta).then(respuesta => {
            navigate(0)
        });

    }

    function deleteAdmin() {
        let id = idAdmin.current?.value;

        let ruta = "http://localhost:8080/api/v2/usuario";
        const axiosdelete = async (rutaGame: string) => {
            try {
                const { data } = await axios.delete(rutaGame + "/" + id, headers)
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }
        axiosdelete(ruta).then(respuesta => {
            navigate(0)
        });

    }

    return (
        <div>
            <Topbar />
            <div className="ajustes">
                <h3 className="titleCreate">Lista de administradores:</h3>
                <br />
                <ul className='genresList'>
                    {
                        usuario?.usuario?.map((u: usuarios) => {
                            if (u.rol === "ROLE_ADMIN")
                                return (
                                    <div>
                                        <li>
                                            <span>{u.id}. {u.nombre}</span>
                                        </li>
                                    </div>
                                );
                        })
                    }
                </ul>

                <form className="formCreateGame">
                    <input placeholder="Nombre" className="inputCreate" ref={nombreGenre} required /> <br />
                    <input type="password" placeholder="Password" className="inputCreate" ref={passwordGenre} required /> <br />
                    <button type="button" className="buttonCreate" onClick={createAdmin}>Crear</button>
                </form>

                <form className="formCreateGame">
                    <input placeholder="Id del admin" className="inputCreate" ref={idAdmin} required /> <br />
                    <button type="button" className="buttonDelete" onClick={deleteAdmin}>Eliminar</button>
                </form>
            </div>
        </div>
    )
}