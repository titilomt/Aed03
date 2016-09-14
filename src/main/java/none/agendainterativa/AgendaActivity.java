package none.agendainterativa;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;

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
    public void findMap(View view){

        EditText endereco2 = (EditText)findViewById(R.id.campo_endereco);
        String endereco = endereco2.getText().toString();

        Uri end = Uri.parse("geo:0,0?q="+endereco);

        Intent map = new Intent(Intent.ACTION_VIEW, end);
        map.setPackage("com.google.android.apps.maps");
        startActivityForResult(map,3);

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

    public void takePhoto(View v){

        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
       // Bitmap photo= (Bitmap)camera.getExtras().get("camera");
          //  ImageView.setScaleType(ImageView.ScaleType.FIT_XY);
           // ImageView.setImageBitmap(photo);
            //Knop.setVisibility(Button.VISIBLE);

        //Uri uri_photo = getImageUri(getApplicationContext(),photo);

        //photo_path =new File(getRealPathFromURI(uri_photo));
        //System.out.println(photo_path.getPath());
        startActivityForResult(camera, 4);

    }

   /* public  void onActivityResult(int requestCode, int resultCode, Intent data){

        if( (requestCode=4 )&& (resultCode == RESULT_OK) ){
            Bitmap photo= (Bitmap)data.getExtras().get("data");
            ImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageView.setImageBitmap(photo);
            //Knop.setVisibility(Button.VISIBLE);

            Uri uri_photo = getImageUri(getApplicationContext(),photo);

            photo_path =new File(getRealPathFromURI(uri_photo));
            System.out.println(photo_path.getPath());

        }

    }*/
    public Uri getImageUri(Context inContext , Bitmap inImage){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG,100,bytes);

        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(),inImage,"title","path");
        return Uri.parse(path);



    }
    public String getRealPathFromURI(Uri uri){
        try{
            Cursor cursor = getContentResolver().query(uri,null,null,null,null);
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);


        }catch(Exception e){
            System.out.println("nada");
            return("not");
        }
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
