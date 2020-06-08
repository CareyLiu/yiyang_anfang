package com.smarthome.magic.util.chenck_banben;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.core.content.FileProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.smarthome.magic.R;
import com.smarthome.magic.app.App;
import com.smarthome.magic.app.AppConfig;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.app.RxBus;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.model.CheckModel;
import com.smarthome.magic.tools.LogUtil;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

import rx.functions.Action1;

import static android.os.Environment.MEDIA_MOUNTED;

/**
 * 应用程序更新工具包
 *
 * @author liux (http://my.oschina.net/liux)
 * @version 1.1
 * @created 2012-6-29
 */
public class UpdateManager {

    private static final int DOWN_NOSDCARD = 0;
    private static final int DOWN_UPDATE = 1;
    private static final int DOWN_OVER = 2;

    private static final int DIALOG_TYPE_LATEST = 0;
    private static final int DIALOG_TYPE_FAIL = 1;

    private static UpdateManager updateManager;

    private Context mContext;
    //通知对话框
    private Dialog noticeDialog;
    //下载对话框
    private Dialog downloadDialog;
    //'已经是最新' 或者 '无法获取最新版本' 的对话框
    private Dialog latestOrFailDialog;
    //进度条
    private ProgressBar mProgress;
    //显示下载数值
    private TextView mProgressText;
    //查询动画
    private ProgressDialog mProDialog;
    //进度值
    private int progress;
    //下载线程
    private Thread downLoadThread;
    //终止标记
    private boolean interceptFlag;
    //提示语
    private String updateMsg = "";
    //返回的安装包url
    private String apkUrl = "";
    //下载包保存路径
    private String savePath = "";
    //apk保存完整路径
    private String apkFilePath = "";
    //临时下载文件路径
    private String tmpFilePath = "";
    //下载文件大小
    private String apkFileSize;
    //已下载文件大小
    private String tmpFileSize;

    private String curVersionName = "";
    private int curVersionCode;
    private CheckModel.DataBean mUpdate;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWN_UPDATE:
                    mProgress.setProgress(progress);
                    mProgressText.setText(tmpFileSize + "/" + apkFileSize);
                    break;
                case DOWN_OVER:
                    if (downloadDialog != null) {
                        downloadDialog.dismiss();
                    }
                    Log.i("apkFilePath", apkFilePath);
                    installApk();
                    break;
                case DOWN_NOSDCARD:
                    downloadDialog.dismiss();
                    Toast.makeText(mContext, "无法下载安装文件，请检查SD卡是否挂载", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        ;
    };

    public static UpdateManager getUpdateManager() {
        if (updateManager == null) {
            updateManager = new UpdateManager();
        }
        updateManager.interceptFlag = false;
        return updateManager;
    }

