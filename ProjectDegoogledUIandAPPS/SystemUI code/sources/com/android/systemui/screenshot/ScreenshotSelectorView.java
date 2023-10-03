package com.android.systemui.screenshot;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.android.systemui.C1775R$dimen;

public class ScreenshotSelectorView extends FrameLayout implements View.OnTouchListener {
    private final int mBorderWidth;
    private final int mCornerWidth;
    private Rect mDrawingRect;
    private boolean mIsFirstSelection;
    private boolean mIsMoving;
    private OnSelectionListener mListener;
    private int mMovingOffsetX;
    private int mMovingOffsetY;
    private final Paint mPaintBackground;
    private final Paint mPaintSelection;
    private final Paint mPaintSelectionBorder;
    private final Paint mPaintSelectionCorner;
    private ResizingHandle mResizingHandle;
    private int mResizingOffsetX;
    private int mResizingOffsetY;
    private Rect mSelectionRect;
    private final int mTouchWidth;

    public interface OnSelectionListener {
        void onSelectionChanged(Rect rect, boolean z);
    }

    private enum ResizingHandle {
        INVALID,
        BOTTOM_RIGHT,
        BOTTOM_LEFT,
        TOP_RIGHT,
        TOP_LEFT,
        RIGHT,
        BOTTOM,
        LEFT,
        TOP;

        public boolean isValid() {
            return this != INVALID;
        }

        public boolean isLeft() {
            return this == LEFT || this == TOP_LEFT || this == BOTTOM_LEFT;
        }

        public boolean isTop() {
            return this == TOP || this == TOP_LEFT || this == TOP_RIGHT;
        }

        public boolean isRight() {
            return this == RIGHT || this == TOP_RIGHT || this == BOTTOM_RIGHT;
        }

        public boolean isBottom() {
            return this == BOTTOM || this == BOTTOM_RIGHT || this == BOTTOM_LEFT;
        }
    }

