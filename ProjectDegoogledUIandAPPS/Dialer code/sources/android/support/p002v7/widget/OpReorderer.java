package android.support.p002v7.widget;

/* renamed from: android.support.v7.widget.OpReorderer */
class OpReorderer {
    final Callback mCallback;

    /* renamed from: android.support.v7.widget.OpReorderer$Callback */
    interface Callback {
    }

    OpReorderer(Callback callback) {
        this.mCallback = callback;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0000 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00f0  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x011d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void reorderOps(java.util.List<android.support.p002v7.widget.AdapterHelper.UpdateOp> r13) {
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
            android.support.v7.widget.AdapterHelper$UpdateOp r5 = (android.support.p002v7.widget.AdapterHelper.UpdateOp) r5
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
            if (r0 == r4) goto L_0x01c5
            int r3 = r0 + 1
            java.lang.Object r5 = r13.get(r0)
            android.support.v7.widget.AdapterHelper$UpdateOp r5 = (android.support.p002v7.widget.AdapterHelper.UpdateOp) r5
            java.lang.Object r6 = r13.get(r3)
            android.support.v7.widget.AdapterHelper$UpdateOp r6 = (android.support.p002v7.widget.AdapterHelper.UpdateOp) r6
            int r7 = r6.cmd
            if (r7 == r1) goto L_0x0193
            r4 = 0
            r8 = 2
            if (r7 == r8) goto L_0x00a0
            r2 = 4
            if (r7 == r2) goto L_0x003b
            goto L_0x0000
        L_0x003b:
            int r7 = r5.itemCount
            int r8 = r6.positionStart
            if (r7 >= r8) goto L_0x0046
            int r8 = r8 + -1
            r6.positionStart = r8
            goto L_0x005c
        L_0x0046:
            int r9 = r6.itemCount
            int r8 = r8 + r9
            if (r7 >= r8) goto L_0x005c
            int r9 = r9 + -1
            r6.itemCount = r9
            android.support.v7.widget.OpReorderer$Callback r7 = r12.mCallback
            int r8 = r5.positionStart
            java.lang.Object r9 = r6.payload
            android.support.v7.widget.AdapterHelper r7 = (android.support.p002v7.widget.AdapterHelper) r7
            android.support.v7.widget.AdapterHelper$UpdateOp r1 = r7.obtainUpdateOp(r2, r8, r1, r9)
            goto L_0x005d
        L_0x005c:
            r1 = r4
        L_0x005d:
            int r7 = r5.positionStart
            int r8 = r6.positionStart
            if (r7 > r8) goto L_0x0068
            int r8 = r8 + 1
            r6.positionStart = r8
            goto L_0x007f
        L_0x0068:
            int r9 = r6.itemCount
            int r8 = r8 + r9
            if (r7 >= r8) goto L_0x007f
            int r8 = r8 - r7
            android.support.v7.widget.OpReorderer$Callback r4 = r12.mCallback
            int r7 = r7 + 1
            java.lang.Object r9 = r6.payload
            android.support.v7.widget.AdapterHelper r4 = (android.support.p002v7.widget.AdapterHelper) r4
            android.support.v7.widget.AdapterHelper$UpdateOp r4 = r4.obtainUpdateOp(r2, r7, r8, r9)
            int r2 = r6.itemCount
            int r2 = r2 - r8
            r6.itemCount = r2
        L_0x007f:
            r13.set(r3, r5)
            int r2 = r6.itemCount
            if (r2 <= 0) goto L_0x008a
            r13.set(r0, r6)
            goto L_0x0094
        L_0x008a:
            r13.remove(r0)
            android.support.v7.widget.OpReorderer$Callback r2 = r12.mCallback
            android.support.v7.widget.AdapterHelper r2 = (android.support.p002v7.widget.AdapterHelper) r2
            r2.recycleUpdateOp(r6)
        L_0x0094:
            if (r1 == 0) goto L_0x0099
            r13.add(r0, r1)
        L_0x0099:
            if (r4 == 0) goto L_0x0000
            r13.add(r0, r4)
            goto L_0x0000
        L_0x00a0:
            int r7 = r5.positionStart
            int r9 = r5.itemCount
            if (r7 >= r9) goto L_0x00b4
            int r10 = r6.positionStart
            if (r10 != r7) goto L_0x00b2
            int r10 = r6.itemCount
            int r9 = r9 - r7
            if (r10 != r9) goto L_0x00b2
            r7 = r2
            r2 = r1
            goto L_0x00c2
        L_0x00b2:
            r7 = r2
            goto L_0x00c2
        L_0x00b4:
            int r10 = r6.positionStart
            int r11 = r9 + 1
            if (r10 != r11) goto L_0x00c1
            int r10 = r6.itemCount
            int r7 = r7 - r9
            if (r10 != r7) goto L_0x00c1
            r2 = r1
            goto L_0x00b2
        L_0x00c1:
            r7 = r1
        L_0x00c2:
            int r9 = r5.itemCount
            int r10 = r6.positionStart
            if (r9 >= r10) goto L_0x00cd
            int r10 = r10 + -1
            r6.positionStart = r10
            goto L_0x00ea
        L_0x00cd:
            int r11 = r6.itemCount
            int r10 = r10 + r11
            if (r9 >= r10) goto L_0x00ea
            int r11 = r11 + -1
            r6.itemCount = r11
            r5.cmd = r8
            r5.itemCount = r1
            int r0 = r6.itemCount
            if (r0 != 0) goto L_0x0000
            r13.remove(r3)
            android.support.v7.widget.OpReorderer$Callback r0 = r12.mCallback
            android.support.v7.widget.AdapterHelper r0 = (android.support.p002v7.widget.AdapterHelper) r0
            r0.recycleUpdateOp(r6)
            goto L_0x0000
        L_0x00ea:
            int r1 = r5.positionStart
            int r9 = r6.positionStart
            if (r1 > r9) goto L_0x00f5
            int r9 = r9 + 1
            r6.positionStart = r9
            goto L_0x010c
        L_0x00f5:
            int r10 = r6.itemCount
            int r9 = r9 + r10
            if (r1 >= r9) goto L_0x010c
            int r9 = r9 - r1
            android.support.v7.widget.OpReorderer$Callback r10 = r12.mCallback
            int r1 = r1 + 1
            android.support.v7.widget.AdapterHelper r10 = (android.support.p002v7.widget.AdapterHelper) r10
            android.support.v7.widget.AdapterHelper$UpdateOp r4 = r10.obtainUpdateOp(r8, r1, r9, r4)
            int r1 = r5.positionStart
            int r8 = r6.positionStart
            int r1 = r1 - r8
            r6.itemCount = r1
        L_0x010c:
            if (r2 == 0) goto L_0x011d
            r13.set(r0, r6)
            r13.remove(r3)
            android.support.v7.widget.OpReorderer$Callback r0 = r12.mCallback
            android.support.v7.widget.AdapterHelper r0 = (android.support.p002v7.widget.AdapterHelper) r0
            r0.recycleUpdateOp(r5)
            goto L_0x0000
        L_0x011d:
            if (r7 == 0) goto L_0x014e
            if (r4 == 0) goto L_0x0137
            int r1 = r5.positionStart
            int r2 = r4.positionStart
            if (r1 <= r2) goto L_0x012c
            int r2 = r4.itemCount
            int r1 = r1 - r2
            r5.positionStart = r1
        L_0x012c:
            int r1 = r5.itemCount
            int r2 = r4.positionStart
            if (r1 <= r2) goto L_0x0137
            int r2 = r4.itemCount
            int r1 = r1 - r2
            r5.itemCount = r1
        L_0x0137:
            int r1 = r5.positionStart
            int r2 = r6.positionStart
            if (r1 <= r2) goto L_0x0142
            int r2 = r6.itemCount
            int r1 = r1 - r2
            r5.positionStart = r1
        L_0x0142:
            int r1 = r5.itemCount
            int r2 = r6.positionStart
            if (r1 <= r2) goto L_0x017c
            int r2 = r6.itemCount
            int r1 = r1 - r2
            r5.itemCount = r1
            goto L_0x017c
        L_0x014e:
            if (r4 == 0) goto L_0x0166
            int r1 = r5.positionStart
            int r2 = r4.positionStart
            if (r1 < r2) goto L_0x015b
            int r2 = r4.itemCount
            int r1 = r1 - r2
            r5.positionStart = r1
        L_0x015b:
            int r1 = r5.itemCount
            int r2 = r4.positionStart
            if (r1 < r2) goto L_0x0166
            int r2 = r4.itemCount
            int r1 = r1 - r2
            r5.itemCount = r1
        L_0x0166:
            int r1 = r5.positionStart
            int r2 = r6.positionStart
            if (r1 < r2) goto L_0x0171
            int r2 = r6.itemCount
            int r1 = r1 - r2
            r5.positionStart = r1
        L_0x0171:
            int r1 = r5.itemCount
            int r2 = r6.positionStart
            if (r1 < r2) goto L_0x017c
            int r2 = r6.itemCount
            int r1 = r1 - r2
            r5.itemCount = r1
        L_0x017c:
            r13.set(r0, r6)
            int r1 = r5.positionStart
            int r2 = r5.itemCount
            if (r1 == r2) goto L_0x0189
            r13.set(r3, r5)
            goto L_0x018c
        L_0x0189:
            r13.remove(r3)
        L_0x018c:
            if (r4 == 0) goto L_0x0000
            r13.add(r0, r4)
            goto L_0x0000
        L_0x0193:
            int r1 = r5.itemCount
            int r7 = r6.positionStart
            if (r1 >= r7) goto L_0x019a
            r2 = r4
        L_0x019a:
            int r1 = r5.positionStart
            int r4 = r6.positionStart
            if (r1 >= r4) goto L_0x01a2
            int r2 = r2 + 1
        L_0x01a2:
            int r1 = r6.positionStart
            int r4 = r5.positionStart
            if (r1 > r4) goto L_0x01ad
            int r1 = r6.itemCount
            int r4 = r4 + r1
            r5.positionStart = r4
        L_0x01ad:
            int r1 = r6.positionStart
            int r4 = r5.itemCount
            if (r1 > r4) goto L_0x01b8
            int r1 = r6.itemCount
            int r4 = r4 + r1
            r5.itemCount = r4
        L_0x01b8:
            int r1 = r6.positionStart
            int r1 = r1 + r2
            r6.positionStart = r1
            r13.set(r0, r6)
            r13.set(r3, r5)
            goto L_0x0000
        L_0x01c5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.OpReorderer.reorderOps(java.util.List):void");
    }
}
