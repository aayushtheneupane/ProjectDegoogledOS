package p000;

import android.support.p002v7.widget.RecyclerView;

/* renamed from: iqm */
/* compiled from: PG */
public enum iqm implements iiz {
    UNKNOWN(0),
    FOREGROUND_TO_BACKGROUND(1),
    BACKGROUND_TO_FOREGROUND(2),
    FOREGROUND_SERVICE_START(3),
    FOREGROUND_SERVICE_STOP(4),
    CUSTOM_MEASURE_START(5),
    CUSTOM_MEASURE_STOP(6);
    

    /* renamed from: d */
    public final int f14659d;

    /* renamed from: a */
    public static ijb m14320a() {
        return iql.f14650a;
    }

    /* renamed from: a */
    public static iqm m14321a(int i) {
        switch (i) {
            case 0:
                return UNKNOWN;
            case 1:
                return FOREGROUND_TO_BACKGROUND;
            case RecyclerView.SCROLL_STATE_SETTLING:
                return BACKGROUND_TO_FOREGROUND;
            case 3:
                return FOREGROUND_SERVICE_START;
            case 4:
                return FOREGROUND_SERVICE_STOP;
            case 5:
                return CUSTOM_MEASURE_START;
            case 6:
                return CUSTOM_MEASURE_STOP;
            default:
                return null;
        }
    }

    public final int getNumber() {
        return this.f14659d;
    }

    public final String toString() {
        return Integer.toString(this.f14659d);
    }

    private iqm(int i) {
        this.f14659d = i;
    }
}
