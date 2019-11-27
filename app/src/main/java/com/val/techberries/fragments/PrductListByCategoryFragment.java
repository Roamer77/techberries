package com.val.techberries.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.val.techberries.entities.Item;
import com.val.techberries.R;
import com.val.techberries.adaptors.GridViewAdaptor;
import com.val.techberries.modelViews.ViewModelForProductListByCategory;

import java.util.ArrayList;
import java.util.Collections;

public class PrductListByCategoryFragment extends Fragment {
    private GridView gridView;
    private Button gridViewStyleBtn;
    private Button filterListBtn;
    private Button filterByPopularityBtn;
    private Integer styleModeID=1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_product_list_by_category,null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridView=view. findViewById(R.id.prodctListGridView);
        gridViewStyleBtn=view. findViewById(R.id.styleModeBtn);

        filterByPopularityBtn=view.findViewById(R.id.filterByPopularity);
        filterListBtn=view. findViewById(R.id.filterListBtn);

        //на  лайв дата
        ViewModelForProductListByCategory viewModelForProductListByCategory=new ViewModelForProductListByCategory();
        ArrayList<Item> data=viewModelForProductListByCategory.getDataForProductListByCategory();


        GridViewAdaptor gridViewAdaptor =new GridViewAdaptor(getActivity(),data);
        gridView.setAdapter(gridViewAdaptor);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"Нажал на "+position,Toast.LENGTH_LONG).show();

            }
        });

        gridViewStyleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridView.setNumColumns(styleModeID);
                if(styleModeID<2){
                    styleModeID=styleModeID+1;
                }else {
                    styleModeID=1;
                }
            }
        });


        filterByPopularityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getActivity());
                initTipDialog(alertDialog);
            }
        });
    }
    private void initTipDialog(AlertDialog.Builder alert) {
        View customLayout=getLayoutInflater().inflate(R.layout.filter_list_for_filter_dialog,null);

        ArrayList<String> filters=new ArrayList<>();
        Collections.addAll(filters,"По цене","По производителю","По размеру");
        ListView listView=customLayout.findViewById(R.id.filter_list_for_filter_dialog);
        listView.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_expandable_list_item_1,filters));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"Нажал на "+position,Toast.LENGTH_LONG).show();
            }
        });
        alert.setView(customLayout);
        AlertDialog dialog =alert.create();
        dialog.show();
    }
}
