package com.android.systemui.statusbar.policy;

import com.android.systemui.statusbar.policy.KeyguardMonitor;
import java.util.function.Consumer;

/* renamed from: com.android.systemui.statusbar.policy.-$$Lambda$CusFj6pVztwBZlitsnMLA9Hx95I  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$CusFj6pVztwBZlitsnMLA9Hx95I implements Consumer {
    public static final /* synthetic */ $$Lambda$CusFj6pVztwBZlitsnMLA9Hx95I INSTANCE = new $$Lambda$CusFj6pVztwBZlitsnMLA9Hx95I();

    private /* synthetic */ $$Lambda$CusFj6pVztwBZlitsnMLA9Hx95I() {
    }

    public final void accept(Object obj) {
        ((KeyguardMonitor.Callback) obj).onKeyguardShowingChanged();
    }
}
