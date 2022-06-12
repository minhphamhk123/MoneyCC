package com.example.assignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.assignment.Model.LoaiThu;
import com.example.assignment.R;

import java.util.List;

public class Spinner_KhoangThu_Adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<LoaiThu>arrLoaiThu;
    TextView txtchonchi;

    public Spinner_KhoangThu_Adapter(Context context, int layout, List<LoaiThu> arrLoaiThu) {
        this.context = context;
        this.layout = layout;
        this.arrLoaiThu = arrLoaiThu;
    }

    @Override
    public int getCount() {
        return arrLoaiThu.size();
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
        txtchonchi = view.findViewById(R.id.txtchonchi);
        LoaiThu loaiThu = arrLoaiThu.get(i);
        txtchonchi.setText(loaiThu.getTen());
        return view;
    }
}
