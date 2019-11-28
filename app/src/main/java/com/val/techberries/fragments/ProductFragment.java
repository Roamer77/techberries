package com.val.techberries.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.val.techberries.R;
import com.val.techberries.adaptors.ViewPagerForProductActivity;
import com.val.techberries.entities.Item;
import com.val.techberries.entities.ItemToUserCart;
import com.val.techberries.modelViews.ViewModelForHomePage;
import com.val.techberries.modelViews.viewModelsForDB.UserCartViewModel;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class ProductFragment extends Fragment {
    private ViewPagerForProductActivity adaptor;

    private Button addToCart;

    private UserCartViewModel userCartViewModel;
    private ViewModelForHomePage viewModelForHomePage;

    private TextView productCost;
    private TextView productName;
    private TextView productDescription;

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

        addToCart=view.findViewById(R.id.addToCart_Btn);

        productCost=view.findViewById(R.id.ProductCost);
        productName=view.findViewById(R.id.ProductName);
        productDescription=view.findViewById(R.id.ProductDescription);

        userCartViewModel= ViewModelProviders.of(this).get(UserCartViewModel.class);
        viewModelForHomePage=ViewModelProviders.of(this).get(ViewModelForHomePage.class);

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
        String cost=getArguments().getString("ProductCost");
        String description=getArguments().getString("ProductDescription");
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

        productName.append(name);
        productCost.append(cost);
        productDescription.append(description);

        ViewPager viewPager = view.findViewById(R.id.ProductImage_viewPager);

        viewPager.setAdapter(adaptor);
        CircleIndicator circleIndicator = view.findViewById(R.id.circleIndicator_for_viewPager);
        circleIndicator.setViewPager(viewPager);



        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Нажал на добавить в карзину",Toast.LENGTH_LONG);
                List<Item> tmpList=viewModelForHomePage.getDataForHomePageForFirstRecyclerView().getValue();
                Item tmpObj=new Item();
                for(int i=0;i<tmpList.size();i++){
                    if(tmpList.get(i).getItemName().equals(name)){
                        tmpObj=tmpList.get(i);
                        break;
                    }
                }

                if(tmpObj!=null){
                    userCartViewModel.insert(new ItemToUserCart(tmpObj.getCost(),tmpObj.getItemName(),tmpObj.getItemImage(),tmpObj.getDescription()));

                }

            }
        });

    }
}
