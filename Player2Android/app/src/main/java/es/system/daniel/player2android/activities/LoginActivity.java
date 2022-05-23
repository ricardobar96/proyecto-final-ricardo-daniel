package es.system.daniel.player2android.activities;

import androidx.appcompat.app.AppCompatActivity;
import es.system.daniel.player2android.R;
import es.system.daniel.player2android.adapter.UsuarioAdapter;
import es.system.daniel.player2android.connection.APIUtils;
import es.system.daniel.player2android.connection.UsuarioService;
import es.system.daniel.player2android.modelo.Usuario;
import es.system.daniel.player2android.modelo.UsuarioInscribirse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    UsuarioService usuarioService;
    Usuario usuarioActual = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuarioService = APIUtils.getUsuarioService();
    }

    public void loginUsuario(View view) {
        EditText txtNombre = (EditText) this.findViewById(R.id.nombreEditText);
        EditText txtPwd = (EditText) this.findViewById(R.id.pwdEditText);
        String nombre = txtNombre.getText().toString();

        String pwd = txtPwd.getText().toString();


        UsuarioInscribirse usuario = new UsuarioInscribirse(nombre, pwd);
       /*Gson gson = new Gson();
        String json = gson.toJson(usuario);
        Log.i("Clicko", json);*/
        Call<String> call = usuarioService.login(usuario);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    getUsuarioActual(nombre, response.body());
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
                //Intent myIntent = new Intent(AddAlumnoActivity.this,MainActivity.class);
                //startActivity(myIntent);
            }
        });
    }

    public Usuario getUsuarioActual(String nombreUsuario, String token) {
        Call<List<Usuario>> call = usuarioService.getUsuarios();

        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                //Log.i("¿Funciona?", response.body());
                if(response.isSuccessful()){
                    for (Usuario usuario : response.body()) {
                        if (usuario.getNombre().equals(nombreUsuario)) {
                            usuarioActual = usuario;
                            break;
                        }
                    }
                    SharedPreferences preferences = getSharedPreferences("usuario",
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("usuarioId", usuarioActual.getId());
                    editor.putString("token", token);
                    editor.commit();
                    Intent myIntent = new Intent(LoginActivity.this,PrincipalActivity.class);
                    myIntent.putExtra("usuarioLogin", usuarioActual);
                    startActivity(myIntent);
                    //Intent myIntent = new Intent(AddAlumnoActivity.this,MainActivity.class);
                    //startActivity(myIntent);
                }
            }
            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
                //Intent myIntent = new Intent(AddAlumnoActivity.this,MainActivity.class);
                //startActivity(myIntent);
            }
        });
        return usuarioActual;
    }


}