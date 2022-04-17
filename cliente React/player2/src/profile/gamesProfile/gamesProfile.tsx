import "./gamesProfile.css";
import TopbarProfile from "../topbarProfile";
import Topbar from "../../topbar/topbar";
import FilterProfile from "./filterProfile";
import TableGames from "./tableGames";
import { usuarios } from "../../modelo/usuarios";
import { useState } from "react";

let genre: any;
let order: any;
let year: any;
let search: any;

export default function GamesProfile() {
    var usuarioActual: usuarios = JSON.parse(localStorage.getItem('usuarioActual') || '{}');

    const [filteredGenre, setFilteredGenre] = useState<any>();
    const [filteredOrder, setFilteredOrder] = useState<any>();
    const [filteredYear, setFilteredYear] = useState<any>();
    const [filteredSearch, setFilteredSearch] = useState<any>();

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
    
    const pull_search = (data: any) => {
        search = data;
        setFilteredSearch(search);
        console.log("Search: " + search);
    }

    return (
        <div style={{
            backgroundColor: usuarioActual.color != "Azul" ? usuarioActual.color : 'lightsteelblue',
            height: "100%"
          }}>
            <Topbar />
            <TopbarProfile/>
            <div className="gamesContainer">
                <FilterProfile genre={pull_genre} order={pull_order} year={pull_year} search={pull_search}/>
                <TableGames genre={filteredGenre} order={filteredOrder} year={filteredYear} search={filteredSearch}/>
            </div>
        </div>
    )
}