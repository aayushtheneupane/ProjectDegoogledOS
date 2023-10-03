package com.android.contacts.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.android.contacts.model.account.GoogleAccountType;
import com.android.contacts.quickcontact.QuickContactActivity;
import java.util.List;

public class ImplicitIntentsUtil {
    public static void startActivityInAppIfPossible(Context context, Intent intent) {
        Intent intentInAppIfExists = getIntentInAppIfExists(context, intent);
        if (intentInAppIfExists != null) {
            context.startActivity(intentInAppIfExists);
        } else {
            context.startActivity(intent);
        }
    }

    public static void startActivityInApp(Context context, Intent intent) {
        intent.setPackage(context.getPackageName());
        context.startActivity(intent);
    }

    public static void startActivityOutsideApp(Context context, Intent intent) {
        if (!(Build.TYPE.equals("eng") || Build.TYPE.equals("userdebug")) || getIntentInAppIfExists(context, intent) == null) {
            context.startActivity(intent);
            return;
        }
        throw new AssertionError("startActivityOutsideApp() was called for an intent that can be handled inside the app");
    }

    public static void startQuickContact(Activity activity, Uri uri, int i) {
        startQuickContact(activity, uri, i, -1);
    }

    private static void startQuickContact(Activity activity, Uri uri, int i, int i2) {
        Intent composeQuickContactIntent = composeQuickContactIntent(activity, uri, i);
        if (i2 >= 0) {
            composeQuickContactIntent.setPackage(activity.getPackageName());
            activity.startActivityForResult(composeQuickContactIntent, i2);
            return;
        }
        startActivityInApp(activity, composeQuickContactIntent);
    }

    public static Intent composeQuickContactIntent(Context context, Uri uri, int i) {
        return composeQuickContactIntent(context, uri, 4, i);
    }

    public static Intent composeQuickContactIntent(Context context, Uri uri, int i, int i2) {
        Intent intent = new Intent(context, QuickContactActivity.class);
        intent.setAction("android.provider.action.QUICK_CONTACT");
        intent.setData(uri);
        intent.putExtra("android.provider.extra.MODE", i);
        intent.addFlags(67108864);
        intent.putExtra("previous_screen_type", i2);
        return intent;
    }

    public static Intent getIntentForAddingAccount() {
        Intent intent = new Intent("android.settings.SYNC_SETTINGS");
        intent.setFlags(524288);
        intent.putExtra("authorities", new String[]{"com.android.contacts"});
        return intent;
    }

    public static Intent getIntentForAddingGoogleAccount() {
        Intent intent = new Intent("android.settings.ADD_ACCOUNT_SETTINGS");
        intent.setFlags(524288);
        intent.putExtra("account_types", new String[]{GoogleAccountType.ACCOUNT_TYPE});
        return intent;
    }

    public static Intent getIntentForQuickContactLauncherShortcut(Context context, Uri uri) {
        Intent composeQuickContactIntent = composeQuickContactIntent(context, uri, 3, 0);
        composeQuickContactIntent.setPackage(context.getPackageName());
        composeQuickContactIntent.addFlags(268533760);
        composeQuickContactIntent.putExtra("com.android.launcher.intent.extra.shortcut.INGORE_LAUNCH_ANIMATION", true);
        composeQuickContactIntent.putExtra("android.provider.extra.EXCLUDE_MIMES", (String[]) null);
        return composeQuickContactIntent;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        r1 = new android.content.Intent("android.settings.EDIT_EMERGENCY_INFO").setPackage("com.android.emergency");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.Intent getIntentForEmergencyInfo(android.content.Context r3) {
        /*
            r0 = 0
            if (r3 == 0) goto L_0x002a
            android.content.pm.PackageManager r1 = r3.getPackageManager()
            if (r1 != 0) goto L_0x000a
            goto L_0x002a
        L_0x000a:
            android.content.Intent r1 = new android.content.Intent
            java.lang.String r2 = "android.settings.EDIT_EMERGENCY_INFO"
            r1.<init>(r2)
            java.lang.String r2 = "com.android.emergency"
            android.content.Intent r1 = r1.setPackage(r2)
            android.content.pm.PackageManager r3 = r3.getPackageManager()
            r2 = 65536(0x10000, float:9.18355E-41)
            java.util.List r3 = r3.queryIntentActivities(r1, r2)
            if (r3 == 0) goto L_0x002a
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x002a
            return r1
        L_0x002a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.util.ImplicitIntentsUtil.getIntentForEmergencyInfo(android.content.Context):android.content.Intent");
    }

    private static Intent getIntentInAppIfExists(Context context, Intent intent) {
        try {
            Intent intent2 = new Intent(intent);
            intent2.setPackage(context.getPackageName());
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent2, 65536);
            if (!(queryIntentActivities == null || queryIntentActivities.size() == 0)) {
                if (queryIntentActivities.get(0).activityInfo != null && !TextUtils.isEmpty(queryIntentActivities.get(0).activityInfo.name)) {
                    intent2.setClassName(context.getPackageName(), queryIntentActivities.get(0).activityInfo.name);
                }
                return intent2;
            }
        } catch (Exception unused) {
        }
        return null;
    }
}
