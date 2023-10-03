package p000;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* renamed from: vz */
/* compiled from: PG */
public class C0599vz extends ViewGroup.MarginLayoutParams {

    /* renamed from: g */
    public float f16179g;

    /* renamed from: h */
    public int f16180h;

    public C0599vz(int i) {
        super(i, -2);
        this.f16180h = -1;
        this.f16179g = 0.0f;
    }

    public C0599vz(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f16180h = -1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0435px.f15586n);
        this.f16179g = obtainStyledAttributes.getFloat(3, 0.0f);
        this.f16180h = obtainStyledAttributes.getInt(0, -1);
        obtainStyledAttributes.recycle();
    }

    public C0599vz(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        this.f16180h = -1;
    }
}
