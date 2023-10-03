package com.android.dialer.calllog;

import android.content.ContentProviderResult;
import java.util.function.ToIntFunction;

/* renamed from: com.android.dialer.calllog.-$$Lambda$CallLogCacheUpdater$PDXsbO5vfRm5_q5CqG0DdTVUdPI  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$CallLogCacheUpdater$PDXsbO5vfRm5_q5CqG0DdTVUdPI implements ToIntFunction {
    public static final /* synthetic */ $$Lambda$CallLogCacheUpdater$PDXsbO5vfRm5_q5CqG0DdTVUdPI INSTANCE = new $$Lambda$CallLogCacheUpdater$PDXsbO5vfRm5_q5CqG0DdTVUdPI();

    private /* synthetic */ $$Lambda$CallLogCacheUpdater$PDXsbO5vfRm5_q5CqG0DdTVUdPI() {
    }

    public final int applyAsInt(Object obj) {
        return ((ContentProviderResult) obj).count.intValue();
    }
}
