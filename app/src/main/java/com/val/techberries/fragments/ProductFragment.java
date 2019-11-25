package com.val.techberries.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.val.techberries.R;
import com.val.techberries.adaptors.ViewPagerForProductActivity;

import java.util.ArrayList;
import java.util.Collections;

import me.relex.circleindicator.CircleIndicator;

public class ProductFragment extends Fragment {
    private ViewPagerForProductActivity adaptor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_product_avtivity, null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Integer> dataForViewPager = new ArrayList<>(); //к примеру один тип курток
        ArrayList<Integer> dataForViewPager_2 = new ArrayList<>(); // это к примеру второй тип курток
        ArrayList<Integer> dataForViewPager_def = new ArrayList<>(); // это дефолтные картинки для того случаяя если данные ещё не загрузились с сервера

        Collections.addAll(dataForViewPager, R.drawable.product_img0,
                R.drawable.product_img1,
                R.drawable.product_img2);

        Collections.addAll(dataForViewPager_2, R.drawable.ked1,
                R.drawable.ked2,
                R.drawable.ked3);

        Collections.addAll(dataForViewPager_def, R.drawable.ic_facebook,
                R.drawable.ic_ok,
                R.drawable.ic_telegram);


        String name = getArguments().getString("ProductName"); // в зависимости от имени товара отображаеться нужная колекция. Позже это будут запросы в базу данных
        Log.e("MyTag", "Name from extras " + name);
        switch (name) {
            case "Куртка 1":
                adaptor = new ViewPagerForProductActivity(getActivity(), dataForViewPager);
                break;
            case "Куртка 2":
                adaptor = new ViewPagerForProductActivity(getActivity(), dataForViewPager_2);
                break;
            default:
                adaptor = new ViewPagerForProductActivity(getActivity(), dataForViewPager_def);
        }


        ViewPager viewPager = view.findViewById(R.id.ProductImage_viewPager);

        viewPager.setAdapter(adaptor);
        CircleIndicator circleIndicator = view.findViewById(R.id.circleIndicator_for_viewPager);
        circleIndicator.setViewPager(viewPager);
    }
}
