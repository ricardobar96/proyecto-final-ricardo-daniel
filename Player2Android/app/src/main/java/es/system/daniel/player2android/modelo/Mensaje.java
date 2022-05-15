package es.system.daniel.player2android.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Mensaje {
    private int id;

    private String contenido;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fecha;

    private Usuario autor;

    private Usuario destinatario;

    public Mensaje(){}

    public Mensaje(String contenido, Date fecha, Usuario autor, Usuario destinatario) {
        this.contenido = contenido;
        this.fecha = fecha;
        this.autor = autor;
        this.destinatario = destinatario;
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

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }
}
