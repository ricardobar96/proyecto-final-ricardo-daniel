package es.iespuertodelacruz.daniel.Player2REST.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the mensajes database table.
 * 
 */
@Entity
@Table(name="mensajes")
@NamedQuery(name="Mensaje.findAll", query="SELECT m FROM Mensaje m")
public class Mensaje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private String contenido;

	private BigInteger fecha;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idautor")
	private Usuario autor;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="iddestinatario")
	private Usuario destinatario;

	public Mensaje() {
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

	public Usuario getAutor() {
		return this.autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public Usuario getDestinatario() {
		return this.destinatario;
	}

	public void setDestinatario(Usuario destinatario) {
		this.destinatario = destinatario;
	}

}