    public ScreenshotSelectorView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ScreenshotSelectorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mResizingHandle = ResizingHandle.INVALID;
        this.mBorderWidth = (int) context.getResources().getDimension(C1775R$dimen.global_screenshot_selector_border_width);
        this.mCornerWidth = (int) context.getResources().getDimension(C1775R$dimen.global_screenshot_selector_corner_width);
        this.mTouchWidth = (int) context.getResources().getDimension(C1775R$dimen.global_screenshot_selector_touch_width);
        this.mPaintBackground = new Paint(-16777216);
        this.mPaintBackground.setAlpha(160);
        this.mPaintSelection = new Paint(0);
        this.mPaintSelection.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        this.mPaintSelectionBorder = new Paint();
        this.mPaintSelectionBorder.setStyle(Paint.Style.STROKE);
        this.mPaintSelectionBorder.setStrokeWidth((float) this.mBorderWidth);
        this.mPaintSelectionBorder.setColor(-1);
        this.mPaintSelectionBorder.setAntiAlias(true);
        this.mPaintSelectionCorner = new Paint();
        this.mPaintSelectionCorner.setStyle(Paint.Style.STROKE);
        this.mPaintSelectionCorner.setStrokeWidth((float) this.mCornerWidth);
        this.mPaintSelectionCorner.setColor(-1);
        this.mPaintSelectionCorner.setAntiAlias(true);
        this.mDrawingRect = new Rect();
        setOnTouchListener(this);
        setWillNotDraw(false);
    }

    private void startSelection(int i, int i2) {
        this.mSelectionRect = new Rect(i, i2, i, i2);
        invalidate();
    }

    public Rect getSelectionRect() {
        return this.mSelectionRect;
    }

    private void sortSelectionRect() {
        this.mSelectionRect.sort();
    }

    public void stopSelection() {
        this.mSelectionRect = null;
        invalidate();
    }

    private void delegateSelection() {
        OnSelectionListener onSelectionListener = this.mListener;
        if (onSelectionListener != null) {
            onSelectionListener.onSelectionChanged(this.mSelectionRect, this.mIsFirstSelection);
        }
    }

    public void setSelectionListener(OnSelectionListener onSelectionListener) {
        this.mListener = onSelectionListener;
    }

    private boolean isTouchingCenteredSquare(int i, int i2, int i3, int i4) {
        int i5 = this.mTouchWidth;
        return i3 >= i - i5 && i3 <= i + i5 && i4 >= i2 - i5 && i4 <= i2 + i5;
    }

    private boolean isTouchingBorder(int i, int i2, int i3, int i4, int i5, boolean z) {
        if (!z) {
            int i6 = i5;
            i5 = i4;
            i4 = i6;
        }
        int i7 = this.mTouchWidth;
        return i4 >= i3 - i7 && i4 <= i3 + i7 && i5 >= i && i5 <= i2;
    }

    /* renamed from: com.android.systemui.screenshot.ScreenshotSelectorView$1 */
    static /* synthetic */ class C10091 {

        /* renamed from: $SwitchMap$com$android$systemui$screenshot$ScreenshotSelectorView$ResizingHandle */
        static final /* synthetic */ int[] f62xf1ad43be = new int[ResizingHandle.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|(3:15|16|18)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.android.systemui.screenshot.ScreenshotSelectorView$ResizingHandle[] r0 = com.android.systemui.screenshot.ScreenshotSelectorView.ResizingHandle.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f62xf1ad43be = r0
                int[] r0 = f62xf1ad43be     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.android.systemui.screenshot.ScreenshotSelectorView$ResizingHandle r1 = com.android.systemui.screenshot.ScreenshotSelectorView.ResizingHandle.LEFT     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f62xf1ad43be     // Catch:{ NoSuchFieldError -> 0x001f }
                com.android.systemui.screenshot.ScreenshotSelectorView$ResizingHandle r1 = com.android.systemui.screenshot.ScreenshotSelectorView.ResizingHandle.TOP_LEFT     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f62xf1ad43be     // Catch:{ NoSuchFieldError -> 0x002a }
                com.android.systemui.screenshot.ScreenshotSelectorView$ResizingHandle r1 = com.android.systemui.screenshot.ScreenshotSelectorView.ResizingHandle.TOP     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f62xf1ad43be     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.android.systemui.screenshot.ScreenshotSelectorView$ResizingHandle r1 = com.android.systemui.screenshot.ScreenshotSelectorView.ResizingHandle.TOP_RIGHT     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f62xf1ad43be     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.android.systemui.screenshot.ScreenshotSelectorView$ResizingHandle r1 = com.android.systemui.screenshot.ScreenshotSelectorView.ResizingHandle.RIGHT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f62xf1ad43be     // Catch:{ NoSuchFieldError -> 0x004b }
                com.android.systemui.screenshot.ScreenshotSelectorView$ResizingHandle r1 = com.android.systemui.screenshot.ScreenshotSelectorView.ResizingHandle.BOTTOM_RIGHT     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = f62xf1ad43be     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.android.systemui.screenshot.ScreenshotSelectorView$ResizingHandle r1 = com.android.systemui.screenshot.ScreenshotSelectorView.ResizingHandle.BOTTOM     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = f62xf1ad43be     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.android.systemui.screenshot.ScreenshotSelectorView$ResizingHandle r1 = com.android.systemui.screenshot.ScreenshotSelectorView.ResizingHandle.BOTTOM_LEFT     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.screenshot.ScreenshotSelectorView.C10091.<clinit>():void");
        }
    }

    private boolean isTouching(ResizingHandle resizingHandle, int i, int i2) {
        switch (C10091.f62xf1ad43be[resizingHandle.ordinal()]) {
            case 1:
                Rect rect = this.mSelectionRect;
                return isTouchingBorder(rect.top, rect.bottom, rect.left, i, i2, true);
            case 2:
                Rect rect2 = this.mSelectionRect;
                return isTouchingCenteredSquare(rect2.left, rect2.top, i, i2);
            case 3:
                Rect rect3 = this.mSelectionRect;
                return isTouchingBorder(rect3.left, rect3.right, rect3.top, i, i2, false);
            case 4:
                Rect rect4 = this.mSelectionRect;
                return isTouchingCenteredSquare(rect4.right, rect4.top, i, i2);
            case 5:
                Rect rect5 = this.mSelectionRect;
                return isTouchingBorder(rect5.top, rect5.bottom, rect5.right, i, i2, true);
            case 6:
                Rect rect6 = this.mSelectionRect;
                return isTouchingCenteredSquare(rect6.right, rect6.bottom, i, i2);
            case 7:
                Rect rect7 = this.mSelectionRect;
                return isTouchingBorder(rect7.left, rect7.right, rect7.bottom, i, i2, false);
            case 8:
                Rect rect8 = this.mSelectionRect;
                return isTouchingCenteredSquare(rect8.left, rect8.bottom, i, i2);
            default:
                return false;
        }
    }

    private ResizingHandle getTouchedResizingHandle(int i, int i2) {
        for (ResizingHandle resizingHandle : ResizingHandle.values()) {
            if (isTouching(resizingHandle, i, i2)) {
                return resizingHandle;
            }
        }
        return ResizingHandle.INVALID;
    }

    public boolean isInsideSelection(int i, int i2) {
        return this.mSelectionRect.contains(i, i2);
    }

    private void resizeSelection(int i, int i2) {
        if (this.mResizingHandle.isLeft()) {
            this.mSelectionRect.left = Math.max(i - this.mResizingOffsetX, 0);
        }
        if (this.mResizingHandle.isTop()) {
            this.mSelectionRect.top = Math.max(i2 - this.mResizingOffsetY, 0);
        }
        if (this.mResizingHandle.isRight()) {
            this.mSelectionRect.right = Math.min(i - this.mResizingOffsetX, getMeasuredWidth());
        }
        if (this.mResizingHandle.isBottom()) {
            this.mSelectionRect.bottom = Math.min(i2 - this.mResizingOffsetY, getMeasuredHeight());
        }
        invalidate();
    }

    private void setMovingOffset(int i, int i2) {
        Rect rect = this.mSelectionRect;
        this.mMovingOffsetX = i - rect.left;
        this.mMovingOffsetY = i2 - rect.top;
    }

    private void setResizingOffset(int i, int i2) {
        this.mResizingOffsetX = 0;
        this.mResizingOffsetY = 0;
        if (this.mResizingHandle.isLeft()) {
            this.mResizingOffsetX = i - this.mSelectionRect.left;
        }
        if (this.mResizingHandle.isTop()) {
            this.mResizingOffsetY = i2 - this.mSelectionRect.top;
        }
        if (this.mResizingHandle.isRight()) {
            this.mResizingOffsetX = i - this.mSelectionRect.right;
        }
        if (this.mResizingHandle.isBottom()) {
            this.mResizingOffsetY = i2 - this.mSelectionRect.bottom;
        }
    }

    private void moveSelection(int i, int i2) {
        int i3 = i - this.mMovingOffsetX;
        int i4 = i2 - this.mMovingOffsetY;
        int width = this.mSelectionRect.width() + i3;
        int height = this.mSelectionRect.height() + i4;
        if (i3 < 0) {
            width += -i3;
            i3 = 0;
        }
        if (i4 < 0) {
            height += -i4;
            i4 = 0;
        }
        int measuredWidth = getMeasuredWidth();
        int i5 = width - measuredWidth;
        if (i5 > 0) {
            i3 -= i5;
            width = measuredWidth;
        }
        int measuredHeight = getMeasuredHeight();
        int i6 = height - measuredHeight;
        if (i6 > 0) {
            i4 -= i6;
            height = measuredHeight;
        }
        this.mSelectionRect.set(i3, i4, width, height);
        invalidate();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect((float) this.mLeft, (float) this.mTop, (float) this.mRight, (float) this.mBottom, this.mPaintBackground);
        Rect rect = this.mSelectionRect;
        if (rect != null) {
            this.mDrawingRect.set(rect);
            this.mDrawingRect.sort();
            canvas.drawRect(this.mDrawingRect, this.mPaintSelectionBorder);
            Rect rect2 = this.mDrawingRect;
            int i = rect2.left;
            float f = (float) rect2.top;
            float min = (float) Math.min(i + this.mTouchWidth, rect2.right);
            Rect rect3 = this.mDrawingRect;
            Canvas canvas2 = canvas;
            canvas2.drawRect((float) i, f, min, (float) Math.min(rect3.top + this.mTouchWidth, rect3.bottom), this.mPaintSelectionCorner);
            Rect rect4 = this.mDrawingRect;
            float max = (float) Math.max(rect4.right - this.mTouchWidth, rect4.left);
            Rect rect5 = this.mDrawingRect;
            int i2 = rect5.top;
            canvas.drawRect(max, (float) i2, (float) rect5.right, (float) Math.min(i2 + this.mTouchWidth, rect5.bottom), this.mPaintSelectionCorner);
            Rect rect6 = this.mDrawingRect;
            float max2 = (float) Math.max(rect6.right - this.mTouchWidth, rect6.left);
            Rect rect7 = this.mDrawingRect;
            float max3 = (float) Math.max(rect7.bottom - this.mTouchWidth, rect7.top);
            Rect rect8 = this.mDrawingRect;
            canvas.drawRect(max2, max3, (float) rect8.right, (float) rect8.bottom, this.mPaintSelectionCorner);
            Rect rect9 = this.mDrawingRect;
            float f2 = (float) rect9.left;
            float max4 = (float) Math.max(rect9.bottom - this.mTouchWidth, rect9.top);
            Rect rect10 = this.mDrawingRect;
            canvas.drawRect(f2, max4, (float) Math.min(rect10.left + this.mTouchWidth, rect10.right), (float) this.mDrawingRect.bottom, this.mPaintSelectionCorner);
            canvas.drawRect(this.mDrawingRect, this.mPaintSelection);
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 1) {
                sortSelectionRect();
                delegateSelection();
                this.mResizingHandle = ResizingHandle.INVALID;
                this.mIsFirstSelection = false;
                this.mIsMoving = false;
            } else if (action == 2) {
                if (this.mResizingHandle.isValid()) {
                    resizeSelection(x, y);
                } else if (this.mIsMoving) {
                    moveSelection(x, y);
                }
            }
        } else if (this.mSelectionRect == null) {
            startSelection(x, y);
            this.mIsFirstSelection = true;
            this.mResizingHandle = ResizingHandle.BOTTOM_RIGHT;
        } else {
            this.mResizingHandle = getTouchedResizingHandle(x, y);
            if (this.mResizingHandle.isValid()) {
                setResizingOffset(x, y);
            } else if (isInsideSelection(x, y)) {
                this.mIsMoving = true;
                setMovingOffset(x, y);
            }
        }
        return true;
    }
}
