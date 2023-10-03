package p000;

import androidx.preference.Preference;

/* renamed from: ebr */
/* compiled from: PG */
final /* synthetic */ class ebr implements acz {

    /* renamed from: a */
    private final eby f7862a;

    public ebr(eby eby) {
        this.f7862a = eby;
    }

    /* renamed from: a */
    public final boolean mo184a(Preference preference, Object obj) {
        eby eby = this.f7862a;
        if (((Boolean) obj).booleanValue()) {
            cwn.m5509a(eby.f7894t.mo4814b(), "SettingsFragmentPeer: Failed to accept user consent.", new Object[0]);
            return true;
        }
        ecf ecf = new ecf();
        ftr.m9611b(ecf);
        ftr.m9610a((C0147fh) ecf);
        ecf.mo5429b(eby.f7878d.mo5659r(), "TurnOffFaceGroupingConfirmationDialogFragmentPeer");
        return false;
    }
}
