package es.system.daniel.player2android.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.system.daniel.player2android.R;
import es.system.daniel.player2android.adapter.ActividadAdapter;
import es.system.daniel.player2android.adapter.GameAdapter;
import es.system.daniel.player2android.adapter.ReviewAdapter;
import es.system.daniel.player2android.connection.APIUtils;
import es.system.daniel.player2android.connection.GameService;
import es.system.daniel.player2android.connection.ReviewService;
import es.system.daniel.player2android.modelo.Actividad;
import es.system.daniel.player2android.modelo.JuegoUsuario;
import es.system.daniel.player2android.modelo.Pista;
import es.system.daniel.player2android.modelo.Review;
import es.system.daniel.player2android.modelo.Usuario;
import es.system.daniel.player2android.modelo.Videojuego;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewsProfileActivity extends AppCompatActivity {

    ListView listViewReviews;

    ReviewService reviewService;
    List<Review> listReview = new ArrayList<Review>();
    List<Review> listReviewUser = new ArrayList<Review>();

    Usuario usuarioLogin = new Usuario();
    String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewsprofile);

        usuarioLogin = (Usuario) getIntent().getSerializableExtra("usuarioLogin");

        if(usuarioLogin != null){
            color = usuarioLogin.getColor();
        }

        if(color.equals("LightSalmon")){
            getWindow().getDecorView().setBackgroundColor((Color. rgb(230,146,146)));
        }
        if(color.equals("lightsteelblue")){
            getWindow().getDecorView().setBackgroundColor((Color. rgb(146,208,230)));
        }
        else{
            getWindow().getDecorView().setBackgroundColor((Color. rgb(139,230,146)));
        }

        listViewReviews = (ListView) findViewById(R.id.reviewsProfileListView);
        reviewService = APIUtils.getReviewService();

        getReviewsList();
    }

    public void getReviewsList() {
        Call<List<Review>> call = reviewService.getReviews();
        call.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                if (response.isSuccessful()) {
                    listReview = response.body();

                    for (Review r : listReview) {
                        if (r.getUsuario().getNombre().equals(usuarioLogin.getNombre())) {
                            listReviewUser.add(r);
                        }
                    }
                    listViewReviews.setAdapter(
                            new ReviewAdapter(ReviewsProfileActivity.this,
                                    R.layout.tarjeta_review, listReviewUser));
                }
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {
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
                Intent intentGames = new Intent(ReviewsProfileActivity.this, GamesProfileActivity.class);
                intentGames.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentGames);
                break;
            case R.id.menuMainProfile:
                Intent intentMain = new Intent(ReviewsProfileActivity.this, MainProfileActivity.class);
                intentMain.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentMain);
                break;
            case R.id.menuSettingsProfile:
                Intent intentSettings = new Intent(ReviewsProfileActivity.this, SettingsActivity.class);
                intentSettings.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentSettings);
                break;
            case R.id.menuSocialProfile:
                Intent intentSocial = new Intent(ReviewsProfileActivity.this, SocialActivity.class);
                intentSocial.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentSocial);
                break;
            case R.id.menuReviewsProfile:
                Intent intentReviews = new Intent(ReviewsProfileActivity.this, ReviewsProfileActivity.class);
                intentReviews.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentReviews);
                break;
            case R.id.menuReturnHome:
                Intent intentHome = new Intent(ReviewsProfileActivity.this, PrincipalActivity.class);
                intentHome.putExtra("usuarioLogin", usuarioLogin);
                startActivity(intentHome);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
