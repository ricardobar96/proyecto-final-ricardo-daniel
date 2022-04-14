package es.iespuertodelacruz.daniel.Player2REST.dto;

import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.iespuertodelacruz.daniel.Player2REST.entity.Pista;

public class PistaDTOUser {
	private int id;

	private String contenido;

	private Date fecha;

	private String titulo;

	private VideojuegoDTO videojuego;
	
	private UsuarioDTO usuario;
	
	public PistaDTOUser() {}
	
	public PistaDTOUser(Pista pista) {
		this.id = pista.getId();
		this.contenido = pista.getContenido();
		this.fecha = new Date(pista.getFecha().longValue());
		this.titulo = pista.getTitulo();
		this.videojuego = new VideojuegoDTO(pista.getVideojuego());
		//this.usuario = new UsuarioDTO(pista.getUsuario());
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

	public VideojuegoDTO getVideojuego() {
		return videojuego;
	}

	public void setVideojuego(VideojuegoDTO videojuego) {
		this.videojuego = videojuego;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	
}
