package com.android.contacts.util;

import android.app.AppOpsManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Process;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class PermissionsUtil {
    public static final String CONTACTS = "android.permission.READ_CONTACTS";
    public static final String LOCATION = "android.permission.ACCESS_FINE_LOCATION";
    public static final String PHONE = "android.permission.CALL_PHONE";

    public static boolean hasPhonePermissions(Context context) {
        return hasPermission(context, PHONE);
    }

    public static boolean hasContactsPermissions(Context context) {
        return hasPermission(context, CONTACTS);
    }

    public static boolean hasLocationPermissions(Context context) {
        return hasPermission(context, LOCATION);
    }

    public static boolean hasPermission(Context context, String str) {
        return ContextCompat.checkSelfPermission(context, str) == 0;
    }

    public static boolean hasAppOp(Context context, String str) {
        return ((AppOpsManager) context.getSystemService("appops")).checkOpNoThrow(str, Process.myUid(), context.getPackageName()) == 0;
    }

    public static void registerPermissionReceiver(Context context, BroadcastReceiver broadcastReceiver, String str) {
        LocalBroadcastManager.getInstance(context).registerReceiver(broadcastReceiver, new IntentFilter(str));
    }

    public static void unregisterPermissionReceiver(Context context, BroadcastReceiver broadcastReceiver) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(broadcastReceiver);
    }

    public static void notifyPermissionGranted(Context context, String str) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(str));
    }
}
