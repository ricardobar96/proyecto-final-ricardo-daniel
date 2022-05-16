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

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    UsuarioService usuarioService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuarioService = APIUtils.getUsuarioService();
    }

    public void loginUsuario(View view) {
        EditText txtNombre = (EditText) this.findViewById(R.id.nombreEditText);
        EditText txtPwd = (EditText) this.findViewById(R.id.pwdEditText);
        TextView txtToken = (TextView) this.findViewById(R.id.tokenTextView);
        String nombre = txtNombre.getText().toString();

        String pwd = txtPwd.getText().toString();


        UsuarioInscribirse usuario = new UsuarioInscribirse(nombre, pwd);
        Gson gson = new Gson();
        String json = gson.toJson(usuario);
        Log.i("Clicko", json);
        Call<String> call = usuarioService.login(usuario);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    txtToken.setText(response.body());
                    Log.i("Funciona", response.body());
                    //Intent myIntent = new Intent(AddAlumnoActivity.this,MainActivity.class);
                    //startActivity(myIntent);
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


}