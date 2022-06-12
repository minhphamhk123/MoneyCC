package com.example.assignment.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.assignment.Fragment.CaiDatFragment;
import com.example.assignment.Fragment.ChiFragment;
import com.example.assignment.Fragment.GioiThieuFragment;
import com.example.assignment.Fragment.ThongKeFragment;
import com.example.assignment.Fragment.ThuFragment;
import com.example.assignment.Fragment.ProfileFragment;

import com.example.assignment.R;
import com.kinda.alert.KAlertDialog;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle barDrawerToggle;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        //
        barDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(barDrawerToggle);
        barDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setItemIconTintList(null);
        //
        Navigation();
    }

    private void anhXa() {
        navigationView = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerlayout);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (barDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Navigation() {

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.khoangthu:
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        //
                        ThuFragment thuFragment = new ThuFragment();
                        fragmentTransaction.replace(R.id.contentlayout, thuFragment);
                        //
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.khoangchi:
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        //
                        ChiFragment chiFragment = new ChiFragment();
                        fragmentTransaction.replace(R.id.contentlayout, chiFragment);
                        //
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.thongke:
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        //
                        ThongKeFragment thongKeFragment = new ThongKeFragment();
                        fragmentTransaction.replace(R.id.contentlayout, thongKeFragment);
                        //
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.caidat:
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        //
                        CaiDatFragment caiDatFragment = new CaiDatFragment();
                        fragmentTransaction.replace(R.id.contentlayout, caiDatFragment);
                        //
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.gioithieu:
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        //
                        GioiThieuFragment gioiThieuFragment = new GioiThieuFragment();
                        fragmentTransaction.replace(R.id.contentlayout, gioiThieuFragment);
                        //
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.thoat:
                        KAlertDialog kAlertDialog = new KAlertDialog(MainActivity.this,KAlertDialog.WARNING_TYPE);
                        kAlertDialog.setTitleText("Thông Báo?");
                        kAlertDialog.setContentText("Bạn có chắc muốn thoát ứng dụng này không?");
                        kAlertDialog.setCancelText("Không");
                        kAlertDialog.setConfirmText("Có");
                        kAlertDialog.showCancelButton(true);
                        kAlertDialog.setConfirmClickListener(new KAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(KAlertDialog kAlertDialog) {
                                System.exit(0);
                                finish();
                            }
                        });
                        kAlertDialog.setCancelClickListener(new KAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(KAlertDialog kAlertDialog) {
                                kAlertDialog.dismissWithAnimation();
                            }
                        });
                        kAlertDialog.show();
                        break;

                    case R.id.canhan:

                        String hoten = getIntent().getStringExtra(AccountActivity.KEY_HOTEN_PRE_TO_MAIN);
                        String user = getIntent().getStringExtra(AccountActivity.KEY_USER_PRE_TO_MAIN);
                        String pass = getIntent().getStringExtra(AccountActivity.KEY_PASS_PRE_TO_MAIN);
                        //
                        Bundle bundle = new Bundle();
                        bundle.putString("KEY_HOTEN", hoten);
                        bundle.putString("KEY_USER", user);
                        bundle.putString("KEY_PASS", pass);
                        //
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        //
                        ProfileFragment profileFragment = new ProfileFragment();
                        //
                        profileFragment.setArguments(bundle);
                        //
                        fragmentTransaction.replace(R.id.contentlayout, profileFragment);
                        //
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                }
                return false;
            }
        });
    }

}
