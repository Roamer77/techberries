package com.val.techberries.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.val.techberries.Entities.Item;
import com.val.techberries.R;
import com.val.techberries.adaptors.GridViewAdaptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductListByCategoryActivity extends AppCompatActivity {

    private GridView gridView;
    private Button gridViewStyleBtn;
    private Button filterListBtn;
    private Button filterByPopularityBtn;
    private Integer styleModeID=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list_by_category);

        gridView=findViewById(R.id.prodctListGridView);
        gridViewStyleBtn=findViewById(R.id.styleModeBtn);

        filterByPopularityBtn=findViewById(R.id.filterByPopularity);
        filterListBtn=findViewById(R.id.filterListBtn);

        ArrayList<Item> data = new ArrayList<>();
        Collections.addAll(data,
                new Item("Кеды 1", R.drawable.ked1),
                new Item("Кеды 2", R.drawable.ked2),
                new Item("Кеды 3", R.drawable.ked3),
                new Item("Кеды 4", R.drawable.ked4),new Item("Кеды 1", R.drawable.ked1),
                new Item("Кеды 2", R.drawable.ked2),
                new Item("Кеды 3", R.drawable.ked3),
                new Item("Кеды 4", R.drawable.ked4),
                new Item("Кеды 1", R.drawable.ked1),
                new Item("Кеды 2", R.drawable.ked2),
                new Item("Кеды 3", R.drawable.ked3),
                new Item("Кеды 4", R.drawable.ked4),
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

       gridViewStyleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridView.setNumColumns(styleModeID);
                if(styleModeID<2){
                    styleModeID=styleModeID+1;
                }else {
                    styleModeID=1;
                }
            }
        });


       filterByPopularityBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               AlertDialog.Builder alertDialog=new AlertDialog.Builder(ProductListByCategoryActivity.this);
               initTipDialog(alertDialog);
           }
       });

    }
    private void initTipDialog(AlertDialog.Builder alert) {
        View customLayout=getLayoutInflater().inflate(R.layout.filter_list_for_filter_dialog,null);

        ArrayList<String> filters=new ArrayList<>();
        Collections.addAll(filters,"По цене","По производителю","По размеру");
        ListView listView=customLayout.findViewById(R.id.filter_list_for_filter_dialog);
        listView.setAdapter(new ArrayAdapter<String>(ProductListByCategoryActivity.this,R.layout.info_list_item,filters));

        alert.setView(customLayout);
        AlertDialog dialog =alert.create();
        dialog.show();
    }

    private void fillTheFilterList(){

    }
}
