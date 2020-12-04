package com.example.dresswardrobe;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adapter_lst extends ArrayAdapter<String>{

    Context context;
    List<String> list = new ArrayList<>();


    public Adapter_lst( Context context,int resource, List<String> list) {
        super(context, resource, list);
        this.context = context;

        this.list = list;


    }



    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {


        VersionHolder holder = new VersionHolder();

        if (convertView == null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.content_allow_sharing,null);


            holder.checkBox = convertView.findViewById(R.id.chbxshare);
            holder.textView = convertView.findViewById(R.id.textusr);

            holder.checkBox.setOnCheckedChangeListener((AllowSharing)context);
            holder.checkBox.setTag(position);
            convertView.setTag(holder);

        }else{
            holder = (VersionHolder) convertView.getTag();
        }

        String versions  = list.get(position);
        holder.textView.setText(versions);

        holder.checkBox.setTag(list);

        return convertView;
    }

    public static class VersionHolder{
        public CheckBox checkBox;
        public TextView textView;
    }
}