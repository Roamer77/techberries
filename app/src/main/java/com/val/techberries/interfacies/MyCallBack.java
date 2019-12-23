package com.val.techberries.interfacies;

import java.util.Map;

public interface MyCallBack {
    void onSuccess(Map nameImagesData);
    void onError(Throwable throwable);
}
