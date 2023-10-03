package p000;

import android.view.View;
import android.widget.AdapterView;
import androidx.preference.DropDownPreference;

/* renamed from: acg */
/* compiled from: PG */
public final class acg implements AdapterView.OnItemSelectedListener {

    /* renamed from: a */
    private final /* synthetic */ DropDownPreference f178a;

    public acg(DropDownPreference dropDownPreference) {
        this.f178a = dropDownPreference;
    }

    public final void onNothingSelected(AdapterView adapterView) {
    }

    public final void onItemSelected(AdapterView adapterView, View view, int i, long j) {
        if (i >= 0) {
            String charSequence = this.f178a.f1083h[i].toString();
            if (!charSequence.equals(this.f178a.f1084i) && this.f178a.mo1179b((Object) charSequence)) {
                this.f178a.mo1161a(charSequence);
            }
        }
    }
}
