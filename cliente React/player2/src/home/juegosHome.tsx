import React, { useState, useEffect } from 'react'
import './juegosHome.css'
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';
import { videojuegos } from '../modelo/videojuegos';
import { usuarios } from '../modelo/usuarios';
import { juegosUsuario } from '../modelo/juegosUsuario';

interface IProps { }

interface IState { videojuegos?: videojuegos[], usuario?: usuarios, juegosUsuario?: juegosUsuario[]; }

export const JuegosHome = () => {
    const [videojuegos, setVideojuego] = useState<IState>();
    const [usuario, setUsuario] = useState<IState>();
    const [juegosUsuario, setJuegosUsuario] = useState<IState>();
    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;
    const { id } = useParams();
    let contadorProgreso = 0;

    var usuarioActual: usuarios = JSON.parse(localStorage.getItem('usuarioActual') || '{}');
    let nameUser = usuarioActual.nombre;

    useEffect(() => {
        const getVideojuego = async () => {
            const rutajuegosHome: string = rutaBase + "/api/v0/videojuego";
            let ruta = rutajuegosHome;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setVideojuego({ videojuegos: respuesta.data });
        }
        const getUsuario = async () => {
            const rutaUsuario: string = rutaBase + "/api/v0/usuario";
            let ruta = rutaUsuario;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setUsuario({ usuario: respuesta.data });
        }
        const getJuegosUsuario = async () => {
            const rutaJuegosUsuario: string = rutaBase + "/api/v0/juegousuario";
            let ruta = rutaJuegosUsuario;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setJuegosUsuario({ juegosUsuario: respuesta.data });
        }
        getVideojuego();
        getUsuario();
        getJuegosUsuario();
    }, []);

    return (
        <div className="juegosHome">
            <h3 className='title'>Videojuegos en tendencias:</h3>
            <div className='tendenciasWrapper'>
                <ul className='tendenciasList'>
                    {
                        videojuegos?.videojuegos?.slice(0, 5).map((a: videojuegos) => {
                            return (
                                <div className='juegosHomeBox'>
                                    <Link to={{ pathname: "/api/v0/videojuego/" + a.id }}>
                                        <li>
                                            <span><img src={a.imagen} className='imageGameHome' /></span>
                                            <div className='titleHomeBox'>
                                                    <h5 className='titleGameHome'>{a.nombre}</h5>
                                                </div>
                                        </li>
                                    </Link>
                                </div>
                            );
                        })
                    }
                </ul>
            </div>

            <h3 className='title'>Nuevos videojuegos:</h3>
            <div className='nuevosWrapper'>
                <ul className='nuevosList'>
                    {
                        videojuegos?.videojuegos?.slice(0, 5).map((a: videojuegos) => {
                            return (
                                <div className='juegosHomeBox'>
                                    <Link to={{ pathname: "/api/v0/videojuego/" + a.id }}>
                                        <li>
                                            <span><img src={a.imagen} className='imageGameHome' /></span>
                                            <div className='titleHomeBox'>
                                                    <h5 className='titleGameHome'>{a.nombre}</h5>
                                                </div>
                                        </li>
                                    </Link>
                                </div>
                            );
                        })
                    }
                </ul>
            </div>

            <h3 className='title'>Tus juegos en progreso:</h3>
            <div className='nuevosWrapper'>
                <ul className='nuevosList'>
                    {
                        juegosUsuario?.juegosUsuario?.map((v: juegosUsuario) => {
                            if ((v.usuario.nombre === nameUser) && (v.completado == 0) && (contadorProgreso <= 4)){
                                contadorProgreso = contadorProgreso+1
                                return (
                                    <div className='juegosHomeBox'>
                                        <Link to={{ pathname: "/api/v0/videojuego/" + v.videojuego.id }}>
                                            <li>
                                                <span><img src={v.videojuego.imagen} className='imageGameHome' /></span>
                                                <div className='titleHomeBox'>
                                                    <h5 className='titleGameHome'>{v.videojuego.nombre}</h5>
                                                </div>
                                            </li>
                                        </Link>
                                    </div>
                                );
                            }                       
                        })
                    }
                </ul>
            </div>
        </div>
    )
}
export default JuegosHome;
