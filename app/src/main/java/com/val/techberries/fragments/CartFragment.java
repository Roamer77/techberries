package com.val.techberries.fragments;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.val.techberries.utils.netWork.DataToServerSender;
import com.val.techberries.utils.netWork.InternetConnectionChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class CartFragment extends Fragment {

    private RecyclerView recyclerView;
    private  Button makeOrder;
    private UserCartViewModel cartViewModel;
    private CartRecyclerViewAdaptor cartRecyclerViewAdaptor;

    private SharedPreferences sharedPreferences;
    private   View view;

    private  int totalPriceForProducts;
    private int productCounter;
    private ArrayList<Integer> productsIDs;

    private InternetConnectionChecker internetConnectionChecker;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_for_cart_with_items, null);
        makeOrder=view.findViewById(R.id.MakeOrderButton);
        productsIDs=new ArrayList<>();
        sharedPreferences=getActivity().getPreferences(Context.MODE_PRIVATE);

        makeOrder.setEnabled(false);

        internetConnectionChecker=new InternetConnectionChecker(getActivity().getApplication());
        internetConnectionChecker.execute();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        doActionsIfNoInternetConnection(view);

        cartRecyclerViewAdaptor = new CartRecyclerViewAdaptor(getActivity());
        recyclerView = view.findViewById(R.id.itemsInCart_recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        cartViewModel = ViewModelProviders.of(this).get(UserCartViewModel.class);

        if(sharedPreferences.getInt("Login",99)==1){
            makeOrder.setEnabled(true);
        }else {
            makeOrder.setEnabled(false);
        }


        cartViewModel.getAllItems().observe(this, new Observer<List<ItemToUserCart>>() {

            @Override
            public void onChanged(List<ItemToUserCart> itemToUserCarts) {
                Log.e("MyTag","size= "+itemToUserCarts.size());
                if(itemToUserCarts.size()==0){
                    Bundle data=new Bundle();
                    data.putInt("FragmentForBackStap",R.id.cartFragment);
                    Navigation.findNavController(view).navigate(R.id.ifCartIsEmpty,data);
                }
                for(int i=0;i<itemToUserCarts.size();i++){
                    ItemToUserCart item=itemToUserCarts.get(i);
                    totalPriceForProducts+=item.getCost();
                    productsIDs.add(item.getId());
                }
                productCounter=itemToUserCarts.size();
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

        makeOrder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Bundle data=new Bundle();
                data.putInt("totalPrice",totalPriceForProducts);
                data.putInt("productCounter",productCounter);
                data.putIntegerArrayList("productIDs",productsIDs);
                Navigation.findNavController(view).navigate(R.id.makeOrderFragment,data);

            }
        });

    }

    private void doActionsIfNoInternetConnection(View view){
        internetConnectionChecker=new InternetConnectionChecker(getActivity().getApplication());
        internetConnectionChecker.execute();
        try {
            boolean internet= internetConnectionChecker.get(1, TimeUnit.SECONDS);
            if(internet){
                Log.e("MyTag","Интернет ЕСТЬ");
            }else {
                Log.e("MyTag","Интернет НЕТ");

                Bundle data=new Bundle();
                data.putInt("FragmentThatIsNotHaveInternet",R.id.cartFragment);
                Navigation.findNavController(view).navigate(R.id.noInternetConection,data);

            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
