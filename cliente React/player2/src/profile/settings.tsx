import "./settings.css";
import React, { useEffect, useRef, useState } from 'react';
import axios from 'axios';
import { Link, Navigate, useNavigate } from 'react-router-dom';
import { usuarios } from "../modelo/usuarios";
import Topbar from "../topbar/topbar";
import TopbarProfile from "./topbarProfile";
import { Check } from "@material-ui/icons";

interface IState { usuario?: usuarios; }

export default function Settings() {
    let navigate = useNavigate();
    const direccionAvatar = useRef<HTMLInputElement>(null);
    const direccionBanner = useRef<HTMLInputElement>(null);
    const descripcionUser = useRef<HTMLTextAreaElement>(null);

    const token = localStorage.getItem("token") as string;
    const headers = {
        headers: { Authorization: token }
    };

    const [stUser, setStUser] = useState<IState>({});

    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;

    var usuarioActual: usuarios = JSON.parse(localStorage.getItem('usuarioActual') || '{}');

    useEffect(() => {
        const getUser = async () => {
            const rutaUser: string = rutaBase + "/api/v0/usuario";
            let ruta = rutaUser;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setStUser({ usuario: respuesta.data });
        }
        getUser();
    },
        []
    )

    function changeAvatar() {
        let avatar = direccionAvatar.current?.value;
        
        const newUsuario = new usuarios(usuarioActual.id, usuarioActual.nombre, usuarioActual.password, usuarioActual.rol,
            avatar!, usuarioActual.activo, usuarioActual.color, usuarioActual.banner, usuarioActual.descripcion, 
            usuarioActual.followeds, usuarioActual.followers);
            
        let ruta = "http://localhost:8080/api/v1/usuario";
        const axiosput = async (rutaUsuario: string) => {
            try {
                const { data } = await axios.put(rutaUsuario + "/" + usuarioActual.id, newUsuario, headers)
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }
        axiosput(ruta).then(respuesta => {
            navigate(0)
            localStorage.setItem("usuarioActual", JSON.stringify(newUsuario));
        });
    }

    function changeBanner() {
        let banner = direccionBanner.current?.value;

        const newUsuario = new usuarios(usuarioActual.id, usuarioActual.nombre, usuarioActual.password, usuarioActual.rol,
            usuarioActual.avatar, usuarioActual.activo, usuarioActual.color, banner!, usuarioActual.descripcion, 
            usuarioActual.followeds, usuarioActual.followers);
            
        let ruta = "http://localhost:8080/api/v1/usuario";
        const axiosput = async (rutaUsuario: string) => {
            try {
                const { data } = await axios.put(rutaUsuario + "/" + usuarioActual.id, newUsuario, headers)
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }
        axiosput(ruta).then(respuesta => {
            navigate(0)
            localStorage.setItem("usuarioActual", JSON.stringify(newUsuario));
        });
    }

    function changeDescripcion() {
        let descripcion = descripcionUser.current?.value;

        const newUsuario = new usuarios(usuarioActual.id, usuarioActual.nombre, usuarioActual.password, usuarioActual.rol,
            usuarioActual.avatar, usuarioActual.activo, usuarioActual.color, usuarioActual.banner, descripcion!, 
            usuarioActual.followeds, usuarioActual.followers);

        let ruta = "http://localhost:8080/api/v1/usuario";
        const axiosput = async (rutaUsuario: string) => {
            try {
                const { data } = await axios.put(rutaUsuario + "/" + usuarioActual.id, newUsuario, headers)
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }
        axiosput(ruta).then(respuesta => {
            navigate(0)
            localStorage.setItem("usuarioActual", JSON.stringify(newUsuario));
        });
    }

    function colorRed() {
        const newUsuario = new usuarios(usuarioActual.id, usuarioActual.nombre, usuarioActual.password, usuarioActual.rol,
            usuarioActual.avatar, usuarioActual.activo, "LightSalmon", usuarioActual.banner, usuarioActual.descripcion, 
            usuarioActual.followeds, usuarioActual.followers);

        let ruta = "http://localhost:8080/api/v1/usuario";
        const axiosput = async (rutaUsuario: string) => {
            try {
                const { data } = await axios.put(rutaUsuario + "/" + usuarioActual.id, newUsuario, headers)
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }
        axiosput(ruta).then(respuesta => {
            navigate(0)
            localStorage.setItem("usuarioActual", JSON.stringify(newUsuario));
        });
    }

    function colorBlue() {
        const newUsuario = new usuarios(usuarioActual.id, usuarioActual.nombre, usuarioActual.password, usuarioActual.rol,
            usuarioActual.avatar, usuarioActual.activo, "lightsteelblue", usuarioActual.banner, usuarioActual.descripcion, 
            usuarioActual.followeds, usuarioActual.followers);

        let ruta = "http://localhost:8080/api/v1/usuario";
        const axiosput = async (rutaUsuario: string) => {
            try {
                const { data } = await axios.put(rutaUsuario + "/" + usuarioActual.id, newUsuario, headers)
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }
        axiosput(ruta).then(respuesta => {
            navigate(0)
            localStorage.setItem("usuarioActual", JSON.stringify(newUsuario));
        });
    }

    function colorGreen() {
        const newUsuario = new usuarios(usuarioActual.id, usuarioActual.nombre, usuarioActual.password, usuarioActual.rol,
            usuarioActual.avatar, usuarioActual.activo, "DarkSeaGreen", usuarioActual.banner, usuarioActual.descripcion, 
            usuarioActual.followeds, usuarioActual.followers);

        let ruta = "http://localhost:8080/api/v1/usuario";
        const axiosput = async (rutaUsuario: string) => {
            try {
                const { data } = await axios.put(rutaUsuario + "/" + usuarioActual.id, newUsuario, headers)
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }
        axiosput(ruta).then(respuesta => {
            navigate(0)
            localStorage.setItem("usuarioActual", JSON.stringify(newUsuario));
        });
    }

    return (
        <div style={{
            backgroundColor: usuarioActual.color != "Azul" ? usuarioActual.color : 'lightsteelblue',
          }}>
            <Topbar />
            <TopbarProfile />
            <div className="ajustes">
                <h3 className="title">Color del perfil:</h3>
                <br />
                <div className="listaColores">
                    <span className="rojo" onClick={colorRed}>&nbsp;Rojo&nbsp;</span>
                    <span className="azul" onClick={colorBlue}>&nbsp;Azul&nbsp;</span>
                    <span className="verde" onClick={colorGreen}>Verde</span>
                </div>
                <br />
                <br />
                <br />
                <h3 className="title">Avatar:</h3>
                <br />
                <div>
                    <form>
                        <input placeholder="Dirección imagen..." className="inputAvatar" ref={direccionAvatar} required />
                        <button type="button" className="buttonForm" onClick={changeAvatar}>Cambiar</button>
                    </form>
                </div>

                <br />

                <h3 className="title">Banner:</h3>
                <br />
                <div>
                    <form>
                        <input placeholder="Dirección imagen..." className="inputAvatar" ref={direccionBanner} required />
                        <button type="button" className="buttonForm" onClick={changeBanner}>Cambiar</button>
                    </form>
                </div>

                <br />

                <h3 className="title">Sobre mí:</h3>
                <br />
                <div>
                    <form>
                        <textarea placeholder="Hola, me llamo..." className="inputAvatar" ref={descripcionUser} cols={80} rows={10} required />
                        <br />
                        <button type="button" className="buttonForm" onClick={changeDescripcion}>Cambiar</button>
                    </form>
                </div>
            </div>
        </div>
    )
}