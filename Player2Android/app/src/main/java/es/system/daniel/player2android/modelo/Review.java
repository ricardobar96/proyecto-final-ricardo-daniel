package es.system.daniel.player2android.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Review {
    private int id;

    private String contenido;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fecha;

    private String titulo;

    private Usuario usuario;

    private Videojuego videojuego;

    public Review(){}

    public Review(String contenido, Date fecha, String titulo, Usuario usuario, Videojuego videojuego) {
        this.contenido = contenido;
        this.fecha = fecha;
        this.titulo = titulo;
        this.usuario = usuario;
        this.videojuego = videojuego;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
}
