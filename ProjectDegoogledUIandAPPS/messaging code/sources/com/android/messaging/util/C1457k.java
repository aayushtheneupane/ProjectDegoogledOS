package com.android.messaging.util;

import android.app.Activity;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import com.android.messaging.R;
import com.android.messaging.p041ui.C1040Ea;

/* renamed from: com.android.messaging.util.k */
class C1457k implements Runnable {

    /* renamed from: Zo */
    private final Fragment f2309Zo;
    private final Activity mActivity;

    public C1457k(C1459l lVar, Activity activity, Fragment fragment) {
        this.mActivity = activity;
        this.f2309Zo = fragment;
    }

    public void run() {
        try {
            Intent b = C1040Ea.get().mo6963b(this.mActivity);
            if (this.f2309Zo != null) {
                this.f2309Zo.startActivityForResult(b, 1);
            } else {
                this.mActivity.startActivityForResult(b, 1);
            }
        } catch (ActivityNotFoundException e) {
            C1430e.m3631w("MessagingApp", "Couldn't find activity:", e);
            C1486ya.m3848Pa(R.string.activity_not_found_message);
        }
    }
}
