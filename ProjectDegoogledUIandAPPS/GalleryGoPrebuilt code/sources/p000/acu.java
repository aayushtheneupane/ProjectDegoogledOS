package p000;

import android.os.Bundle;
import androidx.preference.MultiSelectListPreference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* renamed from: acu */
/* compiled from: PG */
public final class acu extends add {

    /* renamed from: Z */
    public final Set f192Z = new HashSet();

    /* renamed from: aa */
    public boolean f193aa;

    /* renamed from: ab */
    public CharSequence[] f194ab;

    /* renamed from: ad */
    private CharSequence[] f195ad;

    /* renamed from: R */
    private final MultiSelectListPreference m216R() {
        return (MultiSelectListPreference) mo192Q();
    }

    /* renamed from: a */
    public final void mo165a(Bundle bundle) {
        super.mo165a(bundle);
        if (bundle == null) {
            MultiSelectListPreference R = m216R();
            if (R.f1085g == null || R.f1086h == null) {
                throw new IllegalStateException("MultiSelectListPreference requires an entries array and an entryValues array.");
            }
            this.f192Z.clear();
            this.f192Z.addAll(R.f1087i);
            this.f193aa = false;
            this.f195ad = R.f1085g;
            this.f194ab = R.f1086h;
            return;
        }
        this.f192Z.clear();
        this.f192Z.addAll(bundle.getStringArrayList("MultiSelectListPreferenceDialogFragmentCompat.values"));
        this.f193aa = bundle.getBoolean("MultiSelectListPreferenceDialogFragmentCompat.changed", false);
        this.f195ad = bundle.getCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entries");
        this.f194ab = bundle.getCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entryValues");
    }

    /* renamed from: b */
    public final void mo167b(boolean z) {
        if (z && this.f193aa) {
            MultiSelectListPreference R = m216R();
            if (R.mo1179b((Object) this.f192Z)) {
                R.mo1168a(this.f192Z);
            }
        }
        this.f193aa = false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo175a(C0393oi oiVar) {
        int length = this.f194ab.length;
        boolean[] zArr = new boolean[length];
        for (int i = 0; i < length; i++) {
            zArr[i] = this.f192Z.contains(this.f194ab[i].toString());
        }
        CharSequence[] charSequenceArr = this.f195ad;
        act act = new act(this);
        C0389oe oeVar = oiVar.f15411a;
        oeVar.f15362o = charSequenceArr;
        oeVar.f15372y = act;
        oeVar.f15368u = zArr;
        oeVar.f15369v = true;
    }

    /* renamed from: e */
    public final void mo168e(Bundle bundle) {
        super.mo168e(bundle);
        bundle.putStringArrayList("MultiSelectListPreferenceDialogFragmentCompat.values", new ArrayList(this.f192Z));
        bundle.putBoolean("MultiSelectListPreferenceDialogFragmentCompat.changed", this.f193aa);
        bundle.putCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entries", this.f195ad);
        bundle.putCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entryValues", this.f194ab);
    }
}
