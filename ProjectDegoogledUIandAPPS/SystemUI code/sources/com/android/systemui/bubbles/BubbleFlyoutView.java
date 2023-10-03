package com.android.systemui.bubbles;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;
import com.android.systemui.recents.TriangleShape;

public class BubbleFlyoutView extends FrameLayout {
    private final ArgbEvaluator mArgbEvaluator = new ArgbEvaluator();
    private boolean mArrowPointingLeft = true;
    private final Paint mBgPaint = new Paint(3);
    private final RectF mBgRect = new RectF();
    private float mBgTranslationX;
    private float mBgTranslationY;
    private final int mBubbleElevation;
    private final int mBubbleIconBitmapSize;
    private final float mBubbleIconTopPadding;
    private final int mBubbleSize;
    private final float mCornerRadius;
    private float[] mDotCenter;
    private int mDotColor;
    private final int mFloatingBackgroundColor;
    private final int mFlyoutElevation;
    private final int mFlyoutPadding;
    private final int mFlyoutSpaceFromBubble;
    private final TextView mFlyoutText;
    private final ViewGroup mFlyoutTextContainer;
    private float mFlyoutToDotHeightDelta = 0.0f;
    private float mFlyoutToDotWidthDelta = 0.0f;
    private final ShapeDrawable mLeftTriangleShape;
    private final float mNewDotRadius;
    private final float mNewDotSize;
    private Runnable mOnHide;
    private final float mOriginalDotSize;
    private float mPercentStillFlyout = 0.0f;
    private float mPercentTransitionedToDot = 1.0f;
    private final int mPointerSize;
    private float mRestingTranslationX = 0.0f;
    private final ShapeDrawable mRightTriangleShape;
    private float mTranslationXWhenDot = 0.0f;
    private float mTranslationYWhenDot = 0.0f;
    private final Outline mTriangleOutline = new Outline();

