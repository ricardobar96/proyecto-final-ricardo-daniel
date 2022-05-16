package es.system.daniel.player2android.connection;


import es.system.daniel.player2android.connection.retrofit.RetrofitClient;

public class APIUtils {

    private APIUtils(){
    };

    public static final String API_URL = "http://192.168.0.209:8080/api/";

    public static UsuarioService getUsuarioService(){
        return RetrofitClient.getClient(API_URL).create(UsuarioService.class);
    }

}
