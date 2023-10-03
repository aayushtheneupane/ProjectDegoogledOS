package com.google.zxing.datamatrix.encoder;

import java.util.Arrays;

public class DefaultPlacement {
    private final byte[] bits;
    private final String codewords;
    private final int numcols;
    private final int numrows;

    public DefaultPlacement(String str, int i, int i2) {
        this.codewords = str;
        this.numcols = i;
        this.numrows = i2;
        this.bits = new byte[(i * i2)];
        Arrays.fill(this.bits, (byte) -1);
    }

    private void module(int i, int i2, int i3, int i4) {
        if (i < 0) {
            int i5 = this.numrows;
            i += i5;
            i2 += 4 - ((i5 + 4) % 8);
        }
        if (i2 < 0) {
            int i6 = this.numcols;
            i2 += i6;
            i += 4 - ((i6 + 4) % 8);
        }
        boolean z = true;
        if ((this.codewords.charAt(i3) & (1 << (8 - i4))) == 0) {
            z = false;
        }
        setBit(i2, i, z);
    }

    private void utah(int i, int i2, int i3) {
        int i4 = i - 2;
        int i5 = i2 - 2;
        module(i4, i5, i3, 1);
        int i6 = i2 - 1;
        module(i4, i6, i3, 2);
        int i7 = i - 1;
        module(i7, i5, i3, 3);
        module(i7, i6, i3, 4);
        module(i7, i2, i3, 5);
        module(i, i5, i3, 6);
        module(i, i6, i3, 7);
        module(i, i2, i3, 8);
    }

    public final boolean getBit(int i, int i2) {
        return this.bits[(i2 * this.numcols) + i] == 1;
    }

    /* access modifiers changed from: package-private */
    public final boolean hasBit(int i, int i2) {
        return this.bits[(i2 * this.numcols) + i] >= 0;
    }

    public final void place() {
        int i;
        int i2;
        int i3 = 0;
        int i4 = 0;
        int i5 = 4;
        while (true) {
            int i6 = this.numrows;
            if (i5 == i6 && i3 == 0) {
                module(i6 - 1, 0, i4, 1);
                module(this.numrows - 1, 1, i4, 2);
                module(this.numrows - 1, 2, i4, 3);
                module(0, this.numcols - 2, i4, 4);
                module(0, this.numcols - 1, i4, 5);
                module(1, this.numcols - 1, i4, 6);
                module(2, this.numcols - 1, i4, 7);
                module(3, this.numcols - 1, i4, 8);
                i4++;
            }
            int i7 = this.numrows;
            if (i5 == i7 - 2 && i3 == 0 && this.numcols % 4 != 0) {
                module(i7 - 3, 0, i4, 1);
                module(this.numrows - 2, 0, i4, 2);
                module(this.numrows - 1, 0, i4, 3);
                module(0, this.numcols - 4, i4, 4);
                module(0, this.numcols - 3, i4, 5);
                module(0, this.numcols - 2, i4, 6);
                module(0, this.numcols - 1, i4, 7);
                module(1, this.numcols - 1, i4, 8);
                i4++;
            }
            int i8 = this.numrows;
            if (i5 == i8 - 2 && i3 == 0 && this.numcols % 8 == 4) {
                module(i8 - 3, 0, i4, 1);
                module(this.numrows - 2, 0, i4, 2);
                module(this.numrows - 1, 0, i4, 3);
                module(0, this.numcols - 2, i4, 4);
                module(0, this.numcols - 1, i4, 5);
                module(1, this.numcols - 1, i4, 6);
                module(2, this.numcols - 1, i4, 7);
                module(3, this.numcols - 1, i4, 8);
                i4++;
            }
            int i9 = this.numrows;
            if (i5 == i9 + 4 && i3 == 2 && this.numcols % 8 == 0) {
                module(i9 - 1, 0, i4, 1);
                module(this.numrows - 1, this.numcols - 1, i4, 2);
                module(0, this.numcols - 3, i4, 3);
                module(0, this.numcols - 2, i4, 4);
                module(0, this.numcols - 1, i4, 5);
                module(1, this.numcols - 3, i4, 6);
                module(1, this.numcols - 2, i4, 7);
                module(1, this.numcols - 1, i4, 8);
                i4++;
            }
            do {
                if (i5 < this.numrows && i3 >= 0 && !hasBit(i3, i5)) {
                    utah(i5, i3, i4);
                    i4++;
                }
                i5 -= 2;
                i3 += 2;
                if (i5 < 0 || i3 >= this.numcols) {
                    int i10 = i5 + 1;
                    int i11 = i3 + 3;
                }
                utah(i5, i3, i4);
                i4++;
                i5 -= 2;
                i3 += 2;
                break;
            } while (i3 >= this.numcols);
            int i102 = i5 + 1;
            int i112 = i3 + 3;
            do {
                if (i102 >= 0 && i112 < this.numcols && !hasBit(i112, i102)) {
                    utah(i102, i112, i4);
                    i4++;
                }
                i102 += 2;
                i112 -= 2;
                if (i102 >= this.numrows) {
                    break;
                }
            } while (i112 >= 0);
            i5 = i102 + 3;
            i3 = i112 + 1;
            i = this.numrows;
            if (i5 >= i && i3 >= (i2 = this.numcols)) {
                break;
            }
        }
        if (!hasBit(i2 - 1, i - 1)) {
            setBit(this.numcols - 1, this.numrows - 1, true);
            setBit(this.numcols - 2, this.numrows - 2, true);
        }
    }

    /* access modifiers changed from: package-private */
    public final void setBit(int i, int i2, boolean z) {
        this.bits[(i2 * this.numcols) + i] = z ? (byte) 1 : 0;
    }
}
