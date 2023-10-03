package p000;

import android.database.Cursor;

/* renamed from: akq */
/* compiled from: PG */
public final class akq implements ako {

    /* renamed from: a */
    private final C0053bx f694a;

    /* renamed from: b */
    private final C0047br f695b;

    public akq(C0053bx bxVar) {
        this.f694a = bxVar;
        this.f695b = new akp(bxVar);
    }

    /* renamed from: a */
    public final Long mo582a(String str) {
        C0057ca a = C0057ca.m3923a("SELECT long_value FROM Preference where `key`=?", 1);
        a.mo1922a(1, str);
        this.f694a.mo2844d();
        Cursor a2 = this.f694a.mo2840a((C0036bg) a);
        try {
            Long l = null;
            if (a2.moveToFirst() && !a2.isNull(0)) {
                l = Long.valueOf(a2.getLong(0));
            }
            return l;
        } finally {
            a2.close();
            a.mo2953b();
        }
    }

    /* renamed from: a */
    public final void mo583a(akn akn) {
        this.f694a.mo2844d();
        this.f694a.mo2845e();
        try {
            this.f695b.mo2686a(akn);
            this.f694a.mo2847g();
        } finally {
            this.f694a.mo2846f();
        }
    }
}
