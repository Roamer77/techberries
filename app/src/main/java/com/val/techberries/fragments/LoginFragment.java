package com.val.techberries.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.val.techberries.R;
import com.val.techberries.entities.entitiesForNetWork.UserInfo;
import com.val.techberries.interfacies.CallBackToSaveUserInfo;
import com.val.techberries.utils.netWork.DataToServerSender;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class LoginFragment extends Fragment {

    private EditText phoneNumber;
    private EditText login;
    private EditText password;
    private Button sendBtn;

    private DataToServerSender dataToServerSender;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.authorithation_layout, null);

        phoneNumber = view.findViewById(R.id.phone_edittext_auth);
        login = view.findViewById(R.id.login_edittext_auth);
        password = view.findViewById(R.id.password_edittext_auth);
        sendBtn = view.findViewById(R.id.send_btn_auth);

        dataToServerSender=new DataToServerSender();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userPhoneNumber = phoneNumber.getText().toString();
                String userLogin = login.getText().toString();
                String userPassword = password.getText().toString();
                UserInfo userInfo=new UserInfo(userLogin,userPassword);
                new SendAuthData().execute(userInfo);
                clearEditTextFields();
            }
        });
    }

    private class SendAuthData extends AsyncTask<UserInfo,Void,Void>{
        SharedPreferences sharedPreferences=getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        @Override
        protected Void doInBackground(UserInfo... args) {
           OkHttpClient auth=new OkHttpClient.Builder().authenticator((route, response) -> {
                Request request = response.request();
                if (request.header("Authorization") != null){
                    Log.e("MyTag","Пароль неверный");
                    return null;
                }
                return request.newBuilder()
                        .header("Authorization", Credentials.basic(args[0].getUserLogin(),args[0].getUserPassword()))
                        .build();
            }).build();
           dataToServerSender.sendAuthDataToServer(auth, new CallBackToSaveUserInfo() {
                @Override
                public void isOk(int state) {
                    Log.e("MyTag","SendAuthData всё ОК");
                    editor.putString("UserPassword",args[0].getUserPassword());
                    editor.putString("UserLogin",args[0].getUserLogin());
                    editor.putInt("Login",1);
                    editor.commit();
                    Log.e("MyTag","SendAuthData сохранил всё в sharedPreferences ОК");
                }

               @Override
               public void onError(String error) {
                   Log.e("MyTag","SendAuthData не ОК: "+error);
                   editor.putInt("Login",0);
               }
           });
            return null;
        }
    }
    private void clearEditTextFields() {
        phoneNumber.setText("");
        login.setText("");
        password.setText("");
    }
}
