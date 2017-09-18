package com.example.sh_polak.hiyda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.sh_polak.hiyda.adapters.PartiesAdapter;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
  Menu menu;
Parties[]list=new Parties[]{new Parties("הסלינה","תל אביב","msiba","21:00"),new Parties("הפאנג'ויה מלון המלך שלמה","אילת","fanjoya","16:00 21/01/18"),new Parties("הסלינה","תל אביב","msiba","21:00"),new Parties("הסלינה","תל אביב","msiba","21:00"),new Parties("הסלינה","תל אביב","msiba","21:00")};
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.partyList);
        listView.setAdapter(new PartiesAdapter(this,R.layout.row,list));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuselection,menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);

    }


    private void loadBEExample(){
        Backendless.Data.of("tblName").find(new AsyncCallback<List<Map>>() {
            @Override
            public void handleResponse(List<Map> response) {

            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }

    /*

     */
}