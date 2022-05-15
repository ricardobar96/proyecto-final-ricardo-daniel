package es.system.daniel.player2android.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class Videojuego {
    private int id;

    private String descripcion;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fecha;

    private String nombre;

    private String imagen;

    private List<JuegoUsuario> juegoUsuarios;

    private List<Pista> pistas;

    private List<Review> reviews;

    private List<Genero> generos;

    private float puntuacion;

    public Videojuego(){}

    public Videojuego(String descripcion, Date fecha, String nombre, String imagen,
                      List<JuegoUsuario> juegoUsuarios, List<Pista> pistas, List<Review> reviews,
                      List<Genero> generos, float puntuacion) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.nombre = nombre;
        this.imagen = imagen;
        this.juegoUsuarios = juegoUsuarios;
        this.pistas = pistas;
        this.reviews = reviews;
        this.generos = generos;
        this.puntuacion = puntuacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<JuegoUsuario> getJuegoUsuarios() {
        return juegoUsuarios;
    }

    public void setJuegoUsuarios(List<JuegoUsuario> juegoUsuarios) {
        this.juegoUsuarios = juegoUsuarios;
    }

    public List<Pista> getPistas() {
        return pistas;
    }

    public void setPistas(List<Pista> pistas) {
        this.pistas = pistas;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public float getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(float puntuacion) {
        this.puntuacion = puntuacion;
    }
}
