package com.val.techberries.fragments.utileFragments;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.val.techberries.R;
import com.val.techberries.utils.netWork.InternetConnectionChecker;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class NoInternetConection extends Fragment {

    private Button refreshBtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.no_internat_connection_layout, null);
        refreshBtn=view.findViewById(R.id.refreshLayoutBtn);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        refreshBtn.setOnClickListener(v -> {
            Log.e("MyTag","Нажался");
            new InternetConnectionChecker(view).execute();
        });
    }

    public class InternetConnectionChecker extends AsyncTask<Void,Void,Boolean> {
        private View view;

        public InternetConnectionChecker(View view) {
            this.view = view;
        }

        @Override
        public Boolean doInBackground(Void... voids) {

            return checkInternetConnection();
        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            if(aVoid){
                    int fragmentForNavigation=getArguments().getInt("FragmentThatIsNotHaveInternet");
                    Log.e("MyTag","Куда нужно прыгнуть: "+fragmentForNavigation);
                    Navigation.findNavController(view).navigate(fragmentForNavigation);
            }else {
                Log.e("MyTag","некуда идти");
                Toast.makeText(getContext(),"Включите интернет",Toast.LENGTH_SHORT).show();
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
}
