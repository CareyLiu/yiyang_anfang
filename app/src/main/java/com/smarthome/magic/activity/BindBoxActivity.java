package com.smarthome.magic.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;

import com.jaeger.library.StatusBarUtil;
import com.smarthome.magic.R;
import com.smarthome.magic.util.AppToast;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Sl on 2019/6/18.
 *
 */

public class BindBoxActivity extends BaseActivity implements View.OnClickListener,EasyPermissions.PermissionCallbacks {
    private RelativeLayout mRlBack;
    private RelativeLayout mRlScanAdd;
    private RelativeLayout mRlHandAdd;

    @Override
    public void onCreate(Bundle savedInstanceStateundle) {
        super.onCreate(savedInstanceStateundle);
        setContentView(R.layout.activity_bind_box);
        StatusBarUtil.setLightMode(this);
        initView();
    }

    private void initView() {
        mRlBack = (RelativeLayout) findViewById(R.id.rl_back);
        mRlBack.setOnClickListener(this);
        mRlScanAdd = (RelativeLayout) findViewById(R.id.rl_scan_add);
        mRlScanAdd.setOnClickListener(this);
        mRlHandAdd = (RelativeLayout) findViewById(R.id.rl_hand_add);
        mRlHandAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_scan_add:

                if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
                   // startActivity(new Intent(this, ScanActivity.class));
                } else {
                    EasyPermissions.requestPermissions(this, getString(R.string.xjqx), 0, Manifest.permission.CAMERA);
                }
                break;
            case R.id.rl_hand_add:
                startActivity(new Intent(this,HandAddActivity.class));
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 将结果转发到EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        //startActivity(new Intent(this, ScanActivity.class));
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        AppToast.makeShortToast(this, getString(R.string.get_error));
    }
}
