package es.system.daniel.player2android.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import es.system.daniel.player2android.R;
import es.system.daniel.player2android.adapter.GameAdapter;
import es.system.daniel.player2android.adapter.JuegoUsuarioAdapter;
import es.system.daniel.player2android.connection.APIUtils;
import es.system.daniel.player2android.connection.GameService;
import es.system.daniel.player2android.connection.JuegoUsuarioService;
import es.system.daniel.player2android.modelo.JuegoUsuario;
import es.system.daniel.player2android.modelo.Usuario;
import es.system.daniel.player2android.modelo.Videojuego;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class GamesProfileActivity extends AppCompatActivity {

    Spinner spinnerOrderP;
    ArrayAdapter<CharSequence> adapterOrder;

    Spinner spinnerGenreP;
    ArrayAdapter<CharSequence> adapterGenre;

    ListView listViewProgress;
    ListView listViewCompleted;

    GameService gameService;
    JuegoUsuarioService juegoUsuarioService;

    List<JuegoUsuario> listGame = new ArrayList<JuegoUsuario>();
    List<JuegoUsuario> listProgress = new ArrayList<JuegoUsuario>();
    List<JuegoUsuario> listCompleted = new ArrayList<JuegoUsuario>();

    Usuario usuarioLogin = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamesprofile);

        usuarioLogin = (Usuario) getIntent().getSerializableExtra("usuarioLogin");

        adapterOrder = ArrayAdapter.createFromResource(this,
                R.array.spinnerOrder_resources, android.R.layout.simple_spinner_item);
        adapterOrder.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerOrderP = findViewById(R.id.SpinnerOrderProfile);
        spinnerOrderP.setAdapter(adapterOrder);

        adapterGenre = ArrayAdapter.createFromResource(this,
                R.array.spinnerGenre_resources, android.R.layout.simple_spinner_item);
        adapterGenre.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerGenreP = findViewById(R.id.SpinnerGenreProfile);
        spinnerGenreP.setAdapter(adapterGenre);

        getWindow().getDecorView().setBackgroundColor((Color.rgb(139, 230, 146)));

        listViewProgress = (ListView) findViewById(R.id.progressGamesListView);
        listViewCompleted = (ListView) findViewById(R.id.completedGamesListView);

        gameService = APIUtils.getGameService();
        juegoUsuarioService = APIUtils.getJuegoUsuarioService();

        listViewProgress.setVisibility(View.GONE);
        listViewCompleted.setVisibility(View.GONE);
    }

    public void showProgressGames(View view) {
        listViewProgress.setVisibility(View.VISIBLE);
        listViewCompleted.setVisibility(View.GONE);

        listCompleted.clear();

        Call<List<JuegoUsuario>> call = juegoUsuarioService.getJuegoUsuario();
        call.enqueue(new Callback<List<JuegoUsuario>>() {
            @Override
            public void onResponse(Call<List<JuegoUsuario>> call, Response<List<JuegoUsuario>> response) {
                if (response.isSuccessful()) {
                    listGame = response.body();

                    for (JuegoUsuario j : usuarioLogin.getJuegoUsuarios()) {
                        if(!j.isCompletado()){
                            listProgress.add(j);
                        }
                    }

                    listViewProgress.setAdapter(
                            new JuegoUsuarioAdapter(GamesProfileActivity.this,
                                    R.layout.tarjeta_actividad, listProgress));
                }
            }

            @Override
            public void onFailure(Call<List<JuegoUsuario>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void showCompletedGames(View view) {
        listViewProgress.setVisibility(View.GONE);
        listViewCompleted.setVisibility(View.VISIBLE);

        listProgress.clear();

        Call<List<JuegoUsuario>> call = juegoUsuarioService.getJuegoUsuario();
        call.enqueue(new Callback<List<JuegoUsuario>>() {
            @Override
            public void onResponse(Call<List<JuegoUsuario>> call, Response<List<JuegoUsuario>> response) {
                if (response.isSuccessful()) {
                    listGame = response.body();

                    for (JuegoUsuario j : usuarioLogin.getJuegoUsuarios()) {
                        if(j.isCompletado()){
                            listCompleted.add(j);
                        }
                    }

                    listViewCompleted.setAdapter(
                            new JuegoUsuarioAdapter(GamesProfileActivity.this,
                                    R.layout.tarjeta_actividad, listCompleted));
                }
            }

            @Override
            public void onFailure(Call<List<JuegoUsuario>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menuprofile, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuGamesProfile:
                Intent intentGames = new Intent(GamesProfileActivity.this, GamesProfileActivity.class);
                intentGames.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentGames);
                break;
            case R.id.menuMainProfile:
                Intent intentMain = new Intent(GamesProfileActivity.this, MainProfileActivity.class);
                intentMain.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentMain);
                break;
            case R.id.menuSettingsProfile:
                Intent intentSettings = new Intent(GamesProfileActivity.this, SettingsActivity.class);
                intentSettings.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentSettings);
                break;
            case R.id.menuReviewsProfile:
                Intent intentReviews = new Intent(GamesProfileActivity.this, ReviewsProfileActivity.class);
                intentReviews.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentReviews);
                break;
            case R.id.menuSocialProfile:
                Intent intentSocial = new Intent(GamesProfileActivity.this, SocialActivity.class);
                intentSocial.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentSocial);
                break;
            case R.id.menuReturnHome:
                Intent intentHome = new Intent(GamesProfileActivity.this, MainActivity.class);
                intentHome.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentHome);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
