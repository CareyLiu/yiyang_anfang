// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.util.phoneview.sample;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ImageShowActivity_ViewBinding implements Unbinder {
  private ImageShowActivity target;

  @UiThread
  public ImageShowActivity_ViewBinding(ImageShowActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ImageShowActivity_ViewBinding(ImageShowActivity target, View source) {
    this.target = target;

    target.hackyviewPager = Utils.findRequiredViewAsType(source, R.id.hackyview_pager, "field 'hackyviewPager'", HackyViewPager.class);
    target.pageNumber = Utils.findRequiredViewAsType(source, R.id.page_number, "field 'pageNumber'", TextView.class);
    target.fullImageRoot = Utils.findRequiredViewAsType(source, R.id.full_image_root, "field 'fullImageRoot'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ImageShowActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.hackyviewPager = null;
    target.pageNumber = null;
    target.fullImageRoot = null;
  }
}
