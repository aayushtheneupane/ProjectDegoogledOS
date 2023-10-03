package com.google.android.apps.photosgo.oneup.photo;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import p003j$.util.Optional;

/* compiled from: PG */
public final class ScalePhotoView extends bgo {

    /* renamed from: I */
    public Optional f4888I = Optional.empty();

    /* renamed from: J */
    public Optional f4889J = Optional.empty();

    static {
        bgo.f2285H = Bitmap.Config.ARGB_8888;
    }

    public ScalePhotoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x003a, code lost:
        if (r3 != false) goto L_0x003c;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo3407b(boolean r3) {
        /*
            r2 = this;
            j$.util.Optional r0 = r2.f4888I
            boolean r0 = r0.isPresent()
            r1 = 0
            if (r0 == 0) goto L_0x0013
            j$.util.Optional r0 = r2.f4889J
            boolean r0 = r0.isPresent()
            if (r0 != 0) goto L_0x0012
            goto L_0x0013
        L_0x0012:
            r1 = 1
        L_0x0013:
            java.lang.String r0 = "doubleTapZoomFactor and maxZoomFactor must be set when view is initialized"
            p000.ife.m12876b((boolean) r1, (java.lang.Object) r0)
            float r0 = super.mo2007e()
            r1 = 1065353216(0x3f800000, float:1.0)
            float r0 = java.lang.Math.max(r0, r1)
            j$.util.Optional r1 = r2.f4889J
            java.lang.Object r1 = r1.get()
            java.lang.Float r1 = (java.lang.Float) r1
            float r1 = r1.floatValue()
            float r0 = r0 * r1
            float r1 = r2.f2328d
            int r1 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1))
            if (r1 == 0) goto L_0x003a
            r2.f2328d = r0
            goto L_0x003c
        L_0x003a:
            if (r3 == 0) goto L_0x006e
        L_0x003c:
            r3 = 0
            r2.f2291B = r3
            r3 = 0
            float r0 = super.mo1989a((float) r3)
            java.lang.Float r0 = java.lang.Float.valueOf(r0)
            r2.f2338n = r0
            boolean r0 = r2.f2292C
            if (r0 == 0) goto L_0x0064
            android.graphics.PointF r3 = new android.graphics.PointF
            int r0 = super.mo2003b()
            int r0 = r0 / 2
            float r0 = (float) r0
            int r1 = super.mo2005c()
            int r1 = r1 / 2
            float r1 = (float) r1
            r3.<init>(r0, r1)
            r2.f2339o = r3
            goto L_0x006b
        L_0x0064:
            android.graphics.PointF r0 = new android.graphics.PointF
            r0.<init>(r3, r3)
            r2.f2339o = r0
        L_0x006b:
            r2.invalidate()
        L_0x006e:
            j$.util.Optional r3 = r2.f4888I
            java.lang.Object r3 = r3.get()
            java.lang.Float r3 = (java.lang.Float) r3
            float r3 = r3.floatValue()
            float r0 = super.mo2007e()
            float r3 = r3 * r0
            r2.f2332h = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.apps.photosgo.oneup.photo.ScalePhotoView.mo3407b(boolean):void");
    }

    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.f2292C) {
            mo3407b(true);
        }
    }
}
