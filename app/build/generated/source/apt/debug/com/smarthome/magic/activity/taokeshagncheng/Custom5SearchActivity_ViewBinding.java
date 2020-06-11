// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.taokeshagncheng;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.flyco.roundview.RoundLinearLayout;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Custom5SearchActivity_ViewBinding implements Unbinder {
  private Custom5SearchActivity target;

  private View view7f0901e0;

  private View view7f09028d;

  @UiThread
  public Custom5SearchActivity_ViewBinding(Custom5SearchActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Custom5SearchActivity_ViewBinding(final Custom5SearchActivity target, View source) {
    this.target = target;

    View view;
    target.etSerarchKey = Utils.findRequiredViewAsType(source, R.id.et_serarchKey, "field 'etSerarchKey'", EditText.class);
    target.rlSearch = Utils.findRequiredViewAsType(source, R.id.rl_search, "field 'rlSearch'", RoundLinearLayout.class);
    view = Utils.findRequiredView(source, R.id.iv_cancel, "field 'ivCancel' and method 'onClick'");
    target.ivCancel = Utils.castView(view, R.id.iv_cancel, "field 'ivCancel'", TextView.class);
    view7f0901e0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.rlTitle = Utils.findRequiredViewAsType(source, R.id.rl_title, "field 'rlTitle'", RelativeLayout.class);
    target.rvView2 = Utils.findRequiredViewAsType(source, R.id.rv_view2, "field 'rvView2'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.ll_clear_all, "field 'llClearAll' and method 'onClick'");
    target.llClearAll = Utils.castView(view, R.id.ll_clear_all, "field 'llClearAll'", LinearLayout.class);
    view7f09028d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.viewLine = Utils.findRequiredView(source, R.id.view_line, "field 'viewLine'");
    target.llSearch = Utils.findRequiredViewAsType(source, R.id.ll_search, "field 'llSearch'", LinearLayout.class);
    target.activityCustom3Search = Utils.findRequiredViewAsType(source, R.id.activity_custom3_search, "field 'activityCustom3Search'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Custom5SearchActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etSerarchKey = null;
    target.rlSearch = null;
    target.ivCancel = null;
    target.rlTitle = null;
    target.rvView2 = null;
    target.llClearAll = null;
    target.viewLine = null;
    target.llSearch = null;
    target.activityCustom3Search = null;

    view7f0901e0.setOnClickListener(null);
    view7f0901e0 = null;
    view7f09028d.setOnClickListener(null);
    view7f09028d = null;
  }
}
