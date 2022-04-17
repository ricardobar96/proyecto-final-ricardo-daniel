import "./games.css";
import Topbar from "../topbar/topbar";
import { Filter } from "./filter";
import Trends from './trends';
import { useState } from 'react';

let genre: any;
let order: any;
let year: any;

export default function Games() {
    const [filteredGenre, setFilteredGenre] = useState<any>();
    const [filteredOrder, setFilteredOrder] = useState<any>();
    const [filteredYear, setFilteredYear] = useState<any>();

    const pull_genre = (data: any) => {
        genre = data;
        setFilteredGenre(genre);
        console.log("Genre: " + genre);
    }

    const pull_year = (data: any) => {
        year = data;
        setFilteredYear(year);
        console.log("Year: " + year);
    }

    const pull_order = (data: any) => {
        order = data;
        setFilteredOrder(order);
        console.log("Order: " + order);
    }

    /*
    if (genre != null) {
        setFilteredGenre(genre);
    }

    if (order != null) {
        setFilteredOrder(order);
    }

    if (year != null) {
        setFilteredYear(year);
    }
    */

    return (
        <>
            <Topbar />
            <div className="gamesContainer">
                <Filter genre={pull_genre} order={pull_order} year={pull_year} />
                <Trends genre={filteredGenre} order={filteredOrder} year={filteredYear} />
            </div>
        </>
    )
}