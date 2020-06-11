// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.taokeshagncheng;

import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import com.smarthome.magic.view.magicindicator.MagicIndicator;
import com.youth.banner.Banner;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TaoKeHomeActivity_ViewBinding implements Unbinder {
  private TaoKeHomeActivity target;

  @UiThread
  public TaoKeHomeActivity_ViewBinding(TaoKeHomeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TaoKeHomeActivity_ViewBinding(TaoKeHomeActivity target, View source) {
    this.target = target;

    target.banner = Utils.findRequiredViewAsType(source, R.id.banner, "field 'banner'", Banner.class);
    target.magicIndicator4 = Utils.findRequiredViewAsType(source, R.id.magic_indicator4, "field 'magicIndicator4'", MagicIndicator.class);
    target.llSearch = Utils.findRequiredViewAsType(source, R.id.ll_search, "field 'llSearch'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TaoKeHomeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.banner = null;
    target.magicIndicator4 = null;
    target.llSearch = null;
  }
}
