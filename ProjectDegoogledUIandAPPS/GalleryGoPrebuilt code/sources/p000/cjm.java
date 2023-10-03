package p000;

/* renamed from: cjm */
/* compiled from: PG */
public enum cjm implements iiz {
    UNKNOWN(0),
    ACCEPTED(1),
    REVOKED(2),
    CONDITIONALLY_ACCEPTED(3),
    NOT_APPLICABLE(4);
    

    /* renamed from: f */
    public final int f4502f;

    /* renamed from: a */
    public static cjm m4399a(int i) {
        if (i == 0) {
            return UNKNOWN;
        }
        if (i == 1) {
            return ACCEPTED;
        }
        if (i == 2) {
            return REVOKED;
        }
        if (i == 3) {
            return CONDITIONALLY_ACCEPTED;
        }
        if (i != 4) {
            return null;
        }
        return NOT_APPLICABLE;
    }

    /* renamed from: a */
    public static ijb m4400a() {
        return cjl.f4495a;
    }

    public final int getNumber() {
        return this.f4502f;
    }

    public final String toString() {
        return Integer.toString(this.f4502f);
    }

    private cjm(int i) {
        this.f4502f = i;
    }
}
