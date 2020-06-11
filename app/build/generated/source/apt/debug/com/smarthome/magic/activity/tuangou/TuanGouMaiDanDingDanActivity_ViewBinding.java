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

public class TuanGouMaiDanDingDanActivity_ViewBinding implements Unbinder {
  private TuanGouMaiDanDingDanActivity target;

  @UiThread
  public TuanGouMaiDanDingDanActivity_ViewBinding(TuanGouMaiDanDingDanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TuanGouMaiDanDingDanActivity_ViewBinding(TuanGouMaiDanDingDanActivity target,
      View source) {
    this.target = target;

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
    target.ivChoose = Utils.findRequiredViewAsType(source, R.id.iv_choose, "field 'ivChoose'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TuanGouMaiDanDingDanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

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
    target.ivChoose = null;
  }
}
