import "./register.css";
import React, { useRef } from 'react';
import axios from 'axios';
import { Navigate, useNavigate } from 'react-router-dom';

export default function Register() {
    let navigate = useNavigate();
    const nameUser = useRef<HTMLInputElement>(null);
    const passwordUser = useRef<HTMLInputElement>(null);

    const register = (event: React.FormEvent<HTMLFormElement>) => {

        event.preventDefault();
        let formulario: HTMLFormElement = event.currentTarget;

        let nameI = nameUser.current?.value;
        let passwordI = passwordUser.current?.value;

        let register = {
            name: nameI,
            password: passwordI
        }
        const axiospost = async (rutaDeRegister: string) => {
            try {
                const { data } = await axios.post(rutaDeRegister, register)
                localStorage.clear();
                localStorage.setItem("token", data);
            } catch (error) {
                console.log(error);
            }
        }
        alert("Creada nueva cuenta");
        axiospost("http://localhost:8080/api/login");
    }

    const returnLogin = (event: React.FormEvent<HTMLFormElement>) => {
        navigate("/");
    }

    return (
        <div className="register">
            <div className="registerWrapper">
                <div className="registerLeft">
                    <h3 className="registerLogo">Player2</h3>
                    <span className="registerInfo">Tu red social de videojuegos</span>
                </div>
                <div className="registerRight">
                    <form onSubmit={register}>
                        <div className="registerAccountBox">
                            <input type="text" ref={nameUser} placeholder="Nombre" className="registerInput" />
                            <input type="text" ref={passwordUser} placeholder="Contraseña" className="registerInput" />
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