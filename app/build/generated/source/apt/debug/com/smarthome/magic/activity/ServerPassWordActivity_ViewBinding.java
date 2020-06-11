// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.flyco.roundview.RoundTextView;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ServerPassWordActivity_ViewBinding implements Unbinder {
  private ServerPassWordActivity target;

  @UiThread
  public ServerPassWordActivity_ViewBinding(ServerPassWordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ServerPassWordActivity_ViewBinding(ServerPassWordActivity target, View source) {
    this.target = target;

    target.tvFuwupasswordHuashu = Utils.findRequiredViewAsType(source, R.id.tv_fuwupassword_huashu, "field 'tvFuwupasswordHuashu'", TextView.class);
    target.etEdit = Utils.findRequiredViewAsType(source, R.id.et_edit, "field 'etEdit'", EditText.class);
    target.rtTextview = Utils.findRequiredViewAsType(source, R.id.rt_textview, "field 'rtTextview'", RoundTextView.class);
    target.back = Utils.findRequiredViewAsType(source, R.id.back, "field 'back'", LinearLayout.class);
    target.rlMain = Utils.findRequiredViewAsType(source, R.id.rl_main, "field 'rlMain'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ServerPassWordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvFuwupasswordHuashu = null;
    target.etEdit = null;
    target.rtTextview = null;
    target.back = null;
    target.rlMain = null;
  }
}
