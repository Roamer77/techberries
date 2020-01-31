package com.val.techberries.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.val.techberries.R;

import java.net.MalformedURLException;
import java.net.URL;

public class TestPayPalPayment extends Fragment {
    private WebView webView;
    private ProgressBar progressBar;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.paypal_payment_layout, null);
        webView = view.findViewById(R.id.browserScreen);
        progressBar = view.findViewById(R.id.progressBar);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        sharedPreferences=getActivity().getPreferences(Context.MODE_PRIVATE);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        webView.setWebViewClient(new CustomWebViewClient(view));
        int data=getArguments().getInt("SumFroPayment");
        String sum = String.valueOf(data);
        webView.loadUrl("http://192.168.176.17:8080/payment/make?sum=" + sum);
    }

    private class CustomWebViewClient extends WebViewClient {
        private View view;

        public CustomWebViewClient(View view) {
            this.view = view;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            webView.setVisibility(View.GONE);
            progressBar.setVisibility(View.INVISIBLE);

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            Log.w("MyTag", "Переходы по url: " + request.getUrl());
            Log.w("MyTag", "getLastPathSegment:  " + request.getUrl().getLastPathSegment());

            String partOfUrlForComparing = getUrlWithoutParameters(request.getUrl().toString());
            String cancelUrl="http://192.168.59.1:8080/payment/cancel";
            String successUrl="http://192.168.59.1:8080/payment/success";
            Log.w("MyTag", "Переходы по partOfUrlForComparing: " + partOfUrlForComparing);

            if (partOfUrlForComparing.equals(cancelUrl)) {
                Log.e("MyTag", "Пытаюсь вернуться из "+cancelUrl);
                Navigation.findNavController(this.view).popBackStack(R.id.selectPaymentTypeFragment,true);
                addMarkIfPaymentSuccess(1);
            }
            if (partOfUrlForComparing.equals(successUrl)) {
                Log.e("MyTag", "Пытаюсь вернуться из "+successUrl);
                Navigation.findNavController(this.view).popBackStack(R.id.cartFragment,true);
                addMarkIfPaymentSuccess(0);
                Toast.makeText(getContext(),"Заказ успешно оплачен",Toast.LENGTH_SHORT).show();
            }
            return false;
        }
        private void addMarkIfPaymentSuccess(int mark){
            //если передать 0 то заказ не оплачен
            //если передать 1 то заказ оплачен
            editor = sharedPreferences.edit();
            editor.putInt("PaymentSuccess",mark);
            editor.commit();
        }


        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            webView.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
        }

        private String getUrlWithoutParameters(String url) {
            try {
                String tmp=url.replace(new URL(url).getQuery(), "");
                StringBuilder stringBuilder=new StringBuilder(tmp);
                stringBuilder.deleteCharAt(tmp.length()-1);
                return stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
