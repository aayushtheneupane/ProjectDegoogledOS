package com.android.messaging.p041ui.mediapicker.camerafocus;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import java.util.List;

/* renamed from: com.android.messaging.ui.mediapicker.camerafocus.e */
public class C1308e {

    /* renamed from: AJ */
    private int f2080AJ;

    /* renamed from: BJ */
    private int f2081BJ;

    /* renamed from: VI */
    private float f2082VI;
    private float animate;
    private Drawable mDrawable;
    private boolean mEnabled;
    private List mItems;
    private Path mPath;
    private boolean mSelected;
    private float start;

    /* renamed from: zJ */
    private float f2083zJ;

    /* renamed from: Cj */
    public int mo7804Cj() {
        return this.f2081BJ;
    }

    /* renamed from: Dj */
    public float mo7805Dj() {
        return this.f2083zJ;
    }

    /* renamed from: Ej */
    public boolean mo7806Ej() {
        return this.mItems != null;
    }

    /* renamed from: a */
    public void mo7808a(Path path) {
        this.mPath = path;
    }

    public void draw(Canvas canvas) {
        this.mDrawable.draw(canvas);
    }

    public float getCenter() {
        return this.f2082VI;
    }

    public int getInnerRadius() {
        return this.f2080AJ;
    }

    public int getIntrinsicHeight() {
        return this.mDrawable.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        return this.mDrawable.getIntrinsicWidth();
    }

    public List getItems() {
        return this.mItems;
    }

    public Path getPath() {
        return this.mPath;
    }

    public float getStartAngle() {
        return this.start + this.animate;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public boolean isSelected() {
        return this.mSelected;
    }

    public void setAlpha(float f) {
        this.mDrawable.setAlpha((int) (f * 255.0f));
    }

    public void setBounds(int i, int i2, int i3, int i4) {
        this.mDrawable.setBounds(i, i2, i3, i4);
    }

    public void setSelected(boolean z) {
        this.mSelected = z;
    }

    /* renamed from: a */
    public void mo7807a(float f, float f2, int i, int i2) {
        this.start = f;
        this.f2083zJ = f2;
        this.f2080AJ = i;
        this.f2081BJ = i2;
    }
}
