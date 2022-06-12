package com.example.assignment.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment.DataBase.DataBase;
import com.example.assignment.R;

import es.dmoral.toasty.Toasty;

// Cập nhật Mật Khẩu Mới
public class UpdatepassActivity extends AppCompatActivity {

    EditText edtuser, edt_oldpass, edt_newpass;
    Button btncapnhat;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatepass);
        anhXa();
        dataBase = new DataBase(this);
        btncapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vupdate) {
                checkvaloldid();
            }
        });
    }

    private void anhXa(){
        edtuser = findViewById(R.id.edtuserforgot_pass);
        edt_oldpass  = findViewById(R.id.edtoldpass);
        edt_newpass = findViewById(R.id.edtnewpass);
        btncapnhat = findViewById(R.id.btnCapnhat_pass);

    }

    private void checkvaloldid() {
        String user = edtuser.getText().toString();
        String oldpass = edt_oldpass.getText().toString();
        String newpass = edt_newpass.getText().toString();
        //
        Boolean LOGSuccess = dataBase.kiemTraDangNhap(user, oldpass);
        Boolean UpdateSuccess = dataBase.Updatepass(user, newpass);

        // kiem tra User & Pass is true
        if (user.isEmpty() || oldpass.isEmpty() || newpass.isEmpty()) {
            Toasty.warning(this, "Vui Lòng Nhập Đầy Đủ Thông Tin!", Toast.LENGTH_SHORT).show();
            //edtuser.setError("Vui Lòng Nhập Username");
            //edtuser.requestFocus();
        }
        else {
            // checkvaloldid có đúng hay không
            if(LOGSuccess){
                if(UpdateSuccess){
                    Toasty.success(this, "Cập Nhật Mật Khẩu Mới Thành Công", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(UpdatepassActivity.this, LoginActivity.class));
                    Intent myintent = new Intent(UpdatepassActivity.this, LoginActivity.class);
                    //
                    myintent.putExtra(LoginActivity.KEY_USER_FROM_UPDATEPASS, user);
                    myintent.putExtra(LoginActivity.KEY_PASSWORD_FROM_UPDATEPASS, newpass);
                    //
                    //send data
                    setResult(RESULT_OK, myintent);
                    finish();
                }
                else{
                    Toast.makeText(this, "Cập Nhật Mật Khẩu Không Thành Công", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toasty.error(this, "UserName hoặc Password Không Đúng!", Toast.LENGTH_LONG).show();
            }
        }
    }

}
