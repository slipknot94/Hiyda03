package com.example.sh_polak.hiyda;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.dd.CircularProgressButton;
import com.dd.processbutton.ProcessButton;
import com.dd.processbutton.iml.ActionProcessButton;

public class LoginActivity extends AppCompatActivity {


    EditText mName,mPassword,mEmail; // Declaring EditText variables
    SharedPreferences preferences;
    boolean stayRegister=false;
    CheckBox checkBox;
    CircularProgressButton login;
    ProcessButton test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Resources r=getResources();
        //String keyId = r.getString(R.string.keyId);
        //String appId = r.getString(R.string.appId);
        //Backendless.setUrl("http://api.backendless.com");
        Backendless.initApp(getApplicationContext(), r.getString(R.string.appId),r.getString(R.string.keyId));
        mName=(EditText)findViewById(R.id.loginName);
        mPassword=(EditText)findViewById(R.id.loginPass);
        mEmail=(EditText)findViewById(R.id.loginEmail);
        checkBox=(CheckBox)findViewById(R.id.stayIn);
        Typeface tf = Typeface.createFromAsset(getAssets(), "superfruit.ttf");
        TextView tv = (TextView) findViewById(R.id.title);
        tv.setTypeface(tf);

        preferences=getSharedPreferences("User",MODE_PRIVATE);
        Backendless.Messaging.registerDevice("23206336394", "default", new AsyncCallback<Void>() {
            @Override
            public void handleResponse(Void response) {
                Toast.makeText(getApplicationContext(),"This phone registered succseefuly",Toast.LENGTH_LONG).show();
            }
            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(getApplicationContext(),fault.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
     if(!preferences.getString("name","").isEmpty())
         startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }

    public void Login(View view) {
        final String name=mName.getText().toString();
        String password=mPassword.getText().toString();
        final String email=mEmail.getText().toString();
        Backendless.UserService.login(name, password, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                if(checkBox.isChecked()) 
                    preferences.edit().putString("name",name);
                    Toast.makeText(getApplicationContext(), "ברוך הבא", Toast.LENGTH_SHORT).show();
                    if (email.isEmpty()) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {
                        startActivity(new Intent(getApplicationContext(), PublicitActivity.class));
                    }

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                startActivity(new Intent(getApplicationContext(), PublicitActivity.class));
                Toast.makeText(getApplicationContext(),"Error occoured",Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void registerActivty(View view) {

        startActivity(new Intent(getApplicationContext(),SignInActivity.class));
        overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_exit);  //ANIMATION - slides in when presing "not a member?sign up now" and moves to SignInActivity
    }

    public void VipUser(View view) {
        startActivity(new Intent(this,PublicistUserRegister.class));
    }

}
