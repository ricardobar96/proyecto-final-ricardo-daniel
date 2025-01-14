import "./home.css";
import Topbar from "../topbar/topbar";
import Actividad from "./actividad";
import JuegosHome from "./juegosHome";
import React, { useRef } from 'react';
import axios from 'axios';
import { usuarios } from "../modelo/usuarios";

export default function Home() {
    let usuarioActual: usuarios = JSON.parse(localStorage.getItem('usuarioActual') || '{}');
    console.log(typeof usuarioActual);
    return (
        <>
            <Topbar />
            <div className="homeContainer">
                {typeof usuarioActual.nombre !== 'undefined' ? (<Actividad />) : null
                }
                
                <JuegosHome/>
            </div>
        </>
    )
}