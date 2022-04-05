import "./gamesProfile.css";
import TopbarProfile from "../topbarProfile";
import Topbar from "../../topbar/topbar";
import FilterProfile from "./filterProfile";
import TableGames from "./tableGames";

export default function GamesProfile() {
    return (
        <>
            <Topbar />
            <TopbarProfile/>
            <div className="gamesContainer">
                <FilterProfile/>
                <TableGames/>
            </div>
        </>
    )
}