package es.system.daniel.player2android.activities;

import androidx.appcompat.app.AppCompatActivity;
import es.system.daniel.player2android.R;
import es.system.daniel.player2android.connection.APIUtils;
import es.system.daniel.player2android.connection.ReviewService;
import es.system.daniel.player2android.modelo.Actividad;
import es.system.daniel.player2android.modelo.JuegoUsuario;
import es.system.daniel.player2android.modelo.Review;
import es.system.daniel.player2android.modelo.Usuario;
import es.system.daniel.player2android.modelo.Videojuego;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

public class CrearReviewActivity extends AppCompatActivity {

    String token;
    Videojuego videojuego = new Videojuego();
    ReviewService reviewService;
    EditText tituloEditText;
    EditText contenidoEditText;
    Usuario usuarioActual = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_review);
        SharedPreferences preferences = getSharedPreferences("usuario",
                Context.MODE_PRIVATE);
        token = preferences.getString("token", "");
        int usuarioId = preferences.getInt("usuarioId", 0);
        usuarioActual.setId(usuarioId);
        Videojuego videojuegoIn = (Videojuego) getIntent().getSerializableExtra("videojuego");
        videojuego.setId(videojuegoIn.getId());
        reviewService = APIUtils.getReviewService();
        tituloEditText = (EditText) findViewById(R.id.tituloReviewEditText);
        contenidoEditText = (EditText) findViewById(R.id.textoReviewEditText);


    }


    public void crearReview (View view) {
        String titulo = tituloEditText.getText().toString();
        String contenido = contenidoEditText.getText().toString();
        Log.i("Título:", titulo);
        Log.i("Contenido:", contenido);
        Log.i("Usuario:", usuarioActual.getId()+"");
        Log.i("Videojuego:", videojuego.getId()+"");
        Log.i("Token:", token);
        Call<Review> call = reviewService.addReview(new Review(
                contenido, null, titulo, usuarioActual, videojuego), token);
        call.enqueue(new Callback<Review>() {
            @Override
            public void onResponse(Call<Review> call, Response<Review> response) {
                Intent myIntent = new Intent(CrearReviewActivity.this, VideojuegoActivity.class);
                startActivity(myIntent);
                /*if (response.isSuccessful()) {
                    Intent myIntent = new Intent(CrearReviewActivity.this, VideojuegoActivity.class);
                    startActivity(myIntent);
                    Log.i("Buen resultado", "resultado");
                }*/
            }

            @Override
            public void onFailure(Call<Review> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
        /*
        Intent myIntent = new Intent(CrearReviewActivity.this, VideojuegoActivity.class);
        myIntent.putExtra("actividad", actividad);
        startActivity(myIntent);*/
        finish();
    }
}
