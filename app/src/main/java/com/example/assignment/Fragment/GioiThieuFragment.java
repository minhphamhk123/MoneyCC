package com.example.assignment.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment.Adapter.ViewPager_GioiThieuAdapter;
import com.example.assignment.R;

public class GioiThieuFragment extends Fragment {
    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPager_GioiThieuAdapter viewPager_gioiThieuAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gioithieu, container, false);
        viewPager = view.findViewById(R.id.viewpagergioithieu);
        tabLayout = view.findViewById(R.id.tablayoutgioithieu);
        viewPager_gioiThieuAdapter = new ViewPager_GioiThieuAdapter(getChildFragmentManager());
        viewPager.setAdapter(viewPager_gioiThieuAdapter);
        tabLayout.setupWithViewPager(viewPager);
        setIconTablayout();
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        return view;
    }
    // setup icon
    private void setIconTablayout(){
        tabLayout.getTabAt(0).setIcon(R.drawable.contactacc);
        tabLayout.getTabAt(1).setIcon(R.drawable.authoracc);
    }
}
