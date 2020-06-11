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

public class RepairOrderAcitivity_ViewBinding implements Unbinder {
  private RepairOrderAcitivity target;

  private View view7f090352;

  @UiThread
  public RepairOrderAcitivity_ViewBinding(RepairOrderAcitivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RepairOrderAcitivity_ViewBinding(final RepairOrderAcitivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.rl_back, "field 'rlBack' and method 'onViewClicked'");
    target.rlBack = Utils.castView(view, R.id.rl_back, "field 'rlBack'", RelativeLayout.class);
    view7f090352 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.list = Utils.findRequiredViewAsType(source, R.id.list, "field 'list'", LRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RepairOrderAcitivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlBack = null;
    target.list = null;

    view7f090352.setOnClickListener(null);
    view7f090352 = null;
  }
}
