package p000;

import android.view.View;
import android.view.WindowId;

/* renamed from: agl */
/* compiled from: PG */
final class agl implements agm {

    /* renamed from: a */
    private final WindowId f391a;

    public agl(View view) {
        this.f391a = view.getWindowId();
    }

    public final boolean equals(Object obj) {
        return (obj instanceof agl) && ((agl) obj).f391a.equals(this.f391a);
    }

    public final int hashCode() {
        return this.f391a.hashCode();
    }
}
