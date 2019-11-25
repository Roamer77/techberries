package com.val.techberries.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.val.techberries.Entities.Item;
import com.val.techberries.MainActivity;
import com.val.techberries.R;
import com.val.techberries.activities.ProductActivity;
import com.val.techberries.adaptors.RecyclerViewAdaptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.relex.circleindicator.CircleIndicator2;

public class HomeFragment extends Fragment {
    private RecyclerView firstRecyclerView;
    private RecyclerView secondRecyclerView;
    private RecyclerView thirdRecyclerView;

    private CircleIndicator2 circleIndicator;
    private CircleIndicator2 circleIndicator2;

    private Toolbar toolbar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Item> testData = new ArrayList<>();
        Collections.addAll(testData, new Item("Куртка 1", R.drawable.product_img0),
                new Item("Куртка 2", R.drawable.product_img1),
                new Item("Куртка 3", R.drawable.product_img2),
                new Item("Куртка 1", R.drawable.product_img0),
                new Item("Куртка 2", R.drawable.product_img1),
                new Item("Куртка 3", R.drawable.product_img2));

        List<Item> testData2 = new ArrayList<>();
        Collections.addAll(testData2, new Item("", R.drawable.prod_place1),
                new Item("", R.drawable.prod_place2),
                new Item("", R.drawable.prod_place3));

        List<Item> testData3 = new ArrayList<>();
        Collections.addAll(testData3,
                new Item("Кеды 1", R.drawable.ked1),
                new Item("Кеды 2", R.drawable.ked2),
                new Item("Кеды 3", R.drawable.ked3),
                new Item("Кеды 4", R.drawable.ked4));

        firstRecyclerView = view.findViewById(R.id.firstRecyclerView);
        RecyclerViewAdaptor recyclerViewAdaptor = new RecyclerViewAdaptor(testData, getActivity(), R.layout.first_recycler_view_item);
        firstRecyclerView.setAdapter(recyclerViewAdaptor);
        firstRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        firstRecyclerView.setLayoutManager(layoutManager);

        //второй ресайклер. Он будет для рекламы
        secondRecyclerView = view.findViewById(R.id.secondRecyclerView);
        RecyclerViewAdaptor recyclerViewAdaptor2 = new RecyclerViewAdaptor(testData2, getActivity(), R.layout.first_recycler_view_item);
        secondRecyclerView.setAdapter(recyclerViewAdaptor2);
        secondRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager2
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        secondRecyclerView.setLayoutManager(layoutManager2);


        //третий ресайклер
        thirdRecyclerView = view.findViewById(R.id.thirdRecyclerView);
        thirdRecyclerView.setAdapter(new RecyclerViewAdaptor(testData3, getActivity(), R.layout.third_recycler_view_item));
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        thirdRecyclerView.setLayoutManager(linearLayoutManager3);

        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        PagerSnapHelper pagerSnapHelper2 = new PagerSnapHelper();
        pagerSnapHelper2.attachToRecyclerView(secondRecyclerView);
        pagerSnapHelper.attachToRecyclerView(firstRecyclerView);

        circleIndicator = view.findViewById(R.id.circleIndicator_1_NestedScroll_View);
        circleIndicator2 = view.findViewById(R.id.circleIndicator_2_NestedScroll_View);

        circleIndicator.attachToRecyclerView(firstRecyclerView, pagerSnapHelper);
        circleIndicator2.attachToRecyclerView(secondRecyclerView, pagerSnapHelper);


        toolbar = view.findViewById(R.id.customToolbar);
        toolbar.inflateMenu(R.menu.notification);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.d("MyTag", "Нажал на уведомления");
                Toast.makeText(getActivity(), "Нажал на уведомления", Toast.LENGTH_LONG).show();
                return true;
            }
        });

        recyclerViewAdaptor.setItemClickListener(item -> {

            Toast.makeText(getActivity(), "Нажал на " + item.getItemName(), Toast.LENGTH_LONG).show();


            Bundle data=new Bundle();
            data.putString("ProductName",item.getItemName());
            Navigation.findNavController(view).navigate(R.id.productFragment,data);
            /*Intent intent=new Intent(MainActivity.this, ProductActivity.class);
            intent.putExtra("ProductName",item.getItemName());
            startActivity(intent);*/
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.notification, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


}
