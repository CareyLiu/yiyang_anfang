// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.taokeshagncheng;

import android.view.View;
import android.widget.Button;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GoodsActivity_ViewBinding implements Unbinder {
  private GoodsActivity target;

  @UiThread
  public GoodsActivity_ViewBinding(GoodsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GoodsActivity_ViewBinding(GoodsActivity target, View source) {
    this.target = target;

    target.login = Utils.findRequiredViewAsType(source, R.id.login, "field 'login'", Button.class);
    target.logout = Utils.findRequiredViewAsType(source, R.id.logout, "field 'logout'", Button.class);
    target.isLogin = Utils.findRequiredViewAsType(source, R.id.isLogin, "field 'isLogin'", Button.class);
    target.openByUrl = Utils.findRequiredViewAsType(source, R.id.open_by_url, "field 'openByUrl'", Button.class);
    target.openByCode = Utils.findRequiredViewAsType(source, R.id.open_by_code, "field 'openByCode'", Button.class);
    target.openByWebview = Utils.findRequiredViewAsType(source, R.id.open_by_webview, "field 'openByWebview'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GoodsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.login = null;
    target.logout = null;
    target.isLogin = null;
    target.openByUrl = null;
    target.openByCode = null;
    target.openByWebview = null;
  }
}
