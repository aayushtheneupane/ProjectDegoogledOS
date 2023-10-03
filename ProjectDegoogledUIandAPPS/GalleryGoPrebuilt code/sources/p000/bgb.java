package p000;

import android.os.Handler;

/* renamed from: bgb */
/* compiled from: PG */
final class bgb implements Handler.Callback {

    /* renamed from: a */
    private final /* synthetic */ bgo f2233a;

    public bgb(bgo bgo) {
        this.f2233a = bgo;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0005, code lost:
        r4 = r3.f2233a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean handleMessage(android.os.Message r4) {
        /*
            r3 = this;
            int r4 = r4.what
            r0 = 1
            if (r4 != r0) goto L_0x001b
            bgo r4 = r3.f2233a
            android.view.View$OnLongClickListener r1 = r4.f2294E
            if (r1 == 0) goto L_0x001b
            r2 = 0
            r4.f2344t = r2
            p000.bgb.super.setOnLongClickListener(r1)
            bgo r4 = r3.f2233a
            r4.performLongClick()
            bgo r4 = r3.f2233a
            p000.bgb.super.setOnLongClickListener((android.view.View.OnLongClickListener) null)
        L_0x001b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bgb.handleMessage(android.os.Message):boolean");
    }
}
