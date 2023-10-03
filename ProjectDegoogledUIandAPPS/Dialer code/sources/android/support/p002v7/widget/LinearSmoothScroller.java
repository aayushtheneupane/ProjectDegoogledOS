package android.support.p002v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.support.p002v7.widget.RecyclerView;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

/* renamed from: android.support.v7.widget.LinearSmoothScroller */
public class LinearSmoothScroller extends RecyclerView.SmoothScroller {
    private final float MILLISECONDS_PER_PX;
    protected final DecelerateInterpolator mDecelerateInterpolator = new DecelerateInterpolator();
    protected int mInterimTargetDx = 0;
    protected int mInterimTargetDy = 0;
    protected final LinearInterpolator mLinearInterpolator = new LinearInterpolator();
    protected PointF mTargetVector;

    public LinearSmoothScroller(Context context) {
        this.MILLISECONDS_PER_PX = 25.0f / ((float) context.getResources().getDisplayMetrics().densityDpi);
    }

    public int calculateDtToFit(int i, int i2, int i3, int i4, int i5) {
        if (i5 == -1) {
            return i3 - i;
        }
        if (i5 == 0) {
            int i6 = i3 - i;
            if (i6 > 0) {
                return i6;
            }
            int i7 = i4 - i2;
            if (i7 < 0) {
                return i7;
            }
            return 0;
        } else if (i5 == 1) {
            return i4 - i2;
        } else {
            throw new IllegalArgumentException("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");
        }
    }

    /* access modifiers changed from: protected */
    public int calculateTimeForScrolling(int i) {
        return (int) Math.ceil((double) (((float) Math.abs(i)) * this.MILLISECONDS_PER_PX));
    }

    /* access modifiers changed from: protected */
    public void onSeekTargetStep(int i, int i2, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
        if (getChildCount() == 0) {
            stop();
            return;
        }
        int i3 = this.mInterimTargetDx;
        int i4 = i3 - i;
        if (i3 * i4 <= 0) {
            i4 = 0;
        }
        this.mInterimTargetDx = i4;
        int i5 = this.mInterimTargetDy;
        int i6 = i5 - i2;
        if (i5 * i6 <= 0) {
            i6 = 0;
        }
        this.mInterimTargetDy = i6;
        if (this.mInterimTargetDx == 0 && this.mInterimTargetDy == 0) {
            PointF computeScrollVectorForPosition = computeScrollVectorForPosition(getTargetPosition());
            if (computeScrollVectorForPosition == null || (computeScrollVectorForPosition.x == 0.0f && computeScrollVectorForPosition.y == 0.0f)) {
                action.jumpTo(getTargetPosition());
                stop();
                return;
            }
            float f = computeScrollVectorForPosition.x;
            float f2 = computeScrollVectorForPosition.y;
            float sqrt = (float) Math.sqrt((double) ((f2 * f2) + (f * f)));
            computeScrollVectorForPosition.x /= sqrt;
            computeScrollVectorForPosition.y /= sqrt;
            this.mTargetVector = computeScrollVectorForPosition;
            this.mInterimTargetDx = (int) (computeScrollVectorForPosition.x * 10000.0f);
            this.mInterimTargetDy = (int) (computeScrollVectorForPosition.y * 10000.0f);
            action.update((int) (((float) this.mInterimTargetDx) * 1.2f), (int) (((float) this.mInterimTargetDy) * 1.2f), (int) (((float) ((int) Math.ceil((double) (((float) Math.abs(10000)) * this.MILLISECONDS_PER_PX)))) * 1.2f), this.mLinearInterpolator);
        }
    }
}
