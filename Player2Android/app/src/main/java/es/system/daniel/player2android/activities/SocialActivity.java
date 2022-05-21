package es.system.daniel.player2android.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import es.system.daniel.player2android.R;
import es.system.daniel.player2android.adapter.GameAdapter;
import es.system.daniel.player2android.adapter.UsuarioAdapter;
import es.system.daniel.player2android.connection.APIUtils;
import es.system.daniel.player2android.connection.GameService;
import es.system.daniel.player2android.connection.UsuarioService;
import es.system.daniel.player2android.modelo.Usuario;
import es.system.daniel.player2android.modelo.Videojuego;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SocialActivity extends AppCompatActivity {

    ListView listViewFollowers;
    ListView listViewFollowing;

    GameService gameService;
    UsuarioService usuarioService;

    List<Videojuego> listGame = new ArrayList<Videojuego>();
    List<Usuario> list = new ArrayList<Usuario>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        getWindow().getDecorView().setBackgroundColor((Color. rgb(139,230,146)));

        listViewFollowers = (ListView) findViewById(R.id.FollowersProfileListView);
        gameService = APIUtils.getGameService();

        listViewFollowing = (ListView)findViewById(R.id.FollowingProfileListView);
        usuarioService = APIUtils.getUsuarioService();

        listViewFollowers.setVisibility(View.GONE);
        listViewFollowing.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menuprofile, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menuGamesProfile:
                Intent intentGames = new Intent(SocialActivity.this, GamesProfileActivity.class);
                startActivity(intentGames);
                break;
            case R.id.menuMainProfile:
                Intent intentMain = new Intent(SocialActivity.this, MainProfileActivity.class);
                startActivity(intentMain);
                break;
            case R.id.menuSettingsProfile:
                Intent intentSettings = new Intent(SocialActivity.this, SettingsActivity.class);
                startActivity(intentSettings);
                break;
            case R.id.menuReviewsProfile:
                Intent intentReviews = new Intent(SocialActivity.this, ReviewsProfileActivity.class);
                startActivity(intentReviews);
                break;
            case R.id.menuSocialProfile:
                Intent intentSocial = new Intent(SocialActivity.this, SocialActivity.class);
                startActivity(intentSocial);
                break;
            case R.id.menuReturnHome:
                Intent intentHome = new Intent(SocialActivity.this, MainActivity.class);
                startActivity(intentHome);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showFollowing(View view){
        Call<List<Usuario>> call = usuarioService.getUsuarios();

        listViewFollowers.setVisibility(View.GONE);
        listViewFollowing.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(response.isSuccessful()){
                    list = response.body();

                    listViewFollowing.setAdapter(
                            new UsuarioAdapter(SocialActivity.this,
                                    R.layout.tarjeta_actividad, list));
                }
            }
            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void showFollowers(View view) {
        Call<List<Videojuego>> call = gameService.getGames();

        listViewFollowing.setVisibility(View.GONE);
        listViewFollowers.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<List<Videojuego>>() {
            @Override
            public void onResponse(Call<List<Videojuego>> call, Response<List<Videojuego>> response) {
                if (response.isSuccessful()) {
                    listGame = response.body();

                    listViewFollowers.setAdapter(
                            new GameAdapter(SocialActivity.this,
                                    R.layout.tarjeta_actividad, listGame));
                }
            }

            @Override
            public void onFailure(Call<List<Videojuego>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}