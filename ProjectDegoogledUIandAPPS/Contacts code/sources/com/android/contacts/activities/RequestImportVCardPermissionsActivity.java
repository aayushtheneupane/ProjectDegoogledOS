package com.android.contacts.activities;

import android.app.Activity;
import com.android.contacts.util.PermissionsUtil;

public class RequestImportVCardPermissionsActivity extends RequestPermissionsActivity {
    private static final String[] REQUIRED_PERMISSIONS = {"android.permission.GET_ACCOUNTS", PermissionsUtil.CONTACTS, "android.permission.WRITE_CONTACTS", "android.permission.READ_EXTERNAL_STORAGE"};

    /* access modifiers changed from: protected */
    public String[] getPermissions() {
        return REQUIRED_PERMISSIONS;
    }

    public static boolean startPermissionActivity(Activity activity, boolean z) {
        return RequestPermissionsActivityBase.startPermissionActivity(activity, REQUIRED_PERMISSIONS, z, RequestImportVCardPermissionsActivity.class);
    }
}
