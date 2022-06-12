package com.example.assignment.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment.Interface.SetOnItemClickListener;
import com.example.assignment.Model.LoaiThu;
import com.example.assignment.R;

import java.util.List;

public class LoaiThuAdapter extends RecyclerView.Adapter<LoaiThuAdapter.ViewHolder> {

    private Context context;
    private int layout;
    private List<LoaiThu> arrLoaiThu;

    public LoaiThuAdapter(Context context, int layout, List<LoaiThu> arrLoaiThu) {
        this.context = context;
        this.layout = layout;
        this.arrLoaiThu = arrLoaiThu;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        LoaiThu loaiThu = arrLoaiThu.get(i);
        viewHolder.txtten.setText(loaiThu.getTen());
        viewHolder.imgedt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onEdit(i);
            }
        });
        viewHolder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDelete(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrLoaiThu.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imghinh, imgedt, imgdelete;
        TextView txtten;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinh = itemView.findViewById(R.id.imgtienloaithu);
            imgedt = itemView.findViewById(R.id.imgedtloaithu);
            imgdelete = itemView.findViewById(R.id.imgedtxoa);
            txtten = itemView.findViewById(R.id.txttenloaithu);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
    //
    public SetOnItemClickListener listener;
    //
    public void setOnClickListener(SetOnItemClickListener listener){
        this.listener = listener;
    }

}
