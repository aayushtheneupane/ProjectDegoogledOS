package com.google.android.apps.photosgo.settings;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import androidx.preference.SwitchPreference;

/* compiled from: PG */
public final class LinkableSwitchPreference extends SwitchPreference {
    public LinkableSwitchPreference(Context context) {
        super(context);
    }

    /* renamed from: a */
    public final void mo169a(ady ady) {
        super.mo169a(ady);
        ((TextView) ady.mo235c(16908304)).setMovementMethod(LinkMovementMethod.getInstance());
    }
}
