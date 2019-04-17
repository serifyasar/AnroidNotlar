package com.serifyasargmail.fixnfix;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by eddai on 13.11.2017.
 */

public class RepairAdapter extends ArrayAdapter<Repair>{

    Repair repair;

    public RepairAdapter(Context context, int resource, List<Repair> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View v=convertView;

        if(v==null){
            LayoutInflater vi=LayoutInflater.from(getContext());
            v=vi.inflate(R.layout.listview_layout_temp,null);

        }
        if (position % 2 == 1) {
            v.setBackgroundColor(Color.parseColor("#f5f5f5"));
        } else {
            v.setBackgroundColor(Color.WHITE);
        }



        TextView tvad= (TextView) v.findViewById(R.id.textView2);
        TextView tvtel= (TextView) v.findViewById(R.id.textView4);
        repair=getItem(position);
        if(repair!=null){


            if(tvad!=null && tvtel!=null){
                tvad.setText(repair.getName());
                tvtel.setText(repair.getPhone());
            }

        }


        return v;
    }
}
