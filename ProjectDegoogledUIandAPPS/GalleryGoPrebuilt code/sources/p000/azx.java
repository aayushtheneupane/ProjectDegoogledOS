package p000;

/* renamed from: azx */
/* compiled from: PG */
final class azx extends azz {
    /* renamed from: b */
    public final int mo1748b(int i, int i2, int i3, int i4) {
        return f1923g ? 2 : 1;
    }

    /* renamed from: a */
    public final float mo1747a(int i, int i2, int i3, int i4) {
        if (f1923g) {
            return Math.min(((float) i3) / ((float) i), ((float) i4) / ((float) i2));
        }
        int max = Math.max(i2 / i4, i / i3);
        if (max != 0) {
            return 1.0f / ((float) Integer.highestOneBit(max));
        }
        return 1.0f;
    }
}
