package com.google.zxing.common.reedsolomon;

import java.util.ArrayList;
import java.util.List;

public final class ReedSolomonEncoder {
    private final List<GenericGFPoly> cachedGenerators = new ArrayList();
    private final GenericGF field;

    public ReedSolomonEncoder(GenericGF genericGF) {
        this.field = genericGF;
        this.cachedGenerators.add(new GenericGFPoly(genericGF, new int[]{1}));
    }

    public void encode(int[] iArr, int i) {
        if (i != 0) {
            int length = iArr.length - i;
            if (length > 0) {
                if (i >= this.cachedGenerators.size()) {
                    List<GenericGFPoly> list = this.cachedGenerators;
                    GenericGFPoly genericGFPoly = list.get(list.size() - 1);
                    for (int size = this.cachedGenerators.size(); size <= i; size++) {
                        GenericGF genericGF = this.field;
                        genericGFPoly = genericGFPoly.multiply(new GenericGFPoly(genericGF, new int[]{1, genericGF.exp(genericGF.getGeneratorBase() + (size - 1))}));
                        this.cachedGenerators.add(genericGFPoly);
                    }
                }
                int[] iArr2 = new int[length];
                System.arraycopy(iArr, 0, iArr2, 0, length);
                int[] coefficients = new GenericGFPoly(this.field, iArr2).multiplyByMonomial(i, 1).divide(this.cachedGenerators.get(i))[1].getCoefficients();
                int length2 = i - coefficients.length;
                for (int i2 = 0; i2 < length2; i2++) {
                    iArr[length + i2] = 0;
                }
                System.arraycopy(coefficients, 0, iArr, length + length2, coefficients.length);
                return;
            }
            throw new IllegalArgumentException("No data bytes provided");
        }
        throw new IllegalArgumentException("No error correction bytes");
    }
}
