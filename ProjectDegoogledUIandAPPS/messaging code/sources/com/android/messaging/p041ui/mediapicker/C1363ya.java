package com.android.messaging.p041ui.mediapicker;

import android.content.Context;
import android.hardware.Camera;
import android.os.Parcelable;
import android.view.SurfaceView;
import android.view.View;

/* renamed from: com.android.messaging.ui.mediapicker.ya */
public class C1363ya extends SurfaceView implements C1277E {
    /* access modifiers changed from: private */

    /* renamed from: We */
    public final C1278F f2181We = new C1278F(this);

    public C1363ya(Context context) {
        super(context);
        getHolder().addCallback(new C1361xa(this));
    }

    /* renamed from: ca */
    public void mo7687ca() {
        this.f2181We.mo7693ca();
    }

    public View getView() {
        return this;
    }

    public boolean isValid() {
        return getHolder() != null;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f2181We.onAttachedToWindow();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f2181We.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int y = this.f2181We.mo7701y(i, i2);
        super.onMeasure(y, this.f2181We.mo7700x(y, i2));
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(parcelable);
        this.f2181We.mo7699vj();
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        this.f2181We.mo7690Ma(i);
    }

    /* renamed from: a */
    public void mo7686a(Camera camera) {
        camera.setPreviewDisplay(getHolder());
    }
}
