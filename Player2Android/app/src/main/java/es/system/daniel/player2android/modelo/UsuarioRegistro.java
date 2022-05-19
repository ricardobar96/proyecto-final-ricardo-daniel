package es.system.daniel.player2android.modelo;

public class UsuarioRegistro {
    private String nombre, password;

    public UsuarioRegistro() {
    }

    public UsuarioRegistro(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
