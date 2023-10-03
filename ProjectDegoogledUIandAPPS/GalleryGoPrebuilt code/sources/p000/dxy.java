package p000;

/* renamed from: dxy */
/* compiled from: PG */
public enum dxy implements iiz {
    DEFAULT_SORT_METHOD(0),
    DESCENDING_CAPTURE_TIMESTAMP(1),
    DESCENDING_FILE_SIZE_BYTES(2),
    DESCENDING_TRASHED_TIMESTAMP(3);
    

    /* renamed from: e */
    public final int f7618e;

    /* renamed from: a */
    public static dxy m6881a(int i) {
        if (i == 0) {
            return DEFAULT_SORT_METHOD;
        }
        if (i == 1) {
            return DESCENDING_CAPTURE_TIMESTAMP;
        }
        if (i == 2) {
            return DESCENDING_FILE_SIZE_BYTES;
        }
        if (i != 3) {
            return null;
        }
        return DESCENDING_TRASHED_TIMESTAMP;
    }

    /* renamed from: a */
    public static ijb m6882a() {
        return dxx.f7612a;
    }

    public final int getNumber() {
        return this.f7618e;
    }

    public final String toString() {
        return Integer.toString(this.f7618e);
    }

    private dxy(int i) {
        this.f7618e = i;
    }
}
