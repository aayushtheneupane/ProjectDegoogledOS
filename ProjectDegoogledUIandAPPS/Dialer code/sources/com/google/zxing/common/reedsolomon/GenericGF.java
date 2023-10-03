package com.google.zxing.common.reedsolomon;

import com.android.tools.p006r8.GeneratedOutlineSupport;

public final class GenericGF {
    public static final GenericGF AZTEC_DATA_10 = new GenericGF(1033, 1024, 1);
    public static final GenericGF AZTEC_DATA_12 = new GenericGF(4201, 4096, 1);
    public static final GenericGF AZTEC_DATA_6 = new GenericGF(67, 64, 1);
    public static final GenericGF AZTEC_DATA_8 = DATA_MATRIX_FIELD_256;
    public static final GenericGF AZTEC_PARAM = new GenericGF(19, 16, 1);
    public static final GenericGF DATA_MATRIX_FIELD_256 = new GenericGF(301, 256, 1);
    public static final GenericGF QR_CODE_FIELD_256 = new GenericGF(285, 256, 0);
    private int[] expTable;
    private final int generatorBase;
    private boolean initialized = false;
    private int[] logTable;
    private final int primitive;
    private final int size;
    private GenericGFPoly zero;

    public GenericGF(int i, int i2, int i3) {
        this.primitive = i;
        this.size = i2;
        this.generatorBase = i3;
        if (i2 <= 0) {
            initialize();
        }
    }

    static int addOrSubtract(int i, int i2) {
        return i ^ i2;
    }

    private void checkInit() {
        if (!this.initialized) {
            initialize();
        }
    }

    private void initialize() {
        int i = this.size;
        this.expTable = new int[i];
        this.logTable = new int[i];
        int i2 = 0;
        int i3 = 1;
        while (true) {
            int i4 = this.size;
            if (i2 >= i4) {
                break;
            }
            this.expTable[i2] = i3;
            i3 <<= 1;
            if (i3 >= i4) {
                i3 = (i3 ^ this.primitive) & (i4 - 1);
            }
            i2++;
        }
        for (int i5 = 0; i5 < this.size - 1; i5++) {
            this.logTable[this.expTable[i5]] = i5;
        }
        this.zero = new GenericGFPoly(this, new int[]{0});
        new GenericGFPoly(this, new int[]{1});
        this.initialized = true;
    }

    /* access modifiers changed from: package-private */
    public GenericGFPoly buildMonomial(int i, int i2) {
        checkInit();
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.zero;
        } else {
            int[] iArr = new int[(i + 1)];
            iArr[0] = i2;
            return new GenericGFPoly(this, iArr);
        }
    }

    /* access modifiers changed from: package-private */
    public int exp(int i) {
        checkInit();
        return this.expTable[i];
    }

    public int getGeneratorBase() {
        return this.generatorBase;
    }

    /* access modifiers changed from: package-private */
    public GenericGFPoly getZero() {
        checkInit();
        return this.zero;
    }

    /* access modifiers changed from: package-private */
    public int inverse(int i) {
        checkInit();
        if (i != 0) {
            return this.expTable[(this.size - this.logTable[i]) - 1];
        }
        throw new ArithmeticException();
    }

    /* access modifiers changed from: package-private */
    public int log(int i) {
        checkInit();
        if (i != 0) {
            return this.logTable[i];
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: package-private */
    public int multiply(int i, int i2) {
        checkInit();
        if (i == 0 || i2 == 0) {
            return 0;
        }
        int[] iArr = this.expTable;
        int[] iArr2 = this.logTable;
        return iArr[(iArr2[i] + iArr2[i2]) % (this.size - 1)];
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("GF(0x");
        outline13.append(Integer.toHexString(this.primitive));
        outline13.append(',');
        outline13.append(this.size);
        outline13.append(')');
        return outline13.toString();
    }
}
