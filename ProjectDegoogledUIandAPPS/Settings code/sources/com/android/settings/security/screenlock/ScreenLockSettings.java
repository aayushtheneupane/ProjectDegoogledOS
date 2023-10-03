package com.android.settings.security.screenlock;

import android.content.Context;
import android.os.UserHandle;
import android.provider.SearchIndexableResource;
import androidx.fragment.app.Fragment;
import com.android.internal.widget.LockPatternUtils;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.security.OwnerInfoPreferenceController;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class ScreenLockSettings extends DashboardFragment implements OwnerInfoPreferenceController.OwnerInfoCallback {
    private static final int MY_USER_ID = UserHandle.myUserId();
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.screen_lock_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }

        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            return ScreenLockSettings.buildPreferenceControllers(context, (Fragment) null, (Lifecycle) null, new LockPatternUtils(context));
        }
    };
    private LockPatternUtils mLockPatternUtils;

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "ScreenLockSettings";
    }

    public int getMetricsCategory() {
        return 1265;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.screen_lock_settings;
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        this.mLockPatternUtils = new LockPatternUtils(context);
        return buildPreferenceControllers(context, this, getSettingsLifecycle(), this.mLockPatternUtils);
    }

    public void onOwnerInfoUpdated() {
        ((OwnerInfoPreferenceController) use(OwnerInfoPreferenceController.class)).updateSummary();
    }

    /* access modifiers changed from: private */
    public static List<AbstractPreferenceController> buildPreferenceControllers(Context context, Fragment fragment, Lifecycle lifecycle, LockPatternUtils lockPatternUtils) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PatternVisiblePreferenceController(context, MY_USER_ID, lockPatternUtils));
        arrayList.add(new PatternErrorVisiblePreferenceController(context, MY_USER_ID, lockPatternUtils));
        arrayList.add(new PatternDotsVisiblePreferenceController(context, MY_USER_ID, lockPatternUtils));
        arrayList.add(new PowerButtonInstantLockPreferenceController(context, MY_USER_ID, lockPatternUtils));
        arrayList.add(new LockAfterTimeoutPreferenceController(context, MY_USER_ID, lockPatternUtils));
        arrayList.add(new QuickUnlockPreferenceController(context, MY_USER_ID, lockPatternUtils));
        arrayList.add(new ScramblePinPreferenceController(context, MY_USER_ID, lockPatternUtils));
        arrayList.add(new OwnerInfoPreferenceController(context, fragment, lifecycle));
        return arrayList;
    }
}
