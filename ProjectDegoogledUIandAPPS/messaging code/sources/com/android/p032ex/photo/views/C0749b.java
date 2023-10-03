package com.android.p032ex.photo.views;

/* renamed from: com.android.ex.photo.views.b */
class C0749b implements Runnable {

    /* renamed from: Hw */
    private float f953Hw;

    /* renamed from: Iw */
    private float f954Iw;

    /* renamed from: Jw */
    private float f955Jw;

    /* renamed from: Kw */
    private long f956Kw;

    /* renamed from: Lw */
    private boolean f957Lw;
    private final PhotoView mHeader;
    private boolean mRunning;

    public C0749b(PhotoView photoView) {
        this.mHeader = photoView;
    }

    /* renamed from: c */
    public void mo5842c(float f) {
        if (!this.mRunning) {
            this.f953Hw = f;
            this.f955Jw = this.f953Hw / 500.0f;
            this.f954Iw = 0.0f;
            this.f956Kw = -1;
            this.f957Lw = false;
            this.mRunning = true;
            this.mHeader.post(this);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003a, code lost:
        if ((r2 + r4) < r3) goto L_0x003c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0055  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r6 = this;
            boolean r0 = r6.f957Lw
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            float r0 = r6.f954Iw
            float r1 = r6.f953Hw
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 == 0) goto L_0x005a
            long r0 = java.lang.System.currentTimeMillis()
            long r2 = r6.f956Kw
            r4 = -1
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 == 0) goto L_0x001c
            long r2 = r0 - r2
            goto L_0x001e
        L_0x001c:
            r2 = 0
        L_0x001e:
            float r4 = r6.f955Jw
            float r2 = (float) r2
            float r4 = r4 * r2
            float r2 = r6.f954Iw
            float r3 = r6.f953Hw
            int r5 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r5 >= 0) goto L_0x002f
            float r2 = r2 + r4
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 > 0) goto L_0x003c
        L_0x002f:
            float r2 = r6.f954Iw
            float r3 = r6.f953Hw
            int r5 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x0042
            float r2 = r2 + r4
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 >= 0) goto L_0x0042
        L_0x003c:
            float r2 = r6.f953Hw
            float r3 = r6.f954Iw
            float r4 = r2 - r3
        L_0x0042:
            com.android.ex.photo.views.PhotoView r2 = r6.mHeader
            r3 = 0
            com.android.p032ex.photo.views.PhotoView.m1201a((com.android.p032ex.photo.views.PhotoView) r2, (float) r4, (boolean) r3)
            float r2 = r6.f954Iw
            float r2 = r2 + r4
            r6.f954Iw = r2
            float r2 = r6.f954Iw
            float r3 = r6.f953Hw
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 != 0) goto L_0x0058
            r6.stop()
        L_0x0058:
            r6.f956Kw = r0
        L_0x005a:
            boolean r0 = r6.f957Lw
            if (r0 == 0) goto L_0x005f
            return
        L_0x005f:
            com.android.ex.photo.views.PhotoView r0 = r6.mHeader
            r0.post(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.p032ex.photo.views.C0749b.run():void");
    }

    public void stop() {
        this.mRunning = false;
        this.f957Lw = true;
    }
}
