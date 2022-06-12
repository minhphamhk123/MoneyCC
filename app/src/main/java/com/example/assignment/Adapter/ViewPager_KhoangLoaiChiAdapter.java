package com.example.assignment.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.assignment.Fragment.KhoangChiFragment;
import com.example.assignment.Fragment.LoaiChiFragment;

public class ViewPager_KhoangLoaiChiAdapter extends FragmentStatePagerAdapter {
    public ViewPager_KhoangLoaiChiAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                KhoangChiFragment khoangChiFragment = new KhoangChiFragment();
                return khoangChiFragment;
            case 1:
                LoaiChiFragment loaiChiFragment = new LoaiChiFragment();
                return loaiChiFragment;
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
                title = "Khoản Chi ";
                break;
            case 1:
                title = "Loại Chi";
                break;
        }
        return title;
    }
}
