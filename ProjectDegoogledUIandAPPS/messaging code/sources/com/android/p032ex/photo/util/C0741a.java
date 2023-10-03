package com.android.p032ex.photo.util;

/* renamed from: com.android.ex.photo.util.a */
public class C0741a {
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0096, code lost:
        android.util.Log.e("CameraExif", "Invalid length");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x009b, code lost:
        return 0;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int m1190a(java.io.InputStream r11, long r12) {
        /*
            r0 = 0
            if (r11 != 0) goto L_0x0004
            return r0
        L_0x0004:
            com.android.ex.photo.util.f r1 = new com.android.ex.photo.util.f
            r2 = 16
            r1.<init>(r11, r2, r0)
            r11 = 1
            boolean r2 = m1191a(r1, r12, r11)
            if (r2 == 0) goto L_0x0027
            byte r2 = r1.get(r0)
            r3 = -1
            if (r2 != r3) goto L_0x0023
            byte r2 = r1.get(r11)
            r3 = -40
            if (r2 != r3) goto L_0x0023
            r2 = r11
            goto L_0x0024
        L_0x0023:
            r2 = r0
        L_0x0024:
            if (r2 != 0) goto L_0x0027
            return r0
        L_0x0027:
            r2 = r0
        L_0x0028:
            int r3 = r2 + 3
            boolean r3 = m1191a(r1, r12, r3)
            java.lang.String r4 = "CameraExif"
            r5 = 8
            r6 = 2
            r7 = 4
            if (r3 == 0) goto L_0x00a7
            int r3 = r2 + 1
            byte r2 = r1.get(r2)
            r8 = 255(0xff, float:3.57E-43)
            r2 = r2 & r8
            if (r2 != r8) goto L_0x00a4
            byte r2 = r1.get(r3)
            r2 = r2 & r8
            if (r2 != r8) goto L_0x0049
            goto L_0x00a2
        L_0x0049:
            int r3 = r3 + 1
            r8 = 216(0xd8, float:3.03E-43)
            if (r2 == r8) goto L_0x00a2
            if (r2 != r11) goto L_0x0052
            goto L_0x00a2
        L_0x0052:
            r8 = 217(0xd9, float:3.04E-43)
            if (r2 == r8) goto L_0x009c
            r8 = 218(0xda, float:3.05E-43)
            if (r2 != r8) goto L_0x005b
            goto L_0x009c
        L_0x005b:
            int r8 = m1189a(r1, r3, r6, r0)
            if (r8 < r6) goto L_0x0096
            int r9 = r3 + r8
            int r10 = r9 + -1
            boolean r10 = m1191a(r1, r12, r10)
            if (r10 != 0) goto L_0x006c
            goto L_0x0096
        L_0x006c:
            r10 = 225(0xe1, float:3.15E-43)
            if (r2 != r10) goto L_0x008f
            if (r8 < r5) goto L_0x008f
            int r2 = r3 + 2
            int r2 = m1189a(r1, r2, r7, r0)
            r10 = 1165519206(0x45786966, float:3974.5874)
            if (r2 != r10) goto L_0x008f
            int r2 = r3 + 6
            int r2 = m1189a(r1, r2, r6, r0)
            if (r2 != 0) goto L_0x008f
            int r2 = r3 + 8
            int r12 = r8 + -8
            int r13 = r2 + -4
            r1.mo5808fa(r13)
            goto L_0x00a8
        L_0x008f:
            int r2 = r9 + -4
            r1.mo5808fa(r2)
            r2 = r9
            goto L_0x0028
        L_0x0096:
            java.lang.String r11 = "Invalid length"
            android.util.Log.e(r4, r11)
            return r0
        L_0x009c:
            int r12 = r3 + -4
            r1.mo5808fa(r12)
            goto L_0x00a4
        L_0x00a2:
            r2 = r3
            goto L_0x0028
        L_0x00a4:
            r12 = r0
            r2 = r3
            goto L_0x00a8
        L_0x00a7:
            r12 = r0
        L_0x00a8:
            if (r12 <= r5) goto L_0x011d
            int r13 = m1189a(r1, r2, r7, r0)
            r3 = 1229531648(0x49492a00, float:823968.0)
            if (r13 == r3) goto L_0x00be
            r8 = 1296891946(0x4d4d002a, float:2.14958752E8)
            if (r13 == r8) goto L_0x00be
            java.lang.String r11 = "Invalid byte order"
            android.util.Log.e(r4, r11)
            return r0
        L_0x00be:
            if (r13 != r3) goto L_0x00c2
            r13 = r11
            goto L_0x00c3
        L_0x00c2:
            r13 = r0
        L_0x00c3:
            int r3 = r2 + 4
            int r3 = m1189a(r1, r3, r7, r13)
            int r3 = r3 + r6
            r7 = 10
            if (r3 < r7) goto L_0x0118
            if (r3 <= r12) goto L_0x00d1
            goto L_0x0118
        L_0x00d1:
            int r2 = r2 + r3
            int r12 = r12 - r3
            int r3 = r2 + -4
            r1.mo5808fa(r3)
            int r3 = r2 + -2
            int r3 = m1189a(r1, r3, r6, r13)
        L_0x00de:
            int r7 = r3 + -1
            if (r3 <= 0) goto L_0x011d
            r3 = 12
            if (r12 < r3) goto L_0x011d
            int r3 = m1189a(r1, r2, r6, r13)
            r8 = 274(0x112, float:3.84E-43)
            if (r3 != r8) goto L_0x010d
            int r2 = r2 + r5
            int r12 = m1189a(r1, r2, r6, r13)
            if (r12 == r11) goto L_0x010c
            r11 = 3
            if (r12 == r11) goto L_0x0109
            r11 = 6
            if (r12 == r11) goto L_0x0106
            if (r12 == r5) goto L_0x0103
            java.lang.String r11 = "Unsupported orientation"
            android.util.Log.i(r4, r11)
            return r0
        L_0x0103:
            r11 = 270(0x10e, float:3.78E-43)
            return r11
        L_0x0106:
            r11 = 90
            return r11
        L_0x0109:
            r11 = 180(0xb4, float:2.52E-43)
            return r11
        L_0x010c:
            return r0
        L_0x010d:
            int r2 = r2 + 12
            int r12 = r12 + -12
            int r3 = r2 + -4
            r1.mo5808fa(r3)
            r3 = r7
            goto L_0x00de
        L_0x0118:
            java.lang.String r11 = "Invalid offset"
            android.util.Log.e(r4, r11)
        L_0x011d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.p032ex.photo.util.C0741a.m1190a(java.io.InputStream, long):int");
    }

    /* renamed from: a */
    private static int m1189a(C0746f fVar, int i, int i2, boolean z) {
        int i3;
        if (z) {
            i += i2 - 1;
            i3 = -1;
        } else {
            i3 = 1;
        }
        byte b = 0;
        while (true) {
            int i4 = i2 - 1;
            if (i2 <= 0) {
                return b;
            }
            b = (fVar.get(i) & 255) | (b << 8);
            i += i3;
            i2 = i4;
        }
    }

    /* renamed from: a */
    private static boolean m1191a(C0746f fVar, long j, int i) {
        if (j >= 0) {
            return ((long) i) < j;
        }
        return fVar.has(i);
    }
}
