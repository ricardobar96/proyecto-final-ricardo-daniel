import "./trends.css";
import React, { useRef } from 'react';
import axios from 'axios';
import { VideogameAsset } from "@material-ui/icons";

export default function Trends() {
    return (
        <div className="trends">
            <div className="trendsWrapper">
                <h3 className="title">Tendencias:</h3>
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
                <h3 className="title">Nuevos:</h3>
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
        </div>
    )
}