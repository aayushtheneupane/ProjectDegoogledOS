package com.android.messaging.p041ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.C0616a;
import androidx.viewpager.widget.ViewPager;
import com.android.messaging.util.C1486ya;

/* renamed from: com.android.messaging.ui.PagingAwareViewPager */
public class PagingAwareViewPager extends ViewPager {

    /* renamed from: yj */
    private boolean f1665yj = true;

    public PagingAwareViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: A */
    public int mo7058A(int i) {
        C0616a adapter = getAdapter();
        return (adapter == null || !C1486ya.m3860pk()) ? i : (adapter.getCount() - 1) - i;
    }

    public boolean canScrollHorizontally(int i) {
        if (this.f1665yj) {
            return super.canScrollHorizontally(i);
        }
        return false;
    }

    public int getCurrentItem() {
        return mo7058A(super.getCurrentItem());
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.f1665yj) {
            return false;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f1665yj) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setCurrentItem(int i, boolean z) {
        super.setCurrentItem(mo7058A(i), z);
    }

    /* renamed from: w */
    public void mo7059w(boolean z) {
        this.f1665yj = z;
    }

    public void setCurrentItem(int i) {
        super.setCurrentItem(mo7058A(i));
    }
}
