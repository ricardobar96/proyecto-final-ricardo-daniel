package es.iespuertodelacruz.daniel.Player2REST.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigInteger;


/**
 * The persistent class for the pistas database table.
 * 
 */
@Entity
@Table(name="pistas")
@NamedQuery(name="Pista.findAll", query="SELECT p FROM Pista p")
public class Pista implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private String contenido;

	private BigInteger fecha;

	private String titulo;

	@JsonIgnore
	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idusuario")
	private Usuario usuario;

	@JsonIgnore
	//bi-directional many-to-one association to Videojuego
	@ManyToOne
	@JoinColumn(name="idvideojuego")
	private Videojuego videojuego;

	public Pista() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContenido() {
		return this.contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public BigInteger getFecha() {
		return this.fecha;
	}

	public void setFecha(BigInteger fecha) {
		this.fecha = fecha;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Videojuego getVideojuego() {
		return this.videojuego;
	}

	public void setVideojuego(Videojuego videojuego) {
		this.videojuego = videojuego;
	}

}