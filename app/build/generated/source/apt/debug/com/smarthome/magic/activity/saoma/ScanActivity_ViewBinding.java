// Generated code from Butter Knife. Do not modify!
package com.smarthome.magic.activity.saoma;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import cn.bingoogolapple.qrcode.zbar.ZBarView;
import com.smarthome.magic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ScanActivity_ViewBinding implements Unbinder {
  private ScanActivity target;

  private View view7f09009c;

  @UiThread
  public ScanActivity_ViewBinding(ScanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ScanActivity_ViewBinding(final ScanActivity target, View source) {
    this.target = target;

    View view;
    target.mQRCodeView = Utils.findRequiredViewAsType(source, R.id.zxingview, "field 'mQRCodeView'", ZBarView.class);
    view = Utils.findRequiredView(source, R.id.capture_flash, "field 'captureFlash' and method 'onClick'");
    target.captureFlash = Utils.castView(view, R.id.capture_flash, "field 'captureFlash'", ImageView.class);
    view7f09009c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ScanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mQRCodeView = null;
    target.captureFlash = null;

    view7f09009c.setOnClickListener(null);
    view7f09009c = null;
  }
}
