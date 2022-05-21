package es.system.daniel.player2android.connection;

import java.util.List;

import es.system.daniel.player2android.modelo.Videojuego;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GameService {
    @GET("v0/videojuego")
    Call<List<Videojuego>> getGames();
}
