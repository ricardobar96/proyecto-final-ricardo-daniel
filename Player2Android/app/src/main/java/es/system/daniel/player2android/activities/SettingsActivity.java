package es.system.daniel.player2android.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import es.system.daniel.player2android.R;
import es.system.daniel.player2android.connection.APIUtils;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getWindow().getDecorView().setBackgroundColor((Color. rgb(139,230,146)));
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
                startActivity(intentGames);
                break;
            case R.id.menuMainProfile:
                Intent intentMain = new Intent(SettingsActivity.this, MainProfileActivity.class);
                startActivity(intentMain);
                break;
            case R.id.menuSettingsProfile:
                Intent intentSettings = new Intent(SettingsActivity.this, SettingsActivity.class);
                startActivity(intentSettings);
                break;
            case R.id.menuSocialProfile:
                Intent intentSocial = new Intent(SettingsActivity.this, SocialActivity.class);
                startActivity(intentSocial);
                break;
            case R.id.menuReviewsProfile:
                Intent intentReviews = new Intent(SettingsActivity.this, ReviewsProfileActivity.class);
                startActivity(intentReviews);
                break;
            case R.id.menuReturnHome:
                Intent intentHome = new Intent(SettingsActivity.this, MainActivity.class);
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