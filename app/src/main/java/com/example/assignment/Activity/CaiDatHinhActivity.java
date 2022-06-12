package com.example.assignment.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.example.assignment.R;

import java.util.List;

import io.paperdb.Paper;

public class CaiDatHinhActivity extends AppCompatActivity {
    PatternLockView patternLockView;
    String save_patter_key = "save";
    String finalpatter = "";
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("lock",MODE_PRIVATE);
        Paper.init(CaiDatHinhActivity.this);
        final String save_patter = Paper.book().read(save_patter_key);
        if (save_patter != null && !save_patter.equals("null")) {
            setContentView(R.layout.view);
            patternLockView = findViewById(R.id.patter1);
            patternLockView.addPatternLockListener(new PatternLockViewListener() {
                @Override
                public void onStarted() {

                }

                @Override
                public void onProgress(List<PatternLockView.Dot> progressPattern) {

                }

                @Override
                public void onComplete(List<PatternLockView.Dot> pattern) {
                    finalpatter = PatternLockUtils.patternToString(patternLockView, pattern);
                    if (finalpatter.equals(save_patter)) {
                        Toast.makeText(CaiDatHinhActivity.this, "PassWord Đúng", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CaiDatHinhActivity.this, "PassWord Sai", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCleared() {

                }
            });
        } else {
            setContentView(R.layout.activity_cai_dat_hinh);
            patternLockView = findViewById(R.id.patter);
            patternLockView.addPatternLockListener(new PatternLockViewListener() {
                @Override
                public void onStarted() {

                }

                @Override
                public void onProgress(List<PatternLockView.Dot> progressPattern) {

                }

                @Override
                public void onComplete(List<PatternLockView.Dot> pattern) {
                    finalpatter = PatternLockUtils.patternToString(patternLockView, pattern);
                    SharedPreferences.Editor edthinh = sharedPreferences.edit();
                    edthinh.putString(finalpatter, save_patter);
                    Paper.book().write(save_patter_key, finalpatter);
                    AlertDialog.Builder builder = new AlertDialog.Builder(CaiDatHinhActivity.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn đã thiết lập thành công mật khẩu hình");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
                    builder.show();
                }

                @Override
                public void onCleared() {
                }
            });
        }
    }
}
