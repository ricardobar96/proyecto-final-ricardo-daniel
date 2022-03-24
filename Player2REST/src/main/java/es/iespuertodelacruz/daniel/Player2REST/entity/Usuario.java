package es.iespuertodelacruz.daniel.Player2REST.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import javax.persistence.JoinColumn;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private byte activo;

	private String avatar;

	private String banner;

	private String color;

	private String descripcion;

	private String nombre;

	private String password;

	private String rol;

	//bi-directional many-to-one association to JuegoUsuario
	@OneToMany(mappedBy="usuario")
	private List<JuegoUsuario> juegoUsuarios;

	//bi-directional many-to-one association to Pista
	@OneToMany(mappedBy="usuario")
	private List<Pista> pistas;

	//bi-directional many-to-one association to Review
	@OneToMany(mappedBy="usuario")
	private List<Review> reviews;

	//bi-directional many-to-many association to Usuario
	@ManyToMany
	@JoinTable( name="follow",
		joinColumns = @JoinColumn(name="idusuariofollower"),
		inverseJoinColumns = @JoinColumn(name="idusuariofollowed")
	)
	private List<Usuario> followers;

	//bi-directional many-to-many association to Usuario
	@ManyToMany(mappedBy="followers")
	private List<Usuario> followeds;

	public Usuario() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getActivo() {
		return this.activo;
	}

	public void setActivo(byte activo) {
		this.activo = activo;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getBanner() {
		return this.banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public List<JuegoUsuario> getJuegoUsuarios() {
		return this.juegoUsuarios;
	}

	public void setJuegoUsuarios(List<JuegoUsuario> juegoUsuarios) {
		this.juegoUsuarios = juegoUsuarios;
	}

	public JuegoUsuario addJuegoUsuario(JuegoUsuario juegoUsuario) {
		getJuegoUsuarios().add(juegoUsuario);
		juegoUsuario.setUsuario(this);

		return juegoUsuario;
	}

	public JuegoUsuario removeJuegoUsuario(JuegoUsuario juegoUsuario) {
		getJuegoUsuarios().remove(juegoUsuario);
		juegoUsuario.setUsuario(null);

		return juegoUsuario;
	}

	public List<Pista> getPistas() {
		return this.pistas;
	}

	public void setPistas(List<Pista> pistas) {
		this.pistas = pistas;
	}

	public Pista addPista(Pista pista) {
		getPistas().add(pista);
		pista.setUsuario(this);

		return pista;
	}

	public Pista removePista(Pista pista) {
		getPistas().remove(pista);
		pista.setUsuario(null);

		return pista;
	}

	public List<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Review addReview(Review review) {
		getReviews().add(review);
		review.setUsuario(this);

		return review;
	}

	public Review removeReview(Review review) {
		getReviews().remove(review);
		review.setUsuario(null);

		return review;
	}

	public List<Usuario> getFollowers() {
		return this.followers;
	}

	public void setFollowers(List<Usuario> followers) {
		this.followers = followers;
	}

	public List<Usuario> getFolloweds() {
		return this.followeds;
	}

	public void setFolloweds(List<Usuario> followeds) {
		this.followeds = followeds;
	}

}