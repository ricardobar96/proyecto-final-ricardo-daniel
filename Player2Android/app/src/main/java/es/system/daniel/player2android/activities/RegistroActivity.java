package es.system.daniel.player2android.activities;

import androidx.appcompat.app.AppCompatActivity;
import es.system.daniel.player2android.R;
import es.system.daniel.player2android.connection.APIUtils;
import es.system.daniel.player2android.connection.UsuarioService;
import es.system.daniel.player2android.modelo.Usuario;
import es.system.daniel.player2android.modelo.UsuarioInscribirse;
import es.system.daniel.player2android.modelo.UsuarioRegistro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegistroActivity extends AppCompatActivity {

    UsuarioService usuarioService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        usuarioService = APIUtils.getUsuarioService();
    }
    public void registrarUsuario(View view) {
        EditText txtNombre = (EditText) this.findViewById(R.id.nombreRegEditText);
        EditText txtPwd = (EditText) this.findViewById(R.id.pwdRegEditText);
        String nombre = txtNombre.getText().toString();

        String pwd = txtPwd.getText().toString();


        UsuarioRegistro usuario = new UsuarioRegistro(nombre, pwd);
       /*Gson gson = new Gson();
        String json = gson.toJson(usuario);
        Log.i("Clicko", json);*/
        Call<Usuario> call = usuarioService.addUsuario(usuario);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    Log.i("Funciona", response.body().toString());
                    Intent myIntent = new Intent(RegistroActivity.this, LoginActivity.class);
                    startActivity(myIntent);
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void returnMain(View view) {
        Intent mainIntent = new Intent(RegistroActivity.this,MainActivity.class);
        startActivity(mainIntent);
    }
}