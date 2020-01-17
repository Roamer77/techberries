package com.val.techberries.entities.entitiesForNetWork;



public class AdvertisingFromServer {
    long id;
    String advImage;

    public AdvertisingFromServer() {
    }

    public AdvertisingFromServer(long id, String advImage) {
        this.id = id;
        this.advImage = advImage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAdvImage() {
        return advImage;
    }

    public void setAdvImage(String advImage) {
        this.advImage = advImage;
    }

    @Override
    public String toString() {
        return "AdvertisingFromServer{" +
                "id=" + id +
                '}';
    }
}
