package es.iespuertodelacruz.daniel.Player2REST.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import es.iespuertodelacruz.daniel.Player2REST.entity.JuegoUsuario;
import es.iespuertodelacruz.daniel.Player2REST.entity.Mensaje;
import es.iespuertodelacruz.daniel.Player2REST.entity.Pista;
import es.iespuertodelacruz.daniel.Player2REST.entity.Review;
import es.iespuertodelacruz.daniel.Player2REST.entity.Usuario;

public class UsuarioFollDTO {
	private int id;

	private boolean activo;

	private String avatar;

	private String banner;

	private String color;

	private String descripcion;

	private String nombre;

	private String rol;

	private List<JuegoUsuarioDTOUser> juegoUsuarios = new ArrayList<>();
	private List<PistaDTOUser> pistas = new ArrayList<>();
	

	private List<ReviewDTOUser> reviews = new ArrayList<>();
	
	private List<MensajeDTOUser> mensajesEnviados = new ArrayList<>();
	
	private List<MensajeDTOUser> mensajesRecibidos = new ArrayList<>();
	public UsuarioFollDTO() {}
	
	public UsuarioFollDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.activo = (usuario.getActivo() == 1) ? true : false;
		this.avatar = usuario.getAvatar();
		this.banner = usuario.getBanner();
		this.color = usuario.getColor();
		this.descripcion = usuario.getDescripcion();
		this.nombre = usuario.getNombre();
		this.rol = usuario.getRol();
		
		if (usuario.getJuegoUsuarios() != null) {
			for (JuegoUsuario juegoUsuario : usuario.getJuegoUsuarios()) {
				if (juegoUsuario != null) {
					this.juegoUsuarios.add(new JuegoUsuarioDTOUser(juegoUsuario));
				}
				
			}
		}
		if (usuario.getPistas() != null) {
			for (Pista pista : usuario.getPistas()) {
				this.pistas.add(new PistaDTOUser(pista));
			}
		}
		
		if (usuario.getReviews() != null) {
			for (Review review : usuario.getReviews()) {
				this.reviews.add(new ReviewDTOUser(review));
			}
		}
		
		
		if (usuario.getMensajesEnviados() != null) {
			for (Mensaje mensaje : usuario.getMensajesEnviados()) {
				this.mensajesEnviados.add(new MensajeDTOUser(mensaje));
			}
		}
		
		if (usuario.getMensajesRecibidos() != null) {
			for (Mensaje mensaje : usuario.getMensajesRecibidos()) {
				this.mensajesRecibidos.add(new MensajeDTOUser(mensaje));
			}
		}
		
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

	public List<JuegoUsuarioDTOUser> getJuegoUsuarios() {
		return juegoUsuarios;
	}

	public void setJuegoUsuarios(List<JuegoUsuarioDTOUser> juegoUsuarios) {
		this.juegoUsuarios = juegoUsuarios;
	}

	public List<PistaDTOUser> getPistas() {
		return pistas;
	}

	public void setPistas(List<PistaDTOUser> pistas) {
		this.pistas = pistas;
	}

	public List<ReviewDTOUser> getReviews() {
		return reviews;
	}

	public void setReviews(List<ReviewDTOUser> reviews) {
		this.reviews = reviews;
	}

	public List<MensajeDTOUser> getMensajesEnviados() {
		return mensajesEnviados;
	}

	public void setMensajesEnviados(List<MensajeDTOUser> mensajesEnviados) {
		this.mensajesEnviados = mensajesEnviados;
	}

	public List<MensajeDTOUser> getMensajesRecibidos() {
		return mensajesRecibidos;
	}

	public void setMensajesRecibidos(List<MensajeDTOUser> mensajesRecibidos) {
		this.mensajesRecibidos = mensajesRecibidos;
	}


	
	
	
}
