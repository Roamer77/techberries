package com.val.techberries.fragments.Tabs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.val.techberries.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PickUpTab extends Fragment implements FragmentListViewUpdated {
    private ListView listView;
    private TextView textView;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.courier_tab_layout,null);
        listView=view.findViewById(R.id.list_of_addresses);
        Log.e("MyTag","p "+ " List "+listView);
        textView=view.findViewById(R.id.text);
        textView.setText("Выберите адрес для доставки");
        sharedPreferences=getActivity().getPreferences(Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<String> addresses=new ArrayList<>();
        Collections.addAll(addresses,"Ул. Цумного 5 д.4","Ул.Виндова 21 д.13");
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String> listAdaptor=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_single_choice,addresses);
        listView.setAdapter(listAdaptor);

        Log.e("MyTag","p "+ " List "+listView+ " "+this.hashCode());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editor.putString("address",addresses.get(position));
                editor.putInt("onlyOneChoiceOn2fragments",1);
                editor.commit();
            }
        });
    }

    @Override
    public void update(int i ) {
        Log.e("MyTag","p "+" "+i+ " List "+listView+ " "+this.hashCode());
        if(i!=1 &&listView!=null){
            listView.clearChoices();
            listView.invalidateViews();
        }
    }
}
