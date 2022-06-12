package com.example.assignment.Fragment;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import java.util.ArrayList;
import java.util.Calendar;

public class FragmentNam extends Fragment {

    DataBase dataBase;
    int tongthu = 0;
    int tongchi = 0;
    int sodu = 0;
    //
    PieChart pieChart;
    String namHienTai;
    TextView txttongthu, txttongchi, txtsodu;
    ArrayList<ThangNam> arrThangNam = new ArrayList<>();
    Calendar calendar = Calendar.getInstance(); //getInstance

    String tongThuFormat = String.valueOf(0);
    String tongChiFormat = String.valueOf(0);
    String soduYearFormat = String.valueOf(0);
    //
    DecimalFormat format = new DecimalFormat("#,###,###,###");

    Spinner spinner;
    Spinner_Thang_Adapter spinner_thang_adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nam, container, false);
        dataBase = new DataBase(getActivity());
        spinner =  view.findViewById(R.id.spinnerchonnam);
        pieChart = view.findViewById(R.id.piechart2);
        txttongthu = view.findViewById(R.id.txttongthunam);
        txttongchi = view.findViewById(R.id.txttongchinam);
        txtsodu = view.findViewById(R.id.txt_sodunam);
        //
        spinner_thang_adapter = new Spinner_Thang_Adapter(arrThangNam, getActivity(), R.layout.dong_spinner_thang);
        dataNam();
        spinner.setAdapter(spinner_thang_adapter);
        spinner();
        return view;
    }

    private void dataNam() {
        arrThangNam.add(new ThangNam(0, "Chọn Năm"));
        arrThangNam.add(new ThangNam(1, "2019"));
        //arrThangNam.add(new ThangNam(2, "2018"));
        //arrThangNam.add(new ThangNam(3, "2017"));
        //arrThangNam.add(new ThangNam(4, "2016"));
        //arrThangNam.add(new ThangNam(5, "2015"));
    }

    private String namhientai() {
        int nam = calendar.get(Calendar.YEAR);
        namHienTai = "/" + nam;
        return namHienTai;
    }

    private void getThu() {
        Cursor cursor = dataBase.getData("SELECT * FROM khoangthu WHERE deleteflag = 0 ");
        int usd = 0;
        int tovnd = 23000;
        int vietnamdong = 0;
        int vnd = 0;
        while (cursor.moveToNext()) { // moveToNext
            int dinhmucthu = cursor.getInt(2);
            String donVi = cursor.getString(3);
            String ngayThang = cursor.getString(4);
            //ngayThang = "'"+ngayThang+"'";
            //namHienTai = "'"+namHienTai+"'";
            //
            Log.d("CSDL Của Thu ", ngayThang + "");
            Log.d("NAM HIEN TAI Của Thu ", namHienTai + "");
            //
            if (ngayThang.contains(namHienTai)) {
            //if (ngayThang == namHienTai) {
                if (donVi.equalsIgnoreCase("USD")) {
                    usd += dinhmucthu;
                    vnd = usd * tovnd;
                }
                //
                if (donVi.equals("VND")) {
                    vietnamdong += dinhmucthu;
                }
            }
        }
        //
        cursor.close();
        tongthu = vnd + vietnamdong;
        //note sodu nam
        sodu = sodu + tongthu;
        tongThuFormat = format.format(tongthu);
        Log.d("TONGTHU", tongthu + "");
    }

    private void getChi() {
        Cursor cursor = dataBase.getData("SELECT * FROM khoangchi WHERE deleteflag = 0 ");
        int usd = 0;
        int tovnd = 23000;
        int vietnamdong = 0;
        int vnd = 0;
        while (cursor.moveToNext()) {
            int dinhmucthu = cursor.getInt(2);
            String donVi = cursor.getString(3);
            String ngayThang = cursor.getString(4);
            //ngayThang = "'"+ngayThang+"'";
            //namHienTai = "'"+namHienTai+"'";
            //
            Log.d("CSDL Của Tháng CHI ", ngayThang + "");
            Log.d("NAM HIEN TAI Của CHI ", namhientai() + "");
            //
            if (ngayThang.contains(namHienTai)) {
                if (donVi.equalsIgnoreCase("USD")) {
                    usd += dinhmucthu;
                    vnd = usd * tovnd;
                }
                if (donVi.equals("VND")) {
                    vietnamdong += dinhmucthu;

                }
            }
        }
        //
        cursor.close();
        tongchi = vnd + vietnamdong;
        //note sodu nam
        sodu = sodu - tongchi;
        tongChiFormat = format.format(tongchi);
        Log.d("TONGCHI", tongchi + "");
    }

    private void spinner() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ThangNam thangNam = arrThangNam.get(i);
                namHienTai = "/" + thangNam.getThang();
                Log.d("namHienTai ",namHienTai + "");
                //
                if (namHienTai.equals("/Chọn Năm")) {

                }
                else {
                    sodu = 0;
                    getChi();
                    getThu();
                    pieChart();
                    txttongthu.setText("Tổng Thu: " + tongThuFormat + " VND");
                    txttongchi.setText("Tổng Chi: " + tongChiFormat + " VND");
                    //
                    soduYearFormat = format.format(sodu);
                    txtsodu.setText("Số dư Năm: " + soduYearFormat + " VND");
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
        pieEntries.add(new PieEntry(tongchi, "Tổng Chi (%)"));
        pieEntries.add(new PieEntry(tongthu, "Tổng Thu (%)"));

        pieChart.animateY(1800, Easing.EaseInOutCirc);
        PieDataSet dataSet = new PieDataSet(pieEntries, " ");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.CYAN);

        pieChart.setData(data);
    }

}
