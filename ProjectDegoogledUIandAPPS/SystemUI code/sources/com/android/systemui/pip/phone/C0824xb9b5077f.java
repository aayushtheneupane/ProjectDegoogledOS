package com.android.systemui.pip.phone;

import com.android.systemui.pip.phone.PipMenuActivityController;
import java.util.function.Consumer;

/* renamed from: com.android.systemui.pip.phone.-$$Lambda$PipMenuActivityController$1$rDXDKqpw1CLC0fwevwYEng68Bps */
/* compiled from: lambda */
public final /* synthetic */ class C0824xb9b5077f implements Consumer {
    public static final /* synthetic */ C0824xb9b5077f INSTANCE = new C0824xb9b5077f();

    private /* synthetic */ C0824xb9b5077f() {
    }

    public final void accept(Object obj) {
        ((PipMenuActivityController.Listener) obj).onPipDismiss();
    }
}
