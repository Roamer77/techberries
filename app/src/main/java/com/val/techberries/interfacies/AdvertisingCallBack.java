package com.val.techberries.interfacies;

import com.val.techberries.entities.entitiesForNetWork.AdvertisingFromServer;

import java.util.List;

public interface AdvertisingCallBack {
    void onSuccess(List<AdvertisingFromServer> advertising);
    void onError(Throwable throwable);
}
