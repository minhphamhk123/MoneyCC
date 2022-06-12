package com.example.assignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.assignment.Model.LoaiChi;
import com.example.assignment.R;

import java.util.List;

public class Spinner_KhoangChi_Adapter extends BaseAdapter {
    private Context context;
    private List<LoaiChi> arrLoaiChi;
    private int layout;
    TextView txtchonchi;

    public Spinner_KhoangChi_Adapter(Context context, List<LoaiChi> arrLoaiChi, int layout) {
        this.context = context;
        this.arrLoaiChi = arrLoaiChi;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return arrLoaiChi.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(layout, null, false);
        txtchonchi = view.findViewById(R.id.txtchonkc);
        LoaiChi loaiChi = arrLoaiChi.get(i);
        txtchonchi.setText(loaiChi.getTen());
        return view;
    }
}
