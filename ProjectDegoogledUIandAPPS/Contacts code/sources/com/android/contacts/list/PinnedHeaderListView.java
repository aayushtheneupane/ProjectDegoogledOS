package com.android.contacts.list;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.android.contacts.ContactPhotoManager;
import com.android.contacts.util.ViewUtil;

public class PinnedHeaderListView extends AutoScrollListView implements AbsListView.OnScrollListener, AdapterView.OnItemSelectedListener {
    private PinnedHeaderAdapter mAdapter;
    private boolean mAnimating;
    private int mAnimationDuration;
    private long mAnimationTargetTime;
    private RectF mBounds;
    private int mHeaderPaddingStart;
    private boolean mHeaderTouched;
    private int mHeaderWidth;
    private PinnedHeader[] mHeaders;
    private AdapterView.OnItemSelectedListener mOnItemSelectedListener;
    private AbsListView.OnScrollListener mOnScrollListener;
    private int mScrollState;
    private boolean mScrollToSectionOnHeaderTouch;
    private int mSize;

    public interface PinnedHeaderAdapter {
        void configurePinnedHeaders(PinnedHeaderListView pinnedHeaderListView);

        int getPinnedHeaderCount();

        View getPinnedHeaderView(int i, View view, ViewGroup viewGroup);

        int getScrollPositionForHeader(int i);
    }

    private static final class PinnedHeader {
        int alpha;
        boolean animating;
        int height;
        int sourceY;
        int state;
        long targetTime;
        boolean targetVisible;
        int targetY;
        View view;
        boolean visible;

        /* renamed from: y */
        int f11y;

        private PinnedHeader() {
        }
    }

    public PinnedHeaderListView(Context context) {
        this(context, (AttributeSet) null, 16842868);
    }

