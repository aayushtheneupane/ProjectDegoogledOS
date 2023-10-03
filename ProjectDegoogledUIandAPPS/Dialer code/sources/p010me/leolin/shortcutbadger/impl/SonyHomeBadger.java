package p010me.leolin.shortcutbadger.impl;

import android.content.AsyncQueryHandler;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.util.Arrays;
import java.util.List;
import p010me.leolin.shortcutbadger.Badger;
import p010me.leolin.shortcutbadger.ShortcutBadgeException;

/* renamed from: me.leolin.shortcutbadger.impl.SonyHomeBadger */
public class SonyHomeBadger implements Badger {
    private final Uri BADGE_CONTENT_URI = Uri.parse("content://com.sonymobile.home.resourceprovider/badge");
    private AsyncQueryHandler mQueryHandler;

    public void executeBadge(Context context, ComponentName componentName, int i) throws ShortcutBadgeException {
        boolean z = false;
        if (!(context.getPackageManager().resolveContentProvider("com.sonymobile.home.resourceprovider", 0) != null)) {
            Intent intent = new Intent("com.sonyericsson.home.action.UPDATE_BADGE");
            intent.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", componentName.getPackageName());
            intent.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME", componentName.getClassName());
            intent.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", String.valueOf(i));
            if (i > 0) {
                z = true;
            }
            intent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", z);
            context.sendBroadcast(intent);
        } else if (i >= 0) {
            if (this.mQueryHandler == null) {
                this.mQueryHandler = new AsyncQueryHandler(this, context.getApplicationContext().getContentResolver()) {
                };
            }
            String packageName = componentName.getPackageName();
            String className = componentName.getClassName();
            ContentValues contentValues = new ContentValues();
            contentValues.put("badge_count", Integer.valueOf(i));
            contentValues.put("package_name", packageName);
            contentValues.put("activity_name", className);
            this.mQueryHandler.startInsert(0, (Object) null, this.BADGE_CONTENT_URI, contentValues);
        }
    }

    public List<String> getSupportLaunchers() {
        return Arrays.asList(new String[]{"com.sonyericsson.home", "com.sonymobile.home"});
    }
}
