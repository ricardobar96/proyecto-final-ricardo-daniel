package es.iespuertodelacruz.daniel.Player2REST.dto;

import java.math.BigInteger;
import java.util.Date;

import es.iespuertodelacruz.daniel.Player2REST.entity.Review;
import es.iespuertodelacruz.daniel.Player2REST.entity.Usuario;
import es.iespuertodelacruz.daniel.Player2REST.entity.Videojuego;

public class ReviewDTO {

	private int id;

	private String contenido;

	private Date fecha;

	private String titulo;

	private Usuario usuario;

	private Videojuego videojuego;
	
	public ReviewDTO() {}
	
	public ReviewDTO(Review review) {
		this.id = review.getId();
		this.contenido = review.getContenido();
		this.fecha = new Date(review.getFecha().longValue());
		this.titulo = review.getTitulo();
		this.usuario = review.getUsuario();
		this.videojuego = review.getVideojuego();
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
