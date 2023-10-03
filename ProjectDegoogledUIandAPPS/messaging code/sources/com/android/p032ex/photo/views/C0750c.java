package com.android.p032ex.photo.views;

/* renamed from: com.android.ex.photo.views.c */
class C0750c implements Runnable {

    /* renamed from: Jw */
    private float f958Jw;

    /* renamed from: Lw */
    private boolean f959Lw;

    /* renamed from: Mw */
    private boolean f960Mw;

    /* renamed from: Nw */
    private float f961Nw;

    /* renamed from: Ow */
    private float f962Ow;
    private float mCenterX;
    private float mCenterY;
    private final PhotoView mHeader;
    /* access modifiers changed from: private */
    public boolean mRunning;
    private long mStartTime;

    public C0750c(PhotoView photoView) {
        this.mHeader = photoView;
    }

    /* renamed from: b */
    public boolean mo5845b(float f, float f2, float f3, float f4) {
        if (this.mRunning) {
            return false;
        }
        this.mCenterX = f3;
        this.mCenterY = f4;
        this.f961Nw = f2;
        this.mStartTime = System.currentTimeMillis();
        this.f962Ow = f;
        this.f960Mw = this.f961Nw > this.f962Ow;
        this.f958Jw = (this.f961Nw - this.f962Ow) / 200.0f;
        this.mRunning = true;
        this.f959Lw = false;
        this.mHeader.post(this);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002b, code lost:
        if (r4.f960Mw == (r3 > r0)) goto L_0x002d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r4 = this;
            boolean r0 = r4.f959Lw
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            long r0 = java.lang.System.currentTimeMillis()
            long r2 = r4.mStartTime
            long r0 = r0 - r2
            float r2 = r4.f962Ow
            float r3 = r4.f958Jw
            float r0 = (float) r0
            float r3 = r3 * r0
            float r3 = r3 + r2
            com.android.ex.photo.views.PhotoView r0 = r4.mHeader
            float r1 = r4.mCenterX
            float r2 = r4.mCenterY
            r0.scale(r3, r1, r2)
            float r0 = r4.f961Nw
            int r1 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r1 == 0) goto L_0x002d
            boolean r1 = r4.f960Mw
            int r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x002a
            r0 = 1
            goto L_0x002b
        L_0x002a:
            r0 = 0
        L_0x002b:
            if (r1 != r0) goto L_0x003b
        L_0x002d:
            com.android.ex.photo.views.PhotoView r0 = r4.mHeader
            float r1 = r4.f961Nw
            float r2 = r4.mCenterX
            float r3 = r4.mCenterY
            r0.scale(r1, r2, r3)
            r4.stop()
        L_0x003b:
            boolean r0 = r4.f959Lw
            if (r0 != 0) goto L_0x0044
            com.android.ex.photo.views.PhotoView r0 = r4.mHeader
            r0.post(r4)
        L_0x0044:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.p032ex.photo.views.C0750c.run():void");
    }

    public void stop() {
        this.mRunning = false;
        this.f959Lw = true;
    }
}
