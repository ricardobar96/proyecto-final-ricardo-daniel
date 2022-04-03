import "./login.css";
import React, { useRef, useState, useEffect } from 'react';
import axios from 'axios';
import { Navigate, useNavigate } from 'react-router-dom';
import toast, { Toaster } from "react-hot-toast";
import { usuarios } from "../modelo/usuarios";

interface IState { usuario: usuarios[]; }

export default function Login() {
    const nameUser = useRef<HTMLInputElement>(null);
    const passwordUser = useRef<HTMLInputElement>(null);
    const [usuario, setUsuario] = useState<IState>();
    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;
    let navigate = useNavigate();

    const login = (event: React.FormEvent<HTMLFormElement>) => {

        event.preventDefault();
        let formulario: HTMLFormElement = event.currentTarget;

        let nameI = nameUser.current?.value;
        let passwordI = passwordUser.current?.value;

        let login = {
            name: nameI,
            password: passwordI
        }
        const axiospost = async (rutaDeLogin: string) => {
            try {
                const { data } = await axios.post(rutaDeLogin, login)
                const nombre = nameI!;

                let usuarioActual: usuarios | any;
                usuario?.usuario?.map((u: usuarios) => {
                    if (u.nombre == nombre) {
                        usuarioActual = u;
                    }
                });

                localStorage.clear();
                localStorage.setItem("token", data);
                localStorage.setItem("user", nombre);
                localStorage.setItem("usuarioActual", JSON.stringify(usuarioActual));
                navigate("/home");
            } catch (error) {
                toast.error("No existe usuario con esos datos");
                console.log(error);
            }
        }
        axiospost("http://localhost:8080/api/login");
    }

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
    }, []);

    const register = (event: React.FormEvent<HTMLFormElement>) => {
        navigate("/register");
    }

    return (
        <div className="login">
            <div className="loginWrapper">
                <div className="loginLeft">
                    <h3 className="loginLogo">Player2</h3>
                    <span className="loginInfo">Tu red social de videojuegos</span>
                </div>
                <div className="loginRight">
                    <form onSubmit={login}>
                        <div className="loginBox">
                            <input type="text" ref={nameUser} placeholder="Nombre" className="loginInput" required />
                            <input type="password" ref={passwordUser} placeholder="Contraseña" className="loginInput" required />
                            <Toaster position="top-center" gutter={56} />
                            <button type="submit" className="loginButton">Iniciar sesión</button>
                        </div>
                    </form>

                    <form onSubmit={register}>
                        <div className="registerBox">
                            <button type="submit" className="registerButton">Registrarse</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    )
}