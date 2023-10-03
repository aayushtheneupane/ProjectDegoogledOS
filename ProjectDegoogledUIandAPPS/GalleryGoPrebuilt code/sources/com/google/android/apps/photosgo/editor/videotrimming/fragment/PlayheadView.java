package com.google.android.apps.photosgo.editor.videotrimming.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.apps.photosgo.R;

/* compiled from: PG */
public final class PlayheadView extends View {

    /* renamed from: a */
    private final int f4840a;

    /* renamed from: b */
    private Drawable f4841b;

    public PlayheadView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4840a = context.getResources().getColor(R.color.photosgo_videotrimming_trimview_playhead_color);
    }

    /* JADX INFO: finally extract failed */
    public final void onDraw(Canvas canvas) {
        int i;
        super.onDraw(canvas);
        int save = canvas.save();
        try {
            canvas.clipRect((-getWidth()) / 2, 0, getWidth() / 2, getHeight());
            canvas.drawColor(this.f4840a);
            canvas.restoreToCount(save);
            if (isAccessibilityFocused()) {
                Drawable drawable = this.f4841b;
                if (drawable == null) {
                    drawable = getContext().getResources().getDrawable(R.drawable.view_accessibility_focused);
                    this.f4841b = drawable;
                }
                if (drawable != null) {
                    new Object[1][0] = drawable.getBounds();
                    int save2 = canvas.save();
                    try {
                        if (drawable instanceof ShapeDrawable) {
                            i = Math.round(((float) getWidth()) + ((ShapeDrawable) drawable).getPaint().getStrokeWidth() + 0.5f);
                        } else {
                            i = getWidth() * 3;
                        }
                        drawable.setBounds(-i, 0, i, getHeight());
                        drawable.draw(canvas);
                    } finally {
                        canvas.restoreToCount(save2);
                    }
                }
            }
        } catch (Throwable th) {
            canvas.restoreToCount(save);
            throw th;
        }
    }
}
