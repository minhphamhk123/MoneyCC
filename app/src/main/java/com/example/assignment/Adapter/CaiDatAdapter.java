package com.example.assignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment.Model.CaiDat;
import com.example.assignment.R;

import java.util.List;

public class CaiDatAdapter extends BaseAdapter {
    private List<CaiDat> arrCaiDat;
    private int layout;
    private Context context;

    public CaiDatAdapter(List<CaiDat> arrCaiDat, int layout, Context context) {
        this.arrCaiDat = arrCaiDat;
        this.layout = layout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrCaiDat.size();
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
        TextView textView = view.findViewById(R.id.txtcaidat);
        ImageView imageView = view.findViewById(R.id.imghinh);
        //
        CaiDat caiDat = arrCaiDat.get(i);
        textView.setText(caiDat.getTen());
        imageView.setImageResource(caiDat.getHinh());
        return view;
    }
}
