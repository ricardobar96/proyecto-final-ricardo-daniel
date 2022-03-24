package es.iespuertodelacruz.daniel.Player2REST.dto;

import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import es.iespuertodelacruz.daniel.Player2REST.entity.JuegoUsuario;
import es.iespuertodelacruz.daniel.Player2REST.entity.Pista;
import es.iespuertodelacruz.daniel.Player2REST.entity.Review;
import es.iespuertodelacruz.daniel.Player2REST.entity.Usuario;

public class UsuarioDTO {
	private int id;

	private boolean activo;

	private String avatar;

	private String banner;

	private String color;

	private String descripcion;

	private String nombre;

	private String rol;

	private List<JuegoUsuario> juegoUsuarios;
	private List<Pista> pistas;

	private List<Review> reviews;

	private List<Usuario> followers;

	private List<Usuario> followeds;
	public UsuarioDTO() {}
	
	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.activo = (usuario.getActivo() == 1) ? true : false;
		this.avatar = usuario.getAvatar();
		this.banner = usuario.getBanner();
		this.color = usuario.getColor();
		this.descripcion = usuario.getDescripcion();
		this.nombre = usuario.getNombre();
		this.rol = usuario.getRol();
		this.juegoUsuarios = usuario.getJuegoUsuarios();
		this.pistas = usuario.getPistas();
		this.reviews = usuario.getReviews();
		this.followers = usuario.getFollowers();
		this.followeds = usuario.getFolloweds();
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

	public List<Usuario> getFollowers() {
		return followers;
	}

	public void setFollowers(List<Usuario> followers) {
		this.followers = followers;
	}

	public List<Usuario> getFolloweds() {
		return followeds;
	}

	public void setFolloweds(List<Usuario> followeds) {
		this.followeds = followeds;
	}
	
}
