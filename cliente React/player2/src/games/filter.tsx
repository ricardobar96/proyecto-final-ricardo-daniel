import "./filter.css";
import React, { useRef, useState } from 'react';
import axios from 'axios';
import { Slider } from "@material-ui/core";
import TextField from '@material-ui/core/TextField';
import Autocomplete from '@material-ui/lab/Autocomplete';

export default function Filter() {
    const [val, setVal] = useState([1996, 2022]);

    function orderGames(value: string | null) {
        if (value === "Año") {
            alert("Ordenar por año");
        }
        if (value === "Puntuación") {
            alert("Ordenar por puntuación");
        }
        if (value === "Título") {
            alert("Ordenar por título");
        }
    }

    function filterGames(value: string | null) {
        if (value === "Acción") {
            alert("Solo acción");
        }
        if (value === "RPG") {
            alert("Solo RPG");
        }
        if (value === "Aventura") {
            alert("Solo aventura");
        }
    }

    function filterTime(value: number | number[]) {
        if (value == 2000) {
            alert("Año 2000");
        }
        if (value == 1996){
            alert("Año 1996");
        }
    }

    return (
        <div className="filter">
            <div className="filterWrapper">
                <h3 className="title">Ordenar por:</h3>
                <div className="comboOrder">
                    <Autocomplete onChange={(event, value) => orderGames(value)}
                        options={["Puntuación", "Año", "Título"]}
                        style={{ width: 300 }}
                        renderInput={(params) =>
                            <TextField {...params} label="Ordenar por" variant="outlined" />}
                    />
                </div>
                <h3 className="title">Filtro:</h3>
                <div className="comboFilter">
                    <Autocomplete
                        onChange={(event, value) => filterGames(value)}
                        options={["Acción", "RPG", "Aventura"]}
                        style={{ width: 300 }}
                        renderInput={(params) =>
                            <TextField {...params} label="Género" variant="outlined" />}
                    />
                </div>
                <h3 className="title">Año:</h3>
                <div className="filterSlider">
                    <Slider min={1996} defaultValue={1996} max={2022} step={1} valueLabelDisplay="on" onChangeCommitted={(event, value) => filterTime(value)}/>
                </div>
            </div>
        </div>
    )
}