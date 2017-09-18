package com.example.sh_polak.hiyda;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sh_polak.hiyda.fragments.OverallFrag;
import com.example.sh_polak.hiyda.fragments.PartyRegisterFrag;

import java.util.HashMap;
import java.util.Map;

public class PublicitActivity extends AppCompatActivity {
TextView overAll,registerParty;
    private static Map<String,Fragment> fragments = new HashMap();
    static {//Map between fragment name and an Object
        fragments.put("overall", new OverallFrag());
        fragments.put("Register Party", new PartyRegisterFrag());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicit);
        overAll=(TextView)findViewById(R.id.OverallInfo);
        registerParty=(TextView)findViewById(R.id.RegisterParty);

    }
    public void chooseFrag(View view){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        String name =((TextView)view).getText().toString();
        Fragment fragment=fragments.get(name);
        transaction.replace(R.id.section,fragment);
        transaction.addToBackStack(name);
        transaction.commit();
    }
}
