package com.smarthome.magic.activity.tuya_camera.camera;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;


import com.smarthome.magic.R;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.activity.tuya_camera.camera.adapter.TuyaXiangceAdapter;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.util.phoneview.sample.ImageShowActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CameraXiangceActivity extends BaseActivity {

    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    private List<String> filesAllName = new ArrayList<>();
    private TuyaXiangceAdapter adapter;

    private boolean isXuanze;

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, CameraXiangceActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_camera_xiangce;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("相册");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));

        tv_rightTitle.setText("选择");
        tv_rightTitle.setTextSize(17);
        tv_rightTitle.setTextColor(getResources().getColor(R.color.black));
        tv_rightTitle.setVisibility(View.VISIBLE);

        tv_leftTitle.setText("取消");
        tv_leftTitle.setTextSize(17);
        tv_leftTitle.setTextColor(getResources().getColor(R.color.black));

        mToolbar.setNavigationIcon(R.mipmap.back_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_leftTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLeft();
            }
        });

        tv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRight();
            }
        });
    }

    private void clickRight() {
        if (isXuanze) {

        } else {
            isXuanze = true;
//            mToolbar.setVisibility(View.GONE);



            getSupportActionBar().setDisplayHomeAsUpEnabled(false);

            tv_leftTitle.setVisibility(View.VISIBLE);
            tv_rightTitle.setText("全选");
        }
    }

    private void clickLeft() {
        isXuanze = false;
//        mToolbar.setVisibility(View.VISIBLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv_leftTitle.setVisibility(View.GONE);
        tv_rightTitle.setText("选择");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        isXuanze = false;
        initAdapter();
    }

    private void initAdapter() {
        String picPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Camera/";
        filesAllName = getFilesAllName(picPath);
        adapter = new TuyaXiangceAdapter(R.layout.item_device_camera_xiangce, filesAllName);
        rv_content.setLayoutManager(new GridLayoutManager(mContext, 4));
        rv_content.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String fileName = filesAllName.get(position);
                if (fileName.contains(".png")) {
                    ArrayList<String> imgs = new ArrayList<>();
                    imgs.add(fileName);
                    ImageShowActivity.actionStart(mContext, imgs);
                } else {
                    Y.t("是录像");
                }
            }
        });
        for (int i = 0; i < filesAllName.size(); i++) {
            Y.e("相册地址是 " + filesAllName.get(i));
        }
    }

    public List<String> getFilesAllName(String path) {
        //传入指定文件夹的路径
        File file = new File(path);
        File[] files = file.listFiles();
        List<String> imagePaths = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            String path1 = files[i].getPath();
            if (path1.contains(".png") || path1.contains(".mp4")) {
                imagePaths.add(path1);
            }
        }
        return imagePaths;

    }
}
