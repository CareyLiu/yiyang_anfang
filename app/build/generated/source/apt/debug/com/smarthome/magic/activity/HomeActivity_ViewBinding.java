// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import com.smarthome.magic.view.NoScrollViewPager;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeActivity_ViewBinding implements Unbinder {
  private HomeActivity target;

  @UiThread
  public HomeActivity_ViewBinding(HomeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HomeActivity_ViewBinding(HomeActivity target, View source) {
    this.target = target;

    target.mVp = Utils.findRequiredViewAsType(source, R.id.vp, "field 'mVp'", NoScrollViewPager.class);
    target.activityWithViewPager = Utils.findRequiredViewAsType(source, R.id.activity_with_view_pager, "field 'activityWithViewPager'", RelativeLayout.class);
    target.layoutBg = Utils.findRequiredViewAsType(source, R.id.layout_bg, "field 'layoutBg'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mVp = null;
    target.activityWithViewPager = null;
    target.layoutBg = null;
  }
}
