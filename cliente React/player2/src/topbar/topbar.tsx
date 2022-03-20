import "./topbar.css";
import { Search, Person, Menu, Chat, ExitToApp } from "@material-ui/icons";
import React, { useRef } from 'react';
import axios from 'axios';
import { Link, Navigate, useNavigate } from 'react-router-dom';
import Dropdown from 'react-dropdown';
import 'react-dropdown/style.css';

export default function Topbar() {
    let navigate = useNavigate();
    const juegoBuscar = useRef<HTMLInputElement>(null);

    function Logout() {
        localStorage.clear();
        navigate("/");
    }

    function SearchGame() {
        let buscar = juegoBuscar.current?.value;
        navigate("/game/" + buscar);
    }

    function goChat() {
        navigate("/chat");
    }
    
    return (
        <div className="topbarContainer">
            <div className="topbarLeft">
                <span className="logo">Player2</span>
            </div>
            <div className="topbarCenter">
                <div className="topbarLinks">
                    <Link to="/home" style={{ textDecoration: "none" }}>
                        <span className="topbarLink">Home</span>
                    </Link>
                    <Link to="/register" style={{ textDecoration: "none" }}>
                        <span className="topbarLink">Perfil</span>
                    </Link>
                    <Link to="/games" style={{ textDecoration: "none" }}>
                        <span className="topbarLink">Videojuegos</span>
                    </Link>
                </div>
            </div>
            <div className="topbarRight">
                <div className="searchbar">
                    <Search className="searchIcon" onClick={SearchGame}/>
                    <input placeholder="Busca un videojuego" className="searchInput" ref={juegoBuscar}/>
                </div>
                <div className="topbarIcons">
                    <div className="topbarIconsItem">
                        <Chat onClick={goChat}/>
                    </div>
                    <div className="topbarIconsItem">
                        <Person />
                    </div>
                    <div className="topbarIconsItem">
                        <ExitToApp onClick={Logout} />
                    </div>

                </div>
            </div>
        </div>
    )
}