package com.example.assignment.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment.Activity.UpdatepassActivity;
import com.example.assignment.Activity.LoginActivity;
import com.example.assignment.Activity.PrefManager;
import com.example.assignment.R;

public class ProfileFragment extends Fragment {

    TextView txt_update, txt_pass;
    EditText edt_hoTen, edt_uSer, edt_Pass;
    //ImageView image;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        //
        edt_hoTen = view.findViewById(R.id.profile_edt_hoten);
        edt_uSer = view.findViewById(R.id.profile_edt_taikhoan);
        edt_Pass = view.findViewById(R.id.profile_edt_matkhau);
        txt_update = view.findViewById(R.id.profile_txt_update);
        txt_pass = view.findViewById(R.id.txt_matkhau);
        //image = view.findViewById(R.id.profile_image);
        //
        String Hoten = this.getArguments().getString("KEY_HOTEN");
        String User = this.getArguments().getString("KEY_USER");
        String Pass = this.getArguments().getString("KEY_PASS");
        //
        edt_hoTen.setText(Hoten);
        edt_uSer.setText(User);
        txt_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_Pass.setText(Pass);
            }
        });
        //edt_Pass.setText(Pass);
        //
        return view;

    }

}
