package p000;

import java.util.concurrent.Executor;

/* renamed from: ebz */
/* compiled from: PG */
final class ebz implements hol {

    /* renamed from: a */
    private final /* synthetic */ eby f7901a;

    public ebz(eby eby) {
        this.f7901a = eby;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ hom mo2639a(hoi hoi) {
        ecm ecm = (ecm) hoi;
        eby eby = this.f7901a;
        eho eho = eby.f7894t;
        ieh a = gte.m10771a(eho.mo4812a(), (icf) new ehl(eho), (Executor) idh.f13918a);
        cwn.m5509a(a, "SettingsFragmentPeer: Failed to revoke user consent to face grouping", new Object[0]);
        cwn.m5509a(gte.m10769a(a).mo7611a((ice) new ebs(eby), (Executor) idh.f13918a), "SettingsFragmentPeer: Wipeout job.", new Object[0]);
        eby.f7897w.mo1212d(false);
        return hom.f13155a;
    }
}
