package com.android.incallui.incall.impl;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.p000v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import com.android.dialer.R;
import com.android.dialer.common.Assert;

public class InCallPaginator extends View implements ViewPager.OnPageChangeListener {
    private Paint activeDotPaintPortrait;
    private int dotRadius;
    private int dotsSeparation;
    private Paint inactiveDotPaintPortrait;
    private Path inactiveDotPath;
    private boolean pageChanged;
    private float progress;
    private boolean toFirstPage;
    private ValueAnimator transitionAnimator;
    private boolean useModeSwitchTransition;

    public InCallPaginator(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.dotRadius = getResources().getDimensionPixelSize(R.dimen.paginator_dot_radius);
        this.dotsSeparation = getResources().getDimensionPixelSize(R.dimen.paginator_dots_separation);
        int color = context.getColor(R.color.paginator_dot);
        int color2 = context.getColor(R.color.paginator_path);
        this.activeDotPaintPortrait = new Paint(1);
        this.activeDotPaintPortrait.setColor(color);
        this.inactiveDotPaintPortrait = new Paint(1);
        this.inactiveDotPaintPortrait.setColor(color2);
        this.inactiveDotPath = new Path();
        this.transitionAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.transitionAnimator.setInterpolator((TimeInterpolator) null);
        this.transitionAnimator.setCurrentFraction(0.0f);
        this.transitionAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                InCallPaginator.this.lambda$init$0$InCallPaginator(valueAnimator);
            }
        });
    }

    public /* synthetic */ void lambda$init$0$InCallPaginator(ValueAnimator valueAnimator) {
        invalidate();
    }

    public void onDraw(Canvas canvas) {
        Canvas canvas2 = canvas;
        super.onDraw(canvas);
        int width = getWidth() / 2;
        int height = getHeight() / 2;
        float floatValue = ((Float) this.transitionAnimator.getAnimatedValue()).floatValue();
        this.inactiveDotPath.reset();
        if (this.useModeSwitchTransition) {
            int i = this.dotRadius;
            float f = (((float) ((i * 2) + this.dotsSeparation)) * floatValue) + ((float) (i * 2));
            float min = (1.0f - (Math.min(floatValue, 0.5f) * 2.0f)) * ((float) i);
            int i2 = this.dotRadius;
            float f2 = (float) ((this.dotsSeparation / 2) + i2);
            if (this.toFirstPage) {
                float f3 = (float) width;
                float f4 = (f3 - f2) - ((float) i2);
                this.inactiveDotPath.addRoundRect(f4, (float) (height - i2), f4 + f, (float) (height + i2), (float) i2, (float) i2, Path.Direction.CW);
                this.inactiveDotPath.addCircle(f3 + f2, (float) height, min, Path.Direction.CW);
            } else {
                float f5 = (float) width;
                float f6 = f5 + f2 + ((float) i2);
                this.inactiveDotPath.addRoundRect(f6 - f, (float) (height - i2), f6, (float) (height + i2), (float) i2, (float) i2, Path.Direction.CW);
                this.inactiveDotPath.addCircle(f5 - f2, (float) height, min, Path.Direction.CW);
            }
        } else {
            float f7 = ((float) this.dotsSeparation) / 2.0f;
            int i3 = this.dotRadius;
            float f8 = f7 - ((((float) i3) + f7) * floatValue);
            float f9 = (((float) i3) * 2.0f) + f7;
            float f10 = (float) width;
            this.inactiveDotPath.addRoundRect(f10 - f9, (float) (height - i3), f10 - f8, (float) (height + i3), (float) i3, (float) i3, Path.Direction.CW);
            Path path = this.inactiveDotPath;
            float f11 = f10 + f8;
            int i4 = this.dotRadius;
            path.addRoundRect(f11, (float) (height - i4), f10 + f9, (float) (height + i4), (float) i4, (float) i4, Path.Direction.CW);
        }
        canvas2.drawPath(this.inactiveDotPath, this.inactiveDotPaintPortrait);
        float f12 = this.toFirstPage ? 1.0f - (this.progress * 2.0f) : (this.progress * 2.0f) - 1.0f;
        int i5 = this.dotRadius;
        canvas2.drawCircle(((float) width) + (f12 * ((float) ((this.dotsSeparation / 2) + i5))), (float) height, (float) i5, this.activeDotPaintPortrait);
    }

    public void onPageScrollStateChanged(int i) {
        if (i == 0) {
            boolean z = !this.pageChanged;
            if (this.transitionAnimator.getAnimatedFraction() > 0.0f) {
                this.useModeSwitchTransition = !z;
                this.transitionAnimator.cancel();
                this.transitionAnimator.reverse();
            }
            this.pageChanged = false;
        } else if (i == 1 && this.transitionAnimator.getAnimatedFraction() < 1.0f) {
            this.transitionAnimator.setCurrentFraction(this.progress);
            this.useModeSwitchTransition = false;
            this.transitionAnimator.cancel();
            this.transitionAnimator.start();
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        setProgress(f, i != 0);
    }

    public void onPageSelected(int i) {
        this.pageChanged = true;
    }

    public void setProgress(float f, boolean z) {
        this.progress = f;
        this.toFirstPage = z;
        if (this.transitionAnimator.isStarted() && f > this.transitionAnimator.getAnimatedFraction()) {
            this.transitionAnimator.setCurrentFraction(f);
        }
        invalidate();
    }

    public void setupWithViewPager(ViewPager viewPager) {
        Assert.checkArgument(viewPager.getAdapter().getCount() == 2, "Invalid page count.", new Object[0]);
        viewPager.addOnPageChangeListener(this);
    }

    public InCallPaginator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }
}
