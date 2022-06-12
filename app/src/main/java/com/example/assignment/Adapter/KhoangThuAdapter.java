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
import com.example.assignment.Model.KhoangThu;
import com.example.assignment.R;

import java.util.List;

public class KhoangThuAdapter extends RecyclerView.Adapter<KhoangThuAdapter.ViewHolder> {
    private Context context;
    private int layout;
    private List<KhoangThu> arrKhoangThu;

    public KhoangThuAdapter(Context context, int layout, List<KhoangThu> arrKhoangThu) {
        this.context = context;
        this.layout = layout;
        this.arrKhoangThu = arrKhoangThu;
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
        KhoangThu khoangThu = arrKhoangThu.get(i);

        viewHolder.txtten.setText(khoangThu.getTen());
        viewHolder.txtngay.setText(khoangThu.getNgay());
        viewHolder.txttien.setText(khoangThu.getGia());
        viewHolder.txtDonVi.setText(khoangThu.getDonVi());
        viewHolder.imgedtsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onEdit(i);
            }
        });
        viewHolder.imgedtxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDelete(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrKhoangThu.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtten, txttien, txtngay,txtDonVi;
        ImageView imgedtsua,imgedtxoa;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtten = itemView.findViewById(R.id.txtkhoangthu);
            txttien = itemView.findViewById(R.id.txttienthu);
            txtngay = itemView.findViewById(R.id.txtngaythu);
            txtDonVi = itemView.findViewById(R.id.txtdonvitien);
            imgedtsua = itemView.findViewById(R.id.edtsuakhoangthu);

            imgedtxoa = itemView.findViewById(R.id.edtxoakhoangthu);
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
    private static SetOnItemClickListener listener;
    //
    public void setOnItemClickListener(SetOnItemClickListener listener) {
        this.listener = listener;
    }

}
