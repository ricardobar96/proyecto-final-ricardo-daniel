import "./topbar.css";
import { Search, Person, Menu, Chat } from "@material-ui/icons";
import React, { useRef } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

export default function Topbar() {
    return (
        <div className="topbarContainer">
            <div className="topbarLeft">
                <span className="logo">Player2</span>
            </div>
            <div className="topbarCenter">
                <div className="topbarLinks">
                    <Link to="/home" style={{textDecoration:"none"}}>
                        <span className="topbarLink">Home</span>
                    </Link>
                    <Link to="/register" style={{textDecoration:"none"}}>
                        <span className="topbarLink">Perfil</span>
                    </Link>
                    <Link to="/" style={{textDecoration:"none"}}>
                        <span className="topbarLink">Videojuegos</span>
                    </Link>
                </div>
            </div>
            <div className="topbarRight">
                <div className="searchbar">
                    <Search className="searchIcon" />
                    <input placeholder="Busca un videojuego" className="searchInput" />
                </div>
                <div className="topbarIcons">
                    <div className="topbarIconsItem">
                        <Chat />
                    </div>
                    <div className="topbarIconsItem">
                        <Person />
                    </div>
                    <div className="topbarIconsItem">
                        <Menu />
                    </div>

                </div>
            </div>
        </div>
    )
}