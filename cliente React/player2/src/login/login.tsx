import "./login.css";
import React, { useRef } from 'react';
import axios from 'axios';
import { Navigate, useNavigate } from 'react-router-dom';
import toast, { Toaster } from "react-hot-toast";

export default function Login() {
    const nameUser = useRef<HTMLInputElement>(null);
    const passwordUser = useRef<HTMLInputElement>(null);
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
                localStorage.clear();
                localStorage.setItem("token", data);
                localStorage.setItem("user", nombre)
                navigate("/home");
            } catch (error) {
                toast.error("No existe usuario con esos datos");
                console.log(error);
            }
        }
        axiospost("http://localhost:8080/api/login");
    }

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
                            <input type="text" ref={nameUser} placeholder="Nombre" className="loginInput" required/>
                            <input type="password" ref={passwordUser} placeholder="Contraseña" className="loginInput" required/>
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