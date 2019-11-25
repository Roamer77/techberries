package com.val.techberries.fragments;

import android.os.Bundle;
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
import com.val.techberries.Utils.NoScrollArrayList;

import java.util.ArrayList;
import java.util.Collections;

public class ProfileFragment extends Fragment {

    private NoScrollArrayList infoList;
    private ConstraintLayout countryPicker;
    private Button openLoginFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_profile,null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

    }
}
