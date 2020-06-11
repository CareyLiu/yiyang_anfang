// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.view;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CustomBaseDialog_ViewBinding implements Unbinder {
  private CustomBaseDialog target;

  @UiThread
  public CustomBaseDialog_ViewBinding(CustomBaseDialog target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CustomBaseDialog_ViewBinding(CustomBaseDialog target, View source) {
    this.target = target;

    target.ivPic = Utils.findRequiredViewAsType(source, R.id.iv_pic, "field 'ivPic'", ImageView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvContent = Utils.findRequiredViewAsType(source, R.id.tv_content, "field 'tvContent'", TextView.class);
    target.btnCancel = Utils.findRequiredViewAsType(source, R.id.btn_cancel, "field 'btnCancel'", Button.class);
    target.btnConfirm = Utils.findRequiredViewAsType(source, R.id.btn_confirm, "field 'btnConfirm'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CustomBaseDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivPic = null;
    target.tvTitle = null;
    target.tvContent = null;
    target.btnCancel = null;
    target.btnConfirm = null;
  }
}
