package es.system.daniel.player2android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import es.system.daniel.player2android.R;
import es.system.daniel.player2android.activities.VideojuegoActivity;
import es.system.daniel.player2android.modelo.Actividad;
import es.system.daniel.player2android.modelo.Videojuego;


public class ActividadAdapter extends ArrayAdapter<Actividad> {

    private Context context;
    private List<Actividad> actividades;

    public ActividadAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Actividad> objects) {
        super(context, resource, objects);
        this.context = context;
        this.actividades = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.tarjeta_actividad, parent, false);

        TextView txtActividad = (TextView) rowView.findViewById(R.id.actividadTextView);
        TextView txtUsuario = (TextView) rowView.findViewById(R.id.usuarioTextView);
        TextView txtTiempo = (TextView) rowView.findViewById(R.id.tiempoTextView);
        ImageView imgAvatar = (ImageView) rowView.findViewById(R.id.avatarImageView);
        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideojuegoActivity.class);
                intent.putExtra("actividad", actividades.get(pos));
                context.startActivity(intent);
            }
        });

        if (actividades.get(pos).getTipo() == "juegoU") {
            txtActividad.setText(String.format("Ha añadido %s a sus videojuegos jugados.", actividades.get(pos).getVideojuego().getNombre()));
        } else if (actividades.get(pos).getTipo() == "review") {
            txtActividad.setText(String.format("Ha creado una review de %s.", actividades.get(pos).getVideojuego().getNombre()));
        } else if (actividades.get(pos).getTipo() == "pista") {
            txtActividad.setText(String.format("Ha creado una pista de %s.", actividades.get(pos).getVideojuego().getNombre()));
        }

        txtUsuario.setText(String.format("%s", actividades.get(pos).getUsuario().getNombre()));

        Picasso.get().load(actividades.get(pos).getUsuario().getAvatar()).into(imgAvatar);

        txtTiempo.setText(String.format("%s", calcularTiempo(actividades.get(pos).getFecha())));


        //imgAvatar.setImageURI(actividades.get(pos).usuario.getAvatar());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity actividad Form
                Intent intent = new Intent(context, VideojuegoActivity.class);
                intent.putExtra("actividad", actividades.get(pos));
                context.startActivity(intent);
            }
        });

        return rowView;
    }

    public String calcularTiempo(Date fecha) {
        long tiempo = new Date().getTime();
        long miliSeg = tiempo - fecha.getTime();
        String tiempoStr = "";
        int segundos = (int) (miliSeg / 1000);
        if (segundos > 60) {
            int minutos = segundos / 60;
            if (minutos > 60) {
                int horas = minutos / 60;
                if (horas > 24) {
                    int dias = horas / 24;
                    if (dias > 7) {
                        int semanas = dias / 7;
                        tiempoStr = "Han pasado " + semanas + " semanas.";
                    } else {
                        tiempoStr = "Han pasado " + dias + " días.";
                    }
                } else {
                    tiempoStr = "Han pasado " + horas + " horas.";
                }
            } else {
                tiempoStr = "Han pasado " + minutos + " minutos.";
            }
        } else {
            tiempoStr = "Han pasado " + segundos + " segundos.";
        }
        return tiempoStr;
    }

}