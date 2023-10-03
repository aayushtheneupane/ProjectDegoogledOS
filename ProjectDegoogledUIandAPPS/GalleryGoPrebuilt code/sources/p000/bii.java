package p000;

import java.util.List;

/* renamed from: bii */
/* compiled from: PG */
public final class bii {

    /* renamed from: a */
    public static final cns f2452a = new cns((byte[]) null);

    /* renamed from: a */
    public static bid m2609a(List list) {
        if (list != null && !list.isEmpty()) {
            return new bid(list);
        }
        throw new IllegalArgumentException("Uris must not be null or empty.");
    }

    /* renamed from: a */
    public static bie m2610a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("Window start can't be less than 0");
        } else if (i2 >= i) {
            return new bie(i, i2);
        } else {
            throw new IllegalArgumentException("Window end can't be less than window start");
        }
    }
}
