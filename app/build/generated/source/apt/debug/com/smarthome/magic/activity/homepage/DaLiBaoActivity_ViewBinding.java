// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.homepage;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DaLiBaoActivity_ViewBinding implements Unbinder {
  private DaLiBaoActivity target;

  @UiThread
  public DaLiBaoActivity_ViewBinding(DaLiBaoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DaLiBaoActivity_ViewBinding(DaLiBaoActivity target, View source) {
    this.target = target;

    target.iv1 = Utils.findRequiredViewAsType(source, R.id.iv_1, "field 'iv1'", ImageView.class);
    target.rvList = Utils.findRequiredViewAsType(source, R.id.rv_list, "field 'rvList'", RecyclerView.class);
    target.tvLijiBuy = Utils.findRequiredViewAsType(source, R.id.tv_liji_buy, "field 'tvLijiBuy'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DaLiBaoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.iv1 = null;
    target.rvList = null;
    target.tvLijiBuy = null;
  }
}
