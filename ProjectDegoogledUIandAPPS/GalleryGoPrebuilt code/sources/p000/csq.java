package p000;

import android.util.Pair;

/* renamed from: csq */
/* compiled from: PG */
public final class csq implements csp {

    /* renamed from: a */
    private final hsu f5585a;

    /* renamed from: b */
    private final hsp f5586b;

    public csq(hsu hsu, hsp hsp) {
        this.f5585a = hsu;
        this.f5586b = hsp;
    }

    /* renamed from: a */
    public final csb mo3803a(String str, float f) {
        Pair pair = (Pair) this.f5585a.get(str);
        if (pair == null || f <= ((Float) pair.second).floatValue()) {
            return null;
        }
        return (csb) pair.first;
    }

    /* renamed from: b */
    public final hso mo3804b(String str, float f) {
        hso hso = (hso) this.f5586b.f13373b.get(str);
        if (hso == null) {
            hso = hso.m12047f();
        }
        hsj hsj = new hsj();
        hvs i = hso.listIterator();
        while (i.hasNext()) {
            Pair pair = (Pair) i.next();
            if (f > ((Float) pair.second).floatValue()) {
                hsj.mo7908c((csb) pair.first);
            }
        }
        return hsj.mo7905a();
    }
}
