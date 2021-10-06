package com.yiyang.cn.activity.a_yiyang.activity.pinggu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.a_yiyang.model.JiashuModel;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.dialog.newdia.TishiDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JiashuDanganDetailsActivity extends BaseActivity {


    @BindView(R.id.tv_shenfen)
    TextView tv_shenfen;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv_birthday)
    TextView tv_birthday;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_shengao)
    TextView tv_shengao;
    @BindView(R.id.tv_tizhong)
    TextView tv_tizhong;
    @BindView(R.id.tv_xuexing)
    TextView tv_xuexing;
    @BindView(R.id.tv_wenhuachengdu)
    TextView tv_wenhuachengdu;
    @BindView(R.id.tv_hunyinzhuangkuang)
    TextView tv_hunyinzhuangkuang;
    @BindView(R.id.tv_zhiyeqingkuang)
    TextView tv_zhiyeqingkuang;
    @BindView(R.id.tv_yanglaobaoxian)
    TextView tv_yanglaobaoxian;
    @BindView(R.id.tv_yiliaobaoxian)
    TextView tv_yiliaobaoxian;
    @BindView(R.id.tv_gaoxueya)
    TextView tv_gaoxueya;
    @BindView(R.id.tv_xinzangbing)
    TextView tv_xinzangbing;
    @BindView(R.id.tv_is_yinjiu)
    TextView tv_is_yinjiu;
    @BindView(R.id.tv_is_xiya)
    TextView tv_is_xiya;
    @BindView(R.id.tv_is_zili)
    TextView tv_is_zili;
    @BindView(R.id.tv_xingdongnengli)
    TextView tv_xingdongnengli;
    @BindView(R.id.tv_yuyannengli)
    TextView tv_yuyannengli;
    @BindView(R.id.tv_is_chidai)
    TextView tv_is_chidai;
    @BindView(R.id.tv_aihao)
    TextView tv_aihao;
    @BindView(R.id.iv_head)
    ImageView iv_head;

    private JiashuModel model;
    private int pos;

    @Override
    public int getContentViewResId() {
        return R.layout.yiyang_act_jiashudangan_details;
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, JiashuModel model, int pos) {
        Intent intent = new Intent(context, JiashuDanganDetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("jiashuModel", model);
        intent.putExtra("pos", pos);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("档案详情");
        tv_title.setTextSize(17);
        tv_rightTitle.setVisibility(View.VISIBLE);
        tv_rightTitle.setText("删除档案");
        tv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delectDangan();
            }
        });
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        model = (JiashuModel) getIntent().getSerializableExtra("jiashuModel");
        pos = getIntent().getIntExtra("pos", 0);
        initData();
    }

    private void initData() {
        Glide.with(mContext).load(model.getHeadImgPath()).apply(RequestOptions.circleCropTransform()).into(iv_head);

        tv_shenfen.setText(model.getGuanxi());
        tv_name.setText(model.getName());
        tv_sex.setText(model.getSex());
        tv_birthday.setText(model.getBirthday());
        tv_phone.setText(model.getPhone());
        tv_shengao.setText(model.getShengao()+"cm");
        tv_tizhong.setText(model.getTizhong()+"kg");
        tv_xuexing.setText(model.getXuexing());
        tv_wenhuachengdu.setText(model.getWenhuachengdu());
        tv_hunyinzhuangkuang.setText(model.getHunyinzhuangkuang());
        tv_zhiyeqingkuang.setText(model.getZhiyeqingkuang());
        tv_yanglaobaoxian.setText(model.getYanglaobaoxian());
        tv_yiliaobaoxian.setText(model.getYiliaobaoxian());
        tv_gaoxueya.setText(model.getGaoxueya());
        tv_xinzangbing.setText(model.getXinzangbing());
        tv_is_yinjiu.setText(model.getIs_yinjiu());
        tv_is_xiya.setText(model.getIs_xiya());
        tv_is_zili.setText(model.getIs_zili());
        tv_xingdongnengli.setText(model.getXingdongnengli());
        tv_yuyannengli.setText(model.getYuyannengli());
        tv_is_chidai.setText(model.getIs_chidai());
        tv_aihao.setText(model.getAihao());
    }

    private void delectDangan() {
        TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_DELETE, new TishiDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TishiDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TishiDialog dialog) {
                Notice notice = new Notice();
                notice.type = ConstanceValue.MSG_YIYANG_DELETEJIAREN;
                notice.content = pos;
                sendRx(notice);
                finish();
            }

            @Override
            public void onDismiss(TishiDialog dialog) {

            }
        });
        dialog.setTextContent("是否删除该档案!");
        dialog.show();
    }
}
