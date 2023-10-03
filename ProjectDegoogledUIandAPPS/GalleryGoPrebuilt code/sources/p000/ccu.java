package p000;

/* renamed from: ccu */
/* compiled from: PG */
final /* synthetic */ class ccu implements icf {

    /* renamed from: a */
    private final ccy f4073a;

    public ccu(ccy ccy) {
        this.f4073a = ccy;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0169, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:?, code lost:
        p000.ifn.m12935a(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x016f, code lost:
        r0 = th;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:50:0x0150, B:61:0x0165] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0165 A[SYNTHETIC, Splitter:B:61:0x0165] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.ieh mo2538a(java.lang.Object r21) {
        /*
            r20 = this;
            r1 = r20
            ccy r0 = r1.f4073a
            r2 = r21
            iag r2 = (p000.iag) r2
            iae r2 = r2.mo8329c()
            if (r2 == 0) goto L_0x01a8
            cby r3 = r0.f4078b
            java.lang.Object r2 = r2.f13714a
            cxi r2 = (p000.cxi) r2
            int r4 = r0.f4083g
            int r0 = r0.f4082f
            r5 = 2
            java.lang.Object[] r6 = new java.lang.Object[r5]
            java.lang.Integer r7 = java.lang.Integer.valueOf(r4)
            r8 = 0
            r6[r8] = r7
            java.lang.Integer r7 = java.lang.Integer.valueOf(r0)
            r9 = 1
            r6[r9] = r7
            java.lang.String r6 = r2.f5910b
            ebh r6 = p000.ebh.m7086a((java.lang.String) r6)
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.MILLISECONDS
            long r10 = r2.f5915g
            long r10 = r7.toMicros(r10)
            android.media.MediaMetadataRetriever r2 = new android.media.MediaMetadataRetriever
            r2.<init>()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>(r4)
            r2.getClass()     // Catch:{ IOException -> 0x019e, InterruptedException -> 0x018d, Exception -> 0x017f }
            dgf r15 = new dgf     // Catch:{ IOException -> 0x019e, InterruptedException -> 0x018d, Exception -> 0x017f }
            r15.<init>(r2)     // Catch:{ IOException -> 0x019e, InterruptedException -> 0x018d, Exception -> 0x017f }
            dgg r3 = (p000.dgg) r3     // Catch:{ all -> 0x0171 }
            android.content.Context r3 = r3.f6499a     // Catch:{ all -> 0x0171 }
            android.os.ParcelFileDescriptor r3 = r6.mo4660a((android.content.Context) r3)     // Catch:{ all -> 0x0171 }
            java.io.FileDescriptor r6 = r3.getFileDescriptor()     // Catch:{ all -> 0x015f }
            r2.setDataSource(r6)     // Catch:{ all -> 0x015f }
            r6 = 24
            int r6 = p000.dgg.m6084a(r2, r6)     // Catch:{ all -> 0x015f }
            r12 = 18
            int r12 = p000.dgg.m6084a(r2, r12)     // Catch:{ all -> 0x015f }
            r13 = 19
            int r13 = p000.dgg.m6084a(r2, r13)     // Catch:{ all -> 0x015f }
            r14 = 90
            if (r6 == r14) goto L_0x0076
            r14 = 270(0x10e, float:3.78E-43)
            if (r6 != r14) goto L_0x0073
            goto L_0x0076
        L_0x0073:
            r14 = 0
            goto L_0x0077
        L_0x0076:
            r14 = 1
        L_0x0077:
            r21 = r15
            r15 = 3
            java.lang.Object[] r5 = new java.lang.Object[r15]     // Catch:{ all -> 0x015b }
            java.lang.Integer r16 = java.lang.Integer.valueOf(r12)     // Catch:{ all -> 0x015b }
            r5[r8] = r16     // Catch:{ all -> 0x015b }
            java.lang.Integer r16 = java.lang.Integer.valueOf(r13)     // Catch:{ all -> 0x015b }
            r5[r9] = r16     // Catch:{ all -> 0x015b }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x015b }
            r16 = 2
            r5[r16] = r6     // Catch:{ all -> 0x015b }
            if (r14 != 0) goto L_0x0094
            r5 = r13
            goto L_0x0095
        L_0x0094:
            r5 = r12
        L_0x0095:
            if (r14 != 0) goto L_0x0098
            goto L_0x0099
        L_0x0098:
            r12 = r13
        L_0x0099:
            if (r5 > r12) goto L_0x00a1
            int r12 = r12 * r0
            int r12 = r12 / r5
            r5 = r0
            r6 = r12
            goto L_0x00a6
        L_0x00a1:
            int r5 = r5 * r0
            int r5 = r5 / r12
            r6 = r0
        L_0x00a6:
            if (r4 <= r9) goto L_0x00ac
            int r12 = r4 + -1
            long r12 = (long) r12     // Catch:{ all -> 0x015b }
            long r10 = r10 / r12
        L_0x00ac:
            r13 = 0
        L_0x00ad:
            if (r13 >= r4) goto L_0x014c
            boolean r12 = java.lang.Thread.interrupted()     // Catch:{ all -> 0x015b }
            if (r12 != 0) goto L_0x0142
            long r8 = (long) r13     // Catch:{ all -> 0x015b }
            long r8 = r8 * r10
            int r12 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x015b }
            r14 = 27
            if (r12 < r14) goto L_0x00d2
            r16 = 0
            r12 = r2
            r18 = r13
            r13 = r8
            r19 = r21
            r1 = 3
            r15 = r16
            r16 = r6
            r17 = r5
            android.graphics.Bitmap r12 = r12.getScaledFrameAtTime(r13, r15, r16, r17)     // Catch:{ all -> 0x014a }
            goto L_0x00dd
        L_0x00d2:
            r19 = r21
            r18 = r13
            r1 = 3
            r12 = 0
            android.graphics.Bitmap r13 = r2.getFrameAtTime(r8, r12)     // Catch:{ all -> 0x014a }
            r12 = r13
        L_0x00dd:
            r13 = 2
            android.graphics.Bitmap r12 = android.media.ThumbnailUtils.extractThumbnail(r12, r0, r0, r13)     // Catch:{ all -> 0x014a }
            java.lang.String r14 = "failed to get bitmap %s at time %s"
            if (r12 == 0) goto L_0x0127
            r7.add(r12)     // Catch:{ all -> 0x014a }
            java.lang.Object[] r14 = new java.lang.Object[r13]     // Catch:{ all -> 0x014a }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r18)     // Catch:{ all -> 0x014a }
            r15 = 0
            r14[r15] = r13     // Catch:{ all -> 0x014a }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x014a }
            r9 = 1
            r14[r9] = r8     // Catch:{ all -> 0x014a }
            java.lang.Object[] r8 = new java.lang.Object[r1]     // Catch:{ all -> 0x014a }
            int r9 = r12.getWidth()     // Catch:{ all -> 0x014a }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x014a }
            r13 = 0
            r8[r13] = r9     // Catch:{ all -> 0x014a }
            int r9 = r12.getHeight()     // Catch:{ all -> 0x014a }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x014a }
            r13 = 1
            r8[r13] = r9     // Catch:{ all -> 0x014a }
            int r9 = r12.getAllocationByteCount()     // Catch:{ all -> 0x014a }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x014a }
            r12 = 2
            r8[r12] = r9     // Catch:{ all -> 0x014a }
            int r13 = r18 + 1
            r1 = r20
            r21 = r19
            r8 = 0
            r9 = 1
            r15 = 3
            goto L_0x00ad
        L_0x0127:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ all -> 0x014a }
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x014a }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r18)     // Catch:{ all -> 0x014a }
            r4 = 0
            r1[r4] = r2     // Catch:{ all -> 0x014a }
            java.lang.Long r2 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x014a }
            r4 = 1
            r1[r4] = r2     // Catch:{ all -> 0x014a }
            java.lang.String r1 = p000.ife.m12834a((java.lang.String) r14, (java.lang.Object[]) r1)     // Catch:{ all -> 0x014a }
            r0.<init>(r1)     // Catch:{ all -> 0x014a }
            throw r0     // Catch:{ all -> 0x014a }
        L_0x0142:
            r19 = r21
            java.lang.InterruptedException r0 = new java.lang.InterruptedException     // Catch:{ all -> 0x014a }
            r0.<init>()     // Catch:{ all -> 0x014a }
            throw r0     // Catch:{ all -> 0x014a }
        L_0x014a:
            r0 = move-exception
            goto L_0x0162
        L_0x014c:
            r19 = r21
            if (r3 == 0) goto L_0x0153
            r3.close()     // Catch:{ all -> 0x016f }
        L_0x0153:
            r19.close()     // Catch:{ IOException -> 0x019e, InterruptedException -> 0x018d, Exception -> 0x017f }
            ieh r0 = p000.ife.m12820a((java.lang.Object) r7)
            goto L_0x01b1
        L_0x015b:
            r0 = move-exception
            r19 = r21
            goto L_0x0162
        L_0x015f:
            r0 = move-exception
            r19 = r15
        L_0x0162:
            r1 = r0
            if (r3 == 0) goto L_0x016e
            r3.close()     // Catch:{ all -> 0x0169 }
            goto L_0x016e
        L_0x0169:
            r0 = move-exception
            r2 = r0
            p000.ifn.m12935a((java.lang.Throwable) r1, (java.lang.Throwable) r2)     // Catch:{ all -> 0x016f }
        L_0x016e:
            throw r1     // Catch:{ all -> 0x016f }
        L_0x016f:
            r0 = move-exception
            goto L_0x0174
        L_0x0171:
            r0 = move-exception
            r19 = r15
        L_0x0174:
            r1 = r0
            r19.close()     // Catch:{ all -> 0x0179 }
            goto L_0x017e
        L_0x0179:
            r0 = move-exception
            r2 = r0
            p000.ifn.m12935a((java.lang.Throwable) r1, (java.lang.Throwable) r2)     // Catch:{ IOException -> 0x019e, InterruptedException -> 0x018d, Exception -> 0x017f }
        L_0x017e:
            throw r1     // Catch:{ IOException -> 0x019e, InterruptedException -> 0x018d, Exception -> 0x017f }
        L_0x017f:
            r0 = move-exception
            p000.dgg.m6085a(r7)
            boolean r1 = r0 instanceof p000.cbz
            if (r1 != 0) goto L_0x018c
            cbz r0 = p000.cbz.m4020a(r0)
            throw r0
        L_0x018c:
            throw r0
        L_0x018d:
            r0 = move-exception
            java.lang.Thread r1 = java.lang.Thread.currentThread()
            r1.interrupt()
            p000.dgg.m6085a(r7)
            cbz r1 = new cbz
            r1.<init>(r0)
            throw r1
        L_0x019e:
            r0 = move-exception
            p000.dgg.m6085a(r7)
            cbz r1 = new cbz
            r1.<init>(r0)
            throw r1
        L_0x01a8:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            ieh r0 = p000.ife.m12820a((java.lang.Object) r0)
        L_0x01b1:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ccu.mo2538a(java.lang.Object):ieh");
    }
}
