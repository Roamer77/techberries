package com.val.techberries.entities.entitiesForNetWork;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @SerializedName("userPhoneNumber")
    @Expose
    private String userPhoneNumber;
    @SerializedName("userLogin")
    @Expose
    private String userLogin;
    @SerializedName("userPassword")
    @Expose
    private String userPassword;

    public UserInfo(String userPhoneNumber, String userLogin, String userPassword) {
        this.userPhoneNumber = userPhoneNumber;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userPhoneNumber='" + userPhoneNumber + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
