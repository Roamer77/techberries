package com.val.techberries.modelViews;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.val.techberries.entities.Item;
import com.val.techberries.repositories.MainRepository;

import java.util.List;

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
        List<Item> items = mainRepository.getDataForHomePageFirstRecyclerView();
        if (dataForFirstRecyclerView == null) {
            dataForFirstRecyclerView = new MutableLiveData<>();
            dataForFirstRecyclerView.setValue(items);
        }
        return dataForFirstRecyclerView;
    }

    public LiveData<List<Item>> getDataForHomePageForThirdRecyclerView() {
        List<Item> items =mainRepository.getDataForHomePageTherdRecyclerView();
        if(dataForThirdRecyclerView==null){
            dataForThirdRecyclerView= new MutableLiveData<>();
            //сюда данные должны поступать асинхронно
            dataForThirdRecyclerView.setValue(items);
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
