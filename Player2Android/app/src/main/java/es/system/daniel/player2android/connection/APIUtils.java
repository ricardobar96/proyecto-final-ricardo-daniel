package es.system.daniel.player2android.connection;


import es.system.daniel.player2android.connection.retrofit.RetrofitClient;

public class APIUtils {

    private APIUtils(){
    };

    public static final String API_URL = "http://192.168.0.209:8080/api/";

    public static UsuarioService getUsuarioService(){
        return RetrofitClient.getClient(API_URL).create(UsuarioService.class);
    }

    public static JuegoUsuarioService getJuegoUsuarioService(){
        return RetrofitClient.getClient(API_URL).create(JuegoUsuarioService.class);
    }
    public static ReviewService getReviewService(){
        return RetrofitClient.getClient(API_URL).create(ReviewService.class);
    }
    public static PistaService getPistaService(){
        return RetrofitClient.getClient(API_URL).create(PistaService.class);
    }
    public static GameService getGameService(){
        return RetrofitClient.getClient(API_URL).create(GameService.class);
    }
    public static GeneroService getGeneroService() {
        return RetrofitClient.getClient(API_URL).create(GeneroService.class);
    }
}
