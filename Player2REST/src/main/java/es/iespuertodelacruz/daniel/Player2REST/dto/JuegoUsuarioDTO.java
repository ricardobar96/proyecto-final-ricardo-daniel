package es.iespuertodelacruz.daniel.Player2REST.dto;


import es.iespuertodelacruz.daniel.Player2REST.entity.JuegoUsuario;
import es.iespuertodelacruz.daniel.Player2REST.entity.Usuario;
import es.iespuertodelacruz.daniel.Player2REST.entity.Videojuego;

public class JuegoUsuarioDTO {
	private int id;
	
	private boolean completado;
	
	private int horas;
	
	private VideojuegoDTO videojuego;

	private UsuarioDTO usuario;
	
	private int puntuacion;
	
	public JuegoUsuarioDTO() {}
	
	public JuegoUsuarioDTO(JuegoUsuario juegoUsuario) {
		this.id = juegoUsuario.getId();
		this.completado = juegoUsuario.getCompletado() == 1 ? true : false;
		this.horas = juegoUsuario.getHoras();
		this.videojuego = new VideojuegoDTO(juegoUsuario.getVideojuego());
		this.usuario = new UsuarioDTO(juegoUsuario.getUsuario());
		this.puntuacion = juegoUsuario.getPuntuacion();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isCompletado() {
		return completado;
	}

	public void setCompletado(boolean completado) {
		this.completado = completado;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public VideojuegoDTO getVideojuego() {
		return videojuego;
	}

	public void setVideojuego(VideojuegoDTO videojuego) {
		this.videojuego = videojuego;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	
}
