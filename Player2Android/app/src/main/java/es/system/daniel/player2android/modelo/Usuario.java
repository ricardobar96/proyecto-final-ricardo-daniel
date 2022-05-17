package es.system.daniel.player2android.modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;

    private boolean activo;

    private String avatar;

    private String banner;

    private String color;

    private String descripcion;

    private String nombre;

    private String rol;

    private List<JuegoUsuario> juegoUsuarios = new ArrayList<>();
    private List<Pista> pistas = new ArrayList<>();


    private List<Review> reviews = new ArrayList<>();

    private List<Usuario> followers = new ArrayList<>();

    private List<Usuario> followeds = new ArrayList<>();

    private List<Mensaje> mensajesEnviados = new ArrayList<>();

    private List<Mensaje> mensajesRecibidos = new ArrayList<>();

    public Usuario(){}

    public Usuario(boolean activo, String avatar, String banner, String color, String descripcion,
                   String nombre, String rol, List<JuegoUsuario> juegoUsuarios, List<Pista> pistas,
                   List<Review> reviews, List<Usuario> followers, List<Usuario> followeds,
                   List<Mensaje> mensajesEnviados, List<Mensaje> mensajesRecibidos) {
        this.activo = activo;
        this.avatar = avatar;
        this.banner = banner;
        this.color = color;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.rol = rol;
        this.juegoUsuarios = juegoUsuarios;
        this.pistas = pistas;
        this.reviews = reviews;
        this.followers = followers;
        this.followeds = followeds;
        this.mensajesEnviados = mensajesEnviados;
        this.mensajesRecibidos = mensajesRecibidos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
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

    public List<Usuario> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Usuario> followers) {
        this.followers = followers;
    }

    public List<Usuario> getFolloweds() {
        return followeds;
    }

    public void setFolloweds(List<Usuario> followeds) {
        this.followeds = followeds;
    }

    public List<Mensaje> getMensajesEnviados() {
        return mensajesEnviados;
    }

    public void setMensajesEnviados(List<Mensaje> mensajesEnviados) {
        this.mensajesEnviados = mensajesEnviados;
    }

    public List<Mensaje> getMensajesRecibidos() {
        return mensajesRecibidos;
    }

    public void setMensajesRecibidos(List<Mensaje> mensajesRecibidos) {
        this.mensajesRecibidos = mensajesRecibidos;
    }
}
