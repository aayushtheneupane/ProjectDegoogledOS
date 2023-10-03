package com.android.messaging.p041ui.mediapicker;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewpager.widget.C0616a;
import com.android.messaging.R;
import com.android.messaging.p041ui.PagingAwareViewPager;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1486ya;

/* renamed from: com.android.messaging.ui.mediapicker.MediaPickerPanel */
public class MediaPickerPanel extends ViewGroup {
    /* access modifiers changed from: private */

    /* renamed from: Aj */
    public boolean f2031Aj;
    /* access modifiers changed from: private */

    /* renamed from: Bj */
    public boolean f2032Bj;
    /* access modifiers changed from: private */

    /* renamed from: Cj */
    public int f2033Cj;
    /* access modifiers changed from: private */

    /* renamed from: Dj */
    public C1345pa f2034Dj;

    /* renamed from: Ej */
    private final int f2035Ej = getResources().getDimensionPixelSize(R.dimen.mediapicker_default_chooser_height);
    /* access modifiers changed from: private */

    /* renamed from: Fj */
    public C1355ua f2036Fj;

    /* renamed from: kb */
    private LinearLayout f2037kb;
    /* access modifiers changed from: private */

    /* renamed from: lb */
    public PagingAwareViewPager f2038lb;
    private final int mActionBarHeight = getResources().getDimensionPixelSize(R.dimen.action_bar_height);
    private final Handler mHandler = new Handler();

    /* renamed from: zj */
    private boolean f2039zj;

    public MediaPickerPanel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: private */
    public int getDesiredHeight() {
        View findViewById;
        if (!this.f2031Aj) {
            return this.f2032Bj ? -2 : 0;
        }
        int i = getContext().getResources().getDisplayMetrics().heightPixels;
        if (C1464na.m3757Xj() && isAttachedToWindow() && (findViewById = getRootView().findViewById(R.id.conversation_and_compose_container)) != null) {
            i -= C1486ya.m3858h(findViewById).top;
        }
        return this.f2034Dj.mo7885Da() ? i - this.mActionBarHeight : i;
    }

    /* renamed from: nn */
    private int m3269nn() {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE, RtlSpacingHelper.UNDEFINED);
        measureChild(this.f2037kb, makeMeasureSpec, makeMeasureSpec);
        return this.f2037kb.getMeasuredHeight() + this.f2035Ej;
    }

    /* renamed from: on */
    private boolean m3270on() {
        return this.f2039zj || C1486ya.m3859ok();
    }

    /* access modifiers changed from: private */
    /* renamed from: pn */
    public void m3271pn() {
        this.f2038lb.mo7059w(!this.f2031Aj);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Yb */
    public void mo7730Yb() {
        if (this.f2031Aj) {
            m3265d(getDesiredHeight(), true);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isFullScreen() {
        return this.f2031Aj;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f2037kb = (LinearLayout) findViewById(R.id.mediapicker_tabstrip);
        this.f2038lb = (PagingAwareViewPager) findViewById(R.id.mediapicker_view_pager);
        this.f2036Fj = new C1355ua(this);
        setOnTouchListener(this.f2036Fj);
        this.f2038lb.setOnTouchListener(this.f2036Fj);
        addOnLayoutChangeListener(new C1349ra(this));
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f2036Fj.onInterceptTouchEvent(motionEvent) || super.onInterceptTouchEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int measuredHeight = this.f2038lb.getMeasuredHeight() + i2;
        this.f2038lb.layout(0, i2, i5, measuredHeight);
        LinearLayout linearLayout = this.f2037kb;
        linearLayout.layout(0, measuredHeight, i5, linearLayout.getMeasuredHeight() + measuredHeight);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int size = View.MeasureSpec.getSize(i2);
        if (this.f2034Dj.mo7885Da()) {
            size -= this.mActionBarHeight;
        }
        int min = Math.min(this.f2033Cj, size);
        if (this.f2032Bj && min == 0) {
            min = 1;
        } else if (!this.f2032Bj && min == 0) {
            this.f2038lb.setVisibility(8);
            this.f2038lb.mo5330a((C0616a) null);
        }
        measureChild(this.f2037kb, i, i2);
        if (m3270on()) {
            i3 = this.f2037kb.getMeasuredHeight();
        } else {
            i3 = Math.min(this.f2037kb.getMeasuredHeight(), size - min);
        }
        int i4 = min - i3;
        if (i4 <= 1) {
            i4 = this.f2035Ej;
        }
        measureChild(this.f2038lb, i, View.MeasureSpec.makeMeasureSpec(i4, 1073741824));
        setMeasuredDimension(this.f2038lb.getMeasuredWidth(), min);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: x */
    public void mo7739x(boolean z) {
        this.f2039zj = z;
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public void m3265d(int i, boolean z) {
        int i2 = this.f2033Cj;
        if (i == -2) {
            i = m3269nn();
        }
        clearAnimation();
        if (z) {
            C1353ta taVar = new C1353ta(this, i2, i - i2);
            taVar.setDuration((long) C1486ya.f2354YK);
            taVar.setInterpolator(C1486ya.EASE_OUT_INTERPOLATOR);
            startAnimation(taVar);
        } else {
            this.f2033Cj = i;
        }
        requestLayout();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo7732c(C1345pa paVar) {
        this.f2034Dj = paVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo7733c(boolean z, boolean z2) {
        if (z != this.f2031Aj) {
            if (!m3270on() || z) {
                this.f2031Aj = z;
                m3265d(getDesiredHeight(), z2);
                this.f2034Dj.mo7910j(this.f2031Aj);
                m3271pn();
                return;
            }
            mo7731a(false, true, -1);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo7731a(boolean z, boolean z2, int i) {
        m3260a(z, z2, i, false);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m3260a(boolean z, boolean z2, int i, boolean z3) {
        if (z != this.f2032Bj || z3) {
            this.f2031Aj = false;
            this.f2032Bj = z;
            this.mHandler.post(new C1351sa(this, z2));
            if (z) {
                this.f2038lb.setVisibility(0);
                if (i >= 0 && i < this.f2034Dj.mo7889Ha().getCount()) {
                    this.f2038lb.mo5330a(this.f2034Dj.mo7889Ha());
                    this.f2038lb.setCurrentItem(i);
                }
                m3271pn();
                this.f2034Dj.mo7884Ca();
            } else {
                this.f2034Dj.mo7883Ba();
            }
            if (z && m3270on()) {
                mo7733c(true, z2);
            }
        }
    }
}
