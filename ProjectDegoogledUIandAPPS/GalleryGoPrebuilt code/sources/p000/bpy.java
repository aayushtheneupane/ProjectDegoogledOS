package p000;

import java.util.List;

/* renamed from: bpy */
/* compiled from: PG */
public final class bpy implements gvx {

    /* renamed from: a */
    private static final Object f3334a = new Object();

    /* renamed from: b */
    private final gvw f3335b;

    public bpy(gvw gvw) {
        this.f3335b = gvw;
    }

    /* renamed from: a */
    public final void mo2661a(List list, List list2, hpq hpq, C0634xg xgVar) {
        int i;
        boolean z;
        C0634xg xgVar2 = xgVar;
        int size = list.size();
        int size2 = list2.size();
        int min = Math.min(size, size2);
        int a = m3344a(list, list2, 0, 0, min, 1, hpq);
        if (a == size && size == size2) {
            m3345a(list, list2, 0, a, xgVar);
            return;
        }
        int a2 = m3344a(list, list2, size - 1, size2 - 1, min - a, -1, hpq);
        int i2 = size - a2;
        int i3 = size2 - a2;
        if (a > Math.min(i2, i3)) {
            xgVar.mo10540d();
            return;
        }
        List list3 = list;
        List list4 = list2;
        C0634xg xgVar3 = xgVar;
        m3345a(list3, list4, 0, a, xgVar3);
        m3345a(list3, list4, -a2, a2, xgVar3);
        int i4 = i3 - a;
        int[] iArr = new int[Math.min(i2 - a, i4)];
        int[] iArr2 = new int[i4];
        int i5 = a;
        int i6 = 0;
        boolean z2 = false;
        int i7 = 0;
        while (i5 < i2) {
            Object obj = list.get(i5);
            int i8 = a;
            while (true) {
                if (i8 >= i3) {
                    i = i2;
                    z = false;
                    break;
                }
                Object obj2 = list2.get(i8);
                if (hpq.mo7661b(obj, obj2)) {
                    int i9 = i8 - a;
                    i = i2;
                    if (iArr2[i9] != 1) {
                        iArr[i6] = i9;
                        iArr2[i9] = 1;
                        if (i5 != i8) {
                            z2 = true;
                        }
                        i6++;
                        if (!this.f3335b.mo4278a(obj, obj2)) {
                            xgVar2.mo10535a(i5 - i7, f3334a);
                        }
                        z = true;
                    } else {
                        xgVar.mo10540d();
                        return;
                    }
                } else {
                    int i10 = i2;
                    i8++;
                }
            }
            if (!z) {
                xgVar2.mo10542e(i5 - i7);
                i7++;
            }
            i5++;
            i2 = i;
        }
        if (i6 != 0) {
            int i11 = 0;
            for (int i12 = 0; i12 < i4; i12++) {
                if (iArr2[i12] == 1) {
                    iArr2[i12] = i11;
                    i11++;
                } else {
                    iArr2[i12] = -1;
                }
            }
            for (int i13 = 0; i13 < i6; i13++) {
                iArr[i13] = iArr2[iArr[i13]];
            }
            if (i4 - i6 != 0) {
                int i14 = 0;
                for (int i15 = i4 - 1; i15 >= 0; i15--) {
                    if (iArr2[i15] == -1) {
                        i14++;
                        iArr2[i4 - i14] = i15;
                    }
                }
            }
            if (z2) {
                for (int i16 = 0; i16 < i6; i16++) {
                    iArr2[i16] = i16;
                }
                int i17 = i6 - 1;
                int i18 = 0;
                while (i18 < i6) {
                    int i19 = i18 + 1;
                    for (int i20 = i19; i20 < i6; i20++) {
                        int i21 = iArr2[i18];
                        int i22 = iArr2[i20];
                        if (i21 < i22) {
                            iArr2[i20] = i22 - 1;
                        }
                    }
                    for (int i23 = i17; i23 >= 0; i23--) {
                        int i24 = iArr[i17];
                        int i25 = iArr[i23];
                        if (i24 < i25) {
                            iArr[i23] = i25 - 1;
                        }
                    }
                    i17--;
                    i18 = i19;
                }
                int i26 = 0;
                while (i26 < i6) {
                    int i27 = i26 + 1;
                    for (int i28 = i27; i28 < i6; i28++) {
                        int i29 = iArr[i26];
                        int i30 = iArr2[i28];
                        if (i29 > i30) {
                            iArr[i26] = i29 + 1;
                        } else {
                            iArr2[i28] = i30 + 1;
                        }
                    }
                    i26 = i27;
                }
                for (int i31 = 0; i31 < i6; i31++) {
                    int i32 = iArr2[i31] + a;
                    int i33 = iArr[i31] + a;
                    if (i32 != i33) {
                        xgVar2.mo10534a(i32, i33);
                    }
                }
            }
            while (i6 < i4) {
                xgVar2.mo10541d(iArr2[i6] + a);
                i6++;
            }
            return;
        }
        xgVar2.mo10537b(a, i4);
    }

    /* renamed from: a */
    private static final int m3344a(List list, List list2, int i, int i2, int i3, int i4, hpq hpq) {
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = i5 * i4;
            if (!hpq.mo7661b(list.get(i + i6), list2.get(i6 + i2))) {
                return i5;
            }
        }
        return i3;
    }

    /* renamed from: a */
    private final void m3345a(List list, List list2, int i, int i2, C0634xg xgVar) {
        int size = i < 0 ? list.size() + i : i;
        if (i < 0) {
            i += list2.size();
        }
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = size + i3;
            if (!this.f3335b.mo4278a(list.get(i4), list2.get(i + i3))) {
                xgVar.mo10535a(i4, f3334a);
            }
        }
    }
}
