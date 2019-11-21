package com.val.techberries.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.val.techberries.Entities.Item;
import com.val.techberries.R;
import com.val.techberries.adaptors.GridViewAdaptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductListByCategoryActivity extends AppCompatActivity {

    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list_by_category);

        gridView=findViewById(R.id.prodctListGridView);

        ArrayList<Item> data = new ArrayList<>();
        Collections.addAll(data,
                new Item("Кеды 1", R.drawable.ked1),
                new Item("Кеды 2", R.drawable.ked2),
                new Item("Кеды 3", R.drawable.ked3),
                new Item("Кеды 4", R.drawable.ked4));

        GridViewAdaptor gridViewAdaptor =new GridViewAdaptor(this,data);
        gridView.setAdapter(gridViewAdaptor);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Нажал на "+position,Toast.LENGTH_LONG).show();

            }
        });
    }
}
