package p000;

import com.google.apps.tiktok.concurrent.AndroidFuturesService;

/* renamed from: gop */
/* compiled from: PG */
public final /* synthetic */ class gop implements Runnable {

    /* renamed from: a */
    private final AndroidFuturesService f11757a;

    /* renamed from: b */
    private final int f11758b;

    public gop(AndroidFuturesService androidFuturesService, int i) {
        this.f11757a = androidFuturesService;
        this.f11758b = i;
    }

    public final void run() {
        this.f11757a.stopSelf(this.f11758b);
    }
}
