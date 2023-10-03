package p000;

import android.widget.CompoundButton;
import androidx.preference.CheckBoxPreference;

/* renamed from: ace */
/* compiled from: PG */
public final class ace implements CompoundButton.OnCheckedChangeListener {

    /* renamed from: a */
    private final /* synthetic */ CheckBoxPreference f177a;

    public ace(CheckBoxPreference checkBoxPreference) {
        this.f177a = checkBoxPreference;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (!this.f177a.mo1179b((Object) Boolean.valueOf(z))) {
            compoundButton.setChecked(!z);
        } else {
            this.f177a.mo1212d(z);
        }
    }
}
