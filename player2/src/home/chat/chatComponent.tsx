import { ArrowBack, SentimentDissatisfied } from '@material-ui/icons';
import { Send } from '@material-ui/icons';
import axios from 'axios';
import { waitForDebugger } from 'inspector';
import { syncBuiltinESMExports } from 'module';
import React, { useEffect, useRef, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import io from 'socket.io-client';
import { mensajes } from '../../modelo/mensajes';
import { mensajesChat } from '../../modelo/mensajesChat';
import { usuarios } from '../../modelo/usuarios';
import Topbar from '../../topbar/topbar';
import './chatComponent.css';

interface IState { usuario?: usuarios }
interface IStateMensajes { mensajesCh?: mensajesChat[] }

const socket = io('http://localhost:3001');
socket.connect();
export default function ChatComponent() {
    var usuarioActual: usuarios = JSON.parse(localStorage.getItem('usuarioActual') || '{}');

    const [stUser, setStUser] = useState<IState>({});

    const [stDestinatario, setStDestinatario] = useState<IState>({});

    const [stMensajes, setStMensajes] = useState<IStateMensajes>({});

    const { id } = useParams();

    const textoMensaje = useRef<HTMLTextAreaElement>(null);

    let mensajesCh: mensajesChat[] = [];

    let navigate = useNavigate();

    const usuariosArr: string[] = [];
    

    const token = localStorage.getItem("token") as string;
    const headers = {
        headers: { Authorization: token }
    };



    function FormatearFecha(fecha: Date) {
        let month = fecha.getMonth() + 1;
        let monthStr = month + "";
        if (month < 10) {
            monthStr = "0" + month;
        }
        let date = fecha.getDate();
        let dateStr = date + "";
        if (date < 10) {
            dateStr = "0" + date;
        }
        let hours = fecha.getHours();
        let hoursStr = hours + "";
        if (hours < 10) {
            hoursStr = "0" + hours;
        }
        let minutes = fecha.getMinutes();
        let minutesStr = minutes + "";
        if (minutes < 10) {
            minutesStr = "0" + minutes;
        }
        let fechaStr = dateStr + "/" + monthStr + "/" + fecha.getFullYear() + " " + hoursStr + ":" + minutesStr;
        return fechaStr;
    }

    const sendMessage = async (event: React.FormEvent<HTMLFormElement>) => {

        event.preventDefault();
        let formulario: HTMLFormElement = event.currentTarget;

        cargarChat(stUser.usuario!);
        let textoMens = textoMensaje.current?.value;

        const newMensaje = new mensajes(1, textoMens!, new Date(), stUser.usuario!, stDestinatario.usuario!);

        console.log("Mensaje de " + usuariosArr[0]);

        const newMensajeChat = new mensajesChat(textoMens!, new Date(), true, stUser.usuario!,  usuariosArr[0] + "///" + usuariosArr[1]);

        await socket.emit("send_message", newMensajeChat);
        let mensajesCh = stMensajes.mensajesCh;
        mensajesCh!.push(newMensajeChat);
        setStMensajes({ mensajesCh });

        let rutaMensaje = "http://localhost:8080/api/v1/usuario/"+ stUser.usuario?.id +"/mensajes";
        const axiospost = async (rutaMensaje: string) => {
            try {
                const { data } = await axios.post(rutaMensaje, newMensaje, headers);
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }
        axiospost(rutaMensaje).then(respuesta => {
            navigate('.');
        });
    }

    

    

    useEffect(() => {

        const getUser = async (id: number) => {
            let rutadeUsuarios = "http://localhost:8080/api/v0/usuario/";
            let { data } = await axios.get(rutadeUsuarios + id);
            let usuario: usuarios = data;
            console.log(usuario);
            setStUser({ usuario });
            usuariosArr.push(usuario.nombre);
            console.log(usuario.nombre);
        }
        const getDestinario = async (id: string) => {
            let rutadeUsuarios = "http://localhost:8080/api/v0/usuario/";
            let { data } = await axios.get(rutadeUsuarios + id);
            let usuario: usuarios = data;
            
            setStDestinatario({ usuario });
            usuariosArr.push(usuario.nombre);
            await new Promise(r => setTimeout(r, 2000));
            usuariosArr.sort((a, b) => a.localeCompare(b));
            joinRoom();
            await new Promise(r => setTimeout(r, 4000));
            cargarChat(stUser.usuario!);
            
        }
        const joinRoom = () => {
            socket.emit("join_room", usuariosArr[0] + "///" + usuariosArr[1]);
        }
        getUser(usuarioActual.id);
        getDestinario(id!);
        
        
        
        
        
    },
        []
    );

    useEffect(() => {
        let mensajesCh = stMensajes.mensajesCh;
        socket.on("receive_message", (mensaje: mensajesChat) => {
            console.log(mensaje+ "mensaje");
            console.log("stmensajes:" + stMensajes.mensajesCh);
            let mensajeCh = new mensajesChat(mensaje.contenido, new Date(mensaje.fecha), false, mensaje.autor, mensaje.room);
            mensajesCh?.push(mensajeCh);
            setStMensajes({ mensajesCh });
        });
    }, [socket]);

    function cargarChat(usuario: usuarios) {
        usuario.mensajesEnviados?.map((m: mensajes) => {
            if (m.destinatario.id + "" === id) {
                mensajesCh.push(new mensajesChat(m.contenido, new Date(m.fecha), true, stUser.usuario!, ""));
            }   
        });
    
        usuario.mensajesRecibidos?.map((m: mensajes) => {
            if (m.autor.id + "" === id) {
                mensajesCh.push(new mensajesChat(m.contenido, new Date(m.fecha), false, stDestinatario.usuario!, ""));
            }   
        });
    
    
        mensajesCh.sort((a, b) => (a.fecha.getTime()) - (b.fecha.getTime()));
        setStMensajes({ mensajesCh });
    }

    return (
        <div className="cuerpo">
        <Topbar />
        <Link to='/chat' style={{ textDecoration: "none" }}>
            <ArrowBack/>
        </Link>
        <div className="contenedorMensajes">
        {
        stMensajes.mensajesCh?.map((m: mensajesChat) => {
            if (m.enviado) {
                return (
                    <div className='mensajeEnviado'>
                        <div className='fecha'>{FormatearFecha(m.fecha)}</div>
                        <div className='contenidoMensajeEnviado'>{m.contenido}</div>
                    </div>
                )
            } else {
                return (
                    <div className='mensajeRecibido'>
                        
                        <div className='contenidoMensajeRecibido'>{m.contenido}</div>
                        <div className='fecha'>{FormatearFecha(m.fecha)}</div>
                    </div>
                )
            }
    })}
    </div>
    
    <form onSubmit={sendMessage} className='formReview'>
        <div className='formularioMensaje'>
            <textarea ref={textoMensaje} cols={130} rows={3} required /><br />
            <button type="submit" className="buttonForm"><Send/></button>
        </div>
    </form>
    
            
        </div>
    )
}