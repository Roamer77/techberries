package com.val.techberries.modelViews;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.val.techberries.entities.Item;
import com.val.techberries.interfacies.MyCallBack;
import com.val.techberries.interfacies.MyCallBackToRepo;
import com.val.techberries.repositories.MainRepository;
import com.val.techberries.utils.ImageConvertar.ConvertImageFromBase64;
import com.val.techberries.utils.netWork.RequestAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewModelForHomePage extends AndroidViewModel {

    private MainRepository mainRepository;

    private MutableLiveData<List<Item>> advertisement;
    private MutableLiveData<List<Item>> dataForFirstRecyclerView;
    private MutableLiveData<List<Item>> dataForThirdRecyclerView;

    public ViewModelForHomePage(Application application) {
        super(application);
        this.mainRepository = new MainRepository();
    }

    public LiveData<List<Item>> getDataForHomePageForFirstRecyclerView() {

        if (dataForFirstRecyclerView == null) {
            dataForFirstRecyclerView = new MutableLiveData<>();
            mainRepository.getDataForHomePageFirstRecyclerView(new MyCallBackToRepo<Item>() {
                @Override
                public void onOk(List<Item> nameImagesData) {
                    dataForFirstRecyclerView.setValue(nameImagesData);
                }

                @Override
                public void onError(Throwable throwable) {

                }
            });

        }
        return dataForFirstRecyclerView;
    }

    public LiveData<List<Item>> getDataForHomePageForThirdRecyclerView() {

        if(dataForThirdRecyclerView==null){
            dataForThirdRecyclerView= new MutableLiveData<>();
            //сюда данные должны поступать асинхронно
            mainRepository.getDataForHomePageTherdRecyclerView(new MyCallBackToRepo<Item>() {
                @Override
                public void onOk(List<Item> nameImagesData) {
                    dataForThirdRecyclerView.setValue(nameImagesData);
                }

                @Override
                public void onError(Throwable throwable) {

                }
            });

        }
        return dataForThirdRecyclerView;
    }


    public LiveData<List<Item>> getDataFroHomePageAdvertisementRecyclerView() {
        List<Item> items = mainRepository.getDataForHomePageAdvertisementRecyclerView();
        if (advertisement == null) {
            advertisement = new MutableLiveData<>();
            advertisement.setValue(items);
        }
        return advertisement;
    }
}
