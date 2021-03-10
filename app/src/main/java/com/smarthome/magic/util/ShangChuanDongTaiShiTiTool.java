package com.smarthome.magic.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.iflytek.aiui.AIUIAgent;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.AIUIEvent;
import com.iflytek.aiui.AIUIListener;
import com.iflytek.aiui.AIUIMessage;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.smarthome.magic.app.AppConfig;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.app.RxBus;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.common.StringUtils;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.model.DongTaiShiTiModel;
import com.smarthome.magic.model.ResultModel;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static com.smarthome.magic.activity.shuinuan.Y.getResources;
import static com.smarthome.magic.config.MyApplication.CAR_NOTIFY;

public class ShangChuanDongTaiShiTiTool {

    public String TAG = ShangChuanDongTaiShiTiTool.class.getSimpleName();
    private AIUIAgent mAIUIAgent = null;
    private int mAIUIState = AIUIConstant.STATE_IDLE;
    private String mSyncSid = "";
    String uid;
    private Context context;

    List<String> roomList = new ArrayList<>();
    List<String> sheBeiList = new ArrayList<>();

    public ShangChuanDongTaiShiTiTool(Context context, List<String> roomList, List<String> sheBeiList) {
        this.context = context;
        this.roomList = roomList;
        this.sheBeiList = sheBeiList;
        createAgent();
    }

    private void showTip(final String str) {
    }


    private void createAgent() {
        if (null == mAIUIAgent) {
            Log.i(TAG, "create aiui agent");
            mAIUIAgent = AIUIAgent.createAgent(context, getAIUIParams(), mAIUIListener);
        }

        if (null == mAIUIAgent) {
            final String strErrorTip = "创建AIUIAgent失败！";
            showTip(strErrorTip);
            //  mNlpText.setText(strErrorTip);
        } else {
            showTip("AIUIAgent已创建");
        }
    }

