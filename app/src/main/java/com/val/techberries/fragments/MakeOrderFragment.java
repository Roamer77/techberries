package com.val.techberries.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.val.techberries.R;
import com.val.techberries.adaptors.CustomTabAdaprot;
import com.val.techberries.fragments.Tabs.FragmentListViewUpdated;

import java.util.ArrayList;

public class MakeOrderFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button nextBtn;
    private TextView productCounter;
    private TextView productFullPrice;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.make_order_fragment,null);
        tabLayout=view.findViewById(R.id.select_delivery_type_Layout);
        viewPager=view.findViewById(R.id.select_delivery_type_pager);
        nextBtn=view.findViewById(R.id.nextBtn);

        productCounter=view.findViewById(R.id.productCounter);
        productFullPrice=view.findViewById(R.id.fullProductPrice);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout.addTab(tabLayout.newTab().setText("Курьером"));
        tabLayout.addTab(tabLayout.newTab().setText("Самовывоз"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        CustomTabAdaprot customTabAdaprot=new CustomTabAdaprot(getActivity().getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(customTabAdaprot);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                FragmentListViewUpdated test= (FragmentListViewUpdated) customTabAdaprot.getItem(viewPager.getCurrentItem());
                test.update(getActivity().getPreferences(Context.MODE_PRIVATE).getInt("onlyOneChoiceOn2fragments",99));

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        if(getArguments()!=null){
            int price=getArguments().getInt("totalPrice");
            int amountOfProducts=getArguments().getInt("productCounter");
            productFullPrice.append(String.valueOf(price));
            productCounter.append(String.valueOf(amountOfProducts));


        }

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle data=new Bundle();

                if(getArguments()!=null){
                    int price=getArguments().getInt("totalPrice");
                    int amountOfProducts=getArguments().getInt("productCounter");
                    ArrayList<Integer> productIDs=getArguments().getIntegerArrayList("productIDs");
                    data.putIntegerArrayList("productIDs",productIDs);
                    data.putInt("totalPrice",price);
                    data.putInt("productCounter",amountOfProducts);
                }
                Navigation.findNavController(view).navigate(R.id.selectPaymentTypeFragment,data);
            }
        });
    }
}
