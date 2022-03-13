import "./home.css";
import Topbar from "../topbar/topbar";
import Actividad from "./actividad";
import JuegosHome from "./juegosHome";
import React, { useRef } from 'react';
import axios from 'axios';

export default function Home() {
    return (
        <>
            <Topbar />
            <div className="homeContainer">
                <Actividad />
                <JuegosHome/>
            </div>
        </>
    )
}