    public PinnedHeaderListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842868);
    }

    public PinnedHeaderListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBounds = new RectF();
        this.mScrollToSectionOnHeaderTouch = false;
        this.mHeaderTouched = false;
        this.mAnimationDuration = 20;
        super.setOnScrollListener(this);
        super.setOnItemSelectedListener(this);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mHeaderPaddingStart = getPaddingStart();
        this.mHeaderWidth = ((i3 - i) - this.mHeaderPaddingStart) - getPaddingEnd();
    }

    public void setAdapter(ListAdapter listAdapter) {
        this.mAdapter = (PinnedHeaderAdapter) listAdapter;
        super.setAdapter(listAdapter);
    }

    public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        this.mOnScrollListener = onScrollListener;
        super.setOnScrollListener(this);
    }

    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.mOnItemSelectedListener = onItemSelectedListener;
        super.setOnItemSelectedListener(this);
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        PinnedHeaderAdapter pinnedHeaderAdapter = this.mAdapter;
        if (pinnedHeaderAdapter != null) {
            int pinnedHeaderCount = pinnedHeaderAdapter.getPinnedHeaderCount();
            if (pinnedHeaderCount != this.mSize) {
                this.mSize = pinnedHeaderCount;
                PinnedHeader[] pinnedHeaderArr = this.mHeaders;
                if (pinnedHeaderArr == null) {
                    this.mHeaders = new PinnedHeader[this.mSize];
                } else {
                    int length = pinnedHeaderArr.length;
                    int i4 = this.mSize;
                    if (length < i4) {
                        this.mHeaders = new PinnedHeader[i4];
                        System.arraycopy(pinnedHeaderArr, 0, this.mHeaders, 0, pinnedHeaderArr.length);
                    }
                }
            }
            for (int i5 = 0; i5 < this.mSize; i5++) {
                PinnedHeader[] pinnedHeaderArr2 = this.mHeaders;
                if (pinnedHeaderArr2[i5] == null) {
                    pinnedHeaderArr2[i5] = new PinnedHeader();
                }
                PinnedHeader[] pinnedHeaderArr3 = this.mHeaders;
                pinnedHeaderArr3[i5].view = this.mAdapter.getPinnedHeaderView(i5, pinnedHeaderArr3[i5].view, this);
            }
            this.mAnimationTargetTime = System.currentTimeMillis() + ((long) this.mAnimationDuration);
            this.mAdapter.configurePinnedHeaders(this);
            invalidateIfAnimating();
        }
        AbsListView.OnScrollListener onScrollListener = this.mOnScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScroll(this, i, i2, i3);
        }
    }

    /* access modifiers changed from: protected */
    public float getTopFadingEdgeStrength() {
        return this.mSize > 0 ? ContactPhotoManager.OFFSET_DEFAULT : super.getTopFadingEdgeStrength();
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        this.mScrollState = i;
        AbsListView.OnScrollListener onScrollListener = this.mOnScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScrollStateChanged(this, i);
        }
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        int height = getHeight();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= this.mSize) {
                break;
            }
            PinnedHeader pinnedHeader = this.mHeaders[i2];
            if (pinnedHeader.visible) {
                int i4 = pinnedHeader.state;
                if (i4 == 0) {
                    i3 = pinnedHeader.f11y + pinnedHeader.height;
                } else if (i4 == 1) {
                    height = pinnedHeader.f11y;
                    break;
                }
            }
            i2++;
        }
        View selectedView = getSelectedView();
        if (selectedView != null) {
            if (selectedView.getTop() < i3) {
                setSelectionFromTop(i, i3);
            } else if (selectedView.getBottom() > height) {
                setSelectionFromTop(i, height - selectedView.getHeight());
            }
        }
        AdapterView.OnItemSelectedListener onItemSelectedListener = this.mOnItemSelectedListener;
        if (onItemSelectedListener != null) {
            onItemSelectedListener.onItemSelected(adapterView, view, i, j);
        }
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
        AdapterView.OnItemSelectedListener onItemSelectedListener = this.mOnItemSelectedListener;
        if (onItemSelectedListener != null) {
            onItemSelectedListener.onNothingSelected(adapterView);
        }
    }

    public int getPinnedHeaderHeight(int i) {
        ensurePinnedHeaderLayout(i);
        return this.mHeaders[i].view.getHeight();
    }

    public void setHeaderPinnedAtTop(int i, int i2, boolean z) {
        ensurePinnedHeaderLayout(i);
        PinnedHeader pinnedHeader = this.mHeaders[i];
        pinnedHeader.visible = true;
        pinnedHeader.f11y = i2;
        pinnedHeader.state = 0;
        pinnedHeader.animating = false;
    }

    public void setHeaderPinnedAtBottom(int i, int i2, boolean z) {
        ensurePinnedHeaderLayout(i);
        PinnedHeader pinnedHeader = this.mHeaders[i];
        pinnedHeader.state = 1;
        if (pinnedHeader.animating) {
            pinnedHeader.targetTime = this.mAnimationTargetTime;
            pinnedHeader.sourceY = pinnedHeader.f11y;
            pinnedHeader.targetY = i2;
        } else if (!z || (pinnedHeader.f11y == i2 && pinnedHeader.visible)) {
            pinnedHeader.visible = true;
            pinnedHeader.f11y = i2;
        } else {
            if (pinnedHeader.visible) {
                pinnedHeader.sourceY = pinnedHeader.f11y;
            } else {
                pinnedHeader.visible = true;
                pinnedHeader.sourceY = pinnedHeader.height + i2;
            }
            pinnedHeader.animating = true;
            pinnedHeader.targetVisible = true;
            pinnedHeader.targetTime = this.mAnimationTargetTime;
            pinnedHeader.targetY = i2;
        }
    }

    public void setFadingHeader(int i, int i2, boolean z) {
        int bottom;
        int i3;
        ensurePinnedHeaderLayout(i);
        View childAt = getChildAt(i2 - getFirstVisiblePosition());
        if (childAt != null) {
            PinnedHeader pinnedHeader = this.mHeaders[i];
            pinnedHeader.visible = !((TextView) pinnedHeader.view).getText().toString().isEmpty();
            pinnedHeader.state = 2;
            pinnedHeader.alpha = 255;
            pinnedHeader.animating = false;
            int totalTopPinnedHeaderHeight = getTotalTopPinnedHeaderHeight();
            pinnedHeader.f11y = totalTopPinnedHeaderHeight;
            if (z && (bottom = childAt.getBottom() - totalTopPinnedHeaderHeight) < (i3 = pinnedHeader.height)) {
                int i4 = bottom - i3;
                pinnedHeader.alpha = ((i3 + i4) * 255) / i3;
                pinnedHeader.f11y = totalTopPinnedHeaderHeight + i4;
            }
        }
    }

    public void setHeaderInvisible(int i, boolean z) {
        PinnedHeader pinnedHeader = this.mHeaders[i];
        if (!pinnedHeader.visible || ((!z && !pinnedHeader.animating) || pinnedHeader.state != 1)) {
            pinnedHeader.visible = false;
            return;
        }
        pinnedHeader.sourceY = pinnedHeader.f11y;
        if (!pinnedHeader.animating) {
            pinnedHeader.visible = true;
            pinnedHeader.targetY = getBottom() + pinnedHeader.height;
        }
        pinnedHeader.animating = true;
        pinnedHeader.targetTime = this.mAnimationTargetTime;
        pinnedHeader.targetVisible = false;
    }

    private void ensurePinnedHeaderLayout(int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        View view = this.mHeaders[i].view;
        if (view.isLayoutRequested()) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null || (i5 = layoutParams.width) <= 0) {
                i2 = View.MeasureSpec.makeMeasureSpec(this.mHeaderWidth, 1073741824);
            } else {
                i2 = View.MeasureSpec.makeMeasureSpec(i5, 1073741824);
            }
            if (layoutParams == null || (i4 = layoutParams.height) <= 0) {
                i3 = View.MeasureSpec.makeMeasureSpec(0, 0);
            } else {
                i3 = View.MeasureSpec.makeMeasureSpec(i4, 1073741824);
            }
            view.measure(i2, i3);
            int measuredHeight = view.getMeasuredHeight();
            this.mHeaders[i].height = measuredHeight;
            view.layout(0, 0, view.getMeasuredWidth(), measuredHeight);
        }
    }

    public int getTotalTopPinnedHeaderHeight() {
        int i = this.mSize;
        while (true) {
            i--;
            if (i < 0) {
                return 0;
            }
            PinnedHeader pinnedHeader = this.mHeaders[i];
            if (pinnedHeader.visible && pinnedHeader.state == 0) {
                return pinnedHeader.f11y + pinnedHeader.height;
            }
        }
    }

    public int getPositionAt(int i) {
        do {
            int pointToPosition = pointToPosition(getPaddingLeft() + 1, i);
            if (pointToPosition != -1) {
                return pointToPosition;
            }
            i--;
        } while (i > 0);
        return 0;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int i;
        int i2;
        this.mHeaderTouched = false;
        if (super.onInterceptTouchEvent(motionEvent)) {
            return true;
        }
        if (this.mScrollState == 0) {
            int y = (int) motionEvent.getY();
            int x = (int) motionEvent.getX();
            int i3 = this.mSize;
            while (true) {
                i3--;
                if (i3 < 0) {
                    break;
                }
                PinnedHeader pinnedHeader = this.mHeaders[i3];
                if (ViewUtil.isViewLayoutRtl(this)) {
                    i = (getWidth() - this.mHeaderPaddingStart) - pinnedHeader.view.getWidth();
                } else {
                    i = this.mHeaderPaddingStart;
                }
                if (pinnedHeader.visible && (i2 = pinnedHeader.f11y) <= y && i2 + pinnedHeader.height > y && x >= i && i + pinnedHeader.view.getWidth() >= x) {
                    this.mHeaderTouched = true;
                    if (!this.mScrollToSectionOnHeaderTouch || motionEvent.getAction() != 0) {
                        return true;
                    }
                    return smoothScrollToPartition(i3);
                }
            }
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mHeaderTouched) {
            return super.onTouchEvent(motionEvent);
        }
        if (motionEvent.getAction() == 1) {
            this.mHeaderTouched = false;
        }
        return true;
    }

    private boolean smoothScrollToPartition(int i) {
        int scrollPositionForHeader;
        PinnedHeaderAdapter pinnedHeaderAdapter = this.mAdapter;
        if (pinnedHeaderAdapter == null || (scrollPositionForHeader = pinnedHeaderAdapter.getScrollPositionForHeader(i)) == -1) {
            return false;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            PinnedHeader pinnedHeader = this.mHeaders[i3];
            if (pinnedHeader.visible) {
                i2 += pinnedHeader.height;
            }
        }
        smoothScrollToPositionFromTop(scrollPositionForHeader + getHeaderViewsCount(), i2, 100);
        return true;
    }

    private void invalidateIfAnimating() {
        this.mAnimating = false;
        for (int i = 0; i < this.mSize; i++) {
            if (this.mHeaders[i].animating) {
                this.mAnimating = true;
                invalidate();
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        int i;
        int i2;
        int i3;
        long currentTimeMillis = this.mAnimating ? System.currentTimeMillis() : 0;
        int bottom = getBottom();
        boolean z = false;
        int i4 = 0;
        for (int i5 = 0; i5 < this.mSize; i5++) {
            PinnedHeader pinnedHeader = this.mHeaders[i5];
            if (pinnedHeader.visible) {
                if (pinnedHeader.state != 1 || (i3 = pinnedHeader.f11y) >= bottom) {
                    int i6 = pinnedHeader.state;
                    if ((i6 == 0 || i6 == 2) && (i2 = pinnedHeader.f11y + pinnedHeader.height) > i4) {
                        i4 = i2;
                    }
                } else {
                    bottom = i3;
                }
                z = true;
            }
        }
        if (z) {
            canvas.save();
        }
        super.dispatchDraw(canvas);
        if (z) {
            canvas.restore();
            if (this.mSize > 0 && getFirstVisiblePosition() == 0) {
                View childAt = getChildAt(0);
                PinnedHeader pinnedHeader2 = this.mHeaders[0];
                if (pinnedHeader2 != null) {
                    pinnedHeader2.f11y = Math.max(pinnedHeader2.f11y, childAt != null ? childAt.getTop() : 0);
                }
            }
            int i7 = this.mSize;
            while (true) {
                i7--;
                if (i7 < 0) {
                    break;
                }
                PinnedHeader pinnedHeader3 = this.mHeaders[i7];
                if (pinnedHeader3.visible && ((i = pinnedHeader3.state) == 0 || i == 2)) {
                    drawHeader(canvas, pinnedHeader3, currentTimeMillis);
                }
            }
            for (int i8 = 0; i8 < this.mSize; i8++) {
                PinnedHeader pinnedHeader4 = this.mHeaders[i8];
                if (pinnedHeader4.visible && pinnedHeader4.state == 1) {
                    drawHeader(canvas, pinnedHeader4, currentTimeMillis);
                }
            }
        }
        invalidateIfAnimating();
    }

    private void drawHeader(Canvas canvas, PinnedHeader pinnedHeader, long j) {
        int i;
        if (pinnedHeader.animating) {
            int i2 = (int) (pinnedHeader.targetTime - j);
            if (i2 <= 0) {
                pinnedHeader.f11y = pinnedHeader.targetY;
                pinnedHeader.visible = pinnedHeader.targetVisible;
                pinnedHeader.animating = false;
            } else {
                int i3 = pinnedHeader.targetY;
                pinnedHeader.f11y = i3 + (((pinnedHeader.sourceY - i3) * i2) / this.mAnimationDuration);
            }
        }
        if (pinnedHeader.visible) {
            View view = pinnedHeader.view;
            int save = canvas.save();
            if (ViewUtil.isViewLayoutRtl(this)) {
                i = (getWidth() - this.mHeaderPaddingStart) - view.getWidth();
            } else {
                i = this.mHeaderPaddingStart;
            }
            canvas.translate((float) i, (float) pinnedHeader.f11y);
            if (pinnedHeader.state == 2) {
                this.mBounds.set(ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, (float) view.getWidth(), (float) view.getHeight());
                canvas.saveLayerAlpha(this.mBounds, pinnedHeader.alpha);
            }
            view.draw(canvas);
            canvas.restoreToCount(save);
        }
    }
}
