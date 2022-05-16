package es.system.daniel.player2android.modelo;

public class UsuarioInscribirse {
    private String name, password;
    public UsuarioInscribirse() {}

    public UsuarioInscribirse(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
