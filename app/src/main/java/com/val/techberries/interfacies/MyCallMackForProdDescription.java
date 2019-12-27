package com.val.techberries.interfacies;

import com.val.techberries.entities.entitiesForNetWork.ProductDescription;

public interface MyCallMackForProdDescription {
    void onSuccess(ProductDescription description);
    void onError(Throwable throwable);
}
