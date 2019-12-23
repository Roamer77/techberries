package com.val.techberries.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
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
import com.val.techberries.utils.netWork.DataToServerSender;

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

                UserInfo userInfo = new UserInfo(userPhoneNumber, userLogin, userPassword);
                new SendAuthData().execute(userInfo);
                clearEditTextFields();
            }
        });
    }

    private class SendAuthData extends AsyncTask<UserInfo,Void,Void>{

        @Override
        protected Void doInBackground(UserInfo... args) {
            dataToServerSender.sendAuthDataToServer(args[0]);
            return null;
        }
    }
    private void clearEditTextFields() {
        phoneNumber.setText("");
        login.setText("");
        password.setText("");
    }
}
