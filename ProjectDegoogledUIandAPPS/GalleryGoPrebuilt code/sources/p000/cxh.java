package p000;

/* renamed from: cxh */
/* compiled from: PG */
public enum cxh implements iiz {
    UNKNOWN_MEDIA_TYPE(0),
    IMAGE(1),
    VIDEO(2);
    

    /* renamed from: d */
    public final int f5906d;

    /* renamed from: a */
    public static cxh m5592a(int i) {
        if (i == 0) {
            return UNKNOWN_MEDIA_TYPE;
        }
        if (i == 1) {
            return IMAGE;
        }
        if (i != 2) {
            return null;
        }
        return VIDEO;
    }

    /* renamed from: a */
    public static ijb m5593a() {
        return cxg.f5901a;
    }

    public final int getNumber() {
        return this.f5906d;
    }

    public final String toString() {
        return Integer.toString(this.f5906d);
    }

    private cxh(int i) {
        this.f5906d = i;
    }
}
