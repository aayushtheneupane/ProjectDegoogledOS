package p000;

/* renamed from: hyq */
/* compiled from: PG */
public final class hyq extends hyu {

    /* renamed from: a */
    public static final hyu f13658a = new hyq();

    private hyq() {
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [hyr, hyn] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int mo8272a(p000.hyr r7, int r8, java.lang.String r9, int r10, int r11, int r12) {
        /*
            r6 = this;
            int r0 = r12 + 1
            char r1 = r9.charAt(r12)
            r2 = r1 & 32
            r3 = 0
            r4 = 1
            if (r2 != 0) goto L_0x000e
            r5 = 1
            goto L_0x0010
        L_0x000e:
            r5 = 0
        L_0x0010:
            hwv r11 = p000.hwv.m12342a(r9, r11, r12, r5)
            hwu[] r12 = p000.hwu.f13537c
            int r5 = p000.hwu.m12338a(r1)
            r12 = r12[r5]
            r5 = 0
            if (r2 == 0) goto L_0x0020
            goto L_0x0029
        L_0x0020:
            if (r12 == 0) goto L_0x0028
            int r2 = r12.f13549f
            r2 = r2 & 128(0x80, float:1.794E-43)
            if (r2 != 0) goto L_0x0029
        L_0x0028:
            r12 = r5
        L_0x0029:
            if (r12 == 0) goto L_0x0044
            int r1 = r12.f13549f
            hww r2 = r12.f13548e
            boolean r2 = r2.f13562f
            boolean r1 = r11.mo8234a((int) r1, (boolean) r2)
            if (r1 == 0) goto L_0x003c
            hyo r8 = p000.hyo.m12467a(r8, r12, r11)
            goto L_0x009d
        L_0x003c:
            java.lang.String r7 = "invalid format specifier"
            hyt r7 = p000.hyt.m12476a(r7, r9, r10, r0)
            throw r7
        L_0x0044:
            r12 = 116(0x74, float:1.63E-43)
            r2 = 160(0xa0, float:2.24E-43)
            java.lang.String r5 = "invalid format specification"
            if (r1 == r12) goto L_0x0073
            r12 = 84
            if (r1 == r12) goto L_0x0073
            r12 = 104(0x68, float:1.46E-43)
            if (r1 == r12) goto L_0x005f
            r12 = 72
            if (r1 != r12) goto L_0x0059
            goto L_0x005f
        L_0x0059:
            hyt r7 = p000.hyt.m12476a(r5, r9, r10, r0)
            throw r7
        L_0x005f:
            boolean r12 = r11.mo8234a((int) r2, (boolean) r3)
            if (r12 == 0) goto L_0x006d
            hyp r9 = new hyp
            r9.<init>(r11, r8)
            r8 = r9
            goto L_0x009d
        L_0x006d:
            hyt r7 = p000.hyt.m12476a(r5, r9, r10, r0)
            throw r7
        L_0x0073:
            boolean r12 = r11.mo8234a((int) r2, (boolean) r3)
            if (r12 == 0) goto L_0x00f4
            int r12 = r0 + 1
            int r1 = r9.length()
            if (r12 > r1) goto L_0x00ed
            char r1 = r9.charAt(r0)
            java.util.Map r2 = p000.hyk.f13627a
            java.lang.Character r1 = java.lang.Character.valueOf(r1)
            java.lang.Object r1 = r2.get(r1)
            hyk r1 = (p000.hyk) r1
            if (r1 == 0) goto L_0x00e6
            hyl r9 = new hyl
            r9.<init>(r11, r8, r1)
            r8 = r9
            r0 = r12
        L_0x009d:
            int r9 = r8.f13654a
            r11 = 32
            if (r9 >= r11) goto L_0x00aa
            int r11 = r7.f13660e
            int r12 = r4 << r9
            r11 = r11 | r12
            r7.f13660e = r11
        L_0x00aa:
            int r11 = r7.f13661f
            int r9 = java.lang.Math.max(r11, r9)
            r7.f13661f = r9
            hys r9 = r7.mo8273a()
            r11 = r7
            hxi r11 = (p000.hxi) r11
            java.lang.StringBuilder r12 = r11.f13579c
            java.lang.String r1 = r7.mo8274b()
            int r2 = r11.f13580d
            r9.mo8276a(r12, r1, r2, r10)
            java.lang.Object[] r9 = r11.f13578b
            int r10 = r8.f13654a
            int r12 = r9.length
            if (r10 < r12) goto L_0x00d3
            java.lang.StringBuilder r7 = r11.f13579c
            java.lang.String r8 = "[ERROR: MISSING LOG ARGUMENT]"
            r7.append(r8)
            goto L_0x00e2
        L_0x00d3:
            r9 = r9[r10]
            if (r9 != 0) goto L_0x00df
            java.lang.StringBuilder r7 = r11.f13579c
            java.lang.String r8 = "null"
            r7.append(r8)
            goto L_0x00e2
        L_0x00df:
            r8.mo8271a(r7, r9)
        L_0x00e2:
            r11.f13580d = r0
            return r0
        L_0x00e6:
            java.lang.String r7 = "illegal date/time conversion"
            hyt r7 = p000.hyt.m12475a(r7, r9, r0)
            throw r7
        L_0x00ed:
            java.lang.String r7 = "truncated format specifier"
            hyt r7 = p000.hyt.m12475a(r7, r9, r10)
            throw r7
        L_0x00f4:
            hyt r7 = p000.hyt.m12476a(r5, r9, r10, r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.hyq.mo8272a(hyr, int, java.lang.String, int, int, int):int");
    }
}
