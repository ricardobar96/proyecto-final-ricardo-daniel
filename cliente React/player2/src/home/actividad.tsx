import React from 'react'
import './actividad.css'
import { Person } from "@material-ui/icons";
import { usuarios } from '../modelo/usuarios';
import { actividad } from  '../modelo/actividad';
import { juegosUsuario } from '../modelo/juegosUsuario';
import { reviews } from '../modelo/reviews';
import { Link } from 'react-router-dom';
import { pistas } from '../modelo/pistas';

export default function Actividad() {
    var usuarioActual: usuarios = JSON.parse(localStorage.getItem('usuarioActual') || '{}');
    let avatar = usuarioActual.avatar;
    
    let actividades: actividad[] = [];

    console.log(usuarioActual);
    usuarioActual.followeds.map((followed: usuarios) => {
        console.log(followed);
        followed.juegoUsuarios.map((juegoU: juegosUsuario) => {
            let actividadNew = new actividad("juegoU", juegoU.fecha, followed, juegoU.videojuego);
            actividades.push(actividadNew);
        });
    
        followed.reviews.map((review: reviews) => {
            let actividadNew = new actividad("review", review.fecha, followed, review.videojuego);
            console.log(review.videojuego);
            actividades.push(actividadNew);
        });
    
        followed.pistas.map((pista: pistas) => {
            let actividadNew = new actividad("pista", pista.fecha, followed, pista.videojuego);
            actividades.push(actividadNew);
        });
    });
    
    //actividades.sort((a, b) => (a.fecha) - (b.fecha));

    /*return (
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
    )*/
    return (
        <div className="actividad">
            <h3 className='title'>Actividad</h3>
            <div className="actividadWrapper">
                <ul className="actividadList">
                    {actividades.map((actividad: actividad) => {
                        return (
                            <li className="actividadItem">
                        <img src={actividad.usuario.avatar}  className="actividadLeft" />
                        <div className="actividadRight">
                            <span className="actividadTiempo">Hace {actividad.fecha} minutos</span>
                            <Link to={{ pathname: "/api/v0/usuario/" + actividad.usuario.id }}>
                                <span className="actividadNombreUser">{actividad.usuario.nombre}</span>
                            </Link>
                            <Link to={{ pathname: "/api/v0/videojuego/" + actividad.videojuego.id }}>
                            {actividad.tipo === "juegoU" ? (
                                <span className="actividadAccion">Ha añadido {actividad.videojuego.nombre} a sus videojuegos jugados.</span>
                            ) : 
                            actividad.tipo === "review" ? (
                                <span className="actividadAccion">Ha creado una review de {actividad.videojuego.nombre}</span>
                            ) :
                            (
                                <span className="actividadAccion">Ha creado una pista de {actividad.videojuego.nombre}</span>
                            )}
                            </Link>
                        </div>
                    </li>
                        );
        })}
                
                </ul>
            </div>
        </div>
    )
}
