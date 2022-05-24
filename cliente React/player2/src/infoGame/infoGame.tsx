import "./infoGame.css";
import React, { useState, useEffect, useRef } from 'react';
import { Link, Route, BrowserRouter, Routes, useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import Topbar from '../topbar/topbar';
import { videojuegos } from "../modelo/videojuegos";
import { pistas } from "../modelo/pistas";
import { reviews } from "../modelo/reviews";
import { usuarios } from "../modelo/usuarios";
import { Checkbox } from "@material-ui/core";
import { juegosUsuario } from '../modelo/juegosUsuario';
import { Slider } from "@material-ui/core";
import { generos } from '../modelo/generos';

interface IState { videojuego?: videojuegos, pista?: pistas[], review?: reviews[], usuario?: usuarios; juegosUsuario?: juegosUsuario[]; }

export default function InfoGame() {
    let navigate = useNavigate();
    const [stGame, setStGame] = useState<IState>({});
    const [stClue, setStClue] = useState<IState>({});
    const [stReview, setStReview] = useState<IState>({});
    const [stUser, setStUser] = useState<IState>({});
    const [stUserGames, setStUserGames] = useState<IState>({});
    const { id } = useParams();

    const [val, setVal] = useState([0, 10]);

    const token = localStorage.getItem("token") as string;
    const headers = {
        headers: { Authorization: token }
    };

    const ip: string = "localhost";
    const puerto: number = 8080;
    const rutaBase: string = "http://" + ip + ":" + puerto;

    const hoursPlayed = useRef<HTMLInputElement>(null);

    var usuarioActual: usuarios = JSON.parse(localStorage.getItem('usuarioActual') || '{}');
    let initialCheckComplete: any = 0;
    let completado = false;
    let progress = false;
    let juegoUsuarioActual: juegosUsuario;

    let defaultSlider = 5;
    let defaultHours = 0;

    let genresInfo: any = [];
    let checkGenres = 0;
    stGame.videojuego?.generos.map((g: generos) => {
        genresInfo.push(g.nombre);
        checkGenres += 1;
    })

    genresInfo = genresInfo.join(', ');

    stUserGames.juegosUsuario?.map((j: juegosUsuario) => {
        if (j.videojuego.id === stGame.videojuego?.id) {
            if (j.usuario.id === usuarioActual.id) {
                juegoUsuarioActual = j;
                initialCheckComplete = 1;
                defaultSlider = j.puntuacion;
                defaultHours = j.horas;
                progress = true;
                if (j.completado == 1) {
                    completado = true;
                }
            }
        }
    });

    const [checkedCompleted, setCheckedCompleted] = React.useState(false);
    const [checkedProgress, setCheckedProgress] = React.useState(false);

    function createReview() {
        let ruta = "/api/v1/newReview/videojuego/" + stGame.videojuego?.id;
        navigate(ruta);
    }

    function createClue() {
        let ruta = "/api/v1/newClue/videojuego/" + stGame.videojuego?.id;
        navigate(ruta);
    }

    function handleChangeCompleted() {
        if (completado == false) {
            const newJuegoUsuario = new juegosUsuario(juegoUsuarioActual.id, 1, juegoUsuarioActual.horas,
                juegoUsuarioActual.usuario, juegoUsuarioActual.videojuego, juegoUsuarioActual.puntuacion,
                juegoUsuarioActual.fecha);
            let ruta = "http://localhost:8080/api/v1/juegousuario";
            const axiosput = async (rutaDeJuegoUsuario: string) => {
                try {
                    const { data } = await axios.put(rutaDeJuegoUsuario + "/" + juegoUsuarioActual.id, newJuegoUsuario, headers)
                    console.log(data);
                } catch (error) {
                    console.log(error);
                }
            }
            axiosput(ruta).then(respuesta => {
                navigate(0)
            });
        }
        if (completado == true) {
            const newJuegoUsuario = new juegosUsuario(juegoUsuarioActual.id, 0, juegoUsuarioActual.horas, juegoUsuarioActual.usuario,
                juegoUsuarioActual.videojuego, juegoUsuarioActual.puntuacion, juegoUsuarioActual.fecha);
            let ruta = "http://localhost:8080/api/v1/juegousuario";
            const axiosput = async (rutaDeJuegoUsuario: string) => {
                try {
                    const { data } = await axios.put(rutaDeJuegoUsuario + "/" + juegoUsuarioActual.id, newJuegoUsuario, headers)
                    console.log(data);
                } catch (error) {
                    console.log(error);
                }
            }
            axiosput(ruta).then(respuesta => {
                navigate(0)
            });
        }
        setCheckedCompleted(!checkedCompleted);
    }

    function handleChangeProgress() {
        if (progress == false) {
            const newJuegoUsuario = new juegosUsuario(1, 0, 0, usuarioActual, stGame.videojuego!, 5, new Date());
            let ruta = "http://localhost:8080/api/v1/juegousuario";
            const axiospost = async (rutaDeJuegoUsuario: string) => {
                try {
                    const { data } = await axios.post(rutaDeJuegoUsuario, newJuegoUsuario, headers)
                    console.log(data);
                } catch (error) {
                    console.log(error);
                }
            }
            axiospost(ruta).then(respuesta => {
                navigate(0)
            });

            completado = true;
        }
        if (progress == true) {
            let ruta = "http://localhost:8080/api/v1/juegousuario";
            const axiosdelete = async (rutaDeJuegoUsuario: string) => {
                try {
                    const { data } = await axios.delete(rutaDeJuegoUsuario + "/" + juegoUsuarioActual.id, headers)
                    console.log(data);
                } catch (error) {
                    console.log(error);
                }
            }
            axiosdelete(ruta).then(respuesta => {
                navigate(0)
            });

            completado = false;
        }
    }

    function rateGame(value: any) {

        let puntuacion: number = parseInt(value);

        const newJuegoUsuario = new juegosUsuario(juegoUsuarioActual.id, 0, juegoUsuarioActual.horas, juegoUsuarioActual.usuario,
            juegoUsuarioActual.videojuego, puntuacion, juegoUsuarioActual.fecha);
        let ruta = "http://localhost:8080/api/v1/juegousuario";
        const axiosput = async (rutaDeJuegoUsuario: string) => {
            try {
                const { data } = await axios.put(rutaDeJuegoUsuario + "/" + juegoUsuarioActual.id, newJuegoUsuario, headers)
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }
        axiosput(ruta).then(respuesta => {
            navigate(0)
        });

    }

    function changeHours() {
        let horas = hoursPlayed?.current?.value;

        let horasFinal: number = Number(horas);

        const newJuegoUsuario = new juegosUsuario(juegoUsuarioActual.id, 0, horasFinal, juegoUsuarioActual.usuario,
            juegoUsuarioActual.videojuego, juegoUsuarioActual.puntuacion, juegoUsuarioActual.fecha);
        let ruta = "http://localhost:8080/api/v1/juegousuario";
        const axiosput = async (rutaDeJuegoUsuario: string) => {
            try {
                const { data } = await axios.put(rutaDeJuegoUsuario + "/" + juegoUsuarioActual.id, newJuegoUsuario, headers)
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }
        axiosput(ruta).then(respuesta => {
            navigate(0)
        });
    }

    function deleteGame() {
        let ruta = "http://localhost:8080/api/v2/videojuego/";
        const axiosdelete = async (rutaDeJuego: string) => {
            try {
                const { data } = await axios.delete(rutaDeJuego + stGame.videojuego?.id,
                    headers)
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }

        axiosdelete(ruta).then(respuesta => {
            navigate("/")
        });
    }

    function deleteClue(idPista: number) {
        let ruta = "http://localhost:8080/api/v2/pista/";
        const axiosdelete = async (rutaDeJuego: string) => {
            try {
                const { data } = await axios.delete(rutaDeJuego + idPista,
                    headers)
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }

        axiosdelete(ruta).then(respuesta => {
            navigate(0)
        });
    }

    function deleteReview(idReview: number) {
        let ruta = "http://localhost:8080/api/v2/review/";
        const axiosdelete = async (rutaDeJuego: string) => {
            try {
                const { data } = await axios.delete(rutaDeJuego + idReview,
                    headers)
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }

        axiosdelete(ruta).then(respuesta => {
            navigate(0)
        });
    }

    function goModifyGame() {
        let ruta = "/api/v2/modifyGame/videojuego/" + stGame.videojuego?.id;
        navigate(ruta);
    }

    useEffect(() => {
        const getGame = async (id: string | undefined) => {
            let rutaDeJuego = "http://localhost:8080/api/v0/videojuego/";
            let { data } = await axios.get(rutaDeJuego + id);
            let videojuego: videojuegos = data;
            console.log(videojuego);
            setStGame({ videojuego });
        }
        const getClue = async () => {
            const rutaPistas: string = rutaBase + "/api/v0/pista";
            let ruta = rutaPistas;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setStClue({ pista: respuesta.data });
        }
        const getReview = async () => {
            const rutaReviews: string = rutaBase + "/api/v0/review";
            let ruta = rutaReviews;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setStReview({ review: respuesta.data });
        }
        const getUser = async (id: string | undefined) => {
            let rutadeUsuarios = "http://localhost:8080/api/v0/usuario/";
            let { data } = await axios.get(rutadeUsuarios + id);
            let usuario: usuarios = data;
            console.log(usuario);
            setStUser({ usuario });
        }
        const getJuegosUsuario = async () => {
            const rutaJuegosUsuario: string = rutaBase + "/api/v0/juegousuario";
            let ruta = rutaJuegosUsuario;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setStUserGames({ juegosUsuario: respuesta.data });
        }
        getUser(id);
        getReview();
        getClue();
        getGame(id);
        getJuegosUsuario();
    },
        [id]
    )

    return (
        <>
            <Topbar />
            <div className="infoGame">
                {usuarioActual.rol == "ROLE_ADMIN" ?
                    <button className="buttonDelete" onClick={deleteGame}>Eliminar videojuego</button>
                    : null
                }

                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                {usuarioActual.rol == "ROLE_ADMIN" ?
                    <button className="buttonModify" onClick={goModifyGame}>Editar videojuego</button>
                    : null
                }

                <div className="infoGameWrapper">
                    <h2 className='titleGameInfo'>{stGame.videojuego?.nombre}</h2>
                    <span><img src={stGame.videojuego?.imagen} className='imageGameInfo' /></span>
                    <h3 className='titleGameInfo'>Nota media: {stGame.videojuego?.puntuacion}</h3>
                    {checkGenres > 0 ?
                        <span style={{ fontWeight: "bold" }}>Géneros: {genresInfo}</span>
                        : null
                    }
                    <br />
                    <br />

                    {usuarioActual.nombre != null ?
                        <span style={{ color: "orangered", fontWeight: "bolder" }}>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            Añadir a lista</span>
                        : null
                    }

                    {usuarioActual.nombre != null ?
                        <Checkbox
                            value={checkedProgress}
                            checked={progress}
                            onClick={handleChangeProgress}
                            style={{ marginLeft: "5px" }}
                        />
                        : null
                    }

                    &nbsp;&nbsp;&nbsp;&nbsp;

                    {initialCheckComplete == 1 ?
                        <span style={{ color: "orangered", fontWeight: "bolder" }}>¿Completado?</span>
                        : null
                    }

                    {initialCheckComplete == 1 ?
                        <Checkbox
                            value={checkedCompleted}
                            checked={completado}
                            //onChange={handleChangeCompleted}
                            onClick={handleChangeCompleted}
                            style={{ marginLeft: "5px" }}
                        />
                        : null
                    }

                    <br />
                    <br />

                    {initialCheckComplete == 1 ?
                        <>
                            <span style={{ color: "orangered", fontWeight: "bolder" }}>Horas jugadas:</span>
                            <form>
                                <input type="number" ref={hoursPlayed} defaultValue={defaultHours} style={{
                                    color: "blue", fontWeight: "bolder", width: "60px",
                                    marginTop: "10px", height: "25px"
                                }} required />
                                <button type="button" onClick={changeHours} className="buttonHours">Enviar</button>
                            </form>
                        </>
                        : null
                    }

                    <br />
                    <br />

                    {initialCheckComplete == 1 ?
                        <span style={{ color: "blue", fontWeight: "bolder" }}>TU PUNTUACIÓN:</span>
                        : null
                    }

                    <br />
                    <br />
                    <br />

                    {initialCheckComplete == 1 ?

                        <div className="rateSlider">
                            <Slider min={0} defaultValue={defaultSlider} max={10} step={1} valueLabelDisplay="on"
                                onChangeCommitted={(event, value) => rateGame(value)} />
                        </div>
                        : null
                    }

                    <div className="description">
                        <h3 className="title">Descripción:</h3>
                        <p className="infoP">
                            {stGame.videojuego?.descripcion}
                        </p>
                    </div>
                    <div className='infoPistasWrapper'>
                        <h3 className="title">Pistas:</h3>
                        <ul className='pistasList'>
                            {stClue.pista?.map((p: pistas) => {
                                if (p.videojuego.id == stGame.videojuego?.id) {
                                    return (
                                        <li className="pistasItem">
                                            <Link to={{ pathname: "/api/v0/usuario/" + p.usuario.id }} style={{ textDecoration: "none" }}>
                                                <span className="pistasLeft"><img src={p.usuario.avatar} className="avatarInfo" /> {p.usuario.nombre}</span>
                                            </Link>
                                            <div className="pistasRight">
                                                <h3>{p.titulo} ({p.fecha.slice(0, 10)})</h3>
                                                <p>{p.contenido}</p>
                                            </div>
                                            {usuarioActual.rol == "ROLE_ADMIN" ?
                                                <button className="buttonDelete" onClick={() => deleteClue(p.id)}>Eliminar pista</button>
                                                : null
                                            }
                                        </li>
                                    );
                                }
                            })}
                        </ul>
                    </div>
                    <button type="button" className="buttonForm" onClick={createClue}>Crear Pista</button>

                    <div className='infoReviewsWrapper'>
                        <h3 className="title">Reviews:</h3>
                        <ul className='reviewsList'>
                            {stReview.review?.map((r: reviews) => {
                                if (r.videojuego.id == stGame.videojuego?.id)
                                    return (
                                        <li className="pistasItem">
                                            <Link to={{ pathname: "/api/v0/usuario/" + r.usuario.id }} style={{ textDecoration: "none" }}>
                                                <span className="pistasLeft"><img src={r.usuario.avatar} className="avatarInfo" /> {r.usuario.nombre}</span>
                                            </Link>
                                            <div className="pistasRight">
                                                <h3>{r.titulo} ({r.fecha.slice(0, 10)})</h3>
                                                <p>{r.contenido}</p>
                                            </div>
                                            {usuarioActual.rol == "ROLE_ADMIN" ?
                                                <button className="buttonDelete" onClick={() => deleteReview(r.id)}>Eliminar review</button>
                                                : null
                                            }
                                        </li>
                                    );
                            })}
                        </ul>
                    </div>
                    <button type="button" className="buttonForm" onClick={createReview}>Crear Review</button>
                </div>
            </div>
        </>
    )
}