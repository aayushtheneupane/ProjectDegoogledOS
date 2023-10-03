package p000;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* renamed from: dzc */
/* compiled from: PG */
public final class dzc {

    /* renamed from: a */
    public static final long f7698a = TimeUnit.HOURS.toMillis(4);

    /* renamed from: b */
    public final ehd f7699b;

    public dzc(ehd ehd) {
        this.f7699b = ehd;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final List mo4604a(List list) {
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(ede.m7258a((cxi) list.get(i)));
        }
        return arrayList;
    }
}
