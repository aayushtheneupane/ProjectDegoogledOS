package p000;

import java.util.concurrent.Callable;

/* renamed from: cer */
/* compiled from: PG */
final /* synthetic */ class cer implements Callable {

    /* renamed from: a */
    private final cfb f4205a;

    /* renamed from: b */
    private final ceq f4206b;

    public cer(cfb cfb, ceq ceq) {
        this.f4205a = cfb;
        this.f4206b = ceq;
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x00dd A[Catch:{ all -> 0x00d0, all -> 0x00d5, Exception -> 0x0108 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00de A[Catch:{ all -> 0x00d0, all -> 0x00d5, Exception -> 0x0108 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00f3 A[Catch:{ all -> 0x00d0, all -> 0x00d5, Exception -> 0x0108 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0124  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x013d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object call() {
        /*
            r14 = this;
            cfb r0 = r14.f4205a
            ceq r1 = r14.f4206b
            java.lang.String r2 = r1.f4200b
            android.net.Uri r2 = android.net.Uri.parse(r2)
            int r3 = r1.f4199a
            r3 = r3 & 2
            if (r3 == 0) goto L_0x0017
            java.lang.String r1 = r1.f4201c
            j$.util.Optional r1 = p003j$.util.Optional.m16285of(r1)
            goto L_0x001b
        L_0x0017:
            j$.util.Optional r1 = p003j$.util.Optional.empty()
        L_0x001b:
            j$.util.function.Function r3 = p000.cew.f4214a
            j$.util.Optional r3 = r1.map(r3)
            r9 = 1
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r9)
            java.lang.Object r3 = r3.orElse(r4)
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            r10 = 0
            if (r3 == 0) goto L_0x0119
            dgt r1 = r0.f4223a
            j$.util.Optional r3 = p003j$.util.Optional.empty()
            j$.util.Optional r4 = p003j$.util.Optional.empty()     // Catch:{ Exception -> 0x010e }
            android.content.ContentResolver r5 = r1.f6518b     // Catch:{ Exception -> 0x0048 }
            java.lang.String r5 = r5.getType(r2)     // Catch:{ Exception -> 0x0048 }
            j$.util.Optional r4 = p003j$.util.Optional.ofNullable(r5)     // Catch:{ Exception -> 0x0048 }
        L_0x0047:
            goto L_0x0053
        L_0x0048:
            r5 = move-exception
            java.lang.String r6 = "MimeTypeHelper: safeGetMimeType failed for uri[%s]"
            java.lang.Object[] r7 = new java.lang.Object[r9]     // Catch:{ Exception -> 0x010e }
            r7[r10] = r2     // Catch:{ Exception -> 0x010e }
            p000.cwn.m5511a((java.lang.Throwable) r5, (java.lang.String) r6, (java.lang.Object[]) r7)     // Catch:{ Exception -> 0x010e }
            goto L_0x0047
        L_0x0053:
            j$.util.function.Function r3 = p000.dgq.f6514a     // Catch:{ Exception -> 0x010b }
            j$.util.Optional r3 = r4.map(r3)     // Catch:{ Exception -> 0x010b }
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r9)     // Catch:{ Exception -> 0x010b }
            java.lang.Object r3 = r3.orElse(r11)     // Catch:{ Exception -> 0x010b }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ Exception -> 0x010b }
            boolean r3 = r3.booleanValue()     // Catch:{ Exception -> 0x010b }
            if (r3 == 0) goto L_0x007f
            android.webkit.MimeTypeMap r3 = android.webkit.MimeTypeMap.getSingleton()     // Catch:{ Exception -> 0x010b }
            java.lang.String r5 = r2.toString()     // Catch:{ Exception -> 0x010b }
            java.lang.String r5 = android.webkit.MimeTypeMap.getFileExtensionFromUrl(r5)     // Catch:{ Exception -> 0x010b }
            java.lang.String r3 = r3.getMimeTypeFromExtension(r5)     // Catch:{ Exception -> 0x010b }
            j$.util.Optional r3 = p003j$.util.Optional.ofNullable(r3)     // Catch:{ Exception -> 0x010b }
            r12 = r3
            goto L_0x0080
        L_0x007f:
            r12 = r4
        L_0x0080:
            java.lang.String r3 = "*/*"
            dgr r4 = new dgr     // Catch:{ Exception -> 0x0108 }
            r4.<init>(r3)     // Catch:{ Exception -> 0x0108 }
            j$.util.Optional r3 = r12.map(r4)     // Catch:{ Exception -> 0x0108 }
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r10)     // Catch:{ Exception -> 0x0108 }
            java.lang.Object r3 = r3.orElse(r4)     // Catch:{ Exception -> 0x0108 }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ Exception -> 0x0108 }
            boolean r3 = r3.booleanValue()     // Catch:{ Exception -> 0x0108 }
            if (r3 != 0) goto L_0x009d
            goto L_0x0106
        L_0x009d:
            boolean r3 = p000.fxk.m9827a((android.net.Uri) r2)     // Catch:{ Exception -> 0x0108 }
            if (r3 == 0) goto L_0x0106
            j$.util.Optional r13 = p003j$.util.Optional.empty()     // Catch:{ Exception -> 0x0108 }
            android.content.ContentResolver r3 = r1.f6518b     // Catch:{ Exception -> 0x0108 }
            java.lang.String[] r5 = p000.dgt.f6517a     // Catch:{ Exception -> 0x0108 }
            r6 = 0
            r7 = 0
            r8 = 0
            r4 = r2
            android.database.Cursor r1 = r3.query(r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x0108 }
            if (r1 == 0) goto L_0x00da
            boolean r3 = r1.moveToFirst()     // Catch:{ all -> 0x00d0 }
            if (r3 == 0) goto L_0x00da
            android.webkit.MimeTypeMap r3 = android.webkit.MimeTypeMap.getSingleton()     // Catch:{ all -> 0x00d0 }
            java.lang.String r4 = r1.getString(r10)     // Catch:{ all -> 0x00d0 }
            java.lang.String r4 = android.webkit.MimeTypeMap.getFileExtensionFromUrl(r4)     // Catch:{ all -> 0x00d0 }
            java.lang.String r3 = r3.getMimeTypeFromExtension(r4)     // Catch:{ all -> 0x00d0 }
            j$.util.Optional r3 = p003j$.util.Optional.ofNullable(r3)     // Catch:{ all -> 0x00d0 }
            goto L_0x00db
        L_0x00d0:
            r3 = move-exception
            r1.close()     // Catch:{ all -> 0x00d5 }
            goto L_0x00d9
        L_0x00d5:
            r1 = move-exception
            p000.ifn.m12935a((java.lang.Throwable) r3, (java.lang.Throwable) r1)     // Catch:{ Exception -> 0x0108 }
        L_0x00d9:
            throw r3     // Catch:{ Exception -> 0x0108 }
        L_0x00da:
            r3 = r13
        L_0x00db:
            if (r1 != 0) goto L_0x00de
            goto L_0x00e1
        L_0x00de:
            r1.close()     // Catch:{ Exception -> 0x0108 }
        L_0x00e1:
            j$.util.function.Function r1 = p000.dgs.f6516a     // Catch:{ Exception -> 0x0108 }
            j$.util.Optional r1 = r3.map(r1)     // Catch:{ Exception -> 0x0108 }
            java.lang.Object r1 = r1.orElse(r11)     // Catch:{ Exception -> 0x0108 }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ Exception -> 0x0108 }
            boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x0108 }
            if (r1 == 0) goto L_0x0104
            boolean r1 = p000.fxk.m9833b((android.net.Uri) r2)     // Catch:{ Exception -> 0x0108 }
            if (r1 != 0) goto L_0x00fc
            java.lang.String r1 = "image/*"
            goto L_0x00fe
        L_0x00fc:
            java.lang.String r1 = "video/*"
        L_0x00fe:
            j$.util.Optional r1 = p003j$.util.Optional.m16285of(r1)     // Catch:{ Exception -> 0x0108 }
            goto L_0x0119
        L_0x0104:
            r1 = r3
            goto L_0x0119
        L_0x0106:
            r1 = r12
            goto L_0x0119
        L_0x0108:
            r1 = move-exception
            r1 = r12
            goto L_0x0110
        L_0x010b:
            r1 = move-exception
            r1 = r4
            goto L_0x0110
        L_0x010e:
            r1 = move-exception
            r1 = r3
        L_0x0110:
            java.lang.Object[] r3 = new java.lang.Object[r9]
            r3[r10] = r2
            java.lang.String r4 = "MimeTypeHelper: Failed to get MIME type for uri[%s]"
            p000.cwn.m5510a(r4, r3)
        L_0x0119:
            cxi r3 = p000.cxi.f5908x
            iir r3 = r3.mo8793g()
            boolean r4 = r3.f14319c
            if (r4 != 0) goto L_0x0124
            goto L_0x0129
        L_0x0124:
            r3.mo8751b()
            r3.f14319c = r10
        L_0x0129:
            iix r4 = r3.f14318b
            cxi r4 = (p000.cxi) r4
            int r5 = r4.f5909a
            r5 = r5 | 4
            r4.f5909a = r5
            r4.f5912d = r9
            java.lang.String r4 = r2.toString()
            boolean r5 = r3.f14319c
            if (r5 == 0) goto L_0x0142
            r3.mo8751b()
            r3.f14319c = r10
        L_0x0142:
            iix r5 = r3.f14318b
            cxi r5 = (p000.cxi) r5
            r4.getClass()
            int r6 = r5.f5909a
            r6 = r6 | r9
            r5.f5909a = r6
            r5.f5910b = r4
            cex r4 = new cex
            r4.<init>(r0, r3, r2)
            r1.ifPresent(r4)
            j$.util.Optional r0 = p000.cfb.m4221a((android.net.Uri) r2)
            r3.getClass()
            cey r1 = new cey
            r1.<init>(r3)
            r0.ifPresent(r1)
            iix r0 = r3.mo8770g()
            cxi r0 = (p000.cxi) r0
            j$.util.Optional r0 = p003j$.util.Optional.m16285of(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.cer.call():java.lang.Object");
    }
}
