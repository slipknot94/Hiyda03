package com.example.sh_polak.hiyda.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sh_polak.hiyda.Parties;
import com.example.sh_polak.hiyda.R;

/**
 * Created by sh-polak on 25/08/2017.
 */

public class PartiesAdapter extends ArrayAdapter {
    Context context;
    int imdata;
    Parties[] parties;
    TextView text,date,time;
    ImageView image;
    public PartiesAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull Parties[] objects) {
        super(context, resource, objects);
        this.context=context;
        this.imdata=resource;
        this.parties=objects;

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return parties[position];
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PartyHolder holder=null;
        View row=convertView;
        if(row==null) {
          row = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
            holder=new PartyHolder();
            holder.mDate= (TextView) row.findViewById(R.id.date);
            holder.mTime= (TextView) row.findViewById(R.id.time);
            holder.mText= (TextView) row.findViewById(R.id.name);
            holder.mIMageView= (ImageView) row.findViewById(R.id.image);
            row.setTag(holder);
        }else {
            holder = (PartyHolder)row.getTag();

        }
        Parties party= parties[position];
        int resId=context.getResources().getIdentifier(party.picture,"drawable",context.getPackageName());
        holder.mIMageView.setImageResource(resId);
        holder.mText.setText(party.name);
        holder.mTime.setText(party.time);
        holder.mDate.setText(party.place);
        return row;
    }
    private class PartyHolder{
        ImageView mIMageView;
        TextView mTime,mDate,mText;
    }
}
