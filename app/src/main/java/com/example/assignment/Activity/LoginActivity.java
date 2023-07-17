package com.example.assignment.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.DataBase.DataBase;
import com.example.assignment.R;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {

    public static final String KEY_HOTEN_TO_PRE = "KEY_HOTEN_TO_PRE";
    public static final String KEY_USER_TO_PRE = "KEY_USER_TO_PRE";
    public static final String KEY_PASS_TO_PRE = "KEY_PASS_TO_PRE";
    //
    public static final int REQUEST_CODE_REGISTER = 1;
    public static final int REQUEST_CODE_UPDATEPASS = 2;
    //
    public static final String KEY_USER_FROM_REGISTER = "KEY_USER_FROM_REGISTER";
    public static final String KEY_PASSWORD_FROM_REGISTER = "KEY_PASSWORD_FROM_REGISTER";
    public static final String KEY_HOTEN_FROM_REGISTER = "MINH_PHAM";
    //
    public static final String KEY_USER_FROM_UPDATEPASS = "KEY_USER_FROM_UPDATEPASS";
    public static final String KEY_PASSWORD_FROM_UPDATEPASS = "KEY_PASSWORD_FROM_UPDATEPASS";
    //
    CheckBox cbmk;
    TextView updatepass;
    Button btndangnhap, btndangky;
    DataBase dataBase;
    EditText edt_loguser, edt_logpass;
    String hoTen = "Genesis I";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhXa();
        //NAVI();
        //
        dataBase = new DataBase(this);
        //
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkvalid();
            }
        });
        //
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                REG();
            }
        });
        //
        updatepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(LoginActivity.this, UpdatepassActivity.class));
                UPDATE();
            }
        });

        //checkbox is tick
        if(! new PrefManager(this).isUserLogedOut()){
            StartActivity();
        }
    }

    private void StartActivity(){
        //Intent intent = new Intent(this, PreviewActivity.class);
        //
        String User = new PrefManager(this).getUser();
        String Pass = new PrefManager(this).getPass();
        //
        edt_loguser.setText(User);
        edt_logpass.setText(Pass);
        //cbmk.setChecked(true);
    }

    //cho phép received data from register
    private void REG(){
        Intent myintent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivityForResult(myintent, REQUEST_CODE_REGISTER);
    }

    //cho phep received data from updatepass
    private void UPDATE(){
        Intent Myintent = new Intent(LoginActivity.this, UpdatepassActivity.class);
        startActivityForResult(Myintent, REQUEST_CODE_UPDATEPASS);
    }

    @Override
    protected void onActivityResult(int requestcode, int resultcode, Intent data){
        super.onActivityResult(requestcode, resultcode, data);
        //
        if(requestcode == REQUEST_CODE_UPDATEPASS && resultcode == Activity.RESULT_OK){
            String user_update = data.getStringExtra(KEY_USER_FROM_UPDATEPASS);
            String pass_update = data.getStringExtra(KEY_PASSWORD_FROM_UPDATEPASS);
            //
            edt_loguser.setText(user_update);
            edt_logpass.setText(pass_update);
        }
        //
        if(requestcode == REQUEST_CODE_REGISTER && resultcode == Activity.RESULT_OK){
            //
            hoTen = data.getStringExtra(KEY_HOTEN_FROM_REGISTER);
            String user_reg = data.getStringExtra(KEY_USER_FROM_REGISTER);
            String pass_reg = data.getStringExtra(KEY_PASSWORD_FROM_REGISTER);
            //
            edt_loguser.setText(user_reg);
            edt_logpass.setText(pass_reg);
        }

    }

    private boolean checkvalid() {
        String user = edt_loguser.getText().toString().trim();
        String pass = edt_logpass.getText().toString().trim();
        //
        if (user.isEmpty()) {
            edt_loguser.setError("Vui Lòng Nhập Tên!");
            edt_loguser.requestFocus();
            return false;
        } else if (pass.isEmpty()) {
            edt_logpass.setError("Vui Lòng Nhập Mật Khẩu");
            edt_logpass.requestFocus();
            return false;
        }
        // Tạo user && pass default - Not need Login =))
        else if (user.equalsIgnoreCase("admin") && pass.equalsIgnoreCase("admin")) {
            //save user - pass
            if(cbmk.isChecked()){
                saveLoginDetails(user, pass);
            }
            //
            Toasty.success(LoginActivity.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT, true).show();
            //startActivity(new Intent(LoginActivity.this, MainActivity.class));
            //
            Intent myintent = new Intent(LoginActivity.this, AccountActivity.class);
            //
            myintent.putExtra(KEY_HOTEN_TO_PRE, hoTen);
            myintent.putExtra(KEY_USER_TO_PRE, user);
            myintent.putExtra(KEY_PASS_TO_PRE, pass);
            //
            startActivity(myintent);
            finish();
            return true;
        }
        // Kiểm tra checkbox (user, pass) là true =))
        else {
            boolean kt = dataBase.kiemTraDangNhap(user, pass);
            if (kt) {

                // save user - pass
                if(cbmk.isChecked()){
                    saveLoginDetails(user, pass);
                }
                //
                Toasty.success(LoginActivity.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT, true).show();
                //
                Intent myintent = new Intent(LoginActivity.this, AccountActivity.class);
                //
                myintent.putExtra(KEY_HOTEN_TO_PRE, hoTen);
                myintent.putExtra(KEY_USER_TO_PRE, user);
                myintent.putExtra(KEY_PASS_TO_PRE, pass);
                //
                startActivity(myintent);
                finish();
                return true;
            }else {
                Toasty.error(LoginActivity.this, "Đăng Nhập Thất Bại", Toast.LENGTH_SHORT, true).show();
                //
                return false;
            }
        }

    }

    private void saveLoginDetails(String username, String password){
        new PrefManager(this).saveLoginDetail(username, password);
    }

    private void anhXa() {
        edt_loguser = findViewById(R.id.username_login);
        edt_logpass = findViewById(R.id.password_login);
        btndangky = findViewById(R.id.btndangky);
        btndangnhap = findViewById(R.id.btndangnhap);
        cbmk = findViewById(R.id.cbtaikhoan);
        updatepass = findViewById(R.id.log_txtupdate_pass);

    }
}
