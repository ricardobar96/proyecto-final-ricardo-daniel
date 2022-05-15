package es.system.daniel.player2android;

import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    UsuarioService usuarioService;
    List<Usuario> list = new ArrayList<Usuario>();
    Spinner spinnerMenu;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.actividadesListView);
        usuarioService = APIUtils.getUsuarioService();
        getUsuariosList();
        adapter=ArrayAdapter.createFromResource(this,
                R.array.menu_resources, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerMenu = findViewById(R.id.spinner_menu);
        spinnerMenu.setAdapter(adapter);
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
    public void addUsuario(View view) {
        //Intent myIntent = new Intent(MainActivity.this,AddUsuarioActivity.class);
        //startActivity(myIntent);
    }
}