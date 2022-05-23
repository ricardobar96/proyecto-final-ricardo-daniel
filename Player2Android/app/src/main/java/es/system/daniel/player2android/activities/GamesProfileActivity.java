package es.system.daniel.player2android.activities;

import androidx.appcompat.app.AppCompatActivity;
import es.system.daniel.player2android.R;
import es.system.daniel.player2android.modelo.Actividad;

import android.os.Bundle;
import android.widget.TextView;

public class GamesProfileActivity extends AppCompatActivity {

    Actividad actividad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_profile);
        actividad = (Actividad) getIntent().getSerializableExtra("actividad");
        TextView tituloTextView = (TextView) this.findViewById(R.id.nombrePerfilTextView);
        tituloTextView.setText(actividad.usuario.getNombre());
    }
}