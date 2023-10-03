package com.android.dialer.interactions;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.android.dialer.util.PermissionsUtil;

public class UndemoteOutgoingCallReceiver extends BroadcastReceiver {
    /* access modifiers changed from: private */
    public long getContactIdFromPhoneNumber(Context context, String str) {
        if (!PermissionsUtil.hasPermission(context, "android.permission.READ_CONTACTS")) {
            return -1;
        }
        try {
            Cursor query = context.getContentResolver().query(Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(str)), new String[]{"_id"}, (String) null, (String[]) null, (String) null);
            if (query == null) {
                return -1;
            }
            try {
                if (query.moveToFirst()) {
                    return query.getLong(0);
                }
                query.close();
                return -1;
            } finally {
                query.close();
            }
        } catch (SecurityException unused) {
            return -1;
        }
    }

    /* access modifiers changed from: private */
    public void undemoteContactWithId(Context context, long j) {
        if (PermissionsUtil.hasPermission(context, "android.permission.WRITE_CONTACTS")) {
            try {
                ContactsContract.PinnedPositions.undemote(context.getContentResolver(), j);
            } catch (SecurityException unused) {
            }
        }
    }

    public void onReceive(final Context context, Intent intent) {
        if (PermissionsUtil.hasPermission(context, "android.permission.READ_CONTACTS") && PermissionsUtil.hasPermission(context, "android.permission.WRITE_CONTACTS") && intent != null && "android.intent.action.NEW_OUTGOING_CALL".equals(intent.getAction())) {
            final String stringExtra = intent.getStringExtra("android.intent.extra.PHONE_NUMBER");
            if (!TextUtils.isEmpty(stringExtra)) {
                new Thread() {
                    public void run() {
                        long access$000 = UndemoteOutgoingCallReceiver.this.getContactIdFromPhoneNumber(context, stringExtra);
                        if (access$000 != -1) {
                            UndemoteOutgoingCallReceiver.this.undemoteContactWithId(context, access$000);
                        }
                    }
                }.start();
            }
        }
    }
}
