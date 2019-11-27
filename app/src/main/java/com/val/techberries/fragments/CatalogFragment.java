package com.val.techberries.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.val.techberries.R;
import com.val.techberries.utils.NoScrollListView;
import com.val.techberries.adaptors.CustomExpandableListAdaptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CatalogFragment extends Fragment {

    private NoScrollListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_catalog,null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<String> childList=new ArrayList<>();
        Collections.addAll(childList,"1","2","3");

        List<String> childList2=new ArrayList<>();
        Collections.addAll(childList2,"a","b","c");

        List<String> childList3=new ArrayList<>();
        Collections.addAll(childList3,"a1","b2","c3");

        HashMap<String,List<String>> catalog = new HashMap<>();
        catalog.put("Мужчинам",childList);
        catalog.put("Женщинам",childList2);
        catalog.put("Детям",childList3);
        catalog.put("Спорт",childList);

        ArrayList<String> groupTitles=new ArrayList<>();
        Collections.addAll(groupTitles,"Мужчинам","Женщинам","Детям","Спорт");

        ArrayList<Integer> imagesForGroupItems=new ArrayList<>();
        Collections.addAll(imagesForGroupItems,R.drawable.ic_man,R.drawable.ic_wonem,R.drawable.ic_child,R.drawable.ic_sport);

        expandableListView =(NoScrollListView)view.findViewById(R.id.Catalog_elv);
        expandableListAdapter=new CustomExpandableListAdaptor(getActivity(),groupTitles,catalog,imagesForGroupItems);
        expandableListView.setAdapter(expandableListAdapter);




        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                Toast.makeText(getActivity(),"Нажал на "+childPosition,Toast.LENGTH_LONG).show();
                Navigation.findNavController(view).navigate(R.id.prductListByCategoryFragment);
               /* //в будующем в зависимости от катигории будут отображаться разные картинки
                Intent intent=new Intent(CatalogActivity.this, ProductListByCategoryActivity.class);
                startActivity(intent);*/
                return true;
            }
        });
    }
}
