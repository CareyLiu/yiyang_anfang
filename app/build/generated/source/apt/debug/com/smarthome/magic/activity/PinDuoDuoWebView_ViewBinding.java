// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import com.smarthome.magic.util.x5.utils.MyX5WebView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PinDuoDuoWebView_ViewBinding implements Unbinder {
  private PinDuoDuoWebView target;

  @UiThread
  public PinDuoDuoWebView_ViewBinding(PinDuoDuoWebView target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PinDuoDuoWebView_ViewBinding(PinDuoDuoWebView target, View source) {
    this.target = target;

    target.webview = Utils.findRequiredViewAsType(source, R.id.webview, "field 'webview'", MyX5WebView.class);
    target.ivImageBack = Utils.findRequiredViewAsType(source, R.id.iv_image_back, "field 'ivImageBack'", ImageView.class);
    target.tvBack = Utils.findRequiredViewAsType(source, R.id.tv_back, "field 'tvBack'", TextView.class);
    target.titleHeader = Utils.findRequiredViewAsType(source, R.id.title_header, "field 'titleHeader'", ConstraintLayout.class);
    target.ivClose = Utils.findRequiredViewAsType(source, R.id.iv_close, "field 'ivClose'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PinDuoDuoWebView target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.webview = null;
    target.ivImageBack = null;
    target.tvBack = null;
    target.titleHeader = null;
    target.ivClose = null;
  }
}
