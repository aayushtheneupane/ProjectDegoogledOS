package p000;

import androidx.preference.Preference;

/* renamed from: hns */
/* compiled from: PG */
final /* synthetic */ class hns implements ada {

    /* renamed from: a */
    private final hnu f13122a;

    /* renamed from: b */
    private final String f13123b;

    /* renamed from: c */
    private final ada f13124c;

    public hns(hnu hnu, String str, ada ada) {
        this.f13122a = hnu;
        this.f13123b = str;
        this.f13124c = ada;
    }

    /* renamed from: a */
    public final boolean mo189a(Preference preference) {
        hnu hnu = this.f13122a;
        String str = this.f13123b;
        ada ada = this.f13124c;
        hlp a = hnu.f13128a.mo7577a(str);
        try {
            boolean a2 = ada.mo189a(preference);
            if (a != null) {
                a.close();
            }
            return a2;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
