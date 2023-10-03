package p000;

import android.os.Handler;

/* renamed from: eng */
/* compiled from: PG */
final class eng implements elo {

    /* renamed from: a */
    private final /* synthetic */ enp f8643a;

    public eng(enp enp) {
        this.f8643a = enp;
    }

    /* renamed from: a */
    public final void mo4969a(boolean z) {
        Handler handler = this.f8643a.f8684m;
        handler.sendMessage(handler.obtainMessage(1, Boolean.valueOf(z)));
    }
}
