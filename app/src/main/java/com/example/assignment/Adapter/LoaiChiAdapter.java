package com.example.assignment.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment.Interface.SetOnItemClickListener;
import com.example.assignment.Model.LoaiChi;
import com.example.assignment.R;

import java.util.List;

public class LoaiChiAdapter extends RecyclerView.Adapter<LoaiChiAdapter.ViewHolder> {

    private Context context;
    private int layout;
    private List<LoaiChi> arrLoaiChi;

    public LoaiChiAdapter(Context context, int layout, List<LoaiChi> arrLoaiChi) {
        this.context = context;
        this.layout = layout;
        this.arrLoaiChi = arrLoaiChi;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        LoaiChi loaiChi = arrLoaiChi.get(i);
        viewHolder.textView.setText(loaiChi.getTen());
        viewHolder.imgsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onEdit(i);
            }
        });
        viewHolder.imgxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDelete(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrLoaiChi.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imghinh,imgsua,imgxoa;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinh = itemView.findViewById(R.id.imgtienloaichi);
            imgsua = itemView.findViewById(R.id.imgedtloaichi);
            imgxoa = itemView.findViewById(R.id.imgdeleteloaichi);
            textView = itemView.findViewById(R.id.txttenloaichi);
        }
    }
    //
    public SetOnItemClickListener listener;
    //
    public void SetOnClickListenner(SetOnItemClickListener setOnItemClickListener){
        this.listener = setOnItemClickListener;
    }

}
