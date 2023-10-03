package p000;

import android.os.Build;

/* renamed from: aif */
/* compiled from: PG */
final class aif extends C0062cf {
    public aif() {
        super(3, 4);
    }

    /* renamed from: a */
    public final void mo519a(C0028az azVar) {
        int i = Build.VERSION.SDK_INT;
        azVar.mo1736c("UPDATE workspec SET schedule_requested_at=0 WHERE state NOT IN (2, 3, 5) AND schedule_requested_at=-1 AND interval_duration<>0");
    }
}
