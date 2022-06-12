package com.example.assignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.assignment.Model.ThangNam;
import com.example.assignment.R;

import java.util.List;

public class Spinner_Thang_Adapter extends BaseAdapter {
    private List<ThangNam> arrThangNam;
    private Context context;
    private int laytou;
    //
    public Spinner_Thang_Adapter(List<ThangNam> arrThangNam, Context context, int laytou) {
        this.arrThangNam = arrThangNam;
        this.context = context;
        this.laytou = laytou;
    }

    @Override
    public int getCount() {
        return arrThangNam.size();
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
        view = layoutInflater.inflate(laytou, null, false);
        TextView textView = view.findViewById(R.id.txtchonthang);
        ThangNam thangNam = arrThangNam.get(i);
        textView.setText(thangNam.getThang());
        return view;
    }
}
