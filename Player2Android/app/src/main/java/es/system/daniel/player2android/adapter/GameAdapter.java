package es.system.daniel.player2android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.List;

import es.system.daniel.player2android.R;
import es.system.daniel.player2android.activities.VideojuegoActivity;
import es.system.daniel.player2android.modelo.Videojuego;

public class GameAdapter extends ArrayAdapter<Videojuego> {

    private Context context;
    private List<Videojuego> games;

    public GameAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Videojuego> objects) {
        super(context, resource, objects);
        this.context = context;
        this.games = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.tarjeta_juego, parent, false);

        TextView txtgame = (TextView) rowView.findViewById(R.id.gameTextView);
        ImageView imgGame = (ImageView) rowView.findViewById(R.id.gameImageView);

        txtgame.setText(String.format("%s", games.get(pos).getNombre()));

        Picasso.get().load(games.get(pos).getImagen()).into(imgGame);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity usuario Form
                Intent intent = new Intent(context, VideojuegoActivity.class);
                intent.putExtra("videojuego", games.get(pos));
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}

