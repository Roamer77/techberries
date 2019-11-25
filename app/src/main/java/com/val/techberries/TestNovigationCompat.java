package com.val.techberries;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TestNovigationCompat extends AppCompatActivity {

    private NavController navController;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_novigation_compat);

        navController= Navigation.findNavController(TestNovigationCompat.this,R.id.nav_host_fragment);
        bottomNavigationView=findViewById(R.id.navigation_bottom_bar);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

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
            return false;
        }
    }
}
