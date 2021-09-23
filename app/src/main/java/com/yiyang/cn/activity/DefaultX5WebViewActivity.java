package com.yiyang.cn.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.tools.NetworkUtils;
import com.yiyang.cn.util.x5.utils.AndroidForJs;
import com.yiyang.cn.util.x5.utils.X5WebView;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.tencent.smtt.utils.TbsLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


public class DefaultX5WebViewActivity extends BaseActivity {

    public final static String tag = DefaultX5WebViewActivity.class.getSimpleName();
    @BindView(R.id.x5_webView)
    X5WebView x5WebView;


    private String url;
    private Intent intent;
    private int netState;
    private String publicMethod;
    List<String> mGoBackUrlList = new ArrayList<>();
    boolean isLoad = true;

    private ProgressDialog progressDialog;
    private String shareId, shareType;

    /**
     * @param context 上下文
     * @param url     需要显示的url地址
     */
    public static void actionStart(Context context, String url, String shareId, String shareType) {
        Intent intent = new Intent(context, DefaultX5WebViewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("url", url);
        if (shareId != null && shareType != null) {
            intent.putExtra("shareId", shareId);
            intent.putExtra("shareType", shareType);
        }
        context.startActivity(intent);
    }

    public static void actionStart(Context context, String url) {
        actionStart(context, url, null, null);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        netState = NetworkUtils.getAPNType(this);
        Log.e(tag, "网络状态：" + netState);
        intent = getIntent();
        if (intent == null) {
            return;
        }
        url = intent.getStringExtra("url");
        shareId = intent.getStringExtra("shareId");
        shareType = intent.getStringExtra("shareType");
        init();
        if (!TextUtils.isEmpty(shareId) && !TextUtils.isEmpty(shareType)) {
            iv_rightTitle.setVisibility(View.VISIBLE);
        }

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_SAOMASUCCESS) {
                    // x5WebView.loadUrl("javascript:java_js('appToJsPaySuccess')");
                    // x5WebView.loadUrl("http://www.baidu.com");
                    // 通过Handler发送消息
                    x5WebView.post(new Runnable() {
                        @Override
                        public void run() {

                            // 注意调用的JS方法名要对应上
                            // 调用javascript的callJS()方法
                            x5WebView.loadUrl("javascript:appToJsPaySuccess()");
                        }
                    });
                    Log.i("x5webviewsuccess", "webview_success");
                    UIHelper.ToastMessage(DefaultX5WebViewActivity.this, "支付成功");
                } else if (message.type == ConstanceValue.MSG_SAOMAFAILE) {
                    //x5WebView.loadUrl("javascript:java_js('appToJsPayFaile')");


                    x5WebView.post(new Runnable() {
                        @Override
                        public void run() {

                            // 注意调用的JS方法名要对应上
                            // 调用javascript的callJS()方法
                            x5WebView.loadUrl("javascript:appToJsPayFaile()");
                        }
                    });
                    UIHelper.ToastMessage(DefaultX5WebViewActivity.this, "支付失败");
                } else if (message.type == ConstanceValue.MSG_DAILISHANG_TIXIAN) {
                    if (message.content.toString().equals("0")) {
                        x5WebView.loadUrl("javascript:appToJsTXResult(0)");
                        x5WebView.reload();
                    } else {
                        x5WebView.loadUrl("javascript:appToJsTXResult(1)");
                        x5WebView.reload();
                    }
                }
            }
        }));

    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_default_x5_web_view;
    }


    private void init() {
        x5WebView.setWebViewClient(new MyWebViewClient());
        x5WebView.setWebChromeClient(new WebChromeClient());
        x5WebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String s, String s1, String s2, String s3, long l) {
                if (url != null && url.startsWith("http://"))
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });
//        tvLoad.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadUrl(url);
//            }
//        });

        WebSettings webSetting = x5WebView.getSettings();
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setSupportMultipleWindows(false);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setAppCachePath(x5WebView.getContext().getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(x5WebView.getContext().getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(x5WebView.getContext().getDir("geolocation", 0).getPath());
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        x5WebView.addJavascriptInterface(new AndroidForJs(DefaultX5WebViewActivity.this), "android");
        long time = System.currentTimeMillis();
        Log.e(tag, "DefaultX5WebViewActivity : " + url);
        if (url != null) {
            loadUrl(url);
        }
        TbsLog.d("time-cost", "cost time: " + (System.currentTimeMillis() - time));
//        CookieSyncManager.createInstance(x5WebView.getContext());
//        CookieSyncManager.getInstance().sync();
    }


    public void loadUrl(String url) {
        if (url == null) {
            return;
        }
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        x5WebView.loadUrl(url);
    }


    public class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        //网页加载开始时调用，显示加载提示旋转进度条
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(DefaultX5WebViewActivity.this);//网页没加载出来时显示的dialog提示
                progressDialog.setMessage("加载中，请稍后...");
                progressDialog.show();
                x5WebView.setEnabled(false);// 当加载网页的时候将网页进行隐藏
            }
            super.onPageStarted(view, url, favicon);
        }

        //网页加载完成时调用，隐藏加载提示旋转进度条
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
                progressDialog = null;
                x5WebView.setEnabled(true);
            }
            if (tv_title != null && view != null && view.getTitle() != null) {
                tv_title.setText(view.getTitle());
            }
            publicMethod = "javascript:publicMethod('operatingSystem','" + new Gson().toJson("android") + "')";
            if (x5WebView != null) {
                x5WebView.loadUrl(publicMethod);
            }
            mGoBackUrlList.add(url);
            isLoad = false;
        }

        //网页加载失败时调用，隐藏加载提示旋转进度条
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
            handler.proceed(); // 接受证书
        }
    }


    @Override
    public void onDestroy() {
        if (x5WebView != null) {
            x5WebView.destroy();
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if (x5WebView != null) {
            x5WebView.onPause();
            x5WebView.reload();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (x5WebView != null) {
            x5WebView.onResume();
            x5WebView.reload();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (x5WebView != null && x5WebView.canGoBack()) {
                int index = mGoBackUrlList.size();
                if (index == 0 || index == 1) {
                    x5WebView.goBack();
                    mGoBackUrlList.clear();
                } else {
                    if (x5WebView.canGoBackOrForward(-index)) {
                        x5WebView.goBackOrForward(-index);
                        mGoBackUrlList.clear();
                    } else {
                        x5WebView.goBack();
                        mGoBackUrlList.clear();
                    }
                }
                return true;
            } else {
                if (!isLoad) {
                    return super.onKeyDown(keyCode, event);
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }
}
