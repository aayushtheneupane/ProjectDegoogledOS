package com.android.dialer.callcomposer.camera.exif;

import java.util.Objects;

public class Rational {
    private final long denominator;
    private final long numerator;

    Rational(long j, long j2) {
        this.numerator = j;
        this.denominator = j2;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Rational)) {
            return false;
        }
        Rational rational = (Rational) obj;
        if (this.numerator == rational.numerator && this.denominator == rational.denominator) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public long getDenominator() {
        return this.denominator;
    }

    /* access modifiers changed from: package-private */
    public long getNumerator() {
        return this.numerator;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Long.valueOf(this.numerator), Long.valueOf(this.denominator)});
    }

    public String toString() {
        return this.numerator + "/" + this.denominator;
    }
}
