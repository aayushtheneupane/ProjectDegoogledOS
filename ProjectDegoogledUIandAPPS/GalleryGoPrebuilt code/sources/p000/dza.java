package p000;

/* renamed from: dza */
/* compiled from: PG */
public final class dza implements gud {

    /* renamed from: a */
    public final Object f7684a = new Object();

    /* renamed from: b */
    private final dwi f7685b;

    /* renamed from: c */
    private final dxy f7686c;

    /* renamed from: d */
    private dyt f7687d = null;

    /* renamed from: e */
    private ieh f7688e = ife.m12820a((Object) null);

    /* renamed from: f */
    private final /* synthetic */ dzb f7689f;

    public /* synthetic */ dza(dzb dzb, dwi dwi, dxy dxy) {
        this.f7689f = dzb;
        this.f7685b = dwi;
        this.f7686c = dxy;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:?, code lost:
        p000.ifn.m12935a(r1, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x01a2, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x01a3, code lost:
        if (r9 != null) goto L_0x01a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x01a9, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:?, code lost:
        p000.ifn.m12935a(r1, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01ae, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01af, code lost:
        if (r7 != null) goto L_0x01b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01b5, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:?, code lost:
        p000.ifn.m12935a(r1, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01d6, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01d7, code lost:
        if (r5 != null) goto L_0x01d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01dd, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:?, code lost:
        p000.ifn.m12935a(r1, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x01ec, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:19:0x0052, B:44:0x00fc, B:50:0x0124, B:61:0x01a5, B:69:0x01b1, B:84:0x01d9, B:97:0x01e8] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.ieh mo2733a() {
        /*
            r13 = this;
            java.lang.Object r0 = r13.f7684a
            monitor-enter(r0)
            ieh r1 = r13.f7688e     // Catch:{ all -> 0x01f1 }
            r2 = 1
            r1.cancel(r2)     // Catch:{ all -> 0x01f1 }
            dyt r1 = new dyt     // Catch:{ all -> 0x01f1 }
            r3 = 0
            r1.<init>(r3)     // Catch:{ all -> 0x01f1 }
            r13.f7687d = r1     // Catch:{ all -> 0x01f1 }
            java.lang.String r4 = "Get all categories for photo grid"
            hlj r4 = p000.hnb.m11765a((java.lang.String) r4)     // Catch:{ all -> 0x01f1 }
            dwi r5 = r13.f7685b     // Catch:{ all -> 0x01e5 }
            int r5 = r5.f7494a     // Catch:{ all -> 0x01e5 }
            r5 = r5 & r2
            if (r5 == 0) goto L_0x0027
            hso r5 = p000.hso.m12047f()     // Catch:{ all -> 0x01e5 }
            ieh r5 = p000.ife.m12820a((java.lang.Object) r5)     // Catch:{ all -> 0x01e5 }
            goto L_0x0035
        L_0x0027:
            dzb r5 = r13.f7689f     // Catch:{ all -> 0x01e5 }
            bnq r5 = r5.f7692c     // Catch:{ all -> 0x01e5 }
            java.lang.Object r6 = r5.f3216m     // Catch:{ all -> 0x01e5 }
            monitor-enter(r6)     // Catch:{ all -> 0x01e5 }
            grf r5 = r5.f3217n     // Catch:{ all -> 0x01e2 }
            ieh r5 = r5.mo6948a()     // Catch:{ all -> 0x01e2 }
            monitor-exit(r6)     // Catch:{ all -> 0x01e2 }
        L_0x0035:
            dyv r6 = new dyv     // Catch:{ all -> 0x01e5 }
            r6.<init>(r13, r1)     // Catch:{ all -> 0x01e5 }
            idh r1 = p000.idh.f13918a     // Catch:{ all -> 0x01e5 }
            ieh r1 = p000.gte.m10770a((p000.ieh) r5, (p000.hpr) r6, (java.util.concurrent.Executor) r1)     // Catch:{ all -> 0x01e5 }
            ieh r1 = r4.mo7548a(r1)     // Catch:{ all -> 0x01e5 }
            if (r4 != 0) goto L_0x0047
            goto L_0x004a
        L_0x0047:
            r4.close()     // Catch:{ all -> 0x01f1 }
        L_0x004a:
            dyt r4 = r13.f7687d     // Catch:{ all -> 0x01f1 }
            java.lang.String r5 = "Get items for photo grid"
            hlj r5 = p000.hnb.m11765a((java.lang.String) r5)     // Catch:{ all -> 0x01f1 }
            dzb r6 = r13.f7689f     // Catch:{ all -> 0x01d6 }
            dyr r6 = r6.f7690a     // Catch:{ all -> 0x01d6 }
            dyi r7 = new dyi     // Catch:{ all -> 0x01d6 }
            r7.<init>(r3)     // Catch:{ all -> 0x01d6 }
            cxd r8 = p000.cxd.f5884h     // Catch:{ all -> 0x01d6 }
            r7.mo4581a((p000.cxd) r8)     // Catch:{ all -> 0x01d6 }
            r7.f7652c = r2     // Catch:{ all -> 0x01d6 }
            dxy r8 = p000.dxy.DEFAULT_SORT_METHOD     // Catch:{ all -> 0x01d6 }
            r7.mo4582a((p000.dxy) r8)     // Catch:{ all -> 0x01d6 }
            dwi r8 = r13.f7685b     // Catch:{ all -> 0x01d6 }
            cxd r8 = r8.f7495b     // Catch:{ all -> 0x01d6 }
            if (r8 != 0) goto L_0x006f
            cxd r8 = p000.cxd.f5884h     // Catch:{ all -> 0x01d6 }
        L_0x006f:
            r7.mo4581a((p000.cxd) r8)     // Catch:{ all -> 0x01d6 }
            r8 = 3
            r7.f7652c = r8     // Catch:{ all -> 0x01d6 }
            dxy r9 = r13.f7686c     // Catch:{ all -> 0x01d6 }
            r7.mo4582a((p000.dxy) r9)     // Catch:{ all -> 0x01d6 }
            java.lang.String r9 = ""
            java.lang.String r10 = " filter"
            cxd r11 = r7.f7650a     // Catch:{ all -> 0x01d6 }
            if (r11 == 0) goto L_0x0083
            goto L_0x0084
        L_0x0083:
            r9 = r10
        L_0x0084:
            int r10 = r7.f7652c     // Catch:{ all -> 0x01d6 }
            if (r10 != 0) goto L_0x008e
            java.lang.String r10 = " timeGranularity"
            java.lang.String r9 = r9.concat(r10)     // Catch:{ all -> 0x01d6 }
        L_0x008e:
            dxy r10 = r7.f7651b     // Catch:{ all -> 0x01d6 }
            if (r10 != 0) goto L_0x009c
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ all -> 0x01d6 }
            java.lang.String r10 = " sortMethod"
            java.lang.String r9 = r9.concat(r10)     // Catch:{ all -> 0x01d6 }
        L_0x009c:
            boolean r10 = r9.isEmpty()     // Catch:{ all -> 0x01d6 }
            if (r10 == 0) goto L_0x01ba
            dyg r9 = new dyg     // Catch:{ all -> 0x01d6 }
            cxd r10 = r7.f7650a     // Catch:{ all -> 0x01d6 }
            int r11 = r7.f7652c     // Catch:{ all -> 0x01d6 }
            dxy r7 = r7.f7651b     // Catch:{ all -> 0x01d6 }
            r9.<init>(r10, r11, r7)     // Catch:{ all -> 0x01d6 }
            dyq r6 = r6.f7667f     // Catch:{ all -> 0x01d6 }
            java.lang.Object r6 = r6.mo9237a((java.lang.Object) r9)     // Catch:{ all -> 0x01d6 }
            grf r6 = (p000.grf) r6     // Catch:{ all -> 0x01d6 }
            ieh r6 = r6.mo6948a()     // Catch:{ all -> 0x01d6 }
            dyw r7 = new dyw     // Catch:{ all -> 0x01d6 }
            r7.<init>(r13, r4)     // Catch:{ all -> 0x01d6 }
            idh r4 = p000.idh.f13918a     // Catch:{ all -> 0x01d6 }
            ieh r4 = p000.gte.m10770a((p000.ieh) r6, (p000.hpr) r7, (java.util.concurrent.Executor) r4)     // Catch:{ all -> 0x01d6 }
            ieh r4 = r5.mo7548a(r4)     // Catch:{ all -> 0x01d6 }
            if (r5 != 0) goto L_0x00cb
            goto L_0x00ce
        L_0x00cb:
            r5.close()     // Catch:{ all -> 0x01f1 }
        L_0x00ce:
            dyt r5 = r13.f7687d     // Catch:{ all -> 0x01f1 }
            dwi r6 = r13.f7685b     // Catch:{ all -> 0x01f1 }
            boolean r6 = r6.f7496c     // Catch:{ all -> 0x01f1 }
            if (r6 == 0) goto L_0x00e1
            dzb r6 = r13.f7689f     // Catch:{ all -> 0x01f1 }
            dzl r6 = r6.f7691b     // Catch:{ all -> 0x01f1 }
            grf r6 = r6.f7723e     // Catch:{ all -> 0x01f1 }
            ieh r6 = r6.mo6948a()     // Catch:{ all -> 0x01f1 }
            goto L_0x00e9
        L_0x00e1:
            j$.util.Optional r6 = p003j$.util.Optional.empty()     // Catch:{ all -> 0x01f1 }
            ieh r6 = p000.ife.m12820a((java.lang.Object) r6)     // Catch:{ all -> 0x01f1 }
        L_0x00e9:
            dyy r7 = new dyy     // Catch:{ all -> 0x01f1 }
            r7.<init>(r13, r5)     // Catch:{ all -> 0x01f1 }
            idh r5 = p000.idh.f13918a     // Catch:{ all -> 0x01f1 }
            ieh r5 = p000.gte.m10770a((p000.ieh) r6, (p000.hpr) r7, (java.util.concurrent.Executor) r5)     // Catch:{ all -> 0x01f1 }
            dyt r6 = r13.f7687d     // Catch:{ all -> 0x01f1 }
            java.lang.String r7 = "Get special types for photo grid"
            hlj r7 = p000.hnb.m11765a((java.lang.String) r7)     // Catch:{ all -> 0x01f1 }
            dzb r9 = r13.f7689f     // Catch:{ all -> 0x01ae }
            dji r9 = r9.f7693d     // Catch:{ all -> 0x01ae }
            ieh r9 = r9.mo4162a()     // Catch:{ all -> 0x01ae }
            r6.getClass()     // Catch:{ all -> 0x01ae }
            dyx r10 = new dyx     // Catch:{ all -> 0x01ae }
            r10.<init>(r6)     // Catch:{ all -> 0x01ae }
            idh r6 = p000.idh.f13918a     // Catch:{ all -> 0x01ae }
            ieh r6 = p000.gte.m10770a((p000.ieh) r9, (p000.hpr) r10, (java.util.concurrent.Executor) r6)     // Catch:{ all -> 0x01ae }
            ieh r6 = r7.mo7548a(r6)     // Catch:{ all -> 0x01ae }
            if (r7 != 0) goto L_0x0119
            goto L_0x011c
        L_0x0119:
            r7.close()     // Catch:{ all -> 0x01f1 }
        L_0x011c:
            dyt r7 = r13.f7687d     // Catch:{ all -> 0x01f1 }
            java.lang.String r9 = "Restore multiselect state"
            hlj r9 = p000.hnb.m11765a((java.lang.String) r9)     // Catch:{ all -> 0x01f1 }
            dzb r10 = r13.f7689f     // Catch:{ all -> 0x01a2 }
            dzp r10 = r10.f7697h     // Catch:{ all -> 0x01a2 }
            fzx r10 = r10.f7730a     // Catch:{ all -> 0x01a2 }
            ieh r10 = r10.mo6359a()     // Catch:{ all -> 0x01a2 }
            hpr r11 = p000.dzo.f7729a     // Catch:{ all -> 0x01a2 }
            idh r12 = p000.idh.f13918a     // Catch:{ all -> 0x01a2 }
            ieh r10 = p000.gte.m10770a((p000.ieh) r10, (p000.hpr) r11, (java.util.concurrent.Executor) r12)     // Catch:{ all -> 0x01a2 }
            dyz r11 = new dyz     // Catch:{ all -> 0x01a2 }
            r11.<init>(r13, r7)     // Catch:{ all -> 0x01a2 }
            idh r7 = p000.idh.f13918a     // Catch:{ all -> 0x01a2 }
            ieh r7 = p000.gte.m10770a((p000.ieh) r10, (p000.hpr) r11, (java.util.concurrent.Executor) r7)     // Catch:{ all -> 0x01a2 }
            ieh r7 = r9.mo7548a(r7)     // Catch:{ all -> 0x01a2 }
            if (r9 != 0) goto L_0x0148
            goto L_0x014b
        L_0x0148:
            r9.close()     // Catch:{ all -> 0x01f1 }
        L_0x014b:
            r9 = 5
            ieh[] r9 = new p000.ieh[r9]     // Catch:{ all -> 0x01f1 }
            r10 = 0
            r9[r10] = r1     // Catch:{ all -> 0x01f1 }
            r9[r2] = r4     // Catch:{ all -> 0x01f1 }
            r2 = 2
            r9[r2] = r6     // Catch:{ all -> 0x01f1 }
            r9[r8] = r5     // Catch:{ all -> 0x01f1 }
            r2 = 4
            r9[r2] = r7     // Catch:{ all -> 0x01f1 }
            ieh r2 = p000.ife.m12823a((p000.ieh[]) r9)     // Catch:{ all -> 0x01f1 }
            r13.f7688e = r2     // Catch:{ all -> 0x01f1 }
            monitor-exit(r0)     // Catch:{ all -> 0x01f1 }
            dzb r0 = r13.f7689f
            gus r0 = r0.f7694e
            java.lang.String r2 = "PHOTO_GRID_FRAGMENT_DATA_SERVICE_KEY"
            r0.mo7096a((p000.ieh) r1, (java.lang.Object) r2)
            dzb r0 = r13.f7689f
            gus r0 = r0.f7694e
            java.lang.String r2 = "PHOTO_GRID_FRAGMENT_DATA_SERVICE_KEY"
            r0.mo7096a((p000.ieh) r4, (java.lang.Object) r2)
            dzb r0 = r13.f7689f
            gus r0 = r0.f7694e
            java.lang.String r2 = "PHOTO_GRID_FRAGMENT_DATA_SERVICE_KEY"
            r0.mo7096a((p000.ieh) r5, (java.lang.Object) r2)
            dzb r0 = r13.f7689f
            gus r0 = r0.f7694e
            java.lang.String r2 = "PHOTO_GRID_FRAGMENT_DATA_SERVICE_KEY"
            r0.mo7096a((p000.ieh) r6, (java.lang.Object) r2)
            dzb r0 = r13.f7689f
            gus r0 = r0.f7694e
            java.lang.String r2 = "PHOTO_GRID_FRAGMENT_DATA_SERVICE_KEY"
            r0.mo7096a((p000.ieh) r7, (java.lang.Object) r2)
            java.lang.Object[] r0 = new java.lang.Object[r10]
            java.lang.String r2 = "PhotoGridFragmentDataService: Failed to fetch category list."
            p000.cwn.m5509a((p000.ieh) r1, (java.lang.String) r2, (java.lang.Object[]) r0)
            java.lang.Object[] r0 = new java.lang.Object[r10]
            java.lang.String r1 = "PhotoGridFragmentDataService: Failed to fetch media list."
            p000.cwn.m5509a((p000.ieh) r4, (java.lang.String) r1, (java.lang.Object[]) r0)
            ieh r0 = p000.ife.m12820a((java.lang.Object) r3)
            return r0
        L_0x01a2:
            r1 = move-exception
            if (r9 == 0) goto L_0x01ad
            r9.close()     // Catch:{ all -> 0x01a9 }
            goto L_0x01ad
        L_0x01a9:
            r2 = move-exception
            p000.ifn.m12935a((java.lang.Throwable) r1, (java.lang.Throwable) r2)     // Catch:{ all -> 0x01f1 }
        L_0x01ad:
            throw r1     // Catch:{ all -> 0x01f1 }
        L_0x01ae:
            r1 = move-exception
            if (r7 == 0) goto L_0x01b9
            r7.close()     // Catch:{ all -> 0x01b5 }
            goto L_0x01b9
        L_0x01b5:
            r2 = move-exception
            p000.ifn.m12935a((java.lang.Throwable) r1, (java.lang.Throwable) r2)     // Catch:{ all -> 0x01f1 }
        L_0x01b9:
            throw r1     // Catch:{ all -> 0x01f1 }
        L_0x01ba:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ all -> 0x01d6 }
            java.lang.String r2 = java.lang.String.valueOf(r9)     // Catch:{ all -> 0x01d6 }
            java.lang.String r3 = "Missing required properties:"
            int r4 = r2.length()     // Catch:{ all -> 0x01d6 }
            if (r4 != 0) goto L_0x01ce
            java.lang.String r2 = new java.lang.String     // Catch:{ all -> 0x01d6 }
            r2.<init>(r3)     // Catch:{ all -> 0x01d6 }
            goto L_0x01d2
        L_0x01ce:
            java.lang.String r2 = r3.concat(r2)     // Catch:{ all -> 0x01d6 }
        L_0x01d2:
            r1.<init>(r2)     // Catch:{ all -> 0x01d6 }
            throw r1     // Catch:{ all -> 0x01d6 }
        L_0x01d6:
            r1 = move-exception
            if (r5 == 0) goto L_0x01e1
            r5.close()     // Catch:{ all -> 0x01dd }
            goto L_0x01e1
        L_0x01dd:
            r2 = move-exception
            p000.ifn.m12935a((java.lang.Throwable) r1, (java.lang.Throwable) r2)     // Catch:{ all -> 0x01f1 }
        L_0x01e1:
            throw r1     // Catch:{ all -> 0x01f1 }
        L_0x01e2:
            r1 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x01e2 }
            throw r1     // Catch:{ all -> 0x01e5 }
        L_0x01e5:
            r1 = move-exception
            if (r4 == 0) goto L_0x01f0
            r4.close()     // Catch:{ all -> 0x01ec }
            goto L_0x01f0
        L_0x01ec:
            r2 = move-exception
            p000.ifn.m12935a((java.lang.Throwable) r1, (java.lang.Throwable) r2)     // Catch:{ all -> 0x01f1 }
        L_0x01f0:
            throw r1     // Catch:{ all -> 0x01f1 }
        L_0x01f1:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x01f1 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.dza.mo2733a():ieh");
    }

    /* renamed from: c */
    public final Object mo2735c() {
        return guj.m10828a("PHOTO_GRID_FRAGMENT_DATA_SERVICE_KEY", "CATEGORY_DATA_SERVICE_KEY", "GRID_ITEM_DATA_SERVICE_KEY", "MEDIA_STORE_SYNC_STATE_KEY");
    }

    /* renamed from: b */
    public final gpc mo2734b() {
        synchronized (this.f7684a) {
            if (this.f7687d == null) {
                gpc a = gpc.m10579a((Object) guc.f12067a);
                return a;
            } else if (!this.f7688e.isCancelled()) {
                dyt dyt = this.f7687d;
                dyt.f7674f = Boolean.valueOf(this.f7689f.f7695f.f6405a.get());
                String str = "";
                if (dyt.f7674f == null) {
                    str = " syncCompleted";
                }
                if (str.isEmpty()) {
                    gpc a2 = gpc.m10579a((Object) guc.m10815a(new dyh(dyt.f7669a, dyt.f7670b, dyt.f7671c, dyt.f7672d, dyt.f7673e, dyt.f7674f), this.f7689f.f7696g.mo5370a()));
                    return a2;
                }
                throw new IllegalStateException(str.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(str));
            } else {
                gpc a3 = gpc.m10579a((Object) guc.f12067a);
                return a3;
            }
        }
    }
}
