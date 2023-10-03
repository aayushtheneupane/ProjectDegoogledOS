package p000;

/* renamed from: isa */
/* compiled from: PG */
public enum isa implements iiz {
    UNKNOWN(0),
    PRIMES_INITIALIZED(1),
    PRIMES_CRASH_MONITORING_INITIALIZED(2),
    PRIMES_FIRST_ACTIVITY_LAUNCHED(3),
    PRIMES_CUSTOM_LAUNCHED(4);
    

    /* renamed from: c */
    public final int f14968c;

    /* renamed from: a */
    public static ijb m14375a() {
        return irz.f14959a;
    }

    /* renamed from: a */
    public static isa m14376a(int i) {
        if (i == 0) {
            return UNKNOWN;
        }
        if (i == 1) {
            return PRIMES_INITIALIZED;
        }
        if (i == 2) {
            return PRIMES_CRASH_MONITORING_INITIALIZED;
        }
        if (i == 3) {
            return PRIMES_FIRST_ACTIVITY_LAUNCHED;
        }
        if (i != 4) {
            return null;
        }
        return PRIMES_CUSTOM_LAUNCHED;
    }

    public final int getNumber() {
        return this.f14968c;
    }

    public final String toString() {
        return Integer.toString(this.f14968c);
    }

    private isa(int i) {
        this.f14968c = i;
    }
}
