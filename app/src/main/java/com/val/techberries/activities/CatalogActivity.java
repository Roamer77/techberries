package com.val.techberries.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.val.techberries.MainActivity;
import com.val.techberries.R;
import com.val.techberries.Utils.NoScrollListView;
import com.val.techberries.adaptors.CustomExpandableListAdaptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CatalogActivity extends AppCompatActivity {

    private NoScrollListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;

    private Button homeOpenButton;
    private Button listOpenButton;
    private Button cartOpenButton;
    private Button profileOpenButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        List<String> childList=new ArrayList<>();
        Collections.addAll(childList,"1","2","3");

        List<String> childList2=new ArrayList<>();
        Collections.addAll(childList2,"a","b","c");

        List<String> childList3=new ArrayList<>();
        Collections.addAll(childList3,"a1","b2","c3");

        HashMap<String,List<String>> catalog = new HashMap<>();
        catalog.put("Мужчинам",childList);
        catalog.put("Женщинам",childList2);
        catalog.put("Детям",childList3);
        catalog.put("Спорт",childList);

        ArrayList<String> groupTitles=new ArrayList<>();
        Collections.addAll(groupTitles,"Мужчинам","Женщинам","Детям","Спорт");

        ArrayList<Integer> imagesForGroupItems=new ArrayList<>();
        Collections.addAll(imagesForGroupItems,R.drawable.ic_man,R.drawable.ic_wonem,R.drawable.ic_child,R.drawable.ic_sport);

        expandableListView =(NoScrollListView) findViewById(R.id.Catalog_elv);
        expandableListAdapter=new CustomExpandableListAdaptor(this,groupTitles,catalog,imagesForGroupItems);
        expandableListView.setAdapter(expandableListAdapter);

        homeOpenButton = findViewById(R.id.homeButton_cartActivity);
        listOpenButton = findViewById(R.id.listBtn_cartActivity);
        cartOpenButton = findViewById(R.id.cartBtn_cartActivity);
        profileOpenButton=findViewById(R.id.accountBtn_cartActivity);

        listOpenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CatalogActivity.this, CatalogActivity.class);
                startActivity(intent);
            }
        });

        cartOpenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CatalogActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        homeOpenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CatalogActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        profileOpenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);*/
            }
        });

    }
}
