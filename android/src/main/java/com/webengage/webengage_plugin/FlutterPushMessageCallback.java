package com.webengage.webengage_plugin;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.webengage.sdk.android.actions.render.PushNotificationData;
import com.webengage.sdk.android.callbacks.PushNotificationCallbacks;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import static com.webengage.webengage_plugin.Constants.MethodName.*;
import static com.webengage.webengage_plugin.Utils.bundleToMap;

public class FlutterPushMessageCallback implements PushNotificationCallbacks {
    @Override
    public PushNotificationData onPushNotificationReceived(Context context, PushNotificationData pushNotificationData) {

        return pushNotificationData;
    }

    @Override
    public void onPushNotificationShown(Context context, PushNotificationData pushNotificationData) {

    }

    @Override
    public boolean onPushNotificationClicked(Context context, PushNotificationData pushNotificationData) {
        Log.d("WebEngage", "onPushNotificationClicked");

        String uri = pushNotificationData.getPrimeCallToAction().getAction();
        Map<String, Object> map = bundleToMap(pushNotificationData.getCustomData());
        map.put("uri", uri);

        WebEngagePlugin.sendOrQueueCallback(METHOD_NAME_ON_PUSH_CLICK, map);

        return false;
    }

    @Override
    public void onPushNotificationDismissed(Context context, PushNotificationData pushNotificationData) {

    }

    @Override
    public boolean onPushNotificationActionClicked(Context context, PushNotificationData pushNotificationData, String s) {
        Log.d("WebEngage", "onPushNotificationActionClicked");
        String uri = pushNotificationData.getCallToActionById(s).getAction();
        Map<String, Object> map = bundleToMap(pushNotificationData.getCustomData());
        map.put("uri", uri);
        WebEngagePlugin.sendOrQueueCallback(METHOD_NAME_ON_PUSH_ACTION_CLICK, map);
        return false;
    }

}
