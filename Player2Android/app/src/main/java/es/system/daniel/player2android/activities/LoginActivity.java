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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;

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
    //File file;
    //FileOutputStream fileOutputStream;
    //FileInputStream fileInputStream;
    String nombre;
    String pwd;
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
        TextView txtToken = (TextView) this.findViewById(R.id.tokenTextView);
        nombre = txtNombre.getText().toString();
        pwd = txtPwd.getText().toString();


        UsuarioInscribirse usuario = new UsuarioInscribirse(nombre, pwd);
        /*Gson gson = new Gson();
        String json = gson.toJson(usuario);
        Log.i("Clicko", json);*/

        Call<String> call = usuarioService.login(usuario);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("¿Funciona?", response.body());
                if (response.isSuccessful()) {
                    txtToken.setText(response.body());
                    Log.i("Funciona", response.body());
                    getUsuarioActual(nombre);
                    Intent myIntent = new Intent(LoginActivity.this, PrincipalActivity.class);
                    startActivity(myIntent);

                    /*PRUEBA GUARDAR DATOS INTERNAL STORAGE

                    file = getFilesDir();
                    try {
                        nombre = nombre + " ";
                        fileOutputStream = openFileOutput("Code.txt", Context.MODE_PRIVATE);
                        fileOutputStream.write(nombre.getBytes());
                        fileOutputStream.write(pwd.getBytes());
                        txtNombre.setText("");
                        txtPwd.setText("");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        try {
                            fileOutputStream.close();

                            FIN DE PRUEBA GUARDAR DATOS INTERNAL STORAGE*/

                            /*PRUEBA CARGAR DATOS GUARDADOS:
                            try {
                                fileInputStream = openFileInput("Code.txt");
                                int read = -1;
                                StringBuffer buffer = new StringBuffer();
                                while ((read = fileInputStream.read()) != -1) {
                                    buffer.append((char) read);
                                }
                                Log.d("Code", buffer.toString());
                                String nombreCode = buffer.substring(0, buffer.indexOf(" "));
                                String passCode = buffer.substring(buffer.indexOf(" ") + 1);
                                Log.i("Nombre guardado:", nombreCode);
                                Log.i("Password guardado:", passCode);

                                Intent myIntent = new Intent(LoginActivity.this, PrincipalActivity.class);
                                startActivity(myIntent);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    FIN DE PRUEBA CARGAR DATOS GUARDADOS*/

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public Usuario getUsuarioActual(String nombreUsuario) {
        Call<List<Usuario>> call = usuarioService.getUsuarios();

        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                //Log.i("¿Funciona?", response.body());
                if (response.isSuccessful()) {
                    for (Usuario usuario : response.body()) {
                        Log.i("Nombre usuario_", usuario.getNombre());
                        Log.i("Nombre utilizado", nombreUsuario);
                        if (usuario.getNombre().equals(nombreUsuario)) {
                            Log.i("Hecho", usuario.getNombre());
                            usuarioActual = usuario;
                            break;
                        }
                    }
                    SharedPreferences preferences = getSharedPreferences("usuario",
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("usuarioId", usuarioActual.getId());
                    editor.commit();
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