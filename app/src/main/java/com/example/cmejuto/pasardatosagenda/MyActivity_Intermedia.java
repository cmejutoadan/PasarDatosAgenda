package com.example.cmejuto.pasardatosagenda;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//esta clase extiende de LIST ACTIVITY
public class MyActivity_Intermedia extends ListActivity {

    ArrayList<Contacto> aContactos = new ArrayList();
    Intent intento = new Intent();
    Contacto c = new Contacto();
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity__intermedia);//enlaza el layout

        //recogemos el arraylist que vine de activity 1
        aContactos = (ArrayList<Contacto>) getIntent().getSerializableExtra("aContactos");

        //  OJO!! hay que sobreescribir el tostring() del objeto para que lo devuelva correctamente
        setListAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, aContactos));


                /* EN CASO DE 2 ACTIVITIS //recogemos el objeto
        contacto = (Contacto) getIntent().getSerializableExtra("contacto");
        //y lo metemos donde corresponde
        EditText entradaNombre2 = (EditText) findViewById(R.id.entradaNombre2);
        entradaNombre2.setText(contacto.getNombre());
        EditText entradaTlf2 = (EditText) findViewById(R.id.entradaTlf2);
        entradaTlf2.setText(contacto.getTelefono());
        bOk = (Button) findViewById(R.id.bOk); //boton de ok*/

    }

    //creamos un método que va ser llamado cuando ocurre un evento (callback)
    //ocurre cuando clicamos en un elemento de la lista
    public void onListItemClick(ListView parent, View v, int position, long id) {

        //recogemos el objeto del arraylist para mandarlo a la 3a activity
        c = aContactos.get(position); //metemos el contacto seleccionado en un objeto contacto para mandarlo
        intento = new Intent(MyActivity_Intermedia.this, MyActivity2.class);
        intento.putExtra("contacto", c);
        pos = position;
        startActivityForResult(intento, 1);

    }

    //recogemos el objeto contacto de la ACIVITY2 y lo modificamos en el array
    protected void onActivityResult(int requestCode, int resultCode, Intent intento) {

        if (requestCode == 1 && intento != null) { //
            if (resultCode == RESULT_OK) {
                //traemos el objeto de activity 2
                Contacto contacto = (Contacto) intento.getSerializableExtra("contacto");

                //llamamos al método que modifica el array
                modificaArray(pos, contacto, aContactos);

                Log.d("MENSJE", contacto.toString());

                //llamamos de nuevo al listAdapter para que muestre el array modificado
                setListAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, aContactos));

                //    //traemos el objeto de la myActivity2
                //    contacto = (Contacto) intento.getSerializableExtra("contacto");
                //  //String msg = getString(R.string.tostada) + contacto.getNombre() + "   " + contacto.getTelefono();
                //    //y modificamos el arraylist
                //    String mensaje = modificarArray(contacto, nombre);
                //    //y llamamos a la tostada pasándole el string que hemos creado
                //    Toast.makeText(getApplicationContext(), "el contacto ha sido actualizado a: " + mensaje, Toast.LENGTH_LONG).show();
                //    //Toast.makeText(getApplicationContext(),aContactos.get(i).getNombre(), Toast.LENGTH_LONG).show();
                //    //showToast(msg);
                //    //return;

                //    //limpiamos los campos
                //    entradaNombre.setText("");
                //    entradaTlf.setText("");

            }
        }

    }

    //SOBREESCRIBIMOS EL MÉTODO DEL BOTÓN "ATRÁS"
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            //código que queremos que ejecute
            //devolvemos el arraylist a activity 1
            Intent intent2 = new Intent(MyActivity_Intermedia.this, MyActivity.class);
            intent2.putExtra("aContactos", aContactos);
            setResult(RESULT_OK, intent2); //llamamos al protected void onActivityResult. Ojo!! el número de parámetros no coincide pero se hace así
            finish();

            return false; //I have tried here true also
        }
        return super.onKeyDown(keyCode, event);
    }


    //modificar array
    public void modificaArray(int pos, Contacto contacto, ArrayList<Contacto> aContactos) {
        aContactos.set(pos, contacto);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_activity__intermedia, menu);
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
