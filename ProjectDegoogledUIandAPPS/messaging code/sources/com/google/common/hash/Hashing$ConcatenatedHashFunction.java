package com.google.common.hash;

final class Hashing$ConcatenatedHashFunction extends C1710a {

    /* renamed from: nO */
    final C1711b[] f2559nO;

    /* renamed from: oO */
    private final int f2560oO;

    public boolean equals(Object obj) {
        if (obj instanceof Hashing$ConcatenatedHashFunction) {
            Hashing$ConcatenatedHashFunction hashing$ConcatenatedHashFunction = (Hashing$ConcatenatedHashFunction) obj;
            if (this.f2560oO == hashing$ConcatenatedHashFunction.f2560oO && this.f2559nO.length == hashing$ConcatenatedHashFunction.f2559nO.length) {
                int i = 0;
                while (true) {
                    C1711b[] bVarArr = this.f2559nO;
                    if (i >= bVarArr.length) {
                        return true;
                    }
                    if (!bVarArr[i].equals(hashing$ConcatenatedHashFunction.f2559nO[i])) {
                        return false;
                    }
                    i++;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int i = this.f2560oO;
        for (C1711b hashCode : this.f2559nO) {
            i ^= hashCode.hashCode();
        }
        return i;
    }
}
