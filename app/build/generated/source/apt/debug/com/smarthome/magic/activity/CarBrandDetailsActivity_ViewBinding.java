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
import java.lang.IllegalStateException;
import java.lang.Override;

public class CarBrandDetailsActivity_ViewBinding implements Unbinder {
  private CarBrandDetailsActivity target;

  private View view7f090366;

  @UiThread
  public CarBrandDetailsActivity_ViewBinding(CarBrandDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CarBrandDetailsActivity_ViewBinding(final CarBrandDetailsActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.rl_back, "field 'rlBack' and method 'onViewClicked'");
    target.rlBack = Utils.castView(view, R.id.rl_back, "field 'rlBack'", RelativeLayout.class);
    view7f090366 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.mList = Utils.findRequiredViewAsType(source, R.id.list, "field 'mList'", LRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CarBrandDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlBack = null;
    target.mList = null;

    view7f090366.setOnClickListener(null);
    view7f090366 = null;
  }
}
