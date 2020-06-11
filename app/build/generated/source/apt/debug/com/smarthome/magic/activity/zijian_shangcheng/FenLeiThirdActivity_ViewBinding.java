// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.zijian_shangcheng;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FenLeiThirdActivity_ViewBinding implements Unbinder {
  private FenLeiThirdActivity target;

  @UiThread
  public FenLeiThirdActivity_ViewBinding(FenLeiThirdActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FenLeiThirdActivity_ViewBinding(FenLeiThirdActivity target, View source) {
    this.target = target;

    target.rl1 = Utils.findRequiredViewAsType(source, R.id.rl_1, "field 'rl1'", RelativeLayout.class);
    target.rl2 = Utils.findRequiredViewAsType(source, R.id.rl_2, "field 'rl2'", RelativeLayout.class);
    target.flContainer = Utils.findRequiredViewAsType(source, R.id.fl_container, "field 'flContainer'", FrameLayout.class);
    target.tvZonghe = Utils.findRequiredViewAsType(source, R.id.tv_zonghe, "field 'tvZonghe'", TextView.class);
    target.tvXiaoliang = Utils.findRequiredViewAsType(source, R.id.tv_xiaoliang, "field 'tvXiaoliang'", TextView.class);
    target.ivBack = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'ivBack'", ImageView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.rlMain = Utils.findRequiredViewAsType(source, R.id.rl_main, "field 'rlMain'", RelativeLayout.class);
    target.llZongheXiaoliang = Utils.findRequiredViewAsType(source, R.id.ll_zonghe_xiaoliang, "field 'llZongheXiaoliang'", LinearLayout.class);
    target.tvZhekou = Utils.findRequiredViewAsType(source, R.id.tv_zhekou, "field 'tvZhekou'", TextView.class);
    target.view1 = Utils.findRequiredView(source, R.id.view_1, "field 'view1'");
    target.tvJiagesheng = Utils.findRequiredViewAsType(source, R.id.tv_jiagesheng, "field 'tvJiagesheng'", TextView.class);
    target.view2 = Utils.findRequiredView(source, R.id.view_2, "field 'view2'");
    target.tvJiagejiang = Utils.findRequiredViewAsType(source, R.id.tv_jiagejiang, "field 'tvJiagejiang'", TextView.class);
    target.view4 = Utils.findRequiredView(source, R.id.view_4, "field 'view4'");
    target.tvXiaoliagnjiang = Utils.findRequiredViewAsType(source, R.id.tv_xiaoliagnjiang, "field 'tvXiaoliagnjiang'", TextView.class);
    target.constrain = Utils.findRequiredViewAsType(source, R.id.constrain, "field 'constrain'", ConstraintLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FenLeiThirdActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rl1 = null;
    target.rl2 = null;
    target.flContainer = null;
    target.tvZonghe = null;
    target.tvXiaoliang = null;
    target.ivBack = null;
    target.tvTitle = null;
    target.rlMain = null;
    target.llZongheXiaoliang = null;
    target.tvZhekou = null;
    target.view1 = null;
    target.tvJiagesheng = null;
    target.view2 = null;
    target.tvJiagejiang = null;
    target.view4 = null;
    target.tvXiaoliagnjiang = null;
    target.constrain = null;
  }
}
