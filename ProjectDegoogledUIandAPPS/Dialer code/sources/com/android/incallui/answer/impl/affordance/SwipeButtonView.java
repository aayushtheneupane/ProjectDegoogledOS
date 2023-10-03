package com.android.incallui.answer.impl.affordance;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import com.android.dialer.R;
import com.android.incallui.answer.impl.utils.FlingAnimationUtils;
import com.android.incallui.answer.impl.utils.Interpolators;

public class SwipeButtonView extends ImageView {
    /* access modifiers changed from: private */
    public ValueAnimator alphaAnimator;
    private AnimatorListenerAdapter alphaEndListener;
    private int centerX;
    private int centerY;
    /* access modifiers changed from: private */
    public ValueAnimator circleAnimator;
    private int circleColor;
    private AnimatorListenerAdapter circleEndListener;
    private final Paint circlePaint;
    /* access modifiers changed from: private */
    public float circleRadius;
    private float circleStartRadius;
    private float circleStartValue;
    private boolean circleWillBeHidden;
    private AnimatorListenerAdapter clipEndListener;
    private final ArgbEvaluator colorInterpolator;
    /* access modifiers changed from: private */
    public boolean finishing;
    private final FlingAnimationUtils flingAnimationUtils;
    private final int inverseColor;
    private boolean launchingAffordance;
    private float maxCircleSize;
    private final int minBackgroundRadius;
    private final int normalColor;
    /* access modifiers changed from: private */
    public Animator previewClipper;
    /* access modifiers changed from: private */
    public View previewView;
    private float restingAlpha;
    /* access modifiers changed from: private */
    public ValueAnimator scaleAnimator;
    private AnimatorListenerAdapter scaleEndListener;
    private int[] tempPoint;
    /* access modifiers changed from: private */
    public float tmageScale;

    public SwipeButtonView(Context context) {
        this(context, (AttributeSet) null, 0, 0);
    }

