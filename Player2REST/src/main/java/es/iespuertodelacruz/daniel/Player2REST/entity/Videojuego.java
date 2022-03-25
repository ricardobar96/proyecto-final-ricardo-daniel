package es.iespuertodelacruz.daniel.Player2REST.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the videojuegos database table.
 * 
 */
@Entity
@Table(name="videojuegos")
@NamedQuery(name="Videojuego.findAll", query="SELECT v FROM Videojuego v")
public class Videojuego implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Lob
	private String descripcion;

	private BigInteger fecha;

	private String nombre;
	
	private String imagen;


	@JsonIgnore
	//bi-directional many-to-one association to JuegoUsuario
	@OneToMany(mappedBy="videojuego")
	private List<JuegoUsuario> juegoUsuarios;

	
	//bi-directional many-to-one association to Pista
	@OneToMany(mappedBy="videojuego")
	private List<Pista> pistas;

	//bi-directional many-to-one association to Review
	@OneToMany(mappedBy="videojuego")
	private List<Review> reviews;

	//bi-directional many-to-many association to Genero
	@ManyToMany
	@JoinTable( name="videojuego_genero",
	joinColumns = @JoinColumn(name="idvideojuego"),
	inverseJoinColumns = @JoinColumn(name="idgenero")
)
	private List<Genero> generos;

	public Videojuego() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigInteger getFecha() {
		return this.fecha;
	}

	public void setFecha(BigInteger fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public List<JuegoUsuario> getJuegoUsuarios() {
		return this.juegoUsuarios;
	}

	public void setJuegoUsuarios(List<JuegoUsuario> juegoUsuarios) {
		this.juegoUsuarios = juegoUsuarios;
	}

	public JuegoUsuario addJuegoUsuario(JuegoUsuario juegoUsuario) {
		getJuegoUsuarios().add(juegoUsuario);
		juegoUsuario.setVideojuego(this);

		return juegoUsuario;
	}

	public JuegoUsuario removeJuegoUsuario(JuegoUsuario juegoUsuario) {
		getJuegoUsuarios().remove(juegoUsuario);
		juegoUsuario.setVideojuego(null);

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
		pista.setVideojuego(this);

		return pista;
	}

	public Pista removePista(Pista pista) {
		getPistas().remove(pista);
		pista.setVideojuego(null);

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
		review.setVideojuego(this);

		return review;
	}

	public Review removeReview(Review review) {
		getReviews().remove(review);
		review.setVideojuego(null);

		return review;
	}

	public List<Genero> getGeneros() {
		return this.generos;
	}

	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}

}