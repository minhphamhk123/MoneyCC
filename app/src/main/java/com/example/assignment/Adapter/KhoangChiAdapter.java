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
import com.example.assignment.Model.KhoangChi;
import com.example.assignment.Model.LoaiChi;
import com.example.assignment.R;

import java.util.List;

public class KhoangChiAdapter extends RecyclerView.Adapter<KhoangChiAdapter.ViewHolder> {
    private Context context;
    private List<KhoangChi> arrKhoangChis;
    private int layout;

    public KhoangChiAdapter(Context context, List<KhoangChi> arrKhoangChis, int layout) {
        this.context = context;
        this.arrKhoangChis = arrKhoangChis;
        this.layout = layout;
    }

    @NonNull
    @Override
    public KhoangChiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhoangChiAdapter.ViewHolder viewHolder, final int i) {
        KhoangChi khoangChi = arrKhoangChis.get(i);
        viewHolder.txtten.setText(khoangChi.getTen());
        viewHolder.txtien.setText(khoangChi.getGia());
        viewHolder.txtdonviTien.setText(khoangChi.getDonVi());
        viewHolder.txtngaythu.setText(khoangChi.getNgay());
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
        return arrKhoangChis.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imghinhtien,imgsua,imgxoa;
        TextView txtten,txtien,txtdonviTien,txtngaythu;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            imghinhtien = itemView.findViewById(R.id.imgbagmoneylc);
            imgsua = itemView.findViewById(R.id.edtsuakhoangchi);
            imgxoa = itemView.findViewById(R.id.edtxoakhoangchi);
            txtten = itemView.findViewById(R.id.txtkhoangchi);
            txtien = itemView.findViewById(R.id.txttienchi);
            txtdonviTien = itemView.findViewById(R.id.txtdonvitienchi);
            txtngaythu = itemView.findViewById(R.id.txtngaychi);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onItemClick(itemView, getLayoutPosition());
                    }
                }
            });
        }
    }
    //
    public static SetOnItemClickListener listener;
    //
    public void  setOnItemClickListener(SetOnItemClickListener listener){
        this.listener = listener;
    };

}
