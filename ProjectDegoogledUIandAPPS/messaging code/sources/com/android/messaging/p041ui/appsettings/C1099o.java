package com.android.messaging.p041ui.appsettings;

import android.preference.Preference;

/* renamed from: com.android.messaging.ui.appsettings.o */
class C1099o implements Preference.OnPreferenceClickListener {
    final /* synthetic */ C1101q this$0;

    C1099o(C1101q qVar) {
        this.this$0 = qVar;
    }

    public boolean onPreferenceClick(Preference preference) {
        C1098n.m2719f(this.this$0.getActivity(), this.this$0.mSubId);
        return true;
    }
}
