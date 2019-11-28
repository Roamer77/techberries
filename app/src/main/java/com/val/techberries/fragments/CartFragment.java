package com.val.techberries.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.val.techberries.R;
import com.val.techberries.adaptors.CartRecyclerViewAdaptor;
import com.val.techberries.entities.ItemToUserCart;
import com.val.techberries.modelViews.viewModelsForDB.UserCartViewModel;

import java.util.List;

public class CartFragment extends Fragment {

    private RecyclerView recyclerView;

    private UserCartViewModel cartViewModel;
    private CartRecyclerViewAdaptor cartRecyclerViewAdaptor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_for_cart_with_items, null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cartRecyclerViewAdaptor = new CartRecyclerViewAdaptor(getActivity());
        recyclerView = view.findViewById(R.id.itemsInCart_recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        cartViewModel = ViewModelProviders.of(this).get(UserCartViewModel.class);
        cartViewModel.getAllItems().observe(this, new Observer<List<ItemToUserCart>>() {

            @Override
            public void onChanged(List<ItemToUserCart> itemToUserCarts) {
                cartRecyclerViewAdaptor.submitList(itemToUserCarts);
            }
        });
        recyclerView.setAdapter(cartRecyclerViewAdaptor);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                cartViewModel.delete(cartRecyclerViewAdaptor.getItemInCartAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "Удалена одна запись", Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(recyclerView);


    }
}
