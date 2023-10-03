package com.android.dialer.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.content.LocalBroadcastManager;
import android.widget.Toast;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import com.android.dialer.storage.StorageComponent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PermissionsUtil {
    public static final String PREFERENCE_CAMERA_ALLOWED_BY_USER = "camera_allowed_by_user";
    public static final List<String> allContactsGroupPermissionsUsedInDialer = Collections.unmodifiableList(Arrays.asList(new String[]{"android.permission.READ_CONTACTS", "android.permission.WRITE_CONTACTS"}));
    public static final List<String> allLocationGroupPermissionsUsedInDialer = Collections.unmodifiableList(Arrays.asList(new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}));
    public static final List<String> allPhoneGroupPermissionsUsedInDialer = Collections.unmodifiableList(Arrays.asList(new String[]{"android.permission.READ_CALL_LOG", "android.permission.WRITE_CALL_LOG", "android.permission.READ_PHONE_STATE", "android.permission.MODIFY_PHONE_STATE", "android.permission.SEND_SMS", "android.permission.CALL_PHONE", "com.android.voicemail.permission.ADD_VOICEMAIL", "com.android.voicemail.permission.WRITE_VOICEMAIL", "com.android.voicemail.permission.READ_VOICEMAIL"}));

    public static String[] getPermissionsCurrentlyDenied(Context context, List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (String next : list) {
            if (!hasPermission(context, next)) {
                arrayList.add(next);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static boolean hasAddVoicemailPermissions(Context context) {
        return hasPermission(context, "com.android.voicemail.permission.ADD_VOICEMAIL");
    }

    public static boolean hasCallLogReadPermissions(Context context) {
        return hasPermission(context, "android.permission.READ_CALL_LOG");
    }

    public static boolean hasCallLogWritePermissions(Context context) {
        return hasPermission(context, "android.permission.WRITE_CALL_LOG");
    }

    public static boolean hasCameraPermissions(Context context) {
        return hasPermission(context, "android.permission.CAMERA");
    }

    public static boolean hasCameraPrivacyToastShown(Context context) {
        return StorageComponent.get(context).unencryptedSharedPrefs().getBoolean(PREFERENCE_CAMERA_ALLOWED_BY_USER, false);
    }

    public static boolean hasContactsReadPermissions(Context context) {
        return hasPermission(context, "android.permission.READ_CONTACTS");
    }

    public static boolean hasPermission(Context context, String str) {
        return ContextCompat.checkSelfPermission(context, str) == 0;
    }

    public static boolean hasPhonePermissions(Context context) {
        return hasPermission(context, "android.permission.CALL_PHONE");
    }

    public static boolean hasReadPhoneStatePermissions(Context context) {
        return hasPermission(context, "android.permission.READ_PHONE_STATE");
    }

    public static boolean hasReadVoicemailPermissions(Context context) {
        return hasPermission(context, "com.android.voicemail.permission.READ_VOICEMAIL");
    }

    public static boolean isFirstRequest(Context context, String str) {
        return context.getSharedPreferences("dialer_permissions", 0).getBoolean(str, true);
    }

    public static void notifyPermissionGranted(Context context, String str) {
        LogUtil.m9i("PermissionsUtil.notifyPermissionGranted", str, new Object[0]);
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(str));
    }

    public static void permissionRequested(Context context, String str) {
        context.getSharedPreferences("dialer_permissions", 0).edit().putBoolean(str, false).apply();
    }

    public static void registerPermissionReceiver(Context context, BroadcastReceiver broadcastReceiver, String str) {
        LogUtil.m9i("PermissionsUtil.registerPermissionReceiver", str, new Object[0]);
        LocalBroadcastManager.getInstance(context).registerReceiver(broadcastReceiver, new IntentFilter(str));
    }

    public static void setCameraPrivacyToastShown(Context context) {
        StorageComponent.get(context).unencryptedSharedPrefs().edit().putBoolean(PREFERENCE_CAMERA_ALLOWED_BY_USER, true).apply();
    }

    public static void showCameraPermissionToast(Context context) {
        Toast.makeText(context, context.getString(R.string.camera_privacy_text), 1).show();
        setCameraPrivacyToastShown(context);
    }

    public static void unregisterPermissionReceiver(Context context, BroadcastReceiver broadcastReceiver) {
        LogUtil.m9i("PermissionsUtil.unregisterPermissionReceiver", (String) null, new Object[0]);
        LocalBroadcastManager.getInstance(context).unregisterReceiver(broadcastReceiver);
    }
}
