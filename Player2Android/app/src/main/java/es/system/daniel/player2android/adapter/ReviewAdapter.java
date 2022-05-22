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
import es.system.daniel.player2android.modelo.Review;

public class ReviewAdapter extends ArrayAdapter<Review> {

    private Context context;
    private List<Review> reviews;

    public ReviewAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Review> objects) {
        super(context, resource, objects);
        this.context = context;
        this.reviews = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.tarjeta_review, parent, false);

        TextView txtreview = (TextView) rowView.findViewById(R.id.reviewTextView);
        ImageView imgAvatar = (ImageView) rowView.findViewById(R.id.AvatarReviewImageView);

        txtreview.setText(String.format("%s, %s", reviews.get(pos).getTitulo(),
                reviews.get(pos).getUsuario().getNombre()));
        Picasso.get().load(reviews.get(pos).getUsuario().getAvatar()).into(imgAvatar);

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
