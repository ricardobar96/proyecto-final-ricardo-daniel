package es.system.daniel.player2android.activities;

import androidx.appcompat.app.AppCompatActivity;
import es.system.daniel.player2android.R;
import es.system.daniel.player2android.adapter.ActividadAdapter;
import es.system.daniel.player2android.adapter.UsuarioAdapter;
import es.system.daniel.player2android.connection.APIUtils;
import es.system.daniel.player2android.connection.UsuarioService;
import es.system.daniel.player2android.modelo.Actividad;
import es.system.daniel.player2android.modelo.JuegoUsuario;
import es.system.daniel.player2android.modelo.Pista;
import es.system.daniel.player2android.modelo.Review;
import es.system.daniel.player2android.modelo.Usuario;
import es.system.daniel.player2android.modelo.UsuarioInscribirse;
import es.system.daniel.player2android.modelo.Videojuego;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    UsuarioService usuarioService;
    Usuario usuarioLogin = new Usuario();
    Usuario usuarioActual = new Usuario();
    List<Actividad> actividadList = new ArrayList<>();
    ListView listView;
    boolean logueado;
    Integer idUsuarioActual;
    TextView textActividadesNone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        usuarioService = APIUtils.getUsuarioService();
        listView = (ListView)findViewById(R.id.actividadesListView);

        usuarioLogin = (Usuario) getIntent().getSerializableExtra("usuarioLogin");

        textActividadesNone = (TextView) findViewById(R.id.actividadesNone);
        textActividadesNone.setVisibility(View.GONE);

        /*
        SharedPreferences preferences = getSharedPreferences("usuario",
                Context.MODE_PRIVATE);
        idUsuarioActual = preferences.getInt("usuarioId", 0);

        logueado = false;

        if(idUsuarioActual != 0){
            logueado = true;
        }
        */

        getUsuario();
    }

    public void getUsuario() {
        SharedPreferences preferences = getSharedPreferences("usuario",
                Context.MODE_PRIVATE);
        int idUsuario = preferences.getInt("usuarioId", 0);
        //TextView comprobarTextView = (TextView) this.findViewById(R.id.comprobarUsuarioTextView);
        //comprobarTextView.setText(idUsuario+"");
        Call<Usuario> call = usuarioService.getUsuarioById(idUsuario);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    usuarioActual = response.body();
                    getActividades();
                    /*Gson gson = new Gson();
                    String json = gson.toJson(usuarioActual);
                    Log.i("Json del usuario: ", json);
                    comprobarTextView.setText(usuarioActual.getDescripcion());*/
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void getActividades() {
        for (Usuario followed : usuarioActual.getFolloweds()) {
            for (JuegoUsuario juegoUsuario : followed.getJuegoUsuarios()) {
                actividadList.add(new Actividad("juegoU", juegoUsuario.getFecha(),
                        followed, juegoUsuario.getVideojuego()));
            }
            for (Review review : followed.getReviews()) {
                actividadList.add(new Actividad("review", review.getFecha(),
                        followed, review.getVideojuego()));
            }
            for (Pista pista : followed.getPistas()) {
                actividadList.add(new Actividad("pista", pista.getFecha(),
                        followed, pista.getVideojuego()));
            }
        }
        Collections.sort(actividadList);

        if(actividadList.size() == 0){
            textActividadesNone.setVisibility(View.VISIBLE);
        }

        listView.setAdapter(
                new ActividadAdapter(PrincipalActivity.this,
                        R.layout.tarjeta_actividad, actividadList));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menuhome, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menuMainProfile:
                /*
                if(logueado == false){
                    Intent intentLogin = new Intent(PrincipalActivity.this, LoginActivity.class);
                    startActivity(intentLogin);
                }
                */
                Intent intentMain = new Intent(PrincipalActivity.this, MainProfileActivity.class);
                intentMain.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentMain);
                break;
            case R.id.menuHome:
                Intent intentHome = new Intent(PrincipalActivity.this, PrincipalActivity.class);
                intentHome.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentHome);
                break;
            case R.id.menuListaVideojuegos:
                Intent intentJuegos = new Intent(PrincipalActivity.this, ListaVideojuegosActivity.class);
                intentJuegos.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentJuegos);
                break;
            case R.id.menuLogout:
                SharedPreferences preferences = getSharedPreferences("usuario",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                Intent intentLogout = new Intent(PrincipalActivity.this, LoginActivity.class);
                startActivity(intentLogout);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}