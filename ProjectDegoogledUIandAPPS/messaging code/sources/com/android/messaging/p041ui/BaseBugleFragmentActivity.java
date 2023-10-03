package com.android.messaging.p041ui;

import android.app.Activity;
import android.content.Context;
import com.android.messaging.util.C1430e;

/* renamed from: com.android.messaging.ui.BaseBugleFragmentActivity */
public class BaseBugleFragmentActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        C1430e.m3628v("MessagingApp", getLocalClassName() + ".onPause");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        C1430e.m3628v("MessagingApp", getLocalClassName() + ".onResume");
        C1430e.m3614a((Context) this, (Activity) this);
    }
}
