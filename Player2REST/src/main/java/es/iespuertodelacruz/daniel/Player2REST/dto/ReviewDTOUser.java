package es.iespuertodelacruz.daniel.Player2REST.dto;

import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.iespuertodelacruz.daniel.Player2REST.entity.Review;
import es.iespuertodelacruz.daniel.Player2REST.entity.Usuario;
import es.iespuertodelacruz.daniel.Player2REST.entity.Videojuego;

public class ReviewDTOUser {

	private int id;

	private String contenido;

	private Date fecha;

	private String titulo;

	private UsuarioDTO usuario;

	private VideojuegoDTO videojuego;
	
	public ReviewDTOUser() {}
	
	public ReviewDTOUser(Review review) {
		this.id = review.getId();
		this.contenido = review.getContenido();
		this.fecha = new Date(review.getFecha().longValue());
		this.titulo = review.getTitulo();
		//this.usuario = new UsuarioDTO(review.getUsuario());
		this.videojuego = new VideojuegoDTO(review.getVideojuego());
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

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public VideojuegoDTO getVideojuego() {
		return videojuego;
	}

	public void setVideojuego(VideojuegoDTO videojuego) {
		this.videojuego = videojuego;
	}
	
	
}
