import "./topbar.css";
import { Search, Person, Menu, Chat } from "@material-ui/icons";
import React, { useRef } from 'react';
import axios from 'axios';

export default function Topbar() {
    return (
        <div className="topbarContainer">
            <div className="topbarLeft">
                <span className="logo">Player2</span>
            </div>
            <div className="topbarCenter">
                <div className="topbarLinks">
                    <span className="topbarLink">Home</span>
                    <span className="topbarLink">Perfil</span>
                    <span className="topbarLink">Videojuegos</span>
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