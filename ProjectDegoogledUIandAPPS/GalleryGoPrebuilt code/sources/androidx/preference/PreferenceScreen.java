package androidx.preference;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.apps.photosgo.R;

/* compiled from: PG */
public final class PreferenceScreen extends PreferenceGroup {

    /* renamed from: d */
    public boolean f1134d = true;

    public PreferenceScreen(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, C0071co.m4652a(context, (int) R.attr.preferenceScreenStyle, 16842891));
    }

    /* renamed from: r */
    public final boolean mo1205r() {
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo1156a() {
        adt adt;
        if (this.f1120s == null && this.f1121t == null && mo1203g() != 0 && (adt = this.f1112k.f239e) != null) {
            adt.mo205S();
        }
    }
}
