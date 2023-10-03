package com.android.settings.wfd;

import android.app.Activity;
import com.android.settings.dashboard.SummaryLoader;

/* renamed from: com.android.settings.wfd.-$$Lambda$WifiDisplaySettings$FSGRkDMrB620EgLXH7J2ShDkw60  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$WifiDisplaySettings$FSGRkDMrB620EgLXH7J2ShDkw60 implements SummaryLoader.SummaryProviderFactory {
    public static final /* synthetic */ $$Lambda$WifiDisplaySettings$FSGRkDMrB620EgLXH7J2ShDkw60 INSTANCE = new $$Lambda$WifiDisplaySettings$FSGRkDMrB620EgLXH7J2ShDkw60();

    private /* synthetic */ $$Lambda$WifiDisplaySettings$FSGRkDMrB620EgLXH7J2ShDkw60() {
    }

    public final SummaryLoader.SummaryProvider createSummaryProvider(Activity activity, SummaryLoader summaryLoader) {
        return WifiDisplaySettings.lambda$static$0(activity, summaryLoader);
    }
}
