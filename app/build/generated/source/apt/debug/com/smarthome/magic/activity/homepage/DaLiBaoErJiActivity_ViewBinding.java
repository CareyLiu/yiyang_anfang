// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.homepage;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DaLiBaoErJiActivity_ViewBinding implements Unbinder {
  private DaLiBaoErJiActivity target;

  @UiThread
  public DaLiBaoErJiActivity_ViewBinding(DaLiBaoErJiActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DaLiBaoErJiActivity_ViewBinding(DaLiBaoErJiActivity target, View source) {
    this.target = target;

    target.ivImage = Utils.findRequiredViewAsType(source, R.id.iv_image, "field 'ivImage'", ImageView.class);
    target.tvLijiBuy = Utils.findRequiredViewAsType(source, R.id.tv_liji_buy, "field 'tvLijiBuy'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DaLiBaoErJiActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivImage = null;
    target.tvLijiBuy = null;
  }
}
