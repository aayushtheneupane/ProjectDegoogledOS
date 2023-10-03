package p000;

import java.util.AbstractList;
import java.util.Comparator;
import p003j$.util.Collection;
import p003j$.util.Collection$$CC;
import p003j$.util.List;
import p003j$.util.List$$CC;
import p003j$.util.Spliterator;
import p003j$.util.function.Predicate;
import p003j$.util.function.UnaryOperator;
import p003j$.util.stream.Stream;

/* renamed from: htz */
/* compiled from: PG */
public class htz extends AbstractList implements List, Collection {

    /* renamed from: a */
    private final java.util.List f13405a;

    /* renamed from: b */
    private final int f13406b = 999;

    public htz(java.util.List list) {
        this.f13405a = list;
    }

    public final Stream parallelStream() {
        return Collection$$CC.parallelStream$$dflt$$(this);
    }

    public final boolean removeIf(Predicate predicate) {
        return Collection$$CC.removeIf$$dflt$$(this, predicate);
    }

    public final void replaceAll(UnaryOperator unaryOperator) {
        List$$CC.replaceAll$$dflt$$(this, unaryOperator);
    }

    public final void sort(Comparator comparator) {
        List$$CC.sort$$dflt$$(this, comparator);
    }

    public final Spliterator spliterator() {
        return List$$CC.spliterator$$dflt$$(this);
    }

    public final Stream stream() {
        return Collection$$CC.stream$$dflt$$(this);
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        ife.m12873b(i, size());
        int i2 = this.f13406b;
        int i3 = i * i2;
        return this.f13405a.subList(i3, Math.min(i2 + i3, this.f13405a.size()));
    }

    public final boolean isEmpty() {
        return this.f13405a.isEmpty();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0047, code lost:
        if (((r3 & true) & r5) == false) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004a, code lost:
        if (r4 <= 0) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004d, code lost:
        if (r0 > 0) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0050, code lost:
        if (r0 < 0) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0053, code lost:
        return r3 + r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int size() {
        /*
            r8 = this;
            java.util.List r0 = r8.f13405a
            int r0 = r0.size()
            int r1 = r8.f13406b
            java.math.RoundingMode r2 = java.math.RoundingMode.CEILING
            p000.ife.m12898e((java.lang.Object) r2)
            if (r1 == 0) goto L_0x005e
            int r3 = r0 / r1
            int r4 = r1 * r3
            int r4 = r0 - r4
            if (r4 == 0) goto L_0x005d
            r0 = r0 ^ r1
            int r0 = r0 >> 31
            r5 = 1
            r0 = r0 | r5
            int[] r6 = p000.iaz.f13815a
            int r7 = r2.ordinal()
            r6 = r6[r7]
            r7 = 0
            switch(r6) {
                case 1: goto L_0x0054;
                case 2: goto L_0x005d;
                case 3: goto L_0x0050;
                case 4: goto L_0x0052;
                case 5: goto L_0x004d;
                case 6: goto L_0x002e;
                case 7: goto L_0x002e;
                case 8: goto L_0x002e;
                default: goto L_0x0028;
            }
        L_0x0028:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            throw r0
        L_0x002e:
            int r4 = java.lang.Math.abs(r4)
            int r1 = java.lang.Math.abs(r1)
            int r1 = r1 - r4
            int r4 = r4 - r1
            if (r4 != 0) goto L_0x004a
            java.math.RoundingMode r1 = java.math.RoundingMode.HALF_UP
            if (r2 == r1) goto L_0x004f
            java.math.RoundingMode r1 = java.math.RoundingMode.HALF_EVEN
            if (r2 != r1) goto L_0x0043
            goto L_0x0044
        L_0x0043:
            r5 = 0
        L_0x0044:
            r1 = r3 & 1
            r1 = r1 & r5
            if (r1 != 0) goto L_0x004f
            goto L_0x005d
        L_0x004a:
            if (r4 > 0) goto L_0x004f
            goto L_0x005d
        L_0x004d:
            if (r0 <= 0) goto L_0x005d
        L_0x004f:
            goto L_0x0052
        L_0x0050:
            if (r0 >= 0) goto L_0x005d
        L_0x0052:
            int r3 = r3 + r0
            return r3
        L_0x0054:
            if (r4 != 0) goto L_0x0057
            goto L_0x0059
        L_0x0057:
            r5 = 0
        L_0x0059:
            p000.ife.m12844a((boolean) r5)
            return r3
        L_0x005d:
            return r3
        L_0x005e:
            java.lang.ArithmeticException r0 = new java.lang.ArithmeticException
            java.lang.String r1 = "/ by zero"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.htz.size():int");
    }
}
