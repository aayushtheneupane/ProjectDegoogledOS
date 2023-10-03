package p000;

import android.content.Intent;

/* renamed from: ajc */
/* compiled from: PG */
final class ajc implements Runnable {

    /* renamed from: a */
    private final ajf f620a;

    /* renamed from: b */
    private final Intent f621b;

    /* renamed from: c */
    private final int f622c;

    public ajc(ajf ajf, Intent intent, int i) {
        this.f620a = ajf;
        this.f621b = intent;
        this.f622c = i;
    }

    public final void run() {
        this.f620a.mo543a(this.f621b, this.f622c);
    }
}
