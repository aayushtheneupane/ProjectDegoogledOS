package p000;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

/* renamed from: alv */
/* compiled from: PG */
public final class alv implements alt {

    /* renamed from: a */
    private final C0053bx f741a;

    /* renamed from: b */
    private final C0047br f742b;

    public alv(C0053bx bxVar) {
        this.f741a = bxVar;
        this.f742b = new alu(bxVar);
    }

    /* renamed from: a */
    public final List mo620a(String str) {
        C0057ca a = C0057ca.m3923a("SELECT DISTINCT tag FROM worktag WHERE work_spec_id=?", 1);
        if (str == null) {
            a.mo1920a(1);
        } else {
            a.mo1922a(1, str);
        }
        this.f741a.mo2844d();
        Cursor a2 = this.f741a.mo2840a((C0036bg) a);
        try {
            ArrayList arrayList = new ArrayList(a2.getCount());
            while (a2.moveToNext()) {
                arrayList.add(a2.getString(0));
            }
            return arrayList;
        } finally {
            a2.close();
            a.mo2953b();
        }
    }

    /* renamed from: a */
    public final void mo621a(als als) {
        this.f741a.mo2844d();
        this.f741a.mo2845e();
        try {
            this.f742b.mo2686a(als);
            this.f741a.mo2847g();
        } finally {
            this.f741a.mo2846f();
        }
    }
}
