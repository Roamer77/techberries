package com.val.techberries.interfacies;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface MyCallBackToRepo<T> {
    void  onOk  (List<T> nameImagesData);
    void onError(Throwable throwable);
}
