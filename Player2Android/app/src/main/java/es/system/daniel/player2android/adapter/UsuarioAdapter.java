package es.system.daniel.player2android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import es.system.daniel.player2android.R;
import es.system.daniel.player2android.modelo.Usuario;


public class UsuarioAdapter extends ArrayAdapter<Usuario> {

    private Context context;
    private List<Usuario> usuarios;

    public UsuarioAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Usuario> objects) {
        super(context, resource, objects);
        this.context = context;
        this.usuarios = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.tarjeta_usuario, parent, false);

        TextView txtusuario = (TextView) rowView.findViewById(R.id.profileUserTextView);

        txtusuario.setText(String.format("%s, %s", usuarios.get(pos).getNombre(), usuarios.get(pos).getNombre()));

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
