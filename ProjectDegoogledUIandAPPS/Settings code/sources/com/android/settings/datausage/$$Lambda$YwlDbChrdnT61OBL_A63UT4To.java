package com.android.settings.datausage;

import android.app.Activity;
import com.android.settings.dashboard.SummaryLoader;
import com.android.settings.datausage.DataUsageSummary;

/* renamed from: com.android.settings.datausage.-$$Lambda$YwlDb-ChrdnT61OB-L_A63UT4To  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$YwlDbChrdnT61OBL_A63UT4To implements SummaryLoader.SummaryProviderFactory {
    public static final /* synthetic */ $$Lambda$YwlDbChrdnT61OBL_A63UT4To INSTANCE = new $$Lambda$YwlDbChrdnT61OBL_A63UT4To();

    private /* synthetic */ $$Lambda$YwlDbChrdnT61OBL_A63UT4To() {
    }

    public final SummaryLoader.SummaryProvider createSummaryProvider(Activity activity, SummaryLoader summaryLoader) {
        return new DataUsageSummary.SummaryProvider(activity, summaryLoader);
    }
}
