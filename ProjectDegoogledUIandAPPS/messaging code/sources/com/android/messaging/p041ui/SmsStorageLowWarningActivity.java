package com.android.messaging.p041ui;

import android.app.FragmentTransaction;
import android.os.Bundle;

/* renamed from: com.android.messaging.ui.SmsStorageLowWarningActivity */
public class SmsStorageLowWarningActivity extends BaseBugleFragmentActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.add(C1272ma.newInstance(), (String) null);
        beginTransaction.commit();
    }
}
