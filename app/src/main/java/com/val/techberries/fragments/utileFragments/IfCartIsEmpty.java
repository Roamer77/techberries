package com.val.techberries.fragments.utileFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.val.techberries.R;

public class IfCartIsEmpty extends Fragment {

    private Button goСatalogBtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.if_cart_is_emply,null);
        goСatalogBtn=view.findViewById(R.id.goToCatalogFragment);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        goСatalogBtn.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.catalogFragment);
        });
    }
}
