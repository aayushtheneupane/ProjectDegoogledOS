package com.android.settings.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import androidx.viewpager.widget.ViewPager;
import com.android.settings.R$styleable;
import java.util.Arrays;

public class DotsPageIndicator extends View implements ViewPager.OnPageChangeListener {
    private long animDuration;
    /* access modifiers changed from: private */
    public long animHalfDuration;
    /* access modifiers changed from: private */
    public boolean attachedState;
    private final Path combinedUnselectedPath;
    float controlX1;
    float controlX2;
    float controlY1;
    float controlY2;
    private int currentPage;
    private float dotBottomY;
    /* access modifiers changed from: private */
    public float[] dotCenterX;
    private float dotCenterY;
    private int dotDiameter;
    /* access modifiers changed from: private */
    public float dotRadius;
    private float[] dotRevealFractions;
    private float dotTopY;
    float endX1;
    float endX2;
    float endY1;
    float endY2;
    private int gap;
    private float halfDotRadius;
    /* access modifiers changed from: private */
    public final Interpolator interpolator;
    private AnimatorSet joiningAnimationSet;
    private ValueAnimator[] joiningAnimations;
    private float[] joiningFractions;
    private ValueAnimator moveAnimation;
    private ViewPager.OnPageChangeListener pageChangeListener;
    private int pageCount;
    private final RectF rectF;
    /* access modifiers changed from: private */
    public PendingRetreatAnimator retreatAnimation;
    /* access modifiers changed from: private */
    public float retreatingJoinX1;
    /* access modifiers changed from: private */
    public float retreatingJoinX2;
    /* access modifiers changed from: private */
    public PendingRevealAnimator[] revealAnimations;
    private int selectedColour;
    /* access modifiers changed from: private */
    public boolean selectedDotInPosition;
    /* access modifiers changed from: private */
    public float selectedDotX;
    private final Paint selectedPaint;
    private int unselectedColour;
    private final Path unselectedDotLeftPath;
    private final Path unselectedDotPath;
    private final Path unselectedDotRightPath;
    private final Paint unselectedPaint;
    /* access modifiers changed from: private */
    public ViewPager viewPager;

    public DotsPageIndicator(Context context) {
        this(context, (AttributeSet) null, 0);
    }

