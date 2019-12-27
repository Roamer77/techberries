package com.val.techberries.utils.ImageConvertar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class ConvertImageFromBase64 {

    public  Bitmap convertFromBase64toImage(String imgInBase64){

        ByteArrayOutputStream imageInByts=new ByteArrayOutputStream();
        byte[] bytes;
        try {
            //decode and convert image to normal form
            byte[] decodeImageByts= Base64.getDecoder().decode(imgInBase64);
            Bitmap testImg= BitmapFactory.decodeByteArray(decodeImageByts,0,decodeImageByts.length);
            return testImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
