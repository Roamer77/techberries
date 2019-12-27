package com.val.techberries.modelViews;

import android.app.Application;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.val.techberries.entities.Item;
import com.val.techberries.entities.entitiesForNetWork.ProductDescription;
import com.val.techberries.interfacies.MyCallBackToRepo;
import com.val.techberries.interfacies.MyCallMackForProdDescription;
import com.val.techberries.repositories.MainRepository;

import java.util.ArrayList;
import java.util.List;

public class ViewModelForProductFragment extends AndroidViewModel {

    private MainRepository mainRepository;

    private List<Bitmap> bigImages;
    private ProductDescription description;

    private MutableLiveData<List<Item>> similarProducts;
    private MutableLiveData<List<Item>> alsoBuyWithCurrentItem;

    public ViewModelForProductFragment(@NonNull Application application) {
        super(application);
        this.mainRepository = new MainRepository();
    }

    public void getBigImagesFromData(String name, MyCallBackToRepo<Bitmap> myCallBackToRepo) {

        if (bigImages == null) {
            bigImages = new ArrayList<>();
            mainRepository.getBigImagesForCurrentProduct(name, new MyCallBackToRepo<Bitmap>() {
                @Override
                public void onOk(List<Bitmap> nameImagesData) {
                    myCallBackToRepo.onOk(nameImagesData);
                    bigImages.addAll(nameImagesData);
                }

                @Override
                public void onError(Throwable throwable) {

                }
            });

        }

    }

    public LiveData<List<Item>> getSimilarProducts(int categoryID) {

        if (similarProducts == null) {
            similarProducts = new MutableLiveData<>();
            mainRepository.getSimilarProducts(categoryID, new MyCallBackToRepo<Item>() {
                @Override
                public void onOk(List<Item> nameImagesData) {
                    similarProducts.setValue(nameImagesData);
                }

                @Override
                public void onError(Throwable throwable) {

                }
            });

        }
        return similarProducts;
    }
    public LiveData<List<Item>> alsoBuyWithCurrentItem(int categoryID) {

        if (alsoBuyWithCurrentItem == null) {
            alsoBuyWithCurrentItem = new MutableLiveData<>();
            mainRepository.getSimilarProducts(categoryID, new MyCallBackToRepo<Item>() {
                @Override
                public void onOk(List<Item> nameImagesData) {
                    alsoBuyWithCurrentItem.setValue(nameImagesData);
                }

                @Override
                public void onError(Throwable throwable) {

                }
            });

        }
        return alsoBuyWithCurrentItem;
    }


    public ProductDescription getProductDescriptionByName(String name, MyCallMackForProdDescription callback) {
        if (description == null) {
            description = new ProductDescription();
            mainRepository.getDescriptionAboutProduct(name, new MyCallBackToRepo<ProductDescription>() {
                @Override
                public void onOk(List<ProductDescription> nameImagesData) {
                    callback.onSuccess(nameImagesData.get(0));
                    //description=nameImagesData.get(0);
                }

                @Override
                public void onError(Throwable throwable) {

                }
            });
        }
        return description;
    }

}
