package com.val.techberries.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.val.techberries.MainActivity;
import com.val.techberries.R;
import com.val.techberries.adaptors.ViewPagerForProductActivity;

import me.relex.circleindicator.CircleIndicator;

public class ProductActivity extends AppCompatActivity {

    private Button homeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_avtivity);

        ViewPager viewPager=findViewById(R.id.ProductImage_viewPager);
        ViewPagerForProductActivity adaptor=new ViewPagerForProductActivity(this);
        viewPager.setAdapter(adaptor);
        CircleIndicator circleIndicator=findViewById(R.id.circleIndicator_for_viewPager);
        circleIndicator.setViewPager(viewPager);

        homeButton=findViewById(R.id.homeButton_on_ProductActivity);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProductActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
