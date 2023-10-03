package com.android.settings.biometrics.face;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import com.havoc.config.center.C1715R;
import java.util.List;

public class AnimationParticle {
    private int mAnimationState;
    private final int mAssignedColor;
    private final int mBorderWidth;
    private final Rect mBounds;
    private float mCurrentAngle;
    private float mCurrentSize = 10.0f;
    private final int mErrorColor;
    private final ArgbEvaluator mEvaluator;
    private final int mIndex;
    private int mLastAnimationState;
    private final Listener mListener;
    private final float mOffsetTimeSec;
    private final Paint mPaint;
    private float mRingAdjustRate;
    private float mRingCompletionTime;
    private float mRotationSpeed = 0.8f;
    private float mSweepAngle = 0.0f;
    private float mSweepRate = 240.0f;

    public interface Listener {
        void onRingCompleted(int i);
    }

    public AnimationParticle(Context context, Listener listener, Rect rect, int i, int i2, int i3, List<Integer> list) {
        this.mBounds = rect;
        this.mBorderWidth = i;
        this.mEvaluator = new ArgbEvaluator();
        this.mErrorColor = context.getResources().getColor(C1715R.C1716color.face_anim_particle_error, context.getTheme());
        this.mIndex = i2;
        this.mListener = listener;
        float f = ((float) i2) / ((float) i3);
        this.mCurrentAngle = f * 2.0f * 3.1415927f;
        this.mOffsetTimeSec = f * 1.25f * 2.0f * 3.1415927f;
        this.mPaint = new Paint();
        this.mAssignedColor = list.get(i2 % list.size()).intValue();
        this.mPaint.setColor(this.mAssignedColor);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStrokeWidth(this.mCurrentSize);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    public void updateState(int i) {
        if (this.mAnimationState == i) {
            Log.w("AnimationParticle", "Already in state " + i);
            return;
        }
        if (i == 4) {
            this.mPaint.setStyle(Paint.Style.STROKE);
        }
        this.mLastAnimationState = this.mAnimationState;
        this.mAnimationState = i;
    }

    public void setAsPrimary() {
        this.mSweepRate = 480.0f;
    }

    public void update(long j, long j2) {
        if (this.mAnimationState != 4) {
            updateDot(j, j2);
        } else {
            updateRing(j, j2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x007c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateDot(long r6, long r8) {
        /*
            r5 = this;
            float r8 = (float) r8
            r9 = 981668463(0x3a83126f, float:0.001)
            float r8 = r8 * r9
            float r6 = (float) r6
            float r6 = r6 * r9
            float r7 = r5.mRotationSpeed
            r9 = 1061997773(0x3f4ccccd, float:0.8)
            float r7 = r7 / r9
            int r0 = r5.mAnimationState
            r1 = 1073741824(0x40000000, float:2.0)
            r2 = 3
            r3 = 2
            if (r0 == r3) goto L_0x0017
            if (r0 != r2) goto L_0x0027
        L_0x0017:
            float r0 = r5.mRotationSpeed
            r3 = 0
            int r4 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r4 <= 0) goto L_0x0027
            float r1 = r1 * r8
            float r0 = r0 - r1
            float r9 = java.lang.Math.max(r0, r3)
            r5.mRotationSpeed = r9
            goto L_0x0036
        L_0x0027:
            int r0 = r5.mAnimationState
            r3 = 1
            if (r0 != r3) goto L_0x0036
            float r0 = r5.mRotationSpeed
            int r9 = (r0 > r9 ? 1 : (r0 == r9 ? 0 : -1))
            if (r9 >= 0) goto L_0x0036
            float r1 = r1 * r8
            float r0 = r0 + r1
            r5.mRotationSpeed = r0
        L_0x0036:
            float r9 = r5.mCurrentAngle
            float r0 = r5.mRotationSpeed
            float r8 = r8 * r0
            float r9 = r9 + r8
            r5.mCurrentAngle = r9
            r8 = 1084227584(0x40a00000, float:5.0)
            r9 = 1086918619(0x40c90fdb, float:6.2831855)
            float r6 = r6 * r9
            float r9 = r5.mOffsetTimeSec
            float r6 = r6 + r9
            double r0 = (double) r6
            double r0 = java.lang.Math.sin(r0)
            float r6 = (float) r0
            float r6 = r6 * r8
            r8 = 1097859072(0x41700000, float:15.0)
            float r6 = r6 + r8
            r5.mCurrentSize = r6
            float r6 = r5.mCurrentSize
            r8 = 1092616192(0x41200000, float:10.0)
            float r6 = r6 - r8
            float r6 = r6 * r7
            float r6 = r6 + r8
            r5.mCurrentSize = r6
            int r6 = r5.mAssignedColor
            int r8 = r5.mAnimationState
            r9 = 1065353216(0x3f800000, float:1.0)
            if (r8 != r2) goto L_0x007c
            android.animation.ArgbEvaluator r8 = r5.mEvaluator
            float r9 = r9 - r7
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            int r7 = r5.mErrorColor
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            java.lang.Object r6 = r8.evaluate(r9, r6, r7)
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            goto L_0x0097
        L_0x007c:
            int r8 = r5.mLastAnimationState
            if (r8 != r2) goto L_0x0097
            android.animation.ArgbEvaluator r8 = r5.mEvaluator
            float r9 = r9 - r7
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            int r7 = r5.mErrorColor
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            java.lang.Object r6 = r8.evaluate(r9, r6, r7)
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
        L_0x0097:
            android.graphics.Paint r7 = r5.mPaint
            r7.setColor(r6)
            android.graphics.Paint r6 = r5.mPaint
            float r5 = r5.mCurrentSize
            r6.setStrokeWidth(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.biometrics.face.AnimationParticle.updateDot(long, long):void");
    }

    private void updateRing(long j, long j2) {
        float f = ((float) j2) * 0.001f;
        float f2 = ((float) j) * 0.001f;
        if (this.mRingAdjustRate == 0.0f) {
            this.mRingAdjustRate = (15.0f - this.mCurrentSize) / 0.1f;
            if (this.mRingCompletionTime == 0.0f) {
                this.mRingCompletionTime = f2 + 0.1f;
            }
        }
        float f3 = this.mRotationSpeed;
        if (f3 < 0.8f) {
            this.mRotationSpeed = f3 + (2.0f * f);
        }
        this.mCurrentAngle += this.mRotationSpeed * f;
        float f4 = this.mSweepAngle;
        if (f4 < 360.0f) {
            float f5 = this.mSweepRate;
            float f6 = f5 * f;
            this.mSweepAngle = f4 + f6;
            this.mSweepRate = f5 + f6;
        }
        if (this.mSweepAngle > 360.0f) {
            this.mSweepAngle = 360.0f;
            this.mListener.onRingCompleted(this.mIndex);
        }
        if (f2 < 0.1f) {
            this.mCurrentSize += this.mRingAdjustRate * f;
            this.mPaint.setStrokeWidth(this.mCurrentSize);
            return;
        }
        this.mCurrentSize = 15.0f;
        this.mPaint.setStrokeWidth(this.mCurrentSize);
    }

    public void draw(Canvas canvas) {
        if (this.mAnimationState != 4) {
            drawDot(canvas);
        } else {
            drawRing(canvas);
        }
    }

    private void drawDot(Canvas canvas) {
        Rect rect = this.mBounds;
        float exactCenterX = (((float) rect.right) - rect.exactCenterX()) - ((float) this.mBorderWidth);
        Rect rect2 = this.mBounds;
        canvas.drawCircle(this.mBounds.exactCenterX() + (exactCenterX * ((float) Math.cos((double) this.mCurrentAngle))), this.mBounds.exactCenterY() + (((((float) rect2.bottom) - rect2.exactCenterY()) - ((float) this.mBorderWidth)) * ((float) Math.sin((double) this.mCurrentAngle))), this.mCurrentSize, this.mPaint);
    }

    private void drawRing(Canvas canvas) {
        int i = this.mBorderWidth;
        RectF rectF = new RectF((float) i, (float) i, (float) (this.mBounds.width() - this.mBorderWidth), (float) (this.mBounds.height() - this.mBorderWidth));
        Path path = new Path();
        path.arcTo(rectF, (float) Math.toDegrees((double) this.mCurrentAngle), this.mSweepAngle);
        canvas.drawPath(path, this.mPaint);
    }
}
