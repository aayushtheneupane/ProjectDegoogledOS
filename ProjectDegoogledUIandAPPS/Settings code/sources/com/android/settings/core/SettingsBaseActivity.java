package com.android.settings.core;

import android.R;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import com.android.settings.SubSettings;
import com.android.settings.dashboard.CategoryManager;
import com.google.android.setupcompat.util.WizardManagerHelper;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class SettingsBaseActivity extends FragmentActivity {
    /* access modifiers changed from: private */
    public static ArraySet<ComponentName> sTileBlacklist = new ArraySet<>();
    private final List<CategoryListener> mCategoryListeners = new ArrayList();
    private final PackageReceiver mPackageReceiver = new PackageReceiver();

    public interface CategoryListener {
        void onCategoriesChanged();
    }

    public boolean isLaunchableInTaskModePinned() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (isLockTaskModePinned() && !isSettingsRunOnTop() && !isLaunchableInTaskModePinned()) {
            Log.w("SettingsBaseActivity", "Devices lock task mode pinned.");
            finish();
        }
        System.currentTimeMillis();
        getLifecycle().addObserver(new HideNonSystemOverlayMixin(this));
        TypedArray obtainStyledAttributes = getTheme().obtainStyledAttributes(R.styleable.Theme);
        if (!obtainStyledAttributes.getBoolean(38, false)) {
            requestWindowFeature(1);
        }
        if (WizardManagerHelper.isAnySetupWizard(getIntent()) && (this instanceof SubSettings)) {
            setTheme(2131951895);
        }
        super.setContentView(C1715R.layout.settings_base_layout);
        Toolbar toolbar = (Toolbar) findViewById(C1715R.C1718id.action_bar);
        if (obtainStyledAttributes.getBoolean(38, false)) {
            toolbar.setVisibility(8);
        } else {
            setActionBar(toolbar);
        }
    }

    public boolean onNavigateUp() {
        if (super.onNavigateUp()) {
            return true;
        }
        finish();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addDataScheme("package");
        registerReceiver(this.mPackageReceiver, intentFilter);
        new CategoriesUpdateTask().execute(new Void[0]);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        unregisterReceiver(this.mPackageReceiver);
        super.onPause();
    }

    public void addCategoryListener(CategoryListener categoryListener) {
        this.mCategoryListeners.add(categoryListener);
    }

    public void remCategoryListener(CategoryListener categoryListener) {
        this.mCategoryListeners.remove(categoryListener);
    }

    public void setContentView(int i) {
        ViewGroup viewGroup = (ViewGroup) findViewById(C1715R.C1718id.content_frame);
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        LayoutInflater.from(this).inflate(i, viewGroup);
    }

    public void setContentView(View view) {
        ((ViewGroup) findViewById(C1715R.C1718id.content_frame)).addView(view);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        ((ViewGroup) findViewById(C1715R.C1718id.content_frame)).addView(view, layoutParams);
    }

    /* access modifiers changed from: private */
    public void onCategoriesChanged() {
        int size = this.mCategoryListeners.size();
        for (int i = 0; i < size; i++) {
            this.mCategoryListeners.get(i).onCategoriesChanged();
        }
    }

    private boolean isLockTaskModePinned() {
        return ((ActivityManager) getApplicationContext().getSystemService(ActivityManager.class)).getLockTaskModeState() == 2;
    }

    private boolean isSettingsRunOnTop() {
        return TextUtils.equals(getPackageName(), ((ActivityManager) getApplicationContext().getSystemService(ActivityManager.class)).getRunningTasks(1).get(0).baseActivity.getPackageName());
    }

    public boolean setTileEnabled(ComponentName componentName, boolean z) {
        PackageManager packageManager = getPackageManager();
        int componentEnabledSetting = packageManager.getComponentEnabledSetting(componentName);
        if ((componentEnabledSetting == 1) == z && componentEnabledSetting != 0) {
            return false;
        }
        if (z) {
            sTileBlacklist.remove(componentName);
        } else {
            sTileBlacklist.add(componentName);
        }
        packageManager.setComponentEnabledSetting(componentName, z ? 1 : 2, 1);
        return true;
    }

    public void updateCategories() {
        new CategoriesUpdateTask().execute(new Void[0]);
    }

    private class CategoriesUpdateTask extends AsyncTask<Void, Void, Void> {
        private final CategoryManager mCategoryManager;

        public CategoriesUpdateTask() {
            this.mCategoryManager = CategoryManager.get(SettingsBaseActivity.this);
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            this.mCategoryManager.reloadAllCategories(SettingsBaseActivity.this);
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            this.mCategoryManager.updateCategoryFromBlacklist(SettingsBaseActivity.sTileBlacklist);
            SettingsBaseActivity.this.onCategoriesChanged();
        }
    }

    private class PackageReceiver extends BroadcastReceiver {
        private PackageReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            new CategoriesUpdateTask().execute(new Void[0]);
        }
    }
}
