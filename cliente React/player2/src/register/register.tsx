import "./register.css";
import React, { useRef } from 'react';
import axios from 'axios';
import { Navigate, useNavigate } from 'react-router-dom';
import toast, { Toaster } from "react-hot-toast";
import { usuarios } from "../modelo/usuarios";

export default function Register() {
    let navigate = useNavigate();
    const nameUser = useRef<HTMLInputElement>(null);
    const passwordUser = useRef<HTMLInputElement>(null);

    const returnLogin = (event: React.FormEvent<HTMLFormElement>) => {
        navigate("/login");
    }

    const registerUser = (event: React.FormEvent<HTMLFormElement>) => {

        event.preventDefault();
        let formulario: HTMLFormElement = event.currentTarget;

        let name = nameUser.current?.value;
        let password = passwordUser.current?.value;

        const newUser = new usuarios(1, name!, password!, "ROLE_USER", "", 1, "", "", "", [], []);

        let ruta = "http://localhost:8080/api/v0/usuario";
        const axiospost = async (rutaDeUsuario: string) => {
            try {
                const { data } = await axios.post(rutaDeUsuario, newUser)
                console.log(data);
            } catch (error) {
                console.log(name+ " " +password);
                toast.error("Ya existe un usuario con ese nombre");
                console.log(error);
            }
        }
        axiospost(ruta).then(respuesta => {
            navigate("/login")
        });
    }

    return (
        <div className="register">
            <div className="registerWrapper">
                <div className="registerLeft">
                    <h3 className="registerLogo">Player2</h3>
                    <span className="registerInfo">Tu red social de videojuegos</span>
                </div>
                <div className="registerRight">
                    <form onSubmit={registerUser}>
                        <div className="registerAccountBox">
                            <input type="text" ref={nameUser} placeholder="Nombre" className="registerInput" required/>
                            <input type="password" ref={passwordUser} placeholder="Contraseña" className="registerInput" required/>
                            <Toaster position="top-center" gutter={56} />
                            <button type="submit" className="registerAccountButton">Crear cuenta</button>
                        </div>
                    </form>

                    <form onSubmit={returnLogin}>
                        <div className="returnBox">
                        <Toaster position="top-center" gutter={56} />
                            <button type="submit" className="returnButton">Regresar a iniciar sesión</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    )
}