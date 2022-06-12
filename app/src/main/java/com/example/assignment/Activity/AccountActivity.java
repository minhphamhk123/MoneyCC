package com.example.assignment.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment.R;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountActivity extends AppCompatActivity {

    public static final String KEY_HOTEN_PRE_TO_MAIN = "KEY_HOTEN_PRE_TO_MAIN";
    public static final String KEY_USER_PRE_TO_MAIN = "KEY_USER_PRE_TO_MAIN";
    public static final String KEY_PASS_PRE_TO_MAIN = "KEY_PASS_PRE_TO_MAIN";

    @BindView(R.id.img_profile)
    ImageView imgProfile;

    private static final int REQUEST_IMAGE = 1;

    TextView hoTen;//, acc_tk, acc_mk;
    Button btn_next, btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);
        //
        anhxa();
        //
        loadProfileDefault();
        //
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View back_login) {
                startActivity(new Intent(AccountActivity.this, LoginActivity.class));
                finish();
            }
        });
        //
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View next_main) {
                startMain();
            }
        });
        //lay thong tin tu LoginActivity:
        String username = getIntent().getStringExtra(LoginActivity.KEY_HOTEN_TO_PRE);
        //String user_tk = getIntent().getStringExtra(LoginActivity.KEY_USER_TO_PRE);
        //String user_mk = getIntent().getStringExtra(LoginActivity.KEY_PASS_TO_PRE);
        //
        hoTen.setText(username);
        //acc_tk.setText(user_tk);
        //acc_mk.setText(user_mk);
        //
    }

    private void startMain(){
        String hoten = getIntent().getStringExtra(LoginActivity.KEY_HOTEN_TO_PRE);
        String user = getIntent().getStringExtra(LoginActivity.KEY_USER_TO_PRE);
        String pass = getIntent().getStringExtra(LoginActivity.KEY_PASS_TO_PRE);
        //
        Intent intent = new Intent(AccountActivity.this, MainActivity.class);
        //
        intent.putExtra(KEY_HOTEN_PRE_TO_MAIN, hoten);
        intent.putExtra(KEY_USER_PRE_TO_MAIN, user);
        intent.putExtra(KEY_PASS_PRE_TO_MAIN, pass);
        //
        startActivity(intent);
        finish();
    }

    private void anhxa(){
        hoTen = findViewById(R.id.acc_txt_hoten);
        //acc_tk = findViewById(R.id.acc_txt_username);
        //acc_mk = findViewById(R.id.acc_txt_password);
        btn_next = findViewById(R.id.btn_acc_next);
        btn_back = findViewById(R.id.btn_acc_back);

    }
    //


    public void loadProfileDefault() {
        Glide.with(this).load(R.drawable.tuan01)
                .into(imgProfile);
        //imgProfile.setColorFilter(ContextCompat.getColor(this, R.color.profile_default_tint));
    }

    @OnClick({R.id.img_plus, R.id.img_profile})
    public void onProfileImageClick() {
        showImagePickerOptions();
    }

    public void showImagePickerOptions() {
        UcropImage.showImagePickerOptions(this, new UcropImage.PickerOptionListener() {
            @Override
            public void onCameraSelected() {
                launchCamera();
            }

            @Override
            public void onGallerySelected() {
                launchGallery();
            }
        });
    }

    public void launchCamera() {
        Intent intent = new Intent(AccountActivity.this, UcropImage.class);
        intent.putExtra(UcropImage.REQUEST_CODE_TYPE, UcropImage.REQUEST_IMAGE_CAPTURE);

        // Gán tỉ lệ khóa là 1x1
        intent.putExtra(UcropImage.EXTRA_LOCK_ASPECT_RATIO, true);
        intent.putExtra(UcropImage.EXTRA_ASPECT_RATIO_X, 1);
        intent.putExtra(UcropImage.EXTRA_ASPECT_RATIO_Y, 1);

        startActivityForResult(intent, REQUEST_IMAGE);
    }

    public void launchGallery() {
        Intent intent = new Intent(AccountActivity.this, UcropImage.class);
        intent.putExtra(UcropImage.REQUEST_CODE_TYPE, UcropImage.REQUEST_IMAGE_GALLERY);

        // Gán kích thước tối đa cho ảnh
        intent.putExtra(UcropImage.EXTRA_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(UcropImage.EXTRA_BITMAP_MAX_WIDTH, 480);
        intent.putExtra(UcropImage.EXTRA_BITMAP_MAX_HEIGHT, 640);

        startActivityForResult(intent, REQUEST_IMAGE);
    }

    public void loadImageProfile(String url) {
        Glide.with(this).load(url)
                .into(imgProfile);
        imgProfile.setColorFilter(ContextCompat.getColor(this, android.R.color.transparent));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getParcelableExtra("path");
                loadImageProfile(uri.toString());
            }
        }
    }

}
