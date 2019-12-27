package com.val.techberries.utils.DbBitMapUtility;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class DbBitmapUtility {
    public byte[] getBytes(Bitmap bitmap){
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,stream);
        return stream.toByteArray();
    }
    public  Bitmap getImageFromBytes(byte[] image){
        return BitmapFactory.decodeByteArray(image,0,image.length);
    }
}
