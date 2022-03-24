import "./register.css";
import React, { useRef } from 'react';
import axios from 'axios';
import { Navigate, useNavigate } from 'react-router-dom';

declare module player2 {

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

export default function Register() {
    let navigate = useNavigate();
    const nameUser = useRef<HTMLInputElement>(null);
    const passwordUser = useRef<HTMLInputElement>(null);

    const returnLogin = (event: React.FormEvent<HTMLFormElement>) => {
        navigate("/");
    }

    const registerUser = (event: React.FormEvent<HTMLFormElement>) => {

        event.preventDefault();
        let formulario: HTMLFormElement = event.currentTarget;

        let name = nameUser.current?.value;
        let password = passwordUser.current?.value;

        const newUser = {
            "nombre": name,
            "password": password,
            "rol":"ROLE_USER",
            "avatar":"",
            "banner":"",
            "descripcion":"",
            "color":"",
            "activo":1
        }

        let ruta = "http://localhost:8080//api/v1/usuario";
        const axiospost = async (rutaDeUsuario: string) => {
            try {
                const { data } = await axios.post(rutaDeUsuario, newUser)
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }
        axiospost(ruta).then(respuesta => {
            navigate("/")
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
                            <input type="text" ref={nameUser} placeholder="Nombre" className="registerInput" />
                            <input type="password" ref={passwordUser} placeholder="Contraseña" className="registerInput" />
                            <button type="submit" className="registerAccountButton">Crear cuenta</button>
                        </div>
                    </form>

                    <form onSubmit={returnLogin}>
                        <div className="returnBox">
                            <button type="submit" className="returnButton">Regresar a iniciar sesión</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    )
}