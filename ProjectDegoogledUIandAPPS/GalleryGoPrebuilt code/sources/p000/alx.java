package p000;

import androidx.work.impl.WorkDatabase;

/* renamed from: alx */
/* compiled from: PG */
final class alx extends aly {

    /* renamed from: a */
    private final /* synthetic */ aip f745a;

    /* renamed from: b */
    private final /* synthetic */ String f746b;

    public alx(aip aip, String str) {
        this.f745a = aip;
        this.f746b = str;
    }

    /* renamed from: a */
    public final void mo622a() {
        WorkDatabase workDatabase = this.f745a.f554c;
        workDatabase.mo2845e();
        try {
            for (String a : workDatabase.mo1226j().mo616e(this.f746b)) {
                m754a(this.f745a, a);
            }
            workDatabase.mo2847g();
        } finally {
            workDatabase.mo2846f();
        }
    }
}
