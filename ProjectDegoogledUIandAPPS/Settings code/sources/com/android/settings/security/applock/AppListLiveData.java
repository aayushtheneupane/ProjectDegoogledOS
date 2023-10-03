package com.android.settings.security.applock;

import android.app.AppLockManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.ArrayList;
import java.util.List;

/* compiled from: AppLockViewModel */
class AppListLiveData extends LiveData<List<AppLockInfo>> {
    /* access modifiers changed from: private */
    public final AppLockManager mAppLockManager;
    /* access modifiers changed from: private */
    public int mCurrentDataVersion;
    /* access modifiers changed from: private */
    public final PackageManager mPackageManager;

    public AppListLiveData(Context context) {
        this.mPackageManager = context.getPackageManager();
        this.mAppLockManager = (AppLockManager) context.getSystemService("applock");
        loadSupportedAppData();
    }

    /* access modifiers changed from: package-private */
    public void loadSupportedAppData() {
        final int i = this.mCurrentDataVersion + 1;
        this.mCurrentDataVersion = i;
        new AsyncTask<Void, Void, List<AppLockInfo>>() {
            /* access modifiers changed from: protected */
            public List<AppLockInfo> doInBackground(Void... voidArr) {
                Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
                intent.addCategory("android.intent.category.LAUNCHER");
                List<ResolveInfo> queryIntentActivities = AppListLiveData.this.mPackageManager.queryIntentActivities(intent, 128);
                ArrayList arrayList = new ArrayList();
                if (queryIntentActivities != null) {
                    for (ResolveInfo next : queryIntentActivities) {
                        if (!next.activityInfo.packageName.equals("com.android.settings")) {
                            arrayList.add(new AppLockInfo(next, AppListLiveData.this.mPackageManager, AppListLiveData.this.mAppLockManager));
                        }
                    }
                }
                return arrayList;
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(List<AppLockInfo> list) {
                if (AppListLiveData.this.mCurrentDataVersion == i) {
                    AppListLiveData.this.setValue(list);
                }
            }
        }.execute(new Void[0]);
    }
}
