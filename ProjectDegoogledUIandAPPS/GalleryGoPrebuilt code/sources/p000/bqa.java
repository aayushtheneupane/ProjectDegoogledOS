package p000;

import java.util.ArrayList;
import java.util.List;

/* renamed from: bqa */
/* compiled from: PG */
public final class bqa implements gvx {

    /* renamed from: a */
    private final bqb f3348a;

    private bqa(bqb bqb) {
        this.f3348a = bqb;
    }

    /* renamed from: a */
    private static void m3350a(bpz bpz, bpz bpz2, C0634xg xgVar) {
        int i = bpz2.f3336a;
        int i2 = bpz.f3336a;
        int i3 = (i - i2) + 1;
        int i4 = bpz.f3337b - 1;
        if (i4 == 0) {
            xgVar.mo10537b(i2, i3);
        } else if (i4 != 1) {
            xgVar.f16287a.mo10543a(i2, i3);
        } else {
            xgVar.mo10539c(i2, i3);
        }
    }

    /* renamed from: a */
    public final void mo2661a(List list, List list2, hpq hpq, C0634xg xgVar) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        while (i < list.size() && i2 < list2.size()) {
            int a = this.f3348a.mo2662a(list.get(i), list2.get(i2));
            int i3 = a - 1;
            if (a == 0) {
                throw null;
            } else if (i3 == 0) {
                arrayList.add(bpz.m3348b(i));
                i++;
            } else if (i3 == 1) {
                i++;
                i2++;
            } else if (i3 == 2) {
                arrayList.add(new bpz(3, i));
                i++;
                i2++;
            } else if (i3 == 3) {
                arrayList.add(bpz.m3347a(i));
                i2++;
            }
        }
        while (i < list.size()) {
            arrayList.add(bpz.m3348b(i));
            i++;
        }
        while (i2 < list2.size()) {
            arrayList.add(bpz.m3347a(i));
            i2++;
        }
        if (!arrayList.isEmpty()) {
            bpz bpz = (bpz) arrayList.get(arrayList.size() - 1);
            int size = arrayList.size() - 2;
            bpz bpz2 = bpz;
            while (size >= 0) {
                bpz bpz3 = (bpz) arrayList.get(size);
                int i4 = bpz3.f3337b;
                int i5 = bpz2.f3337b;
                int i6 = bpz3.f3336a;
                int i7 = bpz.f3336a + 1;
                if (i4 != i5 || i6 != i7) {
                    m3350a(bpz, bpz2, xgVar);
                    bpz2 = bpz3;
                }
                size--;
                bpz = bpz3;
            }
            m3350a(bpz, bpz2, xgVar);
        }
    }

    /* renamed from: a */
    public static bqa m3349a(bqb bqb) {
        return new bqa(bqb);
    }
}
