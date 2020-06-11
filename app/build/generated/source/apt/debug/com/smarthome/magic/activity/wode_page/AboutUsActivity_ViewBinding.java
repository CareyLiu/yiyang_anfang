// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.wode_page;

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

public class AboutUsActivity_ViewBinding implements Unbinder {
  private AboutUsActivity target;

  @UiThread
  public AboutUsActivity_ViewBinding(AboutUsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AboutUsActivity_ViewBinding(AboutUsActivity target, View source) {
    this.target = target;

    target.ivImage = Utils.findRequiredViewAsType(source, R.id.iv_image, "field 'ivImage'", ImageView.class);
    target.tvYinsi = Utils.findRequiredViewAsType(source, R.id.tv_yinsi, "field 'tvYinsi'", TextView.class);
    target.tvYonghushiyong = Utils.findRequiredViewAsType(source, R.id.tv_yonghushiyong, "field 'tvYonghushiyong'", TextView.class);
    target.tvBanbenHao = Utils.findRequiredViewAsType(source, R.id.tv_banben_hao, "field 'tvBanbenHao'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AboutUsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivImage = null;
    target.tvYinsi = null;
    target.tvYonghushiyong = null;
    target.tvBanbenHao = null;
  }
}
