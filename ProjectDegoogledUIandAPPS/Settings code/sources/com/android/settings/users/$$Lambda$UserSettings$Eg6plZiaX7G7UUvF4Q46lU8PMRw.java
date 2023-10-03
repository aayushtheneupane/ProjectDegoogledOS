package com.android.settings.users;

import android.app.Activity;
import com.android.settings.dashboard.SummaryLoader;

/* renamed from: com.android.settings.users.-$$Lambda$UserSettings$Eg6plZiaX7G7UUvF4Q46lU8PMRw  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$UserSettings$Eg6plZiaX7G7UUvF4Q46lU8PMRw implements SummaryLoader.SummaryProviderFactory {
    public static final /* synthetic */ $$Lambda$UserSettings$Eg6plZiaX7G7UUvF4Q46lU8PMRw INSTANCE = new $$Lambda$UserSettings$Eg6plZiaX7G7UUvF4Q46lU8PMRw();

    private /* synthetic */ $$Lambda$UserSettings$Eg6plZiaX7G7UUvF4Q46lU8PMRw() {
    }

    public final SummaryLoader.SummaryProvider createSummaryProvider(Activity activity, SummaryLoader summaryLoader) {
        return UserSettings.lambda$static$1(activity, summaryLoader);
    }
}
