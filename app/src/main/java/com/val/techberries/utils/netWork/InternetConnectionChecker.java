package com.val.techberries.utils.netWork;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.navigation.Navigation;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class InternetConnectionChecker extends AsyncTask<Void,Void,Boolean> {
    private Application application;

    public InternetConnectionChecker(Application application) {
        this.application = application;
    }

    @Override
    public Boolean doInBackground(Void... voids) {

        return checkInternetConnection();
    }

    @Override
    protected void onPostExecute(Boolean aVoid) {
        if(aVoid){
            Toast.makeText(application,"Интернет есть !!",Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(application,"Интернет нет !!",Toast.LENGTH_LONG).show();
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
}
