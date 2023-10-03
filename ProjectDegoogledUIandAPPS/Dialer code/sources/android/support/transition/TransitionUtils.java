package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.os.Build;

class TransitionUtils {
    private static final boolean HAS_IS_ATTACHED_TO_WINDOW = true;
    private static final boolean HAS_OVERLAY = true;
    private static final boolean HAS_PICTURE_BITMAP = true;

    static {
        int i = Build.VERSION.SDK_INT;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0106  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static android.view.View copyViewImage(android.view.ViewGroup r17, android.view.View r18, android.view.View r19) {
        /*
            r0 = r17
            r1 = r18
            android.graphics.Matrix r2 = new android.graphics.Matrix
            r2.<init>()
            int r3 = r19.getScrollX()
            int r3 = -r3
            float r3 = (float) r3
            int r4 = r19.getScrollY()
            int r4 = -r4
            float r4 = (float) r4
            r2.setTranslate(r3, r4)
            android.support.transition.ViewUtils.transformMatrixToGlobal(r1, r2)
            android.support.transition.ViewUtils.transformMatrixToLocal(r0, r2)
            android.graphics.RectF r3 = new android.graphics.RectF
            int r4 = r18.getWidth()
            float r4 = (float) r4
            int r5 = r18.getHeight()
            float r5 = (float) r5
            r6 = 0
            r3.<init>(r6, r6, r4, r5)
            r2.mapRect(r3)
            float r4 = r3.left
            int r4 = java.lang.Math.round(r4)
            float r5 = r3.top
            int r5 = java.lang.Math.round(r5)
            float r6 = r3.right
            int r6 = java.lang.Math.round(r6)
            float r7 = r3.bottom
            int r7 = java.lang.Math.round(r7)
            android.widget.ImageView r8 = new android.widget.ImageView
            android.content.Context r9 = r18.getContext()
            r8.<init>(r9)
            android.widget.ImageView$ScaleType r9 = android.widget.ImageView.ScaleType.CENTER_CROP
            r8.setScaleType(r9)
            boolean r9 = HAS_IS_ATTACHED_TO_WINDOW
            r10 = 0
            if (r9 == 0) goto L_0x006b
            boolean r9 = r18.isAttachedToWindow()
            r9 = r9 ^ 1
            if (r0 != 0) goto L_0x0066
            r11 = r10
            goto L_0x006d
        L_0x0066:
            boolean r11 = r17.isAttachedToWindow()
            goto L_0x006d
        L_0x006b:
            r9 = r10
            r11 = r9
        L_0x006d:
            boolean r12 = HAS_OVERLAY
            r13 = 0
            if (r12 == 0) goto L_0x008f
            if (r9 == 0) goto L_0x008f
            if (r11 != 0) goto L_0x0078
            goto L_0x0104
        L_0x0078:
            android.view.ViewParent r10 = r18.getParent()
            android.view.ViewGroup r10 = (android.view.ViewGroup) r10
            int r11 = r10.indexOfChild(r1)
            android.view.ViewGroupOverlay r12 = r17.getOverlay()
            r12.add(r1)
            r16 = r11
            r11 = r10
            r10 = r16
            goto L_0x0090
        L_0x008f:
            r11 = r13
        L_0x0090:
            float r12 = r3.width()
            int r12 = java.lang.Math.round(r12)
            float r14 = r3.height()
            int r14 = java.lang.Math.round(r14)
            if (r12 <= 0) goto L_0x00f4
            if (r14 <= 0) goto L_0x00f4
            r15 = 1233125376(0x49800000, float:1048576.0)
            int r13 = r12 * r14
            float r13 = (float) r13
            float r15 = r15 / r13
            r13 = 1065353216(0x3f800000, float:1.0)
            float r13 = java.lang.Math.min(r13, r15)
            float r12 = (float) r12
            float r12 = r12 * r13
            int r12 = java.lang.Math.round(r12)
            float r14 = (float) r14
            float r14 = r14 * r13
            int r14 = java.lang.Math.round(r14)
            float r15 = r3.left
            float r15 = -r15
            float r3 = r3.top
            float r3 = -r3
            r2.postTranslate(r15, r3)
            r2.postScale(r13, r13)
            boolean r3 = HAS_PICTURE_BITMAP
            if (r3 == 0) goto L_0x00e3
            android.graphics.Picture r3 = new android.graphics.Picture
            r3.<init>()
            android.graphics.Canvas r12 = r3.beginRecording(r12, r14)
            r12.concat(r2)
            r1.draw(r12)
            r3.endRecording()
            android.graphics.Bitmap r13 = android.graphics.Bitmap.createBitmap(r3)
            goto L_0x00f4
        L_0x00e3:
            android.graphics.Bitmap$Config r3 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r13 = android.graphics.Bitmap.createBitmap(r12, r14, r3)
            android.graphics.Canvas r3 = new android.graphics.Canvas
            r3.<init>(r13)
            r3.concat(r2)
            r1.draw(r3)
        L_0x00f4:
            boolean r2 = HAS_OVERLAY
            if (r2 == 0) goto L_0x0104
            if (r9 == 0) goto L_0x0104
            android.view.ViewGroupOverlay r0 = r17.getOverlay()
            r0.remove(r1)
            r11.addView(r1, r10)
        L_0x0104:
            if (r13 == 0) goto L_0x0109
            r8.setImageBitmap(r13)
        L_0x0109:
            int r0 = r6 - r4
            r1 = 1073741824(0x40000000, float:2.0)
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r1)
            int r2 = r7 - r5
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r1)
            r8.measure(r0, r1)
            r8.layout(r4, r5, r6, r7)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.TransitionUtils.copyViewImage(android.view.ViewGroup, android.view.View, android.view.View):android.view.View");
    }

    static Animator mergeAnimators(Animator animator, Animator animator2) {
        if (animator == null) {
            return animator2;
        }
        if (animator2 == null) {
            return animator;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{animator, animator2});
        return animatorSet;
    }
}
