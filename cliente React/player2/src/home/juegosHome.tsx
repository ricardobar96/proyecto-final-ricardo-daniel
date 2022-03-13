import React from 'react'
import './juegosHome.css'
import { VideogameAsset } from "@material-ui/icons";

export default function JuegosHome() {
    return (
        <div className="juegosHome">
            <h3 className='title'>Tus juegos en progreso:</h3>
            <div className='progresoWrapper'>
                <ul className='progresoList'>
                    <li>
                        <VideogameAsset />
                    </li>
                    <li>
                        <VideogameAsset />
                    </li>
                    <li>
                        <VideogameAsset />
                    </li>
                </ul>
            </div>

            <h3 className='title'>Videojuegos en tendencias:</h3>
            <div className='tendenciasWrapper'>
                <ul className='tendenciasList'>
                    <li>
                        <VideogameAsset />
                    </li>
                    <li>
                        <VideogameAsset />
                    </li>
                    <li>
                        <VideogameAsset />
                    </li>
                </ul>
            </div>

            <h3 className='title'>Nuevos videojuegos:</h3>
            <div className='nuevosWrapper'>
                <ul className='nuevosList'>
                    <li>
                        <VideogameAsset />
                    </li>
                    <li>
                        <VideogameAsset />
                    </li>
                    <li>
                        <VideogameAsset />
                    </li>
                </ul>
            </div>
        </div>
    )
}
