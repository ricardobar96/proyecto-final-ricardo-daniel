import React from 'react'
import './actividad.css'
import { Person } from "@material-ui/icons";
import { usuarios } from '../modelo/usuarios';

export default function Actividad() {
    var usuarioActual: usuarios = JSON.parse(localStorage.getItem('usuarioActual') || '{}');
    let avatar = usuarioActual.avatar;

    return (
        <div className="actividad">
            <h3 className='title'>Actividad</h3>
            <div className="actividadWrapper">
                <ul className="actividadList">
                    <li className="actividadItem">
                        <img src={avatar}  className="actividadLeft" />
                        <div className="actividadRight">
                            <span className="actividadTiempo">Hace 5 minutos</span>
                            <span className="actividadNombreUser">Usuario A</span>
                            <span className="actividadAccion">Acción 1</span>
                        </div>
                    </li>
                    <li className="actividadItem">
                        <img src={usuarioActual.avatar}  className="actividadLeft" />
                        <div className="actividadRight">
                            <span className="actividadTiempo">Hace 10 minutos</span>
                            <span className="actividadNombreUser">Usuario B</span>
                            <span className="actividadAccion">Acción 2</span>
                        </div>
                    </li>
                    <li className="actividadItem">
                        <img src={usuarioActual.avatar}  className="actividadLeft" />
                        <div className="actividadRight">
                            <span className="actividadTiempo">Hace 15 minutos</span>
                            <span className="actividadNombreUser">Usuario C</span>
                            <span className="actividadAccion">Acción 3</span>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    )
}
