package com.example.assignment.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.assignment.Fragment.KhoangThuFragment;
import com.example.assignment.Fragment.LoaiThuFragment;

public class ViewPager_KhoangLoaiThuAdapter extends FragmentStatePagerAdapter {
    public ViewPager_KhoangLoaiThuAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                KhoangThuFragment khoangThuFragment = new KhoangThuFragment();
                return khoangThuFragment;
            case 1:
                LoaiThuFragment loaiThuFragment = new LoaiThuFragment();
                return loaiThuFragment;
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
                title = "Khoản Thu";
                break;
            case 1:
                title = "Loại Thu";
                break;
        }
        return title;
    }
}
