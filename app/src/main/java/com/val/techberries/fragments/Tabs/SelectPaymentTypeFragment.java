package com.val.techberries.fragments.Tabs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.val.techberries.R;
import com.val.techberries.adaptors.SelectAddressListAdaptor;
import com.val.techberries.entities.entitiesForNetWork.Order;
import com.val.techberries.utils.netWork.DataToServerSender;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

public class SelectPaymentTypeFragment extends Fragment {
    private ListView listView;
    private TextView productCounter;
    private TextView productFullPrice;
    private Button makeOrderButton;
    private DataToServerSender dataToServerSender;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payment_type_layout, null);
        listView = view.findViewById(R.id.list_of_addresses);
        productCounter = view.findViewById(R.id.productCounter);
        productFullPrice = view.findViewById(R.id.productFullPrice);
        makeOrderButton = view.findViewById(R.id.makeOrderBtn);
        dataToServerSender = new DataToServerSender();
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<String> paymentType = new ArrayList<>();
        Collections.addAll(paymentType, "PayPal", "Через ЕРИП", "Начичными при получении");
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String> listAdaptor = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_single_choice, paymentType);
        listView.setAdapter(listAdaptor);

        if (getArguments() != null) {
            int price = getArguments().getInt("totalPrice");
            int amountOfProducts = getArguments().getInt("productCounter");
            productFullPrice.append(String.valueOf(price));
            productCounter.append(String.valueOf(amountOfProducts));
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //пусть у типов оплаты будет свои id
                //PayPal id=0;
                //Через ЕРИП id=1;
                //Начичными при получении id=2;
                int paymentTypeID = position;
                editor.putInt("paymetType", paymentTypeID);
                editor.commit();
                if(position==0){
                    makeOrderButton.setText("Оплатить через PayPal");
                }else {
                    makeOrderButton.setText("Оформить");
                }
            }
        });

        makeOrderButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                int price = getArguments().getInt("totalPrice");
                int amountOfProducts = getArguments().getInt("productCounter");
                int paymetType = sharedPreferences.getInt("paymetType", 99);
                String address = sharedPreferences.getString("address", "_");
                int deliveryType = sharedPreferences.getInt("onlyOneChoiceOn2fragments", 99);
                ArrayList<Integer> productIDs = getArguments().getIntegerArrayList("productIDs");
                Log.e("MyTag", "Заказ: сумма " + price + " продуктов " + amountOfProducts + " тип оплаты  " + paymetType + " адрес  " + address + " тип доставки " + deliveryType);

                Order order = new Order();
                order.setPrice(price);
                order.setAddress(address);
                order.setPaymentType(paymetType);
                order.setDeliveryType(deliveryType);
                order.setProductsID(productIDs);

                if(Integer.valueOf(paymetType)==0){
                    Navigation.findNavController(view).navigate(R.id.testPayPalPayment);
                    Log.e("MyTag","Переходим к оплате через paypal");
                        if(sharedPreferences.getInt("PaymentSuccess",99)==1){
                            Log.e("MyTag","Отправляю заказ после оплаты ");
                           // new SendData().execute(order);
                            editor.putInt("PaymentSuccess",0);
                            editor.commit();
                        }
                }else {
                    Log.e("MyTag","Просто отправляю заказ");
                    new SendData().execute(order);
                }
            }
        });


    }

    private class SendData extends AsyncTask<Order, Void, Void> {

        String pass = sharedPreferences.getString("UserPassword", "-");
        String login = sharedPreferences.getString("UserLogin", "-");

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient auth = new OkHttpClient.Builder().authenticator((route, response) -> {
            Request request = response.request();
            if (request.header("Authorization") != null) {
                Log.e("MyTag", "Пароль неверный");
                return null;
            } else {
                Log.e("MyTag", "Всё хорошо при отправке заказа");
            }
            return request.newBuilder()
                    .header("Authorization", Credentials.basic(login, pass))
                    .build();
        }).addInterceptor(loggingInterceptor).build();

        @Override
        protected Void doInBackground(Order... args) {
            dataToServerSender.makeOrderRequest(auth, args[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            editor.putInt("PaymentSuccess",0);
            editor.commit();
        }
    }

}
