package com.android.p032ex.photo.views;

/* renamed from: com.android.ex.photo.views.a */
class C0748a implements Runnable {
    final /* synthetic */ PhotoView this$0;

    C0748a(PhotoView photoView) {
        this.this$0 = photoView;
    }

    public void run() {
        float f;
        float f2;
        float a = this.this$0.getScale();
        if (a > this.this$0.f949wk) {
            float b = 1.0f / (1.0f - (this.this$0.f949wk / a));
            float f3 = 1.0f - b;
            float width = (float) (this.this$0.getWidth() / 2);
            float height = (float) (this.this$0.getHeight() / 2);
            float f4 = this.this$0.f928Dk.left * f3;
            float f5 = this.this$0.f928Dk.top * f3;
            float width2 = (this.this$0.f928Dk.right * f3) + (((float) this.this$0.getWidth()) * b);
            float height2 = (this.this$0.f928Dk.bottom * f3) + (((float) this.this$0.getHeight()) * b);
            if (width2 > f4) {
                f = (width2 + f4) / 2.0f;
            } else {
                f = Math.min(Math.max(width2, width), f4);
            }
            if (height2 > f5) {
                f2 = (height2 + f5) / 2.0f;
            } else {
                f2 = Math.min(Math.max(height2, height), f5);
            }
            this.this$0.f946uk.mo5845b(a, this.this$0.f949wk, f, f2);
        }
    }
}
