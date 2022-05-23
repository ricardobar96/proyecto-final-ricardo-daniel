package es.system.daniel.player2android.connection;

import java.util.List;

import es.system.daniel.player2android.modelo.JuegoUsuario;
import es.system.daniel.player2android.modelo.Usuario;
import es.system.daniel.player2android.modelo.UsuarioRegistro;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JuegoUsuarioService {
    @GET("v0/juegousuario")
    Call<List<JuegoUsuario>> getJuegoUsuario();
    @Headers({"Accept: application/json"})
    @POST("v1/juegousuario")
    Call<JuegoUsuario> addJuegoUsuario(@Body JuegoUsuario juegoUsuario, @Header("Authorization") String authHeader);

    @PUT("v1/juegousuario/{id}")
    Call<JuegoUsuario> updateJuegoUsuario(@Path("id") Integer id, @Body JuegoUsuario juegoUsuario, @Header("Authorization") String authHeader);

    @DELETE("v1/juegousuario/{id}")
    Call<String> deleteJuegoUsuario(@Path("id") Integer id, @Header("Authorization") String authHeader);
}
