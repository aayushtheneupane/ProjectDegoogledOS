package com.bumptech.glide.signature;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.bumptech.glide.load.Key;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class ApplicationVersionSignature {
    private static final ConcurrentMap<String, Key> PACKAGE_NAME_TO_KEY = new ConcurrentHashMap();

    public static Key obtain(Context context) {
        PackageInfo packageInfo;
        String str;
        String packageName = context.getPackageName();
        Key key = (Key) PACKAGE_NAME_TO_KEY.get(packageName);
        if (key != null) {
            return key;
        }
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            String valueOf = String.valueOf(context.getPackageName());
            Log.e("AppVersionSignature", valueOf.length() != 0 ? "Cannot resolve info for".concat(valueOf) : new String("Cannot resolve info for"), e);
            packageInfo = null;
        }
        if (packageInfo != null) {
            str = String.valueOf(packageInfo.versionCode);
        } else {
            str = UUID.randomUUID().toString();
        }
        ObjectKey objectKey = new ObjectKey(str);
        Key putIfAbsent = PACKAGE_NAME_TO_KEY.putIfAbsent(packageName, objectKey);
        return putIfAbsent == null ? objectKey : putIfAbsent;
    }

    static void reset() {
        PACKAGE_NAME_TO_KEY.clear();
    }
}
