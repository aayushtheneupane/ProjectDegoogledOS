package com.android.messaging.util.exif;

/* renamed from: com.android.messaging.util.exif.o */
public class C1446o {

    /* renamed from: fM */
    private final long f2306fM;

    /* renamed from: gM */
    private final long f2307gM;

    public C1446o(long j, long j2) {
        this.f2306fM = j;
        this.f2307gM = j2;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1446o)) {
            return false;
        }
        C1446o oVar = (C1446o) obj;
        if (this.f2306fM == oVar.f2306fM && this.f2307gM == oVar.f2307gM) {
            return true;
        }
        return false;
    }

    public long getDenominator() {
        return this.f2307gM;
    }

    public long getNumerator() {
        return this.f2306fM;
    }

    public String toString() {
        return this.f2306fM + "/" + this.f2307gM;
    }
}
