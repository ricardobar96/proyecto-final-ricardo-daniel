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
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    UsuarioService usuarioService;
    File file;
    FileOutputStream fileOutputStream;
    FileInputStream fileInputStream;
    String nombre;
    String pwd;

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
                            //Intent myIntent = new Intent(AddAlumnoActivity.this,MainActivity.class);
                            //startActivity(myIntent);

                            //****PRUEBA CARGAR DATOS GUARDADOS:****//
                            try {
                                fileInputStream =  openFileInput("Code.txt");
                                int read = -1;
                                StringBuffer buffer = new StringBuffer();
                                while((read = fileInputStream.read())!= -1){
                                    buffer.append((char)read);
                                }
                                Log.d("Code", buffer.toString());
                                String nombreCode = buffer.substring(0,buffer.indexOf(" "));
                                String passCode = buffer.substring(buffer.indexOf(" ")+1);
                                Log.i("Nombre guardado:", nombreCode);
                                Log.i("Password guardado:", passCode);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            //****FIN DE PRUEBA CARGAR DATOS GUARDADOS****//

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
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