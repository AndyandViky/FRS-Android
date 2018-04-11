package com.example.yanglin.arcface.views;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.utils.systemBar.SystemBarUI;
import com.example.yanglin.arcface.views.dialog.BottomDialog;
import com.example.yanglin.arcface.views.dialog.CenterDialog;
import com.example.yanglin.arcface.views.fragment.MainFragment;
import com.example.yanglin.arcface.views.fragment.MyFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

public class MainActivity extends AppCompatActivity  implements CenterDialog.OnCenterItemClickListener,  EasyPermissions.PermissionCallbacks{
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_CODE = 0x100;
    private String[] perms = new String[]{Manifest.permission.CAMERA};
    @BindView(R.id.key)
    FloatingActionButton buttonKey;
    @BindView(R.id.footer_menu)
    BottomNavigationViewEx footerMenu;
    @BindView(R.id.title_text)
    TextView titleText;

    private CenterDialog centerDialog;

    MainFragment mainFragment = new MainFragment();
    MyFragment myFragment = new MyFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SystemBarUI.initSystemBar(this, R.color.actionTitle);

        EasyPermissions.requestPermissions(
                new PermissionRequest.Builder(this, REQUEST_CODE, perms)
                        .setRationale("权限回调")
                        .build());

        centerDialog = new CenterDialog(this, R.layout.password_dialog,
                new int[]{R.id.dialog_cancel_pwd, R.id.dialog_sure_pwd});
        centerDialog.setOnCenterItemClickListener(this);

        //添加一个fragment
        //获取管理类
        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, mainFragment)
                .add(R.id.fragment_container, myFragment)
                .hide(myFragment)
                .commit();

        footerMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.first_page:
                        titleText.setText("首页");
                        MainActivity.this.getSupportFragmentManager()
                                .beginTransaction()
                                .show(mainFragment)
                                .hide(myFragment)
                                .commit();
                        Toast.makeText(MainActivity.this, "first" , Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.my_page:
                        titleText.setText("我的信息");
                        MainActivity.this.getSupportFragmentManager()
                                .beginTransaction()
                                .show(myFragment)
                                .hide(mainFragment)
                                .commit();
                        Toast.makeText(MainActivity.this, "my" , Toast.LENGTH_SHORT).show();
                        break;
                }

                return true;
            }

        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
    @OnClick(R.id.key)
    void centerKeyClick() {
        centerDialog.show();
    }


    @Override
    public void OnCenterItemClick(CenterDialog dialog, View view) {
        switch (view.getId()) {
            case R.id.dialog_sure_pwd:
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "输入成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.dialog_cancel_pwd:
                dialog.dismiss();
                break;
            default:
                break;
        }
    }
}
