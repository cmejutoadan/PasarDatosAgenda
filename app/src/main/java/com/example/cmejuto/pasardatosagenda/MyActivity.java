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

//programa funcionando correctamente

public class MyActivity extends Activity {


    public static ArrayList<Contacto> aContactos = new ArrayList<Contacto>();
    Contacto contacto;
    Intent intent;
    //Contacto contacto2;
    Button botonAñadir;
    Button botonMostrar;
    EditText entradaNombre;
    EditText entradaTlf;
    String nombre;//lo vamos a usar para buscar en el arrayList y actualizarlo
    String tlf;
    Bundle extras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        botonAñadir = (Button) findViewById(R.id.bAñadir);
        botonMostrar = (Button) findViewById(R.id.bMostrar);

        //creamos el listener del botón añadir
        botonAñadir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                //recogemos los campos a cubrir
                entradaNombre = (EditText) findViewById(R.id.entradaNombre);
                entradaTlf = (EditText) findViewById(R.id.entradaTlf);
                nombre = entradaNombre.getText().toString();
                tlf = entradaTlf.getText().toString();
                Log.d("MiDEBUG", nombre); //esto es para hacer un log de nombres. nos indica lo que vamos añadiendo en eeste camp

                //creamos el objeto y lo metemos en el array
                contacto = new Contacto(nombre, tlf);
                //llamamos al método para que añada al array
                añadirArray(contacto);

                //limpiamos los campos
                // entradaNombre.setText("");
                // entradaTlf.setText("");
            }

        });

        //creamos el listener del botón editar
        botonMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //recogemos nombre a editar
                entradaNombre = (EditText) findViewById(R.id.entradaNombre);
                nombre = entradaNombre.getText().toString();
                Log.d("MiDEBUG2", nombre);

/*  //en caso de sólo dos pantallas
                //para mandar a la clase salutation2
                intent = new Intent(MyActivity.this, MyActivity2.class);
                //lo mandamos a la clase 2, mandamos el arrayList y llamamos a la clase
                intent.putExtra("contacto", contacto); //mandar un objeto -- "contacto" es el identificador
                //intent.putExtra("aContactos", aContactos); //mandar un array -- aContactos es el identificador
                startActivityForResult(intent, 1);//1 es un request code. es como una bandera. la recogemos en onactivityresult. en el método onactivity result esperaremos a que me devuelva lo mismo

                *///mandamos el arraylist a intermedia en un bundle
                intent = new Intent(MyActivity.this, MyActivity_Intermedia.class);
                extras = new Bundle();
                extras.putSerializable("aContactos", aContactos);
                intent.putExtras(extras); //mandamos el arraylist
                startActivityForResult(intent, 1);//1 es un request code. es como una bandera. la recogemo

            }
        });


    }

    //metodo para añadir al arrayList
    public void añadirArray(Contacto contacto) {
        aContactos.add(this.contacto);
    }

    //método para modificar el array
    public String modificarArray(Contacto contacto, String nombre) {
        String mensaje = "";

        for (int i = 0; i < aContactos.size(); i++) {
            if (aContactos.get(i).getNombre().compareToIgnoreCase(nombre) == 0) {

                Contacto c = new Contacto(contacto.getNombre(), contacto.getTelefono());
                aContactos.set(i, c);

                mensaje = aContactos.get(i).getNombre() + " " + aContactos.get(i).getTelefono();
                Log.d("LOG", mensaje);
                //Toast toast(getApplicationContext(),aContactos.get(i).getNombre(), Toast.LENGTH_LONG).show();


            }
        }
        return mensaje;
    }


    //Recogemos el arraylist de INTERMEDIA
   protected void onActivityResult(int requestCode, int resultCode, Intent intento) {

        if (requestCode == 1 && intento != null) { //
            if (resultCode == RESULT_OK) {
                //sobreescribimos el arraylist
                aContactos = (ArrayList) intento.getSerializableExtra("aContactos");
                //String msg = getString(R.string.tostada) + contacto.getNombre() + "   " + contacto.getTelefono();
                /*//y modificamos el arraylist
                String mensaje = modificarArray(contacto, nombre);
                //y llamamos a la tostada pasándole el string que hemos creado
                Toast.makeText(getApplicationContext(), "el contacto ha sido actualizado a: " + mensaje, Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(),aContactos.get(i).getNombre(), Toast.LENGTH_LONG).show();
                //showToast(msg);
                //return;*/

                //limpiamos los campos
                entradaNombre.setText("");
                entradaTlf.setText("");

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
