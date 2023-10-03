package p000;

import java.util.List;

/* renamed from: gvz */
/* compiled from: PG */
public final class gvz implements gvx {
    /* renamed from: a */
    public final void mo2661a(List list, List list2, hpq hpq, C0634xg xgVar) {
        int size = list.size();
        int size2 = list2.size();
        int min = Math.min(size, size2);
        int i = 0;
        while (i < min) {
            if (!hpq.mo7661b(list.get(i), list2.get(i))) {
                int i2 = i + 1;
                while (i2 < min && !hpq.mo7661b(list.get(i2), list2.get(i2))) {
                    i2++;
                }
                int i3 = i2 - i;
                xgVar.mo10539c(i, i3);
                xgVar.mo10537b(i, i3);
                i = i2;
            }
            i++;
        }
        if (size > min) {
            xgVar.mo10539c(min, size - min);
        }
        if (size2 > min) {
            xgVar.mo10537b(min, size2 - min);
        }
    }
}
