package com.android.dialer.callcomposer.camera.camerafocus;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import java.util.List;

public class PieItem {
    private float animate;
    private float center;
    private Drawable drawable;
    private boolean enabled;
    private int inner;
    private List<PieItem> items;
    private int outer;
    private Path path;
    private boolean selected;
    private float start;
    private float sweep;

    public void draw(Canvas canvas) {
        this.drawable.draw(canvas);
    }

    public float getCenter() {
        return this.center;
    }

    public int getInnerRadius() {
        return this.inner;
    }

    public int getIntrinsicHeight() {
        return this.drawable.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        return this.drawable.getIntrinsicWidth();
    }

    public List<PieItem> getItems() {
        return this.items;
    }

    public int getOuterRadius() {
        return this.outer;
    }

    public Path getPath() {
        return this.path;
    }

    public float getStartAngle() {
        return this.start + this.animate;
    }

    public float getSweep() {
        return this.sweep;
    }

    public boolean hasItems() {
        return this.items != null;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setAlpha(float f) {
        this.drawable.setAlpha((int) (f * 255.0f));
    }

    public void setBounds(int i, int i2, int i3, int i4) {
        this.drawable.setBounds(i, i2, i3, i4);
    }

    public void setGeometry(float f, float f2, int i, int i2) {
        this.start = f;
        this.sweep = f2;
        this.inner = i;
        this.outer = i2;
    }

    public void setPath(Path path2) {
        this.path = path2;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }
}
