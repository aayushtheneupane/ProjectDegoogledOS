package p000;

/* renamed from: hse */
/* compiled from: PG */
public abstract class hse {
    /* renamed from: b */
    public abstract void mo7874b(Object obj);

    /* renamed from: a */
    public void mo7872a(Iterable iterable) {
        for (Object b : iterable) {
            mo7874b(b);
        }
    }

    /* renamed from: a */
    static int m12003a(int i, int i2) {
        int i3 = i + (i >> 1) + 1;
        if (i3 < i2) {
            int highestOneBit = Integer.highestOneBit(i2 - 1);
            i3 = highestOneBit + highestOneBit;
        }
        if (i3 < 0) {
            return Integer.MAX_VALUE;
        }
        return i3;
    }
}
