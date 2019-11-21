package com.val.techberries.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.val.techberries.Entities.Item;
import com.val.techberries.MainActivity;
import com.val.techberries.R;
import com.val.techberries.adaptors.ViewPagerForProductActivity;

import java.util.ArrayList;
import java.util.Collections;

import me.relex.circleindicator.CircleIndicator;

public class ProductActivity extends AppCompatActivity {

    private Button homeButton;
    private ViewPagerForProductActivity adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_avtivity);

        ArrayList<Integer> dataForViewPager = new ArrayList<>(); //к примеру один тип курток
        ArrayList<Integer> dataForViewPager_2 = new ArrayList<>(); // это к примеру второй тип курток
        ArrayList<Integer> dataForViewPager_def = new ArrayList<>(); // это дефолтные картинки для того случаяя если данные ещё не загрузились с сервера

        Collections.addAll(dataForViewPager, R.drawable.product_img0,
                R.drawable.product_img1,
                R.drawable.product_img2);

        Collections.addAll(dataForViewPager_2, R.drawable.ked1,
                R.drawable.ked2,
                R.drawable.ked3);

        Collections.addAll(dataForViewPager_def, R.drawable.ic_facebook,
                R.drawable.ic_ok,
                R.drawable.ic_telegram);

        Bundle data = getIntent().getExtras();
        String name=data.getString("ProductName"); // в зависимости от имени товара отображаеться нужная колекция. Позже это будут запросы в базу данных
        Log.e("MyTag","Name from extras "+name);
        switch (name) {
            case "Куртка 1":
               adaptor = new ViewPagerForProductActivity(this, dataForViewPager);
                break;
            case "Куртка 2":
                adaptor = new ViewPagerForProductActivity(this, dataForViewPager_2);
                break;
            default:
                adaptor = new ViewPagerForProductActivity(this, dataForViewPager_def);
        }


        ViewPager viewPager = findViewById(R.id.ProductImage_viewPager);

        viewPager.setAdapter(adaptor);
        CircleIndicator circleIndicator = findViewById(R.id.circleIndicator_for_viewPager);
        circleIndicator.setViewPager(viewPager);

        homeButton = findViewById(R.id.homeButton_on_ProductActivity);
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProductActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
