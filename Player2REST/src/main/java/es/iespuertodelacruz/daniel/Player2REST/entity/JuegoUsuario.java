package es.iespuertodelacruz.daniel.Player2REST.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the juego_usuario database table.
 * 
 */
@Entity
@Table(name="juego_usuario")
@NamedQuery(name="JuegoUsuario.findAll", query="SELECT j FROM JuegoUsuario j")
public class JuegoUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private byte completado;

	private int horas;

	//bi-directional many-to-one association to Videojuego
	@ManyToOne
	@JoinColumn(name="idvideojuego")
	private Videojuego videojuego;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idusuario")
	private Usuario usuario;

	public JuegoUsuario() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getCompletado() {
		return this.completado;
	}

	public void setCompletado(byte completado) {
		this.completado = completado;
	}

	public int getHoras() {
		return this.horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public Videojuego getVideojuego() {
		return this.videojuego;
	}

	public void setVideojuego(Videojuego videojuego) {
		this.videojuego = videojuego;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}