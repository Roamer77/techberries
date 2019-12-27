package com.val.techberries.fragments.Tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.val.techberries.R;
import com.val.techberries.adaptors.SelectAddressListAdaptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PickUpTab extends Fragment {
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.courier_tab_layout,null);
        listView=view.findViewById(R.id.list_of_addresses);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<String> addresses=new ArrayList<>();
        Collections.addAll(addresses,"Ул. Цумного 5 д.4","Ул.Виндова 21 д.13");
        SelectAddressListAdaptor listAdaptor=new SelectAddressListAdaptor(addresses,getContext());
        listView.setAdapter(listAdaptor);
    }
}
