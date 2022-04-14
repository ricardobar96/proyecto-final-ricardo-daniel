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

    usuarioActual.followeds.map((followed: usuarios) => {
        console.log(followed);
        followed.juegoUsuarios.map((juegoU: juegosUsuario) => {
            let actividadNew = new actividad("juegoU", new Date(juegoU.fecha), followed, juegoU.videojuego);
            actividades.push(actividadNew);
        });
    
        followed.reviews.map((review: reviews) => {
            let actividadNew = new actividad("review", new Date(review.fecha), followed, review.videojuego);
            actividades.push(actividadNew);
        });
    
        followed.pistas.map((pista: pistas) => {
            let actividadNew = new actividad("pista", new Date(pista.fecha), followed, pista.videojuego);
            actividades.push(actividadNew);
        });
    });
    
    function EstimarTiempo(milisegundos: number) {
        let tiempo = "";
        let segundos = Math.trunc(milisegundos / 1000);
        if (segundos > 60) {
            let minutos = Math.trunc(segundos / 60);
            if (minutos > 60) {
                let horas = Math.trunc(minutos / 60);
                if (horas > 24) {
                    let dias = Math.trunc(horas / 24);
                    tiempo = "Han  pasado "  + dias + " días.";
                } else {
                    tiempo = "Han  pasado "  + horas + " horas.";
                }
            } else {
                tiempo = "Han  pasado "  + minutos + " minutos.";
            }
        } else {
            tiempo = "Han  pasado "  + segundos + " segundos.";
        } 

        return tiempo;
    }
    actividades.sort((a, b) => (b.fecha.getTime()) - (a.fecha.getTime()));

    return (
        <div className="actividad">
            <h3 className='title'>Actividad</h3>
            <div className="actividadWrapper">
                <ul className="actividadList">
                    {actividades.map((actividad: actividad) => {
                        return (
                            <li className="actividadItem">
                                <Link to={{ pathname: "/api/v0/usuario/" + actividad.usuario.id }}>
                                    <img src={actividad.usuario.avatar}  className="actividadLeft" />
                                </Link>
                        <div className="actividadRight">
                            <span className="actividadTiempo">{EstimarTiempo(new Date().getTime() - actividad.fecha.getTime())}</span>
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
