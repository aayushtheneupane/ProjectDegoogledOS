package p000;

import androidx.work.impl.WorkDatabase;

/* renamed from: amb */
/* compiled from: PG */
public final class amb {

    /* renamed from: a */
    private final WorkDatabase f754a;

    public amb(WorkDatabase workDatabase) {
        this.f754a = workDatabase;
    }

    /* renamed from: a */
    public final int mo639a(String str) {
        this.f754a.mo2845e();
        try {
            Long a = this.f754a.mo1232p().mo582a(str);
            int i = 0;
            int intValue = a != null ? a.intValue() : 0;
            if (intValue != Integer.MAX_VALUE) {
                i = intValue + 1;
            }
            mo640a(str, i);
            this.f754a.mo2847g();
            return intValue;
        } finally {
            this.f754a.mo2846f();
        }
    }

    /* renamed from: a */
    public final void mo640a(String str, int i) {
        this.f754a.mo1232p().mo583a(new akn(str, (long) i));
    }
}
