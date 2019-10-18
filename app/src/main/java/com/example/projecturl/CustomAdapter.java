package com.example.projecturl;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<String> arrayList;
    private Context context;


    public CustomAdapter(Context context, ArrayList<String> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_item, null);

        }

        TextView tvUrl = view.findViewById(R.id.tv_1);
        Button delete = view.findViewById(R.id.btn_1);
        final String item = arrayList.get(position);

        tvUrl.setText(item);

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(context, item, Toast.LENGTH_SHORT).show();
                Intent mIntent = new Intent(context, MainActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("Url", item);
                mIntent.putExtras(mBundle);
                context.startActivity(mIntent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String str = arrayList.get(position);
                SharedPreferences sharedPreferences = context.getSharedPreferences((Constants.MyPreferences), Context.MODE_PRIVATE);
                sharedPreferences.edit().remove(str).apply();
                arrayList.remove(position);
                notifyDataSetChanged();
            }
        });
        return view;
    }
}


