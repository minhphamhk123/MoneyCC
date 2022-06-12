package com.example.assignment.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment.DataBase.DataBase;
import com.example.assignment.Model.NguoiDung;
import com.example.assignment.R;

import es.dmoral.toasty.Toasty;

// Đăng ký người dùng cá nhân mới nè =))
public class RegisterActivity extends AppCompatActivity {

    EditText edthoten, edtuser,edtpass;
    Button btndangky,btnhuy;
    DataBase dataBase;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        anhXa();
        dataBase = new DataBase(this);
        //
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vreg) {
              checkvalid();
            }
        });
        //
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vhuy) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });
    }
    private void anhXa(){
        edthoten = findViewById(R.id.edthotendangky);
        edtuser = findViewById(R.id.edtuserdangky);
        edtpass  = findViewById(R.id.edtpassdangky);
        btndangky = findViewById(R.id.btnDangky);
        btnhuy = findViewById(R.id.btnhuydangky);
    }
    //
    private void checkvalid() {
        //
        String hoten = edthoten.getText().toString();
        String user = edtuser.getText().toString();
        String pass = edtpass.getText().toString();
        //
        if(hoten.isEmpty()){
            edthoten.setError("Vui Lòng Nhập Họ Tên!");
            edthoten.requestFocus();
        }
        else if (user.isEmpty()) {
            edtuser.setError("Vui Lòng Nhập Tài Khoản!");
            edtuser.requestFocus();

        }else if(pass.isEmpty()){
            edtpass.setError("Vui Lòng Nhập Mật Khẩu!");
            edtpass.requestFocus();
        }
        else {
            //NguoiDung nguoiDung = new NguoiDung();
            //nguoiDung.setUserName(user);
            //nguoiDung.setPass(pass);
            Boolean RegGood = dataBase.addNguoiDung(user, pass);
            //
            if (RegGood){
                Toasty.success(this, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
                //
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                //
                intent.putExtra(LoginActivity.KEY_HOTEN_FROM_REGISTER, hoten);
                intent.putExtra(LoginActivity.KEY_USER_FROM_REGISTER, user);
                intent.putExtra(LoginActivity.KEY_PASSWORD_FROM_REGISTER, pass);
                //
                //send data
                setResult(RESULT_OK, intent);
                finish();
            }
            else{
                Toasty.error(this, "Đăng Ký Không Thành Công", Toast.LENGTH_SHORT).show();
                edthoten.setText("");
                edtuser.setText("");
                edtpass.setText("");
                //
                edthoten.requestFocus();
            }

        }
    }
}
