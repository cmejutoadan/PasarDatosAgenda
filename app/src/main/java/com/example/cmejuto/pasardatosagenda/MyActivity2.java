package com.example.cmejuto.pasardatosagenda;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


public class MyActivity2 extends Activity {

    Button bOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity2);

        //recogemos el string nombre
        String nombre2 = getIntent().getExtras().getString("edNombre");
        //y lo metemos donde corresponde
        EditText entradaNombre2 = (EditText)findViewById(R.id.entradaNombre2);
        entradaNombre2.setText(nombre2);
        bOk = (Button) findViewById(R.id.bOk); //boton de ok
        //creamos el listener del botón OK
        bOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //recogemos los campos a cubrir
               EditText entradaNombre = (EditText) findViewById(R.id.entradaNombre);
                EditText entradaTlf = (EditText) findViewById(R.id.entradaTlf);
               String nombre = entradaNombre.getText().toString();
                String tlf = entradaTlf.getText().toString();
                Log.d("MiDEBUG", nombre); //esto es para hacer un log de nombres. nos indica lo que vamos añadiendo en eeste camp
/*
                //creamos el objeto y lo metemos en el array
                Contacto contacto = new Contacto(nombre, tlf);
                aContactos.add(contacto);
*/
                //limpiamos los campos
                entradaNombre.setText("");
                entradaTlf.setText("");
            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_activity2, menu);
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
