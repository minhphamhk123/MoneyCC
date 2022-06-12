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
import android.widget.TextView;
import android.widget.Toast;


import com.example.assignment.DataBase.DataBase;
import com.example.assignment.Model.KhoangChi;
import com.example.assignment.Model.KhoangThu;
import com.example.assignment.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FragmentHomNay extends Fragment {

    PieChart pie;
    SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat format = new DecimalFormat("#,###,###,###");
    DataBase dataBase;
    TextView txttongchi, txttongthu, txtsodu;
    //
    int tongThuNgay = 0; int soDu = 0;
    String tongthuFormat = String.valueOf(0);
    String tongchiFormat = String.valueOf(0);
    String soduFormat = String.valueOf(0);
    //
    Calendar calendar = Calendar.getInstance();
    int tongChiNgay = 0;
    String ngayHienTai;

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homnay, container, false);
        dataBase = new DataBase(getActivity());
        pie = view.findViewById(R.id.piechart);
        txttongchi = view.findViewById(R.id.txttongchi);
        txttongthu = view.findViewById(R.id.txttongthu);
        txtsodu = view.findViewById(R.id.txtsodu);
        //
        ngatHienTai();
        getChi();
        getThu();
        pieChart();
        txttongthu.setText("Tổng Thu: " + tongthuFormat + " VND");
        txttongchi.setText("Tổng Chi: " + tongchiFormat + " VND");
        //note tinh sodu
        soduFormat = format.format(soDu);
        txtsodu.setText("Số dư: " + soduFormat + " VND");
        return view;
    }

    public void ngatHienTai() {
        int nam = calendar.get(Calendar.YEAR);
        int thang = calendar.get(Calendar.MONTH);
        int ngay = calendar.get(Calendar.DAY_OF_MONTH);
        ngayHienTai = ngay + "/" + (thang + 1) + "/" + nam;
        Date date = null;
        try {
            date = simpleDate.parse(ngayHienTai);
            ngayHienTai = simpleDate.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void getThu() {
        Cursor cursor = dataBase.getData("SELECT * FROM " + DataBase.KEY_TABLE_NAME_KHOANGTHU + " WHERE " + DataBase.KEY_TABLE_DELETEFLAG_KHOANGTHU + " = 0 ");
        int usd = 0;
        int tovnd = 23000;
        int vnd = 0;
        int vietNamDong = 0;
        while (cursor.moveToNext()) {
            {
                int dinhmucthu = cursor.getInt(2);
                String donVi = cursor.getString(3);
                String ngayThang = cursor.getString(4);

//                    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
//                    Date date = format.parse(ngayThang);
                Log.d("LAYCSDL", ngayThang + "");
                Log.d("NGAYHIENTAITONGTHU", ngayHienTai + "");
                if (ngayHienTai.contains(ngayThang)) {
                    Log.d("ngaythang", ngayThang + "");
                    if (donVi.equalsIgnoreCase("USD")) {
                        usd += dinhmucthu;
                        vnd = (usd * tovnd);
                    }
                    if (donVi.equalsIgnoreCase("VND")) {
                        vietNamDong += dinhmucthu;
                    }
                }
            }
        }
        cursor.close();

        tongThuNgay = vnd + vietNamDong;
        //note tinh sodu
        soDu = soDu + tongThuNgay;
        tongthuFormat = format.format(tongThuNgay);
        Log.d("TONGTHU", tongThuNgay + "");
    }

    private void getChi() {
        Cursor cursor = dataBase.getData("SELECT * FROM " + DataBase.KEY_TABLE_NAME_KHOANGCHI + " WHERE " + DataBase.KEY_TABLE_DELETEFLAG_KHOANGCHI + " = 0 ");
        int usd = 0;
        int tovnd = 23000;
        int vnd = 0;
        int vietnamdong = 0;
        while (cursor.moveToNext()) {
            int dinhmucthu = cursor.getInt(2);
            String donVi = cursor.getString(3);
            String ngayThang = cursor.getString(4);
            if (ngayHienTai.contains(ngayThang)) {
                Log.d("NGAYHIENTAI", ngayHienTai + "");
                if (donVi.equalsIgnoreCase("USD")) {
                    usd += dinhmucthu;
                    vnd = (usd * tovnd);
                }
                if (donVi.equalsIgnoreCase("VND")) {
                    vietnamdong += dinhmucthu;
                }
            }

        }
        cursor.close();

        tongChiNgay = vnd + vietnamdong;
        //note tinh sodu
        soDu = soDu - tongChiNgay;
        tongchiFormat = format.format(tongChiNgay);

        Log.d("TONGCHI", tongChiNgay + "");
    }

    private void pieChart() {
        pie.setUsePercentValues(true);
        pie.getDescription().setEnabled(false);
        pie.setExtraOffsets(5, 5, 5, 5);
        pie.setDragDecelerationFrictionCoef(0.99f);

        pie.setDrawHoleEnabled(false);
        pie.setHoleColor(Color.RED);
        pie.setTransparentCircleRadius(61f);

        ArrayList<PieEntry>
                pieEntries = new ArrayList<>();
        pieEntries.clear();
        pieEntries.add(new PieEntry(tongChiNgay, "Tổng Chi (%)"));
        pieEntries.add(new PieEntry(tongThuNgay, "Tổng Thu (%)"));

        pie.animateY(1800, Easing.EaseInOutCirc);
        PieDataSet dataSet = new PieDataSet(pieEntries, " ");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.BLUE);

        pie.setData(data);
    }
}
