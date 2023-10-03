package com.android.messaging.p041ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1486ya;

/* renamed from: com.android.messaging.ui.BaseBugleActivity */
public class BaseBugleActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (C1486ya.m3856d(this)) {
        }
    }

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
