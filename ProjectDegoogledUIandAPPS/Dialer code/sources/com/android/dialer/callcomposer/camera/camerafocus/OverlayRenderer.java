package com.android.dialer.callcomposer.camera.camerafocus;

import android.graphics.Canvas;
import com.android.dialer.callcomposer.camera.camerafocus.RenderOverlay;

public abstract class OverlayRenderer implements RenderOverlay.Renderer {
    private int bottom;
    private int left;
    protected RenderOverlay overlay;
    private int right;
    private int top;
    private boolean visible;

    public void draw(Canvas canvas) {
        if (this.visible) {
            onDraw(canvas);
        }
    }

    public int getHeight() {
        return this.bottom - this.top;
    }

    public int getWidth() {
        return this.right - this.left;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void layout(int i, int i2, int i3, int i4) {
        this.left = i;
        this.right = i3;
        this.top = i2;
        this.bottom = i4;
    }

    public abstract void onDraw(Canvas canvas);

    public void setVisible(boolean z) {
        this.visible = z;
        RenderOverlay renderOverlay = this.overlay;
        if (renderOverlay != null) {
            renderOverlay.update();
        }
    }
}