    /**
     * 检查App更新
     *
     * @param context
     * @param isShowMsg 是否显示提示消息
     */
    public void checkAppUpdate(Context context, final boolean isShowMsg, final CheckModel.DataBean update) {
        this.mContext = context;
        getCurrentVersion();
        if (isShowMsg) {
            if (mProDialog == null)
                mProDialog = ProgressDialog.show(mContext, null, "正在检测，请稍后...", true, true);
            else if (mProDialog.isShowing() || (latestOrFailDialog != null && latestOrFailDialog.isShowing()))
                return;
        }
        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                //进度条对话框不显示 - 检测结果也不显示
                if (mProDialog != null && !mProDialog.isShowing()) {
                    return;
                }
                //关闭并释放释放进度条对话框
                if (isShowMsg && mProDialog != null) {
                    mProDialog.dismiss();
                    mProDialog = null;
                }
                //显示检测结果
                if (msg.what == 1) {
                    mUpdate = (CheckModel.DataBean) msg.obj;
                    if (mUpdate != null) {
//                        if (curVersionCode < mUpdate.versionCode) {
//                            apkUrl = mUpdate.downloadUrl;
//                            updateMsg = mUpdate.updateLog;
//                            showNoticeDialog(mUpdate.forceUpdate);
//                        } else if (isShowMsg) {
//                            showLatestOrFailDialog(DIALOG_TYPE_LATEST);
//                        }
                        //判断是否需要更新
                        if (compareVersions(curVersionName, mUpdate.getVersion_no()) < 0) {
                            apkUrl = mUpdate.getVersion_down_url();
                            updateMsg = mUpdate.getVersion_text();
                            showNoticeDialog(mUpdate.getVersion_update());
                        } else if (isShowMsg) {
                            showLatestOrFailDialog(DIALOG_TYPE_LATEST);
                        }

                    }
                } else if (isShowMsg) {
                    showLatestOrFailDialog(DIALOG_TYPE_FAIL);
                }
            }
        };
        new Thread() {
            public void run() {
                Message msg = new Message();
            /*	try {
                DeviceTypeDtoOut update = new DeviceTypeDtoOut();
                update.versionName = "1.0.2";
                update.versionCode = 2;
                update.updateLog = "爱鲜蜂";
                update.downloadUrl = ("http://219.147.148.145/dd.myapp.com/16891/4C374F4F4536B1B689A094637D418E35.apk?mkey=56a80d46d9ef8d2b&f=2320&fsname=com.bjzcht.lovebeequick_2.7.1_186.apk&p=.apk");
              */
                msg.what = 1;
                msg.obj = update;
                handler.sendMessage(msg);
            }
        }.start();
    }

    /**
     * 显示'已经是最新'或者'无法获取版本信息'对话框
     */
    private void showLatestOrFailDialog(int dialogType) {
        if (latestOrFailDialog != null) {
            //关闭并释放之前的对话框
            latestOrFailDialog.dismiss();
            latestOrFailDialog = null;
        }
        Builder builder = new Builder(mContext);
        builder.setTitle("系统提示");
        if (dialogType == DIALOG_TYPE_LATEST) {
            builder.setMessage("您当前已经是最新版本");
        } else if (dialogType == DIALOG_TYPE_FAIL) {
            builder.setMessage("无法获取版本更新信息");
        }
        builder.setPositiveButton("确定", null);
        latestOrFailDialog = builder.create();
        latestOrFailDialog.show();
    }

    /**
     * 获取当前客户端版本信息
     */
    private void getCurrentVersion() {
        try {
            PackageInfo info = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            curVersionName = info.versionName;
            curVersionCode = info.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
    }

    /**
     * 比较两个版本号
     *
     * @return 0 相等，1 大于，-1 小于
     */
    private int compareVersions(String ver1, String ver2) {
        try {
            int iver1 = Integer.parseInt(ver1.replace(".", ""));
            int iver2 = Integer.parseInt(ver2.replace(".", ""));
            if (iver1 != iver2) {
                return iver1 > iver2 ? 1 : -1;
            }
        } catch (Exception e) {
            LogUtil.e("compareVersions, ver1=" + ver1 + ", ver2=" + ver2 + ", e=" + e.getLocalizedMessage());
        }
        return 0;
    }

    /**
     * 显示版本更新通知对话框
     *
     * @param forceUpdate
     */
    private void showNoticeDialog(String forceUpdate) {
        Builder builder = new Builder(mContext);
        builder.setTitle("版本更新");
        builder.setMessage(updateMsg);
        builder.setCancelable(false);

        if ("2".equals(forceUpdate)) {
            builder.setPositiveButton("立即更新", new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                    //检测更新
                    RxPermissions rxPermissions = new RxPermissions((Activity) mContext);
                    rxPermissions.request(App.BASIC_PERMISSIONS_STORAGE).subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean granted) {
                            if (granted) { // 在android 6.0之前会默认返回true
                                showDownloadDialog();
                            } else {
                                Toast.makeText(mContext, "该应用需要赋予存储权限，不开启将无法正常工作！", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }
            });
        } else if ("1".equals(forceUpdate)) {
            builder.setPositiveButton("立即更新", new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();

                    //检测更新
                    RxPermissions rxPermissions = new RxPermissions((Activity) mContext);
                    rxPermissions.request(App.BASIC_PERMISSIONS_STORAGE).subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean granted) {
                            if (granted) { // 在android 6.0之前会默认返回true
                                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                                    showDownloadDialog();
                                } else {
                                    downloadManager();
                                }
                            } else {
                                Toast.makeText(mContext, "该应用需要赋予存储权限，不开启将无法正常工作！", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }
            });
            builder.setNegativeButton("以后再说", new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        noticeDialog = builder.create();
        noticeDialog.show();
    }

    /**
     * 显示下载对话框
     */
    private void showDownloadDialog() {
        Builder builder = new Builder(mContext);
        builder.setTitle("正在下载新版本");
        builder.setCancelable(false);
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.update_progress, null);
        mProgress = (ProgressBar) v.findViewById(R.id.update_progress);
        mProgressText = (TextView) v.findViewById(R.id.update_progress_text);
        builder.setView(v);
        downloadDialog = builder.create();
        downloadDialog.setCanceledOnTouchOutside(false);
        downloadDialog.show();
        downloadApk();
    }

    private Runnable mdownApkRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                String apkName = "JYJApp_" + mUpdate.getVersion_no() + ".apk";
                String tmpApk = "JYJApp_" + mUpdate.getVersion_no() + ".tmp";
                //判断是否挂载了SD卡
                String storageState = Environment.getExternalStorageState();
                if (storageState.equals(MEDIA_MOUNTED)) {

//                    if (Build.VERSION.SDK_INT >= 29) {
////Android10之后
//                        savePath = mContext.getExternalFilesDir(null).getAbsolutePath();
//                    } else {
//                        savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + AppConfig.EXTERNAL_DIR;
//                    }
                    savePath = mContext.getExternalFilesDir(null).getAbsolutePath();
                    File file = new File(savePath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }

                }else {
                    savePath = mContext.getFilesDir().getPath() ;
                }

                apkFilePath = savePath + apkName;
                tmpFilePath = savePath + tmpApk;

                //没有挂载SD卡，无法下载文件
                if (apkFilePath == null || apkFilePath == "") {
                    mHandler.sendEmptyMessage(DOWN_NOSDCARD);
                    return;
                }

                File ApkFile = new File(apkFilePath);
//                //是否已下载更新文件
//                if (ApkFile.exists()) {
//                    downloadDialog.dismiss();
//                    installApk();
//                    return;
//                }

                //输出临时下载文件
                File tmpFile = new File(tmpFilePath);
                FileOutputStream fos = new FileOutputStream(tmpFile);

                int randomnum = (int) (Math.random() * 100);

                String randomnum_str = String.format("?d=%s", randomnum);

                LogUtil.i("下载路径：" + apkUrl + randomnum_str);

                URL url = new URL(apkUrl + randomnum_str);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();
                int length = conn.getContentLength();
                InputStream is = conn.getInputStream();

                //显示文件大小格式：2个小数点显示
                DecimalFormat df = new DecimalFormat("0.00");
                //进度条下面显示的总文件大小
                apkFileSize = df.format((float) length / 1024 / 1024) + "MB";

                int count = 0;
                byte buf[] = new byte[1024];

                do {
                    int numread = is.read(buf);
                    count += numread;
                    //进度条下面显示的当前下载文件大小
                    tmpFileSize = df.format((float) count / 1024 / 1024) + "MB";
                    //当前进度值
                    progress = (int) (((float) count / length) * 100);
                    //更新进度
                    mHandler.sendEmptyMessage(DOWN_UPDATE);
                    if (numread <= 0) {
                        //下载完成 - 将临时下载文件转成APK文件
                        if (tmpFile.renameTo(ApkFile)) {
                            //通知安装
                            mHandler.sendEmptyMessage(DOWN_OVER);
                        }
                        break;
                    }
                    fos.write(buf, 0, numread);
                } while (!interceptFlag);//点击取消就停止下载

                fos.close();
                is.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };

    /**
     * 下载apk
     */
    private void downloadApk() {
        downLoadThread = new Thread(mdownApkRunnable);
        downLoadThread.start();
    }

    /**
     * 安装apk
     */
    private void installApk() {
        Intent instal = new Intent(Intent.ACTION_VIEW);
        // 由于没有在Activity环境下启动Activity,设置下面的标签
        // 这里加上 FLAG_GRANT_READ_URI_PERMISSION ，给目标程序读改uri的权限。
        instal.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) { //判读版本是否在7.0以上
            File file = new File(apkFilePath);
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri = FileProvider.getUriForFile(mContext, "com.smarthome.magic.fileprovider", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            instal.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            instal.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            instal.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            Uri uri = Uri.fromFile(new File(apkFilePath));
            instal.setDataAndType(uri, "application/vnd.android.package-archive");
        }
        mContext.startActivity(instal);
    }


    private void downloadManager() {
        DownloadManager dManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(apkUrl);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        String apkName = "JYJApp_" + mUpdate.getVersion_no() + ".apk";
        String tmpApk = "JYJApp_" + mUpdate.getVersion_no() + ".tmp";

        //判断是否挂载了SD卡
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(MEDIA_MOUNTED)) {
            savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + AppConfig.EXTERNAL_DIR;
            File file = new File(savePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            apkFilePath = savePath + apkName;
            tmpFilePath = savePath + tmpApk;
        }

        //没有挂载SD卡，无法下载文件
        if (apkFilePath == null || apkFilePath == "") {
            downloadDialog.dismiss();
            Toast.makeText(mContext, "无法下载安装文件，请检查SD卡是否挂载", Toast.LENGTH_SHORT).show();
            return;
        }
        File ApkFile = new File(apkFilePath);
        //是否已下载更新文件
        if (ApkFile.exists()) {
            if (downloadDialog != null) {
                downloadDialog.dismiss();
            }
            installApk();
            return;
        }

        // 表示设置下载地址为sd卡的download文件夹，文件名为time2plato.apk。
        //      request.setDestinationInExternalPublicDir("download","time2plato.apk");

//        request.setDestinationInExternalFilesDir(mContext, savePath, apkName);

        //设置文件存放路径
        request.setDestinationInExternalPublicDir(savePath, apkName);

        request.setDescription("新版本下载");

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);//VISIBILITY_VISIBLE_NOTIFY_COMPLETED

        request.setMimeType("application/vnd.android.package-archive");

        // 设置为可被媒体扫描器找到

        request.allowScanningByMediaScanner();

        // 设置为可见和可管理

        request.setVisibleInDownloadsUi(true);

        //long dbManagerId = dManager.enqueue(request);
        //将id放进Intent