    private String getAIUIParams() {
        String params = "";

        AssetManager assetManager = getResources().getAssets();
        try {
            InputStream ins = assetManager.open("cfg/aiui_phone.cfg");
            byte[] buffer = new byte[ins.available()];

            ins.read(buffer);
            ins.close();

            params = new String(buffer);

            JSONObject paramsJson = new JSONObject(params);
            //paramsJson.put("pers_param", "{\"uid\":\"d3b6d50a9f8194b623b5e2d4e298c9d6\"}");
            params = paramsJson.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return params;
    }

    public void syncContactsSheBei(List<String> sheBeiList) {
        if (null == mAIUIAgent) {
            showTip("AIUIAgent 为空，请先创建");
            return;
        }
        // TODO: 2021/2/22 66666
        //经过研究 初步核实认定 是数据问题 数据对了就可以上传成功
        //明天主要研究数据 对应的键值对是什么

        try {
            // 从文件中读取联系人示例数据
            //String dataStr = FucUtil.readFile(context, "data/data_contact.txt", "utf-8");
            //mNlpText.setText(dataStr);
            //UIHelper.ToastMessage(context, dataStr);

//            DongTaiShiTiModel dongTaiShiTiModel = new DongTaiShiTiModel();
//            dongTaiShiTiModel.setName("大熊猫");
//
//            // dongTaiShiTiModel.setCus_room("客厅");
//            String str = new Gson().toJson(dongTaiShiTiModel);
//            Log.i("YuYinChuLiTool", str);

            ShuJuJieXiZhuanHuaTool shuJuJieXiZhuanHuaTool = new ShuJuJieXiZhuanHuaTool(sheBeiList);
            String str = shuJuJieXiZhuanHuaTool.jieXiShuJu();

            // 数据进行no_wrap Base64编码
            String dataStrBase64 = Base64.encodeToString(str.getBytes("utf-8"), Base64.NO_WRAP);

            JSONObject syncSchemaJson = new JSONObject();
            JSONObject dataParamJson = new JSONObject();

            // 设置id_name为uid，即用户级个性化资源
            // 个性化资源使用方法可参见http://doc.xfyun.cn/aiui_mobile/的用户个性化章节
            dataParamJson.put("id_name", "uid");
            if (StringUtils.isEmpty(uid)) {
                // UIHelper.ToastMessage(context, "uid不能为空");
                return;
            }
            dataParamJson.put("id_value", uid);

            // 设置res_name为联系人
            dataParamJson.put("res_name", "OS8569425439.app_device_name");

            syncSchemaJson.put("param", dataParamJson);
            syncSchemaJson.put("data", dataStrBase64);


            Log.i(TAG, "上传的设备名：  " + syncSchemaJson.toString());
            // 传入的数据一定要为utf-8编码
            byte[] syncData = syncSchemaJson.toString().getBytes("utf-8");

            // 给该次同步加上自定义tag，在返回结果中可通过tag将结果和调用对应起来
            JSONObject paramJson = new JSONObject();
            paramJson.put("tag", "sync-tag1");

            // 用schema数据同步上传联系人
            // 注：数据同步请在连接服务器之后进行，否则可能失败
            AIUIMessage syncAthena = new AIUIMessage(AIUIConstant.CMD_SYNC,
                    AIUIConstant.SYNC_DATA_SCHEMA, 0, paramJson.toString(), syncData);

            mAIUIAgent.sendMessage(syncAthena);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void syncContactsRoom(List<String> roomList) {
        if (null == mAIUIAgent) {
            showTip("AIUIAgent 为空，请先创建");
            return;
        }
        // TODO: 2021/2/22 66666
        //经过研究 初步核实认定 是数据问题 数据对了就可以上传成功
        //明天主要研究数据 对应的键值对是什么

        try {
            // 从文件中读取联系人示例数据
            //String dataStr = FucUtil.readFile(context, "data/data_contact.txt", "utf-8");
            //mNlpText.setText(dataStr);
            //UIHelper.ToastMessage(context, dataStr);

//            DongTaiShiTiModel dongTaiShiTiModel = new DongTaiShiTiModel();
//            dongTaiShiTiModel.setName("咸鸭蛋");
//
//            // dongTaiShiTiModel.setCus_room("客厅");
//            String str = new Gson().toJson(dongTaiShiTiModel);
//            Log.i("YuYinChuLiTool", str);

            ShuJuJieXiZhuanHuaTool shuJuJieXiZhuanHuaTool = new ShuJuJieXiZhuanHuaTool(roomList);
            String str = shuJuJieXiZhuanHuaTool.jieXiShuJu();


            // 数据进行no_wrap Base64编码
            String dataStrBase64 = Base64.encodeToString(str.getBytes("utf-8"), Base64.NO_WRAP);

            JSONObject syncSchemaJson = new JSONObject();
            JSONObject dataParamJson = new JSONObject();

            // 设置id_name为uid，即用户级个性化资源
            // 个性化资源使用方法可参见http://doc.xfyun.cn/aiui_mobile/的用户个性化章节
            dataParamJson.put("id_name", "uid");
            if (StringUtils.isEmpty(uid)) {
                // UIHelper.ToastMessage(context, "uid不能为空");
                return;
            }
            dataParamJson.put("id_value", uid);

            // 设置res_name为联系人
            dataParamJson.put("res_name", "OS8569425439.app_room");

            syncSchemaJson.put("param", dataParamJson);
            syncSchemaJson.put("data", dataStrBase64);


            Log.i(TAG, "上传的房间名：  " + syncSchemaJson.toString());
            // 传入的数据一定要为utf-8编码
            byte[] syncData = syncSchemaJson.toString().getBytes("utf-8");

            // 给该次同步加上自定义tag，在返回结果中可通过tag将结果和调用对应起来
            JSONObject paramJson = new JSONObject();
            paramJson.put("tag", "sync-tag");

            // 用schema数据同步上传联系人
            // 注：数据同步请在连接服务器之后进行，否则可能失败
            AIUIMessage syncAthena = new AIUIMessage(AIUIConstant.CMD_SYNC,
                    AIUIConstant.SYNC_DATA_SCHEMA, 0, paramJson.toString(), syncData);

            mAIUIAgent.sendMessage(syncAthena);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String zhiXingYiCi = "1";

    private AIUIListener mAIUIListener = new AIUIListener() {

        @Override
        public void onEvent(AIUIEvent event) {
            Log.i(TAG, "on event: " + event.eventType);

            switch (event.eventType) {
                case AIUIConstant.EVENT_CONNECTED_TO_SERVER:
                    uid = event.data.getString("uid");
                    Log.i("YuYinCHuLiTool_Uid:", uid);
                    showTip("已连接服务器");
                    if (zhiXingYiCi.equals("1")) {
                        syncContactsSheBei(sheBeiList);
                        syncContactsRoom(roomList);

                        YanChi5ZhiXing();
                        zhiXingYiCi = "0";
                    }



                    break;

                case AIUIConstant.EVENT_SERVER_DISCONNECTED:
                    showTip("与服务器断连");
                    break;

                case AIUIConstant.EVENT_WAKEUP:
                    showTip("进入识别状态");
                    break;

                case AIUIConstant.EVENT_RESULT: {


                }
                break;

                case AIUIConstant.EVENT_ERROR: {
                    // mNlpText.append("\n");
                    // mNlpText.append("错误: " + event.arg1 + "\n" + event.info);
                    Log.i(TAG, "ERROR: " + event.arg1 + "\n" + event.info);
                }
                break;

                case AIUIConstant.EVENT_VAD: {
                    //vad事件
                    if (AIUIConstant.VAD_BOS == event.arg1) {
                        //找到语音前端点
                        Log.i(TAG, "找到vad_bos");
                    } else if (AIUIConstant.VAD_EOS == event.arg1) {
                        //找到语音后端点
                        Log.i(TAG, "找到vad_eos");
                    } else if (AIUIConstant.VAD_BOS_TIMEOUT == event.arg1) {
                    } else {
                        Log.i(TAG, "event vad " + event.arg2);
                    }
                }
                break;

                case AIUIConstant.EVENT_START_RECORD: {
                    showTip("已开始录音");
                }
                break;

                case AIUIConstant.EVENT_STOP_RECORD: {
                    showTip("已停止录音");
                }
                break;

                case AIUIConstant.EVENT_STATE: {    // 状态事件
                    mAIUIState = event.arg1;

                    if (AIUIConstant.STATE_IDLE == mAIUIState) {
                        // 闲置状态，AIUI未开启
                        showTip("STATE_IDLE");
                    } else if (AIUIConstant.STATE_READY == mAIUIState) {
                        // AIUI已就绪，等待唤醒
                        showTip("STATE_READY");
                    } else if (AIUIConstant.STATE_WORKING == mAIUIState) {
                        // AIUI工作中，可进行交互
                        showTip("STATE_WORKING");
                    }
                }
                break;

                case AIUIConstant.EVENT_CMD_RETURN: {

                    if (AIUIConstant.CMD_SYNC == event.arg1) {    // 数据同步的返回
                        int dtype = event.data.getInt("sync_dtype", -1);
                        int retCode = event.arg2;

                        switch (dtype) {
                            case AIUIConstant.SYNC_DATA_SCHEMA: {
                                if (AIUIConstant.SUCCESS == retCode) {
                                    // 上传成功，记录上传会话的sid，以用于查询数据打包状态
                                    // 注：上传成功并不表示数据打包成功，打包成功与否应以同步状态查询结果为准，数据只有打包成功后才能正常使用
                                    mSyncSid = event.data.getString("sid");

                                    // 获取上传调用时设置的自定义tag
                                    String tag = event.data.getString("tag");

                                    // 获取上传调用耗时，单位：ms
                                    long timeSpent = event.data.getLong("time_spent", -1);
                                    if (-1 != timeSpent) {
                                        //mTimeSpentText.setText(timeSpent + "ms");
                                        Log.i(TAG, "调用耗时" + timeSpent);
                                    }

                                    showTip("上传成功，sid=" + mSyncSid + "，tag=" + tag + "，你可以试着说“打电话给刘德华”");
                                    Log.i("YuYin", "打包成功_sid=" + mSyncSid);
                                } else {
                                    mSyncSid = "";
                                    showTip("上传失败，错误码：" + retCode);
                                }
                            }
                            break;
                        }
                    } else if (AIUIConstant.CMD_QUERY_SYNC_STATUS == event.arg1) {    // 数据同步状态查询的返回
                        // 获取同步类型
                        int syncType = event.data.getInt("sync_dtype", -1);
                        if (AIUIConstant.SYNC_DATA_QUERY == syncType) {
                            // 若是同步数据查询，则获取查询结果，结果中error字段为0则表示上传数据打包成功，否则为错误码
                            String result = event.data.getString("result");

                            showTip(result);

                            Notice notice = new Notice();
                            notice.type = ConstanceValue.MSG_XIUGAIDONGTAISHITIFINISH;
                            RxBus.getDefault().sendRx(notice);


                            Log.i(TAG, result);
                        }
                        mAIUIAgent.destroy();
                        mAIUIAgent = null;
                    }
                }
                break;

                default:
                    break;
            }
        }

    };
    Handler handler;
    Runnable runnable;

    private void YanChi5ZhiXing() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                chaXunDaBaoZhuangTai();

            }
        };

        handler.postDelayed(runnable, 5000);
    }

    public void chaXunDaBaoZhuangTai() {
        if (null == mAIUIAgent) {
            showTip("AIUIAgent 为空，请先创建");
            return;
        }


        if (TextUtils.isEmpty(mSyncSid)) {
            showTip("sid 为空");
            return;
        }

        try {
            // 构造查询json字符串，填入同步schema数据返回的sid
            JSONObject queryJson = new JSONObject();
            queryJson.put("sid", mSyncSid);

            // 发送同步数据状态查询消息，设置arg1为schema数据类型，params为查询字符串
            AIUIMessage syncQuery = new AIUIMessage(AIUIConstant.CMD_QUERY_SYNC_STATUS,
                    AIUIConstant.SYNC_DATA_SCHEMA, 0, queryJson.toString(), null);
            mAIUIAgent.sendMessage(syncQuery);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
