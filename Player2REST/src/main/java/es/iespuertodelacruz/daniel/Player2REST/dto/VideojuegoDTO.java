package es.iespuertodelacruz.daniel.Player2REST.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import es.iespuertodelacruz.daniel.Player2REST.entity.Genero;
import es.iespuertodelacruz.daniel.Player2REST.entity.JuegoUsuario;
import es.iespuertodelacruz.daniel.Player2REST.entity.Pista;
import es.iespuertodelacruz.daniel.Player2REST.entity.Review;
import es.iespuertodelacruz.daniel.Player2REST.entity.Videojuego;

public class VideojuegoDTO {
	private int id;

	private String descripcion;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fecha;

	private String nombre;
	
	private String imagen;

	private float puntuacion;

	private List<JuegoUsuario> juegoUsuarios;

	private List<Pista> pistas;

	private List<Review> reviews;

	private List<Genero> generos;
	
	public VideojuegoDTO() {}
	
	public VideojuegoDTO(Videojuego videojuego) {
		this.id = videojuego.getId();
		this.descripcion = videojuego.getDescripcion();
		this.nombre = videojuego.getNombre();
		this.imagen = videojuego.getImagen();
		this.puntuacion = videojuego.getPuntuacion();
		this.generos = videojuego.getGeneros();
		this.juegoUsuarios = videojuego.getJuegoUsuarios();
		this.pistas = videojuego.getPistas();
		this.reviews = videojuego.getReviews();
		this.generos = videojuego.getGeneros();
		if (videojuego.getFecha() != null) {
			Date fecha = new Date(videojuego.getFecha().longValue());
			this.fecha = fecha;
		}
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

	public float getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(float puntuacion) {
		this.puntuacion = puntuacion;
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
	
	
}
