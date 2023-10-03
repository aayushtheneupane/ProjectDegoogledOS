package com.android.p032ex.photo.views;

/* renamed from: com.android.ex.photo.views.d */
class C0751d implements Runnable {

    /* renamed from: Lw */
    private boolean f963Lw;

    /* renamed from: Pw */
    private long f964Pw = -1;

    /* renamed from: bu */
    private float f965bu;

    /* renamed from: cu */
    private float f966cu;
    private final PhotoView mHeader;
    private boolean mRunning;

    public C0751d(PhotoView photoView) {
        this.mHeader = photoView;
    }

    /* renamed from: f */
    public boolean mo5848f(float f, float f2) {
        if (this.mRunning) {
            return false;
        }
        this.f964Pw = -1;
        this.f965bu = f;
        this.f966cu = f2;
        this.f963Lw = false;
        this.mRunning = true;
        this.mHeader.postDelayed(this, 250);
        return true;
    }

    public void run() {
        float f;
        float f2;
        if (!this.f963Lw) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = this.f964Pw;
            float f3 = j != -1 ? (float) (currentTimeMillis - j) : 0.0f;
            if (this.f964Pw == -1) {
                this.f964Pw = currentTimeMillis;
            }
            if (f3 >= 100.0f) {
                f = this.f965bu;
                f2 = this.f966cu;
            } else {
                float f4 = 100.0f - f3;
                f = (this.f965bu / f4) * 10.0f;
                f2 = (this.f966cu / f4) * 10.0f;
                if (Math.abs(f) > Math.abs(this.f965bu) || Float.isNaN(f)) {
                    f = this.f965bu;
                }
                if (Math.abs(f2) > Math.abs(this.f966cu) || Float.isNaN(f2)) {
                    f2 = this.f966cu;
                }
            }
            int unused = this.mHeader.translate(f, f2);
            this.f965bu -= f;
            this.f966cu -= f2;
            if (this.f965bu == 0.0f && this.f966cu == 0.0f) {
                stop();
            }
            if (!this.f963Lw) {
                this.mHeader.post(this);
            }
        }
    }

    public void stop() {
        this.mRunning = false;
        this.f963Lw = true;
    }
}
