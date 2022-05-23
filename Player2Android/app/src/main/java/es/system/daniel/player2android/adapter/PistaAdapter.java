package es.system.daniel.player2android.adapter;

import android.content.Context;
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
import es.system.daniel.player2android.modelo.Pista;

public class PistaAdapter extends ArrayAdapter<Pista> {

    private Context context;
    private List<Pista> pistas;

    public PistaAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Pista> objects) {
        super(context, resource, objects);
        this.context = context;
        this.pistas = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.tarjeta_pista, parent, false);

        TextView txtpista = (TextView) rowView.findViewById(R.id.pistaTextView);
        ImageView imgAvatar = (ImageView) rowView.findViewById(R.id.AvatarPistaImageView);

        txtpista.setText(String.format("%s, %s", pistas.get(pos).getTitulo(),
                pistas.get(pos).getUsuario().getNombre()));
        Picasso.get().load(pistas.get(pos).getUsuario().getAvatar()).into(imgAvatar);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity usuario Form
                //Intent intent = new Intent(context, usuarioActivity.class);
                //intent.putExtra("usuario", usuarios.get(pos));
                //context.startActivity(intent);
            }
        });

        return rowView;
    }
}