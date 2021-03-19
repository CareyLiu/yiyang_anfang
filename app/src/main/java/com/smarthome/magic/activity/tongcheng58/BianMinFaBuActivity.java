package com.smarthome.magic.activity.tongcheng58;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.flyco.roundview.RoundRelativeLayout;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.ShuRuInterView;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.dialog.TongYongShuRuDIalog;
import com.smarthome.magic.dialog.TongYongShuRuEditTextDIalog;

import java.io.File;

import butterknife.BindView;

public class BianMinFaBuActivity extends BaseActivity {
    @BindView(R.id.tv_tian_xie_biao_ti)
    TextView tvTianXieBiaoTi;
    @BindView(R.id.rl_fabubiaoti)
    RelativeLayout rlFabubiaoti;
    @BindView(R.id.tv_qingxuanze)
    TextView tvQingxuanze;
    @BindView(R.id.rl_fabulanmu)
    RelativeLayout rlFabulanmu;
    @BindView(R.id.rl_miaoshu_neirong)
    RelativeLayout rlMiaoshuNeirong;
    @BindView(R.id.tv_shuru_lianxiren)
    TextView tvShuruLianxiren;
    @BindView(R.id.rl_xiangxidizhi)
    RelativeLayout rlXiangxidizhi;
    @BindView(R.id.tv_xuanze)
    TextView tvXuanze;
    @BindView(R.id.iv_none_sel)
    ImageView ivNoneSel;
    TongYongShuRuDIalog shuRuDIalog;
    @BindView(R.id.tv_shurugonggao)
    TextView tvShurugonggao;
    @BindView(R.id.tv_xiangqingtu)
    TextView tvXiangqingtu;
    @BindView(R.id.rl_xiangqingtu_huashu)
    RelativeLayout rlXiangqingtuHuashu;

    String biaoTi = "";//标题
    String faBuLanMu = "";//发布栏目
    String miaoShuNeiRong = "";//描述内容
    String lianXiRen = "";//联系人
    String xiangXiDiZhi = "";//详细地址
    @BindView(R.id.tv_xiangxidizhi)
    TextView tvXiangxidizhi;
    @BindView(R.id.rl_xuanze)
    RelativeLayout rlXuanze;
    @BindView(R.id.tv_tongyi_huashu)
    TextView tvTongyiHuashu;
    @BindView(R.id.tv_fabuxuzhi)
    TextView tvFabuxuzhi;
    @BindView(R.id.rl_baocunxinxi)
    RoundRelativeLayout rlBaocunxinxi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rlFabubiaoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuRuDIalog = new TongYongShuRuDIalog(mContext, new ShuRuInterView() {
                    @Override
                    public void cannel() {
                        shuRuDIalog.dismiss();
                    }

                    @Override
                    public void submit(String str) {
                        tvTianXieBiaoTi.setText(str);
                        //   bianJiChangJingMingCheng(str);
                        biaoTi = str;
                    }
                }, "请输入标题");
                shuRuDIalog.show();
            }
        });

        rlFabulanmu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FaBuLanMuDialog faBuLanMuDialog = new FaBuLanMuDialog(BianMinFaBuActivity.this);
//                faBuLanMuDialog.show();
                faBuLanMu();
            }
        });


        ShuRuInterView shuRuInterView = new ShuRuInterView() {
            @Override
            public void cannel() {

            }

            @Override
            public void submit(String str) {
                tvShurugonggao.setText(str);
                miaoShuNeiRong = str;
            }
        };
        rlMiaoshuNeirong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TongYongShuRuEditTextDIalog tongYongShuRuEditTextDIalog = new TongYongShuRuEditTextDIalog(mContext, shuRuInterView, "请输入您要发布的信息");
                tongYongShuRuEditTextDIalog.show();
            }
        });


        tvShuruLianxiren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TongYongShuRuDIalog tongYongShuRuDIalog = new TongYongShuRuDIalog(mContext, new ShuRuInterView() {
                    @Override
                    public void cannel() {

                    }

                    @Override
                    public void submit(String str) {
                        tvShuruLianxiren.setText(str);
                    }
                }, "请输入联系人");
                tongYongShuRuDIalog.show();
            }
        });

        rlXiangxidizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TongYongShuRuDIalog tongYongShuRuDIalog = new TongYongShuRuDIalog(mContext, new ShuRuInterView() {
                    @Override
                    public void cannel() {

                    }

                    @Override
                    public void submit(String str) {
                        tvXiangxidizhi.setText(str);
                        xiangXiDiZhi = str;

                    }
                }, "详细地址");

            }
        });
        ivNoneSel.setBackgroundResource(R.mipmap.weixuanze);

        rlXuanze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ivNoneSel.setBackgroundResource(R.mipmap.yixuanze);
                if (xuanZe.equals("0")) {
                    xuanZe = "1";
                    ivNoneSel.setBackgroundResource(R.mipmap.yixuanze);
                } else if (xuanZe.equals("1")) {
                    ivNoneSel.setBackgroundResource(R.mipmap.weixuanze);
                    xuanZe = "0";
                }
            }
        });
        rlBaocunxinxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (xuanZe.equals("0")) {
                    UIHelper.ToastMessage(mContext, "请您先选择阅读再进行保存信息操作");
                } else if (xuanZe.equals("1")) {
                    //UIHelper.ToastMessage(mContext, "");
                }
            }
        });
    }

    private String xuanZe = "0";//0为选择 1选择

    private void faBuLanMu() {
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

                faBuLanMu = items[position];
                dialog.dismiss();

            }
        });

        rlBaocunxinxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage(mContext, "保存信息");
            }
        });
    }


    @Override
    public int getContentViewResId() {
        return R.layout.layout_bianmin_fabu;
    }

    @Override
    public boolean showToolBarLine() {
        return true;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("便民发布");
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


    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, BianMinFaBuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
