package es.system.daniel.player2android.activities;

import androidx.appcompat.app.AppCompatActivity;
import es.system.daniel.player2android.R;
import es.system.daniel.player2android.adapter.PistaAdapter;
import es.system.daniel.player2android.adapter.ReviewAdapter;
import es.system.daniel.player2android.connection.APIUtils;
import es.system.daniel.player2android.connection.JuegoUsuarioService;
import es.system.daniel.player2android.connection.PistaService;
import es.system.daniel.player2android.connection.ReviewService;
import es.system.daniel.player2android.connection.UsuarioService;
import es.system.daniel.player2android.modelo.Actividad;
import es.system.daniel.player2android.modelo.JuegoUsuario;
import es.system.daniel.player2android.modelo.Pista;
import es.system.daniel.player2android.modelo.Review;
import es.system.daniel.player2android.modelo.Usuario;
import es.system.daniel.player2android.modelo.Videojuego;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VideojuegoActivity extends AppCompatActivity {

    Actividad actividad = new Actividad();
    Usuario usuarioActual = new Usuario();
    List<Review> listReview = new ArrayList<>();
    List<Pista> listPista = new ArrayList<>();
    JuegoUsuario juegoUsuarioActual = new JuegoUsuario();
    List<JuegoUsuario> juegoUsuarios = new ArrayList<>();
    UsuarioService usuarioService;
    JuegoUsuarioService juegoUsuarioService;
    ReviewService reviewService;
    PistaService pistaService;
    List<Review> listReviewVideojuego = new ArrayList<>();
    List<Pista> listPistaVideojuego = new ArrayList<>();
    String token;
    Videojuego videojuegoActual;

    // Elementos de la vista
    TextView tituloTextView;
    TextView puntuacionTextView;
    TextView puntuacionMediaTextView;
    TextView horasJugadasTextView;
    TextView descripcionVideojuegoTextView;
    ImageView videojuegoImageView;
    NumberPicker puntuacionNumberPicker;
    EditText horasJugadasEditText;
    CheckBox completadoCheckBox;
    CheckBox empezadoCheckBox;
    ListView listViewReviews;
    ListView listViewPistas;
    ScrollView videojuegoScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videojuego);
        SharedPreferences preferences = getSharedPreferences("usuario",
                Context.MODE_PRIVATE);
        token = preferences.getString("token", "");
        actividad = (Actividad) getIntent().getSerializableExtra("actividad");
        if (actividad == null) {
            videojuegoActual = (Videojuego) getIntent().getSerializableExtra("videojuego");
        } else {
            videojuegoActual = actividad.getVideojuego();
        }
        usuarioService = APIUtils.getUsuarioService();
        juegoUsuarioService = APIUtils.getJuegoUsuarioService();
        tituloTextView = (TextView) this.findViewById(R.id.tituloTextView);
        puntuacionTextView = (TextView) this.findViewById(R.id.puntuacionTextView);
        puntuacionMediaTextView = (TextView) this.findViewById(R.id.puntuacionMediaTextView);
        horasJugadasTextView = (TextView) this.findViewById(R.id.horasJugadasTextView);
        videojuegoImageView = (ImageView) this.findViewById(R.id.videojuegoInfoImageView);
        puntuacionNumberPicker = (NumberPicker) this.findViewById(R.id.puntuacionNumberPicker);
        horasJugadasEditText = (EditText) this.findViewById(R.id.horasJugadasEditText);
        completadoCheckBox = (CheckBox) this.findViewById(R.id.completadoCheckBox);
        empezadoCheckBox = (CheckBox) this.findViewById(R.id.empezadoCheckBox);
        descripcionVideojuegoTextView = (TextView) this.findViewById(R.id.descripcionVideojuegoTextView);
        videojuegoScrollView = (ScrollView) this.findViewById(R.id.videojuegoScrollView);
        puntuacionNumberPicker.setVisibility(View.GONE);
        horasJugadasEditText.setVisibility(View.GONE);
        completadoCheckBox.setVisibility(View.GONE);
        puntuacionTextView.setVisibility(View.GONE);
        horasJugadasTextView.setVisibility(View.GONE);

        videojuegoScrollView.setVisibility(View.GONE);
        /*tituloTextView.setVisibility(View.GONE);
        videojuegoImageView.setVisibility(View.GONE);
        puntuacionTextView.setVisibility(View.GONE);
        puntuacionMediaTextView.setVisibility(View.GONE);*/
        listViewReviews = (ListView)findViewById(R.id.reviewsVideojuegoListView);
        listViewPistas = (ListView)findViewById(R.id.pistasVideojuegoListView);
        reviewService = APIUtils.getReviewService();
        pistaService = APIUtils.getPistaService();
        getUsuario();
        getJuegoUsuario();
        getReviewsList();
        getPistasList();

        (new Handler()).postDelayed(this::prepararVideojuego, 3000);
    }

    public void prepararVideojuego() {

        boolean empezado = false;
        int numerVal = 0;
        float puntuacionTotal = 0;


        for (JuegoUsuario juegoUsuario : usuarioActual.getJuegoUsuarios()) {
            Log.i("JuegoUsuario", juegoUsuario.getFecha().toString());
            if (juegoUsuario.getVideojuego().getNombre().equals(videojuegoActual.getNombre())) {
                Log.i("Funciona!", juegoUsuario.getVideojuego().getDescripcion());
                Log.i("Fecha now", new Date().toString());
                empezado = true;
                juegoUsuarioActual = juegoUsuario;
                break;
            }
        }

        for (JuegoUsuario juegoUsuario : juegoUsuarios) {
            if (juegoUsuario.getVideojuego().getNombre().equals(videojuegoActual.getNombre())) {
                numerVal++;
                puntuacionTotal += juegoUsuario.getPuntuacion();
            }
        }




        Log.i("Puntuación total:", puntuacionTotal+"");
        Log.i("Número valoraciones:", numerVal+"");
        puntuacionMediaTextView.setText((puntuacionTotal/numerVal)+"");
        puntuacionNumberPicker.setMinValue(0);
        puntuacionNumberPicker.setMaxValue(10);
        empezadoCheckBox.setChecked(empezado);
        Picasso.get().load(videojuegoActual.getImagen()).into(videojuegoImageView);

        tituloTextView.setText(videojuegoActual.getNombre());
        descripcionVideojuegoTextView.setText(videojuegoActual.getDescripcion());


        /**tituloTextView.setVisibility(View.VISIBLE);
        videojuegoImageView.setVisibility(View.VISIBLE);
        puntuacionTextView.setVisibility(View.VISIBLE);
        puntuacionMediaTextView.setVisibility(View.VISIBLE);*/
        videojuegoScrollView.setVisibility(View.VISIBLE);

        if (empezado) {
            puntuacionNumberPicker.setValue(juegoUsuarioActual.getPuntuacion());
            horasJugadasEditText.setText(juegoUsuarioActual.getHoras()+"");
            completadoCheckBox.setChecked(juegoUsuarioActual.isCompletado());
            puntuacionNumberPicker.setVisibility(View.VISIBLE);
            horasJugadasEditText.setVisibility(View.VISIBLE);
            completadoCheckBox.setVisibility(View.VISIBLE);
            puntuacionTextView.setVisibility(View.VISIBLE);
            horasJugadasTextView.setVisibility(View.VISIBLE);
        } else {
            puntuacionNumberPicker.setVisibility(View.GONE);
            horasJugadasEditText.setVisibility(View.GONE);
            completadoCheckBox.setVisibility(View.GONE);
            puntuacionTextView.setVisibility(View.GONE);
            horasJugadasTextView.setVisibility(View.GONE);
        }

    }

    public void getUsuario() {
        SharedPreferences preferences = getSharedPreferences("usuario",
                Context.MODE_PRIVATE);
        int idUsuario = preferences.getInt("usuarioId", 0);
        //TextView comprobarTextView = (TextView) this.findViewById(R.id.comprobarUsuarioTextView);
        //comprobarTextView.setText(idUsuario+"");
        Call<Usuario> call = usuarioService.getUsuarioById(idUsuario);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    usuarioActual = response.body();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
    public void getJuegoUsuario() {
        Call<List<JuegoUsuario>> call = juegoUsuarioService.getJuegoUsuario();
        call.enqueue(new Callback<List<JuegoUsuario>>() {
            @Override
            public void onResponse(Call<List<JuegoUsuario>> call, Response<List<JuegoUsuario>> response) {
                Log.i("Juegos Usuarios", response.body().get(0).getPuntuacion()+"");
                if (response.isSuccessful()) {
                    Log.i("Funciona", response.body().toString());
                    juegoUsuarios = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<JuegoUsuario>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void cambiarEmpezado(View view) {
        Log.i("Token: ", token);
        CheckBox empezadoCheckBox = (CheckBox) this.findViewById(R.id.empezadoCheckBox);
        if (empezadoCheckBox.isChecked()) {
            Videojuego videojuegoJuegoUsuario = new Videojuego();
            videojuegoJuegoUsuario.setId(videojuegoActual.getId());
            Usuario usuarioJuegoUsuario = new Usuario();
            usuarioJuegoUsuario.setId(usuarioActual.getId());
            Call<JuegoUsuario> call = juegoUsuarioService.addJuegoUsuario(new JuegoUsuario(false, 0, videojuegoJuegoUsuario
                    , usuarioJuegoUsuario, 5, null), token);
            puntuacionNumberPicker.setVisibility(View.VISIBLE);
            horasJugadasEditText.setVisibility(View.VISIBLE);
            completadoCheckBox.setVisibility(View.VISIBLE);
            puntuacionTextView.setVisibility(View.VISIBLE);
            horasJugadasTextView.setVisibility(View.VISIBLE);
            call.enqueue(new Callback<JuegoUsuario>() {
                @Override
                public void onResponse(Call<JuegoUsuario> call, Response<JuegoUsuario> response) {
                    if (response.isSuccessful()) {
                        Log.i("Buen resultado", "resultado");
                    }
                }

                @Override
                public void onFailure(Call<JuegoUsuario> call, Throwable t) {
                    Log.e("ERROR: ", t.getMessage());
                }
            });
        } else {
            Call<String> call = juegoUsuarioService.deleteJuegoUsuario(juegoUsuarioActual.getId(), token);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        Log.i("Eliminado: ", response.body());
                        puntuacionNumberPicker.setVisibility(View.GONE);
                        horasJugadasEditText.setVisibility(View.GONE);
                        completadoCheckBox.setVisibility(View.GONE);
                        puntuacionTextView.setVisibility(View.GONE);
                        horasJugadasTextView.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.e("ERROR: ", t.getMessage());
                }
            });
        }
    }
    public void getReviewsList() {
        Call<List<Review>> call = reviewService.getReviews();
        call.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                if (response.isSuccessful()) {
                    listReview = response.body();

                    for (Review r : listReview) {
                        if (r.getVideojuego().getNombre().equals(videojuegoActual.getNombre())) {
                            listReviewVideojuego.add(r);
                        }
                    }
                    listViewReviews.setAdapter(
                            new ReviewAdapter(VideojuegoActivity.this,
                                    R.layout.tarjeta_review, listReviewVideojuego));
                }
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
    public void getPistasList() {
        Call<List<Pista>> call = pistaService.getPistas();
        call.enqueue(new Callback<List<Pista>>() {
            @Override
            public void onResponse(Call<List<Pista>> call, Response<List<Pista>> response) {
                if (response.isSuccessful()) {
                    listPista = response.body();

                    for (Pista p : listPista) {
                        if (p.getVideojuego().getNombre().equals(videojuegoActual.getNombre())) {
                            listPistaVideojuego.add(p);
                        }
                    }
                    listViewPistas.setAdapter(
                            new PistaAdapter(VideojuegoActivity.this,
                                    R.layout.tarjeta_pista, listPistaVideojuego));
                }
            }

            @Override
            public void onFailure(Call<List<Pista>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void actualizarJuegoUsuario(View view) {
        JuegoUsuario juegoUsuarioPut = new JuegoUsuario();
        juegoUsuarioPut.setCompletado(completadoCheckBox.isChecked());
        juegoUsuarioPut.setHoras(Integer.parseInt(horasJugadasEditText.getText().toString()));
        juegoUsuarioPut.setPuntuacion(puntuacionNumberPicker.getValue());
        juegoUsuarioPut.setId(juegoUsuarioActual.getId());

        Usuario usuario = new Usuario();
        usuario.setId(usuarioActual.getId());
        juegoUsuarioPut.setUsuario(usuario);

        Videojuego videojuego = new Videojuego();
        videojuego.setId(videojuegoActual.getId());
        juegoUsuarioPut.setVideojuego(videojuego);

        Call<JuegoUsuario> call = juegoUsuarioService.updateJuegoUsuario(juegoUsuarioPut.getId(),
                juegoUsuarioPut, token);
        call.enqueue(new Callback<JuegoUsuario>() {
            @Override
            public void onResponse(Call<JuegoUsuario> call, Response<JuegoUsuario> response) {
                if (response.isSuccessful()) {
                    Log.i("Buen resultado", "resultado");
                }
            }

            @Override
            public void onFailure(Call<JuegoUsuario> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void navegarCrearPista(View view) {
        Intent myIntent = new Intent(VideojuegoActivity.this, CrearPistaActivity.class);
        myIntent.putExtra("videojuego", videojuegoActual);
        startActivity(myIntent);
    }

    public void navegarCrearReview(View view) {
        Intent myIntent = new Intent(VideojuegoActivity.this, CrearReviewActivity.class);
        myIntent.putExtra("videojuego", videojuegoActual);
        startActivity(myIntent);
    }
}