// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.material.navigation.NavigationView;
import com.smarthome.magic.R;
import com.smarthome.magic.view.CircleMenuView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ControCarActivity_ViewBinding implements Unbinder {
  private ControCarActivity target;

  @UiThread
  public ControCarActivity_ViewBinding(ControCarActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ControCarActivity_ViewBinding(ControCarActivity target, View source) {
    this.target = target;

    target.toolbarTitle = Utils.findRequiredViewAsType(source, R.id.toolbar_title, "field 'toolbarTitle'", TextView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.ivWindmill = Utils.findRequiredViewAsType(source, R.id.iv_windmill, "field 'ivWindmill'", ImageView.class);
    target.sbSpeed = Utils.findRequiredViewAsType(source, R.id.sb_speed, "field 'sbSpeed'", SeekBar.class);
    target.tvWd = Utils.findRequiredViewAsType(source, R.id.tv_wd, "field 'tvWd'", TextView.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.ivCar = Utils.findRequiredViewAsType(source, R.id.iv_car, "field 'ivCar'", ImageView.class);
    target.ivLeftHeadDoor = Utils.findRequiredViewAsType(source, R.id.iv_left_head_door, "field 'ivLeftHeadDoor'", ImageView.class);
    target.ivRightHeadDoor = Utils.findRequiredViewAsType(source, R.id.iv_right_head_door, "field 'ivRightHeadDoor'", ImageView.class);
    target.ivLeftRearDoor = Utils.findRequiredViewAsType(source, R.id.iv_left_rear_door, "field 'ivLeftRearDoor'", ImageView.class);
    target.ivRightRearDoor = Utils.findRequiredViewAsType(source, R.id.iv_right_rear_door, "field 'ivRightRearDoor'", ImageView.class);
    target.ivTrunkCovers = Utils.findRequiredViewAsType(source, R.id.iv_trunk_covers, "field 'ivTrunkCovers'", ImageView.class);
    target.ivHeadLight = Utils.findRequiredViewAsType(source, R.id.iv_head_light, "field 'ivHeadLight'", ImageView.class);
    target.ivBackLight = Utils.findRequiredViewAsType(source, R.id.iv_back_light, "field 'ivBackLight'", ImageView.class);
    target.navView = Utils.findRequiredViewAsType(source, R.id.nav_view, "field 'navView'", NavigationView.class);
    target.drawerLayout = Utils.findRequiredViewAsType(source, R.id.drawer_layout, "field 'drawerLayout'", DrawerLayout.class);
    target.circleMenu = Utils.findRequiredViewAsType(source, R.id.circle_menu, "field 'circleMenu'", CircleMenuView.class);
    target.ivTyre = Utils.findRequiredViewAsType(source, R.id.iv_tyre, "field 'ivTyre'", ImageView.class);
    target.ivFuel = Utils.findRequiredViewAsType(source, R.id.iv_fuel, "field 'ivFuel'", ImageView.class);
    target.tvFuel = Utils.findRequiredViewAsType(source, R.id.tv_fuel, "field 'tvFuel'", TextView.class);
    target.ivCoolant = Utils.findRequiredViewAsType(source, R.id.iv_coolant, "field 'ivCoolant'", ImageView.class);
    target.tvCoolant = Utils.findRequiredViewAsType(source, R.id.tv_coolant, "field 'tvCoolant'", TextView.class);
    target.ivGlassWater = Utils.findRequiredViewAsType(source, R.id.iv_glass_water, "field 'ivGlassWater'", ImageView.class);
    target.tvGlassWater = Utils.findRequiredViewAsType(source, R.id.tv_glass_water, "field 'tvGlassWater'", TextView.class);
    target.ivSpeed = Utils.findRequiredViewAsType(source, R.id.iv_speed, "field 'ivSpeed'", ImageView.class);
    target.tvSpeed = Utils.findRequiredViewAsType(source, R.id.tv_speed, "field 'tvSpeed'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ControCarActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbarTitle = null;
    target.toolbar = null;
    target.ivWindmill = null;
    target.sbSpeed = null;
    target.tvWd = null;
    target.tvTime = null;
    target.ivCar = null;
    target.ivLeftHeadDoor = null;
    target.ivRightHeadDoor = null;
    target.ivLeftRearDoor = null;
    target.ivRightRearDoor = null;
    target.ivTrunkCovers = null;
    target.ivHeadLight = null;
    target.ivBackLight = null;
    target.navView = null;
    target.drawerLayout = null;
    target.circleMenu = null;
    target.ivTyre = null;
    target.ivFuel = null;
    target.tvFuel = null;
    target.ivCoolant = null;
    target.tvCoolant = null;
    target.ivGlassWater = null;
    target.tvGlassWater = null;
    target.ivSpeed = null;
    target.tvSpeed = null;
  }
}