    public BubbleFlyoutView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(C1779R$layout.bubble_flyout, this, true);
        this.mFlyoutTextContainer = (ViewGroup) findViewById(C1777R$id.bubble_flyout_text_container);
        this.mFlyoutText = (TextView) this.mFlyoutTextContainer.findViewById(C1777R$id.bubble_flyout_text);
        Resources resources = getResources();
        this.mFlyoutPadding = resources.getDimensionPixelSize(C1775R$dimen.bubble_flyout_padding_x);
        this.mFlyoutSpaceFromBubble = resources.getDimensionPixelSize(C1775R$dimen.bubble_flyout_space_from_bubble);
        this.mPointerSize = resources.getDimensionPixelSize(C1775R$dimen.bubble_flyout_pointer_size);
        this.mBubbleSize = resources.getDimensionPixelSize(C1775R$dimen.individual_bubble_size);
        this.mBubbleIconBitmapSize = resources.getDimensionPixelSize(C1775R$dimen.bubble_icon_bitmap_size);
        this.mBubbleIconTopPadding = ((float) (this.mBubbleSize - this.mBubbleIconBitmapSize)) / 2.0f;
        this.mBubbleElevation = resources.getDimensionPixelSize(C1775R$dimen.bubble_elevation);
        this.mFlyoutElevation = resources.getDimensionPixelSize(C1775R$dimen.bubble_flyout_elevation);
        this.mOriginalDotSize = ((float) this.mBubbleIconBitmapSize) * 0.228f;
        this.mNewDotRadius = (this.mOriginalDotSize * 1.0f) / 2.0f;
        this.mNewDotSize = this.mNewDotRadius * 2.0f;
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{16844002, 16844145});
        this.mFloatingBackgroundColor = obtainStyledAttributes.getColor(0, -1);
        this.mCornerRadius = (float) obtainStyledAttributes.getDimensionPixelSize(1, 0);
        obtainStyledAttributes.recycle();
        int i = this.mPointerSize;
        setPadding(i, 0, i, 0);
        setWillNotDraw(false);
        setClipChildren(false);
        setTranslationZ((float) this.mFlyoutElevation);
        setOutlineProvider(new ViewOutlineProvider() {
            public void getOutline(View view, Outline outline) {
                BubbleFlyoutView.this.getOutline(outline);
            }
        });
        this.mBgPaint.setColor(this.mFloatingBackgroundColor);
        int i2 = this.mPointerSize;
        this.mLeftTriangleShape = new ShapeDrawable(TriangleShape.createHorizontal((float) i2, (float) i2, true));
        ShapeDrawable shapeDrawable = this.mLeftTriangleShape;
        int i3 = this.mPointerSize;
        shapeDrawable.setBounds(0, 0, i3, i3);
        this.mLeftTriangleShape.getPaint().setColor(this.mFloatingBackgroundColor);
        int i4 = this.mPointerSize;
        this.mRightTriangleShape = new ShapeDrawable(TriangleShape.createHorizontal((float) i4, (float) i4, false));
        ShapeDrawable shapeDrawable2 = this.mRightTriangleShape;
        int i5 = this.mPointerSize;
        shapeDrawable2.setBounds(0, 0, i5, i5);
        this.mRightTriangleShape.getPaint().setColor(this.mFloatingBackgroundColor);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        renderBackground(canvas);
        invalidateOutline();
        super.onDraw(canvas);
    }

    /* access modifiers changed from: package-private */
    public void setupFlyoutStartingAsDot(CharSequence charSequence, PointF pointF, float f, boolean z, int i, Runnable runnable, Runnable runnable2, float[] fArr) {
        this.mArrowPointingLeft = z;
        this.mDotColor = i;
        this.mOnHide = runnable2;
        this.mDotCenter = fArr;
        setCollapsePercent(1.0f);
        this.mFlyoutText.setMaxWidth(((int) (f * 0.6f)) - (this.mFlyoutPadding * 2));
        this.mFlyoutText.setText(charSequence);
        post(new Runnable(pointF, runnable) {
            private final /* synthetic */ PointF f$1;
            private final /* synthetic */ Runnable f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                BubbleFlyoutView.this.lambda$setupFlyoutStartingAsDot$0$BubbleFlyoutView(this.f$1, this.f$2);
            }
        });
    }

    public /* synthetic */ void lambda$setupFlyoutStartingAsDot$0$BubbleFlyoutView(PointF pointF, Runnable runnable) {
        float f;
        float f2;
        float f3;
        if (this.mFlyoutText.getLineCount() > 1) {
            f2 = pointF.y;
            f = this.mBubbleIconTopPadding;
        } else {
            f2 = pointF.y;
            f = ((float) (this.mBubbleSize - this.mFlyoutTextContainer.getHeight())) / 2.0f;
        }
        float f4 = f2 + f;
        setTranslationY(f4);
        if (this.mArrowPointingLeft) {
            f3 = pointF.x + ((float) this.mBubbleSize) + ((float) this.mFlyoutSpaceFromBubble);
        } else {
            f3 = (pointF.x - ((float) getWidth())) - ((float) this.mFlyoutSpaceFromBubble);
        }
        this.mRestingTranslationX = f3;
        this.mFlyoutToDotWidthDelta = ((float) getWidth()) - this.mNewDotSize;
        this.mFlyoutToDotHeightDelta = ((float) getHeight()) - this.mNewDotSize;
        float f5 = pointF.x;
        float[] fArr = this.mDotCenter;
        float f6 = f5 + fArr[0];
        float f7 = this.mOriginalDotSize;
        float f8 = this.mRestingTranslationX;
        float f9 = f4 - ((pointF.y + fArr[1]) - (f7 / 2.0f));
        this.mTranslationXWhenDot = -(f8 - (f6 - (f7 / 2.0f)));
        this.mTranslationYWhenDot = -f9;
        if (runnable != null) {
            runnable.run();
        }
    }

    /* access modifiers changed from: package-private */
    public void hideFlyout() {
        Runnable runnable = this.mOnHide;
        if (runnable != null) {
            runnable.run();
            this.mOnHide = null;
        }
        setVisibility(8);
    }

    /* access modifiers changed from: package-private */
    public void setCollapsePercent(float f) {
        if (!Float.isNaN(f)) {
            this.mPercentTransitionedToDot = Math.max(0.0f, Math.min(f, 1.0f));
            this.mPercentStillFlyout = 1.0f - this.mPercentTransitionedToDot;
            this.mFlyoutText.setTranslationX(((float) (this.mArrowPointingLeft ? -getWidth() : getWidth())) * this.mPercentTransitionedToDot);
            this.mFlyoutText.setAlpha(clampPercentage((this.mPercentStillFlyout - 0.75f) / 0.25f));
            int i = this.mFlyoutElevation;
            setTranslationZ(((float) i) - (((float) (i - this.mBubbleElevation)) * this.mPercentTransitionedToDot));
            invalidate();
        }
    }

    /* access modifiers changed from: package-private */
    public float getRestingTranslationX() {
        return this.mRestingTranslationX;
    }

    private float clampPercentage(float f) {
        return Math.min(1.0f, Math.max(0.0f, f));
    }

    private void renderBackground(Canvas canvas) {
        float width = ((float) getWidth()) - (this.mFlyoutToDotWidthDelta * this.mPercentTransitionedToDot);
        float f = this.mFlyoutToDotHeightDelta;
        float f2 = this.mPercentTransitionedToDot;
        float height = ((float) getHeight()) - (f * f2);
        float f3 = (this.mNewDotRadius * f2) + (this.mCornerRadius * (1.0f - f2));
        this.mBgTranslationX = this.mTranslationXWhenDot * f2;
        this.mBgTranslationY = this.mTranslationYWhenDot * f2;
        RectF rectF = this.mBgRect;
        int i = this.mPointerSize;
        float f4 = this.mPercentStillFlyout;
        rectF.set(((float) i) * f4, 0.0f, width - (((float) i) * f4), height);
        this.mBgPaint.setColor(((Integer) this.mArgbEvaluator.evaluate(this.mPercentTransitionedToDot, Integer.valueOf(this.mFloatingBackgroundColor), Integer.valueOf(this.mDotColor))).intValue());
        canvas.save();
        canvas.translate(this.mBgTranslationX, this.mBgTranslationY);
        renderPointerTriangle(canvas, width, height);
        canvas.drawRoundRect(this.mBgRect, f3, f3, this.mBgPaint);
        canvas.restore();
    }

    private void renderPointerTriangle(Canvas canvas, float f, float f2) {
        canvas.save();
        int i = this.mArrowPointingLeft ? 1 : -1;
        float f3 = this.mPercentTransitionedToDot;
        int i2 = this.mPointerSize;
        float f4 = ((float) i) * f3 * ((float) i2) * 2.0f;
        if (!this.mArrowPointingLeft) {
            f4 += f - ((float) i2);
        }
        float f5 = (f2 / 2.0f) - (((float) this.mPointerSize) / 2.0f);
        ShapeDrawable shapeDrawable = this.mArrowPointingLeft ? this.mLeftTriangleShape : this.mRightTriangleShape;
        canvas.translate(f4, f5);
        shapeDrawable.setAlpha((int) (this.mPercentStillFlyout * 255.0f));
        shapeDrawable.draw(canvas);
        shapeDrawable.getOutline(this.mTriangleOutline);
        this.mTriangleOutline.offset((int) f4, (int) f5);
        canvas.restore();
    }

    /* access modifiers changed from: private */
    public void getOutline(Outline outline) {
        if (!this.mTriangleOutline.isEmpty()) {
            Path path = new Path();
            float f = this.mNewDotRadius;
            float f2 = this.mPercentTransitionedToDot;
            float f3 = (f * f2) + (this.mCornerRadius * (1.0f - f2));
            path.addRoundRect(this.mBgRect, f3, f3, Path.Direction.CW);
            outline.setConvexPath(path);
            if (this.mPercentStillFlyout > 0.5f) {
                outline.mPath.addPath(this.mTriangleOutline.mPath);
            }
            Matrix matrix = new Matrix();
            matrix.postTranslate(((float) getLeft()) + this.mBgTranslationX, ((float) getTop()) + this.mBgTranslationY);
            float f4 = this.mPercentTransitionedToDot;
            if (f4 > 0.98f) {
                float f5 = (f4 - 0.98f) / 0.02f;
                float f6 = 1.0f - f5;
                float f7 = this.mNewDotRadius;
                matrix.postTranslate(f7 * f5, f7 * f5);
                matrix.preScale(f6, f6);
            }
            outline.mPath.transform(matrix);
        }
    }
}
