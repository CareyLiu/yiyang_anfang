// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity;

import android.view.View;
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

public class ConversationActivity_ViewBinding implements Unbinder {
  private ConversationActivity target;

  private View view7f090366;

  @UiThread
  public ConversationActivity_ViewBinding(ConversationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ConversationActivity_ViewBinding(final ConversationActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.rl_back, "field 'mRlBack' and method 'onClick'");
    target.mRlBack = Utils.castView(view, R.id.rl_back, "field 'mRlBack'", RelativeLayout.class);
    view7f090366 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ConversationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRlBack = null;
    target.mTvTitle = null;

    view7f090366.setOnClickListener(null);
    view7f090366 = null;
  }
}
