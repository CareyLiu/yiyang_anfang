package com.smarthome.magic.util;

import android.content.Context;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;

import com.smarthome.magic.tools.NetworkUtils;

import java.util.HashMap;
import java.util.Map;


public class NetUtils<T> {


    public void requestData(Map<String, String> map, String url, Context context, JsonCallback<AppResponse<T>> jsonCallback) {

        if (NetworkUtils.isNetAvailable(context)) {
            Gson gson = new Gson();
            OkGo.<AppResponse<T>>post(url)
                    .tag(context)//
                    .upJson(gson.toJson(map))
                    .execute(jsonCallback);
        } else {
            UIHelper.ToastMessage(context, "请联网后重新尝试");
        }


    }


}
