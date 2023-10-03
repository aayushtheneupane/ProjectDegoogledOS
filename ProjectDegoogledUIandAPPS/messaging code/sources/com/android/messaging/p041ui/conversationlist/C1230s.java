package com.android.messaging.p041ui.conversationlist;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.C0564fa;
import androidx.recyclerview.widget.RecyclerView;
import com.android.messaging.R;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1486ya;

/* renamed from: com.android.messaging.ui.conversationlist.s */
public class C1230s implements C0564fa {
    private final int mMaximumFlingVelocity;
    private final int mMinimumFlingVelocity;
    private final RecyclerView mRecyclerView;
    private final int mTouchSlop;
    private VelocityTracker mVelocityTracker;

    /* renamed from: tH */
    private final long f1939tH;

    /* renamed from: uH */
    private final long f1940uH;

    /* renamed from: vH */
    private final long f1941vH;

    /* renamed from: wH */
    private float f1942wH;

    /* renamed from: xH */
    private float f1943xH;

    /* renamed from: yH */
    private boolean f1944yH;

    /* renamed from: zH */
    private ConversationListItemView f1945zH;

    public C1230s(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        Context context = this.mRecyclerView.getContext();
        Resources resources = context.getResources();
        this.f1939tH = (long) resources.getInteger(R.integer.swipe_duration_ms);
        this.f1940uH = (long) resources.getInteger(R.integer.swipe_duration_ms);
        this.f1941vH = (long) resources.getInteger(R.integer.swipe_duration_ms);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
        this.mMaximumFlingVelocity = Math.min(viewConfiguration.getScaledMaximumFlingVelocity(), resources.getInteger(R.integer.swipe_max_fling_velocity_px_per_s));
        this.mMinimumFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public void m3134c(ConversationListItemView conversationListItemView) {
        conversationListItemView.mo7565n(false);
        ViewCompat.setHasTransientState(conversationListItemView, false);
        m3132a(conversationListItemView, false);
    }

    /* renamed from: g */
    private long m3135g(float f, float f2) {
        C1424b.m3592ia(f2 != 0.0f);
        return Math.min((long) ((int) (Math.abs(f / f2) * 1000.0f)), this.f1941vH);
    }

    /* renamed from: vo */
    private float m3136vo() {
        return this.mVelocityTracker.getXVelocity();
    }

    /* renamed from: wo */
    private boolean m3137wo() {
        return this.f1945zH != null;
    }

    /* renamed from: xo */
    private boolean m3138xo() {
        return m3137wo() && this.f1945zH.getParent() == this.mRecyclerView;
    }

    /* renamed from: yo */
    private void m3139yo() {
        this.mVelocityTracker.recycle();
        this.mVelocityTracker = null;
        this.f1944yH = false;
        this.f1945zH = null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        if (r7 != 3) goto L_0x00ea;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo4665b(androidx.recyclerview.widget.RecyclerView r7, android.view.MotionEvent r8) {
        /*
            r6 = this;
            int r7 = r8.getPointerCount()
            r0 = 0
            r1 = 1
            if (r7 <= r1) goto L_0x0009
            return r0
        L_0x0009:
            int r7 = r8.getActionMasked()
            if (r7 == 0) goto L_0x0099
            if (r7 == r1) goto L_0x008f
            r2 = 2
            if (r7 == r2) goto L_0x0019
            r8 = 3
            if (r7 == r8) goto L_0x008f
            goto L_0x00ea
        L_0x0019:
            boolean r7 = r6.m3138xo()
            if (r7 == 0) goto L_0x00ea
            android.view.VelocityTracker r7 = r6.mVelocityTracker
            r7.addMovement(r8)
            int r7 = r8.getHistorySize()
            r2 = r0
        L_0x0029:
            int r3 = r7 + 1
            if (r2 >= r3) goto L_0x00ea
            if (r2 >= r7) goto L_0x0038
            float r3 = r8.getHistoricalX(r2)
            float r4 = r8.getHistoricalY(r2)
            goto L_0x0040
        L_0x0038:
            float r3 = r8.getX()
            float r4 = r8.getY()
        L_0x0040:
            float r5 = r6.f1942wH
            float r3 = r3 - r5
            float r5 = r6.f1943xH
            float r4 = r4 - r5
            float r3 = java.lang.Math.abs(r3)
            float r4 = java.lang.Math.abs(r4)
            boolean r5 = r6.f1944yH
            if (r5 != 0) goto L_0x0065
            int r5 = r6.mTouchSlop
            float r5 = (float) r5
            int r5 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r5 <= 0) goto L_0x0065
            r5 = 1067030938(0x3f99999a, float:1.2)
            float r5 = r5 * r3
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L_0x0065
            r6.m3139yo()
            return r0
        L_0x0065:
            int r4 = r6.mTouchSlop
            float r4 = (float) r4
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 <= 0) goto L_0x008c
            r6.f1944yH = r1
            float r7 = r8.getX()
            r6.f1942wH = r7
            float r7 = r8.getY()
            r6.f1943xH = r7
            com.android.messaging.ui.conversationlist.ConversationListItemView r7 = r6.f1945zH
            androidx.recyclerview.widget.RecyclerView r8 = r6.mRecyclerView
            android.view.ViewParent r8 = r8.getParent()
            r8.requestDisallowInterceptTouchEvent(r1)
            r6.m3132a((com.android.messaging.p041ui.conversationlist.ConversationListItemView) r7, (boolean) r1)
            r7.mo7565n(r1)
            return r1
        L_0x008c:
            int r2 = r2 + 1
            goto L_0x0029
        L_0x008f:
            boolean r7 = r6.m3137wo()
            if (r7 == 0) goto L_0x00ea
            r6.m3139yo()
            goto L_0x00ea
        L_0x0099:
            boolean r7 = r6.m3137wo()
            if (r7 != 0) goto L_0x00ea
            r6.f1944yH = r0
            android.view.VelocityTracker r7 = r6.mVelocityTracker
            if (r7 != 0) goto L_0x00ab
            android.view.VelocityTracker r7 = android.view.VelocityTracker.obtain()
            r6.mVelocityTracker = r7
        L_0x00ab:
            android.view.VelocityTracker r7 = r6.mVelocityTracker
            r7.clear()
            android.view.VelocityTracker r7 = r6.mVelocityTracker
            r7.addMovement(r8)
            float r7 = r8.getX()
            r6.f1942wH = r7
            float r7 = r8.getY()
            r6.f1943xH = r7
            androidx.recyclerview.widget.RecyclerView r7 = r6.mRecyclerView
            float r8 = r6.f1942wH
            float r0 = r6.f1943xH
            android.view.View r7 = r7.findChildViewUnder(r8, r0)
            r8 = r7
            com.android.messaging.ui.conversationlist.ConversationListItemView r8 = (com.android.messaging.p041ui.conversationlist.ConversationListItemView) r8
            boolean r7 = r7 instanceof com.android.messaging.p041ui.conversationlist.ConversationListItemView
            r0 = 0
            if (r7 == 0) goto L_0x00e8
            if (r8 == 0) goto L_0x00e8
            boolean r7 = r8.mo7561Y()
            if (r7 == 0) goto L_0x00e8
            r6.f1945zH = r8
            com.android.messaging.ui.conversationlist.ConversationListItemView r7 = r6.f1945zH
            boolean r7 = r7.isAnimating()
            if (r7 == 0) goto L_0x00ea
            r6.f1945zH = r0
            goto L_0x00ea
        L_0x00e8:
            r6.f1945zH = r0
        L_0x00ea:
            boolean r6 = r6.f1944yH
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.p041ui.conversationlist.C1230s.mo4665b(androidx.recyclerview.widget.RecyclerView, android.view.MotionEvent):boolean");
    }

    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006a, code lost:
        if (r12 > 0) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0071, code lost:
        if (r12 > 0) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0075, code lost:
        r12 = 1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00f7 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0126  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo4663a(androidx.recyclerview.widget.RecyclerView r11, android.view.MotionEvent r12) {
        /*
            r10 = this;
            boolean r11 = r10.f1944yH
            com.android.messaging.util.C1424b.m3592ia(r11)
            android.view.VelocityTracker r11 = r10.mVelocityTracker
            r11.addMovement(r12)
            int r11 = r12.getActionMasked()
            r0 = 1
            r1 = 2
            r2 = 0
            r3 = 0
            if (r11 == r0) goto L_0x004c
            if (r11 == r1) goto L_0x0038
            r0 = 3
            if (r11 == r0) goto L_0x001e
            r0 = 4
            if (r11 == r0) goto L_0x0038
            goto L_0x0138
        L_0x001e:
            boolean r11 = r10.m3138xo()
            if (r11 == 0) goto L_0x0033
            com.android.messaging.ui.conversationlist.ConversationListItemView r11 = r10.f1945zH
            r10.m3130a((com.android.messaging.p041ui.conversationlist.ConversationListItemView) r11, (float) r3)
            com.android.messaging.ui.conversationlist.ConversationListItemView r11 = r10.f1945zH
            r11.mo7565n(r2)
            r10.m3139yo()
            goto L_0x0138
        L_0x0033:
            r10.m3139yo()
            goto L_0x0138
        L_0x0038:
            boolean r11 = r10.m3138xo()
            if (r11 == 0) goto L_0x0138
            com.android.messaging.ui.conversationlist.ConversationListItemView r11 = r10.f1945zH
            float r12 = r12.getX()
            float r10 = r10.f1942wH
            float r12 = r12 - r10
            r11.setSwipeTranslationX(r12)
            goto L_0x0138
        L_0x004c:
            boolean r11 = r10.m3138xo()
            if (r11 == 0) goto L_0x0135
            int r11 = r10.mMaximumFlingVelocity
            float r11 = (float) r11
            android.view.VelocityTracker r12 = r10.mVelocityTracker
            r4 = 1000(0x3e8, float:1.401E-42)
            r12.computeCurrentVelocity(r4, r11)
            float r11 = r10.m3136vo()
            com.android.messaging.ui.conversationlist.ConversationListItemView r12 = r10.f1945zH
            float r12 = r12.getSwipeTranslationX()
            int r12 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r12 == 0) goto L_0x006d
            if (r12 <= 0) goto L_0x0075
            goto L_0x0073
        L_0x006d:
            int r12 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1))
            if (r12 == 0) goto L_0x0077
            if (r12 <= 0) goto L_0x0075
        L_0x0073:
            r12 = r1
            goto L_0x0078
        L_0x0075:
            r12 = r0
            goto L_0x0078
        L_0x0077:
            r12 = r2
        L_0x0078:
            float r4 = r10.m3136vo()
            android.view.VelocityTracker r5 = r10.mVelocityTracker
            float r5 = r5.getYVelocity()
            int r6 = r10.mMinimumFlingVelocity
            float r6 = (float) r6
            com.android.messaging.ui.conversationlist.ConversationListItemView r7 = r10.f1945zH
            float r7 = r7.getSwipeTranslationX()
            com.android.messaging.ui.conversationlist.ConversationListItemView r8 = r10.f1945zH
            int r8 = r8.getWidth()
            float r8 = (float) r8
            float r9 = java.lang.Math.abs(r4)
            int r6 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r6 <= 0) goto L_0x00c4
            float r6 = java.lang.Math.abs(r4)
            float r5 = java.lang.Math.abs(r5)
            int r5 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
            if (r5 <= 0) goto L_0x00c4
            int r4 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1))
            if (r4 <= 0) goto L_0x00ac
            r4 = r0
            goto L_0x00ad
        L_0x00ac:
            r4 = r2
        L_0x00ad:
            int r5 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x00b3
            r5 = r0
            goto L_0x00b4
        L_0x00b3:
            r5 = r2
        L_0x00b4:
            if (r4 != r5) goto L_0x00c4
            float r4 = java.lang.Math.abs(r7)
            r5 = 1028443341(0x3d4ccccd, float:0.05)
            float r8 = r8 * r5
            int r4 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r4 <= 0) goto L_0x00c4
            r4 = r0
            goto L_0x00c5
        L_0x00c4:
            r4 = r2
        L_0x00c5:
            float r5 = r10.m3136vo()
            com.android.messaging.ui.conversationlist.ConversationListItemView r6 = r10.f1945zH
            float r6 = r6.getSwipeTranslationX()
            com.android.messaging.ui.conversationlist.ConversationListItemView r7 = r10.f1945zH
            int r7 = r7.getWidth()
            float r7 = (float) r7
            int r5 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r5 < 0) goto L_0x00dc
            r5 = r0
            goto L_0x00dd
        L_0x00dc:
            r5 = r2
        L_0x00dd:
            int r8 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r8 <= 0) goto L_0x00e3
            r8 = r0
            goto L_0x00e4
        L_0x00e3:
            r8 = r2
        L_0x00e4:
            if (r5 != r8) goto L_0x00f4
            float r5 = java.lang.Math.abs(r6)
            r6 = 1053609165(0x3ecccccd, float:0.4)
            float r7 = r7 * r6
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 <= 0) goto L_0x00f4
            r5 = r0
            goto L_0x00f5
        L_0x00f4:
            r5 = r2
        L_0x00f5:
            if (r4 != 0) goto L_0x00fc
            if (r5 == 0) goto L_0x00fa
            goto L_0x00fc
        L_0x00fa:
            r5 = r2
            goto L_0x00fd
        L_0x00fc:
            r5 = r0
        L_0x00fd:
            if (r5 == 0) goto L_0x011c
            if (r4 == 0) goto L_0x0116
            com.android.messaging.ui.conversationlist.ConversationListItemView r4 = r10.f1945zH
            int r3 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1))
            if (r3 == 0) goto L_0x0109
            r6 = r0
            goto L_0x010a
        L_0x0109:
            r6 = r2
        L_0x010a:
            com.android.messaging.util.C1424b.m3592ia(r6)
            if (r3 <= 0) goto L_0x0111
            r3 = r1
            goto L_0x0112
        L_0x0111:
            r3 = r0
        L_0x0112:
            r10.m3131a(r4, r3, r11)
            goto L_0x0121
        L_0x0116:
            com.android.messaging.ui.conversationlist.ConversationListItemView r11 = r10.f1945zH
            r10.m3131a(r11, r12, r3)
            goto L_0x0121
        L_0x011c:
            com.android.messaging.ui.conversationlist.ConversationListItemView r3 = r10.f1945zH
            r10.m3130a((com.android.messaging.p041ui.conversationlist.ConversationListItemView) r3, (float) r11)
        L_0x0121:
            com.android.messaging.ui.conversationlist.ConversationListItemView r11 = r10.f1945zH
            if (r5 == 0) goto L_0x0126
            goto L_0x0127
        L_0x0126:
            r12 = r2
        L_0x0127:
            if (r12 == r1) goto L_0x012b
            if (r12 != r0) goto L_0x012e
        L_0x012b:
            r11.mo7571t(r12)
        L_0x012e:
            r11.mo7565n(r2)
            r10.m3139yo()
            goto L_0x0138
        L_0x0135:
            r10.m3139yo()
        L_0x0138:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.p041ui.conversationlist.C1230s.mo4663a(androidx.recyclerview.widget.RecyclerView, android.view.MotionEvent):void");
    }

    /* renamed from: a */
    private void m3130a(ConversationListItemView conversationListItemView, float f) {
        long j;
        conversationListItemView.mo7565n(true);
        ViewCompat.setHasTransientState(conversationListItemView, true);
        m3132a(conversationListItemView, true);
        float swipeTranslationX = conversationListItemView.getSwipeTranslationX();
        int i = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
        if (i != 0) {
            if ((i > 0) != (swipeTranslationX > 0.0f)) {
                j = m3135g(swipeTranslationX, f);
                Interpolator interpolator = C1486ya.f2357aL;
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(conversationListItemView, "swipeTranslationX", new float[]{0.0f});
                ofFloat.setDuration(j);
                ofFloat.setInterpolator(interpolator);
                ofFloat.addListener(new C1229r(this, conversationListItemView));
                ofFloat.start();
            }
        }
        j = this.f1939tH;
        Interpolator interpolator2 = C1486ya.f2357aL;
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(conversationListItemView, "swipeTranslationX", new float[]{0.0f});
        ofFloat2.setDuration(j);
        ofFloat2.setInterpolator(interpolator2);
        ofFloat2.addListener(new C1229r(this, conversationListItemView));
        ofFloat2.start();
    }

    /* renamed from: a */
    private void m3131a(ConversationListItemView conversationListItemView, int i, float f) {
        long j;
        C1424b.m3592ia(i != 0);
        conversationListItemView.mo7565n(true);
        ViewCompat.setHasTransientState(conversationListItemView, true);
        m3132a(conversationListItemView, true);
        float width = (float) (i == 2 ? this.mRecyclerView.getWidth() : -this.mRecyclerView.getWidth());
        if (f != 0.0f) {
            j = m3135g(width - conversationListItemView.getSwipeTranslationX(), f);
        } else {
            j = this.f1940uH;
        }
        Interpolator interpolator = C1486ya.f2357aL;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(conversationListItemView, "swipeTranslationX", new float[]{width});
        ofFloat.setDuration(j);
        ofFloat.setInterpolator(interpolator);
        ofFloat.addListener(new C1228q(this, conversationListItemView));
        ofFloat.start();
    }

    /* renamed from: a */
    private void m3132a(ConversationListItemView conversationListItemView, boolean z) {
        if (z) {
            conversationListItemView.setLayerType(2, (Paint) null);
            if (conversationListItemView.getWindowToken() != null) {
                conversationListItemView.buildLayer();
                return;
            }
            return;
        }
        conversationListItemView.setLayerType(0, (Paint) null);
    }
}
