package p000;

import android.database.ContentObserver;
import android.os.Handler;

/* renamed from: exh */
/* compiled from: PG */
final class exh extends ContentObserver {
    public exh() {
        super((Handler) null);
    }

    public final void onChange(boolean z) {
        exi.f9173d.set(true);
    }
}
