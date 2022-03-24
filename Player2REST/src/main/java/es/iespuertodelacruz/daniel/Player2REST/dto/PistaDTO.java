package es.iespuertodelacruz.daniel.Player2REST.dto;

import java.math.BigInteger;
import java.util.Date;

import es.iespuertodelacruz.daniel.Player2REST.entity.Pista;
import es.iespuertodelacruz.daniel.Player2REST.entity.Usuario;
import es.iespuertodelacruz.daniel.Player2REST.entity.Videojuego;

public class PistaDTO {
	private int id;

	private String contenido;

	private Date fecha;

	private String titulo;

	private Videojuego videojuego;
	
	private Usuario usuario;
	
	public PistaDTO() {}
	
	public PistaDTO(Pista pista) {
		this.id = pista.getId();
		this.contenido = pista.getContenido();
		this.fecha = new Date(pista.getFecha().longValue());
		this.titulo = pista.getTitulo();
		this.videojuego = pista.getVideojuego();
		this.usuario = pista.getUsuario();
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

	public Videojuego getVideojuego() {
		return videojuego;
	}

	public void setVideojuego(Videojuego videojuego) {
		this.videojuego = videojuego;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
