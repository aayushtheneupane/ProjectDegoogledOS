package p000;

import android.util.SparseArray;

/* renamed from: car */
/* compiled from: PG */
public enum car {
    COLOR_POP(-1),
    ORIGINAL(0),
    AUTO_ENHANCE(1),
    WEST(2),
    PALMA(3),
    METRO(4),
    EIFFEL(5),
    BLUSH(6),
    MODENA(7),
    REEL(8),
    VOGUE(9),
    OLLIE(10),
    BAZAAR(11),
    ALPACA(12),
    VISTA(13);
    

    /* renamed from: q */
    private static final SparseArray f3988q = null;

    /* renamed from: p */
    public final int f3990p;

    static {
        int i;
        f3988q = new SparseArray();
        for (car car : values()) {
            f3988q.put(car.f3990p, car);
        }
    }

    private car(int i) {
        this.f3990p = i;
    }

    /* renamed from: a */
    public static car m3967a(int i) {
        return (car) f3988q.get(i);
    }

    /* renamed from: a */
    public static car m3968a(String str) {
        return (car) Enum.valueOf(car.class, str);
    }
}
