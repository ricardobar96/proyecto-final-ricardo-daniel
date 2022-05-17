package es.system.daniel.player2android.activities;

import androidx.appcompat.app.AppCompatActivity;
import es.system.daniel.player2android.R;
import es.system.daniel.player2android.adapter.UsuarioAdapter;
import es.system.daniel.player2android.connection.APIUtils;
import es.system.daniel.player2android.connection.UsuarioService;
import es.system.daniel.player2android.modelo.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    UsuarioService usuarioService;
    List<Usuario> list = new ArrayList<Usuario>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.actividadesListView);
        usuarioService = APIUtils.getUsuarioService();
        getUsuariosList();
    }

    public void login(){


        /*Call<List<Usuario>> call = usuarioService.getUsuarios();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(response.isSuccessful()){
                    list = response.body();

                    listView.setAdapter(
                            new UsuarioAdapter(MainActivity.this,
                                    R.layout.tarjeta_actividad, list));
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });*/
    }


    public void getUsuariosList(){


        Call<List<Usuario>> call = usuarioService.getUsuarios();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(response.isSuccessful()){
                    list = response.body();

                    listView.setAdapter(
                            new UsuarioAdapter(MainActivity.this,
                                    R.layout.tarjeta_actividad, list));
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getUsuariosList();
    }
    public void goLoginUsuario(View view) {
        Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(myIntent);
    }
}