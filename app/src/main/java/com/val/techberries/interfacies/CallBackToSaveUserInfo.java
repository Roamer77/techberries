package com.val.techberries.interfacies;

import com.val.techberries.entities.entitiesForNetWork.UserInfo;

public interface CallBackToSaveUserInfo {
    void isOk(int state);
    void onError(String error);
}