    private ValueAnimator getAnimatorToRadius(float f) {
        boolean z = true;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{this.circleRadius, f});
        this.circleAnimator = ofFloat;
        this.circleStartValue = this.circleRadius;
        if (f != 0.0f) {
            z = false;
        }
        this.circleWillBeHidden = z;
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float unused = SwipeButtonView.this.circleRadius = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                SwipeButtonView.this.updateIconColor();
                SwipeButtonView.this.invalidate();
            }
        });
        ofFloat.addListener(this.circleEndListener);
        return ofFloat;
    }

    private float getMaxCircleSize() {
        getLocationInWindow(this.tempPoint);
        float f = (float) (this.tempPoint[0] + this.centerX);
        return (float) Math.hypot((double) Math.max(((float) getRootView().getWidth()) - f, f), (double) ((float) (this.tempPoint[1] + this.centerY)));
    }

    /* access modifiers changed from: private */
    public void updateIconColor() {
        getDrawable().mutate().setColorFilter(((Integer) this.colorInterpolator.evaluate(Math.min(1.0f, this.circleRadius / ((float) this.minBackgroundRadius)), Integer.valueOf(this.normalColor), Integer.valueOf(this.inverseColor))).intValue(), PorterDuff.Mode.SRC_ATOP);
    }

    public void finishAnimation(float f, final Runnable runnable) {
        ValueAnimator valueAnimator = this.circleAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        Animator animator = this.previewClipper;
        if (animator != null) {
            animator.cancel();
        }
        this.finishing = true;
        this.circleStartRadius = this.circleRadius;
        final float maxCircleSize2 = getMaxCircleSize();
        ValueAnimator animatorToRadius = getAnimatorToRadius(maxCircleSize2);
        this.flingAnimationUtils.applyDismissing(animatorToRadius, this.circleRadius, maxCircleSize2, f, maxCircleSize2);
        animatorToRadius.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                Runnable runnable = runnable;
                if (runnable != null) {
                    runnable.run();
                }
                boolean unused = SwipeButtonView.this.finishing = false;
                float unused2 = SwipeButtonView.this.circleRadius = maxCircleSize2;
                SwipeButtonView.this.invalidate();
            }
        });
        animatorToRadius.start();
        setImageAlpha(0.0f, true);
        View view = this.previewView;
        if (view != null) {
            view.setVisibility(0);
            this.previewClipper = ViewAnimationUtils.createCircularReveal(this.previewView, getLeft() + this.centerX, getTop() + this.centerY, this.circleRadius, maxCircleSize2);
            this.flingAnimationUtils.applyDismissing(this.previewClipper, this.circleRadius, maxCircleSize2, f, maxCircleSize2);
            this.previewClipper.addListener(this.clipEndListener);
            this.previewClipper.start();
        }
    }

    public float getCircleRadius() {
        return this.circleRadius;
    }

    public float getRestingAlpha() {
        return this.restingAlpha;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.circleRadius > 0.0f || this.finishing) {
            float f = this.circleRadius;
            float f2 = (float) this.minBackgroundRadius;
            float max = (Math.max(0.0f, Math.min(1.0f, (f - f2) / (f2 * 0.5f))) * 0.5f) + 0.5f;
            View view = this.previewView;
            if (view != null && view.getVisibility() == 0) {
                max *= 1.0f - (Math.max(0.0f, this.circleRadius - this.circleStartRadius) / (this.maxCircleSize - this.circleStartRadius));
            }
            this.circlePaint.setColor(Color.argb((int) (((float) Color.alpha(this.circleColor)) * max), Color.red(this.circleColor), Color.green(this.circleColor), Color.blue(this.circleColor)));
            canvas.drawCircle((float) this.centerX, (float) this.centerY, this.circleRadius, this.circlePaint);
        }
        canvas.save();
        float f3 = this.tmageScale;
        canvas.scale(f3, f3, (float) (getWidth() / 2), (float) (getHeight() / 2));
        super.onDraw(canvas);
        canvas.restore();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.centerX = getWidth() / 2;
        this.centerY = getHeight() / 2;
        this.maxCircleSize = getMaxCircleSize();
    }

    public boolean performClick() {
        return isClickable() && super.performClick();
    }

    public void setCircleRadius(float f, boolean z) {
        setCircleRadius(f, z, false);
    }

    public void setCircleRadiusWithoutAnimation(float f) {
        ValueAnimator valueAnimator = this.circleAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        setCircleRadius(f, false, true);
    }

    public void setImageAlpha(float f, boolean z) {
        setImageAlpha(f, z, -1, (Interpolator) null, (Runnable) null);
    }

    public void setImageScale(float f, boolean z) {
        setImageScale(f, z, -1, (Interpolator) null);
    }

    public void setPreviewView(View view) {
        View view2 = this.previewView;
        this.previewView = view;
        View view3 = this.previewView;
        if (view3 != null) {
            view3.setVisibility(this.launchingAffordance ? view2.getVisibility() : 4);
        }
    }

    public SwipeButtonView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0);
    }

    private void setCircleRadius(float f, boolean z, boolean z2) {
        Interpolator interpolator;
        View view;
        boolean z3 = (this.circleAnimator != null && this.circleWillBeHidden) || (this.circleAnimator == null && this.circleRadius == 0.0f);
        int i = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
        boolean z4 = i == 0;
        if (!(z3 != z4 && !z2)) {
            ValueAnimator valueAnimator = this.circleAnimator;
            if (valueAnimator == null) {
                this.circleRadius = f;
                updateIconColor();
                invalidate();
                if (z4 && (view = this.previewView) != null) {
                    view.setVisibility(4);
                }
            } else if (!this.circleWillBeHidden) {
                valueAnimator.getValues()[0].setFloatValues(new float[]{this.circleStartValue + (f - ((float) this.minBackgroundRadius)), f});
                ValueAnimator valueAnimator2 = this.circleAnimator;
                valueAnimator2.setCurrentPlayTime(valueAnimator2.getCurrentPlayTime());
            }
        } else {
            ValueAnimator valueAnimator3 = this.circleAnimator;
            if (valueAnimator3 != null) {
                valueAnimator3.cancel();
            }
            Animator animator = this.previewClipper;
            if (animator != null) {
                animator.cancel();
            }
            ValueAnimator animatorToRadius = getAnimatorToRadius(f);
            if (i == 0) {
                interpolator = Interpolators.FAST_OUT_LINEAR_IN;
            } else {
                interpolator = Interpolators.LINEAR_OUT_SLOW_IN;
            }
            animatorToRadius.setInterpolator(interpolator);
            long j = 250;
            if (!z) {
                j = Math.min((long) ((Math.abs(this.circleRadius - f) / ((float) this.minBackgroundRadius)) * 80.0f), 200);
            }
            animatorToRadius.setDuration(j);
            animatorToRadius.start();
            View view2 = this.previewView;
            if (view2 != null && view2.getVisibility() == 0) {
                this.previewView.setVisibility(0);
                this.previewClipper = ViewAnimationUtils.createCircularReveal(this.previewView, getLeft() + this.centerX, getTop() + this.centerY, this.circleRadius, f);
                this.previewClipper.setInterpolator(interpolator);
                this.previewClipper.setDuration(j);
                this.previewClipper.addListener(this.clipEndListener);
                this.previewClipper.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        SwipeButtonView.this.previewView.setVisibility(4);
                    }
                });
                this.previewClipper.start();
            }
        }
    }

    public void setImageAlpha(float f, boolean z, long j, Interpolator interpolator, final Runnable runnable) {
        ValueAnimator valueAnimator = this.alphaAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        if (this.launchingAffordance) {
            f = 0.0f;
        }
        int i = (int) (f * 255.0f);
        final Drawable background = getBackground();
        if (!z) {
            if (background != null) {
                background.mutate().setAlpha(i);
            }
            setImageAlpha(i);
            return;
        }
        int imageAlpha = getImageAlpha();
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{imageAlpha, i});
        this.alphaAnimator = ofInt;
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                Drawable drawable = background;
                if (drawable != null) {
                    drawable.mutate().setAlpha(intValue);
                }
                SwipeButtonView.this.setImageAlpha(intValue);
            }
        });
        ofInt.addListener(this.alphaEndListener);
        if (interpolator == null) {
            interpolator = f == 0.0f ? Interpolators.FAST_OUT_LINEAR_IN : Interpolators.LINEAR_OUT_SLOW_IN;
        }
        ofInt.setInterpolator(interpolator);
        if (j == -1) {
            j = (long) (Math.min(1.0f, ((float) Math.abs(imageAlpha - i)) / 255.0f) * 200.0f);
        }
        ofInt.setDuration(j);
        if (runnable != null) {
            ofInt.addListener(new AnimatorListenerAdapter(this) {
                boolean cancelled;

                public void onAnimationCancel(Animator animator) {
                    this.cancelled = true;
                }

                public void onAnimationEnd(Animator animator) {
                    if (!this.cancelled) {
                        runnable.run();
                    }
                }
            });
        }
        ofInt.start();
    }

    public void setImageScale(float f, boolean z, long j, Interpolator interpolator) {
        ValueAnimator valueAnimator = this.scaleAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        if (!z) {
            this.tmageScale = f;
            invalidate();
            return;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{this.tmageScale, f});
        this.scaleAnimator = ofFloat;
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float unused = SwipeButtonView.this.tmageScale = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                SwipeButtonView.this.invalidate();
            }
        });
        ofFloat.addListener(this.scaleEndListener);
        if (interpolator == null) {
            if (f == 0.0f) {
                interpolator = Interpolators.FAST_OUT_LINEAR_IN;
            } else {
                interpolator = Interpolators.LINEAR_OUT_SLOW_IN;
            }
        }
        ofFloat.setInterpolator(interpolator);
        if (j == -1) {
            j = (long) (Math.min(1.0f, Math.abs(this.tmageScale - f) / 0.19999999f) * 200.0f);
        }
        ofFloat.setDuration(j);
        ofFloat.start();
    }

    public SwipeButtonView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public SwipeButtonView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.tempPoint = new int[2];
        this.tmageScale = 1.0f;
        this.restingAlpha = 0.87f;
        this.clipEndListener = new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                Animator unused = SwipeButtonView.this.previewClipper = null;
            }
        };
        this.circleEndListener = new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                ValueAnimator unused = SwipeButtonView.this.circleAnimator = null;
            }
        };
        this.scaleEndListener = new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                ValueAnimator unused = SwipeButtonView.this.scaleAnimator = null;
            }
        };
        this.alphaEndListener = new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                ValueAnimator unused = SwipeButtonView.this.alphaAnimator = null;
            }
        };
        this.circlePaint = new Paint();
        this.circlePaint.setAntiAlias(true);
        this.circleColor = -1;
        this.circlePaint.setColor(this.circleColor);
        this.normalColor = -1;
        this.inverseColor = -16777216;
        this.minBackgroundRadius = context.getResources().getDimensionPixelSize(R.dimen.answer_affordance_min_background_radius);
        this.colorInterpolator = new ArgbEvaluator();
        this.flingAnimationUtils = new FlingAnimationUtils(context, 0.3f);
    }
}
