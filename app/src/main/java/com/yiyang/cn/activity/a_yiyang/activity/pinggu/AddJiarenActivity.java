package com.yiyang.cn.activity.a_yiyang.activity.pinggu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import org.jaaksi.pickerview.picker.TimePicker;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddJiarenActivity extends BaseActivity implements TakePhoto.TakeResultListener, InvokeListener {


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
    @BindView(R.id.tv_birthday)
    TextView tv_birthday;
    @BindView(R.id.ll_birthday)
    LinearLayout ll_birthday;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.ll_phone)
    LinearLayout ll_phone;
    @BindView(R.id.tv_guanxi)
    TextView tv_guanxi;
    @BindView(R.id.ll_guanxi)
    LinearLayout ll_guanxi;
    @BindView(R.id.tv_shengao)
    TextView tv_shengao;
    @BindView(R.id.ll_shengao)
    LinearLayout ll_shengao;
    @BindView(R.id.tv_tizhong)
    TextView tv_tizhong;
    @BindView(R.id.ll_tizhong)
    LinearLayout ll_tizhong;
    @BindView(R.id.tv_xuexing)
    TextView tv_xuexing;
    @BindView(R.id.ll_xuexing)
    LinearLayout ll_xuexing;
    @BindView(R.id.tv_wenhuachengdu)
    TextView tv_wenhuachengdu;
    @BindView(R.id.ll_wenhuachengdu)
    LinearLayout ll_wenhuachengdu;
    @BindView(R.id.tv_hunyinzhuangkuang)
    TextView tv_hunyinzhuangkuang;
    @BindView(R.id.ll_hunyinzhuangkuang)
    LinearLayout ll_hunyinzhuangkuang;
    @BindView(R.id.tv_zhiyeqingkuang)
    TextView tv_zhiyeqingkuang;
    @BindView(R.id.ll_zhiyeqingkuang)
    LinearLayout ll_zhiyeqingkuang;
    @BindView(R.id.tv_yanglaobaoxian)
    TextView tv_yanglaobaoxian;
    @BindView(R.id.ll_yanglaobaoxian)
    LinearLayout ll_yanglaobaoxian;
    @BindView(R.id.tv_yiliaobaoxian)
    TextView tv_yiliaobaoxian;
    @BindView(R.id.ll_yiliaobaoxian)
    LinearLayout ll_yiliaobaoxian;
    @BindView(R.id.tv_gaoxueya)
    TextView tv_gaoxueya;
    @BindView(R.id.ll_gaoxueya)
    LinearLayout ll_gaoxueya;
    @BindView(R.id.tv_xinzaobing)
    TextView tv_xinzaobing;
    @BindView(R.id.ll_xinzangbing)
    LinearLayout ll_xinzangbing;
    @BindView(R.id.tv_is_yinjiu)
    TextView tv_is_yinjiu;
    @BindView(R.id.ll_is_yinjiu)
    LinearLayout ll_is_yinjiu;
    @BindView(R.id.tv_is_xiya)
    TextView tv_is_xiya;
    @BindView(R.id.ll_is_xiya)
    LinearLayout ll_is_xiya;
    @BindView(R.id.tv_is_zili)
    TextView tv_is_zili;
    @BindView(R.id.ll_is_zili)
    LinearLayout ll_is_zili;
    @BindView(R.id.tv_xingdongnengli)
    TextView tv_xingdongnengli;
    @BindView(R.id.ll_xingdongnengli)
    LinearLayout ll_xingdongnengli;
    @BindView(R.id.tv_yuyannengli)
    TextView tv_yuyannengli;
    @BindView(R.id.ll_yuyannengli)
    LinearLayout ll_yuyannengli;
    @BindView(R.id.tv_is_chidai)
    TextView tv_is_chidai;
    @BindView(R.id.ll_is_chidai)
    LinearLayout ll_is_chidai;
    @BindView(R.id.tv_aihao)
    TextView tv_aihao;
    @BindView(R.id.ll_aihao)
    LinearLayout ll_aihao;
    @BindView(R.id.bt_save)
    TextView bt_save;

    private String headImgPath;
    private String name;
    private String sex;
    private String birthday;
    private String phone;
    private String guanxi;
    private String shengao;
    private String tizhong;
    private String xuexing;
    private String wenhuachengdu;
    private String hunyinzhuangkuang;
    private String zhiyeqingkuang;
    private String yanglaobaoxian;
    private String yiliaobaoxian;
    private String gaoxueya;
    private String xinzangbing;
    private String is_yinjiu;
    private String is_xiya;
    private String is_zili;
    private String xingdongnengli;
    private String yuyannengli;
    private String is_chidai;
    private String aihao;

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    private final static int INPUT_SEX = 0;//性别
    private String type;

    @Override
    public int getContentViewResId() {
        return R.layout.yiyang_act_add_jiaren;
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String type) {
        Intent intent = new Intent(context, AddJiarenActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
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
        type = getIntent().getStringExtra("type");
        if (type.equals("1")){
            tv_title.setText("添加家人");
            bt_save.setText("保存");
        }else {
            tv_title.setText("评估详情");
            bt_save.setText("评估");
        }
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


    @OnClick({R.id.ll_head, R.id.ll_name, R.id.ll_sex, R.id.ll_birthday, R.id.ll_phone, R.id.ll_guanxi, R.id.ll_shengao, R.id.ll_tizhong, R.id.ll_xuexing, R.id.ll_wenhuachengdu, R.id.ll_hunyinzhuangkuang, R.id.ll_zhiyeqingkuang, R.id.ll_yanglaobaoxian, R.id.ll_yiliaobaoxian, R.id.ll_gaoxueya, R.id.ll_xinzangbing, R.id.ll_is_yinjiu, R.id.ll_is_xiya, R.id.ll_is_zili, R.id.ll_xingdongnengli, R.id.ll_yuyannengli, R.id.ll_is_chidai, R.id.ll_aihao, R.id.bt_save})
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
            case R.id.ll_birthday:
                clickBirthday();
                break;
            case R.id.ll_phone:
                clickPhone();
                break;
            case R.id.ll_guanxi:
                clickGuanxi();
                break;
            case R.id.ll_shengao:
                clickShengao();
                break;
            case R.id.ll_tizhong:
                clickTizhong();
                break;
            case R.id.ll_xuexing:
                clickXuexing();
                break;
            case R.id.ll_wenhuachengdu:
                clickWenhuachengdu();
                break;
            case R.id.ll_hunyinzhuangkuang:
                ckhunyinzhuangkuang();
                break;
            case R.id.ll_zhiyeqingkuang:
                clickZhiyeqingkuang();
                break;
            case R.id.ll_yanglaobaoxian:
                clickYanglao();
                break;
            case R.id.ll_yiliaobaoxian:
                clickYiliao();
                break;
            case R.id.ll_gaoxueya:
                clickGaoxueya();
                break;
            case R.id.ll_xinzangbing:
                clickXinzangbing();
                break;
            case R.id.ll_is_yinjiu:
                cilckYinjiu();
                break;
            case R.id.ll_is_xiya:
                clickXiyan();
                break;
            case R.id.ll_is_zili:
                clickZili();
                break;
            case R.id.ll_xingdongnengli:
                clickXingdongnengli();
                break;
            case R.id.ll_yuyannengli:
                clickYuyannnegli();
                break;
            case R.id.ll_is_chidai:
                clickChidai();
                break;
            case R.id.ll_aihao:
                clickAihao();
                break;
            case R.id.bt_save:
                clickSave();
                break;
        }
    }

    private void clickChidai() {
        final List<String> item = new ArrayList<>();
        item.add("是");
        item.add("否");
        OptionsPickerView pvOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                is_chidai = item.get(options1);
                tv_is_chidai.setText(is_chidai);
            }
        }).build();
        pvOptions.setTitleText("是否患有老年痴呆");
        pvOptions.setPicker(item);
        pvOptions.show();
    }

    private void clickYuyannnegli() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                yuyannengli = dialog.getTextContent();
                tv_yuyannengli.setText(yuyannengli);
            }

            @Override
            public void onDismiss(InputDialog dialog) {
                Y.hideInputMethod(dialog.tv_content);
            }
        });

        dialog.setTextTitle("请输入语言能力");
        dialog.setTextContent(tv_yuyannengli.getText().toString());
        dialog.show();
    }

    private void clickXingdongnengli() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                xingdongnengli = dialog.getTextContent();
                tv_xingdongnengli.setText(xingdongnengli);
            }

            @Override
            public void onDismiss(InputDialog dialog) {
                Y.hideInputMethod(dialog.tv_content);
            }
        });

        dialog.setTextTitle("请输入行动能力");
        dialog.setTextContent(tv_xingdongnengli.getText().toString());
        dialog.show();
    }

    private void clickZili() {
        final List<String> item = new ArrayList<>();
        item.add("是");
        item.add("否");
        OptionsPickerView pvOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                is_zili = item.get(options1);
                tv_is_zili.setText(is_zili);
            }
        }).build();
        pvOptions.setTitleText("能否自理");
        pvOptions.setPicker(item);
        pvOptions.show();
    }

    private void clickXiyan() {
        final List<String> item = new ArrayList<>();
        item.add("是");
        item.add("否");
        OptionsPickerView pvOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                is_xiya = item.get(options1);
                tv_is_xiya.setText(is_xiya);
            }
        }).build();
        pvOptions.setTitleText("是否吸烟");
        pvOptions.setPicker(item);
        pvOptions.show();
    }

    private void cilckYinjiu() {
        final List<String> item = new ArrayList<>();
        item.add("是");
        item.add("否");
        OptionsPickerView pvOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                is_yinjiu = item.get(options1);
                tv_is_yinjiu.setText(is_yinjiu);
            }
        }).build();
        pvOptions.setTitleText("是否饮酒");
        pvOptions.setPicker(item);
        pvOptions.show();
    }

    private void clickXinzangbing() {
        final List<String> item = new ArrayList<>();
        item.add("是");
        item.add("否");
        OptionsPickerView pvOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                xinzangbing = item.get(options1);
                tv_xinzaobing.setText(xinzangbing);
            }
        }).build();
        pvOptions.setTitleText("是否具有心脏病或血管疾病");
        pvOptions.setPicker(item);
        pvOptions.show();
    }

    private void clickGaoxueya() {
        final List<String> item = new ArrayList<>();
        item.add("是");
        item.add("否");
        OptionsPickerView pvOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                gaoxueya = item.get(options1);
                tv_gaoxueya.setText(gaoxueya);
            }
        }).build();
        pvOptions.setTitleText("是否患有高血压或者服用过治疗高血压药物");
        pvOptions.setPicker(item);
        pvOptions.show();
    }

    private void clickYiliao() {
        final List<String> item = new ArrayList<>();
        item.add("是");
        item.add("否");
        OptionsPickerView pvOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                yiliaobaoxian = item.get(options1);
                tv_yiliaobaoxian.setText(yiliaobaoxian);
            }
        }).build();
        pvOptions.setTitleText("是否具有医疗保险");
        pvOptions.setPicker(item);
        pvOptions.show();
    }

    private void clickYanglao() {
        final List<String> item = new ArrayList<>();
        item.add("是");
        item.add("否");
        OptionsPickerView pvOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                yanglaobaoxian = item.get(options1);
                tv_yanglaobaoxian.setText(yanglaobaoxian);
            }
        }).build();
        pvOptions.setTitleText("是否具有养老保险");
        pvOptions.setPicker(item);
        pvOptions.show();
    }

    private void clickZhiyeqingkuang() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                zhiyeqingkuang = dialog.getTextContent();
                tv_zhiyeqingkuang.setText(zhiyeqingkuang);
            }

            @Override
            public void onDismiss(InputDialog dialog) {
                Y.hideInputMethod(dialog.tv_content);
            }
        });

        dialog.setTextTitle("请输入职业情况");
        dialog.setTextContent(tv_zhiyeqingkuang.getText().toString());
        dialog.show();
    }

    private void ckhunyinzhuangkuang() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                hunyinzhuangkuang = dialog.getTextContent();
                tv_hunyinzhuangkuang.setText(hunyinzhuangkuang);
            }

            @Override
            public void onDismiss(InputDialog dialog) {
                Y.hideInputMethod(dialog.tv_content);
            }
        });

        dialog.setTextTitle("请选择输入状况");
        dialog.setTextContent(tv_hunyinzhuangkuang.getText().toString());
        dialog.show();
    }

    private void clickWenhuachengdu() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                wenhuachengdu = dialog.getTextContent();
                tv_wenhuachengdu.setText(wenhuachengdu);
            }

            @Override
            public void onDismiss(InputDialog dialog) {
                Y.hideInputMethod(dialog.tv_content);
            }
        });

        dialog.setTextTitle("请输入文化程度");
        dialog.setTextContent(tv_wenhuachengdu.getText().toString());
        dialog.show();
    }

    private void clickXuexing() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                xuexing = dialog.getTextContent();
                tv_xuexing.setText(xuexing);
            }

            @Override
            public void onDismiss(InputDialog dialog) {
                Y.hideInputMethod(dialog.tv_content);
            }
        });

        dialog.setTextTitle("请输入血型");
        dialog.setTextContent(tv_xuexing.getText().toString());
        dialog.show();
    }

    private void clickGuanxi() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                guanxi = dialog.getTextContent();
                tv_guanxi.setText(guanxi);

            }

            @Override
            public void onDismiss(InputDialog dialog) {
                Y.hideInputMethod(dialog.tv_content);
            }
        });

        dialog.setTextTitle("请输入与测评人关系");
        dialog.setTextContent(tv_guanxi.getText().toString());
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
                Y.hideInputMethod(dialog.tv_content);
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
                Y.hideInputMethod(dialog.tv_content);
            }
        });

        dialog.setTextInput(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        dialog.setTextTitle("请输入身高(cm)");
        dialog.setTextContent(tv_shengao.getText().toString());
        dialog.show();
    }


    private void clickTizhong() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                tizhong = dialog.getTextContent();
                tv_tizhong.setText(tizhong);
            }

            @Override
            public void onDismiss(InputDialog dialog) {
                Y.hideInputMethod(dialog.tv_content);
            }
        });

        dialog.setTextInput(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        dialog.setTextTitle("请输入体重(kg)");
        dialog.setTextContent(tv_tizhong.getText().toString());
        dialog.show();
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

        dialog.setTextInput(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        dialog.setTextTitle("请输入手机号");
        dialog.setTextContent(tv_phone.getText().toString());
        dialog.show();
    }

    private void clickBirthday() {
        TimePicker timePicker = new TimePicker.Builder(this, TimePicker.TYPE_DATE, new TimePicker.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(TimePicker picker, Date date) {
                birthday = getTime(date);
                tv_birthday.setText(birthday);
            }
        }).setSelectedDate(System.currentTimeMillis()).create();
        timePicker.show();
    }

    @SuppressLint("SimpleDateFormat")
    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format;
        format = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = format.format(date);
        return strDate;
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


    private void clickSave() {
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

        if (TextUtils.isEmpty(birthday)) {
            Y.t("请选择出生日期");
            return;
        }

        if (TextUtils.isEmpty(phone)) {
            Y.t("请输入手机号");
            return;
        }

        if (TextUtils.isEmpty(guanxi)) {
            Y.t("请输入与测评人关系");
            return;
        }

        if (TextUtils.isEmpty(shengao)) {
            Y.t("请输入身高");
            return;
        }

        if (TextUtils.isEmpty(tizhong)) {
            Y.t("请输入体重");
            return;
        }


        JiashuModel model = new JiashuModel();
        model.setHeadImgPath(headImgPath);
        model.setName(name);
        model.setSex(sex);
        model.setBirthday(birthday);
        model.setPhone(phone);
        model.setGuanxi(guanxi);
        model.setShengao(shengao);
        model.setTizhong(tizhong);
        model.setXuexing(xuexing);
        model.setWenhuachengdu(wenhuachengdu);
        model.setHunyinzhuangkuang(hunyinzhuangkuang);
        model.setZhiyeqingkuang(zhiyeqingkuang);
        model.setYanglaobaoxian(yanglaobaoxian);
        model.setYiliaobaoxian(yiliaobaoxian);
        model.setGaoxueya(gaoxueya);
        model.setXinzangbing(xinzangbing);
        model.setIs_yinjiu(is_yinjiu);
        model.setIs_xiya(is_xiya);
        model.setIs_zili(is_zili);
        model.setXingdongnengli(xingdongnengli);
        model.setYuyannengli(yuyannengli);
        model.setIs_chidai(is_chidai);
        model.setAihao(aihao);
        if (type.equals("2")){
            model.setPinggu(true);
            Y.t("评估完成");
            PinggujieguoActivity.actionStart(mContext);
        }else {
            model.setPinggu(false);
            Y.t("添加家庭成员成功");
        }

        Notice notice = new Notice();
        notice.type = ConstanceValue.MSG_YIYANG_ADDJIAREN;
        notice.content = model;
        sendRx(notice);
        finish();
    }
}
