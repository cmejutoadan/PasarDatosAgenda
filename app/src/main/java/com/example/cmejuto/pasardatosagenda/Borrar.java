package com.example.cmejuto.pasardatosagenda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Borrar extends Activity {

    Contacto contacto;
    Button bEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar);

        //recogemos el objeto que viene de MyActivity2 y que vamos a borrar
        contacto = (Contacto) getIntent().getSerializableExtra("contactoB");
        //y lo metemos donde corresponde
        EditText entradaNombreB = (EditText) findViewById(R.id.entradaNombre2);
        entradaNombreB.setText(contacto.getNombre());
        EditText entradaTlfB = (EditText) findViewById(R.id.entradaTlf2);
        entradaTlfB.setText(contacto.getTelefono());

        //creamos el botón de confirmar eliminar y el listener del botón
        bEliminar = (Button) findViewById(R.id.bEliminar); //boton de eliminar

        //creamos el listener del botón OK
        bEliminar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //mostramos una tostada
                Toast.makeText(getApplicationContext(), "Contacto eliminado", Toast.LENGTH_LONG).show();
                //y mandamos el contacto INTERMEDIA para actualizar el arraylist. Ojo!! usamos setResult y le mandamos un ok y el intento
                Log.d("MENSJE", contacto.toString());
                Intent intent3 = new Intent(Borrar.this, MyActivity2.class);
                contacto = new Contacto("borrado", "0");
                intent3.putExtra("contacto", contacto);

                //startActivity(intent3);
                setResult(RESULT_OK, intent3); //llamamos al protected void onActivityResult. Ojo!! el número de parámetros no coincide pero se hace así
                finish();
            }

        });
    }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.borrar, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
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
