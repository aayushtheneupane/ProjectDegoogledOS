package p000;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.preference.ListPreference;

/* renamed from: acq */
/* compiled from: PG */
public final class acq extends add {

    /* renamed from: Z */
    public int f187Z;

    /* renamed from: aa */
    private CharSequence[] f188aa;

    /* renamed from: ab */
    private CharSequence[] f189ab;

    /* renamed from: R */
    private final ListPreference m211R() {
        return (ListPreference) mo192Q();
    }

    /* renamed from: a */
    public final void mo165a(Bundle bundle) {
        super.mo165a(bundle);
        if (bundle == null) {
            ListPreference R = m211R();
            if (R.f1082g == null || R.f1083h == null) {
                throw new IllegalStateException("ListPreference requires an entries array and an entryValues array.");
            }
            this.f187Z = R.mo1165b(R.f1084i);
            this.f188aa = R.f1082g;
            this.f189ab = R.f1083h;
            return;
        }
        this.f187Z = bundle.getInt("ListPreferenceDialogFragment.index", 0);
        this.f188aa = bundle.getCharSequenceArray("ListPreferenceDialogFragment.entries");
        this.f189ab = bundle.getCharSequenceArray("ListPreferenceDialogFragment.entryValues");
    }

    /* renamed from: b */
    public final void mo167b(boolean z) {
        int i;
        if (z && (i = this.f187Z) >= 0) {
            String charSequence = this.f189ab[i].toString();
            ListPreference R = m211R();
            if (R.mo1179b((Object) charSequence)) {
                R.mo1161a(charSequence);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo175a(C0393oi oiVar) {
        CharSequence[] charSequenceArr = this.f188aa;
        int i = this.f187Z;
        acp acp = new acp(this);
        C0389oe oeVar = oiVar.f15411a;
        oeVar.f15362o = charSequenceArr;
        oeVar.f15364q = acp;
        oeVar.f15371x = i;
        oeVar.f15370w = true;
        oiVar.mo9524a((CharSequence) null, (DialogInterface.OnClickListener) null);
    }

    /* renamed from: e */
    public final void mo168e(Bundle bundle) {
        super.mo168e(bundle);
        bundle.putInt("ListPreferenceDialogFragment.index", this.f187Z);
        bundle.putCharSequenceArray("ListPreferenceDialogFragment.entries", this.f188aa);
        bundle.putCharSequenceArray("ListPreferenceDialogFragment.entryValues", this.f189ab);
    }
}
