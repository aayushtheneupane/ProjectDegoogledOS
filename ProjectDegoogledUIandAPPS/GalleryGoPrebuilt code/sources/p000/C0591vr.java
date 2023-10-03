package p000;

import java.util.Comparator;
import p003j$.util.Comparator$$CC;
import p003j$.util.function.Function;
import p003j$.util.function.ToDoubleFunction;
import p003j$.util.function.ToIntFunction;
import p003j$.util.function.ToLongFunction;

/* renamed from: vr */
/* compiled from: PG */
final class C0591vr implements Comparator, p003j$.util.Comparator {
    public final Comparator reversed() {
        return Comparator$$CC.reversed$$dflt$$(this);
    }

    public final Comparator thenComparing(Function function) {
        return Comparator$$CC.thenComparing$$dflt$$((Comparator) this, function);
    }

    public final Comparator thenComparing(Function function, Comparator comparator) {
        return Comparator$$CC.thenComparing$$dflt$$(this, function, comparator);
    }

    public final Comparator thenComparing(Comparator comparator) {
        return Comparator$$CC.thenComparing$$dflt$$((Comparator) this, comparator);
    }

    public final Comparator thenComparingDouble(ToDoubleFunction toDoubleFunction) {
        return Comparator$$CC.thenComparingDouble$$dflt$$(this, toDoubleFunction);
    }

    public final Comparator thenComparingInt(ToIntFunction toIntFunction) {
        return Comparator$$CC.thenComparingInt$$dflt$$(this, toIntFunction);
    }

    public final Comparator thenComparingLong(ToLongFunction toLongFunction) {
        return Comparator$$CC.thenComparingLong$$dflt$$(this, toLongFunction);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x001a A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ int compare(java.lang.Object r7, java.lang.Object r8) {
        /*
            r6 = this;
            vt r7 = (p000.C0593vt) r7
            vt r8 = (p000.C0593vt) r8
            android.support.v7.widget.RecyclerView r0 = r7.f16158d
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x000c
            r3 = 0
            goto L_0x000d
        L_0x000c:
            r3 = 1
        L_0x000d:
            android.support.v7.widget.RecyclerView r4 = r8.f16158d
            if (r4 == 0) goto L_0x0013
            r4 = 0
            goto L_0x0015
        L_0x0013:
            r4 = 1
        L_0x0015:
            r5 = -1
            if (r3 == r4) goto L_0x001d
            if (r0 == 0) goto L_0x001c
        L_0x001a:
            r1 = -1
            goto L_0x0038
        L_0x001c:
            return r2
        L_0x001d:
            boolean r0 = r7.f16155a
            boolean r3 = r8.f16155a
            if (r0 == r3) goto L_0x0027
            if (r0 == 0) goto L_0x0026
            goto L_0x001a
        L_0x0026:
            return r2
        L_0x0027:
            int r0 = r8.f16156b
            int r2 = r7.f16156b
            int r0 = r0 - r2
            if (r0 != 0) goto L_0x0037
            int r7 = r7.f16157c
            int r8 = r8.f16157c
            int r7 = r7 - r8
            if (r7 != 0) goto L_0x0036
            goto L_0x0038
        L_0x0036:
            return r7
        L_0x0037:
            r1 = r0
        L_0x0038:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0591vr.compare(java.lang.Object, java.lang.Object):int");
    }
}
