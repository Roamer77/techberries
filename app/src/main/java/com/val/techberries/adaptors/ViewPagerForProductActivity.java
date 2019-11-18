package com.val.techberries.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


import com.val.techberries.R;

public class ViewPagerForProductActivity extends PagerAdapter {
    private Context context;

    public ViewPagerForProductActivity(Context context) {
        this.context = context;
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
        switch (position) {
            case 0:
                return R.drawable.product_img0;
            case 1:
                return R.drawable.product_img1;
            case 2:
                return R.drawable.product_img2;
            default:
                return R.drawable.product_img0;
        }
    }
}
