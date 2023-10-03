package com.android.messaging.p041ui.mediapicker;

import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.android.messaging.R;

/* renamed from: com.android.messaging.ui.mediapicker.ua */
class C1355ua implements View.OnTouchListener {

    /* renamed from: AI */
    private MotionEvent f2169AI;

    /* renamed from: BI */
    private boolean f2170BI = false;

    /* renamed from: CI */
    private boolean f2171CI = false;
    private final int mTouchSlop;
    final /* synthetic */ MediaPickerPanel this$0;

    /* renamed from: wI */
    private int f2172wI = -1;

    /* renamed from: xI */
    private boolean f2173xI = false;

    /* renamed from: yI */
    private final float f2174yI;

    /* renamed from: zI */
    private final float f2175zI;

    C1355ua(MediaPickerPanel mediaPickerPanel) {
        this.this$0 = mediaPickerPanel;
        Resources resources = mediaPickerPanel.getContext().getResources();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(mediaPickerPanel.getContext());
        this.f2174yI = (float) resources.getDimensionPixelSize(R.dimen.mediapicker_fling_threshold);
        this.f2175zI = (float) resources.getDimensionPixelSize(R.dimen.mediapicker_big_fling_threshold);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0077  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r8) {
        /*
            r7 = this;
            int r0 = r8.getActionMasked()
            r1 = 0
            if (r0 == 0) goto L_0x00b1
            r2 = 2
            if (r0 == r2) goto L_0x000b
            return r1
        L_0x000b:
            com.android.messaging.ui.mediapicker.MediaPickerPanel r0 = r7.this$0
            com.android.messaging.ui.mediapicker.pa r0 = r0.f2034Dj
            boolean r0 = r0.mo7891Ja()
            r2 = 1
            if (r0 == 0) goto L_0x0081
            long r3 = r8.getEventTime()
            long r5 = r8.getDownTime()
            long r3 = r3 - r5
            android.view.MotionEvent r0 = r7.f2169AI
            if (r0 == 0) goto L_0x0061
            r5 = 0
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 == 0) goto L_0x0061
            r5 = 500(0x1f4, double:2.47E-321)
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 <= 0) goto L_0x0032
            goto L_0x0061
        L_0x0032:
            float r0 = r8.getRawX()
            android.view.MotionEvent r5 = r7.f2169AI
            float r5 = r5.getRawX()
            float r0 = r0 - r5
            float r8 = r8.getRawY()
            android.view.MotionEvent r5 = r7.f2169AI
            float r5 = r5.getRawY()
            float r8 = r8 - r5
            float r3 = (float) r3
            r4 = 1148846080(0x447a0000, float:1000.0)
            float r3 = r3 / r4
            float r0 = java.lang.Math.abs(r0)
            float r8 = java.lang.Math.abs(r8)
            float r8 = java.lang.Math.max(r0, r8)
            float r8 = r8 / r3
            float r0 = r7.f2174yI
            int r8 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r8 <= 0) goto L_0x0061
            r8 = r2
            goto L_0x0062
        L_0x0061:
            r8 = r1
        L_0x0062:
            if (r8 == 0) goto L_0x0077
            com.android.messaging.ui.mediapicker.MediaPickerPanel r8 = r7.this$0
            com.android.messaging.ui.mediapicker.pa r8 = r8.f2034Dj
            r8.mo7893La()
            com.android.messaging.ui.mediapicker.MediaPickerPanel r7 = r7.this$0
            com.android.messaging.ui.PagingAwareViewPager r7 = r7.f2038lb
            r7.mo7059w(r2)
            return r1
        L_0x0077:
            com.android.messaging.ui.mediapicker.MediaPickerPanel r7 = r7.this$0
            com.android.messaging.ui.PagingAwareViewPager r7 = r7.f2038lb
            r7.mo7059w(r1)
            return r1
        L_0x0081:
            boolean r0 = r7.f2171CI
            if (r0 == 0) goto L_0x0086
            return r1
        L_0x0086:
            com.android.messaging.ui.mediapicker.MediaPickerPanel r0 = r7.this$0
            boolean r0 = r0.f2031Aj
            if (r0 != 0) goto L_0x0093
            boolean r0 = r7.f2173xI
            if (r0 == 0) goto L_0x0093
            return r2
        L_0x0093:
            boolean r0 = r7.f2170BI
            if (r0 == 0) goto L_0x0098
            return r2
        L_0x0098:
            com.android.messaging.ui.mediapicker.MediaPickerPanel r0 = r7.this$0
            com.android.messaging.ui.mediapicker.ua r0 = r0.f2036Fj
            com.android.messaging.ui.mediapicker.MediaPickerPanel r1 = r7.this$0
            r0.onTouch(r1, r8)
            com.android.messaging.ui.mediapicker.MediaPickerPanel r8 = r7.this$0
            boolean r8 = r8.f2031Aj
            if (r8 == 0) goto L_0x00ae
            boolean r7 = r7.f2170BI
            goto L_0x00b0
        L_0x00ae:
            boolean r7 = r7.f2173xI
        L_0x00b0:
            return r7
        L_0x00b1:
            com.android.messaging.ui.mediapicker.MediaPickerPanel r0 = r7.this$0
            com.android.messaging.ui.mediapicker.ua r0 = r0.f2036Fj
            com.android.messaging.ui.mediapicker.MediaPickerPanel r2 = r7.this$0
            r0.onTouch(r2, r8)
            com.android.messaging.ui.mediapicker.MediaPickerPanel r8 = r7.this$0
            com.android.messaging.ui.mediapicker.pa r8 = r8.f2034Dj
            boolean r8 = r8.mo7925za()
            r7.f2171CI = r8
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.p041ui.mediapicker.C1355ua.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00e0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouch(android.view.View r9, android.view.MotionEvent r10) {
        /*
            r8 = this;
            int r9 = r10.getAction()
            r0 = 1
            if (r9 == 0) goto L_0x00fd
            r1 = 1066192077(0x3f8ccccd, float:1.1)
            r2 = 0
            if (r9 == r0) goto L_0x005e
            r3 = 2
            if (r9 == r3) goto L_0x0012
            goto L_0x00f9
        L_0x0012:
            android.view.MotionEvent r9 = r8.f2169AI
            if (r9 != 0) goto L_0x0019
            boolean r8 = r8.f2173xI
            return r8
        L_0x0019:
            float r9 = r9.getRawX()
            float r3 = r10.getRawX()
            float r9 = r9 - r3
            android.view.MotionEvent r3 = r8.f2169AI
            float r3 = r3.getRawY()
            float r10 = r10.getRawY()
            float r3 = r3 - r10
            float r10 = java.lang.Math.abs(r3)
            int r4 = r8.mTouchSlop
            float r4 = (float) r4
            int r10 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r10 <= 0) goto L_0x005b
            float r10 = java.lang.Math.abs(r3)
            float r9 = java.lang.Math.abs(r9)
            float r10 = r10 / r9
            int r9 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r9 <= 0) goto L_0x005b
            com.android.messaging.ui.mediapicker.MediaPickerPanel r9 = r8.this$0
            int r10 = r8.f2172wI
            float r10 = (float) r10
            float r10 = r10 + r3
            int r10 = (int) r10
            r9.m3265d(r10, r2)
            r8.f2173xI = r0
            int r9 = r8.mTouchSlop
            int r9 = -r9
            float r9 = (float) r9
            int r9 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r9 >= 0) goto L_0x005b
            r8.f2170BI = r0
        L_0x005b:
            boolean r8 = r8.f2173xI
            return r8
        L_0x005e:
            boolean r9 = r8.f2173xI
            if (r9 == 0) goto L_0x00fc
            android.view.MotionEvent r9 = r8.f2169AI
            if (r9 != 0) goto L_0x0068
            goto L_0x00fc
        L_0x0068:
            float r9 = r10.getRawX()
            android.view.MotionEvent r3 = r8.f2169AI
            float r3 = r3.getRawX()
            float r9 = r9 - r3
            float r3 = r10.getRawY()
            android.view.MotionEvent r4 = r8.f2169AI
            float r4 = r4.getRawY()
            float r3 = r3 - r4
            long r4 = r10.getEventTime()
            android.view.MotionEvent r10 = r8.f2169AI
            long r6 = r10.getEventTime()
            long r4 = r4 - r6
            float r10 = (float) r4
            r4 = 1148846080(0x447a0000, float:1000.0)
            float r10 = r10 / r4
            float r10 = r3 / r10
            r4 = 0
            int r5 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            r6 = -1
            if (r5 == 0) goto L_0x00a2
            float r3 = java.lang.Math.abs(r3)
            float r9 = java.lang.Math.abs(r9)
            float r3 = r3 / r9
            int r9 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r9 <= 0) goto L_0x00dd
        L_0x00a2:
            float r9 = java.lang.Math.abs(r10)
            float r1 = r8.f2174yI
            int r9 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r9 <= 0) goto L_0x00dd
            int r9 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r9 >= 0) goto L_0x00bf
            com.android.messaging.ui.mediapicker.MediaPickerPanel r9 = r8.this$0
            boolean r9 = r9.f2032Bj
            if (r9 == 0) goto L_0x00bf
            com.android.messaging.ui.mediapicker.MediaPickerPanel r9 = r8.this$0
            r9.mo7733c(r0, r0)
        L_0x00bd:
            r9 = r0
            goto L_0x00de
        L_0x00bf:
            int r9 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r9 <= 0) goto L_0x00dd
            com.android.messaging.ui.mediapicker.MediaPickerPanel r9 = r8.this$0
            boolean r9 = r9.f2031Aj
            if (r9 == 0) goto L_0x00d7
            float r9 = r8.f2175zI
            int r9 = (r10 > r9 ? 1 : (r10 == r9 ? 0 : -1))
            if (r9 >= 0) goto L_0x00d7
            com.android.messaging.ui.mediapicker.MediaPickerPanel r9 = r8.this$0
            r9.mo7733c(r2, r0)
            goto L_0x00bd
        L_0x00d7:
            com.android.messaging.ui.mediapicker.MediaPickerPanel r9 = r8.this$0
            r9.mo7731a((boolean) r2, (boolean) r0, (int) r6)
            goto L_0x00bd
        L_0x00dd:
            r9 = r2
        L_0x00de:
            if (r9 != 0) goto L_0x00e9
            com.android.messaging.ui.mediapicker.MediaPickerPanel r9 = r8.this$0
            int r10 = r9.getDesiredHeight()
            r9.m3265d(r10, r0)
        L_0x00e9:
            r9 = 0
            r8.f2169AI = r9
            r8.f2172wI = r6
            r8.f2173xI = r2
            r8.f2170BI = r2
            r8.f2171CI = r2
            com.android.messaging.ui.mediapicker.MediaPickerPanel r9 = r8.this$0
            r9.m3271pn()
        L_0x00f9:
            boolean r8 = r8.f2173xI
            return r8
        L_0x00fc:
            return r2
        L_0x00fd:
            com.android.messaging.ui.mediapicker.MediaPickerPanel r9 = r8.this$0
            int r9 = r9.getHeight()
            r8.f2172wI = r9
            android.view.MotionEvent r9 = android.view.MotionEvent.obtain(r10)
            r8.f2169AI = r9
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.p041ui.mediapicker.C1355ua.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }
}
