package com.android.messaging.datamodel.p038b;

/* renamed from: com.android.messaging.datamodel.b.l */
class C0872l implements C0883w {

    /* renamed from: tC */
    private final C0883w f1119tC;
    final /* synthetic */ C0873m this$0;

    public C0872l(C0873m mVar, C0883w wVar) {
        this.this$0 = mVar;
        this.f1119tC = wVar;
        mVar.mo6100Oh();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0075, code lost:
        if (r8 != r7.this$0.getBitmap()) goto L_0x008e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x008c, code lost:
        if (r8 != r7.this$0.getBitmap()) goto L_0x008e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x008e, code lost:
        r8.recycle();
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.messaging.datamodel.p038b.C0846I mo6120a(java.util.List r8) {
        /*
            r7 = this;
            com.android.messaging.util.C1424b.m3584Gj()
            com.android.messaging.datamodel.b.m r8 = r7.this$0
            r8.acquireLock()
            r8 = 0
            com.android.messaging.datamodel.b.m r0 = r7.this$0     // Catch:{ Exception -> 0x007a }
            android.graphics.Bitmap r0 = r0.getBitmap()     // Catch:{ Exception -> 0x007a }
            boolean r1 = r0.hasAlpha()     // Catch:{ Exception -> 0x007a }
            com.android.messaging.util.C1424b.m3591ha(r1)     // Catch:{ Exception -> 0x007a }
            int r1 = r0.getWidth()     // Catch:{ Exception -> 0x007a }
            int r2 = r0.getHeight()     // Catch:{ Exception -> 0x007a }
            if (r1 <= 0) goto L_0x0056
            if (r2 <= 0) goto L_0x0056
            com.android.messaging.datamodel.b.w r3 = r7.f1119tC     // Catch:{ Exception -> 0x007a }
            boolean r3 = r3 instanceof com.android.messaging.datamodel.p038b.C0879s     // Catch:{ Exception -> 0x007a }
            if (r3 == 0) goto L_0x0056
            com.android.messaging.datamodel.b.w r3 = r7.f1119tC     // Catch:{ Exception -> 0x007a }
            com.android.messaging.datamodel.b.s r3 = (com.android.messaging.datamodel.p038b.C0879s) r3     // Catch:{ Exception -> 0x007a }
            com.android.messaging.datamodel.b.t r3 = r3.getDescriptor()     // Catch:{ Exception -> 0x007a }
            int r4 = r3.desiredWidth     // Catch:{ Exception -> 0x007a }
            float r4 = (float) r4     // Catch:{ Exception -> 0x007a }
            float r5 = (float) r1     // Catch:{ Exception -> 0x007a }
            float r4 = r4 / r5
            int r3 = r3.f1128yC     // Catch:{ Exception -> 0x007a }
            float r3 = (float) r3     // Catch:{ Exception -> 0x007a }
            float r6 = (float) r2     // Catch:{ Exception -> 0x007a }
            float r3 = r3 / r6
            float r3 = java.lang.Math.max(r4, r3)     // Catch:{ Exception -> 0x007a }
            float r5 = r5 * r3
            int r4 = (int) r5     // Catch:{ Exception -> 0x007a }
            float r6 = r6 * r3
            int r5 = (int) r6     // Catch:{ Exception -> 0x007a }
            r6 = 1065353216(0x3f800000, float:1.0)
            int r3 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r3 >= 0) goto L_0x0056
            if (r4 <= 0) goto L_0x0056
            if (r5 <= 0) goto L_0x0056
            if (r4 == r1) goto L_0x0056
            if (r5 == r2) goto L_0x0056
            r1 = 0
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createScaledBitmap(r0, r4, r5, r1)     // Catch:{ Exception -> 0x007a }
            r8 = r0
        L_0x0056:
            r1 = 50
            byte[] r0 = com.android.messaging.util.C1416U.m3567a((android.graphics.Bitmap) r0, (int) r1)     // Catch:{ Exception -> 0x007a }
            com.android.messaging.datamodel.b.o r1 = new com.android.messaging.datamodel.b.o     // Catch:{ Exception -> 0x007a }
            com.android.messaging.datamodel.b.m r2 = r7.this$0     // Catch:{ Exception -> 0x007a }
            java.lang.String r2 = r2.getKey()     // Catch:{ Exception -> 0x007a }
            com.android.messaging.datamodel.b.m r3 = r7.this$0     // Catch:{ Exception -> 0x007a }
            int r3 = r3.getOrientation()     // Catch:{ Exception -> 0x007a }
            r1.<init>(r2, r0, r3)     // Catch:{ Exception -> 0x007a }
            if (r8 == 0) goto L_0x0091
            com.android.messaging.datamodel.b.m r0 = r7.this$0
            android.graphics.Bitmap r0 = r0.getBitmap()
            if (r8 == r0) goto L_0x0091
            goto L_0x008e
        L_0x0078:
            r0 = move-exception
            goto L_0x009c
        L_0x007a:
            r0 = move-exception
            java.lang.String r1 = "MessagingAppImage"
            java.lang.String r2 = "Error compressing bitmap"
            com.android.messaging.util.C1430e.m3623e(r1, r2, r0)     // Catch:{ all -> 0x0078 }
            com.android.messaging.datamodel.b.m r1 = r7.this$0     // Catch:{ all -> 0x0078 }
            if (r8 == 0) goto L_0x0091
            com.android.messaging.datamodel.b.m r0 = r7.this$0
            android.graphics.Bitmap r0 = r0.getBitmap()
            if (r8 == r0) goto L_0x0091
        L_0x008e:
            r8.recycle()
        L_0x0091:
            com.android.messaging.datamodel.b.m r8 = r7.this$0
            r8.releaseLock()
            com.android.messaging.datamodel.b.m r7 = r7.this$0
            r7.release()
            return r1
        L_0x009c:
            if (r8 == 0) goto L_0x00a9
            com.android.messaging.datamodel.b.m r1 = r7.this$0
            android.graphics.Bitmap r1 = r1.getBitmap()
            if (r8 == r1) goto L_0x00a9
            r8.recycle()
        L_0x00a9:
            com.android.messaging.datamodel.b.m r8 = r7.this$0
            r8.releaseLock()
            com.android.messaging.datamodel.b.m r7 = r7.this$0
            r7.release()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.p038b.C0872l.mo6120a(java.util.List):com.android.messaging.datamodel.b.I");
    }

    /* renamed from: fa */
    public C0882v mo6121fa() {
        return this.f1119tC.mo6121fa();
    }

    public C0884x getDescriptor() {
        return this.f1119tC.getDescriptor();
    }

    public String getKey() {
        return this.this$0.getKey();
    }

    public int getRequestType() {
        return 1;
    }
}
