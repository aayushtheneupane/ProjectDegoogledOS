package com.android.messaging.p041ui.appsettings;

import android.content.ActivityNotFoundException;
import android.preference.Preference;
import com.android.messaging.p041ui.C1040Ea;
import com.android.messaging.util.C1430e;

/* renamed from: com.android.messaging.ui.appsettings.p */
class C1100p implements Preference.OnPreferenceClickListener {
    final /* synthetic */ C1101q this$0;

    C1100p(C1101q qVar) {
        this.this$0 = qVar;
    }

    public boolean onPreferenceClick(Preference preference) {
        try {
            this.this$0.startActivity(C1040Ea.get().mo6973dj());
            return true;
        } catch (ActivityNotFoundException e) {
            C1430e.m3623e("MessagingApp", "Failed to launch wireless alerts activity", e);
            return true;
        }
    }
}
