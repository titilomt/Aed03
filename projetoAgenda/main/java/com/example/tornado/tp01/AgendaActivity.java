package com.example.tornado.tp01;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class AgendaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    //metodo para salvar na agenda de contados
    public void save(View v){
        boolean photo_ok=false;
       /* //Intent agenda = new Intent(ContactsContract.Contacts.)
        Intent agenda = new Intent(Intent.ACTION_PICK);
        //android.intent.action.INSERT

       // Intent agenda = new Intent(Intent.ACTION_PICK, ContactsContract.);
        startActivityForResult(agenda,2);*/
        Intent agenda = new Intent(Intent.ACTION_INSERT);
        agenda.setType(ContactsContract.Contacts.CONTENT_TYPE);

        EditText nome1 =(EditText) findViewById(R.id.campo_nome);
        EditText telefone1 =(EditText) findViewById(R.id.campo_telefone);
        EditText email1 =(EditText) findViewById(R.id.campo_email);
        EditText endereco1 = (EditText)findViewById(R.id.campo_endereco);

        String nome = nome1.getText().toString();
        String telefone = telefone1.getText().toString();
        String email = email1.getText().toString();
        String endereco = endereco1.getText().toString();

        agenda.putExtra(ContactsContract.Intents.Insert.NAME, nome);
        agenda.putExtra(ContactsContract.Intents.Insert.PHONE, telefone);
        agenda.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
        agenda.putExtra(ContactsContract.Intents.Insert.POSTAL, endereco);
        if(photo_ok){
            //inserir foto aqui;
        }


        //context.startActivity(agenda);
        startActivityForResult(agenda,2);
        clearFields();


    }

    public void clearFields() {

        final EditText clearName = (EditText) findViewById(R.id.campo_nome);
        final EditText clearTelefone = (EditText) findViewById(R.id.campo_telefone);
        final EditText clearEmail = (EditText) findViewById(R.id.campo_email);
        final EditText clearAdress = (EditText) findViewById(R.id.campo_endereco);

        clearName.setText("");
        clearTelefone.setText("");
        clearEmail.setText("");
        clearAdress.setText("");
    }

    public void clearFields(View view) {

        final EditText clearName = (EditText) findViewById(R.id.campo_nome);
        final EditText clearTelefone = (EditText) findViewById(R.id.campo_telefone);
        final EditText clearEmail = (EditText) findViewById(R.id.campo_email);
        final EditText clearAdress = (EditText) findViewById(R.id.campo_endereco);


        clearName.setText("");
        clearTelefone.setText("");
        clearEmail.setText("");
        clearAdress.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_agenda, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}