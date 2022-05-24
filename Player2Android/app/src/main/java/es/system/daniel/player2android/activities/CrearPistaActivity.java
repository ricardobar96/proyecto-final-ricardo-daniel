package es.system.daniel.player2android.activities;

import androidx.appcompat.app.AppCompatActivity;
import es.system.daniel.player2android.R;
import es.system.daniel.player2android.connection.APIUtils;
import es.system.daniel.player2android.connection.PistaService;
import es.system.daniel.player2android.modelo.Actividad;
import es.system.daniel.player2android.modelo.JuegoUsuario;
import es.system.daniel.player2android.modelo.Pista;
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

public class CrearPistaActivity extends AppCompatActivity {

    String token;
    Videojuego videojuego = new Videojuego();
    PistaService pistaService;
    EditText tituloEditText;
    EditText contenidoEditText;
    Usuario usuarioActual = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_pista);
        SharedPreferences preferences = getSharedPreferences("usuario",
                Context.MODE_PRIVATE);
        token = preferences.getString("token", "");
        int usuarioId = preferences.getInt("usuarioId", 0);
        usuarioActual.setId(usuarioId);
        Videojuego videojuegoIn = (Videojuego) getIntent().getSerializableExtra("videojuego");
        videojuego.setId(videojuegoIn.getId());
        pistaService = APIUtils.getPistaService();
        tituloEditText = (EditText) findViewById(R.id.tituloPistaEditText);
        contenidoEditText = (EditText) findViewById(R.id.textoPistaEditText);


    }


    public void crearPista (View view) {
        String titulo = tituloEditText.getText().toString();
        String contenido = contenidoEditText.getText().toString();
        Log.i("Título:", titulo);
        Log.i("Contenido:", contenido);
        Log.i("Usuario:", usuarioActual.getId()+"");
        Log.i("Videojuego:", videojuego.getId()+"");
        Log.i("Token:", token);
        Call<Pista> call = pistaService.addPista(new Pista(
                contenido, null, titulo, videojuego, usuarioActual), token);
        call.enqueue(new Callback<Pista>() {
            @Override
            public void onResponse(Call<Pista> call, Response<Pista> response) {
                Intent myIntent = new Intent(CrearPistaActivity.this, VideojuegoActivity.class);
                startActivity(myIntent);
                /*if (response.isSuccessful()) {
                    Intent myIntent = new Intent(CrearPistaActivity.this, VideojuegoActivity.class);
                    startActivity(myIntent);
                    Log.i("Buen resultado", "resultado");
                }*/
            }

            @Override
            public void onFailure(Call<Pista> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
        /*Intent myIntent = new Intent(CrearPistaActivity.this, VideojuegoActivity.class);
        myIntent.putExtra("actividad", actividad);
        startActivity(myIntent);*/
        finish();
    }
}