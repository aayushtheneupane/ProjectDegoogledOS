package com.android.messaging.p041ui;

import android.view.View;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.ui.qa */
public class C1376qa {
    private final View mAnchorView;

    /* renamed from: qG */
    private final boolean f2195qG;

    private C1376qa(View view, boolean z) {
        C1424b.m3594t(view);
        this.mAnchorView = view;
        this.f2195qG = z;
    }

    /* renamed from: f */
    public static C1376qa m3512f(View view) {
        return new C1376qa(view, true);
    }

    /* renamed from: Zi */
    public boolean mo7994Zi() {
        return this.f2195qG;
    }

    public View getAnchorView() {
        return this.mAnchorView;
    }
}
