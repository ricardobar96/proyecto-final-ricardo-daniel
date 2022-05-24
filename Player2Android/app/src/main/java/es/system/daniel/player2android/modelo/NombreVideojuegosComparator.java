package es.system.daniel.player2android.modelo;

import java.util.Comparator;

public class NombreVideojuegosComparator implements Comparator<Videojuego> {
    @Override
    public int compare(Videojuego videojuegoUno, Videojuego videojuegoDos) {
        return videojuegoUno.getNombre().compareTo(videojuegoDos.getNombre());
    }
}