package p000;

import android.support.p002v7.widget.RecyclerView;

/* renamed from: cpi */
/* compiled from: PG */
public enum cpi implements iiz {
    DEFAULT(0),
    PICKER(1),
    HOME(2),
    HOME_NO_MOVE(5),
    FOLDER_CREATION(3),
    SHOW_AND_HIDE(4),
    FIND_LARGE_FILES(6);
    

    /* renamed from: h */
    public final int f5364h;

    /* renamed from: a */
    public static cpi m5217a(int i) {
        switch (i) {
            case 0:
                return DEFAULT;
            case 1:
                return PICKER;
            case RecyclerView.SCROLL_STATE_SETTLING:
                return HOME;
            case 3:
                return FOLDER_CREATION;
            case 4:
                return SHOW_AND_HIDE;
            case 5:
                return HOME_NO_MOVE;
            case 6:
                return FIND_LARGE_FILES;
            default:
                return null;
        }
    }

    /* renamed from: a */
    public static ijb m5218a() {
        return cph.f5355a;
    }

    public final int getNumber() {
        return this.f5364h;
    }

    public final String toString() {
        return Integer.toString(this.f5364h);
    }

    private cpi(int i) {
        this.f5364h = i;
    }
}
