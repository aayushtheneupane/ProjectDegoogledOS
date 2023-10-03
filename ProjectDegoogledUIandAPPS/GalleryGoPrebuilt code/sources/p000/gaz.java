package p000;

import android.os.CancellationSignal;

/* renamed from: gaz */
/* compiled from: PG */
final class gaz extends gbb implements CancellationSignal.OnCancelListener {

    /* renamed from: a */
    private final CancellationSignal f10821a = new CancellationSignal();

    public /* synthetic */ gaz(gba gba) {
        super(gba);
    }

    public final boolean cancel(boolean z) {
        this.f10821a.cancel();
        return super.cancel(z);
    }

    public final void onCancel() {
        super.cancel(true);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo6383a(p000.gba r3) {
        /*
            r2 = this;
            android.os.CancellationSignal r0 = r2.f10821a     // Catch:{ OperationCanceledException -> 0x003a }
            r0.setOnCancelListener(r2)     // Catch:{ OperationCanceledException -> 0x003a }
            android.os.CancellationSignal r0 = r2.f10821a     // Catch:{ OperationCanceledException -> 0x003a }
            android.database.Cursor r3 = r3.mo6368a(r0)     // Catch:{ OperationCanceledException -> 0x003a }
            boolean r0 = r2.isCancelled()     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x0016
            if (r3 == 0) goto L_0x0016
            r3.getCount()     // Catch:{ all -> 0x0020 }
        L_0x0016:
            boolean r0 = r2.mo8346b((java.lang.Object) r3)     // Catch:{ OperationCanceledException -> 0x003a }
            if (r0 != 0) goto L_0x002d
            p000.fsa.m9484a((java.io.Closeable) r3)     // Catch:{ OperationCanceledException -> 0x003a }
            return
        L_0x0020:
            r0 = move-exception
            r2.mo6952a((java.lang.Throwable) r0)     // Catch:{ all -> 0x002e }
            boolean r0 = r2.mo8346b((java.lang.Object) r3)     // Catch:{ OperationCanceledException -> 0x003a }
            if (r0 != 0) goto L_0x002d
            p000.fsa.m9484a((java.io.Closeable) r3)     // Catch:{ OperationCanceledException -> 0x003a }
        L_0x002d:
            return
        L_0x002e:
            r0 = move-exception
            boolean r1 = r2.mo8346b((java.lang.Object) r3)     // Catch:{ OperationCanceledException -> 0x003a }
            if (r1 == 0) goto L_0x0036
            goto L_0x0039
        L_0x0036:
            p000.fsa.m9484a((java.io.Closeable) r3)     // Catch:{ OperationCanceledException -> 0x003a }
        L_0x0039:
            throw r0     // Catch:{ OperationCanceledException -> 0x003a }
        L_0x003a:
            r3 = move-exception
            r3 = 1
            super.cancel(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.gaz.mo6383a(gba):void");
    }
}
