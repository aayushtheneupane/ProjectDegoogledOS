package com.google.common.base;

import java.util.Iterator;

/* renamed from: com.google.common.base.L */
abstract class C1515L implements Iterator {

    /* renamed from: YM */
    final CharSequence f2389YM;

    /* renamed from: ZM */
    final C1545t f2390ZM;

    /* renamed from: _M */
    final boolean f2391_M;
    int limit;
    private Object next;
    int offset = 0;
    private AbstractIterator$State state = AbstractIterator$State.NOT_READY;

    protected C1515L(C1516M m, CharSequence charSequence) {
        this.f2390ZM = m.f2392ZM;
        this.f2391_M = m.f2393_M;
        this.limit = m.limit;
        this.f2389YM = charSequence;
    }

    public final boolean hasNext() {
        String str;
        int i;
        C1508E.checkState(this.state != AbstractIterator$State.FAILED);
        int ordinal = this.state.ordinal();
        if (ordinal == 0) {
            return true;
        }
        if (ordinal != 2) {
            this.state = AbstractIterator$State.FAILED;
            int i2 = this.offset;
            while (true) {
                int i3 = this.offset;
                if (i3 == -1) {
                    this.state = AbstractIterator$State.DONE;
                    str = null;
                    break;
                }
                C1512I i4 = (C1512I) this;
                int length = i4.this$0.f2387WM.length();
                int length2 = i4.f2389YM.length() - length;
                while (true) {
                    if (i3 > length2) {
                        i3 = -1;
                        break;
                    }
                    int i5 = 0;
                    while (i5 < length) {
                        if (i4.f2389YM.charAt(i5 + i3) != i4.this$0.f2387WM.charAt(i5)) {
                            i3++;
                        } else {
                            i5++;
                        }
                    }
                    break;
                }
                if (i3 == -1) {
                    i3 = this.f2389YM.length();
                    this.offset = -1;
                } else {
                    this.offset = i4.this$0.f2387WM.length() + i3;
                }
                int i6 = this.offset;
                if (i6 == i2) {
                    this.offset = i6 + 1;
                    if (this.offset >= this.f2389YM.length()) {
                        this.offset = -1;
                    }
                } else {
                    while (i2 < i3 && this.f2390ZM.mo8551d(this.f2389YM.charAt(i2))) {
                        i2++;
                    }
                    while (i > i2) {
                        int i7 = i - 1;
                        if (!this.f2390ZM.mo8551d(this.f2389YM.charAt(i7))) {
                            break;
                        }
                        i3 = i7;
                    }
                    if (!this.f2391_M || i2 != i) {
                        int i8 = this.limit;
                    } else {
                        i2 = this.offset;
                    }
                }
            }
            int i82 = this.limit;
            if (i82 == 1) {
                i = this.f2389YM.length();
                this.offset = -1;
                while (i > i2) {
                    int i9 = i - 1;
                    if (!this.f2390ZM.mo8551d(this.f2389YM.charAt(i9))) {
                        break;
                    }
                    i = i9;
                }
            } else {
                this.limit = i82 - 1;
            }
            str = this.f2389YM.subSequence(i2, i).toString();
            this.next = str;
            if (this.state != AbstractIterator$State.DONE) {
                this.state = AbstractIterator$State.READY;
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00fc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object next() {
        /*
            r12 = this;
            com.google.common.base.AbstractIterator$State r0 = r12.state
            com.google.common.base.AbstractIterator$State r1 = com.google.common.base.AbstractIterator$State.FAILED
            r2 = 0
            r3 = 1
            if (r0 == r1) goto L_0x000a
            r0 = r3
            goto L_0x000b
        L_0x000a:
            r0 = r2
        L_0x000b:
            com.google.common.base.C1508E.checkState(r0)
            com.google.common.base.AbstractIterator$State r0 = r12.state
            int r0 = r0.ordinal()
            r1 = 0
            if (r0 == 0) goto L_0x00f0
            r4 = 2
            if (r0 == r4) goto L_0x00f1
            com.google.common.base.AbstractIterator$State r0 = com.google.common.base.AbstractIterator$State.FAILED
            r12.state = r0
            int r0 = r12.offset
        L_0x0020:
            int r4 = r12.offset
            r5 = -1
            if (r4 == r5) goto L_0x00df
            r6 = r12
            com.google.common.base.I r6 = (com.google.common.base.C1512I) r6
            com.google.common.base.J r7 = r6.this$0
            java.lang.String r7 = r7.f2387WM
            int r7 = r7.length()
            java.lang.CharSequence r8 = r6.f2389YM
            int r8 = r8.length()
            int r8 = r8 - r7
        L_0x0037:
            if (r4 > r8) goto L_0x0054
            r9 = r2
        L_0x003a:
            if (r9 >= r7) goto L_0x0055
            java.lang.CharSequence r10 = r6.f2389YM
            int r11 = r9 + r4
            char r10 = r10.charAt(r11)
            com.google.common.base.J r11 = r6.this$0
            java.lang.String r11 = r11.f2387WM
            char r11 = r11.charAt(r9)
            if (r10 == r11) goto L_0x0051
            int r4 = r4 + 1
            goto L_0x0037
        L_0x0051:
            int r9 = r9 + 1
            goto L_0x003a
        L_0x0054:
            r4 = r5
        L_0x0055:
            if (r4 != r5) goto L_0x0060
            java.lang.CharSequence r4 = r12.f2389YM
            int r4 = r4.length()
            r12.offset = r5
            goto L_0x006b
        L_0x0060:
            com.google.common.base.J r6 = r6.this$0
            java.lang.String r6 = r6.f2387WM
            int r6 = r6.length()
            int r6 = r6 + r4
            r12.offset = r6
        L_0x006b:
            int r6 = r12.offset
            if (r6 != r0) goto L_0x0080
            int r6 = r6 + 1
            r12.offset = r6
            int r4 = r12.offset
            java.lang.CharSequence r6 = r12.f2389YM
            int r6 = r6.length()
            if (r4 < r6) goto L_0x0020
            r12.offset = r5
            goto L_0x0020
        L_0x0080:
            if (r0 >= r4) goto L_0x0093
            com.google.common.base.t r6 = r12.f2390ZM
            java.lang.CharSequence r7 = r12.f2389YM
            char r7 = r7.charAt(r0)
            boolean r6 = r6.mo8551d((char) r7)
            if (r6 == 0) goto L_0x0093
            int r0 = r0 + 1
            goto L_0x0080
        L_0x0093:
            if (r4 <= r0) goto L_0x00a7
            com.google.common.base.t r6 = r12.f2390ZM
            java.lang.CharSequence r7 = r12.f2389YM
            int r8 = r4 + -1
            char r7 = r7.charAt(r8)
            boolean r6 = r6.mo8551d((char) r7)
            if (r6 == 0) goto L_0x00a7
            r4 = r8
            goto L_0x0093
        L_0x00a7:
            boolean r6 = r12.f2391_M
            if (r6 == 0) goto L_0x00b1
            if (r0 != r4) goto L_0x00b1
            int r0 = r12.offset
            goto L_0x0020
        L_0x00b1:
            int r6 = r12.limit
            if (r6 != r3) goto L_0x00d1
            java.lang.CharSequence r4 = r12.f2389YM
            int r4 = r4.length()
            r12.offset = r5
        L_0x00bd:
            if (r4 <= r0) goto L_0x00d4
            com.google.common.base.t r5 = r12.f2390ZM
            java.lang.CharSequence r6 = r12.f2389YM
            int r7 = r4 + -1
            char r6 = r6.charAt(r7)
            boolean r5 = r5.mo8551d((char) r6)
            if (r5 == 0) goto L_0x00d4
            r4 = r7
            goto L_0x00bd
        L_0x00d1:
            int r6 = r6 - r3
            r12.limit = r6
        L_0x00d4:
            java.lang.CharSequence r5 = r12.f2389YM
            java.lang.CharSequence r0 = r5.subSequence(r0, r4)
            java.lang.String r0 = r0.toString()
            goto L_0x00e4
        L_0x00df:
            com.google.common.base.AbstractIterator$State r0 = com.google.common.base.AbstractIterator$State.DONE
            r12.state = r0
            r0 = r1
        L_0x00e4:
            r12.next = r0
            com.google.common.base.AbstractIterator$State r0 = r12.state
            com.google.common.base.AbstractIterator$State r4 = com.google.common.base.AbstractIterator$State.DONE
            if (r0 == r4) goto L_0x00f1
            com.google.common.base.AbstractIterator$State r0 = com.google.common.base.AbstractIterator$State.READY
            r12.state = r0
        L_0x00f0:
            r2 = r3
        L_0x00f1:
            if (r2 == 0) goto L_0x00fc
            com.google.common.base.AbstractIterator$State r0 = com.google.common.base.AbstractIterator$State.NOT_READY
            r12.state = r0
            java.lang.Object r0 = r12.next
            r12.next = r1
            return r0
        L_0x00fc:
            java.util.NoSuchElementException r12 = new java.util.NoSuchElementException
            r12.<init>()
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.base.C1515L.next():java.lang.Object");
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
