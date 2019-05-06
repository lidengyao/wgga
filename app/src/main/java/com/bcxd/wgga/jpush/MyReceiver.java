package com.bcxd.wgga.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.bcxd.wgga.model.Inf.JPushReceiverInf;
import com.bcxd.wgga.ui.activity.Z_BaojingChuLi_Activity;
import com.bcxd.wgga.ui.activity.Z_ChuLiJianCha_Activity;
import com.bcxd.wgga.ui.activity.Z_Login_Activity;
import com.bcxd.wgga.ui.activity.Z_Main_Sec_Activity;
import com.bcxd.wgga.ui.activity.Z_ZhengGaiChuLi_Activity;
import com.bcxd.wgga.utils.DbKeyS;
import com.bcxd.wgga.utils.SpUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "JIGUANG-Example";

    //0:居民列表1：居民详情2:随访界面（小护医疗界面）
    public static int TYPE = -1;
    public static JPushReceiverInf _JuMinLieBiaojPushReceiverInf;
    public static JPushReceiverInf _JuMinXiangQingjPushReceiverInf;
    public static JPushReceiverInf _XiaoHuYiLiaojPushReceiverInf;
    public static Context _context;

    public static void init(Context context, JPushReceiverInf jPushReceiverInf) {
        _context = context;
        if (TYPE == 0) {
            _JuMinLieBiaojPushReceiverInf = jPushReceiverInf;
        }
        if (TYPE == 1) {
            _JuMinXiangQingjPushReceiverInf = jPushReceiverInf;
        }
        if (TYPE == 2) {
            _XiaoHuYiLiaojPushReceiverInf = jPushReceiverInf;
        }

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Bundle bundle = intent.getExtras();
            String title = bundle.getString(JPushInterface.EXTRA_TITLE);
            String messaget = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            String title2 = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            String content = bundle.getString(JPushInterface.EXTRA_ALERT);
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            String fileHtml = bundle.getString(JPushInterface.EXTRA_RICHPUSH_HTML_PATH);

            Log.e("onReceive", "--------------------------------------------------");
            Log.e("onReceive", "title : " + title);
            Log.e("onReceive", "title2 : " + title2);
            Log.e("onReceive", "message: " + messaget);
            Log.e("onReceive", "content: 测试有值 " + content);
            Log.e("onReceive", "extras: " + extras);
            Log.e("onReceive", "fileHtml: " + fileHtml);

            Logger.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
                String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
                Logger.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
                //send the Registration Id to your server...

            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
                Logger.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
                processCustomMessage(context, bundle);
                intent.putExtra(JPushInterface.EXTRA_MESSAGE, bundle.getString(JPushInterface.EXTRA_MESSAGE));

                String EXTRA_MESSAGE = bundle.getString(JPushInterface.EXTRA_MESSAGE);
                Gson gson = new Gson();
                EXTRA_MESSAGE extra_message = gson.fromJson(EXTRA_MESSAGE, EXTRA_MESSAGE.class);

                //实现单点登录
                if (extra_message.getMessage().equals("账号已在别处登录")) {
                    JPushInterface.stopPush(context);
                    SpUtils.saveSettingNote(context, DbKeyS.status, "0");
                    Intent intentLogin = new Intent(context, Z_Login_Activity.class);
                    intentLogin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intentLogin);
                    Toast.makeText(context, "您的账号已经在其他设备上登录了", Toast.LENGTH_LONG).show();
                }

            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
                Logger.d(TAG, "[MyReceiver] 接收到推送下来的通知");
                int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
                Logger.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
//                if (content.equals("账号已在别处登录")) {
//                    JPushInterface.stopPush(context);
//                    Intent intentLogin = new Intent(context, Z_Login_Activity.class);
//                    intentLogin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    context.startActivity(intentLogin);
//                    Toast.makeText(context, "您的账号已经在其他设备上登录了", Toast.LENGTH_LONG).show();
//                }

            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                Logger.d(TAG, "[MyReceiver] 用户点击打开了通知");

                //打开自定义的Activity
