package com.android.p032ex.photo.views;

/* renamed from: com.android.ex.photo.views.e */
class C0752e implements Runnable {

    /* renamed from: Lw */
    private boolean f967Lw;

    /* renamed from: Qw */
    private float f968Qw;

    /* renamed from: Rw */
    private float f969Rw;

    /* renamed from: Sw */
    private float f970Sw;

    /* renamed from: Tw */
    private float f971Tw;

    /* renamed from: Uw */
    private long f972Uw = -1;
    private final PhotoView mHeader;
    /* access modifiers changed from: private */
    public boolean mRunning;

    public C0752e(PhotoView photoView) {
        this.mHeader = photoView;
    }

    /* renamed from: f */
    public boolean mo5851f(float f, float f2) {
        if (this.mRunning) {
            return false;
        }
        this.f972Uw = -1;
        this.f968Qw = f;
        this.f969Rw = f2;
        double atan2 = (double) ((float) Math.atan2((double) this.f969Rw, (double) this.f968Qw));
        this.f970Sw = (float) (Math.cos(atan2) * 20000.0d);
        this.f971Tw = (float) (Math.sin(atan2) * 20000.0d);
        this.f967Lw = false;
        this.mRunning = true;
        this.mHeader.post(this);
        return true;
    }

    public void run() {
        if (!this.f967Lw) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = this.f972Uw;
            float f = j != -1 ? ((float) (currentTimeMillis - j)) / 1000.0f : 0.0f;
            int a = this.mHeader.translate(this.f968Qw * f, this.f969Rw * f);
            this.f972Uw = currentTimeMillis;
            float f2 = this.f970Sw * f;
            if (Math.abs(this.f968Qw) > Math.abs(f2)) {
                this.f968Qw -= f2;
            } else {
                this.f968Qw = 0.0f;
            }
            float f3 = this.f971Tw * f;
            if (Math.abs(this.f969Rw) > Math.abs(f3)) {
                this.f969Rw -= f3;
            } else {
                this.f969Rw = 0.0f;
            }
            if ((this.f968Qw == 0.0f && this.f969Rw == 0.0f) || a == 0) {
                stop();
                this.mHeader.m1208vn();
            } else {
                float f4 = 20000.0f;
                if (a == 1) {
                    if (this.f968Qw <= 0.0f) {
                        f4 = -20000.0f;
                    }
                    this.f970Sw = f4;
                    this.f971Tw = 0.0f;
                    this.f969Rw = 0.0f;
                } else if (a == 2) {
                    this.f970Sw = 0.0f;
                    if (this.f969Rw <= 0.0f) {
                        f4 = -20000.0f;
                    }
                    this.f971Tw = f4;
                    this.f968Qw = 0.0f;
                }
            }
            if (!this.f967Lw) {
                this.mHeader.post(this);
            }
        }
    }

    public void stop() {
        this.mRunning = false;
        this.f967Lw = true;
    }
}
