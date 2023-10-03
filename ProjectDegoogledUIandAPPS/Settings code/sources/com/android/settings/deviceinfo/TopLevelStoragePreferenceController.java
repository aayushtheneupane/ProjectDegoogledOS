package com.android.settings.deviceinfo;

import android.content.Context;
import android.os.storage.StorageManager;
import android.text.format.Formatter;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.deviceinfo.PrivateStorageInfo;
import com.android.settingslib.deviceinfo.StorageManagerVolumeProvider;
import com.android.settingslib.utils.ThreadUtils;
import com.havoc.config.center.C1715R;
import java.text.NumberFormat;

public class TopLevelStoragePreferenceController extends BasePreferenceController {
    private final StorageManager mStorageManager = ((StorageManager) this.mContext.getSystemService(StorageManager.class));
    private final StorageManagerVolumeProvider mStorageManagerVolumeProvider = new StorageManagerVolumeProvider(this.mStorageManager);

    public int getAvailabilityStatus() {
        return 1;
    }

    public TopLevelStoragePreferenceController(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: protected */
    public void refreshSummary(Preference preference) {
        if (preference != null) {
            ThreadUtils.postOnBackgroundThread((Runnable) new Runnable(preference) {
                private final /* synthetic */ Preference f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    TopLevelStoragePreferenceController.this.lambda$refreshSummary$1$TopLevelStoragePreferenceController(this.f$1);
                }
            });
        }
    }

    public /* synthetic */ void lambda$refreshSummary$1$TopLevelStoragePreferenceController(Preference preference) {
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        PrivateStorageInfo privateStorageInfo = PrivateStorageInfo.getPrivateStorageInfo(this.mStorageManagerVolumeProvider);
        ThreadUtils.postOnMainThread(new Runnable(preference, percentInstance, (double) (privateStorageInfo.totalBytes - privateStorageInfo.freeBytes), privateStorageInfo) {
            private final /* synthetic */ Preference f$1;
            private final /* synthetic */ NumberFormat f$2;
            private final /* synthetic */ double f$3;
            private final /* synthetic */ PrivateStorageInfo f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r6;
            }

            public final void run() {
                TopLevelStoragePreferenceController.this.lambda$refreshSummary$0$TopLevelStoragePreferenceController(this.f$1, this.f$2, this.f$3, this.f$4);
            }
        });
    }

    public /* synthetic */ void lambda$refreshSummary$0$TopLevelStoragePreferenceController(Preference preference, NumberFormat numberFormat, double d, PrivateStorageInfo privateStorageInfo) {
        preference.setSummary((CharSequence) this.mContext.getString(C1715R.string.storage_summary, new Object[]{numberFormat.format(d / ((double) privateStorageInfo.totalBytes)), Formatter.formatFileSize(this.mContext, privateStorageInfo.freeBytes)}));
    }
}