//        Intent downLoadApkService = new Intent(mContext, DownLoadApkService.class);
//        downLoadApkService.putExtra("id", String.valueOf(dbManagerId));
//        downLoadApkService.putExtra("path", apkFilePath);
//        mContext.startService(downLoadApkService);

        UIHelper.ToastMessage(mContext,"系统正在下载和安装最新版本");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();


                try {
                    String apkName = "JYJApp_" + mUpdate.getVersion_no() + ".apk";
                    String tmpApk = "JYJApp_" + mUpdate.getVersion_no() + ".tmp";
                    //判断是否挂载了SD卡
                    String storageState = Environment.getExternalStorageState();
                    if (storageState.equals(MEDIA_MOUNTED)) {
                        savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + AppConfig.EXTERNAL_DIR;
                        File file = new File(savePath);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        apkFilePath = savePath + apkName;
                        tmpFilePath = savePath + tmpApk;
                    }

                    //没有挂载SD卡，无法下载文件
                    if (apkFilePath == null || apkFilePath == "") {
                        mHandler.sendEmptyMessage(DOWN_NOSDCARD);
                        return;
                    }

                    File ApkFile = new File(apkFilePath);
//                //是否已下载更新文件
//                if (ApkFile.exists()) {
//                    downloadDialog.dismiss();
//                    installApk();
//                    return;
//                }

                    //输出临时下载文件
                    File tmpFile = new File(tmpFilePath);
                    FileOutputStream fos = new FileOutputStream(tmpFile);

                    int randomnum = (int) (Math.random() * 100);

                    String randomnum_str = String.format("?d=%s", randomnum);

                    LogUtil.i("下载路径：" + apkUrl + randomnum_str);

                    URL url = new URL(apkUrl + randomnum_str);

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();
                    int length = conn.getContentLength();
                    InputStream is = conn.getInputStream();

                    //显示文件大小格式：2个小数点显示
                    DecimalFormat df = new DecimalFormat("0.00");
                    //进度条下面显示的总文件大小
                    apkFileSize = df.format((float) length / 1024 / 1024) + "MB";

                    int count = 0;
                    byte buf[] = new byte[1024];

                    do {
                        int numread = is.read(buf);
                        count += numread;
                        //进度条下面显示的当前下载文件大小
                        tmpFileSize = df.format((float) count / 1024 / 1024) + "MB";
                        //当前进度值
                        progress = (int) (((float) count / length) * 100);
                        //更新进度
                        //  mHandler.sendEmptyMessage(DOWN_UPDATE);
                        if (numread <= 0) {
                            //下载完成 - 将临时下载文件转成APK文件
                            if (tmpFile.renameTo(ApkFile)) {
                                //通知安装
                                mHandler.sendEmptyMessage(DOWN_OVER);
                            }
                            break;
                        }
                        fos.write(buf, 0, numread);
                    } while (!interceptFlag);//点击取消就停止下载

                    Looper.loop();
                } catch (Exception e) {
                }
            }

        }).start();
    }


    /**
     * 删除单个文件
     *
     * @param filePath 被删除文件的文件名
     * @return 文件删除成功返回true，否则返回false
     */
    public boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * 删除文件夹以及目录下的文件
     *
     * @param filePath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public boolean deleteDirectory(String filePath) {
        boolean flag = false;
        //如果filePath不以文件分隔符结尾，自动添加文件分隔符
        if (!filePath.endsWith(File.separator)) {
            filePath = filePath + File.separator;
        }
        File dirFile = new File(filePath);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        flag = true;
        File[] files = dirFile.listFiles();
        //遍历删除文件夹下的所有文件(包括子目录)
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                //删除子文件
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } else {
                //删除子目录
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前空目录
        return dirFile.delete();
    }

    /**
     * 根据路径删除指定的目录或文件，无论存在与否
     *
     * @param filePath 要删除的目录或文件
     * @return 删除成功返回 true，否则返回 false。
     */
    public boolean DeleteFolder(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        } else {
            if (file.isFile()) {
                // 为文件时调用删除文件方法
                return deleteFile(filePath);
            } else {
                // 为目录时调用删除目录方法
                return deleteDirectory(filePath);
            }
        }
    }


}
