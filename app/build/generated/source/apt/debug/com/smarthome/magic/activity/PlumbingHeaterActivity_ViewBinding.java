// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.material.navigation.NavigationView;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PlumbingHeaterActivity_ViewBinding implements Unbinder {
  private PlumbingHeaterActivity target;

  @UiThread
  public PlumbingHeaterActivity_ViewBinding(PlumbingHeaterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PlumbingHeaterActivity_ViewBinding(PlumbingHeaterActivity target, View source) {
    this.target = target;

    target.toolbarTitle = Utils.findRequiredViewAsType(source, R.id.toolbar_title, "field 'toolbarTitle'", TextView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.ivHeaterHost = Utils.findRequiredViewAsType(source, R.id.iv_heater_host, "field 'ivHeaterHost'", RelativeLayout.class);
    target.shuiwen1 = Utils.findRequiredViewAsType(source, R.id.shuiwen1, "field 'shuiwen1'", TextView.class);
    target.wendu2 = Utils.findRequiredViewAsType(source, R.id.wendu2, "field 'wendu2'", TextView.class);
    target.btnHeaterClose = Utils.findRequiredViewAsType(source, R.id.btn_heater_close, "field 'btnHeaterClose'", ImageView.class);
    target.navView = Utils.findRequiredViewAsType(source, R.id.nav_view, "field 'navView'", NavigationView.class);
    target.drawerLayout = Utils.findRequiredViewAsType(source, R.id.drawer_layout, "field 'drawerLayout'", DrawerLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PlumbingHeaterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbarTitle = null;
    target.toolbar = null;
    target.ivHeaterHost = null;
    target.shuiwen1 = null;
    target.wendu2 = null;
    target.btnHeaterClose = null;
    target.navView = null;
    target.drawerLayout = null;
  }
}
