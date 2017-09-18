package com.example.sh_polak.hiyda;

import android.Manifest;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PartiesList extends AppCompatActivity {
    EditText partyName, partLocation;
    int REQUEST_IMAGE = 2;
    int WRITE_IMAGE = 5;
    int IMAGEREQUEST = 6;
    Uri selectedImage;
    ImageView partyImage;
    File f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parties_list);
        PremissionManger.check(this, Manifest.permission.READ_EXTERNAL_STORAGE, REQUEST_IMAGE);
        PremissionManger.check(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, WRITE_IMAGE);
        Resources r = getResources();
        Backendless.initApp(getApplicationContext(), r.getString(R.string.appId), r.getString(R.string.keyId));
        partyName = (EditText) findViewById(R.id.partyName);
        partLocation = (EditText) findViewById(R.id.location);
        partyImage = (ImageView) findViewById(R.id.partyImage);
    }

    public void getImage(View view) {
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, IMAGEREQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String a = "";
        System.out.println(a.length());
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println(resultCode);
        if ((resultCode == RESULT_OK) && (requestCode == IMAGEREQUEST)){
            partyImage.setImageURI(data.getData());
            selectedImage = data.getData();
            //String path = selectedImage.getPath();
            f = new File(getPath(selectedImage));
            Backendless.Files.upload(f, "PartyImages", new AsyncCallback<BackendlessFile>() {
                @Override
                public void handleResponse(BackendlessFile response) {
                    System.out.println("sucssessfull");

                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    System.out.println("error" + fault.getMessage());

                }
            });

        }
    }

    // }
    public String getPath(Uri uri) {

        if (uri == null) {
            return null;
        }//u dont get the full path from the intent so u have to call a method that do that for u
        // this will only work for images selected from gallery source stackoverflow
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return uri.getPath();
    }

    public void uploadFileAData(View view) {
        String partyName=this.partyName.getText().toString();
        String partyLocation=this.partLocation.getText().toString();
        if((!partyName.isEmpty())&&(!partyLocation.isEmpty())){
        HashMap data=new HashMap();
        data.put("name",partyName);
        data.put("Location",partyLocation);
        data.put("PartyIMage",f.getName());
        Backendless.Persistence.of("A_publicist_user").save(data,new AsyncCallback<Map>() {
            @Override
            public void handleResponse(Map response) {
                Toast.makeText(getApplicationContext(),"upload data successfully",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PartiesList.this,LoginActivity.class));
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(getApplicationContext(),"Error Occuered",Toast.LENGTH_SHORT).show();
                System.out.println(fault.getDetail());
            }
        });

    }else {
            Toast.makeText(getApplicationContext(),"Wrong Input",Toast.LENGTH_SHORT).show();
        }
    }
}
