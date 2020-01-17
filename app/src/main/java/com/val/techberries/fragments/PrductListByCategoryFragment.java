package com.val.techberries.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.val.techberries.entities.Item;
import com.val.techberries.R;
import com.val.techberries.adaptors.GridViewAdaptor;
import com.val.techberries.interfacies.MyCallBackToRepo;
import com.val.techberries.modelViews.ViewModelForProductListByCategory;
import com.val.techberries.modelViews.viewModelsForDB.UserCartViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrductListByCategoryFragment extends Fragment {
    private GridView gridView;
    private Button gridViewStyleBtn;
    private Button filterByBtn;
    private Integer styleModeID = 1;
    private UserCartViewModel userCartViewModel;
    private ViewModelForProductListByCategory viewModelForProductListByCategory;
    private GridViewAdaptor gridViewAdaptor;

    private EditText searchLine;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_product_list_by_category, null);

        viewModelForProductListByCategory = new ViewModelForProductListByCategory();

        searchLine = view.findViewById(R.id.search_line_et);

        gridView = view.findViewById(R.id.prodctListGridView);
        gridViewStyleBtn = view.findViewById(R.id.styleModeBtn);

        filterByBtn = view.findViewById(R.id.filterBy);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<Item> defData = new ArrayList<>();
        defData.add(new Item("...",null,111,"desc"));
        defData.add(new Item("...",null,111,"desc"));


        gridViewAdaptor = new GridViewAdaptor(getActivity(), defData);

        long categoryId = getArguments().getLong("categoryID");
        String sex=getArguments().getString("sex");

        userCartViewModel = ViewModelProviders.of(this).get(UserCartViewModel.class);
        gridViewAdaptor.setUserCartViewModel(userCartViewModel);


      /*  viewModelForProductListByCategory.getDataForProductListByCategory(categoryId, new MyCallBackToRepo<Item>() {
            @Override
            public void onOk(List<Item> nameImagesData) {
                gridViewAdaptor.setGridViewData((ArrayList<Item>) nameImagesData);
                gridViewAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });*/
        viewModelForProductListByCategory.getDataForProductListByCategoryAndSex(sex,categoryId, new MyCallBackToRepo<Item>() {
            @Override
            public void onOk(List<Item> nameImagesData) {
                gridViewAdaptor.setGridViewData((ArrayList<Item>) nameImagesData);
                gridViewAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

        if (getArguments().getString("ProductThatNeedToFind") != null) {
            String name = getArguments().getString("ProductThatNeedToFind");
            updateGridViewData(name);
        }

        searchLine.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    String name = searchLine.getText().toString();
                    updateGridViewData(name);
                }

                return false;
            }
        });


        gridView.setAdapter(gridViewAdaptor);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Нажал на " + position, Toast.LENGTH_LONG).show();

            }
        });

        gridViewStyleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridViewStyleBtn.animate().rotation(gridViewStyleBtn.getRotation()-180).start();
                gridView.setNumColumns(styleModeID);
                if (styleModeID < 2) {
                    styleModeID = styleModeID + 1;
                } else {
                    styleModeID = 1;
                }
            }
        });


        filterByBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                initTipDialog(alertDialog);
            }
        });
    }

    private void initTipDialog(AlertDialog.Builder alert) {
        View customLayout = getLayoutInflater().inflate(R.layout.filter_list_for_filter_dialog, null);

        ArrayList<String> filters = new ArrayList<>();
        Collections.addAll(filters, "По цене", "По производителю", "По размеру");
        ListView listView = customLayout.findViewById(R.id.filter_list_for_filter_dialog);
        listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, filters));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Нажал на " + position, Toast.LENGTH_LONG).show();
                filterByBtn.setText(filters.get(position));
            }
        });
        alert.setView(customLayout);
        AlertDialog dialog = alert.create();
        dialog.show();
    }


    //для поиска по имени в строке поиска )
    private void updateGridViewData(String name) {
        viewModelForProductListByCategory.getDataForProductListByName(name, new MyCallBackToRepo<Item>() {
            @Override
            public void onOk(List<Item> nameImagesData) {
                gridViewAdaptor.setGridViewData((ArrayList<Item>) nameImagesData);
                gridViewAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }

}
