package com.smarthome.magic.activity.tongcheng58;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.flyco.roundview.RoundRelativeLayout;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.ShuRuInterView;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.dialog.TongYongShuRuDIalog;

import java.io.File;

import butterknife.BindView;

public class GongJiangRuZhuActivity extends BaseActivity {
    @BindView(R.id.tv_gongjiang_huashu)
    TextView tvGongjiangHuashu;
    @BindView(R.id.rl_gongjiang_touxiang)
    RelativeLayout rlGongjiangTouxiang;
    @BindView(R.id.rl_gongjiang_xingming)
    RelativeLayout rlGongjiangXingming;
    @BindView(R.id.rl_fuwugongzhong)
    RelativeLayout rlFuwugongzhong;
    @BindView(R.id.ll_tianjia)
    LinearLayout llTianjia;
    @BindView(R.id.iv_shenfenzheng_zhengmian)
    ImageView ivShenfenzhengZhengmian;
    @BindView(R.id.iv_shenfengzheng_fanmian)
    ImageView ivShenfengzhengFanmian;
    @BindView(R.id.rl_tianxie_jianjie)
    RelativeLayout rlTianxieJianjie;
    @BindView(R.id.rl_fuwu_quyu)
    RelativeLayout rlFuwuQuyu;
    @BindView(R.id.rl_weixin)
    RelativeLayout rlWeixin;
    @BindView(R.id.ll_yuedu)
    LinearLayout llYuedu;
    @BindView(R.id.rrl_tijiao)
    RoundRelativeLayout rrlTijiao;
    @BindView(R.id.tv_gongjiang_xingming)
    TextView tvGongjiangXingming;
    @BindView(R.id.ll_fuwugongzhong)
    LinearLayout llFuwugongzhong;
    @BindView(R.id.tv_geren_jianjie)
    TextView tvGerenJianjie;
    @BindView(R.id.tv_fuwu_gongzhong)
    TextView tvFuwuGongzhong;
    @BindView(R.id.iv_yuedu)
    ImageView ivYuedu;

    private String xuanZe = "0";//0未选择 1已选择

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rlGongjiangTouxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rlGongjiangXingming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TongYongShuRuDIalog tongYongShuRuDIalog = new TongYongShuRuDIalog(mContext, new ShuRuInterView() {
                    @Override
                    public void cannel() {

                    }

                    @Override
                    public void submit(String str) {
                        tvGongjiangXingming.setText(str);

                    }
                }, "请输入姓名");
                tongYongShuRuDIalog.show();
            }
        });

        llFuwugongzhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseFuWuGongZhongActivity.actionStart(mContext);
                //gongJiangGongZhong();
            }
        });

        llTianjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ivShenfenzhengZhengmian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ivShenfengzhengFanmian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rlTianxieJianjie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TongYongShuRuDIalog tongYongShuRuDIalog = new TongYongShuRuDIalog(mContext, new ShuRuInterView() {
                    @Override
                    public void cannel() {

                    }

                    @Override
                    public void submit(String str) {
                        tvGerenJianjie.setText(str);
                    }
                }, "请填写简介");
                tongYongShuRuDIalog.show();
            }
        });

        llYuedu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (xuanZe1.equals("0")) {
                    ivYuedu.setBackgroundResource(R.mipmap.yixuanze);
                    xuanZe1 = "1";
                } else if (xuanZe1.equals("1")) {
                    ivYuedu.setBackgroundResource(R.mipmap.weixuanze);
                    xuanZe1 = "0";
                }

            }
        });
        ivYuedu.setBackgroundResource(R.mipmap.weixuanze);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_gongjiang_ruzhu;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    public boolean showToolBarLine() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("工匠入驻");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
    }

    private String xuanZe1 = "0";//0为选择 1选择
    private String gongZhong = "";

    private void gongJiangGongZhong() {
        String[] items = {"拍照", "相册"};
        final ActionSheetDialog dialog = new ActionSheetDialog(this, items, null);
        dialog.isTitleShow(false).show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                Uri imageUri = Uri.fromFile(file);
                switch (position) {
                    case 0:


                        break;
                    case 1:

                        break;


                }

                gongZhong = items[position];
                tvFuwuGongzhong.setText(gongZhong);
                dialog.dismiss();

            }
        });

    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, GongJiangRuZhuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
