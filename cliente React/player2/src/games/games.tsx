import "./games.css";
import Topbar from "../topbar/topbar";
import Filter from "./filter";
import Trends from './trends';
import { useState } from 'react';

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