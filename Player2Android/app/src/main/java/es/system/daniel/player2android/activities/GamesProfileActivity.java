package es.system.daniel.player2android.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import es.system.daniel.player2android.R;
import es.system.daniel.player2android.connection.APIUtils;

import android.widget.Spinner;

public class GamesProfileActivity extends AppCompatActivity {

    Spinner spinnerOrderP;
    ArrayAdapter<CharSequence> adapterOrder;

    Spinner spinnerGenreP;
    ArrayAdapter<CharSequence> adapterGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamesprofile);

        adapterOrder=ArrayAdapter.createFromResource(this,
                R.array.spinnerOrder_resources, android.R.layout.simple_spinner_item);
        adapterOrder.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerOrderP = findViewById(R.id.SpinnerOrderProfile);
        spinnerOrderP.setAdapter(adapterOrder);

        adapterGenre=ArrayAdapter.createFromResource(this,
                R.array.spinnerGenre_resources, android.R.layout.simple_spinner_item);
        adapterGenre.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerGenreP = findViewById(R.id.SpinnerGenreProfile);
        spinnerGenreP.setAdapter(adapterGenre);
    }
}
