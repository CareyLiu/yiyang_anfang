// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ReviseNicknameActivity_ViewBinding implements Unbinder {
  private ReviseNicknameActivity target;

  private View view7f090366;

  private View view7f09052e;

  @UiThread
  public ReviseNicknameActivity_ViewBinding(ReviseNicknameActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ReviseNicknameActivity_ViewBinding(final ReviseNicknameActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.rl_back, "field 'rlBack' and method 'onViewClicked'");
    target.rlBack = Utils.castView(view, R.id.rl_back, "field 'rlBack'", RelativeLayout.class);
    view7f090366 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_save, "field 'tvSave' and method 'onViewClicked'");
    target.tvSave = Utils.castView(view, R.id.tv_save, "field 'tvSave'", TextView.class);
    view7f09052e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etNickName = Utils.findRequiredViewAsType(source, R.id.et_nick_name, "field 'etNickName'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ReviseNicknameActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlBack = null;
    target.tvSave = null;
    target.etNickName = null;

    view7f090366.setOnClickListener(null);
    view7f090366 = null;
    view7f09052e.setOnClickListener(null);
    view7f09052e = null;
  }
}
