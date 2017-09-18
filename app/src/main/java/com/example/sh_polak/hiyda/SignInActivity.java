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

import static com.example.sh_polak.hiyda.R.id.eMail;

public class SignInActivity extends AppCompatActivity {
    EditText mName, mPassword, mConfirmPass, mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Resources r = getResources();
        String keyId = r.getString(R.string.keyId);
        String appId = r.getString(R.string.appId);
        Backendless.setUrl("http://api.backendless.com");
        Backendless.initApp(getApplicationContext(), appId, keyId);
        mName = (EditText) findViewById(R.id.userName);
        mPassword = (EditText) findViewById(R.id.password);
        mEmail = (EditText)findViewById(R.id.eMail);
        mConfirmPass = (EditText) findViewById(R.id.confirmPassword);
    }


    public void registerUser(View view) {
        String name = mName.getText().toString();
        String password = mPassword.getText().toString();
        String confimPass = mConfirmPass.getText().toString();
        String email = mEmail.getText().toString();
        final BackendlessUser user = new BackendlessUser();
        System.out.println("Test");
        if (true/*isValid(name, password, confimPass) && password.equals(confimPass)*/) {
            System.out.println("test02");
            user.setProperty("name", name);
            user.setEmail(email);
            user.setPassword(password);
            Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                @Override
                public void handleResponse(BackendlessUser response) {
                    Toast.makeText(getApplicationContext(), "נרשמת בהצלחה" + response.getObjectId(), Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Toast.makeText(getApplicationContext(), "Error occoured", Toast.LENGTH_SHORT).show();
                    System.out.println(fault.getMessage()+"------->"+fault.getCode());
                }
            });

        }
    }
    public Boolean isValid(String... valid) {
        if (!valid[0].isEmpty() && (!valid[1].isEmpty()) && (!valid[2].isEmpty())) {
            if ((valid[0].length() > 4) && (valid[1].length()) > 4 && (valid[2].length() > 4))
                return true;

        }
        return false;
    }

    @Override
    public void onBackPressed() {  // When pressing the BACK BUTTON in Sign Up Activity,will Animate with android's built in FADE IN & OUT
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
}

