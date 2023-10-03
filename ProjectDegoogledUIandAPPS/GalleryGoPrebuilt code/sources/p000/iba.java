package p000;

import android.support.p002v7.widget.RecyclerView;
import java.math.BigInteger;

/* renamed from: iba */
/* compiled from: PG */
public final class iba {

    /* renamed from: a */
    public static final int[] f13823a = new int[37];

    /* renamed from: b */
    private static final long[] f13824b = new long[37];

    /* renamed from: c */
    private static final int[] f13825c = new int[37];

    static {
        long j;
        BigInteger bigInteger = new BigInteger("10000000000000000", 16);
        for (int i = 2; i <= 36; i++) {
            long j2 = (long) i;
            long[] jArr = f13824b;
            long j3 = -1;
            if (j2 < 0) {
                j = ife.m12807a(-1, j2) >= 0 ? 1 : 0;
            } else {
                long j4 = RecyclerView.FOREVER_NS / j2;
                long j5 = j4 + j4;
                j = j5 + ((long) (ife.m12807a(-1 - (j5 * j2), j2) >= 0 ? 1 : 0));
            }
            jArr[i] = j;
            int[] iArr = f13825c;
            if (j2 >= 0) {
                long j6 = RecyclerView.FOREVER_NS / j2;
                long j7 = -1 - ((j6 + j6) * j2);
                if (ife.m12807a(j7, j2) < 0) {
                    j2 = 0;
                }
                j3 = j7 - j2;
            } else if (ife.m12807a(-1, j2) >= 0) {
                j3 = -1 - j2;
            }
            iArr[i] = (int) j3;
            f13823a[i] = bigInteger.toString(i).length() - 1;
        }
    }

    /* renamed from: a */
    public static boolean m12598a(long j, int i) {
        if (j < 0) {
            return true;
        }
        long[] jArr = f13824b;
        if (j >= jArr[16]) {
            return j > jArr[16] || i > f13825c[16];
        }
        return false;
    }
}
