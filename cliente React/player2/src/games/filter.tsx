import "./filter.css";
import React, { useState, useEffect, useRef } from 'react'
import axios from 'axios';
import { Slider } from "@material-ui/core";
import { generos } from "../modelo/generos";
import { usuarios } from "../modelo/usuarios";
import { Link, useNavigate } from "react-router-dom";
import { videojuegos } from "../modelo/videojuegos";

interface IState { generos?: generos[]; videojuegos?: videojuegos[]; }

interface IProps {
    genre: any,
    order: any,
    year: any
}

export function Filter(props: IProps) {
    const [filteredGenre, setFilteredGenre] = useState<any>();
    const [filteredOrder, setFilteredOrder] = useState<any>();
    const [filteredYear, setFilteredYear] = useState<any>();

    let { genre } = props.genre;
    let { year } = props.year;
    let { order } = props.order;

    const [val, setVal] = useState([1996, 2022]);
    const [generos, setGenero] = useState<IState>();
    const [videojuegos, setVideojuego] = useState<IState>();
    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;
    const rutageneros: string = rutaBase + "/api/v0/genero";
    const rutajuegosHome: string = rutaBase + "/api/v0/videojuego";
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
        const getVideojuego = async () => {
            let ruta = rutajuegosHome;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setVideojuego({ videojuegos: respuesta.data });
        }
        getVideojuego();
        getGenero();
    }, []);

    function orderGames(event: React.ChangeEvent<HTMLSelectElement>) {

        let order: string = event.currentTarget.value;

        setFilteredOrder({ order });

        props.order(event.currentTarget.value);
    }

    function filterGames(event: React.ChangeEvent<HTMLSelectElement>) {
        event.preventDefault();

        let genero: string = event.currentTarget.value;

        props.genre(event.currentTarget.value);

        setFilteredGenre({ genero });
    }

    function filterTime(value: number | number[]) {

        let year: any = value;

        props.year(value);

        setFilteredYear({ year });
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