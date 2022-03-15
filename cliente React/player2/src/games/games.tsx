import "./games.css";
import Topbar from "../topbar/topbar";
import Filter from "./filter";
import Trends from './trends';
import React, { useRef } from 'react';
import axios from 'axios';

export default function Games() {
    return (
        <>
            <Topbar />
            <div className="gamesContainer">
                <Filter/>
                <Trends/>
            </div>
        </>
    )
}