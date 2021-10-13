package com.yiyang.cn.activity.a_yiyang.activity.jinlaoka;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.a_yiyang.activity.pinggu.PinggujieguoActivity;
import com.yiyang.cn.activity.a_yiyang.model.JiashuModel;
import com.yiyang.cn.activity.a_yiyang.model.JinglaokaModel;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.dialog.InputDialog;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.util.Y;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoImpl;
import org.devio.takephoto.model.CropOptions;
import org.devio.takephoto.model.InvokeParam;
import org.devio.takephoto.model.TContextWrap;
import org.devio.takephoto.model.TResult;
import org.devio.takephoto.permission.InvokeListener;
import org.devio.takephoto.permission.PermissionManager;
import org.devio.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JinglaokaShenqingActivity extends BaseActivity implements TakePhoto.TakeResultListener, InvokeListener {


    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.ll_head)
    RelativeLayout ll_head;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.ll_name)
    LinearLayout ll_name;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.ll_sex)
    LinearLayout ll_sex;
    @BindView(R.id.tv_card)
    TextView tv_card;
    @BindView(R.id.ll_card)
    LinearLayout ll_card;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.ll_phone)
    LinearLayout ll_phone;
    @BindView(R.id.iv_zhifubao)
    ImageView iv_zhifubao;
    @BindView(R.id.rl_zhifubao)
    RelativeLayout rl_zhifubao;
    @BindView(R.id.iv_weixin)
    ImageView iv_weixin;
    @BindView(R.id.rl_weixin)
    RelativeLayout rl_weixin;
    @BindView(R.id.bt_pay)
    TextView bt_pay;

    private String headImgPath;
    private String name;
    private String sex;
    private String card;
    private String phone;

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    @Override
    public int getContentViewResId() {
        return R.layout.yiyang_act_jinglaoka_shenqing;
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, JinglaokaShenqingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("敬老卡");
        tv_title.setTextSize(17);
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
        getTakePhoto().onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }


    @OnClick({R.id.ll_head, R.id.ll_name, R.id.ll_sex, R.id.ll_card, R.id.ll_phone, R.id.rl_zhifubao, R.id.rl_weixin, R.id.bt_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_head:
                clickHead();
                break;
            case R.id.ll_name:
                clickName();
                break;
            case R.id.ll_sex:
                clickSex();
                break;
            case R.id.ll_card:
                clickCard();
                break;
            case R.id.ll_phone:
                clickPhone();
                break;
            case R.id.rl_zhifubao:
                clickZhifubao();
                break;
            case R.id.rl_weixin:
                clickWeixin();
                break;
            case R.id.bt_pay:
                clickPay();
                break;
        }
    }

    private void clickWeixin() {
        iv_weixin.setImageResource(R.mipmap.tuya_faxian_icon_selector_sel_1);
        iv_zhifubao.setImageResource(R.mipmap.tuya_faxian_icon_selector_nor);
    }

    private void clickZhifubao() {
        iv_zhifubao.setImageResource(R.mipmap.tuya_faxian_icon_selector_sel_1);
        iv_weixin.setImageResource(R.mipmap.tuya_faxian_icon_selector_nor);
    }

    private void clickPhone() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                phone = dialog.getTextContent();
                tv_phone.setText(phone);
            }

            @Override
            public void onDismiss(InputDialog dialog) {
                Y.hideInputMethod(dialog.tv_content);
            }
        });

        dialog.setTextInput(InputType.TYPE_CLASS_NUMBER);
        dialog.setTextTitle("请输入手机号");
        dialog.setTextContent(tv_phone.getText().toString());
        dialog.show();
    }

    private void clickCard() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                card = dialog.getTextContent();
                tv_card.setText(card);

            }

            @Override
            public void onDismiss(InputDialog dialog) {
                Y.hideInputMethod(dialog.tv_content);
            }
        });

        dialog.setTextTitle("请输入身份证号");
        dialog.setTextContent(tv_card.getText().toString());
        dialog.show();
    }

    private void clickSex() {
        final List<String> item = new ArrayList<>();
        item.add("男");
        item.add("女");
        OptionsPickerView pvOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                sex = item.get(options1);
                tv_sex.setText(sex);
            }
        }).build();
        pvOptions.setTitleText("请选择性别");
        pvOptions.setPicker(item);
        pvOptions.show();
    }

    private void clickName() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                name = dialog.getTextContent();
                tv_name.setText(name);

            }

            @Override
            public void onDismiss(InputDialog dialog) {
                Y.hideInputMethod(dialog.tv_content);
            }
        });

        dialog.setTextTitle("请输入姓名");
        dialog.setTextContent(tv_name.getText().toString());
        dialog.show();
    }

    /**
     * 选择头像
     */
    private void clickHead() {
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
                        takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions());
                        break;
                    case 1:
                        takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions());
                        break;
                }
                dialog.dismiss();

            }
        });
    }

    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    public CropOptions getCropOptions() {
        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setAspectX(800).setAspectY(800);
        return builder.create();
    }

    @Override
    public void takeSuccess(TResult result) {
        File file = new File(result.getImage().getOriginalPath());
        headImgPath = result.getImage().getOriginalPath();
        Glide.with(mContext).load(headImgPath).apply(RequestOptions.circleCropTransform()).into(iv_head);
    }

    @Override
    public void takeFail(TResult result, String msg) {
        Y.t(msg);
    }

    @Override
    public void takeCancel() {
        Y.t("取消选择");
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }


    private void clickPay() {
        if (TextUtils.isEmpty(headImgPath)) {
            Y.t("请选择头像");
            return;
        }

        if (TextUtils.isEmpty(name)) {
            Y.t("请输入姓名");
            return;
        }

        if (TextUtils.isEmpty(sex)) {
            Y.t("请输入性别");
            return;
        }

        if (TextUtils.isEmpty(card)) {
            Y.t("请输入身份证号");
            return;
        }

        if (TextUtils.isEmpty(phone)) {
            Y.t("请输入手机号");
            return;
        }

        JinglaokaModel model = new JinglaokaModel();
        model.setHeadImgPath(headImgPath);
        model.setName(name);
        model.setSex(sex);
        model.setCard(card);
        model.setPhone(phone);
        model.setHaveCard("1");
        String jinglaokaJson = JSON.toJSONString(model);
        PreferenceHelper.getInstance(mContext).putString(AppConfig.YIYANG_YANGLAOKA, jinglaokaJson);
        TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_SUCESS, new TishiDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TishiDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TishiDialog dialog) {

            }

            @Override
            public void onDismiss(TishiDialog dialog) {
                Notice notice = new Notice();
                notice.type = ConstanceValue.MSG_YIYANG_JINGLAOKA;
                sendRx(notice);
                JinglaokaYouActivity.actionStart(mContext,model);
                finish();
            }
        });
        dialog.setTextContent("敬老卡申请成功!");
        dialog.show();
    }
}
