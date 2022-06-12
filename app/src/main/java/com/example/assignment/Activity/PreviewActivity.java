package com.example.assignment.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.assignment.R;

public class PreviewActivity extends AppCompatActivity {

    public static final String KEY_HOTEN_PRE_TO_MAIN = "KEY_HOTEN_PRE_TO_MAIN";
    public static final String KEY_USER_PRE_TO_MAIN = "KEY_USER_PRE_TO_MAIN";
    public static final String KEY_PASS_PRE_TO_MAIN = "KEY_PASS_PRE_TO_MAIN";

    TextView uSername;
    Button btn_to_main, btn_to_log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        anhxa();
        //
        btn_to_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vmain) {
                //startActivity(new Intent(PreviewActivity.this, MainActivity.class));
                StartMain();
                //finish();
            }
        });
        //
        btn_to_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vlogin) {
                startActivity(new Intent(PreviewActivity.this, AccountActivity.class));
                //finish();
            }
        });
        //
        String user_from_LOG = getIntent().getStringExtra(LoginActivity.KEY_USER_TO_PRE);
        //String pass_from_LOG = getIntent().getStringExtra(LoginActivity.KEY_PASS_TO_PRE);
        //
        uSername.setText(user_from_LOG);

    }

    private void StartMain(){

        String hoten = getIntent().getStringExtra(LoginActivity.KEY_HOTEN_TO_PRE);
        String user = getIntent().getStringExtra(LoginActivity.KEY_USER_TO_PRE);
        String pass = getIntent().getStringExtra(LoginActivity.KEY_PASS_TO_PRE);
        //
        Intent intent = new Intent(PreviewActivity.this, MainActivity.class);
        //
        intent.putExtra(KEY_HOTEN_PRE_TO_MAIN, hoten);
        intent.putExtra(KEY_USER_PRE_TO_MAIN, user);
        intent.putExtra(KEY_PASS_PRE_TO_MAIN, pass);
        //
        startActivity(intent);
        //finish();
    }

    private void anhxa(){
        uSername = findViewById(R.id.txt_pre_username);
        btn_to_main = findViewById(R.id.btn_pre_next);
        btn_to_log = findViewById(R.id.btn_pre_back);
    }
}
