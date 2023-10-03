package p000;

import android.content.Context;
import android.support.p002v7.widget.RecyclerView;

/* renamed from: fny */
/* compiled from: PG */
final class fny {

    /* renamed from: a */
    private volatile boolean f10125a;

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo5990a(Context context, String str) {
        boolean z = this.f10125a;
        long j = RecyclerView.FOREVER_NS;
        if (!z) {
            try {
                j = exi.m8309a(context.getContentResolver(), str, (long) RecyclerView.FOREVER_NS);
            } catch (SecurityException e) {
                this.f10125a = true;
                flw.m9198b("GservicesWrapper", "Failed to read GServices.", e, new Object[0]);
            }
        }
        return j > 0 && j <= 282316422;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final boolean mo5991b(Context context, String str) {
        if (!this.f10125a) {
            try {
                return exi.m8315a(context.getContentResolver(), str, false);
            } catch (SecurityException e) {
                this.f10125a = true;
                flw.m9198b("GservicesWrapper", "Failed to read GServices.", e, new Object[0]);
            }
        }
        return false;
    }
}
