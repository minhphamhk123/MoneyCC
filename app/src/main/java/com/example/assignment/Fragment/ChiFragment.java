package com.example.assignment.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment.Adapter.ViewPager_KhoangLoaiChiAdapter;
import com.example.assignment.R;

public class ChiFragment extends Fragment {
    ViewPager_KhoangLoaiChiAdapter viewPagerKhoangLoaiChiAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;
    //
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chi, container, false);
        viewPager = view.findViewById(R.id.view_pagerchi);
        tabLayout = view.findViewById(R.id.tablayoutchi);
        viewPagerKhoangLoaiChiAdapter = new ViewPager_KhoangLoaiChiAdapter(getChildFragmentManager());
        viewPager.setAdapter(viewPagerKhoangLoaiChiAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        return view;
    }
}
