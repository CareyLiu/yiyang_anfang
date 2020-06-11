// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.tuangou;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.flyco.roundview.RoundRelativeLayout;
import com.flyco.roundview.RoundTextView;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TuanGouShengChengDingDanActivity_ViewBinding implements Unbinder {
  private TuanGouShengChengDingDanActivity target;

  @UiThread
  public TuanGouShengChengDingDanActivity_ViewBinding(TuanGouShengChengDingDanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TuanGouShengChengDingDanActivity_ViewBinding(TuanGouShengChengDingDanActivity target,
      View source) {
    this.target = target;

    target.ivImage = Utils.findRequiredViewAsType(source, R.id.iv_image, "field 'ivImage'", ImageView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvXianshitui = Utils.findRequiredViewAsType(source, R.id.tv_xianshitui, "field 'tvXianshitui'", TextView.class);
    target.viewLine = Utils.findRequiredView(source, R.id.view_line, "field 'viewLine'");
    target.tvNumber = Utils.findRequiredViewAsType(source, R.id.tv_number, "field 'tvNumber'", TextView.class);
    target.ivJia = Utils.findRequiredViewAsType(source, R.id.iv_jia, "field 'ivJia'", ImageView.class);
    target.tvGeshu = Utils.findRequiredViewAsType(source, R.id.tv_geshu, "field 'tvGeshu'", TextView.class);
    target.ivJian = Utils.findRequiredViewAsType(source, R.id.iv_jian, "field 'ivJian'", ImageView.class);
    target.tvXiaoji = Utils.findRequiredViewAsType(source, R.id.tv_xiaoji, "field 'tvXiaoji'", TextView.class);
    target.rl1 = Utils.findRequiredViewAsType(source, R.id.rl_1, "field 'rl1'", RoundRelativeLayout.class);
    target.tvZanwu = Utils.findRequiredViewAsType(source, R.id.tv_zanwu, "field 'tvZanwu'", TextView.class);
    target.rl2 = Utils.findRequiredViewAsType(source, R.id.rl_2, "field 'rl2'", RoundRelativeLayout.class);
    target.tvHongbaodikouHuashu = Utils.findRequiredViewAsType(source, R.id.tv_hongbaodikou_huashu, "field 'tvHongbaodikouHuashu'", TextView.class);
    target.viewLine1 = Utils.findRequiredView(source, R.id.view_line_1, "field 'viewLine1'");
    target.tvDanqianDikou = Utils.findRequiredViewAsType(source, R.id.tv_danqian_dikou, "field 'tvDanqianDikou'", TextView.class);
    target.tvDikoujine = Utils.findRequiredViewAsType(source, R.id.tv_dikoujine, "field 'tvDikoujine'", TextView.class);
    target.rl3 = Utils.findRequiredViewAsType(source, R.id.rl_3, "field 'rl3'", RoundRelativeLayout.class);
    target.tvShifujine = Utils.findRequiredViewAsType(source, R.id.tv_shifujine, "field 'tvShifujine'", TextView.class);
    target.tvMoney = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tvMoney'", TextView.class);
    target.viewLine2 = Utils.findRequiredView(source, R.id.view_line_2, "field 'viewLine2'");
    target.tvShoujihao = Utils.findRequiredViewAsType(source, R.id.tv_shoujihao, "field 'tvShoujihao'", TextView.class);
    target.tvShoujihaoNumber = Utils.findRequiredViewAsType(source, R.id.tv_shoujihao_number, "field 'tvShoujihaoNumber'", TextView.class);
    target.rl4 = Utils.findRequiredViewAsType(source, R.id.rl_4, "field 'rl4'", RoundRelativeLayout.class);
    target.rtvJine = Utils.findRequiredViewAsType(source, R.id.rtv_jine, "field 'rtvJine'", RoundTextView.class);
    target.tvJine = Utils.findRequiredViewAsType(source, R.id.tv_jine, "field 'tvJine'", TextView.class);
    target.tvXiaojiPrice = Utils.findRequiredViewAsType(source, R.id.tv_xiaoji_price, "field 'tvXiaojiPrice'", TextView.class);
    target.ivChoose = Utils.findRequiredViewAsType(source, R.id.iv_choose, "field 'ivChoose'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TuanGouShengChengDingDanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivImage = null;
    target.tvName = null;
    target.tvXianshitui = null;
    target.viewLine = null;
    target.tvNumber = null;
    target.ivJia = null;
    target.tvGeshu = null;
    target.ivJian = null;
    target.tvXiaoji = null;
    target.rl1 = null;
    target.tvZanwu = null;
    target.rl2 = null;
    target.tvHongbaodikouHuashu = null;
    target.viewLine1 = null;
    target.tvDanqianDikou = null;
    target.tvDikoujine = null;
    target.rl3 = null;
    target.tvShifujine = null;
    target.tvMoney = null;
    target.viewLine2 = null;
    target.tvShoujihao = null;
    target.tvShoujihaoNumber = null;
    target.rl4 = null;
    target.rtvJine = null;
    target.tvJine = null;
    target.tvXiaojiPrice = null;
    target.ivChoose = null;
  }
}
