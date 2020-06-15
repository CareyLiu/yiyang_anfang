// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.smarthome.magic.R;
import com.smarthome.magic.view.HintSideBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CarBrandActivity_ViewBinding implements Unbinder {
  private CarBrandActivity target;

  private View view7f090359;

  @UiThread
  public CarBrandActivity_ViewBinding(CarBrandActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CarBrandActivity_ViewBinding(final CarBrandActivity target, View source) {
    this.target = target;

    View view;
    target.mList = Utils.findRequiredViewAsType(source, R.id.list, "field 'mList'", LRecyclerView.class);
    target.mHintSideBar = Utils.findRequiredViewAsType(source, R.id.hintSideBar, "field 'mHintSideBar'", HintSideBar.class);
    view = Utils.findRequiredView(source, R.id.rl_back, "field 'rlBack' and method 'onViewClicked'");
    target.rlBack = Utils.castView(view, R.id.rl_back, "field 'rlBack'", RelativeLayout.class);
    view7f090359 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CarBrandActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mList = null;
    target.mHintSideBar = null;
    target.rlBack = null;

    view7f090359.setOnClickListener(null);
    view7f090359 = null;
  }
}
