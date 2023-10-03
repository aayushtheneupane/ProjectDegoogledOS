package com.android.systemui.statusbar.phone;

import android.content.pm.ActivityInfo;
import android.provider.Settings;
import android.view.View;
import android.widget.ListView;
import com.havoc.support.preferences.AppPicker;

public class LeftBackSwipeCustomApp extends AppPicker {
    /* access modifiers changed from: protected */
    public void onListItemClick(ListView listView, View view, int i, long j) {
        boolean z = this.mIsActivitiesList;
        if (!z) {
            setPackage(this.applist.get(i).packageName, (String) this.applist.get(i).loadLabel(this.packageManager));
            setPackageActivity((ActivityInfo) null);
        } else if (z) {
            setPackageActivity(this.mActivitiesList.get(i));
        }
        this.mIsActivitiesList = false;
        finish();
    }

    /* access modifiers changed from: protected */
    public void onLongClick(int i) {
        if (!this.mIsActivitiesList) {
            String str = this.applist.get(i).packageName;
            setPackage(str, (String) this.applist.get(i).loadLabel(this.packageManager));
            setPackageActivity((ActivityInfo) null);
            showActivitiesDialog(str);
        }
    }

    /* access modifiers changed from: protected */
    public void setPackage(String str, String str2) {
        Settings.System.putStringForUser(getContentResolver(), "left_long_back_swipe_app_action", str, -2);
        Settings.System.putStringForUser(getContentResolver(), "left_long_back_swipe_app_fr_action", str2, -2);
    }

    /* access modifiers changed from: protected */
    public void setPackageActivity(ActivityInfo activityInfo) {
        Settings.System.putStringForUser(getContentResolver(), "left_long_back_swipe_app_activity_action", activityInfo != null ? activityInfo.name : "NONE", -2);
    }
}
