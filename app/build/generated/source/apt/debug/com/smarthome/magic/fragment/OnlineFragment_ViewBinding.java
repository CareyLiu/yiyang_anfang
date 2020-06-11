// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.fragment;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OnlineFragment_ViewBinding implements Unbinder {
  private OnlineFragment target;

  @UiThread
  public OnlineFragment_ViewBinding(OnlineFragment target, View source) {
    this.target = target;

    target.mList = Utils.findRequiredViewAsType(source, R.id.list, "field 'mList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OnlineFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mList = null;
  }
}
