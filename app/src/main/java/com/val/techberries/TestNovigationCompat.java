package com.val.techberries;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

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

        new InternetConnectionChecker().execute();
    }

    @Override
    protected void onRestart() {
        new InternetConnectionChecker().execute();
        super.onRestart();
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

    private boolean checkInternetConnection(){
        try {
            int timeoutMs = 1500;
            Socket sock = new Socket();
            SocketAddress sockaddr = new InetSocketAddress("8.8.8.8", 53);

            sock.connect(sockaddr, timeoutMs);
            sock.close();

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private class InternetConnectionChecker extends AsyncTask<Void,Void,Boolean>{
        @Override
        protected Boolean doInBackground(Void... voids) {

            return checkInternetConnection();
        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            if(!aVoid){
                Bundle data=new Bundle();
                data.putInt("FragmentThatIsNotHaveInternet",R.id.homeFragment);
                navController.navigate(R.id.noInternetConection,data);
                Toast.makeText(getApplicationContext(),"Интернет нет !!",Toast.LENGTH_LONG).show();
            }
        }
    }
}
