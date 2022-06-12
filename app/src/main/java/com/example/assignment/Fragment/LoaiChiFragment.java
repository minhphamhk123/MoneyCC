package com.example.assignment.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment.Adapter.LoaiChiAdapter;
import com.example.assignment.DataBase.DataBase;
import com.example.assignment.Interface.SetOnItemClickListener;
import com.example.assignment.Model.LoaiChi;
import com.example.assignment.R;
import com.kinda.alert.KAlertDialog;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class LoaiChiFragment extends Fragment {
    FloatingActionButton floatingActionButton;
    DataBase dataBase;
    ArrayList<LoaiChi> arrLoaiChi = new ArrayList<>();
    LoaiChiAdapter loaiChiAdapter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loaichi, container, false);
        floatingActionButton = view.findViewById(R.id.floatingloaichi);
        recyclerView = view.findViewById(R.id.recyclerviewloaichi);

        dataBase = new DataBase(getActivity());
        recyclerView.setHasFixedSize(true);
        loaiChiAdapter = new LoaiChiAdapter(getActivity(), R.layout.dong_loaichi, arrLoaiChi);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(loaiChiAdapter);
        loaiChiAdapter.SetOnClickListenner(new SetOnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {

            }

            @Override
            public void onEdit(int position) {
                LoaiChi loaiChi = arrLoaiChi.get(position);
                dialogSua(loaiChi);
            }

            @Override
            public void onDelete(int position) {
                LoaiChi loaiChi = arrLoaiChi.get(position);
                alerDiaglogXoa(loaiChi);

            }
        });
        getData();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAdd();
            }
        });
        //
        return view;
    }

    private void dialogAdd() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_diaglog_add_lc);
        final EditText edtthemlc = dialog.findViewById(R.id.edtloaichi);
        Button btnthemlc = dialog.findViewById(R.id.btnthemloaichi);
        Button btnhuylc = dialog.findViewById(R.id.btnhuyloaichi);
        btnthemlc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edtthemlc.getText().toString().trim();
                if (edtthemlc.length() == 0) {
                    Toasty.warning(getActivity(), "Vui Lòng Nhập Đầy Đủ Thông Tin",
                            Toast.LENGTH_SHORT, true).show();
                    edtthemlc.requestFocus();
                    return;
                } else {
                    CustomDialogSuccess();
                    dataBase.addLoaiChi(new LoaiChi(0, 0, ten));
                    if (arrLoaiChi.size() > 0)
                        arrLoaiChi.clear();
                    arrLoaiChi.addAll(dataBase.getAllListLoaiChi());

                }
                getData();
                dialog.dismiss();
            }
        });
        btnhuylc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void getData() {
        Cursor cursor = dataBase.getData("SELECT * FROM " + DataBase.KEY_TABLE_NAME_LOAICHI + " WHERE " + DataBase.KEY_TABLE_DELETEFLAG_LOAICHI + " = 0 ");
        arrLoaiChi.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            int delete = cursor.getInt(2);
            arrLoaiChi.add(new LoaiChi(id, delete, ten));
        }
        loaiChiAdapter.notifyDataSetChanged();
    }

    private void dialogSua(final LoaiChi loaiChi) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_dialog_sua_lc);
        dialog.setCanceledOnTouchOutside(true);
        final EditText edtSua = dialog.findViewById(R.id.edtsualoaihi);
        Button btnthemlc = dialog.findViewById(R.id.btnthemlc);
        Button btnhuylc = dialog.findViewById(R.id.btnhuylc);
        final String ten = loaiChi.getTen();
        edtSua.setText(ten);
        btnthemlc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtSua.length() == 0) {
                    Toasty.warning(getActivity(), "Vui Lòng Nhập Đầy Đủ Thông Tin",
                            Toast.LENGTH_SHORT, true).show();
                    edtSua.requestFocus();
                    return;
                } else {
                    loaiChi.setTen(edtSua.getText().toString());
                    dataBase.edtLoaiChi(loaiChi);
                    CustomDialogSuccess();
                    getData();
                    dialog.dismiss();
                }
            }
        });
        btnhuylc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void alerDiaglogXoa(final LoaiChi loaiChi) {
        final KAlertDialog kAlertDialog = new KAlertDialog(getActivity(), KAlertDialog.WARNING_TYPE);
        kAlertDialog.setTitleText("Thông Báo?");
        kAlertDialog.setContentText("Bạn có chắc muốn xóa " + loaiChi.getTen() + " này không!");
        kAlertDialog.setCancelText("Không");
        kAlertDialog.setConfirmText("Có");
        kAlertDialog.showCancelButton(true);
        kAlertDialog.setConfirmClickListener(new KAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(KAlertDialog diaglog) {
                dataBase.QueryData("UPDATE loaichi SET deleteflag = 1 WHERE id = '" + loaiChi.getId() + "' ");
                getData();
                Toasty.success(getActivity(), "Xóa Thành Công", Toast.LENGTH_SHORT, true).show();
                kAlertDialog.dismissWithAnimation();
            }
        });
        kAlertDialog.setCancelClickListener(new KAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(KAlertDialog kAlertDialog) {
                Toasty.error(getActivity(), "Xóa Thất Bại", Toast.LENGTH_SHORT, true).show();
                kAlertDialog.dismissWithAnimation();
            }
        });
        kAlertDialog.show();
    }

    private void CustomDialogSuccess() {
        KAlertDialog kAlertDialog = new KAlertDialog(getActivity(), KAlertDialog.SUCCESS_TYPE);
        kAlertDialog.setTitleText("Hoàn Thành!");
        kAlertDialog.setContentText("Đã Thêm Thành Công");
        kAlertDialog.show();
    }


}
