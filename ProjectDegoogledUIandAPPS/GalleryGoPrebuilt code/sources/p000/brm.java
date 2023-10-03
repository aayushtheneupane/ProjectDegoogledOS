package p000;

/* renamed from: brm */
/* compiled from: PG */
public enum brm implements iiz {
    SIZE_AND_NUMBER(0),
    NUMBER_OF_ITEMS(1);
    

    /* renamed from: c */
    public final int f3428c;

    /* renamed from: a */
    public static brm m3486a(int i) {
        if (i == 0) {
            return SIZE_AND_NUMBER;
        }
        if (i != 1) {
            return null;
        }
        return NUMBER_OF_ITEMS;
    }

    /* renamed from: a */
    public static ijb m3487a() {
        return brl.f3424a;
    }

    public final int getNumber() {
        return this.f3428c;
    }

    public final String toString() {
        return Integer.toString(this.f3428c);
    }

    private brm(int i) {
        this.f3428c = i;
    }
}
