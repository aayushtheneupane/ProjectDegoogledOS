package p000;

/* renamed from: ilz */
/* compiled from: PG */
final class ilz extends ilw {
    /* renamed from: b */
    public final String mo8991b(byte[] bArr, int i, int i2) {
        int i3;
        int length = bArr.length;
        if ((i | i2 | ((length - i) - i2)) >= 0) {
            int i4 = i + i2;
            char[] cArr = new char[i2];
            int i5 = 0;
            while (r12 < i4) {
                byte a = ilv.m14026a(bArr, (long) r12);
                if (!imi.m14113a(a)) {
                    break;
                }
                i = r12 + 1;
                imi.m14106a(a, cArr, i3);
                i5 = i3 + 1;
            }
            while (r12 < i4) {
                int i6 = r12 + 1;
                byte a2 = ilv.m14026a(bArr, (long) r12);
                if (imi.m14113a(a2)) {
                    imi.m14106a(a2, cArr, i3);
                    r12 = i6;
                    i3++;
                    while (r12 < i4) {
                        byte a3 = ilv.m14026a(bArr, (long) r12);
                        if (!imi.m14113a(a3)) {
                            break;
                        }
                        r12++;
                        imi.m14106a(a3, cArr, i3);
                        i3++;
                    }
                } else if (!imi.m14116b(a2)) {
                    if (!imi.m14119c(a2)) {
                        if (i6 < i4 - 2) {
                            int i7 = i6 + 1;
                            int i8 = i7 + 1;
                            imi.m14103a(a2, ilv.m14026a(bArr, (long) i6), ilv.m14026a(bArr, (long) i7), ilv.m14026a(bArr, (long) i8), cArr, i3);
                            i3 += 2;
                            r12 = i8 + 1;
                        } else {
                            throw ijh.m13662i();
                        }
                    } else if (i6 < i4 - 1) {
                        int i9 = i6 + 1;
                        imi.m14104a(a2, ilv.m14026a(bArr, (long) i6), ilv.m14026a(bArr, (long) i9), cArr, i3);
                        r12 = i9 + 1;
                        i3++;
                    } else {
                        throw ijh.m13662i();
                    }
                } else if (i6 < i4) {
                    imi.m14105a(a2, ilv.m14026a(bArr, (long) i6), cArr, i3);
                    r12 = i6 + 1;
                    i3++;
                } else {
                    throw ijh.m13662i();
                }
            }
            return new String(cArr, 0, i3);
        }
        throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", new Object[]{Integer.valueOf(length), Integer.valueOf(i), Integer.valueOf(i2)}));
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0032 A[LOOP:1: B:11:0x0032->B:35:0x00fe, LOOP_START, PHI: r2 r3 r4 r11 
      PHI: (r2v3 int) = (r2v2 int), (r2v5 int) binds: [B:10:0x0030, B:35:0x00fe] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r3v2 char) = (r3v1 char), (r3v3 char) binds: [B:10:0x0030, B:35:0x00fe] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r4v4 long) = (r4v2 long), (r4v6 long) binds: [B:10:0x0030, B:35:0x00fe] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r11v3 long) = (r11v2 long), (r11v4 long) binds: [B:10:0x0030, B:35:0x00fe] A[DONT_GENERATE, DONT_INLINE]] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int mo8989a(java.lang.CharSequence r23, byte[] r24, int r25, int r26) {
        /*
            r22 = this;
            r0 = r23
            r1 = r24
            r2 = r25
            r3 = r26
            long r4 = (long) r2
            long r6 = (long) r3
            long r6 = r6 + r4
            int r8 = r23.length()
            java.lang.String r9 = " at index "
            java.lang.String r10 = "Failed writing "
            if (r8 > r3) goto L_0x014b
            int r11 = r1.length
            int r11 = r11 - r3
            if (r11 < r2) goto L_0x014b
            r2 = 0
        L_0x001a:
            r3 = 128(0x80, float:1.794E-43)
            r11 = 1
            if (r2 >= r8) goto L_0x0030
            char r13 = r0.charAt(r2)
            if (r13 >= r3) goto L_0x0030
            long r11 = r11 + r4
            byte r3 = (byte) r13
            p000.ilv.m14038a((byte[]) r1, (long) r4, (byte) r3)
            int r2 = r2 + 1
            r4 = r11
            goto L_0x001a
        L_0x0030:
            if (r2 == r8) goto L_0x0149
        L_0x0032:
            if (r2 >= r8) goto L_0x0149
            char r13 = r0.charAt(r2)
            if (r13 < r3) goto L_0x003b
            goto L_0x004c
        L_0x003b:
            int r14 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r14 >= 0) goto L_0x004c
            long r14 = r4 + r11
            byte r13 = (byte) r13
            p000.ilv.m14038a((byte[]) r1, (long) r4, (byte) r13)
            r4 = r11
            r12 = r14
            r11 = 128(0x80, float:1.794E-43)
            goto L_0x00fe
        L_0x004c:
            r14 = 2048(0x800, float:2.87E-42)
            if (r13 >= r14) goto L_0x0075
            r14 = -2
            long r14 = r14 + r6
            int r16 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1))
            if (r16 > 0) goto L_0x0075
            long r14 = r4 + r11
            int r3 = r13 >>> 6
            r3 = r3 | 960(0x3c0, float:1.345E-42)
            byte r3 = (byte) r3
            p000.ilv.m14038a((byte[]) r1, (long) r4, (byte) r3)
            long r3 = r14 + r11
            r5 = r13 & 63
            r13 = 128(0x80, float:1.794E-43)
            r5 = r5 | r13
            byte r5 = (byte) r5
            p000.ilv.m14038a((byte[]) r1, (long) r14, (byte) r5)
            r20 = r11
            r11 = 128(0x80, float:1.794E-43)
            r12 = r3
            r4 = r20
            goto L_0x00fe
        L_0x0075:
            r3 = 57343(0xdfff, float:8.0355E-41)
            r14 = 55296(0xd800, float:7.7486E-41)
            if (r13 >= r14) goto L_0x007e
            goto L_0x0080
        L_0x007e:
            if (r13 <= r3) goto L_0x00b1
        L_0x0080:
            r15 = -3
            long r15 = r15 + r6
            int r17 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r17 > 0) goto L_0x00b1
            long r14 = r4 + r11
            int r3 = r13 >>> 12
            r3 = r3 | 480(0x1e0, float:6.73E-43)
            byte r3 = (byte) r3
            p000.ilv.m14038a((byte[]) r1, (long) r4, (byte) r3)
            long r3 = r14 + r11
            int r5 = r13 >>> 6
            r5 = r5 & 63
            r11 = 128(0x80, float:1.794E-43)
            r5 = r5 | r11
            byte r5 = (byte) r5
            p000.ilv.m14038a((byte[]) r1, (long) r14, (byte) r5)
            r14 = 1
            long r18 = r3 + r14
            r5 = r13 & 63
            r5 = r5 | r11
            byte r5 = (byte) r5
            p000.ilv.m14038a((byte[]) r1, (long) r3, (byte) r5)
            r12 = r18
            r4 = 1
            r11 = 128(0x80, float:1.794E-43)
            goto L_0x00fe
        L_0x00b1:
            r11 = -4
            long r11 = r11 + r6
            int r15 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r15 > 0) goto L_0x0113
            int r3 = r2 + 1
            if (r3 == r8) goto L_0x010b
            char r2 = r0.charAt(r3)
            boolean r11 = java.lang.Character.isSurrogatePair(r13, r2)
            if (r11 == 0) goto L_0x010a
            int r2 = java.lang.Character.toCodePoint(r13, r2)
            r11 = 1
            long r13 = r4 + r11
            int r15 = r2 >>> 18
            r15 = r15 | 240(0xf0, float:3.36E-43)
            byte r15 = (byte) r15
            p000.ilv.m14038a((byte[]) r1, (long) r4, (byte) r15)
            long r4 = r13 + r11
            int r15 = r2 >>> 12
            r15 = r15 & 63
            r11 = 128(0x80, float:1.794E-43)
            r12 = r15 | 128(0x80, float:1.794E-43)
            byte r12 = (byte) r12
            p000.ilv.m14038a((byte[]) r1, (long) r13, (byte) r12)
            r12 = 1
            long r14 = r4 + r12
            int r16 = r2 >>> 6
            r12 = r16 & 63
            r12 = r12 | r11
            byte r12 = (byte) r12
            p000.ilv.m14038a((byte[]) r1, (long) r4, (byte) r12)
            r4 = 1
            long r12 = r14 + r4
            r2 = r2 & 63
            r2 = r2 | r11
            byte r2 = (byte) r2
            p000.ilv.m14038a((byte[]) r1, (long) r14, (byte) r2)
            r2 = r3
        L_0x00fe:
            int r2 = r2 + 1
            r3 = 128(0x80, float:1.794E-43)
            r20 = r4
            r4 = r12
            r11 = r20
            goto L_0x0032
        L_0x010a:
            r2 = r3
        L_0x010b:
            ily r0 = new ily
            int r2 = r2 + -1
            r0.<init>(r2, r8)
            throw r0
        L_0x0113:
            if (r13 < r14) goto L_0x012c
            if (r13 > r3) goto L_0x012c
            int r1 = r2 + 1
            if (r1 == r8) goto L_0x0126
            char r0 = r0.charAt(r1)
            boolean r0 = java.lang.Character.isSurrogatePair(r13, r0)
            if (r0 != 0) goto L_0x012c
        L_0x0126:
            ily r0 = new ily
            r0.<init>(r2, r8)
            throw r0
        L_0x012c:
            java.lang.ArrayIndexOutOfBoundsException r0 = new java.lang.ArrayIndexOutOfBoundsException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r2 = 46
            r1.<init>(r2)
            r1.append(r10)
            r1.append(r13)
            r1.append(r9)
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0149:
            int r0 = (int) r4
            return r0
        L_0x014b:
            java.lang.ArrayIndexOutOfBoundsException r1 = new java.lang.ArrayIndexOutOfBoundsException
            int r8 = r8 + -1
            char r0 = r0.charAt(r8)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r5 = 37
            r4.<init>(r5)
            r4.append(r10)
            r4.append(r0)
            r4.append(r9)
            int r0 = r2 + r3
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            r1.<init>(r0)
            goto L_0x0171
        L_0x0170:
            throw r1
        L_0x0171:
            goto L_0x0170
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ilz.mo8989a(java.lang.CharSequence, byte[], int, int):int");
    }

    /* renamed from: a */
    public final int mo8988a(int i, byte[] bArr, int i2, int i3) {
        int i4;
        byte b;
        int i5 = i;
        byte[] bArr2 = bArr;
        int i6 = i2;
        int i7 = i3;
        int length = bArr2.length;
        if ((i6 | i7 | (length - i7)) >= 0) {
            long j = (long) i6;
            long j2 = (long) i7;
            if (i5 != 0) {
                if (j >= j2) {
                    return i5;
                }
                byte b2 = (byte) i5;
                if (b2 < -32) {
                    if (b2 >= -62) {
                        long j3 = j + 1;
                        if (ilv.m14026a(bArr2, j) <= -65) {
                            j = j3;
                        }
                    }
                    return -1;
                } else if (b2 >= -16) {
                    byte b3 = (byte) ((i5 >> 8) ^ -1);
                    if (b3 != 0) {
                        b = (byte) (i5 >> 16);
                    } else {
                        long j4 = j + 1;
                        b3 = ilv.m14026a(bArr2, j);
                        if (j4 >= j2) {
                            return ima.m14065a(b2, b3);
                        }
                        j = j4;
                        b = 0;
                    }
                    if (b == 0) {
                        long j5 = j + 1;
                        b = ilv.m14026a(bArr2, j);
                        if (j5 >= j2) {
                            return ima.m14066a((int) b2, (int) b3, (int) b);
                        }
                        j = j5;
                    }
                    if (b3 <= -65 && (((b2 << 28) + (b3 + 112)) >> 30) == 0 && b <= -65) {
                        long j6 = j + 1;
                        if (ilv.m14026a(bArr2, j) <= -65) {
                            j = j6;
                        }
                    }
                    return -1;
                } else {
                    byte b4 = (byte) ((i5 >> 8) ^ -1);
                    if (b4 == 0) {
                        long j7 = j + 1;
                        b4 = ilv.m14026a(bArr2, j);
                        if (j7 >= j2) {
                            return ima.m14065a(b2, b4);
                        }
                        j = j7;
                    }
                    if (b4 <= -65 && ((b2 != -32 || b4 >= -96) && (b2 != -19 || b4 < -96))) {
                        long j8 = j + 1;
                        if (ilv.m14026a(bArr2, j) <= -65) {
                            j = j8;
                        }
                    }
                    return -1;
                }
            }
            int i8 = (int) (j2 - j);
            if (i8 >= 16) {
                long j9 = j;
                i4 = 0;
                while (true) {
                    if (i4 >= i8) {
                        i4 = i8;
                        break;
                    }
                    long j10 = j9 + 1;
                    if (ilv.m14026a(bArr2, j9) < 0) {
                        break;
                    }
                    i4++;
                    j9 = j10;
                }
            } else {
                i4 = 0;
            }
            int i9 = i8 - i4;
            long j11 = j + ((long) i4);
            while (true) {
                byte b5 = 0;
                while (true) {
                    if (i9 <= 0) {
                        break;
                    }
                    long j12 = j11 + 1;
                    byte a = ilv.m14026a(bArr2, j11);
                    if (a < 0) {
                        long j13 = j12;
                        b5 = a;
                        j11 = j13;
                        break;
                    }
                    i9--;
                    long j14 = j12;
                    b5 = a;
                    j11 = j14;
                }
                if (i9 == 0) {
                    return 0;
                }
                int i10 = i9 - 1;
                if (b5 >= -32) {
                    if (b5 >= -16) {
                        if (i10 >= 3) {
                            i9 = i10 - 3;
                            long j15 = j11 + 1;
                            byte a2 = ilv.m14026a(bArr2, j11);
                            if (a2 > -65 || (((b5 << 28) + (a2 + 112)) >> 30) != 0) {
                                break;
                            }
                            long j16 = j15 + 1;
                            if (ilv.m14026a(bArr2, j15) > -65) {
                                break;
                            }
                            j11 = j16 + 1;
                            if (ilv.m14026a(bArr2, j16) > -65) {
                                break;
                            }
                        } else {
                            return m14060a(bArr2, (int) b5, j11, i10);
                        }
                    } else if (i10 >= 2) {
                        i9 = i10 - 2;
                        long j17 = j11 + 1;
                        byte a3 = ilv.m14026a(bArr2, j11);
                        if (a3 > -65 || ((b5 == -32 && a3 < -96) || (b5 == -19 && a3 >= -96))) {
                            break;
                        }
                        j11 = j17 + 1;
                        if (ilv.m14026a(bArr2, j17) > -65) {
                            break;
                        }
                    } else {
                        return m14060a(bArr2, (int) b5, j11, i10);
                    }
                } else if (i10 != 0) {
                    i9 = i10 - 1;
                    if (b5 >= -62) {
                        long j18 = j11 + 1;
                        if (ilv.m14026a(bArr2, j11) > -65) {
                            break;
                        }
                        j11 = j18;
                    } else {
                        break;
                    }
                } else {
                    return b5;
                }
            }
            return -1;
        }
        throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", new Object[]{Integer.valueOf(length), Integer.valueOf(i2), Integer.valueOf(i3)}));
    }

    /* renamed from: a */
    private static int m14060a(byte[] bArr, int i, long j, int i2) {
        if (i2 == 0) {
            return ima.m14064a(i);
        }
        if (i2 == 1) {
            return ima.m14065a(i, ilv.m14026a(bArr, j));
        }
        if (i2 == 2) {
            return ima.m14066a(i, (int) ilv.m14026a(bArr, j), (int) ilv.m14026a(bArr, j + 1));
        }
        throw new AssertionError();
    }
}
