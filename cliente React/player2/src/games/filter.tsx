import "./filter.css";
import React, { useRef, useState } from 'react';
import axios from 'axios';
import { Slider } from "@material-ui/core";
import Combobox from "react-widgets/Combobox";
import TextField from '@material-ui/core/TextField';
import Autocomplete from '@material-ui/lab/Autocomplete';

export default function Filter() {
    const [val, setVal] = useState([1996, 2022]);

    return (
        <div className="filter">
            <div className="filterWrapper">
                <h3 className="title">Ordenar por:</h3>
                <div className="comboOrder">
                    <Autocomplete
                        options={["Puntuación", "Año", "Título"]}
                        style={{ width: 300 }}
                        renderInput={(params) =>
                            <TextField {...params} label="Ordenar por" variant="outlined" />}
                    />
                </div>
                <h3 className="title">Filtro:</h3>
                <div className="comboFilter">
                    <Autocomplete
                        options={["Acción", "RPG", "Aventura"]}
                        style={{ width: 300 }}
                        renderInput={(params) =>
                            <TextField {...params} label="Género" variant="outlined" />}
                    />
                </div>
                <h3 className="title">Año:</h3>
                <div className="filterSlider">
                    <Slider min={1996} defaultValue={1996} max={2022} step={1} valueLabelDisplay="on"/>
                </div>
            </div>
        </div>
    )
}