package es.system.daniel.player2android.connection;

import java.util.List;

import es.system.daniel.player2android.modelo.Genero;
import es.system.daniel.player2android.modelo.Videojuego;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GeneroService {
    @GET("v0/genero")
    Call<List<Genero>> getGeneros();
}
