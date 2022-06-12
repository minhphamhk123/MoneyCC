package com.example.assignment.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.assignment.Activity.MainActivity;
import com.example.assignment.Adapter.CaiDatAdapter;
import com.example.assignment.DataBase.DataBase;
import com.example.assignment.Model.CaiDat;
import com.example.assignment.Model.KhoangChi;
import com.example.assignment.Model.LoaiThu;
import com.example.assignment.R;
import com.kinda.alert.KAlertDialog;
import com.mahfa.dnswitch.DayNightSwitch;
import com.mahfa.dnswitch.DayNightSwitchListener;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class CaiDatFragment extends Fragment {
    ListView listView;
    CaiDatAdapter caiDatAdapter;
    ArrayList<CaiDat> arrCaiDat = new ArrayList<>();
    DataBase dataBase;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_caidat, container, false);
        listView = view.findViewById(R.id.lvsetting);

        dataBase = new DataBase(getActivity());
        arrCaiDat.add(new CaiDat("Khôi Phục Dữ Liệu Khoản Chi ", R.drawable.whatmoney));
        arrCaiDat.add(new CaiDat("Khôi Phục Dữ Liệu Khoản Khu ", R.drawable.savemoney));
        caiDatAdapter = new CaiDatAdapter(arrCaiDat, R.layout.dong_caidat, getActivity());
        listView.setAdapter(caiDatAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // edit 1:50 05-10
                switch (i) {
                    case 0:

                            KAlertDialog kAlertDialog = new KAlertDialog(getActivity(), KAlertDialog.WARNING_TYPE);
                            kAlertDialog.setTitleText("Thông Báo");
                            kAlertDialog.setContentText("Bạn Có Muốn Khôi Phục Dữ Liệu Của Khoản Chi Không?");
                            kAlertDialog.setConfirmText("Có");
                            kAlertDialog.showCancelButton(true);
                            kAlertDialog.setCanceledOnTouchOutside(true);
                            //
                            kAlertDialog.setConfirmClickListener(new KAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(KAlertDialog kAlertDialog) {
                                    dataBase.QueryData("UPDATE khoangchi SET deleteflag = 0");
                                    dataBase.QueryData("UPDATE loaichi SET deleteflag = 0");
                                    kAlertDialog.setTitleText("Thành Công");
                                    kAlertDialog.setContentText("Dữ Liệu Của Bạn Đã Được Khôi Phục");
                                    kAlertDialog.setConfirmText("OK");
                                    kAlertDialog.setConfirmClickListener(null);
                                    kAlertDialog.changeAlertType(KAlertDialog.SUCCESS_TYPE);
                                }
                            });
                            kAlertDialog.show();
                            //Toasty.error(getActivity(),"HÃY CHẮC CHẮN",205,false).show();
                        break;

                    case 1:
                        KAlertDialog kAlertDialog1 = new KAlertDialog(getActivity(), KAlertDialog.WARNING_TYPE);
                        kAlertDialog1.setTitleText("Thông Báo");
                        kAlertDialog1.setContentText("Bạn Có Muốn Khôi Phục Dữ Liệu Của Khoản Thu Không?");
                        kAlertDialog1.setConfirmText("Có");
                        kAlertDialog1.showCancelButton(true);
                        kAlertDialog1.setCanceledOnTouchOutside(true);
                        //
                        kAlertDialog1.setConfirmClickListener(new KAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(KAlertDialog kAlertDialog) {
                                dataBase.QueryData("UPDATE khoangthu SET deleteflag = 0");
                                dataBase.QueryData("UPDATE loaithu SET deleflag = 0");
                                kAlertDialog.setTitleText("Thành Công");
                                kAlertDialog.setContentText("Dữ Liệu Của Bạn Đã Được Khôi Phục");
                                kAlertDialog.setConfirmText("OK");
                                kAlertDialog.setConfirmClickListener(null);
                                kAlertDialog.changeAlertType(KAlertDialog.SUCCESS_TYPE);
                            }
                        });
                        kAlertDialog1.show();
                        //Toasty.error(getActivity(),"HÃY CHẮC CHẮN",205,false).show();
                        break;

                }

            }
        });

        return view;

    }

}
