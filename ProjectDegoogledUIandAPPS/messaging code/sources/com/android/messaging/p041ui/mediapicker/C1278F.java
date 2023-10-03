package com.android.messaging.p041ui.mediapicker;

import android.content.Context;
import android.hardware.Camera;
import android.view.View;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.ui.mediapicker.F */
public class C1278F {

    /* renamed from: lI */
    private int f2011lI = -1;
    private final C1277E mHost;

    /* renamed from: mI */
    private int f2012mI = -1;

    public C1278F(C1277E e) {
        C1424b.m3594t(e);
        C1424b.m3594t(e.getView());
        this.mHost = e;
    }

    /* renamed from: Ma */
    public void mo7690Ma(int i) {
        if (!C1352t.m3450nj()) {
            return;
        }
        if (i == 0) {
            C1352t.get().mo7950sj();
        } else {
            C1352t.get().mo7941kj();
        }
    }

    /* renamed from: a */
    public void mo7691a(Camera.Size size, int i) {
        if (i == 0 || i == 180) {
            this.f2011lI = size.width;
            this.f2012mI = size.height;
        } else {
            this.f2011lI = size.height;
            this.f2012mI = size.width;
        }
        this.mHost.getView().requestLayout();
    }

    /* renamed from: ca */
    public void mo7693ca() {
        C1352t.get().mo7950sj();
    }

    public Context getContext() {
        return this.mHost.getView().getContext();
    }

    public boolean isValid() {
        return this.mHost.isValid();
    }

    public void onAttachedToWindow() {
        if (C1352t.m3450nj()) {
            C1352t.get().mo7950sj();
        }
    }

    public void onDetachedFromWindow() {
        C1352t.get().mo7941kj();
    }

    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.mHost.getView().setOnTouchListener(onTouchListener);
    }

    /* renamed from: vj */
    public void mo7699vj() {
        if (C1352t.m3450nj()) {
            C1352t.get().mo7950sj();
        }
    }

    /* renamed from: x */
    public int mo7700x(int i, int i2) {
        if (this.f2012mI < 0) {
            return i2;
        }
        int i3 = getContext().getResources().getConfiguration().orientation;
        int size = View.MeasureSpec.getSize(i);
        float f = ((float) this.f2011lI) / ((float) this.f2012mI);
        return View.MeasureSpec.makeMeasureSpec((int) (i3 == 2 ? ((float) size) * f : ((float) size) / f), 1073741824);
    }

    /* renamed from: y */
    public int mo7701y(int i, int i2) {
        return this.f2012mI >= 0 ? View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i), 1073741824) : i;
    }

    /* renamed from: a */
    public void mo7692a(Camera camera) {
        this.mHost.mo7686a(camera);
    }
}
