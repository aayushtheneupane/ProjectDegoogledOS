package com.android.messaging.p041ui.p042a;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import com.android.messaging.util.C1464na;

/* renamed from: com.android.messaging.ui.a.j */
public class C1083j {
    /* renamed from: a */
    public static void m2687a(ViewGroup viewGroup, View view, boolean z, int i) {
        if (C1464na.m3756Wj() && (view.getContext() instanceof Activity)) {
            new C1082i(view, viewGroup, z, i).startAnimation();
        }
    }
}
