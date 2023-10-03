package p000;

import android.os.Build;

/* renamed from: aft */
/* compiled from: PG */
final class aft {

    /* renamed from: a */
    private static final boolean f354a = (Build.VERSION.SDK_INT >= 28);

    static {
        int i = Build.VERSION.SDK_INT;
        int i2 = Build.VERSION.SDK_INT;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x00fd  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static android.view.View m408a(android.view.ViewGroup r16, android.view.View r17, android.view.View r18) {
        /*
            r0 = r17
            android.graphics.Matrix r1 = new android.graphics.Matrix
            r1.<init>()
            int r2 = r18.getScrollX()
            int r2 = -r2
            float r2 = (float) r2
            int r3 = r18.getScrollY()
            int r3 = -r3
            float r3 = (float) r3
            r1.setTranslate(r2, r3)
            gbz r2 = p000.agb.f367b
            r2.mo347a((android.view.View) r0, (android.graphics.Matrix) r1)
            gbz r2 = p000.agb.f367b
            r3 = r16
            r2.mo348b(r3, r1)
            android.graphics.RectF r2 = new android.graphics.RectF
            int r4 = r17.getWidth()
            float r4 = (float) r4
            int r5 = r17.getHeight()
            float r5 = (float) r5
            r6 = 0
            r2.<init>(r6, r6, r4, r5)
            r1.mapRect(r2)
            float r4 = r2.left
            int r4 = java.lang.Math.round(r4)
            float r5 = r2.top
            int r5 = java.lang.Math.round(r5)
            float r6 = r2.right
            int r6 = java.lang.Math.round(r6)
            float r7 = r2.bottom
            int r7 = java.lang.Math.round(r7)
            android.widget.ImageView r8 = new android.widget.ImageView
            android.content.Context r9 = r17.getContext()
            r8.<init>(r9)
            android.widget.ImageView$ScaleType r9 = android.widget.ImageView.ScaleType.CENTER_CROP
            r8.setScaleType(r9)
            boolean r9 = r17.isAttachedToWindow()
            r9 = r9 ^ 1
            boolean r10 = r16.isAttachedToWindow()
            r11 = 0
            if (r9 != 0) goto L_0x006c
            r10 = 0
            r10 = r11
            r12 = 0
            goto L_0x007f
        L_0x006c:
            if (r10 == 0) goto L_0x00f8
            android.view.ViewParent r10 = r17.getParent()
            android.view.ViewGroup r10 = (android.view.ViewGroup) r10
            int r12 = r10.indexOfChild(r0)
            android.view.ViewGroupOverlay r13 = r16.getOverlay()
            r13.add(r0)
        L_0x007f:
            float r13 = r2.width()
            int r13 = java.lang.Math.round(r13)
            float r14 = r2.height()
            int r14 = java.lang.Math.round(r14)
            if (r13 > 0) goto L_0x0092
        L_0x0091:
            goto L_0x00eb
        L_0x0092:
            if (r14 <= 0) goto L_0x00ea
            r15 = 1233125376(0x49800000, float:1048576.0)
            int r11 = r13 * r14
            float r11 = (float) r11
            float r15 = r15 / r11
            r11 = 1065353216(0x3f800000, float:1.0)
            float r11 = java.lang.Math.min(r11, r15)
            float r13 = (float) r13
            float r13 = r13 * r11
            int r13 = java.lang.Math.round(r13)
            float r14 = (float) r14
            float r14 = r14 * r11
            int r14 = java.lang.Math.round(r14)
            float r15 = r2.left
            float r15 = -r15
            float r2 = r2.top
            float r2 = -r2
            r1.postTranslate(r15, r2)
            r1.postScale(r11, r11)
            boolean r2 = f354a
            if (r2 == 0) goto L_0x00d6
            android.graphics.Picture r2 = new android.graphics.Picture
            r2.<init>()
            android.graphics.Canvas r11 = r2.beginRecording(r13, r14)
            r11.concat(r1)
            r0.draw(r11)
            r2.endRecording()
            android.graphics.Bitmap r1 = android.graphics.Bitmap.createBitmap(r2)
            r11 = r1
            goto L_0x00eb
        L_0x00d6:
            android.graphics.Bitmap$Config r2 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r2 = android.graphics.Bitmap.createBitmap(r13, r14, r2)
            android.graphics.Canvas r11 = new android.graphics.Canvas
            r11.<init>(r2)
            r11.concat(r1)
            r0.draw(r11)
            r11 = r2
            goto L_0x00eb
        L_0x00ea:
            goto L_0x0091
        L_0x00eb:
            if (r9 == 0) goto L_0x00f9
            android.view.ViewGroupOverlay r1 = r16.getOverlay()
            r1.remove(r0)
            r10.addView(r0, r12)
            goto L_0x00fa
        L_0x00f8:
        L_0x00f9:
        L_0x00fa:
            if (r11 != 0) goto L_0x00fd
            goto L_0x0100
        L_0x00fd:
            r8.setImageBitmap(r11)
        L_0x0100:
            int r0 = r6 - r4
            r1 = 1073741824(0x40000000, float:2.0)
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r1)
            int r2 = r7 - r5
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r1)
            r8.measure(r0, r1)
            r8.layout(r4, r5, r6, r7)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.aft.m408a(android.view.ViewGroup, android.view.View, android.view.View):android.view.View");
    }
}
