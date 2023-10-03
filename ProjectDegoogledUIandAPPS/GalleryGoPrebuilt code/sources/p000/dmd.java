package p000;

/* renamed from: dmd */
/* compiled from: PG */
public enum dmd implements iiz {
    UNKNOWN(0),
    SHARE(1),
    EDIT_IN(2),
    USE_AS(3);
    

    /* renamed from: e */
    public final int f6822e;

    /* renamed from: a */
    public static dmd m6351a(int i) {
        if (i == 0) {
            return UNKNOWN;
        }
        if (i == 1) {
            return SHARE;
        }
        if (i == 2) {
            return EDIT_IN;
        }
        if (i != 3) {
            return null;
        }
        return USE_AS;
    }

    /* renamed from: a */
    public static ijb m6352a() {
        return dmc.f6816a;
    }

    public final int getNumber() {
        return this.f6822e;
    }

    public final String toString() {
        return Integer.toString(this.f6822e);
    }

    private dmd(int i) {
        this.f6822e = i;
    }
}
