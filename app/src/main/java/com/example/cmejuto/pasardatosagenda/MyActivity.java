package com.example.cmejuto.pasardatosagenda;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;


public class MyActivity extends Activity {

    ArrayList<Contacto> aContactos;
    Contacto contacto;
    Button botonAñadir;
    Button botonEditar;
    EditText entradaNombre;
    EditText entradaTlf;
    String nombre;
    String tlf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        botonAñadir = (Button) findViewById(R.id.bAñadir);
        botonEditar = (Button) findViewById(R.id.bEditar);

        //creamos el listener del botón añadir
        botonAñadir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                aContactos = new ArrayList<Contacto>();
                //recogemos los campos a cubrir
                entradaNombre = (EditText) findViewById(R.id.entradaNombre);
                entradaTlf = (EditText) findViewById(R.id.entradaTlf);
                nombre = entradaNombre.getText().toString();
                tlf = entradaTlf.getText().toString();
                Log.d("MiDEBUG", nombre); //esto es para hacer un log de nombres. nos indica lo que vamos añadiendo en eeste camp

                //creamos el objeto y lo metemos en el array
                contacto = new Contacto(nombre, tlf);
                aContactos.add(contacto);

                //limpiamos los campos
                // entradaNombre.setText("");
                // entradaTlf.setText("");
            }

        });

        //creamos el listener del botón editar
        botonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //recogemos nombre a editar
                entradaNombre = (EditText) findViewById(R.id.entradaNombre);
                nombre = entradaNombre.getText().toString();
                Log.d("MiDEBUG2", nombre);

                //para mandar a la clase salutation2
                Intent intent = new Intent(MyActivity.this, MyActivity2.class);
                //lo mandamos a la clase 2, mandamos el arrayList y llamamos a la clase
                intent.putExtra("contacto", contacto); //mandar un objeto -- "contacto" es el identificador
                //intent.putExtra("aContactos", aContactos); //mandar un string -- aContactos es el identificador
                startActivityForResult(intent, 1);//1 es un request code. es como una bandera. la recogemos en onactivityresult. en el método onactivity result esperaremos a que me devuelva lo mismo


            }
        });


    }
    //creamos este método para recoger de la activity2
    protected void onActivityResult(int requestCode, int resultCode, Intent intento) {

        if (requestCode == 1 && intento != null) { //
            if (resultCode == RESULT_OK) {
                //traemos el objeto de la myActivity2
                contacto = (Contacto) intento.getSerializableExtra("contacto");
                String msg = getString(R.string.tostada) + contacto.getNombre() +"   "+ contacto.getTelefono();
                //y llamamos a la tostada pasándole el string que hemos creado
                showToast(msg);
                return;
            }
        }

    }

    protected void showToast(String msg) {
        Context contexto = getApplicationContext();
        int duracion = Toast.LENGTH_LONG;
        Toast tostada = Toast.makeText(contexto, msg, duracion);
        tostada.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