    public DotsPageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DotsPageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        int i2 = (int) context.getResources().getDisplayMetrics().scaledDensity;
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.DotsPageIndicator, i, 0);
        this.dotDiameter = obtainStyledAttributes.getDimensionPixelSize(2, i2 * 8);
        this.dotRadius = (float) (this.dotDiameter / 2);
        this.halfDotRadius = this.dotRadius / 2.0f;
        this.gap = obtainStyledAttributes.getDimensionPixelSize(3, i2 * 12);
        this.animDuration = (long) obtainStyledAttributes.getInteger(0, 400);
        this.animHalfDuration = this.animDuration / 2;
        this.unselectedColour = obtainStyledAttributes.getColor(4, -2130706433);
        this.selectedColour = obtainStyledAttributes.getColor(1, -1);
        obtainStyledAttributes.recycle();
        this.unselectedPaint = new Paint(1);
        this.unselectedPaint.setColor(this.unselectedColour);
        this.selectedPaint = new Paint(1);
        this.selectedPaint.setColor(this.selectedColour);
        if (Build.VERSION.SDK_INT >= 21) {
            this.interpolator = AnimationUtils.loadInterpolator(context, 17563661);
        } else {
            this.interpolator = AnimationUtils.loadInterpolator(context, 17432580);
        }
        this.combinedUnselectedPath = new Path();
        this.unselectedDotPath = new Path();
        this.unselectedDotLeftPath = new Path();
        this.unselectedDotRightPath = new Path();
        this.rectF = new RectF();
        addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            public void onViewAttachedToWindow(View view) {
                boolean unused = DotsPageIndicator.this.attachedState = true;
            }

            public void onViewDetachedFromWindow(View view) {
                boolean unused = DotsPageIndicator.this.attachedState = false;
            }
        });
    }

    public void setViewPager(ViewPager viewPager2) {
        this.viewPager = viewPager2;
        viewPager2.setOnPageChangeListener(this);
        setPageCount(viewPager2.getAdapter().getCount());
        viewPager2.getAdapter().registerDataSetObserver(new DataSetObserver() {
            public void onChanged() {
                DotsPageIndicator dotsPageIndicator = DotsPageIndicator.this;
                dotsPageIndicator.setPageCount(dotsPageIndicator.viewPager.getAdapter().getCount());
            }
        });
        setCurrentPageImmediate();
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.pageChangeListener = onPageChangeListener;
    }

    public void onPageScrolled(int i, float f, int i2) {
        ViewPager.OnPageChangeListener onPageChangeListener = this.pageChangeListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrolled(i, f, i2);
        }
    }

    public void onPageSelected(int i) {
        if (this.attachedState) {
            setSelectedPage(i);
        } else {
            setCurrentPageImmediate();
        }
        ViewPager.OnPageChangeListener onPageChangeListener = this.pageChangeListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageSelected(i);
        }
    }

    public void onPageScrollStateChanged(int i) {
        ViewPager.OnPageChangeListener onPageChangeListener = this.pageChangeListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrollStateChanged(i);
        }
    }

    /* access modifiers changed from: private */
    public void setPageCount(int i) {
        this.pageCount = i;
        calculateDotPositions();
        resetState();
    }

    private void calculateDotPositions() {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        float width = ((float) (paddingLeft + ((((getWidth() - getPaddingRight()) - paddingLeft) - getRequiredWidth()) / 2))) + this.dotRadius;
        this.dotCenterX = new float[this.pageCount];
        for (int i = 0; i < this.pageCount; i++) {
            this.dotCenterX[i] = ((float) ((this.dotDiameter + this.gap) * i)) + width;
        }
        float f = (float) paddingTop;
        this.dotTopY = f;
        this.dotCenterY = f + this.dotRadius;
        this.dotBottomY = (float) (paddingTop + this.dotDiameter);
        setCurrentPageImmediate();
    }

    private void setCurrentPageImmediate() {
        ViewPager viewPager2 = this.viewPager;
        if (viewPager2 != null) {
            this.currentPage = viewPager2.getCurrentItem();
        } else {
            this.currentPage = 0;
        }
        if (this.pageCount > 0) {
            this.selectedDotX = this.dotCenterX[this.currentPage];
        }
    }

    private void resetState() {
        int i = this.pageCount;
        if (i > 0) {
            this.joiningFractions = new float[(i - 1)];
            Arrays.fill(this.joiningFractions, 0.0f);
            this.dotRevealFractions = new float[this.pageCount];
            Arrays.fill(this.dotRevealFractions, 0.0f);
            this.retreatingJoinX1 = -1.0f;
            this.retreatingJoinX2 = -1.0f;
            this.selectedDotInPosition = true;
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int desiredHeight = getDesiredHeight();
        int mode = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE) {
            desiredHeight = Math.min(desiredHeight, View.MeasureSpec.getSize(i2));
        } else if (mode == 1073741824) {
            desiredHeight = View.MeasureSpec.getSize(i2);
        }
        int desiredWidth = getDesiredWidth();
        int mode2 = View.MeasureSpec.getMode(i);
        if (mode2 == Integer.MIN_VALUE) {
            desiredWidth = Math.min(desiredWidth, View.MeasureSpec.getSize(i));
        } else if (mode2 == 1073741824) {
            desiredWidth = View.MeasureSpec.getSize(i);
        }
        setMeasuredDimension(desiredWidth, desiredHeight);
        calculateDotPositions();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        setMeasuredDimension(i, i2);
        calculateDotPositions();
    }

    public void clearAnimation() {
        super.clearAnimation();
        if (Build.VERSION.SDK_INT >= 16) {
            cancelRunningAnimations();
        }
    }

    private int getDesiredHeight() {
        return getPaddingTop() + this.dotDiameter + getPaddingBottom();
    }

    private int getRequiredWidth() {
        int i = this.pageCount;
        return (this.dotDiameter * i) + ((i - 1) * this.gap);
    }

    private int getDesiredWidth() {
        return getPaddingLeft() + getRequiredWidth() + getPaddingRight();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.viewPager != null && this.pageCount != 0) {
            drawUnselected(canvas);
            drawSelected(canvas);
        }
    }

    private void drawUnselected(Canvas canvas) {
        this.combinedUnselectedPath.rewind();
        int i = 0;
        while (true) {
            int i2 = this.pageCount;
            if (i >= i2) {
                break;
            }
            int i3 = i == i2 + -1 ? i : i + 1;
            if (Build.VERSION.SDK_INT >= 21) {
                float[] fArr = this.dotCenterX;
                this.combinedUnselectedPath.op(getUnselectedPath(i, fArr[i], fArr[i3], i == this.pageCount + -1 ? -1.0f : this.joiningFractions[i], this.dotRevealFractions[i]), Path.Op.UNION);
            } else {
                canvas.drawCircle(this.dotCenterX[i], this.dotCenterY, this.dotRadius, this.unselectedPaint);
            }
            i++;
        }
        if (this.retreatingJoinX1 != -1.0f && Build.VERSION.SDK_INT >= 21) {
            this.combinedUnselectedPath.op(getRetreatingJoinPath(), Path.Op.UNION);
        }
        canvas.drawPath(this.combinedUnselectedPath, this.unselectedPaint);
    }

    private Path getUnselectedPath(int i, float f, float f2, float f3, float f4) {
        int i2 = i;
        float f5 = f;
        float f6 = f2;
        this.unselectedDotPath.rewind();
        int i3 = (f3 > 0.0f ? 1 : (f3 == 0.0f ? 0 : -1));
        if ((i3 == 0 || f3 == -1.0f) && f4 == 0.0f && !(i2 == this.currentPage && this.selectedDotInPosition)) {
            this.unselectedDotPath.addCircle(this.dotCenterX[i2], this.dotCenterY, this.dotRadius, Path.Direction.CW);
        }
        if (i3 > 0 && f3 < 0.5f && this.retreatingJoinX1 == -1.0f) {
            this.unselectedDotLeftPath.rewind();
            this.unselectedDotLeftPath.moveTo(f5, this.dotBottomY);
            RectF rectF2 = this.rectF;
            float f7 = this.dotRadius;
            rectF2.set(f5 - f7, this.dotTopY, f7 + f5, this.dotBottomY);
            this.unselectedDotLeftPath.arcTo(this.rectF, 90.0f, 180.0f, true);
            this.endX1 = this.dotRadius + f5 + (((float) this.gap) * f3);
            this.endY1 = this.dotCenterY;
            float f8 = this.halfDotRadius;
            this.controlX1 = f5 + f8;
            this.controlY1 = this.dotTopY;
            float f9 = this.endX1;
            this.controlX2 = f9;
            float f10 = this.endY1;
            this.controlY2 = f10 - f8;
            this.unselectedDotLeftPath.cubicTo(this.controlX1, this.controlY1, this.controlX2, this.controlY2, f9, f10);
            this.endX2 = f5;
            float f11 = this.dotBottomY;
            this.endY2 = f11;
            this.controlX1 = this.endX1;
            float f12 = this.endY1;
            float f13 = this.halfDotRadius;
            this.controlY1 = f12 + f13;
            this.controlX2 = f5 + f13;
            this.controlY2 = f11;
            this.unselectedDotLeftPath.cubicTo(this.controlX1, this.controlY1, this.controlX2, this.controlY2, this.endX2, this.endY2);
            if (Build.VERSION.SDK_INT >= 21) {
                this.unselectedDotPath.op(this.unselectedDotLeftPath, Path.Op.UNION);
            }
            this.unselectedDotRightPath.rewind();
            this.unselectedDotRightPath.moveTo(f6, this.dotBottomY);
            RectF rectF3 = this.rectF;
            float f14 = this.dotRadius;
            rectF3.set(f6 - f14, this.dotTopY, f14 + f6, this.dotBottomY);
            this.unselectedDotRightPath.arcTo(this.rectF, 90.0f, -180.0f, true);
            this.endX1 = (f6 - this.dotRadius) - (((float) this.gap) * f3);
            this.endY1 = this.dotCenterY;
            float f15 = this.halfDotRadius;
            this.controlX1 = f6 - f15;
            this.controlY1 = this.dotTopY;
            float f16 = this.endX1;
            this.controlX2 = f16;
            float f17 = this.endY1;
            this.controlY2 = f17 - f15;
            this.unselectedDotRightPath.cubicTo(this.controlX1, this.controlY1, this.controlX2, this.controlY2, f16, f17);
            this.endX2 = f6;
            float f18 = this.dotBottomY;
            this.endY2 = f18;
            this.controlX1 = this.endX1;
            float f19 = this.endY1;
            float f20 = this.halfDotRadius;
            this.controlY1 = f19 + f20;
            float f21 = this.endX2;
            this.controlX2 = f21 - f20;
            this.controlY2 = f18;
            this.unselectedDotRightPath.cubicTo(this.controlX1, this.controlY1, this.controlX2, this.controlY2, f21, this.endY2);
            if (Build.VERSION.SDK_INT >= 21) {
                this.unselectedDotPath.op(this.unselectedDotRightPath, Path.Op.UNION);
            }
        }
        if (f3 > 0.5f && f3 < 1.0f && this.retreatingJoinX1 == -1.0f) {
            this.unselectedDotPath.moveTo(f5, this.dotBottomY);
            RectF rectF4 = this.rectF;
            float f22 = this.dotRadius;
            rectF4.set(f5 - f22, this.dotTopY, f22 + f5, this.dotBottomY);
            this.unselectedDotPath.arcTo(this.rectF, 90.0f, 180.0f, true);
            float f23 = this.dotRadius;
            this.endX1 = f5 + f23 + ((float) (this.gap / 2));
            this.endY1 = this.dotCenterY - (f3 * f23);
            float f24 = this.endX1;
            this.controlX1 = f24 - (f3 * f23);
            this.controlY1 = this.dotTopY;
            float f25 = 1.0f - f3;
            this.controlX2 = f24 - (f23 * f25);
            float f26 = this.endY1;
            this.controlY2 = f26;
            this.unselectedDotPath.cubicTo(this.controlX1, this.controlY1, this.controlX2, this.controlY2, f24, f26);
            this.endX2 = f6;
            float f27 = this.dotTopY;
            this.endY2 = f27;
            float f28 = this.endX1;
            float f29 = this.dotRadius;
            this.controlX1 = (f25 * f29) + f28;
            this.controlY1 = this.endY1;
            this.controlX2 = f28 + (f29 * f3);
            this.controlY2 = f27;
            this.unselectedDotPath.cubicTo(this.controlX1, this.controlY1, this.controlX2, this.controlY2, this.endX2, this.endY2);
            RectF rectF5 = this.rectF;
            float f30 = this.dotRadius;
            rectF5.set(f6 - f30, this.dotTopY, f30 + f6, this.dotBottomY);
            this.unselectedDotPath.arcTo(this.rectF, 270.0f, 180.0f, true);
            float f31 = this.dotCenterY;
            float f32 = this.dotRadius;
            this.endY1 = f31 + (f3 * f32);
            float f33 = this.endX1;
            this.controlX1 = (f3 * f32) + f33;
            this.controlY1 = this.dotBottomY;
            this.controlX2 = (f32 * f25) + f33;
            float f34 = this.endY1;
            this.controlY2 = f34;
            this.unselectedDotPath.cubicTo(this.controlX1, this.controlY1, this.controlX2, this.controlY2, f33, f34);
            this.endX2 = f5;
            this.endY2 = this.dotBottomY;
            float f35 = this.endX1;
            float f36 = this.dotRadius;
            this.controlX1 = f35 - (f25 * f36);
            this.controlY1 = this.endY1;
            this.controlX2 = f35 - (f36 * f3);
            float f37 = this.endY2;
            this.controlY2 = f37;
            this.unselectedDotPath.cubicTo(this.controlX1, this.controlY1, this.controlX2, this.controlY2, this.endX2, f37);
        }
        if (f3 == 1.0f && this.retreatingJoinX1 == -1.0f) {
            RectF rectF6 = this.rectF;
            float f38 = this.dotRadius;
            rectF6.set(f5 - f38, this.dotTopY, f6 + f38, this.dotBottomY);
            Path path = this.unselectedDotPath;
            RectF rectF7 = this.rectF;
            float f39 = this.dotRadius;
            path.addRoundRect(rectF7, f39, f39, Path.Direction.CW);
        }
        if (f4 > 1.0E-5f) {
            this.unselectedDotPath.addCircle(f5, this.dotCenterY, this.dotRadius * f4, Path.Direction.CW);
        }
        return this.unselectedDotPath;
    }

    private Path getRetreatingJoinPath() {
        this.unselectedDotPath.rewind();
        this.rectF.set(this.retreatingJoinX1, this.dotTopY, this.retreatingJoinX2, this.dotBottomY);
        Path path = this.unselectedDotPath;
        RectF rectF2 = this.rectF;
        float f = this.dotRadius;
        path.addRoundRect(rectF2, f, f, Path.Direction.CW);
        return this.unselectedDotPath;
    }

    private void drawSelected(Canvas canvas) {
        canvas.drawCircle(this.selectedDotX, this.dotCenterY, this.dotRadius, this.selectedPaint);
    }

    private void setSelectedPage(int i) {
        int i2 = this.currentPage;
        if (i != i2 && this.pageCount != 0) {
            this.currentPage = i;
            if (Build.VERSION.SDK_INT >= 16) {
                cancelRunningAnimations();
                int abs = Math.abs(i - i2);
                this.moveAnimation = createMoveSelectedAnimator(this.dotCenterX[i], i2, i, abs);
                this.joiningAnimations = new ValueAnimator[abs];
                for (int i3 = 0; i3 < abs; i3++) {
                    this.joiningAnimations[i3] = createJoiningAnimator(i > i2 ? i2 + i3 : (i2 - 1) - i3, ((long) i3) * (this.animDuration / 8));
                }
                this.moveAnimation.start();
                startJoiningAnimations();
                return;
            }
            setCurrentPageImmediate();
            invalidate();
        }
    }

    private ValueAnimator createMoveSelectedAnimator(float f, int i, int i2, int i3) {
        StartPredicate startPredicate;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{this.selectedDotX, f});
        if (i2 > i) {
            startPredicate = new RightwardStartPredicate(f - ((f - this.selectedDotX) * 0.25f));
        } else {
            startPredicate = new LeftwardStartPredicate(f + ((this.selectedDotX - f) * 0.25f));
        }
        this.retreatAnimation = new PendingRetreatAnimator(i, i2, i3, startPredicate);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float unused = DotsPageIndicator.this.selectedDotX = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                DotsPageIndicator.this.retreatAnimation.startIfNecessary(DotsPageIndicator.this.selectedDotX);
                DotsPageIndicator.this.postInvalidateOnAnimation();
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                boolean unused = DotsPageIndicator.this.selectedDotInPosition = false;
            }

            public void onAnimationEnd(Animator animator) {
                boolean unused = DotsPageIndicator.this.selectedDotInPosition = true;
            }
        });
        ofFloat.setStartDelay(this.selectedDotInPosition ? this.animDuration / 4 : 0);
        ofFloat.setDuration((this.animDuration * 3) / 4);
        ofFloat.setInterpolator(this.interpolator);
        return ofFloat;
    }

    private ValueAnimator createJoiningAnimator(final int i, long j) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                DotsPageIndicator.this.setJoiningFraction(i, valueAnimator.getAnimatedFraction());
            }
        });
        ofFloat.setDuration(this.animHalfDuration);
        ofFloat.setStartDelay(j);
        ofFloat.setInterpolator(this.interpolator);
        return ofFloat;
    }

    /* access modifiers changed from: private */
    public void setJoiningFraction(int i, float f) {
        this.joiningFractions[i] = f;
        postInvalidateOnAnimation();
    }

    /* access modifiers changed from: private */
    public void clearJoiningFractions() {
        Arrays.fill(this.joiningFractions, 0.0f);
        postInvalidateOnAnimation();
    }

    /* access modifiers changed from: private */
    public void setDotRevealFraction(int i, float f) {
        this.dotRevealFractions[i] = f;
        postInvalidateOnAnimation();
    }

    private void cancelRunningAnimations() {
        cancelMoveAnimation();
        cancelJoiningAnimations();
        cancelRetreatAnimation();
        cancelRevealAnimations();
        resetState();
    }

    private void cancelMoveAnimation() {
        ValueAnimator valueAnimator = this.moveAnimation;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.moveAnimation.cancel();
        }
    }

    private void startJoiningAnimations() {
        this.joiningAnimationSet = new AnimatorSet();
        this.joiningAnimationSet.playTogether(this.joiningAnimations);
        this.joiningAnimationSet.start();
    }

    /* access modifiers changed from: private */
    public void cancelJoiningAnimations() {
        AnimatorSet animatorSet = this.joiningAnimationSet;
        if (animatorSet != null && animatorSet.isRunning()) {
            this.joiningAnimationSet.cancel();
        }
    }

    private void cancelRetreatAnimation() {
        PendingRetreatAnimator pendingRetreatAnimator = this.retreatAnimation;
        if (pendingRetreatAnimator != null && pendingRetreatAnimator.isRunning()) {
            this.retreatAnimation.cancel();
        }
    }

    private void cancelRevealAnimations() {
        PendingRevealAnimator[] pendingRevealAnimatorArr = this.revealAnimations;
        if (pendingRevealAnimatorArr != null) {
            for (PendingRevealAnimator cancel : pendingRevealAnimatorArr) {
                cancel.cancel();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int getUnselectedColour() {
        return this.unselectedColour;
    }

    /* access modifiers changed from: package-private */
    public int getSelectedColour() {
        return this.selectedColour;
    }

    /* access modifiers changed from: package-private */
    public float getDotCenterY() {
        return this.dotCenterY;
    }

    /* access modifiers changed from: package-private */
    public float getSelectedDotX() {
        return this.selectedDotX;
    }

    /* access modifiers changed from: package-private */
    public int getCurrentPage() {
        return this.currentPage;
    }

    public abstract class PendingStartAnimator extends ValueAnimator {
        protected boolean hasStarted = false;
        protected StartPredicate predicate;

        public PendingStartAnimator(StartPredicate startPredicate) {
            this.predicate = startPredicate;
        }

        public void startIfNecessary(float f) {
            if (!this.hasStarted && this.predicate.shouldStart(f)) {
                start();
                this.hasStarted = true;
            }
        }
    }

    public class PendingRetreatAnimator extends PendingStartAnimator {
        public PendingRetreatAnimator(int i, int i2, int i3, StartPredicate startPredicate) {
            super(startPredicate);
            float f;
            float f2;
            float f3;
            float f4;
            float f5;
            float f6;
            float f7;
            float f8;
            setDuration(DotsPageIndicator.this.animHalfDuration);
            setInterpolator(DotsPageIndicator.this.interpolator);
            if (i2 > i) {
                f = Math.min(DotsPageIndicator.this.dotCenterX[i], DotsPageIndicator.this.selectedDotX);
                f2 = DotsPageIndicator.this.dotRadius;
            } else {
                f = DotsPageIndicator.this.dotCenterX[i2];
                f2 = DotsPageIndicator.this.dotRadius;
            }
            final float f9 = f - f2;
            if (i2 > i) {
                f3 = DotsPageIndicator.this.dotCenterX[i2];
                f4 = DotsPageIndicator.this.dotRadius;
            } else {
                f3 = DotsPageIndicator.this.dotCenterX[i2];
                f4 = DotsPageIndicator.this.dotRadius;
            }
            float f10 = f3 - f4;
            if (i2 > i) {
                f6 = DotsPageIndicator.this.dotCenterX[i2];
                f5 = DotsPageIndicator.this.dotRadius;
            } else {
                f6 = Math.max(DotsPageIndicator.this.dotCenterX[i], DotsPageIndicator.this.selectedDotX);
                f5 = DotsPageIndicator.this.dotRadius;
            }
            final float f11 = f6 + f5;
            if (i2 > i) {
                f7 = DotsPageIndicator.this.dotCenterX[i2];
                f8 = DotsPageIndicator.this.dotRadius;
            } else {
                f7 = DotsPageIndicator.this.dotCenterX[i2];
                f8 = DotsPageIndicator.this.dotRadius;
            }
            float f12 = f7 + f8;
            PendingRevealAnimator[] unused = DotsPageIndicator.this.revealAnimations = new PendingRevealAnimator[i3];
            final int[] iArr = new int[i3];
            int i4 = 0;
            if (f9 != f10) {
                setFloatValues(new float[]{f9, f10});
                while (i4 < i3) {
                    int i5 = i + i4;
                    DotsPageIndicator.this.revealAnimations[i4] = new PendingRevealAnimator(i5, new RightwardStartPredicate(DotsPageIndicator.this.dotCenterX[i5]));
                    iArr[i4] = i5;
                    i4++;
                }
                addUpdateListener(new ValueAnimator.AnimatorUpdateListener(DotsPageIndicator.this) {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float unused = DotsPageIndicator.this.retreatingJoinX1 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        DotsPageIndicator.this.postInvalidateOnAnimation();
                        for (PendingRevealAnimator startIfNecessary : DotsPageIndicator.this.revealAnimations) {
                            startIfNecessary.startIfNecessary(DotsPageIndicator.this.retreatingJoinX1);
                        }
                    }
                });
            } else {
                setFloatValues(new float[]{f11, f12});
                while (i4 < i3) {
                    int i6 = i - i4;
                    DotsPageIndicator.this.revealAnimations[i4] = new PendingRevealAnimator(i6, new LeftwardStartPredicate(DotsPageIndicator.this.dotCenterX[i6]));
                    iArr[i4] = i6;
                    i4++;
                }
                addUpdateListener(new ValueAnimator.AnimatorUpdateListener(DotsPageIndicator.this) {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float unused = DotsPageIndicator.this.retreatingJoinX2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        DotsPageIndicator.this.postInvalidateOnAnimation();
                        for (PendingRevealAnimator startIfNecessary : DotsPageIndicator.this.revealAnimations) {
                            startIfNecessary.startIfNecessary(DotsPageIndicator.this.retreatingJoinX2);
                        }
                    }
                });
            }
            final DotsPageIndicator dotsPageIndicator = DotsPageIndicator.this;
            addListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    DotsPageIndicator.this.cancelJoiningAnimations();
                    DotsPageIndicator.this.clearJoiningFractions();
                    for (int access$1600 : iArr) {
                        DotsPageIndicator.this.setDotRevealFraction(access$1600, 1.0E-5f);
                    }
                    float unused = DotsPageIndicator.this.retreatingJoinX1 = f9;
                    float unused2 = DotsPageIndicator.this.retreatingJoinX2 = f11;
                    DotsPageIndicator.this.postInvalidateOnAnimation();
                }

                public void onAnimationEnd(Animator animator) {
                    float unused = DotsPageIndicator.this.retreatingJoinX1 = -1.0f;
                    float unused2 = DotsPageIndicator.this.retreatingJoinX2 = -1.0f;
                    DotsPageIndicator.this.postInvalidateOnAnimation();
                }
            });
        }
    }

    public class PendingRevealAnimator extends PendingStartAnimator {
        /* access modifiers changed from: private */
        public final int dot;

        public PendingRevealAnimator(int i, StartPredicate startPredicate) {
            super(startPredicate);
            this.dot = i;
            setFloatValues(new float[]{1.0E-5f, 1.0f});
            setDuration(DotsPageIndicator.this.animHalfDuration);
            setInterpolator(DotsPageIndicator.this.interpolator);
            addUpdateListener(new ValueAnimator.AnimatorUpdateListener(DotsPageIndicator.this) {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    PendingRevealAnimator pendingRevealAnimator = PendingRevealAnimator.this;
                    DotsPageIndicator.this.setDotRevealFraction(pendingRevealAnimator.dot, ((Float) valueAnimator.getAnimatedValue()).floatValue());
                }
            });
            addListener(new AnimatorListenerAdapter(DotsPageIndicator.this) {
                public void onAnimationEnd(Animator animator) {
                    PendingRevealAnimator pendingRevealAnimator = PendingRevealAnimator.this;
                    DotsPageIndicator.this.setDotRevealFraction(pendingRevealAnimator.dot, 0.0f);
                    DotsPageIndicator.this.postInvalidateOnAnimation();
                }
            });
        }
    }

    public abstract class StartPredicate {
        protected float thresholdValue;

        /* access modifiers changed from: package-private */
        public abstract boolean shouldStart(float f);

        public StartPredicate(float f) {
            this.thresholdValue = f;
        }
    }

    public class RightwardStartPredicate extends StartPredicate {
        public RightwardStartPredicate(float f) {
            super(f);
        }

        /* access modifiers changed from: package-private */
        public boolean shouldStart(float f) {
            return f > this.thresholdValue;
        }
    }

    public class LeftwardStartPredicate extends StartPredicate {
        public LeftwardStartPredicate(float f) {
            super(f);
        }

        /* access modifiers changed from: package-private */
        public boolean shouldStart(float f) {
            return f < this.thresholdValue;
        }
    }
}
