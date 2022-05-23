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
import es.system.daniel.player2android.R;
import es.system.daniel.player2android.connection.APIUtils;
import es.system.daniel.player2android.connection.UsuarioService;
import es.system.daniel.player2android.modelo.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsActivity extends AppCompatActivity {

    Usuario usuarioLogin = new Usuario();
    UsuarioService usuarioService;
    String token;
    EditText textInfoUser;
    Integer idUsuarioActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        usuarioLogin = (Usuario) getIntent().getSerializableExtra("usuarioLogin");

        usuarioService = APIUtils.getUsuarioService();

        SharedPreferences preferences = getSharedPreferences("usuario",
                Context.MODE_PRIVATE);
        token = preferences.getString("token", "");
        idUsuarioActual = preferences.getInt("usuarioId", 0);

        String color = usuarioLogin.getColor();

        if(color!=null){
            if(color.equals("LightSalmon")){
                getWindow().getDecorView().setBackgroundColor((Color. rgb(230,146,146)));
            }
            if(color.equals("lightsteelblue")){
                getWindow().getDecorView().setBackgroundColor((Color. rgb(146,208,230)));
            }
            if(color.equals("DarkSeaGreen")){
                getWindow().getDecorView().setBackgroundColor((Color. rgb(139,230,146)));
            }
        }
        else{
            getWindow().getDecorView().setBackgroundColor((Color. rgb(139,230,146)));
        }
    }

    public void changeInfoUser(View view) {
        try{
            textInfoUser = (EditText) this.findViewById(R.id.idInfoIntro);
            usuarioLogin.setDescripcion(textInfoUser.getText().toString());
            Call<Usuario> call = usuarioService.updateUsuario(idUsuarioActual, usuarioLogin, token);
            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if(response.isSuccessful()){
                        Intent intentSettings = new Intent(SettingsActivity.this, MainProfileActivity.class);
                        intentSettings.putExtra("usuarioLogin", usuarioLogin);
                        startActivity(intentSettings);
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Log.e("ERROR: ", t.getMessage());
                }
            });
        }
        catch (Exception ex){
            Log.e("ERROR: ", ex.getMessage());
        }
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
                Intent intentGames = new Intent(SettingsActivity.this, GamesProfileActivity.class);
                intentGames.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentGames);
                break;
            case R.id.menuMainProfile:
                Intent intentMain = new Intent(SettingsActivity.this, MainProfileActivity.class);
                intentMain.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentMain);
                break;
            case R.id.menuSettingsProfile:
                Intent intentSettings = new Intent(SettingsActivity.this, SettingsActivity.class);
                intentSettings.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentSettings);
                break;
            case R.id.menuSocialProfile:
                Intent intentSocial = new Intent(SettingsActivity.this, SocialActivity.class);
                intentSocial.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentSocial);
                break;
            case R.id.menuReviewsProfile:
                Intent intentReviews = new Intent(SettingsActivity.this, ReviewsProfileActivity.class);
                intentReviews.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentReviews);
                break;
            case R.id.menuReturnHome:
                Intent intentHome = new Intent(SettingsActivity.this, PrincipalActivity.class);
                intentHome.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentHome);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void greenProfile(View view) {
        getWindow().getDecorView().setBackgroundColor((Color. rgb(139,230,146)));
    }

    public void redProfile(View view) {
        getWindow().getDecorView().setBackgroundColor((Color. rgb(230,146,146)));
    }

    public void blueProfile(View view) {
        getWindow().getDecorView().setBackgroundColor((Color. rgb(146,208,230)));
    }
}