package es.system.daniel.player2android.connection;

import java.util.List;

import es.system.daniel.player2android.modelo.Mensaje;
import es.system.daniel.player2android.modelo.Usuario;
import es.system.daniel.player2android.modelo.UsuarioInscribirse;
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

public interface UsuarioService {

    //@Headers("Content-Type: multipart/form-data")
    @Headers("Accept: application/json")
    @POST("login")
    Call<String> login(@Body UsuarioInscribirse usuario);

    @GET("v0/usuario")
    Call<List<Usuario>> getUsuarios();

    @GET("v0/usuario/{id}")
    Call<Usuario> getUsuarioById(@Path("id") Integer usuarioId);

    @GET("v1/usuario/{id}/mensajes")
    Call<List<Mensaje>> getMensajes(@Path("id") Integer usuarioId, @Header("Authorization") String authHeader);

    @Headers("Content-Type: application/json")
    @POST("v0/usuario")
    Call<Usuario> addUsuario(@Body UsuarioRegistro usuario);

    @PUT("v1/usuario/{id}")
    Call<Usuario> updateUsuario(@Path("id") Integer id, @Body Usuario usuario, @Header("Authorization") String authHeader);

    @DELETE("v1/usuario/{id}")
    Call<Usuario> deleteUsuario(@Path("id") Integer id, @Header("Authorization") String authHeader);
}