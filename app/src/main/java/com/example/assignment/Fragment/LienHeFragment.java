package com.example.assignment.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.assignment.R;


public class LienHeFragment extends Fragment {
    ImageView imgfb,imgtwitter,imggoogle,imgzalo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lienhe, container, false);
        imgfb = view.findViewById(R.id.imgfacebook);
        imgtwitter = view.findViewById(R.id.imgtwiiter);
        imggoogle = view.findViewById(R.id.imggoogleplus);
        imgzalo = view.findViewById(R.id.imgzalo);
        imgfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.facebook.com/"));
                startActivity(intent);
            }
        });
        imgtwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://twitter.com/")));
            }
        });
        imggoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.google.com")));
            }
        });
        imgzalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://chat.zalo.me")));
            }
        });
        return view;
    }

}
