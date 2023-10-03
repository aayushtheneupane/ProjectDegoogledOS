package com.android.p032ex.chips;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Looper;

/* renamed from: com.android.ex.chips.aa */
class C0665aa implements C0706x {

    /* renamed from: mv */
    final /* synthetic */ C0699ra f779mv;

    /* renamed from: nv */
    final /* synthetic */ C0669ca f780nv;
    final /* synthetic */ C0697qa this$0;

    C0665aa(C0697qa qaVar, C0699ra raVar, C0669ca caVar) {
        this.this$0 = qaVar;
        this.f779mv = raVar;
        this.f780nv = caVar;
    }

    /* renamed from: b */
    private void m1067b(Bitmap bitmap) {
        this.this$0.drawIcon(this.f780nv, bitmap);
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.this$0.invalidate();
        } else {
            this.this$0.post(new C0657Z(this));
        }
    }

    public void onPhotoBytesAsyncLoadFailed() {
        m1067b(this.this$0.mDefaultContactPhoto);
    }

    public void onPhotoBytesAsynchronouslyPopulated() {
        byte[] xd = this.f779mv.mo5665xd();
        m1067b(BitmapFactory.decodeByteArray(xd, 0, xd.length));
    }

    public void onPhotoBytesPopulated() {
        byte[] xd = this.f779mv.mo5665xd();
        m1067b(BitmapFactory.decodeByteArray(xd, 0, xd.length));
    }
}
