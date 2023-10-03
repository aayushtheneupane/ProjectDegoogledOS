package com.android.dialer.voicemail.settings;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.p000v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.Button;
import com.android.dialer.R;

public class RecordButton extends Button {
    private RectF bodyRect;
    private final int centerIconRadius = getResources().getDimensionPixelSize(R.dimen.center_icon_radius);
    private Rect centerIconRect;
    private Drawable currentCenterDrawable;
    private float mainTrackFraction;
    private Paint mainTrackPaint;
    private Drawable playingDrawable;
    private Drawable readyDrawable;
    private Drawable recordedDrawable;
    private Drawable recordingDrawable;
    private float secondaryTrackFraction;
    private Paint secondaryTrackPaint;
    private final float trackWidth = ((float) getResources().getDimensionPixelSize(R.dimen.track_width));

    public RecordButton(Context context) {
        super(context);
        init();
    }

    private Paint getBasePaint(int i) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(this.trackWidth);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(ContextCompat.getColor(getContext(), i));
        return paint;
    }

    private void init() {
        this.readyDrawable = ContextCompat.getDrawable(getContext(), R.drawable.start_recording_drawable);
        this.recordingDrawable = ContextCompat.getDrawable(getContext(), R.drawable.stop_recording_drawable);
        this.recordedDrawable = ContextCompat.getDrawable(getContext(), R.drawable.start_playback_drawable);
        this.playingDrawable = ContextCompat.getDrawable(getContext(), R.drawable.stop_playback_drawable);
        Drawable findDrawableByLayerId = ((LayerDrawable) this.recordedDrawable).findDrawableByLayerId(R.id.play_icon);
        findDrawableByLayerId.mutate().setTint(-1);
        ((LayerDrawable) this.recordedDrawable).setDrawableByLayerId(R.id.play_icon, findDrawableByLayerId);
        Drawable findDrawableByLayerId2 = ((LayerDrawable) this.readyDrawable).findDrawableByLayerId(R.id.record_icon);
        findDrawableByLayerId2.mutate().setTint(-1);
        ((LayerDrawable) this.readyDrawable).setDrawableByLayerId(R.id.record_icon, findDrawableByLayerId2);
        this.mainTrackPaint = getBasePaint(R.color.dialer_call_green);
        this.secondaryTrackPaint = getBasePaint(R.color.dialer_call_green);
        this.secondaryTrackPaint.setAlpha(64);
        setState(1);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(this.bodyRect, -90.0f, this.secondaryTrackFraction * 360.0f, false, this.secondaryTrackPaint);
        canvas.drawArc(this.bodyRect, -90.0f, this.mainTrackFraction * 360.0f, false, this.mainTrackPaint);
        this.currentCenterDrawable.setBounds(this.centerIconRect);
        this.currentCenterDrawable.draw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        float f = this.trackWidth;
        float min = (((float) Math.min(i, i2)) / 2.0f) - f;
        float f2 = ((float) i) / 2.0f;
        float f3 = (f / 2.0f) + min;
        this.bodyRect = new RectF(f2 - min, f3 - min, f2 + min, min + f3);
        int i5 = (int) f2;
        int i6 = this.centerIconRadius;
        int i7 = (int) f3;
        this.centerIconRect = new Rect(i5 - i6, i7 - i6, i5 + i6, i7 + i6);
    }

    public void setState(int i) {
        if (i == 1) {
            this.mainTrackPaint = getBasePaint(R.color.dialer_call_green);
            this.secondaryTrackPaint = getBasePaint(R.color.dialer_call_green);
            this.secondaryTrackPaint.setAlpha(64);
            this.currentCenterDrawable = this.readyDrawable;
        } else if (i == 2) {
            this.mainTrackPaint = getBasePaint(R.color.dialer_red);
            this.secondaryTrackPaint = getBasePaint(R.color.dialer_red);
            this.secondaryTrackPaint.setAlpha(64);
            this.currentCenterDrawable = this.recordingDrawable;
        } else if (i == 3) {
            this.mainTrackPaint = getBasePaint(R.color.google_blue_500);
            this.secondaryTrackPaint = getBasePaint(R.color.google_blue_50);
            this.currentCenterDrawable = this.recordedDrawable;
        } else if (i == 4) {
            this.mainTrackPaint = getBasePaint(R.color.google_blue_500);
            this.secondaryTrackPaint = getBasePaint(R.color.google_blue_50);
            this.currentCenterDrawable = this.playingDrawable;
        } else {
            throw new RuntimeException("Invalid button state");
        }
        refreshDrawableState();
        invalidate();
    }

    public void setTracks(float f, float f2) {
        this.mainTrackFraction = f;
        this.secondaryTrackFraction = f2;
        invalidate();
    }

    public RecordButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public RecordButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }
}
