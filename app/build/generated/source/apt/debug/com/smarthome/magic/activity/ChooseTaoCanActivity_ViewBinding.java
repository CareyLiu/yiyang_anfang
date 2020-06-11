// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.flyco.roundview.RoundTextView;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChooseTaoCanActivity_ViewBinding implements Unbinder {
  private ChooseTaoCanActivity target;

  @UiThread
  public ChooseTaoCanActivity_ViewBinding(ChooseTaoCanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChooseTaoCanActivity_ViewBinding(ChooseTaoCanActivity target, View source) {
    this.target = target;

    target.ivImage = Utils.findRequiredViewAsType(source, R.id.iv_image, "field 'ivImage'", ImageView.class);
    target.ivClose = Utils.findRequiredViewAsType(source, R.id.iv_close, "field 'ivClose'", ImageView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", TextView.class);
    target.viewLine = Utils.findRequiredView(source, R.id.view_line, "field 'viewLine'");
    target.tvChooseYansefenlei = Utils.findRequiredViewAsType(source, R.id.tv_choose_yansefenlei, "field 'tvChooseYansefenlei'", TextView.class);
    target.viewLine1 = Utils.findRequiredView(source, R.id.view_line1, "field 'viewLine1'");
    target.tvGoumaiNumber = Utils.findRequiredViewAsType(source, R.id.tv_goumai_number, "field 'tvGoumaiNumber'", TextView.class);
    target.tvJia = Utils.findRequiredViewAsType(source, R.id.tv_jia, "field 'tvJia'", TextView.class);
    target.tvNumber = Utils.findRequiredViewAsType(source, R.id.tv_number, "field 'tvNumber'", TextView.class);
    target.rtvEnter = Utils.findRequiredViewAsType(source, R.id.rtv_enter, "field 'rtvEnter'", RoundTextView.class);
    target.rlvList = Utils.findRequiredViewAsType(source, R.id.rlv_list, "field 'rlvList'", RecyclerView.class);
    target.constrain = Utils.findRequiredViewAsType(source, R.id.constrain, "field 'constrain'", ConstraintLayout.class);
    target.tvKucun = Utils.findRequiredViewAsType(source, R.id.tv_kucun, "field 'tvKucun'", TextView.class);
    target.tvYanse = Utils.findRequiredViewAsType(source, R.id.tv_yanse, "field 'tvYanse'", TextView.class);
    target.constrain1 = Utils.findRequiredViewAsType(source, R.id.constrain1, "field 'constrain1'", ConstraintLayout.class);
    target.tvCanshu = Utils.findRequiredViewAsType(source, R.id.tv_canshu, "field 'tvCanshu'", TextView.class);
    target.rlGuige = Utils.findRequiredViewAsType(source, R.id.rl_guige, "field 'rlGuige'", RelativeLayout.class);
    target.rtvText = Utils.findRequiredViewAsType(source, R.id.rtv_text, "field 'rtvText'", RoundTextView.class);
    target.tvJian = Utils.findRequiredViewAsType(source, R.id.tv_jian, "field 'tvJian'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChooseTaoCanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivImage = null;
    target.ivClose = null;
    target.tvPrice = null;
    target.viewLine = null;
    target.tvChooseYansefenlei = null;
    target.viewLine1 = null;
    target.tvGoumaiNumber = null;
    target.tvJia = null;
    target.tvNumber = null;
    target.rtvEnter = null;
    target.rlvList = null;
    target.constrain = null;
    target.tvKucun = null;
    target.tvYanse = null;
    target.constrain1 = null;
    target.tvCanshu = null;
    target.rlGuige = null;
    target.rtvText = null;
    target.tvJian = null;
  }
}
