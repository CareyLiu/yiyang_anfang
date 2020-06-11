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

public class TuiGuangMaActivity_ViewBinding implements Unbinder {
  private TuiGuangMaActivity target;

  @UiThread
  public TuiGuangMaActivity_ViewBinding(TuiGuangMaActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TuiGuangMaActivity_ViewBinding(TuiGuangMaActivity target, View source) {
    this.target = target;

    target.ivErweima = Utils.findRequiredViewAsType(source, R.id.iv_erweima, "field 'ivErweima'", ImageView.class);
    target.tvYonghuYaoqing = Utils.findRequiredViewAsType(source, R.id.tv_yonghu_yaoqing, "field 'tvYonghuYaoqing'", TextView.class);
    target.iv1 = Utils.findRequiredViewAsType(source, R.id.iv_1, "field 'iv1'", ImageView.class);
    target.iv2 = Utils.findRequiredViewAsType(source, R.id.iv_2, "field 'iv2'", ImageView.class);
    target.iv3 = Utils.findRequiredViewAsType(source, R.id.iv_3, "field 'iv3'", ImageView.class);
    target.iv4 = Utils.findRequiredViewAsType(source, R.id.iv_4, "field 'iv4'", ImageView.class);
    target.iv5 = Utils.findRequiredViewAsType(source, R.id.iv_5, "field 'iv5'", ImageView.class);
    target.iv6 = Utils.findRequiredViewAsType(source, R.id.iv_6, "field 'iv6'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TuiGuangMaActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivErweima = null;
    target.tvYonghuYaoqing = null;
    target.iv1 = null;
    target.iv2 = null;
    target.iv3 = null;
    target.iv4 = null;
    target.iv5 = null;
    target.iv6 = null;
  }
}
