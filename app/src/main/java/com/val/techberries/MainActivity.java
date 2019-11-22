package com.val.techberries;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.val.techberries.Entities.Item;
import com.val.techberries.activities.CartActivity;
import com.val.techberries.activities.CatalogActivity;
import com.val.techberries.activities.ProductActivity;
import com.val.techberries.activities.ProductListByCategoryActivity;
import com.val.techberries.activities.ProfileActivity;
import com.val.techberries.adaptors.RecyclerViewAdaptor;
import com.val.techberries.interfacies.OnRecyclerViewItemClick;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.relex.circleindicator.CircleIndicator2;

public class MainActivity extends AppCompatActivity {

    private Toolbar customMainToolBar;
    private LinearLayout searchBar;
    private String titleForToolBar;

    private RecyclerView firstRecyclerView;
    private RecyclerView secondRecyclerView;
    private RecyclerView thirdRecyclerView;


    private Button listOpenButton;
    private Button cartOpenButton;
    private Button profileOpenButton;
    private CircleIndicator2 circleIndicator;
    private CircleIndicator2 circleIndicator2;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Item> testData = new ArrayList<>();
        Collections.addAll(testData, new Item("Куртка 1", R.drawable.product_img0),
                new Item("Куртка 2", R.drawable.product_img1),
                new Item("Куртка 3", R.drawable.product_img2),
                new Item("Куртка 1", R.drawable.product_img0),
                new Item("Куртка 2", R.drawable.product_img1),
                new Item("Куртка 3", R.drawable.product_img2));

        List<Item> testData2 = new ArrayList<>();
        Collections.addAll(testData2, new Item("", R.drawable.prod_place1),
                new Item("", R.drawable.prod_place2),
                new Item("", R.drawable.prod_place3));

        List<Item> testData3 = new ArrayList<>();
        Collections.addAll(testData3,
                new Item("Кеды 1", R.drawable.ked1),
                new Item("Кеды 2", R.drawable.ked2),
                new Item("Кеды 3", R.drawable.ked3),
                new Item("Кеды 4", R.drawable.ked4));



        listOpenButton = findViewById(R.id.listBtn_mainActivity);
        cartOpenButton = findViewById(R.id.cartBtn_mainActivity);
        profileOpenButton=findViewById(R.id.accountBtn_mainActivity);

        //первый ресайклер. Для одиночных товаров
        firstRecyclerView = findViewById(R.id.firstRecyclerView);
        RecyclerViewAdaptor recyclerViewAdaptor=new RecyclerViewAdaptor(testData, this,R.layout.first_recycler_view_item);
        firstRecyclerView.setAdapter(recyclerViewAdaptor);
        firstRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        firstRecyclerView.setLayoutManager(layoutManager);

        //второй ресайклер. Он будет для рекламы
        secondRecyclerView = findViewById(R.id.secondRecyclerView);
        RecyclerViewAdaptor recyclerViewAdaptor2=new RecyclerViewAdaptor(testData2, this,R.layout.first_recycler_view_item);
        secondRecyclerView.setAdapter(recyclerViewAdaptor2);
        secondRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager2
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        secondRecyclerView.setLayoutManager(layoutManager2);


        //третий ресайклер
        thirdRecyclerView=findViewById(R.id.thirdRecyclerView);
        thirdRecyclerView.setAdapter(new RecyclerViewAdaptor(testData3,this,R.layout.third_recycler_view_item));
        LinearLayoutManager  linearLayoutManager3=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        thirdRecyclerView.setLayoutManager(linearLayoutManager3);

        toolbar = findViewById(R.id.customToolbar);
        toolbar.inflateMenu(R.menu.notification);

        //добавляет точки под ресайклер вью
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        PagerSnapHelper pagerSnapHelper2 = new PagerSnapHelper();
        pagerSnapHelper2.attachToRecyclerView(secondRecyclerView);
        pagerSnapHelper.attachToRecyclerView(firstRecyclerView);

        circleIndicator = findViewById(R.id.circleIndicator_1_NestedScroll_View);
        circleIndicator2 = findViewById(R.id.circleIndicator_2_NestedScroll_View);

        circleIndicator.attachToRecyclerView(firstRecyclerView, pagerSnapHelper);
        circleIndicator2.attachToRecyclerView(secondRecyclerView,pagerSnapHelper);

        toolbar.setOnMenuItemClickListener(item -> {

            if (item.getItemId() == R.id.notification) {
                //открытие новой активити с уведомлениями
                Log.d("MyTag", "Нажал на уведомления");
                Toast.makeText(getApplicationContext(),"Нажал на уведомления",Toast.LENGTH_LONG).show();

                startActivity( new Intent(MainActivity.this, ProductListByCategoryActivity.class));
                return true;
            } else {
                return false;
            }
        });
        listOpenButton.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, CatalogActivity.class);
            startActivity(intent);
        });

        cartOpenButton.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        });


        profileOpenButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        recyclerViewAdaptor.setItemClickListener(item -> {

            Toast.makeText(getApplicationContext(),"Нажал на "+ item.getItemName(),Toast.LENGTH_LONG).show();

            Intent intent=new Intent(MainActivity.this,ProductActivity.class);
            intent.putExtra("ProductName",item.getItemName());
            startActivity(intent);
        });

        //на картинки можно кликать поментка на будующее
        ImageView test=findViewById(R.id.ic_facebook_soсial_network);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Нажал на facebook",Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notification, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.notification) {
            //открытие новой активити с уведомлениями
            Log.d("MyTag", "Нажал на уведомления");

            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

}
