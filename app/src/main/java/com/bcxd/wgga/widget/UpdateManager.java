package com.bcxd.wgga.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.bcxd.wgga.BuildConfig;
import com.bcxd.wgga.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by lidengyao on 2017-01-11 0011.
 */

public class UpdateManager {

    /* 下载中 */
    private static final int DOWNLOAD = 1;
    /* 下载结束 */
    private static final int DOWNLOAD_FINISH = 2;
    /* 保存解析的XML信息 */
    HashMap<String, String> mHashMap;
    /* 下载保存路径 */
    private String mSavePath;
    /* 下载路径 */
    private String AppUrl;
    /* 下载文件名称 */
    private String AppName = "智慧工地";
    ;
    /* 记录进度条数量 */
    private int progress;
    /* 是否取消更新 */
    private boolean cancelUpdate = false;

    private Context mContext;
    /* 更新进度条 */
    private ProgressBar mProgress;
    private Dialog mDownloadDialog;
    private boolean force = false;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                // 正在下载
                case DOWNLOAD:
                    // 设置进度条位置
                    mProgress.setProgress(progress);
                    break;
                case DOWNLOAD_FINISH:
                    // 安装文件
                    installApk();
                    break;
                default:
                    break;
            }
        }

        ;
    };

    public UpdateManager(Context context) {
        this.mContext = context;
    }

    /**
     * 检测软件更新
     */
    public void checkUpdate(String appVersion, String appAddress, String VersionName) {
        if (isUpdate(appVersion, appAddress)) {
            // 显示提示对话框
            showNoticeDialog();
        } else {
            Toast.makeText(mContext, "没有检测到新版本", Toast.LENGTH_LONG).show();
        }
    }

    public void forceCheckUpdate(String appAddress ) {
        AppUrl = appAddress;
        force = true;
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setCancelable(false);
        builder.setTitle("软件更新").setMessage("检测到新版本，立即更新吗?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showDownloadDialog();
                dialog.dismiss();
            }
        }).show();
    }

    public void noForceCheckUpdate(String appAddress ) {
        AppUrl = appAddress;
        force = false;
//        showNoticeDialogNoForce();
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("软件更新").setMessage("检测到新版本，立即更新吗?").setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showDownloadDialog();
                dialog.dismiss();
            }
        }).show();
    }

    /**
     * 检查软件是否有更新版本
     *
     * @return
     */
    private boolean isUpdate(String AppVersion, String appAddress) {
        // 获取当前软件版本
        int versionCode = getVersionCode(mContext);
        AppUrl = appAddress;
        int AppVersionCode = Integer.parseInt(AppVersion);

        // 版本判断
        if (AppVersionCode > versionCode) {
            return true;
        }
        return false;
    }

    /**
     * 获取软件版本号
     *
     * @param context
     * @return
     */
    private int getVersionCode(Context context) {
        int versionCode = 0;
        versionCode = BuildConfig.VERSION_CODE;
//        try {
//            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
//            versionCode = context.getPackageManager().getPackageInfo(
//                    "com.example.xsjyk", 0).versionCode;
//            PublicData.showVersionNameString = context.getPackageManager()
//                    .getPackageInfo("com.example.xsjyk", 0).versionName;
//        } catch (NameNotFoundException e) {
//            e.printStackTrace();
//        }
        return versionCode;
    }

    /**
     * 显示软件更新对话框
     */
    private void showNoticeDialog() {
//        // 构造对话框


        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("软件更新").setMessage("检测到新版本，立即更新吗?").setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showDownloadDialog();
                dialog.dismiss();
            }
        }).show();
    }


    /**
     * 显示软件更新对话框
     */
    private void showNoticeDialogForce() {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setCancelable(false);
        builder.setTitle("软件更新").setMessage("检测到新版本，立即更新吗?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showDownloadDialog();
                dialog.dismiss();
            }
        }).show();
    }

    /**
     * 显示软件更新对话框
     */
    private void showNoticeDialogNoForce() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("软件更新").setMessage("检测到新版本，立即更新吗?").setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showDownloadDialog();
                dialog.dismiss();
            }
        }).show();
    }

    /**
     * 显示软件下载对话框
     */
    private void showDownloadDialog() {
        // 构造软件下载对话框

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.z_softupdate_progress, null);

        mProgress = (ProgressBar) v.findViewById(R.id.update_progress);
        TextView codeTV = (TextView) v.findViewById(R.id.VersionCodeDataTV);
        LinearLayout zhanweiLL = (LinearLayout) v.findViewById(R.id.ZhanWeiLL);


        builder.setView(v);
        if (force == false) {
            zhanweiLL.setVisibility(View.GONE);
            builder.setTitle("").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
//                        // 设置取消状态
                    cancelUpdate = true;
                }
            });
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                showDownloadDialog();
//                dialog.dismiss();
//            }
//        })

        } else {
            builder.setCancelable(false);
        }


        // 现在文件
        mDownloadDialog = builder.create();
        mDownloadDialog.show();
        downloadApk();
    }

    /**
     * 下载apk文件
     */
    private void downloadApk() {
        // 启动新线程下载软件
        new downloadApkThread().start();
    }

    /**
     * 下载文件线程
     *
     * @author coolszy
     * @date 2012-4-26
     * @blog http://blog.92coding.com
     */
    private class downloadApkThread extends Thread {
        @Override
        public void run() {
            try {
                // 判断SD卡是否存在，并且是否具有读写权限
                if (Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)) {
                    // 获得存储卡的路径
                    String sdpath = Environment.getExternalStorageDirectory()
                            + "/";
                    mSavePath = sdpath + "download";
                    URL url = new URL(AppUrl);
                    // 创建连接
                    HttpURLConnection conn = (HttpURLConnection) url
                            .openConnection();
                    conn.connect();
                    // 获取文件大小
                    int length = conn.getContentLength();
                    // 创建输入流
                    InputStream is = conn.getInputStream();

                    File file = new File(mSavePath);
                    // 判断文件目录是否存在
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    File apkFile = new File(mSavePath, AppName);
                    FileOutputStream fos = new FileOutputStream(apkFile);
                    int count = 0;
                    // 缓存
                    byte buf[] = new byte[1024];
                    // 写入到文件中
                    do {
                        int numread = is.read(buf);
                        count += numread;
                        // 计算进度条位置
                        progress = (int) (((float) count / length) * 100);
                        // 更新进度
                        mHandler.sendEmptyMessage(DOWNLOAD);
                        if (numread <= 0) {
                            // 下载完成
                            mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                            break;
                        }
                        // 写入文件
                        fos.write(buf, 0, numread);
                    } while (!cancelUpdate);// 点击取消就停止下载.
                    fos.close();
                    is.close();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 取消下载对话框显示
            mDownloadDialog.dismiss();
        }
    }

    ;

    /**
     * 安装APK文件
     */
    private void installApk() {

        File apkfile = new File(mSavePath, AppName);
//        if (!apkfile.exists()) {
//            return;
//        }
//        // 通过Intent安装APK文件
//        Intent i = new Intent(Intent.ACTION_VIEW);
//        i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
//                "application/vnd.android.package-archive");
//        mContext.startActivity(i);

        Uri fileUri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            fileUri = FileProvider.getUriForFile(mContext, "com.bcxd.wgga.fileProvider",
                    apkfile);
        } else {
            fileUri = Uri.fromFile(apkfile);
        }
        Intent installIntent = new Intent(Intent.ACTION_VIEW);
        installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        installIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        installIntent.setAction(Intent.ACTION_VIEW);
        installIntent.setDataAndType(fileUri,
                "application/vnd.android.package-archive");
        mContext.startActivity(installIntent);

    }

}
