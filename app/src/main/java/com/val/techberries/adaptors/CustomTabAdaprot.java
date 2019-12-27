package com.val.techberries.adaptors;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.val.techberries.fragments.Tabs.CourierTab;
import com.val.techberries.fragments.Tabs.PickUpTab;

public class CustomTabAdaprot extends FragmentStatePagerAdapter {

    private int tubNumber;

    public CustomTabAdaprot(FragmentManager fm, Integer tubNumber) {
        super(fm);
        this.tubNumber = tubNumber;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PickUpTab();
            case 1:
                return new CourierTab();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tubNumber;
    }
}
