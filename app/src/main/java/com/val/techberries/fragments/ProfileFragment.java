package com.val.techberries.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.val.techberries.R;
import com.val.techberries.interfacies.CallBackToSaveUserInfo;
import com.val.techberries.utils.NoScrollArrayList;
import com.val.techberries.utils.netWork.DataToServerSender;
import com.val.techberries.utils.netWork.InternetConnectionChecker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ProfileFragment extends Fragment {

    private NoScrollArrayList infoList;
    private ConstraintLayout countryPicker;
    private Button openLoginFragment;
    private Button logoutBtn;

    private SharedPreferences sharedPreferences;

    private DataToServerSender dataToServerSender;

    private InternetConnectionChecker internetConnectionChecker;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_profile,null);
        logoutBtn=view.findViewById(R.id.logout_from_account);
        dataToServerSender=new DataToServerSender();
        sharedPreferences=getActivity().getPreferences(Context.MODE_PRIVATE);

        internetConnectionChecker=new InternetConnectionChecker(getActivity().getApplication());
        internetConnectionChecker.execute();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        doActionsIfNoInternetConnection(view);

        infoList =view. findViewById(R.id.listOfInformation);
        countryPicker=view. findViewById(R.id.countryPicker);
        openLoginFragment=view.findViewById(R.id.open_login_fragment);


        ArrayList<String> titles = new ArrayList<>();
        Collections.addAll(titles, "Доставка", "Как сделать заказ", "Способы оплаты", "Возврат товара", "О нас",
                "Наши скидки", "Контакты", "Публичная оферта", "Правила продажи", "Сертефикаты",
                "О приложении", "Пункты самовывоза");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.info_list_item, titles);
        infoList.setAdapter(adapter);

        countryPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"KEK",Toast.LENGTH_LONG).show();
            }
        });

        openLoginFragment.setOnClickListener(v-> Navigation.findNavController(view).navigate(R.id.registrationFragment));
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                    dataToServerSender.logoutFromAccount(new CallBackToSaveUserInfo() {
                        @Override
                        public void isOk(int state) {
                            Log.e("MyTag","logoutBtn.logoutFromAccount: Разлогинился "+state);
                            editor.putInt("Login",0);
                            editor.commit();
                        }

                        @Override
                        public void onError(String error) {

                        }
                    });

            }
        });
    }

    private void doActionsIfNoInternetConnection(View view){
        internetConnectionChecker=new InternetConnectionChecker(getActivity().getApplication());
        internetConnectionChecker.execute();
        try {
            boolean internet= internetConnectionChecker.get(1, TimeUnit.SECONDS);
            if(internet){
                Log.e("MyTag","Интернет ЕСТЬ");
            }else {
                Log.e("MyTag","Интернет НЕТ");

                Bundle data=new Bundle();
                data.putInt("FragmentThatIsNotHaveInternet",R.id.profileFragment);
                Navigation.findNavController(view).navigate(R.id.noInternetConection,data);

            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
