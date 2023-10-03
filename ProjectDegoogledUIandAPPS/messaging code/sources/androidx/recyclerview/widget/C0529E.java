package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.recyclerview.widget.E */
public class C0529E {

    /* renamed from: gr */
    private int f514gr = -1;

    /* renamed from: hr */
    private boolean f515hr;

    /* renamed from: ir */
    private final C0578ma f516ir = new C0578ma(0, 0);

    /* renamed from: jr */
    private final DisplayMetrics f517jr;

    /* renamed from: kr */
    private boolean f518kr = false;

    /* renamed from: lr */
    private float f519lr;
    protected final DecelerateInterpolator mDecelerateInterpolator = new DecelerateInterpolator();
    protected int mInterimTargetDx = 0;
    protected int mInterimTargetDy = 0;
    private C0558ca mLayoutManager;
    protected final LinearInterpolator mLinearInterpolator = new LinearInterpolator();
    private RecyclerView mRecyclerView;
    private boolean mRunning;
    private boolean mStarted;
    protected PointF mTargetVector;
    private View mTargetView;

    public C0529E(Context context) {
        this.f517jr = context.getResources().getDisplayMetrics();
    }

    /* renamed from: S */
    private int m570S(int i, int i2) {
        int i3 = i - i2;
        if (i * i3 <= 0) {
            return 0;
        }
        return i3;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4647a(RecyclerView recyclerView, C0558ca caVar) {
        recyclerView.mViewFlinger.stop();
        if (this.mStarted) {
            StringBuilder Pa = C0632a.m1011Pa("An instance of ");
            Pa.append(C0529E.class.getSimpleName());
            Pa.append(" was started more than once. Each instance of");
            Pa.append(C0529E.class.getSimpleName());
            Pa.append(" is intended to only be used once. You should create a new instance for each use.");
            Log.w("RecyclerView", Pa.toString());
        }
        this.mRecyclerView = recyclerView;
        this.mLayoutManager = caVar;
        int i = this.f514gr;
        if (i != -1) {
            RecyclerView recyclerView2 = this.mRecyclerView;
            recyclerView2.mState.f658gr = i;
            this.mRunning = true;
            this.f515hr = true;
            this.mTargetView = recyclerView2.mLayout.findViewByPosition(i);
            this.mRecyclerView.mViewFlinger.postOnAnimation();
            this.mStarted = true;
            return;
        }
        throw new IllegalArgumentException("Invalid target position");
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
        float abs = (float) Math.abs(i);
        if (!this.f518kr) {
            this.f519lr = 25.0f / ((float) this.f517jr.densityDpi);
            this.f518kr = true;
        }
        return (int) Math.ceil((double) (abs * this.f519lr));
    }

    public PointF computeScrollVectorForPosition(int i) {
        C0558ca caVar = this.mLayoutManager;
        if (caVar instanceof C0580na) {
            return ((C0580na) caVar).computeScrollVectorForPosition(i);
        }
        StringBuilder Pa = C0632a.m1011Pa("You should override computeScrollVectorForPosition when the LayoutManager does not implement ");
        Pa.append(C0580na.class.getCanonicalName());
        Log.w("RecyclerView", Pa.toString());
        return null;
    }

    public int getChildPosition(View view) {
        return this.mRecyclerView.getChildLayoutPosition(view);
    }

    public int getTargetPosition() {
        return this.f514gr;
    }

    public boolean isPendingInitialRun() {
        return this.f515hr;
    }

    public boolean isRunning() {
        return this.mRunning;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: j */
    public void mo4655j(int i, int i2) {
        int i3;
        int i4;
        int i5;
        PointF computeScrollVectorForPosition;
        RecyclerView recyclerView = this.mRecyclerView;
        if (this.f514gr == -1 || recyclerView == null) {
            stop();
        }
        if (!(!this.f515hr || this.mTargetView != null || this.mLayoutManager == null || (computeScrollVectorForPosition = computeScrollVectorForPosition(this.f514gr)) == null || (computeScrollVectorForPosition.x == 0.0f && computeScrollVectorForPosition.y == 0.0f))) {
            recyclerView.mo4818a((int) Math.signum(computeScrollVectorForPosition.x), (int) Math.signum(computeScrollVectorForPosition.y), (int[]) null);
        }
        int i6 = 0;
        this.f515hr = false;
        View view = this.mTargetView;
        if (view != null) {
            if (getChildPosition(view) == this.f514gr) {
                View view2 = this.mTargetView;
                C0582oa oaVar = recyclerView.mState;
                C0578ma maVar = this.f516ir;
                PointF pointF = this.mTargetVector;
                int i7 = (pointF == null || pointF.x == 0.0f) ? 0 : i5 > 0 ? 1 : -1;
                C0558ca caVar = this.mLayoutManager;
                if (caVar == null || !caVar.canScrollHorizontally()) {
                    i3 = 0;
                } else {
                    C0560da daVar = (C0560da) view2.getLayoutParams();
                    i3 = calculateDtToFit(caVar.getDecoratedLeft(view2) - daVar.leftMargin, caVar.getDecoratedRight(view2) + daVar.rightMargin, caVar.getPaddingLeft(), caVar.getWidth() - caVar.getPaddingRight(), i7);
                }
                PointF pointF2 = this.mTargetVector;
                int i8 = (pointF2 == null || pointF2.y == 0.0f) ? 0 : i4 > 0 ? 1 : -1;
                C0558ca caVar2 = this.mLayoutManager;
                if (caVar2 != null && caVar2.canScrollVertically()) {
                    C0560da daVar2 = (C0560da) view2.getLayoutParams();
                    i6 = calculateDtToFit(caVar2.getDecoratedTop(view2) - daVar2.topMargin, caVar2.getDecoratedBottom(view2) + daVar2.bottomMargin, caVar2.getPaddingTop(), caVar2.getHeight() - caVar2.getPaddingBottom(), i8);
                }
                int ceil = (int) Math.ceil(((double) calculateTimeForScrolling((int) Math.sqrt((double) ((i6 * i6) + (i3 * i3))))) / 0.3356d);
                if (ceil > 0) {
                    maVar.update(-i3, -i6, ceil, this.mDecelerateInterpolator);
                }
                this.f516ir.mo5164j(recyclerView);
                stop();
            } else {
                Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                this.mTargetView = null;
            }
        }
        if (this.mRunning) {
            C0582oa oaVar2 = recyclerView.mState;
            C0578ma maVar2 = this.f516ir;
            if (this.mRecyclerView.mLayout.getChildCount() == 0) {
                stop();
            } else {
                this.mInterimTargetDx = m570S(this.mInterimTargetDx, i);
                this.mInterimTargetDy = m570S(this.mInterimTargetDy, i2);
                if (this.mInterimTargetDx == 0 && this.mInterimTargetDy == 0) {
                    PointF computeScrollVectorForPosition2 = computeScrollVectorForPosition(this.f514gr);
                    if (computeScrollVectorForPosition2 == null || (computeScrollVectorForPosition2.x == 0.0f && computeScrollVectorForPosition2.y == 0.0f)) {
                        maVar2.jumpTo(this.f514gr);
                        stop();
                    } else {
                        float f = computeScrollVectorForPosition2.x;
                        float f2 = computeScrollVectorForPosition2.y;
                        float sqrt = (float) Math.sqrt((double) ((f2 * f2) + (f * f)));
                        computeScrollVectorForPosition2.x /= sqrt;
                        computeScrollVectorForPosition2.y /= sqrt;
                        this.mTargetVector = computeScrollVectorForPosition2;
                        this.mInterimTargetDx = (int) (computeScrollVectorForPosition2.x * 10000.0f);
                        this.mInterimTargetDy = (int) (computeScrollVectorForPosition2.y * 10000.0f);
                        maVar2.update((int) (((float) this.mInterimTargetDx) * 1.2f), (int) (((float) this.mInterimTargetDy) * 1.2f), (int) (((float) calculateTimeForScrolling(10000)) * 1.2f), this.mLinearInterpolator);
                    }
                }
            }
            boolean hasJumpTarget = this.f516ir.hasJumpTarget();
            this.f516ir.mo5164j(recyclerView);
            if (hasJumpTarget && this.mRunning) {
                this.f515hr = true;
                recyclerView.mViewFlinger.postOnAnimation();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onChildAttachedToWindow(View view) {
        if (getChildPosition(view) == this.f514gr) {
            this.mTargetView = view;
        }
    }

    public void setTargetPosition(int i) {
        this.f514gr = i;
    }

    /* access modifiers changed from: protected */
    public final void stop() {
        if (this.mRunning) {
            this.mRunning = false;
            this.mInterimTargetDy = 0;
            this.mInterimTargetDx = 0;
            this.mTargetVector = null;
            this.mRecyclerView.mState.f658gr = -1;
            this.mTargetView = null;
            this.f514gr = -1;
            this.f515hr = false;
            this.mLayoutManager.mo5017a(this);
            this.mLayoutManager = null;
            this.mRecyclerView = null;
        }
    }
}
