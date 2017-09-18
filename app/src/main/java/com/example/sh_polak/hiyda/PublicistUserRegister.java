package com.example.sh_polak.hiyda;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class PublicistUserRegister extends AppCompatActivity {
        EditText mName, mPassword,mEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicist_user);
        Resources r=getResources();
        String keyId = r.getString(R.string.keyId);
        String appId = r.getString(R.string.appId);
        Backendless.initApp(getApplicationContext(), appId, keyId);
        mName = (EditText) findViewById(R.id.nameRegist);
        mPassword = (EditText) findViewById(R.id.passwordRegist);
        mEmail = (EditText) findViewById(R.id.emailRegist);

    }

    public void RegisterPublicit(View view) {
        String name = mName.getText().toString();
        String password = mPassword.getText().toString();
        String email = mEmail.getText().toString();
        final BackendlessUser user = new BackendlessUser();
        if (isValid(name, password, email)) {
            user.setEmail(email);
            user.setPassword(password);
            user.setProperty("name", name);
            isValid(name, password, email);
            Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                @Override
                public void handleResponse(BackendlessUser response) {
                    Toast.makeText(getApplicationContext(), "Register successful", Toast.LENGTH_LONG).show();
                    finish();
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    System.out.println(fault.getDetail());
                }
            });
        }else
        Toast.makeText(this,"unValid input",Toast.LENGTH_LONG).show();
    }
    public Boolean isValid(String... valid) {
        if (!valid[0].isEmpty() && (!valid[1].isEmpty()) && (!valid[2].isEmpty())) {
            if ((valid[0].length() > 4) && (valid[1].length()) > 4 && (valid[2].length() > 4))
                return true;

        }
        return false;
    }
}
