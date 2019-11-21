package com.val.techberries.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


import com.val.techberries.R;

import java.util.ArrayList;

public class ViewPagerForProductActivity extends PagerAdapter {
    private Context context;


    private ArrayList<Integer> imagesForViewPager;

    public ViewPagerForProductActivity(Context context, ArrayList<Integer> imagesForViewPager) {
        this.context = context;
        this.imagesForViewPager = imagesForViewPager;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_for_product_view_pager,null);
        ImageView imageView= view.findViewById(R.id.productImage);
        imageView.setImageDrawable(context.getResources().getDrawable(getImageAt(position)));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return object.equals(view);
    }

    //вернёт id  картинки для отображения
    private int getImageAt(int position) {
        return imagesForViewPager.get(position);
    }

    public void setImagesForViewPager(ArrayList<Integer> imagesForViewPager) {
        this.imagesForViewPager = imagesForViewPager;
    }
}
