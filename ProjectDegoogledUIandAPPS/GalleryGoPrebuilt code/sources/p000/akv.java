package p000;

import android.database.Cursor;

/* renamed from: akv */
/* compiled from: PG */
public final class akv implements aks {

    /* renamed from: a */
    private final C0053bx f698a;

    /* renamed from: b */
    private final C0047br f699b;

    /* renamed from: c */
    private final C0059cc f700c;

    public akv(C0053bx bxVar) {
        this.f698a = bxVar;
        this.f699b = new akt(bxVar);
        this.f700c = new aku(bxVar);
    }

    /* renamed from: a */
    public final akr mo586a(String str) {
        akr akr;
        C0057ca a = C0057ca.m3923a("SELECT `SystemIdInfo`.`work_spec_id` AS `work_spec_id`, `SystemIdInfo`.`system_id` AS `system_id` FROM SystemIdInfo WHERE work_spec_id=?", 1);
        if (str == null) {
            a.mo1920a(1);
        } else {
            a.mo1922a(1, str);
        }
        this.f698a.mo2844d();
        Cursor a2 = this.f698a.mo2840a((C0036bg) a);
        try {
            int a3 = C0350mt.m14757a(a2, "work_spec_id");
            int a4 = C0350mt.m14757a(a2, "system_id");
            if (a2.moveToFirst()) {
                akr = new akr(a2.getString(a3), a2.getInt(a4));
            } else {
                akr = null;
            }
            return akr;
        } finally {
            a2.close();
            a.mo2953b();
        }
    }

    /* renamed from: a */
    public final void mo587a(akr akr) {
        this.f698a.mo2844d();
        this.f698a.mo2845e();
        try {
            this.f699b.mo2686a(akr);
            this.f698a.mo2847g();
        } finally {
            this.f698a.mo2846f();
        }
    }

    /* renamed from: b */
    public final void mo588b(String str) {
        this.f698a.mo2844d();
        C0037bh b = this.f700c.mo3016b();
        if (str == null) {
            b.mo1920a(1);
        } else {
            b.mo1922a(1, str);
        }
        this.f698a.mo2845e();
        try {
            b.mo2033b();
            this.f698a.mo2847g();
        } finally {
            this.f698a.mo2846f();
            this.f700c.mo3015a(b);
        }
    }
}
