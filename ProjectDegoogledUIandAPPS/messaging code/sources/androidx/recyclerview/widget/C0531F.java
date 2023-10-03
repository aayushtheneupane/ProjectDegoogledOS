package androidx.recyclerview.widget;

/* renamed from: androidx.recyclerview.widget.F */
class C0531F {
    final C0555b mCallback;

    C0531F(C0555b bVar) {
        this.mCallback = bVar;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0000 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00ed  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0111  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void reorderOps(java.util.List r13) {
        /*
            r12 = this;
        L_0x0000:
            int r0 = r13.size()
            r1 = 1
            int r0 = r0 - r1
            r2 = 0
            r3 = r2
        L_0x0008:
            r4 = -1
            if (r0 < 0) goto L_0x001e
            java.lang.Object r5 = r13.get(r0)
            androidx.recyclerview.widget.a r5 = (androidx.recyclerview.widget.C0553a) r5
            int r5 = r5.cmd
            r6 = 8
            if (r5 != r6) goto L_0x001a
            if (r3 == 0) goto L_0x001b
            goto L_0x001f
        L_0x001a:
            r3 = r1
        L_0x001b:
            int r0 = r0 + -1
            goto L_0x0008
        L_0x001e:
            r0 = r4
        L_0x001f:
            if (r0 == r4) goto L_0x01b9
            int r3 = r0 + 1
            java.lang.Object r5 = r13.get(r0)
            androidx.recyclerview.widget.a r5 = (androidx.recyclerview.widget.C0553a) r5
            java.lang.Object r6 = r13.get(r3)
            androidx.recyclerview.widget.a r6 = (androidx.recyclerview.widget.C0553a) r6
            int r7 = r6.cmd
            if (r7 == r1) goto L_0x0187
            r4 = 0
            r8 = 2
            if (r7 == r8) goto L_0x009a
            r2 = 4
            if (r7 == r2) goto L_0x003b
            goto L_0x0000
        L_0x003b:
            int r7 = r5.itemCount
            int r8 = r6.positionStart
            if (r7 >= r8) goto L_0x0046
            int r8 = r8 + -1
            r6.positionStart = r8
            goto L_0x005a
        L_0x0046:
            int r9 = r6.itemCount
            int r8 = r8 + r9
            if (r7 >= r8) goto L_0x005a
            int r9 = r9 + -1
            r6.itemCount = r9
            androidx.recyclerview.widget.b r7 = r12.mCallback
            int r8 = r5.positionStart
            java.lang.Object r9 = r6.payload
            androidx.recyclerview.widget.a r1 = r7.obtainUpdateOp(r2, r8, r1, r9)
            goto L_0x005b
        L_0x005a:
            r1 = r4
        L_0x005b:
            int r7 = r5.positionStart
            int r8 = r6.positionStart
            if (r7 > r8) goto L_0x0066
            int r8 = r8 + 1
            r6.positionStart = r8
            goto L_0x007b
        L_0x0066:
            int r9 = r6.itemCount
            int r8 = r8 + r9
            if (r7 >= r8) goto L_0x007b
            int r8 = r8 - r7
            androidx.recyclerview.widget.b r4 = r12.mCallback
            int r7 = r7 + 1
            java.lang.Object r9 = r6.payload
            androidx.recyclerview.widget.a r4 = r4.obtainUpdateOp(r2, r7, r8, r9)
            int r2 = r6.itemCount
            int r2 = r2 - r8
            r6.itemCount = r2
        L_0x007b:
            r13.set(r3, r5)
            int r2 = r6.itemCount
            if (r2 <= 0) goto L_0x0086
            r13.set(r0, r6)
            goto L_0x008e
        L_0x0086:
            r13.remove(r0)
            androidx.recyclerview.widget.b r2 = r12.mCallback
            r2.mo4993a(r6)
        L_0x008e:
            if (r1 == 0) goto L_0x0093
            r13.add(r0, r1)
        L_0x0093:
            if (r4 == 0) goto L_0x0000
            r13.add(r0, r4)
            goto L_0x0000
        L_0x009a:
            int r7 = r5.positionStart
            int r9 = r5.itemCount
            if (r7 >= r9) goto L_0x00ae
            int r10 = r6.positionStart
            if (r10 != r7) goto L_0x00ac
            int r10 = r6.itemCount
            int r9 = r9 - r7
            if (r10 != r9) goto L_0x00ac
            r7 = r2
            r2 = r1
            goto L_0x00bc
        L_0x00ac:
            r7 = r2
            goto L_0x00bc
        L_0x00ae:
            int r10 = r6.positionStart
            int r11 = r9 + 1
            if (r10 != r11) goto L_0x00bb
            int r10 = r6.itemCount
            int r7 = r7 - r9
            if (r10 != r7) goto L_0x00bb
            r2 = r1
            goto L_0x00ac
        L_0x00bb:
            r7 = r1
        L_0x00bc:
            int r9 = r5.itemCount
            int r10 = r6.positionStart
            if (r9 >= r10) goto L_0x00c7
            int r10 = r10 + -1
            r6.positionStart = r10
            goto L_0x00e2
        L_0x00c7:
            int r11 = r6.itemCount
            int r10 = r10 + r11
            if (r9 >= r10) goto L_0x00e2
            int r11 = r11 + -1
            r6.itemCount = r11
            r5.cmd = r8
            r5.itemCount = r1
            int r0 = r6.itemCount
            if (r0 != 0) goto L_0x0000
            r13.remove(r3)
            androidx.recyclerview.widget.b r0 = r12.mCallback
            r0.mo4993a(r6)
            goto L_0x0000
        L_0x00e2:
            int r1 = r5.positionStart
            int r9 = r6.positionStart
            if (r1 > r9) goto L_0x00ed
            int r9 = r9 + 1
            r6.positionStart = r9
            goto L_0x0102
        L_0x00ed:
            int r10 = r6.itemCount
            int r9 = r9 + r10
            if (r1 >= r9) goto L_0x0102
            int r9 = r9 - r1
            androidx.recyclerview.widget.b r10 = r12.mCallback
            int r1 = r1 + 1
            androidx.recyclerview.widget.a r4 = r10.obtainUpdateOp(r8, r1, r9, r4)
            int r1 = r5.positionStart
            int r8 = r6.positionStart
            int r1 = r1 - r8
            r6.itemCount = r1
        L_0x0102:
            if (r2 == 0) goto L_0x0111
            r13.set(r0, r6)
            r13.remove(r3)
            androidx.recyclerview.widget.b r0 = r12.mCallback
            r0.mo4993a(r5)
            goto L_0x0000
        L_0x0111:
            if (r7 == 0) goto L_0x0142
            if (r4 == 0) goto L_0x012b
            int r1 = r5.positionStart
            int r2 = r4.positionStart
            if (r1 <= r2) goto L_0x0120
            int r2 = r4.itemCount
            int r1 = r1 - r2
            r5.positionStart = r1
        L_0x0120:
            int r1 = r5.itemCount
            int r2 = r4.positionStart
            if (r1 <= r2) goto L_0x012b
            int r2 = r4.itemCount
            int r1 = r1 - r2
            r5.itemCount = r1
        L_0x012b:
            int r1 = r5.positionStart
            int r2 = r6.positionStart
            if (r1 <= r2) goto L_0x0136
            int r2 = r6.itemCount
            int r1 = r1 - r2
            r5.positionStart = r1
        L_0x0136:
            int r1 = r5.itemCount
            int r2 = r6.positionStart
            if (r1 <= r2) goto L_0x0170
            int r2 = r6.itemCount
            int r1 = r1 - r2
            r5.itemCount = r1
            goto L_0x0170
        L_0x0142:
            if (r4 == 0) goto L_0x015a
            int r1 = r5.positionStart
            int r2 = r4.positionStart
            if (r1 < r2) goto L_0x014f
            int r2 = r4.itemCount
            int r1 = r1 - r2
            r5.positionStart = r1
        L_0x014f:
            int r1 = r5.itemCount
            int r2 = r4.positionStart
            if (r1 < r2) goto L_0x015a
            int r2 = r4.itemCount
            int r1 = r1 - r2
            r5.itemCount = r1
        L_0x015a:
            int r1 = r5.positionStart
            int r2 = r6.positionStart
            if (r1 < r2) goto L_0x0165
            int r2 = r6.itemCount
            int r1 = r1 - r2
            r5.positionStart = r1
        L_0x0165:
            int r1 = r5.itemCount
            int r2 = r6.positionStart
            if (r1 < r2) goto L_0x0170
            int r2 = r6.itemCount
            int r1 = r1 - r2
            r5.itemCount = r1
        L_0x0170:
            r13.set(r0, r6)
            int r1 = r5.positionStart
            int r2 = r5.itemCount
            if (r1 == r2) goto L_0x017d
            r13.set(r3, r5)
            goto L_0x0180
        L_0x017d:
            r13.remove(r3)
        L_0x0180:
            if (r4 == 0) goto L_0x0000
            r13.add(r0, r4)
            goto L_0x0000
        L_0x0187:
            int r1 = r5.itemCount
            int r7 = r6.positionStart
            if (r1 >= r7) goto L_0x018e
            r2 = r4
        L_0x018e:
            int r1 = r5.positionStart
            int r4 = r6.positionStart
            if (r1 >= r4) goto L_0x0196
            int r2 = r2 + 1
        L_0x0196:
            int r1 = r6.positionStart
            int r4 = r5.positionStart
            if (r1 > r4) goto L_0x01a1
            int r1 = r6.itemCount
            int r4 = r4 + r1
            r5.positionStart = r4
        L_0x01a1:
            int r1 = r6.positionStart
            int r4 = r5.itemCount
            if (r1 > r4) goto L_0x01ac
            int r1 = r6.itemCount
            int r4 = r4 + r1
            r5.itemCount = r4
        L_0x01ac:
            int r1 = r6.positionStart
            int r1 = r1 + r2
            r6.positionStart = r1
            r13.set(r0, r6)
            r13.set(r3, r5)
            goto L_0x0000
        L_0x01b9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.C0531F.reorderOps(java.util.List):void");
    }
}
