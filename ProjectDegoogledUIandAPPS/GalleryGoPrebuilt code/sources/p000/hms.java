package p000;

/* renamed from: hms */
/* compiled from: PG */
public enum hms implements iiz {
    UNKNOWN(0),
    REALTIME(1),
    UPTIME(2);
    

    /* renamed from: c */
    public final int f13054c;

    /* renamed from: a */
    public static hms m11751a(int i) {
        if (i == 0) {
            return UNKNOWN;
        }
        if (i == 1) {
            return REALTIME;
        }
        if (i != 2) {
            return null;
        }
        return UPTIME;
    }

    /* renamed from: a */
    public static ijb m11752a() {
        return hmr.f13049a;
    }

    public final int getNumber() {
        return this.f13054c;
    }

    public final String toString() {
        return Integer.toString(this.f13054c);
    }

    private hms(int i) {
        this.f13054c = i;
    }
}
