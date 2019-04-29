package com.example.yanglin.arcface.views;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.controllers.UserCtrl;
import com.example.yanglin.arcface.models.BaseResponse;
import com.example.yanglin.arcface.utils.FingerUtil;
import com.example.yanglin.arcface.utils.LoaddingDialog;
import com.example.yanglin.arcface.utils.OkhttpService;
import com.example.yanglin.arcface.utils.systemBar.SystemBarUI;
import com.example.yanglin.arcface.views.dialog.BottomDialog;
import com.example.yanglin.arcface.views.dialog.CenterDialog;
import com.example.yanglin.arcface.views.fragment.MainFragment;
import com.example.yanglin.arcface.views.fragment.MyFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

public class MainActivity extends AppCompatActivity  implements CenterDialog.OnCenterItemClickListener,  EasyPermissions.PermissionCallbacks {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_CODE = 0x100;
    private String[] perms = new String[]{Manifest.permission.CAMERA};
    @BindView(R.id.key)
    FloatingActionButton buttonKey;
    @BindView(R.id.footer_menu)
    BottomNavigationViewEx footerMenu;
    @BindView(R.id.title_text)
    TextView titleText;

    EditText passwordD;

    private CenterDialog centerDialog;

    MainFragment mainFragment = new MainFragment();
    MyFragment myFragment = new MyFragment();

    OkHttpClient okHttpClient = new OkHttpClient();
    UserCtrl userCtrl = new UserCtrl();
    FingerprintManagerCompat.AuthenticationCallback mFingerListen;
    FingerUtil mFingerUtil;
    private Dialog Loadding;

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
        centerDialog.setView(new EditText(getApplicationContext()));

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
                switch (item.getItemId()) {
                    case R.id.first_page:
                        titleText.setText("首页");
                        MainActivity.this.getSupportFragmentManager()
                                .beginTransaction()
                                .show(mainFragment)
                                .hide(myFragment)
                                .commit();
                        break;
                    case R.id.my_page:
                        titleText.setText("我的信息");
                        MainActivity.this.getSupportFragmentManager()
                                .beginTransaction()
                                .show(myFragment)
                                .hide(mainFragment)
                                .commit();
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
        if(checkFingerModule()) {
            Toast.makeText(MainActivity.this, "请按压指纹", Toast.LENGTH_SHORT).show();
            // 实例化指纹监听
            mFingerListen = new FingerprintManagerCompat.AuthenticationCallback() {
                /**
                 * 指纹识别成功
                 */
                @Override
                public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                    mFingerUtil.stopsFingerListen();
                    openDoor("-1");
                }

                /**
                 * 识别失败
                 */
                @Override
                public void onAuthenticationFailed() {
                }

                /**
                 * 多次指纹密码验证错误后，进入此方法；并且，不能短时间内调用指纹验证
                 *
                 * @param errMsgId  错误码
                 * @param errString 剩余禁用时间
                 */
                @Override
                public void onAuthenticationError(int errMsgId, CharSequence errString) {
                    switch (errMsgId) {
                        case 5:      // 可以进行识别
                            Toast.makeText(MainActivity.this, "可以进行识别", Toast.LENGTH_SHORT).show();
                            break;
                        case 7:      // 失败次数过多，禁用倒计时未结束
                            stopsFingerListen();
                            centerDialog.show();
                            Toast.makeText(MainActivity.this, "失败次数过多，请输入密码", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            };
            // 实例化工具类
            mFingerUtil = new FingerUtil(this);
            // 使用指纹监听监听
            mFingerUtil.startFingerListen(mFingerListen);
        }
        else centerDialog.show();
    }

    @Override
    public void OnCenterItemClick(CenterDialog dialog, View view) {

        switch (view.getId()) {
            case R.id.dialog_sure_pwd:
                passwordD = centerDialog.findViewById(R.id.password_dialog_edit);
                String pwd = passwordD.getText().toString();
                if (pwd.isEmpty()) {
                    Toast.makeText(MainActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                dialog.dismiss();
                openDoor(pwd);
                break;
            case R.id.dialog_cancel_pwd:
                dialog.dismiss();
                break;
            default:
                break;
        }
    }

    void stopsFingerListen() {
        if (mFingerUtil != null) {
            mFingerUtil.stopsFingerListen();
        }
    }

    private boolean checkFingerModule() {
        try {
            FingerprintManager fingerManager = (FingerprintManager) getSystemService(Context.FINGERPRINT_SERVICE);
            return fingerManager.isHardwareDetected();
        } catch (Exception e) {
            return false;
        }
    }

    void openDoor(String pwd) {
        Loadding = LoaddingDialog.createLoadingDialog(MainActivity.this, "加载中...");
        userCtrl.openDoor(okHttpClient, "{\"selfPwd\": \"" + pwd + "\"}", new OkhttpService.OnResponseListener() {
            @Override
            public void onSuccess(String result) {
                LoaddingDialog.closeDialog(Loadding);
                java.lang.reflect.Type type = new TypeToken<BaseResponse>() {
                }.getType();
                final BaseResponse baseResponse = new Gson().fromJson(result, type);
                if (baseResponse.getCode() != 1) {
                    (MainActivity.this).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, baseResponse.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    return;
                }
                (MainActivity.this).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "开门成功", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onFailure(IOException error) {
                LoaddingDialog.closeDialog(Loadding);
                (MainActivity.this).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "开门失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
