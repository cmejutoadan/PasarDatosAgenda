package com.example.cmejuto.pasardatosagenda;

import android.app.Activity;
import android.content.Intent;
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
    Button bElim;
    Contacto contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity2);

        //recogemos el objeto que viene de la INTERMEDIA
        contacto = (Contacto) getIntent().getSerializableExtra("contacto");
        //y lo metemos donde corresponde
        EditText entradaNombre2 = (EditText) findViewById(R.id.entradaNombre2);
        entradaNombre2.setText(contacto.getNombre());
        EditText entradaTlf2 = (EditText) findViewById(R.id.entradaTlf2);
        entradaTlf2.setText(contacto.getTelefono());

        bOk = (Button) findViewById(R.id.bOk); //boton de ok
        bElim = (Button) findViewById(R.id.bBorrar); //boton de eliminar

        //creamos el listener del botón OK
        bOk.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                //recogemos los campos a cubrir
                EditText entradaNombre2 = (EditText) findViewById(R.id.entradaNombre2);
                EditText entradaTlf2 = (EditText) findViewById(R.id.entradaTlf2);

                contacto = new Contacto(entradaNombre2.getText().toString(), entradaTlf2.getText().toString());


                //y los mandamos a la INTERMEDIA. Ojo!! usamos setResult y le mandamos un ok y el intento
                Log.d("MENSJE",contacto.toString());
                Intent intent = new Intent(MyActivity2.this, MyActivity_Intermedia.class);
                intent.putExtra("contacto", contacto);
                 //llamamos al protected void onActivityResult. Ojo!! el número de parámetros no coincide pero se hace así
                 startActivityForResult(intent, 1);
                 finish();
            }

        });

        //creamos el listener del botón BORRAR
        bElim.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //recogemos los campos a cubrir
                EditText entradaNombre2 = (EditText) findViewById(R.id.entradaNombre2);
                EditText entradaTlf2 = (EditText) findViewById(R.id.entradaTlf2);

                Contacto contactoB = new Contacto(entradaNombre2.getText().toString(), entradaTlf2.getText().toString());


                //y los mandamos a la activity BORRAR.
                //Log.d("MENSJE",contactoB.toString());
                Intent intent2 = new Intent(MyActivity2.this, Borrar.class);
                intent2.putExtra("contactoB", contactoB);
                startActivityForResult(intent2, 1);

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
