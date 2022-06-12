package com.example.assignment.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.assignment.Fragment.LienHeFragment;
import com.example.assignment.Fragment.TacGiaFragment;

public class ViewPager_GioiThieuAdapter extends FragmentStatePagerAdapter {
    public ViewPager_GioiThieuAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                LienHeFragment lienHeFragment = new LienHeFragment();
                return lienHeFragment;
            case 1:
                TacGiaFragment tacGiaFragment = new TacGiaFragment();
                return tacGiaFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Liên Hệ";
                break;
            case 1:
                title = "Tác Giả";
                break;
        }
        return title;
    }
}
