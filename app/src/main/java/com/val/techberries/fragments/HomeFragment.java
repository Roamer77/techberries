package com.val.techberries.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.val.techberries.entities.Item;
import com.val.techberries.R;
import com.val.techberries.adaptors.RecyclerViewAdaptor;
import com.val.techberries.modelViews.ViewModelForHomePage;

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

        ViewModelForHomePage viewModelForHomePage = ViewModelProviders.of(this).get(ViewModelForHomePage.class);


        //второй ресайклер. Он будет для рекламы
        secondRecyclerView = view.findViewById(R.id.secondRecyclerView);
        LinearLayoutManager layoutManager2
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        secondRecyclerView.setLayoutManager(layoutManager2);


        PagerSnapHelper pagerSnapHelper2 = new PagerSnapHelper();
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();


        circleIndicator = view.findViewById(R.id.circleIndicator_1_NestedScroll_View);
        circleIndicator2 = view.findViewById(R.id.circleIndicator_2_NestedScroll_View);


        RecyclerViewAdaptor recyclerViewAdaptor2 = new RecyclerViewAdaptor(R.layout.first_recycler_view_item,getActivity());
        secondRecyclerView.setAdapter(recyclerViewAdaptor2);
        secondRecyclerView.setHasFixedSize(true);

        viewModelForHomePage.getDataFroHomePageAdvertisementRecyclerView().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                recyclerViewAdaptor2.submitList(items);
                pagerSnapHelper2.attachToRecyclerView(secondRecyclerView);
                circleIndicator2.attachToRecyclerView(secondRecyclerView, pagerSnapHelper);

            }
        });

        firstRecyclerView = view.findViewById(R.id.firstRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        firstRecyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdaptor recyclerViewAdaptor = new RecyclerViewAdaptor(R.layout.first_recycler_view_item,getActivity());
        firstRecyclerView.setAdapter(recyclerViewAdaptor);
        firstRecyclerView.setHasFixedSize(true);

        viewModelForHomePage.getDataForHomePageForFirstRecyclerView().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {

                recyclerViewAdaptor.submitList(items);
                pagerSnapHelper.attachToRecyclerView(firstRecyclerView);
                circleIndicator.attachToRecyclerView(firstRecyclerView, pagerSnapHelper);

            }
        });
        thirdRecyclerView = view.findViewById(R.id.thirdRecyclerView);
        RecyclerViewAdaptor recyclerViewAdaptor3=new RecyclerViewAdaptor(R.layout.third_recycler_view_item,getActivity());
        thirdRecyclerView.setAdapter(recyclerViewAdaptor3);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        thirdRecyclerView.setLayoutManager(linearLayoutManager3);

        viewModelForHomePage.getDataForHomePageForThirdRecyclerView().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                recyclerViewAdaptor3.submitList(items);
            }
        });

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
            Bundle data = new Bundle();
            data.putString("ProductName", item.getItemName());
            Navigation.findNavController(view).navigate(R.id.productFragment, data);
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
