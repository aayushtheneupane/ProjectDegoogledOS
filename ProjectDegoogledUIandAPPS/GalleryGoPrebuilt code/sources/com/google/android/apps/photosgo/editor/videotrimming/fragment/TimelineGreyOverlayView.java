package com.google.android.apps.photosgo.editor.videotrimming.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.apps.photosgo.R;

/* compiled from: PG */
public final class TimelineGreyOverlayView extends View {

    /* renamed from: a */
    public final RectF f4842a;

    /* renamed from: b */
    public final RectF f4843b;

    /* renamed from: c */
    private final Paint f4844c;

    public TimelineGreyOverlayView(Context context) {
        this(context, (AttributeSet) null);
    }

    public TimelineGreyOverlayView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4842a = new RectF();
        this.f4843b = new RectF();
        Paint paint = new Paint();
        this.f4844c = paint;
        paint.setColor(context.getResources().getColor(R.color.quantum_grey800));
        this.f4844c.setAlpha(204);
    }

    public final void draw(Canvas canvas) {
        super.draw(canvas);
        int height = getHeight();
        if (this.f4842a.width() > 0.0f) {
            this.f4842a.bottom = (float) height;
            canvas.drawRect(this.f4842a, this.f4844c);
        }
        if (this.f4843b.width() > 0.0f) {
            this.f4843b.bottom = (float) height;
            canvas.drawRect(this.f4843b, this.f4844c);
        }
    }
}
