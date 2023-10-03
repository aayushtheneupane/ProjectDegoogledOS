package p000;

import android.widget.CompoundButton;
import androidx.preference.SwitchPreference;

/* renamed from: aee */
/* compiled from: PG */
public final class aee implements CompoundButton.OnCheckedChangeListener {

    /* renamed from: a */
    private final /* synthetic */ SwitchPreference f270a;

    public aee(SwitchPreference switchPreference) {
        this.f270a = switchPreference;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (!this.f270a.mo1179b((Object) Boolean.valueOf(z))) {
            compoundButton.setChecked(!z);
        } else {
            this.f270a.mo1212d(z);
        }
    }
}
