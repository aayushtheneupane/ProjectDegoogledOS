package com.android.systemui.statusbar;

import com.android.systemui.statusbar.SysuiStatusBarStateController;
import java.util.function.ToIntFunction;

/* renamed from: com.android.systemui.statusbar.-$$Lambda$StatusBarStateControllerImpl$7y8VOe44iFeEd9HPscwVVB7kUfw */
/* compiled from: lambda */
public final /* synthetic */ class C1067xf20ff57f implements ToIntFunction {
    public static final /* synthetic */ C1067xf20ff57f INSTANCE = new C1067xf20ff57f();

    private /* synthetic */ C1067xf20ff57f() {
    }

    public final int applyAsInt(Object obj) {
        return ((SysuiStatusBarStateController.RankedListener) obj).mRank;
    }
}
