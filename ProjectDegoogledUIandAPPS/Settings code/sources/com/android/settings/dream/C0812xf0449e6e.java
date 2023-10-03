package com.android.settings.dream;

import com.android.settingslib.dream.DreamBackend;
import java.util.function.Predicate;

/* renamed from: com.android.settings.dream.-$$Lambda$CurrentDreamPreferenceController$JJd0D4Ql1FstWgOpYrMCLEB2pnU */
/* compiled from: lambda */
public final /* synthetic */ class C0812xf0449e6e implements Predicate {
    public static final /* synthetic */ C0812xf0449e6e INSTANCE = new C0812xf0449e6e();

    private /* synthetic */ C0812xf0449e6e() {
    }

    public final boolean test(Object obj) {
        return ((DreamBackend.DreamInfo) obj).isActive;
    }
}
