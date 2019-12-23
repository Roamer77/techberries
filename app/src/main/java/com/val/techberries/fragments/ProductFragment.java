package com.val.techberries.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.val.techberries.R;
import com.val.techberries.adaptors.RecyclerViewAdaptor;
import com.val.techberries.adaptors.ViewPagerForProductActivity;
import com.val.techberries.entities.Item;
import com.val.techberries.entities.ItemToUserCart;
import com.val.techberries.entities.entitiesForNetWork.ProductDescription;
import com.val.techberries.interfacies.MyCallBackToRepo;
import com.val.techberries.interfacies.MyCallMackForProdDescription;
import com.val.techberries.modelViews.ViewModelForHomePage;
import com.val.techberries.modelViews.ViewModelForProductFragment;
import com.val.techberries.modelViews.viewModelsForDB.UserCartViewModel;
import com.val.techberries.utils.DbBitMapUtility.DbBitmapUtility;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class ProductFragment extends Fragment {
    private ViewPagerForProductActivity adaptor;

    private  Item itemToCart;

    private Button addToCart;

    private UserCartViewModel userCartViewModel;
    private ViewModelForHomePage viewModelForHomePage;

    private RecyclerView similarProducts;
    private RecyclerView buyWithThisProduct;

    private TextView productCost;
    private TextView productName;
    private TextView productSeason;
    private TextView productSex;
    private TextView productMaterialDescription;

    private ViewModelForProductFragment viewModelForPridctFragment;
    private ArrayList<Bitmap> bigImages;

    private RecyclerViewAdaptor recyclerViewAdaptor;
    private RecyclerViewAdaptor recyclerViewAdaptor1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_product_avtivity, null);

        addToCart = view.findViewById(R.id.addToCart_Btn);

        productCost = view.findViewById(R.id.ProductCost);
        productName = view.findViewById(R.id.ProductName);
        productSeason = view.findViewById(R.id.ProductSeason);
        productMaterialDescription=view.findViewById(R.id.ProductMaterialDescription);
        productSex=view.findViewById(R.id.ProductSex);

        buyWithThisProduct =view.findViewById(R.id.buyWithThisRecyclerView);
        similarProducts=view.findViewById(R.id.similarItemsRecyclerView);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<Bitmap> dataForViewPager_def = new ArrayList<>(); // это дефолтные картинки для того случаяя если данные ещё не загрузились с сервера

        userCartViewModel = ViewModelProviders.of(this).get(UserCartViewModel.class);
        viewModelForHomePage = ViewModelProviders.of(this).get(ViewModelForHomePage.class);
        viewModelForPridctFragment = ViewModelProviders.of(this).get(ViewModelForProductFragment.class);

        itemToCart=new Item();

        Collections.addAll(dataForViewPager_def,
                BitmapFactory.decodeResource(getResources(), R.drawable.placeholder),
                BitmapFactory.decodeResource(getResources(), R.drawable.placeholder),
                BitmapFactory.decodeResource(getResources(), R.drawable.placeholder));


         // в зависимости от имени товара отображаеться нужная колекция. Позже это будут запросы в базу данных
        String name = getArguments().getString("ProductName");
        String cost = getArguments().getString("ProductCost");
        Integer productCategory=getArguments().getInt("ProductCategory");
        String description = getArguments().getString("ProductDescription");
        Log.e("MyTag", "Name from extras " + name);



        adaptor=new ViewPagerForProductActivity(getActivity(),dataForViewPager_def);

         viewModelForPridctFragment.getBigImagesFromData(name, new MyCallBackToRepo<Bitmap>() {
            @Override
            public void onOk(List<Bitmap> nameImagesData) {
                adaptor.setImagesForViewPager((ArrayList<Bitmap>) nameImagesData);
                adaptor.notifyDataSetChanged();

                itemToCart.setItemImage(nameImagesData.get(2));
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

         viewModelForPridctFragment.getProductDescriptionByName(name, new MyCallMackForProdDescription() {
             @Override
             public void onSuccess(ProductDescription description) {
                 productName.append(name);
                 productCost.append(cost);
                 productSeason.append(description.getSeason());
                 productSex.append(description.getSex());
                 productMaterialDescription.append(description.getSoleMaterial());

                 itemToCart.setItemName(name);
                 itemToCart.setCost(Integer.valueOf(cost ) );
                 itemToCart.setDescription("\n Сезон: "+description.getSeason() +"\n "
                         +"Пол: "+description.getSex()+"\n "
                         +"Состав матиреала: "+description.getSoleMaterial());
             }

             @Override
             public void onError(Throwable throwable) {

             }
         });


        recyclerViewAdaptor=new RecyclerViewAdaptor(R.layout.third_recycler_view_item,getContext());
        similarProducts.setAdapter(recyclerViewAdaptor);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        similarProducts.setLayoutManager(linearLayoutManager);

        viewModelForPridctFragment.getSimilarProducts(productCategory).observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                recyclerViewAdaptor.submitList(items);
            }
        });

        recyclerViewAdaptor1=new RecyclerViewAdaptor(R.layout.third_recycler_view_item,getContext());
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        buyWithThisProduct.setAdapter(recyclerViewAdaptor1);
        buyWithThisProduct.setLayoutManager(linearLayoutManager1);

        viewModelForPridctFragment.getSimilarProducts(productCategory).observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                recyclerViewAdaptor1.submitList(items);
            }
        });


        ViewPager viewPager = view.findViewById(R.id.ProductImage_viewPager);

        viewPager.setAdapter(adaptor);
        CircleIndicator circleIndicator = view.findViewById(R.id.circleIndicator_for_viewPager);
        circleIndicator.setViewPager(viewPager);


        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Нажал на добавить в карзину", Toast.LENGTH_LONG).show();
                Item tmpObj = itemToCart;

                if (tmpObj != null) {
                    Log.e("MyTag","Item to cart: "+tmpObj.toString());

                    DbBitmapUtility dbBitmapUtility = new DbBitmapUtility();
                    byte[] image = dbBitmapUtility.getBytes(tmpObj.getItemImage());
                    userCartViewModel.insert(new ItemToUserCart(tmpObj.getCost(), tmpObj.getItemName(), image, tmpObj.getDescription()));

                }

            }
        });

    }


}
