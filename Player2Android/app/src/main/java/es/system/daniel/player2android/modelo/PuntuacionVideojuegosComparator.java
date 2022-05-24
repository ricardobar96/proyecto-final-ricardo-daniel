package es.system.daniel.player2android.modelo;

import java.util.Comparator;

public class PuntuacionVideojuegosComparator implements Comparator<Videojuego> {
    @Override
    public int compare(Videojuego videojuegoUno, Videojuego videojuegoDos) {
        return Float.compare(videojuegoUno.getPuntuacion(), videojuegoDos.getPuntuacion());
    }
}
