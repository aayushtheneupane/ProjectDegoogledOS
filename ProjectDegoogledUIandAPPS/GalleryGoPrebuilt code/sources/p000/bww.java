package p000;

import android.graphics.drawable.Drawable;
import p003j$.util.function.Consumer;

/* renamed from: bww */
/* compiled from: PG */
final class bww extends bek {

    /* renamed from: a */
    private final /* synthetic */ Consumer f3787a;

    /* renamed from: b */
    private final /* synthetic */ Runnable f3788b;

    public bww(Consumer consumer, Runnable runnable) {
        this.f3787a = consumer;
        this.f3788b = runnable;
    }

    /* renamed from: b */
    public final void mo1798b(Drawable drawable) {
        this.f3788b.run();
    }

    /* renamed from: a */
    public final void mo1432a(Drawable drawable) {
        cwn.m5510a("OemExternalEditorButtonMixin: Failed to load icon.", new Object[0]);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo1433a(Object obj, bex bex) {
        this.f3787a.accept((Drawable) obj);
    }
}
