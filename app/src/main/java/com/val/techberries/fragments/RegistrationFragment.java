package com.val.techberries.fragments;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.val.techberries.R;
import com.val.techberries.entities.entitiesForNetWork.UserInfo;
import com.val.techberries.utils.netWork.DataToServerSender;

public class RegistrationFragment extends Fragment {

    private TextView iHaveAccountTextView;

    private EditText phoneNumber;
    private EditText login;
    private EditText password;

    private Button sendBtn;

    private DataToServerSender serverSender;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registration_layout, null);

        phoneNumber = view.findViewById(R.id.phone_edittext_registration);
        login = view.findViewById(R.id.login_edittext_registration);
        password = view.findViewById(R.id.password_edittext_registration);
        sendBtn = view.findViewById(R.id.reg_btn_registration);
        iHaveAccountTextView = view.findViewById(R.id.i_have_Account_textView);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        serverSender = new DataToServerSender();


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userPhoneNumber = phoneNumber.getText().toString();
                String userLogin = login.getText().toString();
                String userPassword = password.getText().toString();

                UserInfo userInfo = new UserInfo(userPhoneNumber, userLogin, userPassword);

                Toast.makeText(getContext(), "Данные отправлены", Toast.LENGTH_SHORT).show();
                new SendUserInfo().execute(userInfo);
                clearEditTextFields();
            }
        });

        iHaveAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iHaveAccountTextView.setTextColor(getActivity().getResources().getColor(R.color.backgroundArea_Color));
                goToLoginFragment(v);
            }
        });
    }

    private class SendUserInfo extends AsyncTask<UserInfo, Void, Void> {
        @Override
        protected Void doInBackground(UserInfo... args) {
            serverSender.sendUserRegistrationInfo(args[0]);
            return null;
        }

    }

    private void clearEditTextFields() {
        phoneNumber.setText("");
        login.setText("");
        password.setText("");
    }

    private void goToLoginFragment(View view) {
        Navigation.findNavController(view).navigate(R.id.loginFragment);
    }
}
