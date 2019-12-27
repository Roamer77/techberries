package com.val.techberries.entities.DTO;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;
import com.val.techberries.entities.Item;
import com.val.techberries.entities.entitiesForNetWork.ProductDescription;

import java.util.List;

public class ProductEntityFromServer {

    private List<Bitmap> bigImages;
    private ProductDescription productDescription;

    public ProductEntityFromServer(List<Bitmap> bigImages, ProductDescription productDescription) {
        this.bigImages = bigImages;
        this.productDescription = productDescription;
    }

    public List<Bitmap> getBigImages() {
        return bigImages;
    }

    public void setBigImages(List<Bitmap> bigImages) {
        this.bigImages = bigImages;
    }

    public ProductDescription getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(ProductDescription productDescription) {
        this.productDescription = productDescription;
    }
}
