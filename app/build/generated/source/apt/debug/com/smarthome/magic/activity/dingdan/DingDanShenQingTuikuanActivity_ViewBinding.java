// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.dingdan;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.flyco.roundview.RoundRelativeLayout;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DingDanShenQingTuikuanActivity_ViewBinding implements Unbinder {
  private DingDanShenQingTuikuanActivity target;

  @UiThread
  public DingDanShenQingTuikuanActivity_ViewBinding(DingDanShenQingTuikuanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DingDanShenQingTuikuanActivity_ViewBinding(DingDanShenQingTuikuanActivity target,
      View source) {
    this.target = target;

    target.tvShenqingtuikuanHuashu = Utils.findRequiredViewAsType(source, R.id.tv_shenqingtuikuan_huashu, "field 'tvShenqingtuikuanHuashu'", TextView.class);
    target.tvShouhuozhuagntaiHuashu = Utils.findRequiredViewAsType(source, R.id.tv_shouhuozhuagntai_huashu, "field 'tvShouhuozhuagntaiHuashu'", TextView.class);
    target.tvShenqingyuanyinHuashu = Utils.findRequiredViewAsType(source, R.id.tv_shenqingyuanyin_huashu, "field 'tvShenqingyuanyinHuashu'", TextView.class);
    target.tvTuikuanjine = Utils.findRequiredViewAsType(source, R.id.tv_tuikuanjine, "field 'tvTuikuanjine'", TextView.class);
    target.tvJine = Utils.findRequiredViewAsType(source, R.id.tv_jine, "field 'tvJine'", TextView.class);
    target.tvShuoming = Utils.findRequiredViewAsType(source, R.id.tv_shuoming, "field 'tvShuoming'", TextView.class);
    target.tvPhoneHuashu = Utils.findRequiredViewAsType(source, R.id.tv_phone_huashu, "field 'tvPhoneHuashu'", TextView.class);
    target.etPhone = Utils.findRequiredViewAsType(source, R.id.et_phone, "field 'etPhone'", EditText.class);
    target.rrlTijiaoshenqing = Utils.findRequiredViewAsType(source, R.id.rrl_tijiaoshenqing, "field 'rrlTijiaoshenqing'", RoundRelativeLayout.class);
    target.tvShenqingtuikuan = Utils.findRequiredViewAsType(source, R.id.tv_shenqingtuikuan, "field 'tvShenqingtuikuan'", TextView.class);
    target.etContent = Utils.findRequiredViewAsType(source, R.id.et_content, "field 'etContent'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DingDanShenQingTuikuanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvShenqingtuikuanHuashu = null;
    target.tvShouhuozhuagntaiHuashu = null;
    target.tvShenqingyuanyinHuashu = null;
    target.tvTuikuanjine = null;
    target.tvJine = null;
    target.tvShuoming = null;
    target.tvPhoneHuashu = null;
    target.etPhone = null;
    target.rrlTijiaoshenqing = null;
    target.tvShenqingtuikuan = null;
    target.etContent = null;
  }
}
