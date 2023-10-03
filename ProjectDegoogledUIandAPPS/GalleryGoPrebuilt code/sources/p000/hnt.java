package p000;

import androidx.preference.Preference;

/* renamed from: hnt */
/* compiled from: PG */
final /* synthetic */ class hnt implements acz {

    /* renamed from: a */
    private final hnu f13125a;

    /* renamed from: b */
    private final String f13126b;

    /* renamed from: c */
    private final acz f13127c;

    public hnt(hnu hnu, String str, acz acz) {
        this.f13125a = hnu;
        this.f13126b = str;
        this.f13127c = acz;
    }

    /* renamed from: a */
    public final boolean mo184a(Preference preference, Object obj) {
        hnu hnu = this.f13125a;
        String str = this.f13126b;
        acz acz = this.f13127c;
        hlp a = hnu.f13128a.mo7577a(str);
        try {
            boolean a2 = acz.mo184a(preference, obj);
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
