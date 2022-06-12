package com.example.assignment.Fragment;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.assignment.Adapter.Spinner_Thang_Adapter;
import com.example.assignment.DataBase.DataBase;
import com.example.assignment.Model.ThangNam;
import com.example.assignment.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FragmentThang extends Fragment {
    Spinner spinner;
    Spinner_Thang_Adapter spinner_thang_adapter;
    ArrayList<ThangNam> arrThangNam = new ArrayList<>();
    //
    int tongThu = 0;
    int tongChi = 0;
    int sodu = 0;
    //
    PieChart pieChart;
    DataBase dataBase;
    TextView txttongchi, txttongthu, txtsodu;
    //
    String tongthuFormat = String.valueOf(0);
    String tongchiFormat = String.valueOf(0);
    String soduMonthFormat = String.valueOf(0);

    String time;
    Calendar calendar = Calendar.getInstance();
    DecimalFormat format = new DecimalFormat("#,###,###,###");


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thang, container, false);
        dataBase = new DataBase(getActivity());
        spinner = view.findViewById(R.id.spinnerchonthang);
        txttongchi = view.findViewById(R.id.txttongchithang);
        txttongthu = view.findViewById(R.id.txttongthuthang);
        txtsodu = view.findViewById(R.id.txt_sodu);
        pieChart = view.findViewById(R.id.piechart1);

        arrThangNam.add(new ThangNam(0, "Chọn Tháng"));
        for (int i = 1; i < 13; i++) {
            arrThangNam.add(new ThangNam(i, "Tháng " + i));
        }

        spinner_thang_adapter = new Spinner_Thang_Adapter(arrThangNam, getActivity(), R.layout.dong_spinner_thang);
        spinner.setAdapter(spinner_thang_adapter);
        spinner();

        return view;
    }

    private void getChi() {
        Cursor cursor = dataBase.getData("SELECT * FROM khoangchi WHERE deleteflag = 0 ");
        int usd = 0;
        int tovnd = 23000;
        int vnd = 0;
        int vietnamdong = 0;
        while (cursor.moveToNext()) {
            int dinhmucthu = cursor.getInt(2);
            String donVi = cursor.getString(3);
            String ngayThang = cursor.getString(4);
            Log.d("CSDL Của Tháng Chi ", ngayThang + "");
            Log.d("THANG HIEN TAI Của Chi ", ngayHienTai() );
            if (ngayThang.contains(ngayHienTai())) {

                if (donVi.equalsIgnoreCase("USD")) {
                    usd += dinhmucthu;
                    vnd = usd * tovnd;
                }
                if (donVi.equalsIgnoreCase("VND")) {
                    vietnamdong += dinhmucthu;
                }
            }
        }
        cursor.close();
        tongChi = vietnamdong + vnd;
        sodu = sodu - tongChi;
        //note sodu
        tongchiFormat = format.format(tongChi);
        Log.d("TONGCHI", tongChi + "");
    }

    private void getThu() {
        Cursor cursor = dataBase.getData("SELECT * FROM khoangthu WHERE deleteflag = 0 ");
        int usd = 0;
        int tovnd = 23000;
        int vnd = 0;
        int vietnamdong = 0;
        while (cursor.moveToNext()) {
            int dinhmucthu = cursor.getInt(2);
            String donVi = cursor.getString(3);
            String ngayThang = cursor.getString(4);
            Log.d("CSDL Của Tháng Thu ", ngayThang + "");
            Log.d("THANG HIEN TAI Của Thu ", ngayHienTai() + "");
            if (ngayThang.contains(ngayHienTai())) {
                if (donVi.equalsIgnoreCase("USD")) {
                    usd += dinhmucthu;
                    vnd = usd * tovnd;
                }
                if (donVi.equalsIgnoreCase("VND")) {
                    vietnamdong += dinhmucthu;

                }
            }
        }
        cursor.close();
        tongThu = vnd + vietnamdong;
        sodu = sodu + tongThu;
        //note sodu
        tongthuFormat = format.format(tongThu);
        Log.d("TONGTHU", tongThu + "");
    }


    private String ngayHienTai() {
        if(time==null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                time = new SimpleDateFormat("MM/yyyy").format(calendar.getTimeInMillis());
            }
        }

        return time;
    }

    private void spinner() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ThangNam thangNam = arrThangNam.get(i);
                if (thangNam.getId() == 0) {

                    String txt = "";
                    pieChart.setNoDataText(txt);
                } else {
                    sodu = 0;
                    time = thangNam.getId() + "/" + calendar.get(Calendar.YEAR);
                    getChi();
                    getThu();
                    pieChart();

                    //
                    txttongthu.setText("Tổng Thu: " + tongthuFormat + " VND");
                    txttongchi.setText("Tổng Chi: " + tongchiFormat + " VND");
                    //
                    soduMonthFormat = format.format(sodu);
                    txtsodu.setText("Số dư Tháng: " + soduMonthFormat + " VND");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void pieChart() {
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 5, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.99f);

        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.RED);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry>
                pieEntries = new ArrayList<>();
        pieEntries.clear();
        pieEntries.add(new PieEntry(tongChi, "Tổng Chi (%)"));
        pieEntries.add(new PieEntry(tongThu, "Tổng Thu (%)"));

        pieChart.animateY(1800, Easing.EaseInOutCirc);
        PieDataSet dataSet = new PieDataSet(pieEntries, " ");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.BLUE);


        pieChart.setData(data);
    }

}
