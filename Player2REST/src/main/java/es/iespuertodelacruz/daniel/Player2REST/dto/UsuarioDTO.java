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
}
