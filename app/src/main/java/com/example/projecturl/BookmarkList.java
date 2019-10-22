package com.example.projecturl;


import android.os.Bundle;

import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;





public class BookmarkList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmar_klist);
        ListView listView1 = findViewById(R.id.list_view);


        CustomAdapter adapter = new CustomAdapter(this, Utils.getAllSharedPrefList(this),getIntent());


        listView1.setAdapter(adapter);
    }
}



