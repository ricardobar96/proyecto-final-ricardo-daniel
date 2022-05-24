import "./modifyGenres.css";
import React, { useEffect, useRef, useState } from 'react';
import axios from 'axios';
import { Link, Navigate, useNavigate, useParams } from 'react-router-dom';
import { usuarios } from "../modelo/usuarios";
import Topbar from "../topbar/topbar";
import { videojuegos } from '../modelo/videojuegos';
import { generos } from '../modelo/generos';

interface IState { videojuegos?: videojuegos, generos?: generos[]; }

export default function ModifyGenres() {
    let navigate = useNavigate();
    const nombreGenre = useRef<HTMLInputElement>(null);
    const idGenre = useRef<HTMLInputElement>(null);
    const { id } = useParams();

    const token = localStorage.getItem("token") as string;
    const headers = {
        headers: { Authorization: token }
    };

    const [generos, setGenero] = useState<IState>();

    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;

    useEffect(() => {
        const getGenero = async () => {
            const rutageneros: string = rutaBase + "/api/v0/genero";
            let ruta = rutageneros;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setGenero({ generos: respuesta.data });
        }
        getGenero();
    },
        []
    )

    function createGenre() {
        let nombre = nombreGenre.current?.value;

        let newGenre = {
            nombre: nombre
        }

        let ruta = "http://localhost:8080/api/v2/genero";
        const axiospost = async (rutaGame: string) => {
            try {
                const { data } = await axios.post(rutaGame, newGenre, headers)
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }
        axiospost(ruta).then(respuesta => {
            navigate(0)
        });

    }

    function deleteGenre() {
        let id = idGenre.current?.value;

        let ruta = "http://localhost:8080/api/v2/genero";
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
                <h3 className="titleCreate">Lista de géneros:</h3>
                <br />
                <ul className='genresList'>
                    {
                        generos?.generos?.map((g: generos) => {
                            return (
                                <div>
                                    <li>
                                        <span>{g.id}. {g.nombre}</span>
                                    </li>
                                </div>
                            );
                        })
                    }
                </ul>

                <div className="containerButtonsGenres">
                    <form className="formCreateGame">
                        <input placeholder="Nombre" className="inputCreate" ref={nombreGenre} required /> <br />
                        <button type="button" className="buttonCreate" onClick={createGenre}>Crear</button>
                    </form>

                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                    <form className="formCreateGame">
                        <input placeholder="Id del género" className="inputCreate" ref={idGenre} required /> <br />
                        <button type="button" className="buttonDelete" onClick={deleteGenre}>Eliminar</button>
                    </form>
                </div>
            </div>
        </div>
    )
}