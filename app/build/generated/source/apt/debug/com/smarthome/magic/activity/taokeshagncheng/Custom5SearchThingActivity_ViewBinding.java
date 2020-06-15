// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.taokeshagncheng;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.flyco.roundview.RoundLinearLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Custom5SearchThingActivity_ViewBinding implements Unbinder {
  private Custom5SearchThingActivity target;

  private View view7f0901e0;

  private View view7f0901e6;

  @UiThread
  public Custom5SearchThingActivity_ViewBinding(Custom5SearchThingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Custom5SearchThingActivity_ViewBinding(final Custom5SearchThingActivity target,
      View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'ivBack' and method 'onClick'");
    target.ivBack = Utils.castView(view, R.id.iv_back, "field 'ivBack'", ImageView.class);
    view7f0901e0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.etSerarchKey = Utils.findRequiredViewAsType(source, R.id.et_serarchKey, "field 'etSerarchKey'", EditText.class);
    target.rlSearch = Utils.findRequiredViewAsType(source, R.id.rl_search, "field 'rlSearch'", RoundLinearLayout.class);
    view = Utils.findRequiredView(source, R.id.iv_cancel, "field 'ivCancel' and method 'onClick'");
    target.ivCancel = Utils.castView(view, R.id.iv_cancel, "field 'ivCancel'", TextView.class);
    view7f0901e6 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.rlTitle = Utils.findRequiredViewAsType(source, R.id.rl_title, "field 'rlTitle'", RelativeLayout.class);
    target.llNone = Utils.findRequiredViewAsType(source, R.id.ll_none, "field 'llNone'", LinearLayout.class);
    target.activityCustom3Search = Utils.findRequiredViewAsType(source, R.id.activity_custom3_search, "field 'activityCustom3Search'", RelativeLayout.class);
    target.swipeTarget = Utils.findRequiredViewAsType(source, R.id.swipe_target, "field 'swipeTarget'", RecyclerView.class);
    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.refreshLayout, "field 'refreshLayout'", SmartRefreshLayout.class);
    target.tvZonghe = Utils.findRequiredViewAsType(source, R.id.tv_zonghe, "field 'tvZonghe'", TextView.class);
    target.rl1 = Utils.findRequiredViewAsType(source, R.id.rl_1, "field 'rl1'", RelativeLayout.class);
    target.tvXiaoliang = Utils.findRequiredViewAsType(source, R.id.tv_xiaoliang, "field 'tvXiaoliang'", TextView.class);
    target.rl2 = Utils.findRequiredViewAsType(source, R.id.rl_2, "field 'rl2'", RelativeLayout.class);
    target.llZongheXiaoliang = Utils.findRequiredViewAsType(source, R.id.ll_zonghe_xiaoliang, "field 'llZongheXiaoliang'", LinearLayout.class);
    target.tvZhekou = Utils.findRequiredViewAsType(source, R.id.tv_zhekou, "field 'tvZhekou'", TextView.class);
    target.view1 = Utils.findRequiredView(source, R.id.view_1, "field 'view1'");
    target.tvJiagesheng = Utils.findRequiredViewAsType(source, R.id.tv_jiagesheng, "field 'tvJiagesheng'", TextView.class);
    target.view2 = Utils.findRequiredView(source, R.id.view_2, "field 'view2'");
    target.tvJiagejiang = Utils.findRequiredViewAsType(source, R.id.tv_jiagejiang, "field 'tvJiagejiang'", TextView.class);
    target.view4 = Utils.findRequiredView(source, R.id.view_4, "field 'view4'");
    target.tvXiaoliagnjiang = Utils.findRequiredViewAsType(source, R.id.tv_xiaoliagnjiang, "field 'tvXiaoliagnjiang'", TextView.class);
    target.constrain = Utils.findRequiredViewAsType(source, R.id.constrain, "field 'constrain'", ConstraintLayout.class);
    target.constrainXx = Utils.findRequiredViewAsType(source, R.id.constrain_xx, "field 'constrainXx'", ConstraintLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Custom5SearchThingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.etSerarchKey = null;
    target.rlSearch = null;
    target.ivCancel = null;
    target.rlTitle = null;
    target.llNone = null;
    target.activityCustom3Search = null;
    target.swipeTarget = null;
    target.refreshLayout = null;
    target.tvZonghe = null;
    target.rl1 = null;
    target.tvXiaoliang = null;
    target.rl2 = null;
    target.llZongheXiaoliang = null;
    target.tvZhekou = null;
    target.view1 = null;
    target.tvJiagesheng = null;
    target.view2 = null;
    target.tvJiagejiang = null;
    target.view4 = null;
    target.tvXiaoliagnjiang = null;
    target.constrain = null;
    target.constrainXx = null;

    view7f0901e0.setOnClickListener(null);
    view7f0901e0 = null;
    view7f0901e6.setOnClickListener(null);
    view7f0901e6 = null;
  }
}
