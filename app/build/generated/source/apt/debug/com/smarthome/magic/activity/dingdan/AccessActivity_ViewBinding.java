// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.dingdan;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.AppCompatRatingBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.flyco.roundview.RoundRelativeLayout;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AccessActivity_ViewBinding implements Unbinder {
  private AccessActivity target;

  @UiThread
  public AccessActivity_ViewBinding(AccessActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AccessActivity_ViewBinding(AccessActivity target, View source) {
    this.target = target;

    target.ivProduct = Utils.findRequiredViewAsType(source, R.id.iv_product, "field 'ivProduct'", ImageView.class);
    target.tvPinglunHuashu = Utils.findRequiredViewAsType(source, R.id.tv_pinglun_huashu, "field 'tvPinglunHuashu'", TextView.class);
    target.rbBar1 = Utils.findRequiredViewAsType(source, R.id.rb_bar1, "field 'rbBar1'", AppCompatRatingBar.class);
    target.view = Utils.findRequiredView(source, R.id.view, "field 'view'");
    target.rrlTijiao = Utils.findRequiredViewAsType(source, R.id.rrl_tijiao, "field 'rrlTijiao'", RoundRelativeLayout.class);
    target.etText = Utils.findRequiredViewAsType(source, R.id.et_text, "field 'etText'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AccessActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivProduct = null;
    target.tvPinglunHuashu = null;
    target.rbBar1 = null;
    target.view = null;
    target.rrlTijiao = null;
    target.etText = null;
  }
}
