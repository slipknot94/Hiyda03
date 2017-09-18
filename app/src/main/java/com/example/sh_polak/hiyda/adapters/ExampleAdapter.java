package com.example.sh_polak.hiyda.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by hackeru on 17/09/2017.
 */

public class ExampleAdapter extends BaseAdapter {

    private final List<Map> results;
    private final Context context;

    public ExampleAdapter(List<Map> results, Context context){
        this.results = results;
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        if(convertView == null) ;//TODO - create your view and assign to converView

        //TextView txt = (TextView)convertView.findViewById(R.id.text);//TODO - must to have TextView by id text
       // txt.setText(results.get(i).get("text").toString());//get relevant property by name "text"

        return convertView;
    }
}
