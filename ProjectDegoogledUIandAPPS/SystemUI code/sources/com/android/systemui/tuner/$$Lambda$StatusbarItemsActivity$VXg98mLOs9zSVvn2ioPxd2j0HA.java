package com.android.systemui.tuner;

import com.android.systemui.fragments.FragmentService;
import java.util.function.Consumer;

/* renamed from: com.android.systemui.tuner.-$$Lambda$StatusbarItemsActivity$VXg98mLOs9-zSVvn2ioPxd2j0HA  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$StatusbarItemsActivity$VXg98mLOs9zSVvn2ioPxd2j0HA implements Consumer {
    public static final /* synthetic */ $$Lambda$StatusbarItemsActivity$VXg98mLOs9zSVvn2ioPxd2j0HA INSTANCE = new $$Lambda$StatusbarItemsActivity$VXg98mLOs9zSVvn2ioPxd2j0HA();

    private /* synthetic */ $$Lambda$StatusbarItemsActivity$VXg98mLOs9zSVvn2ioPxd2j0HA() {
    }

    public final void accept(Object obj) {
        ((FragmentService) obj).destroyAll();
    }
}
