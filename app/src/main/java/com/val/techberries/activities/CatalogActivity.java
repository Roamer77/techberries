package com.val.techberries.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.val.techberries.R;
import com.val.techberries.adaptors.CustomExpandableListAdaptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CatalogActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
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
        catalog.put("Раздел 1",childList);
        catalog.put("Раздел 2",childList2);
        catalog.put("Раздел 3",childList3);

        ArrayList<String> groupTitles=new ArrayList<>();
        Collections.addAll(groupTitles,"Раздел 1","Раздел 2","Раздел 3");

        expandableListView =(ExpandableListView) findViewById(R.id.Catalog_elv);
        expandableListAdapter=new CustomExpandableListAdaptor(this,groupTitles,catalog);
        expandableListView.setAdapter(expandableListAdapter);


    }
}
