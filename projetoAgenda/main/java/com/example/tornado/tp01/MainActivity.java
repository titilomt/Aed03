package com.example.tornado.tp01;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pl.looksok.searchviewdemo.R;

/**
 * Created by tornado on 8/29/16.
 */

public class MainActivity extends AppCompatActivity{

    private static final int READ_CONTACTS_PERMISSIONS_REQUEST = 1;
    protected static final String LOG_TAG = "sa";
    private static final String TAG = "Error";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        getPermissionToReadUserContacts();
    }

    @Override
    protected void onStart(){
        super.onStart();
        final Button button = (Button) findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    try {
                        EditText id_name = (EditText) findViewById(R.id.id_name);
                        String nome = String.valueOf(id_name.getText()).toLowerCase();
                        Cursor cursor  = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
                        while (cursor.moveToNext()) {
                            //String id = cursor.getString(Integer.parseInt(ContactsContract.Contacts._ID));
                            int nameIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                            String name = cursor.getString(nameIdx).toLowerCase();
                            if(name.contains(nome)) {
                                //int contactIdIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID);
                                //String idContact = cursor.getString(contactIdIdx);
                                int phoneNumberIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                                String phoneNumber = cursor.getString(phoneNumberIdx);
                                EditText value = (EditText) findViewById(R.id.id_number);
                                value.setText(phoneNumber);
                            } else {
                                Context context = getApplicationContext();
                                CharSequence text = "Contato nao encontrado!";
                                int duration = Toast.LENGTH_SHORT;

                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                            }
                        }
                        cursor.close();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

    }

    public void getPermissionToReadUserContacts() {
        // 1) Use the support library version ContextCompat.checkSelfPermission(...) to avoid
        // checking the build version since Context.checkSelfPermission(...) is only available
        // in Marshmallow
        // 2) Always check for permission (even if permission has already been granted)
        // since the user can revoke permissions at any time through Settings
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            // The permission is NOT already granted.
            // Check if the user has been asked about this permission already and denied
            // it. If so, we want to give more explanation about why the permission is needed.
            // Fire off an async request to actually get the permission
            // This will show the standard permission request dialog UI
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                        READ_CONTACTS_PERMISSIONS_REQUEST);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        // Make sure it's our original READ_CONTACTS request
        if (requestCode == READ_CONTACTS_PERMISSIONS_REQUEST) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Read Contacts permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Read Contacts permission denied", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}