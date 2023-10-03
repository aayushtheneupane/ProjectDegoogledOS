package p000;

/* renamed from: ilx */
/* compiled from: PG */
final class ilx extends ilw {
    /* renamed from: b */
    public final String mo8991b(byte[] bArr, int i, int i2) {
        int i3;
        int length = bArr.length;
        if ((i | i2 | ((length - i) - i2)) >= 0) {
            int i4 = i + i2;
            char[] cArr = new char[i2];
            int i5 = 0;
            while (r12 < i4) {
                byte b = bArr[r12];
                if (!imi.m14113a(b)) {
                    break;
                }
                i = r12 + 1;
                imi.m14106a(b, cArr, i3);
                i5 = i3 + 1;
            }
            while (r12 < i4) {
                int i6 = r12 + 1;
                byte b2 = bArr[r12];
                if (imi.m14113a(b2)) {
                    imi.m14106a(b2, cArr, i3);
                    r12 = i6;
                    i3++;
                    while (r12 < i4) {
                        byte b3 = bArr[r12];
                        if (!imi.m14113a(b3)) {
                            break;
                        }
                        r12++;
                        imi.m14106a(b3, cArr, i3);
                        i3++;
                    }
                } else if (!imi.m14116b(b2)) {
                    if (!imi.m14119c(b2)) {
                        if (i6 < i4 - 2) {
                            int i7 = i6 + 1;
                            int i8 = i7 + 1;
                            imi.m14103a(b2, bArr[i6], bArr[i7], bArr[i8], cArr, i3);
                            i3 += 2;
                            r12 = i8 + 1;
                        } else {
                            throw ijh.m13662i();
                        }
                    } else if (i6 < i4 - 1) {
                        int i9 = i6 + 1;
                        imi.m14104a(b2, bArr[i6], bArr[i9], cArr, i3);
                        r12 = i9 + 1;
                        i3++;
                    } else {
                        throw ijh.m13662i();
                    }
                } else if (i6 < i4) {
                    imi.m14105a(b2, bArr[i6], cArr, i3);
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

    /* renamed from: a */
    public final int mo8989a(CharSequence charSequence, byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        char charAt;
        int length = charSequence.length();
        int i5 = i2 + i;
        int i6 = 0;
        while (i6 < length && (i4 = i6 + i) < i5 && (charAt = charSequence.charAt(i6)) < 128) {
            bArr[i4] = (byte) charAt;
            i6++;
        }
        if (i6 == length) {
            return i + length;
        }
        int i7 = i + i6;
        while (i6 < length) {
            char charAt2 = charSequence.charAt(i6);
            if (charAt2 < 128 && i7 < i5) {
                bArr[i7] = (byte) charAt2;
                i7++;
            } else if (charAt2 < 2048 && i7 <= i5 - 2) {
                int i8 = i7 + 1;
                bArr[i7] = (byte) ((charAt2 >>> 6) | 960);
                i7 = i8 + 1;
                bArr[i8] = (byte) ((charAt2 & '?') | 128);
            } else if ((charAt2 < 55296 || charAt2 > 57343) && i7 <= i5 - 3) {
                int i9 = i7 + 1;
                bArr[i7] = (byte) ((charAt2 >>> 12) | 480);
                int i10 = i9 + 1;
                bArr[i9] = (byte) (((charAt2 >>> 6) & 63) | 128);
                bArr[i10] = (byte) ((charAt2 & '?') | 128);
                i7 = i10 + 1;
            } else if (i7 <= i5 - 4) {
                int i11 = i6 + 1;
                if (i11 != charSequence.length()) {
                    char charAt3 = charSequence.charAt(i11);
                    if (Character.isSurrogatePair(charAt2, charAt3)) {
                        int codePoint = Character.toCodePoint(charAt2, charAt3);
                        int i12 = i7 + 1;
                        bArr[i7] = (byte) ((codePoint >>> 18) | 240);
                        int i13 = i12 + 1;
                        bArr[i12] = (byte) (((codePoint >>> 12) & 63) | 128);
                        int i14 = i13 + 1;
                        bArr[i13] = (byte) (((codePoint >>> 6) & 63) | 128);
                        i7 = i14 + 1;
                        bArr[i14] = (byte) ((codePoint & 63) | 128);
                        i6 = i11;
                    } else {
                        i6 = i11;
                    }
                }
                throw new ily(i6 - 1, length);
            } else if (charAt2 < 55296 || charAt2 > 57343 || ((i3 = i6 + 1) != charSequence.length() && Character.isSurrogatePair(charAt2, charSequence.charAt(i3)))) {
                StringBuilder sb = new StringBuilder(37);
                sb.append("Failed writing ");
                sb.append(charAt2);
                sb.append(" at index ");
                sb.append(i7);
                throw new ArrayIndexOutOfBoundsException(sb.toString());
            } else {
                throw new ily(i6, length);
            }
            i6++;
        }
        return i7;
    }

    /* renamed from: a */
    public final int mo8988a(int i, byte[] bArr, int i2, int i3) {
        byte b;
        if (i != 0) {
            if (i2 >= i3) {
                return i;
            }
            byte b2 = (byte) i;
            if (b2 < -32) {
                if (b2 >= -62) {
                    int i4 = i2 + 1;
                    if (bArr[i2] <= -65) {
                        i2 = i4;
                    }
                }
                return -1;
            } else if (b2 >= -16) {
                byte b3 = (byte) ((i >> 8) ^ -1);
                if (b3 != 0) {
                    b = (byte) (i >> 16);
                } else {
                    int i5 = i2 + 1;
                    b3 = bArr[i2];
                    if (i5 >= i3) {
                        return ima.m14065a(b2, b3);
                    }
                    i2 = i5;
                    b = 0;
                }
                if (b == 0) {
                    int i6 = i2 + 1;
                    byte b4 = bArr[i2];
                    if (i6 >= i3) {
                        return ima.m14066a((int) b2, (int) b3, (int) b4);
                    }
                    byte b5 = b4;
                    i2 = i6;
                    b = b5;
                }
                if (b3 <= -65 && (((b2 << 28) + (b3 + 112)) >> 30) == 0 && b <= -65) {
                    int i7 = i2 + 1;
                    if (bArr[i2] <= -65) {
                        i2 = i7;
                    }
                }
                return -1;
            } else {
                byte b6 = (byte) ((i >> 8) ^ -1);
                if (b6 == 0) {
                    int i8 = i2 + 1;
                    byte b7 = bArr[i2];
                    if (i8 >= i3) {
                        return ima.m14065a(b2, b7);
                    }
                    byte b8 = b7;
                    i2 = i8;
                    b6 = b8;
                }
                if (b6 <= -65 && ((b2 != -32 || b6 >= -96) && (b2 != -19 || b6 < -96))) {
                    int i9 = i2 + 1;
                    if (bArr[i2] <= -65) {
                        i2 = i9;
                    }
                }
                return -1;
            }
        }
        while (r14 < i3 && bArr[r14] >= 0) {
            i2 = r14 + 1;
        }
        if (r14 >= i3) {
            return 0;
        }
        while (r14 < i3) {
            int i10 = r14 + 1;
            byte b9 = bArr[r14];
            if (b9 < 0) {
                if (b9 >= -32) {
                    if (b9 >= -16) {
                        if (i10 >= i3 - 2) {
                            return ima.m14070b(bArr, i10, i3);
                        }
                        int i11 = i10 + 1;
                        byte b10 = bArr[i10];
                        if (b10 <= -65 && (((b9 << 28) + (b10 + 112)) >> 30) == 0) {
                            int i12 = i11 + 1;
                            if (bArr[i11] <= -65) {
                                r14 = i12 + 1;
                                if (bArr[i12] > -65) {
                                }
                            }
                        }
                    } else if (i10 >= i3 - 1) {
                        return ima.m14070b(bArr, i10, i3);
                    } else {
                        int i13 = i10 + 1;
                        byte b11 = bArr[i10];
                        if (b11 <= -65 && ((b9 != -32 || b11 >= -96) && (b9 != -19 || b11 < -96))) {
                            r14 = i13 + 1;
                            if (bArr[i13] > -65) {
                            }
                        }
                    }
                } else if (i10 >= i3) {
                    return b9;
                } else {
                    if (b9 >= -62) {
                        r14 = i10 + 1;
                        if (bArr[i10] > -65) {
                        }
                    }
                }
                return -1;
            }
            r14 = i10;
        }
        return 0;
    }
}
