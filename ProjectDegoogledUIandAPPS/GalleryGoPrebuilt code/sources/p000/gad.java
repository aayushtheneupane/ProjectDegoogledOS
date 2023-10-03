package p000;

import java.util.concurrent.Callable;

/* renamed from: gad */
/* compiled from: PG */
public final class gad implements Callable {

    /* renamed from: a */
    public volatile boolean f10776a;

    /* renamed from: b */
    private final /* synthetic */ gbp f10777b;

    /* renamed from: c */
    private final /* synthetic */ gbr f10778c;

    /* renamed from: d */
    private final /* synthetic */ gag f10779d;

    public gad(gag gag, gbp gbp, gbr gbr) {
        this.f10779d = gag;
        this.f10777b = gbp;
        this.f10778c = gbr;
    }

    /* JADX INFO: finally extract failed */
    public final Object call() {
        ife.m12876b(!this.f10779d.f10783a.inTransaction(), (Object) "Thread is already in a transaction! This should never happen, or this will be treated as a nested transaction.");
        this.f10779d.f10783a.beginTransactionWithListener(new gac(this));
        try {
            this.f10776a = true;
            ((hfy) ((gaa) this.f10777b).f10772a).f12686a.mo2584a(new hfz(this.f10778c));
            gbr.m9983a();
            this.f10779d.f10783a.setTransactionSuccessful();
            this.f10776a = false;
            this.f10779d.f10783a.endTransaction();
            return null;
        } catch (Throwable th) {
            this.f10779d.f10783a.endTransaction();
            throw th;
        }
    }
}
