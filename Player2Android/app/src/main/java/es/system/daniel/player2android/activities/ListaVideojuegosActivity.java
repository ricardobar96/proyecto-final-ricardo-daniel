package es.system.daniel.player2android.activities;

import androidx.appcompat.app.AppCompatActivity;
import es.system.daniel.player2android.R;
import es.system.daniel.player2android.adapter.ActividadAdapter;
import es.system.daniel.player2android.adapter.GameAdapter;
import es.system.daniel.player2android.connection.APIUtils;
import es.system.daniel.player2android.connection.GameService;
import es.system.daniel.player2android.connection.GeneroService;
import es.system.daniel.player2android.connection.JuegoUsuarioService;
import es.system.daniel.player2android.connection.UsuarioService;
import es.system.daniel.player2android.modelo.Genero;
import es.system.daniel.player2android.modelo.JuegoUsuario;
import es.system.daniel.player2android.modelo.NombreVideojuegosComparator;
import es.system.daniel.player2android.modelo.PuntuacionVideojuegosComparator;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListaVideojuegosActivity extends AppCompatActivity {

    Usuario usuarioActual = new Usuario();
    UsuarioService usuarioService;
    GameService gameService;
    JuegoUsuarioService juegoUsuarioService;
    GeneroService generoService;
    ListView listaVideojuegosListView;
    List<Videojuego> listaVideojuegos;
    Spinner ordenarSpinner;
    Spinner generoSpinner;
    Context context = this;
    List<Genero> listaGeneros = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_videojuegos);

        usuarioService = APIUtils.getUsuarioService();

        gameService = APIUtils.getGameService();
        generoService = APIUtils.getGeneroService();
        juegoUsuarioService = APIUtils.getJuegoUsuarioService();
        listaVideojuegosListView = findViewById(R.id.listaVideojuegosListView);
        ordenarSpinner = (Spinner) findViewById(R.id.ordenarSpinner);
        generoSpinner = (Spinner) findViewById(R.id.generoSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ordenar_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ordenarSpinner.setAdapter(adapter);

        getUsuario();

        getVideojuegos();
        getGeneros();
    }
    public void getVideojuegos() {
        Call<List<Videojuego>> call = gameService.getGames();
        call.enqueue(new Callback<List<Videojuego>>() {
            @Override
            public void onResponse(Call<List<Videojuego>> call, Response<List<Videojuego>> response) {
                if (response.isSuccessful()) {
                    listaVideojuegos = response.body();
                    Collections.sort(listaVideojuegos);
                    Collections.reverse(listaVideojuegos);
                    getJuegosUsuario();
                    listaVideojuegosListView.setAdapter(
                            new GameAdapter(ListaVideojuegosActivity.this,
                                    R.layout.tarjeta_juego, listaVideojuegos));
                }
            }

            @Override
            public void onFailure(Call<List<Videojuego>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
    public void getJuegosUsuario() {
        //TextView comprobarTextView = (TextView) this.findViewById(R.id.comprobarUsuarioTextView);
        //comprobarTextView.setText(idUsuario+"");
        Call<List<JuegoUsuario>> call = juegoUsuarioService.getJuegoUsuario();
        call.enqueue(new Callback<List<JuegoUsuario>>() {
            @Override
            public void onResponse(Call<List<JuegoUsuario>> call, Response<List<JuegoUsuario>> response) {
                if (response.isSuccessful()) {
                    List<JuegoUsuario> listaJuegoUsuario = response.body();
                    for (Videojuego videojuego : listaVideojuegos) {
                        int numVideojuego = 0;
                        float notaTotal = 0;
                        for (JuegoUsuario juegoUsuario : listaJuegoUsuario) {
                            if (juegoUsuario.getVideojuego().getNombre().equals(videojuego.getNombre())) {
                                numVideojuego++;
                                notaTotal += juegoUsuario.getPuntuacion();
                            }
                        }
                        if (numVideojuego > 0) {
                            videojuego.setPuntuacion(notaTotal/numVideojuego);
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<List<JuegoUsuario>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
    public void getGeneros() {
        //TextView comprobarTextView = (TextView) this.findViewById(R.id.comprobarUsuarioTextView);
        //comprobarTextView.setText(idUsuario+"");
        Call<List<Genero>> call = generoService.getGeneros();
        call.enqueue(new Callback<List<Genero>>() {
            @Override
            public void onResponse(Call<List<Genero>> call, Response<List<Genero>> response) {
                if (response.isSuccessful()) {
                    listaGeneros = response.body();
                    List<String> listaStrGeneros = new ArrayList<>();
                    for (Genero genero : listaGeneros) {
                        listaStrGeneros.add(genero.getNombre());
                    }
                    String[] generosArr = new String[listaStrGeneros.size()];
                    generosArr = listaStrGeneros.toArray(generosArr);

                    ArrayAdapter<String> arrayAdapter;
                    arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, generosArr);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    generoSpinner.setAdapter(arrayAdapter);

                }

            }

            @Override
            public void onFailure(Call<List<Genero>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

    }
    public void filtrarListaVideojuegos(View view) {
        List<Videojuego> listaVideojuegosFiltrados = new ArrayList<>();
        for (Videojuego videojuego : listaVideojuegos) {
            listaVideojuegosFiltrados.add(videojuego);
        }
        Log.i("Seleccionaste:", ordenarSpinner.getSelectedItem().toString());
        if (ordenarSpinner.getSelectedItem().toString().equals("Puntuación")) {
            listaVideojuegosFiltrados.sort(new PuntuacionVideojuegosComparator());
        } else if (ordenarSpinner.getSelectedItem().toString().equals("Título")) {
            listaVideojuegosFiltrados.sort(new NombreVideojuegosComparator());
        } else if (ordenarSpinner.getSelectedItem().toString().equals("Fecha")) {
            Collections.sort(listaVideojuegosFiltrados);
            Collections.reverse(listaVideojuegosFiltrados);
        }
        if (!generoSpinner.getSelectedItem().toString().equals(null)) {
            Log.i("Genero spinner: ", generoSpinner.getSelectedItem().toString());
            for (Videojuego videojuego : listaVideojuegos) {
                boolean tieneGenero = false;
                for(Genero genero : videojuego.getGeneros()) {
                    if (genero.getNombre().equals(generoSpinner.getSelectedItem().toString())) {
                        Log.i("Tiene genero: ", genero.getNombre());
                        tieneGenero = true;
                        break;
                    }
                }
                if (!tieneGenero) {
                    listaVideojuegosFiltrados.remove(videojuego);
                }
            }

        }
        listaVideojuegosListView.setAdapter(
                new GameAdapter(ListaVideojuegosActivity.this,
                        R.layout.tarjeta_juego, listaVideojuegosFiltrados));
    }
    public void quitarFiltros(View view) {
        listaVideojuegosListView.setAdapter(
                new GameAdapter(ListaVideojuegosActivity.this,
                        R.layout.tarjeta_juego, listaVideojuegos));
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
                Intent intentMain = new Intent(ListaVideojuegosActivity.this, MainProfileActivity.class);
                intentMain.putExtra("usuarioLogin", usuarioActual);
                startActivity(intentMain);
                break;
            case R.id.menuHome:
                Intent intentHome = new Intent(ListaVideojuegosActivity.this, PrincipalActivity.class);
                intentHome.putExtra("usuarioLogin", usuarioActual);
                startActivity(intentHome);
                break;
            case R.id.menuListaVideojuegos:
                Intent intentJuegos = new Intent(ListaVideojuegosActivity.this, ListaVideojuegosActivity.class);
                intentJuegos.putExtra("usuarioLogin", usuarioActual);
                startActivity(intentJuegos);
                break;
            case R.id.menuLogout:
                SharedPreferences preferences = getSharedPreferences("usuario",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                Intent intentLogout = new Intent(ListaVideojuegosActivity.this, LoginActivity.class);
                startActivity(intentLogout);
                break;
        }
        return super.onOptionsItemSelected(item);
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
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}