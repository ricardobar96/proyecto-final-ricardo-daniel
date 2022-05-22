package es.system.daniel.player2android.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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

    UsuarioService usuarioService;
    Usuario usuarioAjeno = new Usuario();

    EditText searchUserText;
    String nameUser;

    List<Usuario> listFollowers = new ArrayList<Usuario>();
    List<Usuario> listFollowing = new ArrayList<Usuario>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        getWindow().getDecorView().setBackgroundColor((Color. rgb(139,230,146)));

        listViewFollowers = (ListView) findViewById(R.id.FollowersProfileListView);
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
                    listFollowing = response.body();

                    listViewFollowing.setAdapter(
                            new UsuarioAdapter(SocialActivity.this,
                                    R.layout.tarjeta_usuario, listFollowing));
                }
            }
            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void showFollowers(View view) {
        Call<List<Usuario>> call = usuarioService.getUsuarios();

        listViewFollowing.setVisibility(View.GONE);
        listViewFollowers.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (response.isSuccessful()) {
                    listFollowers = response.body();

                    listViewFollowers.setAdapter(
                            new UsuarioAdapter(SocialActivity.this,
                                    R.layout.tarjeta_usuario, listFollowers));
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void searchUser(View view) {
        try{
            searchUserText = (EditText) this.findViewById(R.id.idSearchUserIntro);
            nameUser = searchUserText.getText().toString();
            usuarioAjeno = getUsuarioAjeno(nameUser);
        }
        catch (Exception ex){
            Log.e("ERROR: ", ex.getMessage());
        }
    }

    public Usuario getUsuarioAjeno(String nombreUsuario) {
        Call<List<Usuario>> call = usuarioService.getUsuarios();

        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (response.isSuccessful()) {
                    for (Usuario usuario : response.body()) {
                        if (usuario.getNombre().equals(nombreUsuario)) {
                            usuarioAjeno = usuario;
                            break;
                        }
                    }
                    SharedPreferences preferences = getSharedPreferences("usuario",
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("usuarioId", (usuarioAjeno.getId() - 1));
                    editor.commit();

                    Intent intent = new Intent(SocialActivity.this, MainProfileActivity.class);
                    intent.putExtra("usuario", usuarioAjeno);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
        return usuarioAjeno;
    }
}