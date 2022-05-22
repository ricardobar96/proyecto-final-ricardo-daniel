package es.system.daniel.player2android.modelo;

import java.util.Date;

public class Actividad implements Comparable<Actividad> {
    public String tipo;
    public Date fecha;
    public Usuario usuario;
    public Videojuego videojuego;

    public Actividad() {
    }

    public Actividad(String tipo, Date fecha, Usuario usuario, Videojuego videojuego) {
        this.tipo = tipo;
        this.fecha = fecha;
        this.usuario = usuario;
        this.videojuego = videojuego;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Videojuego getVideojuego() {
        return videojuego;
    }

    public void setVideojuego(Videojuego videojuego) {
        this.videojuego = videojuego;
    }

    @Override
    public int compareTo(Actividad o) {
        return getFecha().compareTo(o.getFecha());
    }
}