//                Intent i = new Intent(context, TestActivity.class);
//                i.putExtras(bundle);
//                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                context.startActivity(i);


                Gson gsonZU = new Gson();
                String EXTRA_EXTRA = bundle.getString(JPushInterface.EXTRA_EXTRA);
                MyReceiverClass myReceiverClass = gsonZU.fromJson(EXTRA_EXTRA, MyReceiverClass.class);
                int btype = myReceiverClass.getBtype();
                int bid = myReceiverClass.getBid();
                String message = myReceiverClass.getMessage();

                //检查通知
                if (btype == 1) {
                    Logger.d(TAG, "检查通知" + bid);
//                    Toast.makeText(_context, "检查通知bid" + bid, Toast.LENGTH_LONG).show();
                    Intent i = new Intent(context, Z_ChuLiJianCha_Activity.class);
                    i.putExtra("btype", btype + "");
                    i.putExtra("bid", bid + "");
                    i.putExtras(bundle);
                    //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);

                }

                //整改通知
                if (btype == 2 && !message.equals("账号已在别处登录")) {
                    Logger.d(TAG, "整改通知" + bid);
//                    Toast.makeText(_context, "整改通知" + bid, Toast.LENGTH_LONG).show();
                    Intent i = new Intent(context, Z_ZhengGaiChuLi_Activity.class);
                    i.putExtra("btype", btype + "");
                    i.putExtra("bid", bid + "");
                    i.putExtras(bundle);
                    //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);

                }

                //报警通知
                if (btype == 3) {
                    Logger.d(TAG, "报警通知" + bid);
//                    Toast.makeText(_context, "报警通知" + bid, Toast.LENGTH_LONG).show();
                    Intent i = new Intent(context, Z_BaojingChuLi_Activity.class);
                    i.putExtra("btype", btype + "");
                    i.putExtra("bid", bid + "");
                    i.putExtras(bundle);
                    //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                }


            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
                Logger.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
                Logger.w(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
            } else {
                Logger.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
            }
        } catch (Exception e) {
            Logger.w(TAG, "[Exception]" + intent.getAction() + e.getMessage());
        }

    }

    public class MyReceiverClass {
        private int bid;

        //1:检查通知，2:整改通知3：环境报警
        private int btype;
        private String message;

        public int getBid() {
            return bid;
        }

        public void setBid(int bid) {
            this.bid = bid;
        }

        public int getBtype() {
            return btype;
        }

        public void setBtype(int btype) {
            this.btype = btype;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    Logger.i(TAG, "This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Logger.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.get(key));
            }
        }
        return sb.toString();
    }

    //send msg to MainActivity
    private void processCustomMessage(Context context, Bundle bundle) {
        if (Z_Main_Sec_Activity.isForeground) {
            String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            Intent msgIntent = new Intent(Z_Main_Sec_Activity.MESSAGE_RECEIVED_ACTION);
            msgIntent.putExtra(Z_Main_Sec_Activity.KEY_MESSAGE, message);
            if (!ExampleUtil.isEmpty(extras)) {
                try {
                    JSONObject extraJson = new JSONObject(extras);
                    if (extraJson.length() > 0) {
                        msgIntent.putExtra(Z_Main_Sec_Activity.KEY_EXTRAS, extras);
                    }
                } catch (JSONException e) {

                }

            }
            LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
        }
    }

    public class EXTRA_MESSAGE {
        //{"bid":0,"btype":2,"message":"账号已在别处登录"}
        public int bid;
        public int btype;
        public String message;

        public int getBid() {
            return bid;
        }

        public void setBid(int bid) {
            this.bid = bid;
        }

        public int getBtype() {
            return btype;
        }

        public void setBtype(int btype) {
            this.btype = btype;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}
