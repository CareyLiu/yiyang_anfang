// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.homepage;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LiBaoPageActivity_ViewBinding implements Unbinder {
  private LiBaoPageActivity target;

  @UiThread
  public LiBaoPageActivity_ViewBinding(LiBaoPageActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LiBaoPageActivity_ViewBinding(LiBaoPageActivity target, View source) {
    this.target = target;

    target.ivBackground = Utils.findRequiredViewAsType(source, R.id.iv_background, "field 'ivBackground'", ImageView.class);
    target.rvList = Utils.findRequiredViewAsType(source, R.id.rv_list, "field 'rvList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LiBaoPageActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBackground = null;
    target.rvList = null;
  }
}
