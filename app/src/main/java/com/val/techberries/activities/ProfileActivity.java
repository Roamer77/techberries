package com.val.techberries.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.val.techberries.MainActivity;
import com.val.techberries.R;
import com.val.techberries.Utils.NoScrollArrayList;

import java.util.ArrayList;
import java.util.Collections;

public class ProfileActivity extends AppCompatActivity {

    private NoScrollArrayList infoList;
    private ConstraintLayout countryPicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        infoList = findViewById(R.id.listOfInformation);
        countryPicker=findViewById(R.id.countryPicker);



        ArrayList<String> titles = new ArrayList<>();
        Collections.addAll(titles, "Доставка", "Как сделать заказ", "Способы оплаты", "Возврат товара", "О нас",
                "Наши скидки", "Контакты", "Публичная оферта", "Правила продажи", "Сертефикаты",
                "О приложении", "Пункты самовывоза");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.info_list_item, titles);
        infoList.setAdapter(adapter);

        countryPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"KEK",Toast.LENGTH_LONG).show();
            }
        });


    }

    public void goToHomeActivity(View v){
        Intent intent=new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public  void goToCatalogActivity(View v){
        Intent intent=new Intent(ProfileActivity.this, CatalogActivity.class);
        startActivity(intent);
    }
    public void goToCartActivity(View v){
        Intent intent=new Intent(ProfileActivity.this, CartActivity.class);
        startActivity(intent);
    }
}
