package p000;

import android.widget.CompoundButton;
import androidx.preference.SwitchPreferenceCompat;

/* renamed from: aef */
/* compiled from: PG */
public final class aef implements CompoundButton.OnCheckedChangeListener {

    /* renamed from: a */
    private final /* synthetic */ SwitchPreferenceCompat f271a;

    public aef(SwitchPreferenceCompat switchPreferenceCompat) {
        this.f271a = switchPreferenceCompat;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (!this.f271a.mo1179b((Object) Boolean.valueOf(z))) {
            compoundButton.setChecked(!z);
        } else {
            this.f271a.mo1212d(z);
        }
    }
}
