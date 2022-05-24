import "./modifyGame.css";
import React, { useEffect, useRef, useState } from 'react';
import axios from 'axios';
import { Link, Navigate, useNavigate, useParams } from 'react-router-dom';
import { usuarios } from "../modelo/usuarios";
import Topbar from "../topbar/topbar";
import { videojuegos } from '../modelo/videojuegos';
import { generos } from '../modelo/generos';

interface IState { videojuego?: videojuegos, generos?: generos[]; }

export default function ModifyGame() {
    let navigate = useNavigate();
    const fechaGame = useRef<HTMLInputElement>(null);
    const nombreGame = useRef<HTMLInputElement>(null);
    const imagenGame = useRef<HTMLInputElement>(null);
    const descripcionGame = useRef<HTMLTextAreaElement>(null);
    const { id } = useParams();

    const token = localStorage.getItem("token") as string;
    const headers = {
        headers: { Authorization: token }
    };

    const [stGame, setStGame] = useState<IState>({});
    const [generos, setGenero] = useState<IState>();

    const [checked, setChecked] = useState<string[]>([]);

    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;

    let checkGenreExistent = false

    useEffect(() => {
        const getGame = async (id: string | undefined) => {
            let rutaDeJuego = "http://localhost:8080/api/v0/videojuego/";
            let { data } = await axios.get(rutaDeJuego + id);
            let videojuego: videojuegos = data;
            console.log(videojuego);
            setStGame({ videojuego });
        }
        const getGenero = async () => {
            const rutageneros: string = rutaBase + "/api/v0/genero";
            let ruta = rutageneros;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setGenero({ generos: respuesta.data });
        }
        getGenero();
        getGame(id);
    },
        [id]
    )

    function modifyGame() {
        let nombre = nombreGame.current?.value;
        let descripcion = descripcionGame.current?.value;
        let fecha = fechaGame.current?.value;
        let imagen = imagenGame.current?.value;

        let newGenres: generos[] = [];

        generos?.generos?.map((genero: generos) =>{
            checked.map((g: string)=>{
                if(g == genero.nombre){
                    newGenres.push(genero);
                }
            })
        })

        let newGame = {
            nombre: nombre,
            fecha: fecha,
            descripcion: descripcion,
            imagen: imagen,
            generos: newGenres
        }

        let ruta = "http://localhost:8080/api/v2/videojuego";
        const axiosput = async (rutaGame: string) => {
            try {
                const { data } = await axios.put(rutaGame + "/" + stGame.videojuego?.id, newGame, headers)
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }
        axiosput(ruta).then(respuesta => {
            navigate(-1)
        });
    }

    const handleCheck = (event: React.ChangeEvent<HTMLInputElement>) => {
        var updatedList = [...checked];
        if (event.target.checked) {
            updatedList = [...checked, event.target.value];
        } else {
            updatedList.splice(checked.indexOf(event.target.value), 1);
        }
        setChecked(updatedList);
    };

    return (
        <div>
            <Topbar />
            <div className="ajustes">
                <h3 className="titleCreate">Modificar juego:</h3>
                <br />
                <div>
                    <form className="formCreateGame">
                        <input placeholder="Nombre" defaultValue={stGame.videojuego?.nombre} className="inputCreate" ref={nombreGame} required /> <br />
                        <input placeholder="Fecha (yyyy-MM-dd)" defaultValue={stGame.videojuego?.fecha} className="inputCreate" ref={fechaGame} required /> <br />
                        <input placeholder="Dirección imagen" defaultValue={stGame.videojuego?.imagen} className="inputCreate" ref={imagenGame} required /> <br />
                        <div className="columnForm">
                            <div className="genresBox">
                                <h3 className="titleGenre">Géneros:</h3>
                                {generos?.generos?.map((g: generos) => (
                                    <div key={g.id}>
                                        <input value={g.nombre} type="checkbox" onChange={handleCheck} />
                                        <span>{g.nombre}</span>
                                    </div>
                                ))}
                            </div>
                            <br />
                            <div className="textAreaAdmin">
                                <textarea placeholder="Descripción" defaultValue={stGame.videojuego?.descripcion} className="inputCreate" ref={descripcionGame} cols={80} rows={10} required /> <br />
                            </div>
                        </div>
                        <button type="button" className="buttonForm" onClick={modifyGame}>Modificar</button>
                    </form>
                </div>
            </div>
        </div>
    )
}