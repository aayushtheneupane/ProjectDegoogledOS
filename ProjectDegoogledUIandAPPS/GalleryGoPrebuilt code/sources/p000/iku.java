package p000;

import java.util.ArrayDeque;
import java.util.Arrays;

/* renamed from: iku */
/* compiled from: PG */
final class iku {

    /* renamed from: a */
    public final ArrayDeque f14410a = new ArrayDeque();

    private iku() {
    }

    public /* synthetic */ iku(byte[] bArr) {
    }

    /* renamed from: a */
    public final void mo8880a(ihw ihw) {
        if (ihw.mo8605d()) {
            int a = m13870a(ihw.mo8597a());
            int d = ikx.m13879d(a + 1);
            if (this.f14410a.isEmpty() || ((ihw) this.f14410a.peek()).mo8597a() >= d) {
                this.f14410a.push(ihw);
                return;
            }
            int d2 = ikx.m13879d(a);
            ihw ihw2 = (ihw) this.f14410a.pop();
            while (!this.f14410a.isEmpty() && ((ihw) this.f14410a.peek()).mo8597a() < d2) {
                ihw2 = new ikx((ihw) this.f14410a.pop(), ihw2, (byte[]) null);
            }
            ikx ikx = new ikx(ihw2, ihw, (byte[]) null);
            while (!this.f14410a.isEmpty() && ((ihw) this.f14410a.peek()).mo8597a() < ikx.m13879d(m13870a(ikx.f14421d) + 1)) {
                ikx = new ikx((ihw) this.f14410a.pop(), ikx, (byte[]) null);
            }
            this.f14410a.push(ikx);
        } else if (ihw instanceof ikx) {
            ikx ikx2 = (ikx) ihw;
            int[] iArr = ikx.f14420a;
            mo8880a(ikx2.f14422e);
            mo8880a(ikx2.f14423f);
        } else {
            String valueOf = String.valueOf(ihw.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 49);
            sb.append("Has a new type of ByteString been created? Found ");
            sb.append(valueOf);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /* renamed from: a */
    private static final int m13870a(int i) {
        int binarySearch = Arrays.binarySearch(ikx.f14420a, i);
        return binarySearch < 0 ? (-(binarySearch + 1)) - 1 : binarySearch;
    }
}
