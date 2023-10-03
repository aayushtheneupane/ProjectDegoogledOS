package com.android.contacts.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Toast;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.android.contacts.R;
import com.android.contacts.util.PermissionsUtil;
import java.util.ArrayList;

public class RequestPermissionsActivity extends RequestPermissionsActivityBase {
    private static String[] sRequiredPermissions;

    /* access modifiers changed from: protected */
    public String[] getPermissions() {
        return getPermissions(getPackageManager());
    }

    public static boolean hasRequiredPermissions(Context context) {
        return RequestPermissionsActivityBase.hasPermissions(context, getPermissions(context.getPackageManager()));
    }

    public static boolean startPermissionActivityIfNeeded(Activity activity) {
        return RequestPermissionsActivityBase.startPermissionActivity(activity, getPermissions(activity.getPackageManager()), RequestPermissionsActivity.class);
    }

    private static String[] getPermissions(PackageManager packageManager) {
        if (sRequiredPermissions == null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("android.permission.GET_ACCOUNTS");
            arrayList.add(PermissionsUtil.CONTACTS);
            arrayList.add("android.permission.WRITE_CONTACTS");
            if (packageManager.hasSystemFeature("android.hardware.telephony")) {
                arrayList.add(PermissionsUtil.PHONE);
                arrayList.add("android.permission.READ_PHONE_STATE");
            }
            sRequiredPermissions = (String[]) arrayList.toArray(new String[0]);
        }
        return sRequiredPermissions;
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (strArr == null || strArr.length <= 0 || !isAllGranted(strArr, iArr)) {
            Toast.makeText(this, R.string.missing_required_permission, 0).show();
            finish();
            return;
        }
        this.mPreviousActivityIntent.setFlags(65536);
        if (this.mIsCallerSelf) {
            startActivityForResult(this.mPreviousActivityIntent, 0);
        } else {
            startActivity(this.mPreviousActivityIntent);
        }
        finish();
        overridePendingTransition(0, 0);
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("broadcastPermissionsGranted"));
    }
}
