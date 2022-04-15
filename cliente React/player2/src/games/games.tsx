import "./games.css";
import Topbar from "../topbar/topbar";
import {Filter} from "./filter";
import Trends from './trends';
import { useState } from 'react';

export default function Games() {
    const [filteredGenre, setFilteredGenre] = useState<any>();
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