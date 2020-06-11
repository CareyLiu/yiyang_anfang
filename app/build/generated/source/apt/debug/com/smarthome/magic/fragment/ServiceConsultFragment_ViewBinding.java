// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.fragment;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ServiceConsultFragment_ViewBinding implements Unbinder {
  private ServiceConsultFragment target;

  @UiThread
  public ServiceConsultFragment_ViewBinding(ServiceConsultFragment target, View source) {
    this.target = target;

    target.list = Utils.findRequiredViewAsType(source, R.id.list, "field 'list'", LRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ServiceConsultFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.list = null;
  }
}
