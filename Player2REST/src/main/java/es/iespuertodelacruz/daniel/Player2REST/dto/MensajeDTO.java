package es.iespuertodelacruz.daniel.Player2REST.dto;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import es.iespuertodelacruz.daniel.Player2REST.entity.Mensaje;
import es.iespuertodelacruz.daniel.Player2REST.entity.Usuario;

public class MensajeDTO {
	private int id;

	private String contenido;

	private Date fecha;

	private Usuario autor;

	private Usuario destinatario;
	public MensajeDTO() {}
	public MensajeDTO(Mensaje mensaje) {
		this.id = mensaje.getId();
		this.contenido = mensaje.getContenido();
		this.fecha = new Date(mensaje.getFecha().longValue());
		this.autor = mensaje.getAutor();
		this.destinatario = mensaje.getDestinatario();
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
	public Usuario getAutor() {
		return autor;
	}
	public void setAutor(Usuario autor) {
		this.autor = autor;
	}
	public Usuario getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(Usuario destinatario) {
		this.destinatario = destinatario;
	}
	
	
}
