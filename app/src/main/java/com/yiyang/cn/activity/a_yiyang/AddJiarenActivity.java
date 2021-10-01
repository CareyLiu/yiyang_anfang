package com.yiyang.cn.activity.a_yiyang;

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
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.a_yiyang.model.JiashuModel;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.dialog.InputDialog;
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

public class AddJiarenActivity extends BaseActivity implements TakePhoto.TakeResultListener, InvokeListener {

    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv_id_card)
    TextView tv_id_card;
    @BindView(R.id.tv_age)
    TextView tv_age;
    @BindView(R.id.tv_peiou_name)
    TextView tv_peiou_name;
    @BindView(R.id.tv_shengao)
    TextView tv_shengao;
    @BindView(R.id.tv_aihao)
    TextView tv_aihao;
    @BindView(R.id.bt_save)
    TextView bt_save;
    @BindView(R.id.tv_phone)
    TextView tv_phone;

    private String imgPath;
    private String name;
    private String sex;
    private String idCard;
    private String age;
    private String peiouName;
    private String shengao;
    private String aihao;
    private String phone;


    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private OptionsPickerView pvOptions;
    private int index;

    @Override
    public int getContentViewResId() {
        return R.layout.yiyang_act_add_jiaren;
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, AddJiarenActivity.class);
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
        tv_title.setText("添加家人");
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

    @OnClick({R.id.tv_phone,R.id.iv_head, R.id.tv_name, R.id.tv_sex, R.id.tv_id_card, R.id.tv_age, R.id.tv_peiou_name, R.id.tv_shengao, R.id.tv_aihao, R.id.bt_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                clickHead();
                break;
            case R.id.tv_name:
                clickName();
                break;
            case R.id.tv_sex:
                clickSex();
                break;
            case R.id.tv_id_card:
                clickCard();
                break;
            case R.id.tv_age:
                clickAge();
                break;
            case R.id.tv_peiou_name:
                clickPeiouName();
                break;
            case R.id.tv_shengao:
                clickShengao();
                break;
            case R.id.tv_aihao:
                clickAihao();
                break;
            case R.id.bt_save:
                clickSave();
                break;
            case R.id.tv_phone:
                clickPhone();
                break;
        }
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

            }
        });

        dialog.setTextInput(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        dialog.setTextTitle("请输入手机号");
        dialog.setTextContent(tv_phone.getText().toString());
        dialog.show();
    }

    private void clickAihao() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                aihao = dialog.getTextContent();
                tv_aihao.setText(aihao);
            }

            @Override
            public void onDismiss(InputDialog dialog) {

            }
        });

        dialog.setTextTitle("请输入兴趣爱好");
        dialog.setTextContent(tv_aihao.getText().toString());
        dialog.show();
    }

    private void clickShengao() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                shengao = dialog.getTextContent();
                tv_shengao.setText(shengao);
            }

            @Override
            public void onDismiss(InputDialog dialog) {

            }
        });

        dialog.setTextInput(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        dialog.setTextTitle("请输入身高");
        dialog.setTextContent(tv_shengao.getText().toString());
        dialog.show();
    }

    private void clickPeiouName() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                peiouName = dialog.getTextContent();
                tv_peiou_name.setText(peiouName);
            }

            @Override
            public void onDismiss(InputDialog dialog) {

            }
        });

        dialog.setTextTitle("请输入配偶姓名");
        dialog.setTextContent(tv_peiou_name.getText().toString());
        dialog.show();
    }

    private void clickAge() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                age = dialog.getTextContent();
                tv_age.setText(age);
            }

            @Override
            public void onDismiss(InputDialog dialog) {

            }
        });

        dialog.setTextInput(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        dialog.setTextTitle("请输入年龄");
        dialog.setTextContent(tv_age.getText().toString());
        dialog.show();
    }

    private void clickCard() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                idCard = dialog.getTextContent();
                tv_id_card.setText(idCard);
            }

            @Override
            public void onDismiss(InputDialog dialog) {

            }
        });

        dialog.setTextInput(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        dialog.setTextTitle("请输入身份证号");
        dialog.setTextContent(tv_id_card.getText().toString());
        dialog.show();
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

            }
        });

        dialog.setTextTitle("请输入姓名");
        dialog.setTextContent(tv_name.getText().toString());
        dialog.show();
    }

    private void clickSex() {
        final List<String> item = new ArrayList<>();
        pvOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                sex = item.get(options1);
                tv_sex.setText(sex);
                index = options1;
            }
        }).build();
        item.clear();
        item.add("男");
        item.add("女");
        pvOptions.setTitleText("请选择性别");
        pvOptions.setPicker(item);
        pvOptions.setSelectOptions(index);
        pvOptions.show();
    }

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


    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    /**
     * 配置takerPhoto参数
     */
    public CropOptions getCropOptions() {
        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setAspectX(800).setAspectY(800);
        return builder.create();
    }

    @Override
    public void takeSuccess(TResult result) {
        File file = new File(result.getImage().getOriginalPath());
        imgPath = result.getImage().getOriginalPath();
        Glide.with(mContext).load(imgPath).apply(RequestOptions.circleCropTransform()).into(iv_head);
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


    private void clickSave() {
        if (TextUtils.isEmpty(imgPath)) {
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

        if (TextUtils.isEmpty(idCard)) {
            Y.t("请输入身份证号");
            return;
        }

        if (TextUtils.isEmpty(age)) {
            Y.t("请输入年龄");
            return;
        }

        if (TextUtils.isEmpty(peiouName)) {
            Y.t("请输入配偶姓名");
            return;
        }

        if (TextUtils.isEmpty(shengao)) {
            Y.t("请输入身高");
            return;
        }

        if (TextUtils.isEmpty(aihao)) {
            Y.t("请输入兴趣爱好");
            return;
        }

        Y.t("添加家庭成员成功");

        JiashuModel model = new JiashuModel();
        model.setImgPath(imgPath);
        model.setName(name);
        model.setSex(sex);
        model.setIdCard(idCard);
        model.setAge(age);
        model.setPeiouName(peiouName);
        model.setShengao(shengao);
        model.setAihao(aihao);

        Notice notice = new Notice();
        notice.type = ConstanceValue.MSG_YIYANG_ADDJIAREN;
        notice.content = model;
        sendRx(notice);
        finish();
    }
}
