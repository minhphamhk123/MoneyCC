package com.example.assignment.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
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

import com.example.assignment.Adapter.LoaiThuAdapter;
import com.example.assignment.DataBase.DataBase;
import com.example.assignment.Interface.SetOnItemClickListener;
import com.example.assignment.Model.LoaiThu;
import com.example.assignment.R;
import com.kinda.alert.KAlertDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class LoaiThuFragment extends Fragment {
    FloatingActionButton floatingActionButton;
    EditText edtloaithu;
    Button btnhuy, btnthem;
    RecyclerView recyclerView;
    LoaiThuAdapter loaiThuAdapter;
    ArrayList<LoaiThu> arrLoaiThu = new ArrayList<>();
    DataBase dataBase;
    //
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loaithu, container, false);
        floatingActionButton = view.findViewById(R.id.floatingloaithu);
        recyclerView = view.findViewById(R.id.recyclerviewloaithu);

        dataBase = new DataBase(getActivity());
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        loaiThuAdapter = new LoaiThuAdapter(getActivity(), R.layout.dong_loaithu, arrLoaiThu);
        recyclerView.setAdapter(loaiThuAdapter);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });
        return view;
    }

    private void addData() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_diaglog_add_lt);
        dialog.setCanceledOnTouchOutside(true);
        edtloaithu = dialog.findViewById(R.id.edtloaithu);
        btnhuy = dialog.findViewById(R.id.btnhuyloaithu);
        btnthem = dialog.findViewById(R.id.btnthemloaithu);
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String edt = edtloaithu.getText().toString().trim();
                if (edt.length() == 0) {
                    Toasty.warning(getActivity(), "Vui Lòng Nhập Đầy Đủ Thông Tin",
                            Toast.LENGTH_SHORT, true).show();
                    edtloaithu.requestFocus();
                    return;
                } else {
                    LoaiThu loaiThu = new LoaiThu();
                    loaiThu.setTen(edt);

                    dataBase.addLoaiThu(new LoaiThu(0, edt, 0));
                    CustomDialogSuccess();
                    if (arrLoaiThu.size() > 0) {
                        arrLoaiThu.clear();
                        arrLoaiThu.addAll(dataBase.getAllListLoaiThu());
                    }
                    dialog.dismiss();
                    getData();
                }
            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void getData() {
        Cursor cursor = dataBase.getData("SELECT * FROM " + DataBase.KEY_TABLE_NAME_LOAITHU + " WHERE " + DataBase.KEY_TABLE_DELETEFLAG_LOAITHU + " = 0 ");
        arrLoaiThu.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            int deleflag = cursor.getInt(2);
            arrLoaiThu.add(new LoaiThu(id, ten, deleflag));
        }
        loaiThuAdapter.notifyDataSetChanged();
    }



    private void dialogSua(final LoaiThu loaiThu) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.custom_dialog_sua_lt);
        final EditText edtsualt = dialog.findViewById(R.id.edtsualoaithu);
        Button btnthemlt = dialog.findViewById(R.id.btnthemlt);
        Button btnhuylt = dialog.findViewById(R.id.btnhuylt);
        final String ten = loaiThu.getTen();
        edtsualt.setText(ten);
        btnthemlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtsualt.length() == 0) {
                    Toasty.warning(getActivity(), "Vui Lòng Nhập Đầy Đủ Thông Tin",
                            Toast.LENGTH_SHORT, true).show();
                    edtsualt.requestFocus();
                } else {
                    loaiThu.setTen(edtsualt.getText().toString());
                    dataBase.editLoaiThu(loaiThu);
                    CustomDialogSuccess();
                    getData();
                    dialog.dismiss();
                }
            }
        });
        btnhuylt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loaiThuAdapter.setOnClickListener(new SetOnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
            }
            @Override
            public void onEdit(int position) {
                LoaiThu loaiThu = arrLoaiThu.get(position);
                dialogSua(loaiThu);
            }

            @Override
            public void onDelete(int position) {
                LoaiThu loaiThu = arrLoaiThu.get(position);
                alerDialogXoa(loaiThu);
            }
        });
        dataBase = new DataBase(getActivity());
        getData();

    }

    private void alerDialogXoa(final LoaiThu loaiThu) {
        final KAlertDialog kAlertDialog = new KAlertDialog(getActivity(), KAlertDialog.WARNING_TYPE);
        kAlertDialog.setTitleText("Thông Báo?");
        kAlertDialog.setContentText("Bạn có chắc muốn xóa " + loaiThu.getTen() + " này không!");
        kAlertDialog.setCancelText("Không");
        kAlertDialog.setConfirmText("Có");
        kAlertDialog.showCancelButton(true);
        kAlertDialog.setConfirmClickListener(new KAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(KAlertDialog diaglog) {
                dataBase.QueryData("UPDATE loaithu SET deleflag = 1 WHERE id = '" + loaiThu.getId() + "' ");
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