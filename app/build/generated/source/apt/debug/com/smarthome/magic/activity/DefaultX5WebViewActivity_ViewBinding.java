// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import com.smarthome.magic.util.x5.utils.X5WebView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DefaultX5WebViewActivity_ViewBinding implements Unbinder {
  private DefaultX5WebViewActivity target;

  @UiThread
  public DefaultX5WebViewActivity_ViewBinding(DefaultX5WebViewActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DefaultX5WebViewActivity_ViewBinding(DefaultX5WebViewActivity target, View source) {
    this.target = target;

    target.x5WebView = Utils.findRequiredViewAsType(source, R.id.x5_webView, "field 'x5WebView'", X5WebView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DefaultX5WebViewActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.x5WebView = null;
  }
}
