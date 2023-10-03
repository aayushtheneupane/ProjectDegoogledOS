package com.google.android.apps.photosgo.editor.videotrimming.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.google.android.apps.photosgo.R;

/* compiled from: PG */
public final class TrimHandleView extends View {

    /* renamed from: a */
    public final Paint f4845a;

    /* renamed from: b */
    private final Paint f4846b;

    /* renamed from: c */
    private final float f4847c;

    /* renamed from: d */
    private final ViewOutlineProvider f4848d;

    public TrimHandleView(Context context) {
        this(context, (AttributeSet) null);
    }

    public TrimHandleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4848d = new ccl(this);
        Resources resources = context.getResources();
        float dimension = resources.getDimension(R.dimen.photosgo_videotrimming_trimview_view_handle_width);
        this.f4847c = resources.getDimension(R.dimen.photosgo_videotrimming_trimview_gripdot_radius);
        Paint paint = new Paint();
        this.f4845a = paint;
        paint.setColor(resources.getColor(R.color.photosgo_videotrimming_trimview_handle_color));
        this.f4845a.setStrokeCap(Paint.Cap.ROUND);
        this.f4845a.setDither(true);
        this.f4845a.setAntiAlias(true);
        this.f4845a.setStrokeWidth(dimension);
        Paint paint2 = new Paint(this.f4845a);
        this.f4846b = paint2;
        paint2.setColor(resources.getColor(R.color.quantum_grey300));
        setOutlineProvider(this.f4848d);
    }

    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        try {
            float width = (float) (getWidth() / 2);
            float height = (float) (getHeight() / 2);
            float strokeWidth = this.f4845a.getStrokeWidth() / 2.0f;
            canvas.drawLine(width, ((float) getTop()) + strokeWidth, width, ((float) getBottom()) - strokeWidth, this.f4845a);
            float f = this.f4847c;
            canvas.drawCircle(width, height - (f * 3.0f), f, this.f4846b);
            canvas.drawCircle(width, height, this.f4847c, this.f4846b);
            float f2 = this.f4847c;
            canvas.drawCircle(width, height + (3.0f * f2), f2, this.f4846b);
        } finally {
            canvas.restore();
        }
    }
}
