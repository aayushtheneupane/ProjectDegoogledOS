package com.android.p032ex.photo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.C0627l;
import androidx.viewpager.widget.ViewPager;

/* renamed from: com.android.ex.photo.PhotoViewPager */
public class PhotoViewPager extends ViewPager {

    /* renamed from: _i */
    private float f831_i;
    private int mActivePointerId;
    private C0734o mListener;

    /* renamed from: wj */
    private float f832wj;

    /* renamed from: xj */
    private float f833xj;

    /* renamed from: com.android.ex.photo.PhotoViewPager$InterceptType */
    public enum InterceptType {
        NONE,
        LEFT,
        RIGHT,
        BOTH
    }

    public PhotoViewPager(Context context) {
        super(context);
        mo5332a(true, (C0627l) new C0735p(this));
    }

    /* renamed from: a */
    public void mo5708a(C0734o oVar) {
        this.mListener = oVar;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        InterceptType interceptType;
        int i;
        C0734o oVar = this.mListener;
        if (oVar != null) {
            interceptType = oVar.mo5787e(this.f832wj, this.f833xj);
        } else {
            interceptType = InterceptType.NONE;
        }
        int i2 = 1;
        boolean z = interceptType == InterceptType.BOTH || interceptType == InterceptType.LEFT;
        boolean z2 = interceptType == InterceptType.BOTH || interceptType == InterceptType.RIGHT;
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            this.mActivePointerId = -1;
        }
        if (action == 0) {
            this.f831_i = motionEvent.getX();
            this.f832wj = motionEvent.getRawX();
            this.f833xj = motionEvent.getRawY();
            this.mActivePointerId = motionEvent.getPointerId(0);
        } else if (action != 2) {
            if (action == 6) {
                int actionIndex = motionEvent.getActionIndex();
                if (motionEvent.getPointerId(actionIndex) == this.mActivePointerId) {
                    if (actionIndex != 0) {
                        i2 = 0;
                    }
                    this.f831_i = motionEvent.getX(i2);
                    this.mActivePointerId = motionEvent.getPointerId(i2);
                }
            }
        } else if ((z || z2) && (i = this.mActivePointerId) != -1) {
            float x = motionEvent.getX(motionEvent.findPointerIndex(i));
            if (z && z2) {
                this.f831_i = x;
                return false;
            } else if (z && x > this.f831_i) {
                this.f831_i = x;
                return false;
            } else if (z2 && x < this.f831_i) {
                this.f831_i = x;
                return false;
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public PhotoViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo5332a(true, (C0627l) new C0735p(this));
    }
}
