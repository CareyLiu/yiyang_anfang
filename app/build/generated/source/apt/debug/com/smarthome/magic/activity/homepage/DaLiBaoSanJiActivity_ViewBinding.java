// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.homepage;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DaLiBaoSanJiActivity_ViewBinding implements Unbinder {
  private DaLiBaoSanJiActivity target;

  @UiThread
  public DaLiBaoSanJiActivity_ViewBinding(DaLiBaoSanJiActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DaLiBaoSanJiActivity_ViewBinding(DaLiBaoSanJiActivity target, View source) {
    this.target = target;

    target.tvLijiBuy = Utils.findRequiredViewAsType(source, R.id.tv_liji_buy, "field 'tvLijiBuy'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DaLiBaoSanJiActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvLijiBuy = null;
  }
}
