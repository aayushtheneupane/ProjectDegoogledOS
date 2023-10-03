package com.android.p032ex.chips;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import com.android.p032ex.chips.p033a.C0660b;

/* renamed from: com.android.ex.chips.ka */
final class C0685ka extends View.DragShadowBuilder {

    /* renamed from: Ve */
    private final C0660b f804Ve;

    public C0685ka(C0697qa qaVar, C0660b bVar) {
        this.f804Ve = bVar;
    }

    public void onDrawShadow(Canvas canvas) {
        this.f804Ve.draw(canvas);
    }

    public void onProvideShadowMetrics(Point point, Point point2) {
        Rect bounds = this.f804Ve.getBounds();
        point.set(bounds.width(), bounds.height());
        point2.set(bounds.centerX(), bounds.centerY());
    }
}
