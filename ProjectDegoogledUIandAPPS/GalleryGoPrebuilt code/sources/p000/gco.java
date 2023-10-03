package p000;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

/* renamed from: gco */
/* compiled from: PG */
public final class gco extends LinearLayout.LayoutParams {

    /* renamed from: a */
    public int f10949a = 1;

    /* renamed from: b */
    public Interpolator f10950b;

    public gco() {
        super(-1, -2);
    }

    public gco(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, gcs.f10966b);
        this.f10949a = obtainStyledAttributes.getInt(0, 0);
        if (obtainStyledAttributes.hasValue(1)) {
            this.f10950b = AnimationUtils.loadInterpolator(context, obtainStyledAttributes.getResourceId(1, 0));
        }
        obtainStyledAttributes.recycle();
    }

    public gco(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
    }

    public gco(ViewGroup.MarginLayoutParams marginLayoutParams) {
        super(marginLayoutParams);
    }

    public gco(LinearLayout.LayoutParams layoutParams) {
        super(layoutParams);
    }
}
