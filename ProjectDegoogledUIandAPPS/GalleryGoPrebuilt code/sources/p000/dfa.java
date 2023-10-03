package p000;

import java.util.List;
import java.util.concurrent.Callable;

/* renamed from: dfa */
/* compiled from: PG */
final /* synthetic */ class dfa implements Callable {

    /* renamed from: a */
    private final inw f6428a;

    /* renamed from: b */
    private final List f6429b;

    public dfa(inw inw, List list) {
        this.f6428a = inw;
        this.f6429b = list;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r3 = p000.hnb.m11765a("SelfieDetector isProbablyASelfie");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r0 = r0.f5995a.mo4664a(r1.mo3991O());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r5 = new p000.abz(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0087, code lost:
        if (android.os.Build.MANUFACTURER.equals(r5.mo153a("Make")) == false) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0095, code lost:
        if (android.os.Build.MODEL.equals(r5.mo153a("Model")) == false) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0097, code lost:
        r5 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x009a, code lost:
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x009b, code lost:
        if (r0 == null) goto L_0x00a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a0, code lost:
        if (r3 == null) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00a5, code lost:
        r2 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00a7, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a8, code lost:
        if (r0 != null) goto L_0x00aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ae, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        p000.ifn.m12935a(r5, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00b3, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b4, code lost:
        if (r3 != null) goto L_0x00b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00ba, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        p000.ifn.m12935a(r0, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00be, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00bf, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00c0, code lost:
        p000.cwn.m5511a((java.lang.Throwable) r0, "Failed to read EXIF from %s", r1.mo3991O().toString());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object call() {
        /*
            r13 = this;
            inw r0 = r13.f6428a
            java.util.List r1 = r13.f6429b
            int r2 = p000.dfj.f6440a
            java.lang.Object r0 = r0.mo9034a()
            cye r0 = (p000.cye) r0
            r2 = 0
            java.lang.Object r1 = r1.get(r2)
            cyg r1 = (p000.cyg) r1
            int r3 = r1.mo3909c()
            r4 = 1
            if (r3 != r4) goto L_0x00d8
            int r3 = r1.mo3913f()
            int r5 = r1.mo3911e()
            android.graphics.Rect[] r6 = r0.f5996b
            float r7 = (float) r3
            r8 = 1008981770(0x3c23d70a, float:0.01)
            float r7 = r7 * r8
            int r7 = (int) r7
            int r7 = r7 + r3
            float r9 = (float) r5
            float r9 = r9 * r8
            int r8 = (int) r9
            int r8 = r8 + r5
            android.graphics.Rect r9 = new android.graphics.Rect
            r9.<init>(r2, r2, r3, r5)
            android.graphics.Rect r10 = new android.graphics.Rect
            r10.<init>(r2, r2, r5, r3)
            android.graphics.Rect r3 = new android.graphics.Rect
            r3.<init>(r2, r2, r7, r8)
            android.graphics.Rect r5 = new android.graphics.Rect
            r5.<init>(r2, r2, r8, r7)
            int r7 = r6.length
            r8 = 0
        L_0x0047:
            if (r8 >= r7) goto L_0x00d7
            r11 = r6[r8]
            boolean r12 = r3.contains(r11)
            if (r12 == 0) goto L_0x0058
            boolean r12 = r11.contains(r9)
            if (r12 == 0) goto L_0x0058
            goto L_0x0066
        L_0x0058:
            boolean r12 = r5.contains(r11)
            if (r12 != 0) goto L_0x0060
        L_0x005e:
            goto L_0x00d3
        L_0x0060:
            boolean r11 = r11.contains(r10)
            if (r11 == 0) goto L_0x005e
        L_0x0066:
            java.lang.String r3 = "SelfieDetector isProbablyASelfie"
            hlj r3 = p000.hnb.m11765a((java.lang.String) r3)     // Catch:{ IOException -> 0x00bf }
            ebi r0 = r0.f5995a     // Catch:{ all -> 0x00b3 }
            android.net.Uri r5 = r1.mo3991O()     // Catch:{ all -> 0x00b3 }
            java.io.InputStream r0 = r0.mo4664a(r5)     // Catch:{ all -> 0x00b3 }
            abz r5 = new abz     // Catch:{ all -> 0x00a7 }
            r5.<init>(r0)     // Catch:{ all -> 0x00a7 }
            java.lang.String r6 = android.os.Build.MANUFACTURER     // Catch:{ all -> 0x00a7 }
            java.lang.String r7 = "Make"
            java.lang.String r7 = r5.mo153a((java.lang.String) r7)     // Catch:{ all -> 0x00a7 }
            boolean r6 = r6.equals(r7)     // Catch:{ all -> 0x00a7 }
            if (r6 == 0) goto L_0x009a
            java.lang.String r6 = android.os.Build.MODEL     // Catch:{ all -> 0x00a7 }
            java.lang.String r7 = "Model"
            java.lang.String r5 = r5.mo153a((java.lang.String) r7)     // Catch:{ all -> 0x00a7 }
            boolean r5 = r6.equals(r5)     // Catch:{ all -> 0x00a7 }
            if (r5 == 0) goto L_0x0099
            r5 = 1
            goto L_0x009b
        L_0x0099:
        L_0x009a:
            r5 = 0
        L_0x009b:
            if (r0 == 0) goto L_0x00a0
            r0.close()     // Catch:{ all -> 0x00b3 }
        L_0x00a0:
            if (r3 == 0) goto L_0x00a5
            r3.close()     // Catch:{ IOException -> 0x00bf }
        L_0x00a5:
            r2 = r5
            goto L_0x00d9
        L_0x00a7:
            r5 = move-exception
            if (r0 == 0) goto L_0x00b2
            r0.close()     // Catch:{ all -> 0x00ae }
            goto L_0x00b2
        L_0x00ae:
            r0 = move-exception
            p000.ifn.m12935a((java.lang.Throwable) r5, (java.lang.Throwable) r0)     // Catch:{ all -> 0x00b3 }
        L_0x00b2:
            throw r5     // Catch:{ all -> 0x00b3 }
        L_0x00b3:
            r0 = move-exception
            if (r3 == 0) goto L_0x00be
            r3.close()     // Catch:{ all -> 0x00ba }
            goto L_0x00be
        L_0x00ba:
            r3 = move-exception
            p000.ifn.m12935a((java.lang.Throwable) r0, (java.lang.Throwable) r3)     // Catch:{ IOException -> 0x00bf }
        L_0x00be:
            throw r0     // Catch:{ IOException -> 0x00bf }
        L_0x00bf:
            r0 = move-exception
            java.lang.Object[] r3 = new java.lang.Object[r4]
            android.net.Uri r1 = r1.mo3991O()
            java.lang.String r1 = r1.toString()
            r3[r2] = r1
            java.lang.String r1 = "Failed to read EXIF from %s"
            p000.cwn.m5511a((java.lang.Throwable) r0, (java.lang.String) r1, (java.lang.Object[]) r3)
            goto L_0x00d9
        L_0x00d3:
            int r8 = r8 + 1
            goto L_0x0047
        L_0x00d7:
        L_0x00d8:
        L_0x00d9:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.dfa.call():java.lang.Object");
    }
}
