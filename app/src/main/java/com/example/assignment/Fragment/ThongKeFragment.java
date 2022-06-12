package com.example.assignment.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.assignment.DataBase.DataBase;
import com.example.assignment.R;

public class ThongKeFragment extends Fragment implements View.OnClickListener {

    ProgressBar progressBar;
    DataBase dataBase;
    CardView today,thismonth,thisyear;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke, container, false);
        dataBase = new DataBase(getActivity());
        //
        today=view.findViewById(R.id.today);
        thismonth=view.findViewById(R.id.thismonth);
        thisyear=view.findViewById(R.id.thisyear);
        //
        today.setOnClickListener(this);
        thismonth.setOnClickListener(this);
        thisyear.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        int id = view.getId();
        switch (id){
            case R.id.today:
                FragmentHomNay fragment = new FragmentHomNay();
                fragmentTransaction.replace(R.id.contentlayout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.thismonth:
                FragmentThang fragmentThang  = new FragmentThang();
                fragmentTransaction.replace(R.id.contentlayout,fragmentThang);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.thisyear:
                FragmentNam fragmentNam = new FragmentNam();
                fragmentTransaction.replace(R.id.contentlayout,fragmentNam);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
        }
    }
}
