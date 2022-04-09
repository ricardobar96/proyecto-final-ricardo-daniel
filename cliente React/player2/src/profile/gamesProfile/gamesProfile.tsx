import "./gamesProfile.css";
import TopbarProfile from "../topbarProfile";
import Topbar from "../../topbar/topbar";
import FilterProfile from "./filterProfile";
import TableGames from "./tableGames";
import { usuarios } from "../../modelo/usuarios";

export default function GamesProfile() {
    var usuarioActual: usuarios = JSON.parse(localStorage.getItem('usuarioActual') || '{}');
    
    return (
        <div style={{
            backgroundColor: usuarioActual.color != "Azul" ? usuarioActual.color : 'lightsteelblue',
          }}>
            <Topbar />
            <TopbarProfile/>
            <div className="gamesContainer">
                <FilterProfile/>
                <TableGames/>
            </div>
        </div>
    )
}