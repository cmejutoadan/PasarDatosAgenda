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
    Contacto contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity2);

        //recogemos el objeto
        contacto = (Contacto) getIntent().getSerializableExtra("contacto");
        //y lo metemos donde corresponde
        EditText entradaNombre2 = (EditText) findViewById(R.id.entradaNombre2);
        entradaNombre2.setText(contacto.getNombre());
        EditText entradaTlf2 = (EditText) findViewById(R.id.entradaTlf2);
        entradaTlf2.setText(contacto.getTelefono());
        bOk = (Button) findViewById(R.id.bOk); //boton de ok

        //creamos el listener del botón OK
        bOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //recogemos los campos a cubrir
                EditText entradaNombre2 = (EditText) findViewById(R.id.entradaNombre2);
                EditText entradaTlf2 = (EditText) findViewById(R.id.entradaTlf2);

                contacto= new Contacto(entradaNombre2.getText().toString(), entradaTlf2.getText().toString());
            /*    contacto.setNombre(entradaNombre2.getText().toString());//modificamos el objeto
                contacto.setTelefono(entradaTlf2.getText().toString()); //modificamos el objeto*/

                //y los mandamos a la activity1. Ojo!! usamos setResult y le mandamos un ok y el intento
                Intent intent = new Intent(MyActivity2.this, MyActivity.class);
                intent.putExtra("contacto", contacto);
                setResult(RESULT_OK, intent); //llamamos al protected void onActivityResult. Ojo!! el número de parámetros no coincide pero se hace así
                finish();
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
