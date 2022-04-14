import "./filter.css";
import React, { useState, useEffect, useRef } from 'react'
import axios from 'axios';
import { Slider } from "@material-ui/core";
import { generos } from "../modelo/generos";
import { usuarios } from "../modelo/usuarios";
import { useNavigate } from "react-router-dom";

interface IState { generos?: generos[]; }

export default function Filter() {
    const [val, setVal] = useState([1996, 2022]);
    const [generos, setGenero] = useState<IState>();
    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;
    const rutageneros: string = rutaBase + "/api/v0/genero";
    let navigate = useNavigate();

    let optionsGeneros = generos?.generos?.map((g: generos) =>
        <option style={{ fontSize: "16px" }}>{g.nombre}</option>
    );

    var usuarioActual: usuarios = JSON.parse(localStorage.getItem('usuarioActual') || '{}');

    useEffect(() => {
        const getGenero = async () => {
            let ruta = rutageneros;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setGenero({ generos: respuesta.data });
        }
        getGenero();
    }, []);

    function orderGames(event: React.ChangeEvent<HTMLSelectElement>) {
        if (event.currentTarget.value === "Año") {
            alert("Ordenar por año");
        }
        if (event.currentTarget.value === "Puntuación") {
            alert("Ordenar por puntuación");
        }
        if (event.currentTarget.value === "Título") {
            alert("Ordenar por título");
        }
    }

    function filterGames(event: React.ChangeEvent<HTMLSelectElement>) {
        event.preventDefault();
        if (event.currentTarget.value === "Acción") {
            alert("Solo acción");
        }
    }

    function filterTime(value: number | number[]) {
        if (value == 2000) {
            alert("Año 2000");
        }
        if (value == 1996) {
            alert("Año 1996");
        }
    }

    function goToGenres() {
        let ruta = "/api/v2/modifyGenres";
        navigate(ruta);
    }

    function goToAddAdmin() {
        let ruta = "/api/v2/addAdmin";
        navigate(ruta);
    }

    return (
        <div className="filter">
            <div className="filterWrapper">
                <h3 className="title">Ordenar por:</h3>
                <div className="comboOrder">
                    <select style={{ width: 300, backgroundColor: "lightsteelblue", height: "60px", fontSize: "16px", padding: "10px", border: "groove lightsteelblue" }}
                        onChange={orderGames} defaultValue="">
                        <option>Puntuación</option>
                        <option>Año</option>
                        <option>Título</option>
                        <option hidden value="">Selecciona un método</option>
                    </select>
                </div>
                <h3 className="title">Filtro:</h3>
                <div className="comboFilter">
                    <select style={{ width: 300, backgroundColor: "lightsteelblue", height: "60px", fontSize: "16px", padding: "10px", border: "groove lightsteelblue" }}
                        onChange={filterGames} defaultValue="">
                        {optionsGeneros}
                        <option hidden value="">Selecciona un género</option>
                    </select>
                </div>
                <h3 className="title">Año:</h3>
                <div className="filterSlider">
                    <Slider min={1996} defaultValue={1996} max={2022} step={1} valueLabelDisplay="on"
                        onChangeCommitted={(event, value) => filterTime(value)} />
                </div>
                <div className="boxButtonGenre">
                    {usuarioActual.rol == "ROLE_ADMIN" ?
                        <button className="buttonModify" onClick={goToGenres}>Modificar géneros</button>
                        : null
                    }

                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                    {usuarioActual.rol == "ROLE_ADMIN" ?
                        <button className="buttonCreate" onClick={goToAddAdmin}>Agregar Admin</button>
                        : null
                    }
                </div>
            </div>
        </div>
    )
}