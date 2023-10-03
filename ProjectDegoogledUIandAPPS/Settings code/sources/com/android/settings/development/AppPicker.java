package com.android.settings.development;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.havoc.config.center.C1715R;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AppPicker extends ListActivity {
    /* access modifiers changed from: private */
    public static final Comparator<MyApplicationInfo> sDisplayNameComparator = new Comparator<MyApplicationInfo>() {
        private final Collator collator = Collator.getInstance();

        public final int compare(MyApplicationInfo myApplicationInfo, MyApplicationInfo myApplicationInfo2) {
            return this.collator.compare(myApplicationInfo.label, myApplicationInfo2.label);
        }
    };
    private AppListAdapter mAdapter;
    /* access modifiers changed from: private */
    public boolean mDebuggableOnly;
    /* access modifiers changed from: private */
    public boolean mNonSystemOnly;
    /* access modifiers changed from: private */
    public String mPermissionName;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPermissionName = getIntent().getStringExtra("com.android.settings.extra.REQUESTIING_PERMISSION");
        this.mDebuggableOnly = getIntent().getBooleanExtra("com.android.settings.extra.DEBUGGABLE", false);
        this.mNonSystemOnly = getIntent().getBooleanExtra("com.android.settings.extra.NON_SYSTEM", false);
        this.mAdapter = new AppListAdapter(this);
        if (this.mAdapter.getCount() <= 0) {
            finish();
        } else {
            setListAdapter(this.mAdapter);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public void onListItemClick(ListView listView, View view, int i, long j) {
        Intent intent = new Intent();
        ApplicationInfo applicationInfo = ((MyApplicationInfo) this.mAdapter.getItem(i)).info;
        if (applicationInfo != null) {
            intent.setAction(applicationInfo.packageName);
        }
        setResult(-1, intent);
        finish();
    }

    class MyApplicationInfo {
        ApplicationInfo info;
        CharSequence label;

        MyApplicationInfo() {
        }
    }

    public class AppListAdapter extends ArrayAdapter<MyApplicationInfo> {
        private final LayoutInflater mInflater;
        private final List<MyApplicationInfo> mPackageInfoList = new ArrayList();

        public AppListAdapter(Context context) {
            super(context, 0);
            boolean z;
            this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
            List<ApplicationInfo> installedApplications = context.getPackageManager().getInstalledApplications(0);
            for (int i = 0; i < installedApplications.size(); i++) {
                ApplicationInfo applicationInfo = installedApplications.get(i);
                if (applicationInfo.uid != 1000 && ((!AppPicker.this.mDebuggableOnly || (applicationInfo.flags & 2) != 0 || !"user".equals(Build.TYPE)) && (!AppPicker.this.mNonSystemOnly || !applicationInfo.isSystemApp()))) {
                    if (AppPicker.this.mPermissionName != null) {
                        try {
                            PackageInfo packageInfo = AppPicker.this.getPackageManager().getPackageInfo(applicationInfo.packageName, 4096);
                            if (packageInfo.requestedPermissions != null) {
                                String[] strArr = packageInfo.requestedPermissions;
                                int length = strArr.length;
                                int i2 = 0;
                                while (true) {
                                    if (i2 >= length) {
                                        z = false;
                                        break;
                                    } else if (strArr[i2].equals(AppPicker.this.mPermissionName)) {
                                        z = true;
                                        break;
                                    } else {
                                        i2++;
                                    }
                                }
                                if (!z) {
                                }
                            }
                        } catch (PackageManager.NameNotFoundException unused) {
                        }
                    }
                    MyApplicationInfo myApplicationInfo = new MyApplicationInfo();
                    myApplicationInfo.info = applicationInfo;
                    myApplicationInfo.label = myApplicationInfo.info.loadLabel(AppPicker.this.getPackageManager()).toString();
                    this.mPackageInfoList.add(myApplicationInfo);
                }
            }
            Collections.sort(this.mPackageInfoList, AppPicker.sDisplayNameComparator);
            MyApplicationInfo myApplicationInfo2 = new MyApplicationInfo();
            myApplicationInfo2.label = context.getText(C1715R.string.no_application);
            this.mPackageInfoList.add(0, myApplicationInfo2);
            addAll(this.mPackageInfoList);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            AppViewHolder createOrRecycle = AppViewHolder.createOrRecycle(this.mInflater, view);
            View view2 = createOrRecycle.rootView;
            MyApplicationInfo myApplicationInfo = (MyApplicationInfo) getItem(i);
            createOrRecycle.appName.setText(myApplicationInfo.label);
            ApplicationInfo applicationInfo = myApplicationInfo.info;
            if (applicationInfo != null) {
                createOrRecycle.appIcon.setImageDrawable(applicationInfo.loadIcon(AppPicker.this.getPackageManager()));
                createOrRecycle.summary.setText(myApplicationInfo.info.packageName);
            } else {
                createOrRecycle.appIcon.setImageDrawable((Drawable) null);
                createOrRecycle.summary.setText("");
            }
            createOrRecycle.disabled.setVisibility(8);
            return view2;
        }
    }
}
