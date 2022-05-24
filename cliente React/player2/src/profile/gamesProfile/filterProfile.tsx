import "./filterProfile.css";
import React, { useState, useEffect, useRef } from 'react'
import axios from 'axios';
import { Slider } from "@material-ui/core";
import { Link, Navigate, useNavigate } from 'react-router-dom';
import { generos } from "../../modelo/generos";
import toast, { Toaster } from "react-hot-toast";
import { Search } from "@material-ui/icons";
import { videojuegos } from "../../modelo/videojuegos";

interface IState { videojuegos?: videojuegos[], generos?: generos[]; }

interface IProps {
    genre: any,
    order: any,
    year: any,
    search: any
}

export default function FilterProfile(props: IProps) {
    const [filteredGenre, setFilteredGenre] = useState<any>();
    const [filteredOrder, setFilteredOrder] = useState<any>();
    const [filteredYear, setFilteredYear] = useState<any>();
    const [filteredSearch, setFilteredSearch] = useState<any>();

    let { genre } = props.genre;
    let { year } = props.year;
    let { order } = props.order;
    let { search } = props.search;

    let navigate = useNavigate();
    const juegoBuscar = useRef<HTMLInputElement>(null);
    const [val, setVal] = useState([1996, 2022]);
    const [generos, setGenero] = useState<IState>();
    const [videojuegos, setVideojuego] = useState<IState>();
    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;
    const rutageneros: string = rutaBase + "/api/v0/genero";

    let optionsGeneros = generos?.generos?.map((g: generos) =>
        <option style={{ fontSize: "16px" }}>{g.nombre}</option>
    );

    useEffect(() => {
        const getVideojuego = async () => {
            const rutajuegosHome: string = rutaBase + "/api/v0/videojuego";
            let ruta = rutajuegosHome;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setVideojuego({ videojuegos: respuesta.data });
        }
        const getGenero = async () => {
            let ruta = rutageneros;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setGenero({ generos: respuesta.data });
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

    const handleKeypress = (event: React.KeyboardEvent<HTMLInputElement>) => {
        if (event.keyCode === 13) {
            SearchGame();
        }
    }

    function SearchGame() {
        let buscar = juegoBuscar.current?.value;
        
        props.search(buscar);

        setFilteredSearch({ buscar });
    }

    return (
        <div className="filterProfile">
            <div className="filterWrapper">
                <h3 className="title">Nombre:</h3>
                <div className="searchbar">
                    <Toaster position="top-center" gutter={56} />
                    <Search className="searchIcon" onClick={SearchGame} />
                    <input placeholder="Nombre a filtrar" className="searchInput" ref={juegoBuscar} onKeyDown={(e) => handleKeypress(e)} />
                </div>

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
            </div>
        </div>
    )
}