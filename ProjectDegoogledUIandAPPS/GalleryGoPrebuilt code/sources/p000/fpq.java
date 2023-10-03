package p000;

import android.database.ContentObserver;
import android.os.Handler;

/* renamed from: fpq */
/* compiled from: PG */
final class fpq extends ContentObserver {

    /* renamed from: a */
    private final /* synthetic */ fpr f10225a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public fpq(fpr fpr) {
        super((Handler) null);
        this.f10225a = fpr;
    }

    public final void onChange(boolean z) {
        fpr fpr = this.f10225a;
        synchronized (fpr.f10230c) {
            fpr.f10231d = null;
            fqg.m9403a();
        }
        synchronized (fpr) {
            for (fps a : fpr.f10232e) {
                a.mo6023a();
            }
        }
    }
}
