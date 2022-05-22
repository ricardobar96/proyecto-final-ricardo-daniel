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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.List;

import es.system.daniel.player2android.R;
import es.system.daniel.player2android.connection.APIUtils;
import es.system.daniel.player2android.connection.UsuarioService;
import es.system.daniel.player2android.modelo.Actividad;
import es.system.daniel.player2android.modelo.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainProfileActivity extends AppCompatActivity {

    Usuario usuarioAjeno = new Usuario();
    Usuario usuarioActual = new Usuario();
    UsuarioService usuarioService;
    Usuario usuarioLogin = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainprofile);
        usuarioLogin = (Usuario) getIntent().getSerializableExtra("usuarioLogin");

        Button buttonFolow = (Button) this.findViewById(R.id.idButtonFollowUser);
        buttonFolow.setVisibility(View.GONE);

        usuarioAjeno = (Usuario) getIntent().getSerializableExtra("usuario");
        usuarioService = APIUtils.getUsuarioService();

        if(usuarioAjeno!=null){

            String color = usuarioAjeno.getColor();

            if(color.equals("LightSalmon")){
                getWindow().getDecorView().setBackgroundColor((Color. rgb(230,146,146)));
            }
            if(color.equals("lightsteelblue")){
                getWindow().getDecorView().setBackgroundColor((Color. rgb(146,208,230)));
            }
            if(color.equals("DarkSeaGreen")){
                getWindow().getDecorView().setBackgroundColor((Color. rgb(139,230,146)));
            }

            buttonFolow.setVisibility(View.VISIBLE);

            TextView infoTextView = (TextView) this.findViewById(R.id.idMainInfoUser);
            infoTextView.setText(usuarioAjeno.getDescripcion());

            ImageView imgAvatar = (ImageView) this.findViewById(R.id.avatarUserImageProfileView);
            Picasso.get().load(usuarioAjeno.getAvatar()).into(imgAvatar);

        }
        if(usuarioAjeno==null){

            String color = usuarioLogin.getColor();

            if(color.equals("LightSalmon")){
                getWindow().getDecorView().setBackgroundColor((Color. rgb(230,146,146)));
            }
            if(color.equals("lightsteelblue")){
                getWindow().getDecorView().setBackgroundColor((Color. rgb(146,208,230)));
            }
            if(color.equals("DarkSeaGreen")){
                getWindow().getDecorView().setBackgroundColor((Color. rgb(139,230,146)));
            }

            TextView infoTextView = (TextView) this.findViewById(R.id.idMainInfoUser);
            infoTextView.setText(usuarioLogin.getDescripcion());

            ImageView imgAvatar = (ImageView) this.findViewById(R.id.avatarUserImageProfileView);
            Picasso.get().load(usuarioLogin.getAvatar()).into(imgAvatar);
        }
    }

    public Usuario getUsuarioActual(String nombreUsuario) {
        Call<List<Usuario>> call = usuarioService.getUsuarios();

        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (response.isSuccessful()) {
                    for (Usuario usuario : response.body()) {
                        if (usuario.getNombre().equals(nombreUsuario)) {
                            usuarioActual = usuario;
                            break;
                        }
                    }
                    SharedPreferences preferences = getSharedPreferences("usuario",
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("usuarioId", usuarioActual.getId());
                    editor.commit();
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
        return usuarioActual;
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
                Intent intentGames = new Intent(MainProfileActivity.this, GamesProfileActivity.class);
                intentGames.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentGames);
                break;
            case R.id.menuMainProfile:
                Intent intentMain = new Intent(MainProfileActivity.this, MainProfileActivity.class);
                intentMain.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentMain);
                break;
            case R.id.menuSettingsProfile:
                Intent intentSettings = new Intent(MainProfileActivity.this, SettingsActivity.class);
                intentSettings.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentSettings);
                break;
            case R.id.menuReviewsProfile:
                Intent intentReviews = new Intent(MainProfileActivity.this, ReviewsProfileActivity.class);
                intentReviews.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentReviews);
                break;
            case R.id.menuSocialProfile:
                Intent intentSocial = new Intent(MainProfileActivity.this, SocialActivity.class);
                intentSocial.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentSocial);
                break;
            case R.id.menuReturnHome:
                Intent intentHome = new Intent(MainProfileActivity.this, MainActivity.class);
                intentHome.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentHome);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}