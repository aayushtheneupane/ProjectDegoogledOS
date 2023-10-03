package p000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: fod */
/* compiled from: PG */
final class fod implements fof {

    /* renamed from: a */
    private static final int[] f10135a = {0, 4, 8, 10, 12, 14, 16, 18, 20, 25, 30, 40, 50, 60, 70, 80, 90, 100, 150, 200, 300, 400, 500, 600, 700, 800, 900, 1000};

    /* renamed from: b */
    private final int[] f10136b = new int[f10135a.length];

    /* renamed from: c */
    private int f10137c;

    /* renamed from: d */
    private int f10138d;

    /* renamed from: e */
    private int f10139e;

    /* renamed from: f */
    private int f10140f;

    /* renamed from: a */
    public final boolean mo5996a() {
        return this.f10138d != 0;
    }

    /* renamed from: a */
    public final void mo5995a(int i, int i2) {
        ife.m12890c(i >= 0);
        this.f10138d++;
        if (i > i2) {
            this.f10137c++;
        }
        int[] iArr = this.f10136b;
        int binarySearch = Arrays.binarySearch(f10135a, i);
        if (binarySearch < 0) {
            binarySearch = -(binarySearch + 2);
        }
        iArr[binarySearch] = iArr[binarySearch] + 1;
        this.f10139e = Math.max(this.f10139e, i);
        this.f10140f += i;
    }

    /* renamed from: b */
    public final irw mo5997b() {
        boolean z;
        if (!mo5996a()) {
            return null;
        }
        iir g = irw.f14932h.mo8793g();
        int i = this.f10137c;
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        irw irw = (irw) g.f14318b;
        int i2 = irw.f14934a | 1;
        irw.f14934a = i2;
        irw.f14935b = i;
        int i3 = this.f10138d;
        int i4 = i2 | 2;
        irw.f14934a = i4;
        irw.f14936c = i3;
        int i5 = this.f10140f;
        int i6 = i4 | 8;
        irw.f14934a = i6;
        irw.f14938e = i5;
        int i7 = this.f10139e;
        irw.f14934a = i6 | 4;
        irw.f14937d = i7;
        int[] iArr = this.f10136b;
        ArrayList arrayList = new ArrayList();
        int i8 = 0;
        while (true) {
            int[] iArr2 = f10135a;
            if (i8 >= iArr2.length) {
                break;
            }
            if (iArr[i8] > 0) {
                int i9 = i8 + 1;
                int length = iArr2.length;
                int i10 = iArr[i8];
                int i11 = iArr2[i8];
                Integer valueOf = i9 != length ? Integer.valueOf(iArr2[i9] - 1) : null;
                if (i10 > 0) {
                    z = true;
                } else {
                    z = false;
                }
                ife.m12890c(z);
                iir g2 = irv.f14926e.mo8793g();
                if (g2.f14319c) {
                    g2.mo8751b();
                    g2.f14319c = false;
                }
                irv irv = (irv) g2.f14318b;
                int i12 = irv.f14928a | 2;
                irv.f14928a = i12;
                irv.f14930c = i11;
                irv.f14928a = i12 | 1;
                irv.f14929b = i10;
                if (valueOf != null) {
                    int intValue = valueOf.intValue();
                    if (g2.f14319c) {
                        g2.mo8751b();
                        g2.f14319c = false;
                    }
                    irv irv2 = (irv) g2.f14318b;
                    irv2.f14928a |= 4;
                    irv2.f14931d = intValue;
                }
                arrayList.add((irv) g2.mo8770g());
            }
            i8++;
        }
        List asList = Arrays.asList((irv[]) arrayList.toArray(new irv[0]));
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        irw irw2 = (irw) g.f14318b;
        if (!irw2.f14939f.mo8521a()) {
            irw2.f14939f = iix.m13608a(irw2.f14939f);
        }
        igz.m12986a((Iterable) asList, (List) irw2.f14939f);
        return (irw) g.mo8770g();
    }
}
